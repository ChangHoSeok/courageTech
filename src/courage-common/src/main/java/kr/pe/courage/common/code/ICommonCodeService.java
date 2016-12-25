
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
public interface ICommonCodeService {
	/**
	 * <pre>
	 * 1. 개요 : 공통코드 목록 조회
	 * </pre>
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 4.
	 * @Method Name : selectCommonCodeList
	 * @param commonCodeVO
	 * @return 공통코드 목록
	 * @throws Exception
	 */
	List<CommonCodeVO> selectCommonCodeList(CommonCodeVO commonCodeVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 공통코드 상세조회
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 4.
	 * @Method Name : selectCommonCode
	 * @param commonCodeVo
	 * @throws Exception
	 */
	CommonCodeVO selectCommonCode(CommonCodeVO commonCodeVo) throws Exception;
}
