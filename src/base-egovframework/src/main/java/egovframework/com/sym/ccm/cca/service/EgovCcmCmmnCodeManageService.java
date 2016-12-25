package egovframework.com.sym.ccm.cca.service;

import java.util.List;

import kr.pe.courage.common.excellog.ExcelLogVO;

/**
 * 
 * 공통코드에 관한 서비스 인터페이스 클래스를 정의한다
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *
 * </pre>
 */
public interface EgovCcmCmmnCodeManageService {
	    
	/**
	 * 공통코드를 삭제한다.
	 * @param cmmnCode
	 * @throws Exception
	 */
	void deleteCmmnCode(CmmnCodeVO cmmnCode) throws Exception;

	/**
	 * 공통코드를 등록한다.
	 * @param cmmnCode
	 * @throws Exception
	 */
	void insertCmmnCode(CmmnCodeVO cmmnCode) throws Exception;

	/**
	 * 공통코드 상세항목을 조회한다.
	 * @param cmmnCode
	 * @return CmmnCode(공통코드)
	 * @throws Exception
	 */
	CmmnCodeVO selectCmmnCodeDetail(CmmnCodeVO cmmnCode) throws Exception;
	
	/**
	 * 공통코드 목록을 조회한다.
	 * @param searchVO
	 * @return List(공통코드 목록)
	 * @throws Exception
	 */
	List<CmmnCodeVO> selectCmmnCodeList(CmmnCodeVO searchVO) throws Exception;

    /**
	 * 공통코드 총 갯수를 조회한다.
     * @param searchVO
     * @return int(공통코드 총 갯수)
     */
    int selectCmmnCodeListTotCnt(CmmnCodeVO searchVO) throws Exception;
	
	/**
	 * 공통코드를 수정한다.
	 * @param cmmnCode
	 * @throws Exception
	 */
	void updateCmmnCode(CmmnCodeVO cmmnCode) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 공통코드 엑셀 일괄등록
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 2. 1.
	 * @Method Name : insertCmmnCodeExcelUpload
	 * @param excelRegistVO
	 * @return
	 * @throws Exception
	 */
	ExcelLogVO insertCmmnCodeExcelUpload(ExcelLogVO excelRegistVO) throws Exception;
}
