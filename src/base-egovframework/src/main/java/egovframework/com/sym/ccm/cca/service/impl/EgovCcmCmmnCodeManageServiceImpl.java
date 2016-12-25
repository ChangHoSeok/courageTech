
package egovframework.com.sym.ccm.cca.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.annotation.Resource;

import kr.pe.courage.common.excellog.ExcelLogVO;
import kr.pe.courage.common.storage.Storage;
import kr.pe.courage.common.utils.Util;

import org.springframework.stereotype.Service;

import egovframework.com.sym.ccm.cca.service.CmmnCodeExcelMapping;
import egovframework.com.sym.ccm.cca.service.CmmnCodeVO;
import egovframework.com.sym.ccm.cca.service.EgovCcmCmmnCodeManageService;
import egovframework.com.sym.ccm.cde.service.impl.CmmnDetailCodeManageDAO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.excel.CourageExcelService;
import egovframework.rte.fdl.excel.EgovExcelService;
import egovframework.rte.fdl.excel.impl.CourageExcelServiceImpl;
import egovframework.rte.fdl.excel.impl.EgovExcelServiceImpl;

/**
 * 
 * 공통코드에 대한 서비스 구현클래스를 정의한다
 * 
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 * 
 * </pre>
 */
@Service("cmmnCodeManageService")
public class EgovCcmCmmnCodeManageServiceImpl extends AbstractServiceImpl implements EgovCcmCmmnCodeManageService {

	@Resource(name = "cmmnCodeManageDAO")
	private CmmnCodeManageDAO cmmnCodeManageDAO;
	
	@Resource(name = "cmmnDetailCodeManageDAO")
	private CmmnDetailCodeManageDAO cmmnDetailCodeManageDAO;

	/**
	 * 공통코드를 삭제한다.
	 */
	public void deleteCmmnCode(CmmnCodeVO cmmnCode) throws Exception {
		cmmnCodeManageDAO.deleteCmmnCode(cmmnCode);
	}

	/**
	 * 공통코드를 등록한다.
	 */
	public void insertCmmnCode(CmmnCodeVO cmmnCode) throws Exception {
		cmmnCodeManageDAO.insertCmmnCode(cmmnCode);
	}

	/**
	 * 공통코드 상세항목을 조회한다.
	 */
	public CmmnCodeVO selectCmmnCodeDetail(CmmnCodeVO cmmnCode) throws Exception {
		CmmnCodeVO ret = (CmmnCodeVO) cmmnCodeManageDAO.selectCmmnCodeDetail(cmmnCode);
		return ret;
	}

	/**
	 * 공통코드 목록을 조회한다.
	 */
	public List<CmmnCodeVO> selectCmmnCodeList(CmmnCodeVO cmmnCode) throws Exception {
		return cmmnCodeManageDAO.selectCmmnCodeList(cmmnCode);
	}

	/**
	 * 공통코드 총 갯수를 조회한다.
	 */
	public int selectCmmnCodeListTotCnt(CmmnCodeVO cmmnCode) throws Exception {
		return cmmnCodeManageDAO.selectCmmnCodeListTotCnt(cmmnCode);
	}

	/**
	 * 공통코드를 수정한다.
	 */
	public void updateCmmnCode(CmmnCodeVO cmmnCode) throws Exception {
		cmmnCodeManageDAO.updateCmmnCode(cmmnCode);
	}
	
	public ExcelLogVO insertCmmnCodeExcelUpload(ExcelLogVO excelRegistVO) throws Exception {
		ExcelLogVO resultVO = new ExcelLogVO();
		// 첨부파일 존재 확인
		String excelUploadPath = Storage.getInstance().getTempPath(excelRegistVO.getSessionId());
		String excelUploadFile = excelRegistVO.getExcelUploadFile();
		
		int totalCnt = 0;
		int sucessCnt = 0;
		int failedCnt = 0;
		int idx = 1;
		
		File excelFile = new File(excelUploadPath + File.separator + excelUploadFile);
		
		if (excelFile.exists()) {
			CourageExcelService courageExcelService = new CourageExcelServiceImpl();
			courageExcelService.setMapClass("egovframework.com.sym.ccm.cca.service.CmmnCodeVO");
			
			List<Object> resultList = courageExcelService.parseHSSFSheetExcel(new FileInputStream(excelFile), 1);
			
			if (resultList != null && resultList.size() > 0) {
				totalCnt = resultList.size();
				
				// 기존정보 삭제 여부
				if (Util.nvl(excelRegistVO.getDeleteFlag()).equals(ExcelLogVO.DELETE_FLAG_Y)) {
					// 공통 상세코드 제거
					cmmnDetailCodeManageDAO.truncateCmmnDetailCode();
					// 공통코드 제거
					cmmnCodeManageDAO.truncateCmmnCode();
				}
				
				for (Object codeObj : resultList) {
					CmmnCodeVO cmmnCodeVO = (CmmnCodeVO) codeObj;
					
					if (cmmnCodeVO.isValidateError()) {
						// 오류내역 저장
						failedCnt++;
					} else {
						// 코드 중복 검사
						CmmnCodeVO duplecateVO = cmmnCodeManageDAO.selectCmmnCodeDetail(cmmnCodeVO);
						if (duplecateVO != null) {
							// 코드중복 오류 저장
							// 행번호 idx번째 데이터가 이미 등록되었습니다[중복오류]
							failedCnt++;
						} else {
							cmmnCodeManageDAO.insertCmmnCode(cmmnCodeVO);
							sucessCnt++;
						}
					}
					
					// 오류내역 저장
					
					idx++;
				}
			} else {
				// 오류정보 저장처리 추가 필요
				// 행번호 0번째 엑셀파일을 파싱할 수 없습니다. (엑셀 정보가 등록되었는지 확인하시기 바랍니다)
				failedCnt++;
			}
			
		} else {
			resultVO.setExcelUploadSttus(ExcelLogVO.STATUS_FAILED);
			// 오류정보 저장처리 추가 필요
			// 행번호 0번째 일괄등록 대상 파일이 존재하지 않습니다.
			failedCnt++;
		}
		
		excelRegistVO.setExcelUploadTotalCnt("" + totalCnt);
		excelRegistVO.setExcelUploadSuccessCnt("" + sucessCnt);
		excelRegistVO.setExcelUploadFailCnt("" + failedCnt);
		
		if (failedCnt > 0) {
			excelRegistVO.setExcelUploadSuccessCnt(ExcelLogVO.STATUS_FAILED);
		} else {
			excelRegistVO.setExcelUploadSuccessCnt(ExcelLogVO.STATUS_SUCCESS);
		}
		
		return excelRegistVO;
	}
}
