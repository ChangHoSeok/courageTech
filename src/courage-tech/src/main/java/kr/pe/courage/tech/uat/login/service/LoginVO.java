
package kr.pe.courage.tech.uat.login.service;

import java.util.List;

import egovframework.com.sec.ram.service.AuthorManageVO;


/**
 * <pre>
 * kr.pe.courage.tech.uat.login.service
 * LoginVO.java
 * </pre>
 * 
 * @Author : ChangHo Seok
 * @Date : 2016. 1. 6.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 1. 6., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class LoginVO extends egovframework.com.cmm.LoginVO {
	private static final long serialVersionUID = 5612189738433638917L;
	
	public static final String COOKIE_USER_ID = "cookieUserID";
	
	private boolean rememberMe; // 로그인 아이디 기억여부
	private String sbscrbDe; // 가입일자
	private String loginStat; // 로그인 상태
	private boolean enfrcLogin = false; // 중복로그인시 강제 로그인 여부 (최종 로그인 사용자만 유지)
	private String deptCode; // 부서코드
	private String deptNm; // 부서명
	private String avatarUrl; // 사용자 지정 아이콘 URL
	private String salt; // 패스워드 암호화 salt
	
	private List<AuthorManageVO> authList; // 사용자 권한 목록

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public String getSbscrbDe() {
		return sbscrbDe;
	}

	public void setSbscrbDe(String sbscrbDe) {
		this.sbscrbDe = sbscrbDe;
	}

	public String getLoginStat() {
		return loginStat;
	}

	public void setLoginStat(String loginStat) {
		this.loginStat = loginStat;
	}

	public List<AuthorManageVO> getAuthList() {
		return authList;
	}

	public void setAuthList(List<AuthorManageVO> authList) {
		this.authList = authList;
	}

	public boolean isEnfrcLogin() {
		return enfrcLogin;
	}

	public void setEnfrcLogin(boolean enfrcLogin) {
		this.enfrcLogin = enfrcLogin;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptNm() {
		return deptNm;
	}

	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
}
