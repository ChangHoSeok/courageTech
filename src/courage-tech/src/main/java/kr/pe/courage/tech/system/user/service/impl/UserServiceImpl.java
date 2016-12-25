package kr.pe.courage.tech.system.user.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import kr.pe.courage.common.utils.EncryString;
import kr.pe.courage.common.utils.PasswordGenerater;
import kr.pe.courage.common.utils.Util;
import kr.pe.courage.common.utils.ValidationUtil;
import kr.pe.courage.tech.system.user.service.UserService;
import kr.pe.courage.tech.system.user.service.UserVO;

import org.springframework.stereotype.Service;

import egovframework.com.sec.ram.service.AuthorManageVO;
import egovframework.com.sec.ram.service.impl.AuthorManageDAO;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource(name = "userDAO")
	private UserDAO userDAO;
	
	@Resource(name = "authorManageDAO")
	private AuthorManageDAO authorManageDAO;

	@Override
	public List<UserVO> selectUserList(UserVO userVO) throws EgovBizException {
		return userDAO.selectList(userVO);
	}

	@Override
	public int selectUserListCount(UserVO userVO) throws EgovBizException {
		return userDAO.selectListCount(userVO);
	}

	@Override
	public UserVO selectUserDetail(UserVO userVO) throws EgovBizException {
		UserVO resultVO = userDAO.getById(userVO);
		
		if (resultVO.getEmailAdres() != null) {
			String[] emailArr = resultVO.getEmailAdres().split("@");
			
			resultVO.setEmailId(emailArr[0]);
			resultVO.setEmailDomain(emailArr[1]);
		}
		
		return resultVO;
	}

	@Override
	public boolean selectUserIdExists(UserVO userVO) throws EgovBizException {
		return userDAO.isExist(userVO);
	}

	@Override
	public void insertUser(UserVO userVO) throws EgovBizException {
		userVO.setSalt(PasswordGenerater.generateRandomPasswd(32));
		userVO.setPassword(EncryString.encSHA512(userVO.getPassword(), userVO.getSalt())); // 패스워드 암호화
		userVO.setEmailAdres(userVO.getEmailId() + "@" + userVO.getEmailDomain()); // 이메일 설정
		
		userDAO.insert(userVO);
		
		// 사용자 기본권한 설정
		String authorCode = authorManageDAO.selectMberBassAuthor(); // 사용자 기본권한 조회
		
		if (!Util.nvl(authorCode).equals("")) {
			AuthorManageVO userAuthorVO = new AuthorManageVO();
			userAuthorVO.setEsntlId(userVO.getEsntlId());
			userAuthorVO.setAuthorCode(authorCode);
			userAuthorVO.setDefaultAuthorYn("Y");
			
			authorManageDAO.insertUserAuthManage(userAuthorVO);
			
		}
	}

	@Override
	public void updateUser(UserVO userVO) throws EgovBizException {
		if (Util.checkNull(userVO.getEmailAdres())) {
			userVO.setEmailAdres(userVO.getEmailId() + "@" + userVO.getEmailDomain()); // 이메일 설정
		}
		
		userDAO.update(userVO);
	}

	@Override
	public void updateUserSbscrbConfm(UserVO userVO) throws EgovBizException {
		try {
			if (userDAO.getById(userVO) != null) {
				userDAO.updateUserSbscrbConfm(userVO);
			} else {
				throw new EgovBizException("존재하지 않는 사용자 입니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new EgovBizException("DB오류 발생");
		}
	}

	@Override
	public void updateUserInitPassword(UserVO userVO) throws EgovBizException {
		UserVO userInfo = userDAO.getById(userVO);
		
		if (userInfo == null) {
			throw new EgovBizException("사용자 정보가 존재하지 않습니다. : " + userVO.getEsntlId());
		}
		
		String initPassword = PasswordGenerater.generateRandomPasswd(8); // 8자리 임이의 비밀번호 생성
		
		if (!Util.checkNull(userVO.getPassword())) {
			initPassword = userVO.getPassword();
		}
		
		if (!ValidationUtil.isPassword(initPassword, true)) {
			throw new EgovBizException("패드워드 형식에 맞지 않습니다.");
		}
		
		userVO.setPassword(EncryString.encSHA512(initPassword, userInfo.getSalt()));
		
		try {
			userDAO.updateUserPassword(userVO);
			userVO.setPassword(initPassword);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new EgovBizException("DB오류 발생");
		}
	}

	@Override
	public void deleteUser(UserVO userVO) throws EgovBizException {
		try {
			AuthorManageVO authorManageVO = new AuthorManageVO();
			authorManageVO.setEsntlId(userVO.getEsntlId());
			authorManageDAO.deleteEmplyrScrtyEstbs(authorManageVO);
			
			userDAO.updateUserDeleteSttus(userVO);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new EgovBizException("DB오류 발생");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean userPasswordCertificate(UserVO userVO) throws EgovBizException {
		UserVO userInfo = userDAO.getById(userVO);
		
		if (userInfo == null) {
			throw new EgovBizException("사용자 정보가 존재하지 않습니다. : " + userVO.getEsntlId());
		}
		
		String inputPassword = EncryString.encSHA512(userVO.getPassword(), userInfo.getSalt());
		
		if (inputPassword.equals(userInfo.getPassword())) {
			return true;
		} else {
			return false;
		}
	}

}
