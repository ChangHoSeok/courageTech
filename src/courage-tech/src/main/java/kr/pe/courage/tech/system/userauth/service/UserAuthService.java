package kr.pe.courage.tech.system.userauth.service;

import java.util.List;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * <pre>
 * kr.pe.courage.tech.system.userauth.service
 * UserAuthService.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 3. 2.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 3. 2., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public interface UserAuthService {
	/**
	 * <pre>
	 * 1. 개요 : 사용자 권한목록 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 3. 2.
	 * @Method Name : selectUserAuthList
	 * @param userAuthVO
	 * @return
	 * @throws EgovBizException
	 */
	public List<UserAuthVO> selectUserAuthList(UserAuthVO userAuthVO) throws EgovBizException;
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 권한목록 갯수 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 3. 2.
	 * @Method Name : selectUserAuthListCount
	 * @param userAuthVO
	 * @return
	 * @throws EgovBizException
	 */
	public int selectUserAuthListCount(UserAuthVO userAuthVO) throws EgovBizException;
}
