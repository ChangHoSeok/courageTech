package kr.pe.courage.tech.uat.login.service.impl;

import kr.pe.courage.common.utils.EncryString;
import kr.pe.courage.tech.uat.login.service.LoginService;
import kr.pe.courage.tech.uat.login.service.LoginVO;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * <pre>
 * kr.pe.courage.tech.uat.login.service.impl
 * LoginServiceImpl.java
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
@Service("loginService")
public class LoginServiceImpl implements LoginService {
	@Resource(name = "loginDAO")
	private LoginDAO loginDAO;

	@Override
	public LoginVO selectLoginUserInfo(LoginVO loginVO) throws EgovBizException {
		LoginVO resultVO = null;
		
		try {
			resultVO = loginDAO.selectLoginUserInfo(loginVO);
			
			if (resultVO != null) {
				loginVO.setPassword(EncryString.encSHA512(loginVO.getPassword(), resultVO.getSalt()));
				
				if (loginVO.getPassword().equals(resultVO.getPassword())) {
					if (resultVO.getEmplyrSttusCode().equals("P")) {
						resultVO.setLoginStat(LoginVO.LOGIN_STATUS_SUCCESS); // 로그인 성공
					} else {
						resultVO = new LoginVO(); // 로그인 조회정보 초기화
						resultVO.setLoginStat(LoginVO.LOGIN_STATUS_USER_NOTCONFM); // 상태 미승인
					}
					
				} else {
					resultVO = new LoginVO(); // 로그인 조회정보 초기화
					resultVO.setLoginStat(LoginVO.LOGIN_STATUS_FAIL);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new EgovBizException("사용자 정보 조회 실패");
		}
		
		return resultVO;
	}

}
