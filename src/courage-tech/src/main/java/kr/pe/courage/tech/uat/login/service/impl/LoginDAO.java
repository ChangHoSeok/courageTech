package kr.pe.courage.tech.uat.login.service.impl;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import kr.pe.courage.common.dao.AbstractDAO;
import kr.pe.courage.tech.uat.login.service.LoginVO;

/**
 * <pre>
 * kr.pe.courage.tech.uat.login.service.impl
 * LoginDAO.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 1. 6.
 * @Version	: 
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 1. 6., 수정자 : ChangHo Seok, 수정내용 : 
 * </pre>
 */
@Repository("loginDAO")
public class LoginDAO extends AbstractDAO<LoginVO> {

	@Override
	protected String getNameSpace() {
		return "loginDAO";
	}
	
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
	 * @throws SQLException
	 */
	public LoginVO selectLoginUserInfo(LoginVO loginVO) throws SQLException {
		return (LoginVO) getById("selectLoginUserInfo", loginVO);
	}
}
