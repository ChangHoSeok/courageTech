
package kr.pe.courage.tech.das.log.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import kr.pe.courage.common.storage.IStorageService;
import kr.pe.courage.common.storage.StorageFile;
import kr.pe.courage.common.storage.StorageUtils;
import kr.pe.courage.tech.das.log.service.LogDetailService;
import kr.pe.courage.tech.das.log.service.LogDetailVO;
import kr.pe.courage.tech.das.log.service.LogFileRceptService;
import kr.pe.courage.tech.das.log.service.LogFileRceptVO;
import kr.pe.courage.tech.das.log.service.LogRPSService;
import kr.pe.courage.tech.das.log.service.LogRPSVO;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * <pre>
 * kr.pe.courage.tech.das.log.service.impl
 * LogFileRceptProcess.java
 * </pre>
 * 
 * @Author : ChangHo Seok
 * @Date : 2016. 8. 11.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 8. 11., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
@Scope("prototype")
@Component("logFileRceptProcess")
public class LogFileRceptProcess implements Runnable {
	private final Logger logger = Logger.getLogger(this.getClass());
	
	private LogFileRceptVO logFileRceptVO;

	public void setLogFileRceptVO(LogFileRceptVO logFileRceptVO) {
		this.logFileRceptVO = logFileRceptVO;
	}

	@Resource(name = "logFileRceptService")
	private LogFileRceptService logFileRceptService;
	
	@Resource(name = "logDetailService")
	private LogDetailService logDetailService;
	
	@Resource(name = "logRPSService")
	private LogRPSService logRPSService;
	
	@Resource(name = "courageStorageService")
	private IStorageService storageService;
	
	@Resource(name = "txManager")
	private PlatformTransactionManager txManager;

	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
		boolean isSuccess = true;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
		
		// 작업상태 진행중으로 변경
		logFileRceptVO.setProcessSttus(LogFileRceptVO.PROCESS_STTUS_START);
		logFileRceptService.updateProcessSttus(logFileRceptVO);
		
		// Start Transaction
		TransactionStatus tx = txManager.getTransaction(new DefaultTransactionDefinition());
		
		logger.info("## logFileRceptProcess Start");
		logger.info("## groupId = " + logFileRceptVO.getGroupId() + ", rceptId = " + logFileRceptVO.getRceptId());
		
		try {
			if (logFileRceptVO != null) {
				StorageFile storageFile = new StorageFile(logFileRceptVO.getRceptFileId());
				
				if (logFileRceptVO.getRceptTy().equals(LogFileRceptVO.RECPT_TYPE_NEW)) {
					// 데이터 삭제 후 신규 접수
					logDetailService.truncateLogDetail(new LogDetailVO(logFileRceptVO.getGroupId()));
				}
				
				List<StorageFile> storageFileList = storageService.selectFileList(storageFile);
				
				for (StorageFile rceptFile : storageFileList) {
					logger.info("## 접수 파일 : " + rceptFile.getFileNm());
					File file = StorageUtils.get(rceptFile);
					
					BufferedReader br = new BufferedReader(new FileReader(file));
					
					String line = "";
					String logData = "";
					
					int rceptCnt = 0;
					
					while ((line = br.readLine()) != null) {
						LogDetailVO logDetailVO = new LogDetailVO();
						logDetailVO.setGroupId(logFileRceptVO.getGroupId());
						logDetailVO.setRceptId(logFileRceptVO.getRceptId());
						
						if (Pattern.matches("^(.*)(\\[UCRequest\\]).+(\\[TS\\]).+", line)) {
							logData = line.substring(line.indexOf("[UCRequest]"));
							
							logDetailVO.setLogDataChrctr1(logData.substring(logData.indexOf("]") + 1, logData.indexOf("-"))); //CertCn
							
							logData = logData.substring(logData.indexOf("-") + 1);
							String[] processData = logData.split("/");
							
							logDetailVO.setLogDataChrctr2(processData[0].replaceAll("\\D", "").substring(0, 8));
							logDetailVO.setLogDataChrctr3(processData[0].replaceAll("\\D", "")); // Start DT
							logDetailVO.setLogDataChrctr4(processData[1].replaceAll("\\D", "")); // Send DT
							logDetailVO.setLogDataChrctr5(processData[2].replaceAll("\\D", "")); // End DT
							
							cal.setTime(sdf.parse(logDetailVO.getLogDataChrctr3()));
							logDetailVO.setLogDataNumber1(cal.getTimeInMillis()); // Start DT TimeMillis
							logDetailVO.setLogDataNumber2(Long.parseLong(processData[3].replaceAll("\\D", ""))); // Rcv-Snd TimeMillis
							logDetailVO.setLogDataNumber3(Long.parseLong(processData[4].replaceAll("\\D", ""))); // Total TimeMillis
							
							logDetailService.insert(logDetailVO);
							rceptCnt++;
						}
					}
					
					logger.info("## 접수 완료 : " + rceptFile.getFileNm() + " [" + rceptCnt + "]");
				}
				
				logger.info("## RPS 데이터 등록 - begin");
				
				LogDetailVO logDetailVO = new LogDetailVO();
				logDetailVO.setGroupId(logFileRceptVO.getGroupId());
				logDetailVO.setRceptId(logFileRceptVO.getRceptId());
				List<LogDetailVO> rpsStdrList = logDetailService.selectStdrDataList(logDetailVO);
				
				for (LogDetailVO stdrVO : rpsStdrList) {
					LogRPSVO logRPSVO = new LogRPSVO();
					logRPSVO.setGroupId(stdrVO.getGroupId());
					logRPSVO.setStdrDe(stdrVO.getLogDataChrctr2());
					
					logRPSService.deleteLogRPS(logRPSVO);
					logRPSService.insertLogRPS(logRPSVO);
				}
				
				logger.info("## RPS 데이터 등록 - end");
				
			} else {
				logger.warn("## 접수대상 정보가 존재하지 않습니다. logFileRceptVO is null");
			}
			
			txManager.commit(tx);
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
			txManager.rollback(tx);
		} finally {
			// 작업결과 저장
			if (isSuccess) {
				logFileRceptVO.setProcessSttus(LogFileRceptVO.PROCESS_STTUS_COMPT);
			} else {
				logFileRceptVO.setProcessSttus(LogFileRceptVO.PROCESS_STTUS_ERROR);
			}
			
			logFileRceptService.updateProcessSttus(logFileRceptVO);
		}
		
		
		logger.info("## process Timemillis : " + (System.currentTimeMillis() - startTime));
		logger.info("## logFileRceptProcess End");
	}

}
