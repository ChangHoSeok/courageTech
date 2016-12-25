
package egovframework.com.sym.ccm.cde.service;

import java.util.List;

/**
 * 공통코드상세 서비스에 관한 인터페이스 정의
 * 
 * <pre>
 * egovframework.com.sym.ccm.cde.service
 * EgovCcmCmmnDetailCodeManageService.java
 * </pre>
 * 
 * @Author : Chang Ho Seok
 * @Date : 2013. 11. 3.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * ================================================================
 * 		수정일		|		수정자			|			수정내용
 * 	2013-11-03		석창호					최초등록
 * ================================================================
 * </pre>
 */
public interface EgovCcmCmmnDetailCodeManageService {

	/**
	 * <pre>
	 * 1. 개요 : 공통상세코드를 삭제한다.
	 * </pre>
	 * 
	 * @Author	: Chang Ho Seok
	 * @Date	: 2013. 11. 3.
	 * @Method Name : deleteCmmnDetailCode
	 * @param cmmnDetailCodeVO
	 * @throws Exception
	 */
	void deleteCmmnDetailCode(CmmnDetailCodeVO cmmnDetailCodeVO) throws Exception;

	/**
	 * <pre>
	 * 1. 개요 : 공통상세코드를 등록한다.
	 * </pre>
	 * 
	 * @Author	: Chang Ho Seok
	 * @Date	: 2013. 11. 3.
	 * @Method Name : insertCmmnDetailCode
	 * @param cmmnDetailCodeVO
	 * @throws Exception
	 */
	void insertCmmnDetailCode(CmmnDetailCodeVO cmmnDetailCodeVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 정의 상세코드 등록
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2015. 1. 5.
	 * @Method Name : insertCmmnDetailCodeCustom
	 * @param cmmnDetailCodeVO
	 * @throws Exception
	 */
	CmmnDetailCodeVO insertCmmnDetailCodeCustom(CmmnDetailCodeVO cmmnDetailCodeVO) throws Exception;

	/**
	 * <pre>
	 * 1. 개요 : 공통상세코드 상세항목을 조회한다.
	 * </pre>
	 * 
	 * @Author	: Chang Ho Seok
	 * @Date	: 2013. 11. 3.
	 * @Method Name : selectCmmnDetailCodeDetail
	 * @param cmmnDetailCodeVO
	 * @return
	 * @throws Exception
	 */
	CmmnDetailCodeVO selectCmmnDetailCodeDetail(CmmnDetailCodeVO cmmnDetailCodeVO) throws Exception;

	/**
	 * <pre>
	 * 1. 개요 : 공통코드 상세항목을 조회한다. (조회조건 코드명)
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2015. 2. 3.
	 * @Method Name : selectCmmnDetailCodeByName
	 * @param cmmnDetailCodeVO
	 * @return
	 * @throws Exception
	 */
	CmmnDetailCodeVO selectCmmnDetailCodeByName(CmmnDetailCodeVO cmmnDetailCodeVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 공통상세코드 목록을 조회한다.
	 * </pre>
	 * 
	 * @Author	: Chang Ho Seok
	 * @Date	: 2013. 11. 3.
	 * @Method Name : selectCmmnDetailCodeList
	 * @param cmmnDetailCodeVO
	 * @return 공통상세코드 목록
	 * @throws Exception
	 */
	List<CmmnDetailCodeVO> selectCmmnDetailCodeList(CmmnDetailCodeVO cmmnDetailCodeVO) throws Exception;

	/**
	 * <pre>
	 * 1. 개요 : 공통상세코드 총 갯수를 조회한다.
	 * </pre>
	 * 
	 * @Author	: Chang Ho Seok
	 * @Date	: 2013. 11. 3.
	 * @Method Name : selectCmmnDetailCodeListTotCnt
	 * @param cmmnDetailCodeVO
	 * @return 공통상세코드 총 갯수
	 * @throws Exception
	 */
	int selectCmmnDetailCodeListTotCnt(CmmnDetailCodeVO cmmnDetailCodeVO) throws Exception;

	/**
	 * <pre>
	 * 1. 개요 : 공통상세코드를 수정한다.
	 * </pre>
	 * 
	 * @Author	: Chang Ho Seok
	 * @Date	: 2013. 11. 3.
	 * @Method Name : updateCmmnDetailCode
	 * @param cmmnDetailCodeVO
	 * @throws Exception
	 */
	void updateCmmnDetailCode(CmmnDetailCodeVO cmmnDetailCodeVO) throws Exception;

}
