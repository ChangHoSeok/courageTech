package kr.pe.courage.tech.uat.login.service;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * <pre>
 * kr.pe.courage.tech.uat.login.service
 * LoginService.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 1. 6.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 1. 6., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public interface LoginService {
	/**
	 * <pre>
	 * 1. 개요 : 로그인 사용자 정보 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 1. 18.
	 * @Method Name : selectLoginUserInfo
	 * @param loginVO
	 * @return
	 * @throws EgovBizException
	 */
	public LoginVO selectLoginUserInfo(LoginVO loginVO) throws EgovBizException;
}
