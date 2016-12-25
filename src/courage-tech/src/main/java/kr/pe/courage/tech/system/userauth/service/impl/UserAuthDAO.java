package kr.pe.courage.tech.system.userauth.service.impl;

import org.springframework.stereotype.Repository;

import kr.pe.courage.common.dao.AbstractDAO;
import kr.pe.courage.tech.system.userauth.service.UserAuthVO;

/**
 * <pre>
 * kr.pe.courage.tech.system.userauth.service.impl
 * UserAuthDAO.java
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
@Repository("userAuthDAO")
public class UserAuthDAO extends AbstractDAO<UserAuthVO> {

	@Override
	protected String getNameSpace() {
		return "userAuthDAO";
	}
	
}
