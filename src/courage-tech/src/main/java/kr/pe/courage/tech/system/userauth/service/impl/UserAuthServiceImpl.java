package kr.pe.courage.tech.system.userauth.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import kr.pe.courage.tech.system.userauth.service.UserAuthService;
import kr.pe.courage.tech.system.userauth.service.UserAuthVO;

/**
 * <pre>
 * kr.pe.courage.tech.system.userauth.service.impl
 * UserAuthServiceImpl.java
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
@Service("userAuthService")
public class UserAuthServiceImpl implements UserAuthService {
	
	@Resource(name = "userAuthDAO")
	private UserAuthDAO userAuthDAO;

	@Override
	public List<UserAuthVO> selectUserAuthList(UserAuthVO userAuthVO) throws EgovBizException {
		return userAuthDAO.selectList(userAuthVO);
	}

	@Override
	public int selectUserAuthListCount(UserAuthVO userAuthVO) throws EgovBizException {
		return userAuthDAO.selectListCount(userAuthVO);
	}

}
