
package kr.pe.courage.common.code;

import java.util.List;

/**
 * 공통코드 조회 interface 정의
 * 
 * <pre>
 * kr.pe.courage.common.code.service
 * CommonCodeService.java
 * </pre>
 * 
 * @Author : Seok Chang Ho
 * @Date : 2013. 11. 4.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * ================================================================
 * 		수정일		|		수정자			|			수정내용
 * 2013-11-04		석창호					최초등록
 * ================================================================
 * </pre>
 */
public interface ICommonDetailCodeService {
	/**
	 * <pre>
	 * 1. 개요 : 공통 상세코드 목록 조회
	 * </pre>
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 4.
	 * @Method Name : selectCommonDetailCodeList
	 * @param commonCodeVO
	 * @return 공통 상세코드 목록
	 * @throws Exception
	 */
	List<CommonCodeVO> selectCommonDetailCodeList(CommonCodeVO commonCodeVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 공통 상세코드 상세조회
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 4.
	 * @Method Name : selectCommonDetailCode
	 * @param commonCodeVo
	 * @throws Exception
	 */
	CommonCodeVO selectCommonDetailCode(CommonCodeVO commonCodeVo) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 정의 query id 코드 조회
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 4.
	 * @Method Name : selectOperationCodeList
	 * @param commonCodeVO
	 * @return 사용자 정의 코드 목록
	 * @throws Exception
	 */
	List<CommonCodeVO> selectOperationCodeList(CommonCodeVO commonCodeVO) throws Exception;
}
