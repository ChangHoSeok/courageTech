
package kr.pe.courage.tech.das.log.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import kr.pe.courage.common.storage.IStorageService;
import kr.pe.courage.common.storage.Storage;
import kr.pe.courage.common.storage.StorageFile;
import kr.pe.courage.common.utils.Util;
import kr.pe.courage.tech.das.log.service.LogDetailService;
import kr.pe.courage.tech.das.log.service.LogDetailVO;
import kr.pe.courage.tech.das.log.service.LogFileRceptService;
import kr.pe.courage.tech.das.log.service.LogFileRceptVO;
import kr.pe.courage.tech.das.log.service.LogRPSService;
import kr.pe.courage.tech.das.log.service.LogRPSVO;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * <pre>
 * kr.pe.courage.tech.das.log.service.impl
 * LogFileRceptServiceImpl.java
 * </pre>
 * 
 * @Author : ChangHo Seok
 * @Date : 2016. 7. 22.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 7. 22., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
@Service("logFileRceptService")
public class LogFileRceptServiceImpl implements LogFileRceptService {
	@Resource(name = "logFileRceptDAO")
	private LogFileRceptDAO logFileRceptDAO;

	@Resource(name = "courageStorageService")
	private IStorageService storageService;
	
	@Resource(name = "logDetailService")
	private LogDetailService logDetailService;
	
	@Resource(name = "logRPSService")
	private LogRPSService logRPSService;

	@Override
	public List<LogFileRceptVO> selectLogFileRceptList(LogFileRceptVO logFileRceptVO) throws EgovBizException {
		return logFileRceptDAO.selectList(logFileRceptVO);
	}

	@Override
	public int selectLogFileRceptListCount(LogFileRceptVO logFileRceptVO) throws EgovBizException {
		return logFileRceptDAO.selectListCount(logFileRceptVO);
	}

	@Override
	public LogFileRceptVO selectLogFileRceptDetail(LogFileRceptVO logFileRceptVO) throws EgovBizException {
		LogFileRceptVO resultVO = logFileRceptDAO.getById(logFileRceptVO);

		if (resultVO != null && !"".equals(Util.nvl(resultVO.getRceptFileId()))) {
			try {
				resultVO.setAtchFileList(storageService.selectFileList(new StorageFile(resultVO.getRceptFileId())));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return resultVO;
	}

	@Override
	public void insertLogFileRcept(LogFileRceptVO logFileRceptVO) throws EgovBizException {

		// 로그파일 등록 처리
		if (logFileRceptVO.getUploadAtchFiles() != null) {
			List<StorageFile> storageFileList = new ArrayList<StorageFile>();
			String tempStoragePath = Storage.getInstance().getTempPath(logFileRceptVO.getJsessionId());

			for (String atchFile : logFileRceptVO.getUploadAtchFiles()) {
				File uploadFile = new File(tempStoragePath + File.separator + atchFile);

				StorageFile storageFile = new StorageFile();
				storageFile.setFileLocation(Storage.FILE_LOCATION_STORAGE);
				storageFile.setUploadFile(uploadFile);

				storageFileList.add(storageFile);
			}

			try {
				logFileRceptVO.setRceptFileId(storageService.insertFile(storageFileList));
			} catch (Exception e) {
				e.printStackTrace();
				throw new EgovBizException("첨부파일 정보 처리중 오류발생");
			}
		}

		logFileRceptVO.setProcessSttus(LogFileRceptVO.PROCESS_STTUS_WAIT);

		logFileRceptDAO.insert(logFileRceptVO);
	}

	@Override
	public void deleteLogFileRcept(LogFileRceptVO logFileRceptVO) throws EgovBizException {
		LogDetailVO logDetailVO = new LogDetailVO();
		logDetailVO.setGroupId(logFileRceptVO.getGroupId());
		logDetailVO.setRceptId(logFileRceptVO.getRceptId());
		
		// RPS 데이터 삭제
		List<LogDetailVO> stdrDataList = logDetailService.selectStdrDataList(logDetailVO);
		
		for (LogDetailVO stdrVO : stdrDataList) {
			LogRPSVO logRPSVO = new LogRPSVO();
			logRPSVO.setGroupId(stdrVO.getGroupId());
			logRPSVO.setStdrDe(stdrVO.getLogDataChrctr2());
			
			// 통계 갱신을 위해 삭제 후 재등록
			logRPSService.deleteLogRPS(logRPSVO);
			logRPSService.insertLogRPS(logRPSVO);
		}
		
		// 로그상세정보 삭제
		logDetailService.deleteLogDetailForRceptUnit(logDetailVO);
		
		// 접수정보 삭제
		logFileRceptDAO.delete(logFileRceptVO);
	}

	@Override
	public void truncateLogFileRcept(LogFileRceptVO logFileRceptVO) {
		logDetailService.truncateLogDetail(new LogDetailVO(logFileRceptVO.getGroupId()));
		logFileRceptDAO.truncateLogFileRcept(logFileRceptVO);
	}

	@Override
	public void updateProcessSttus(LogFileRceptVO logFileRceptVO) {
		logFileRceptDAO.updateProcessSttus(logFileRceptVO);
	}
}
