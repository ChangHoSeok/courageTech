package kr.pe.courage.tech.system.user.service.impl;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import kr.pe.courage.common.dao.AbstractDAO;
import kr.pe.courage.tech.system.user.service.UserVO;

@Repository("userDAO")
public class UserDAO extends AbstractDAO<UserVO> {

	@Override
	protected String getNameSpace() {
		return "userDAO";
	}

	/**
	 * <pre>
	 * 1. 개요 : 사용자 가입 승인
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 3. 1.
	 * @Method Name : updateUserSbscrbConfm
	 * @param userVO
	 * @throws SQLException
	 */
	public void updateUserSbscrbConfm(UserVO userVO) throws SQLException {
		this.update("updateUserSbscrbConfm", userVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 비밀번호 변경
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 3. 1.
	 * @Method Name : updateUserPassword
	 * @param userVO
	 * @throws SQLException
	 */
	public void updateUserPassword(UserVO userVO) throws SQLException {
		this.update("updateUserPassword", userVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 삭제
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 3. 20.
	 * @Method Name : updateUserDeleteSttus
	 * @param userVO
	 * @throws SQLException
	 */
	public void updateUserDeleteSttus(UserVO userVO) throws SQLException {
		this.update("updateUserDeleteSttus", userVO);
	}
}
