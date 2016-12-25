
package egovframework.com.cmm;

import java.io.Serializable;

import kr.pe.courage.common.user.UserVO;

/**
 * @Class Name : LoginVO.java
 * @Description : Login VO class
 * @Modification Information @ @ 수정일 수정자 수정내용 @ ------- --------
 *               --------------------------- @ 2009.03.03 박지욱 최초 생성
 * 
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.03
 * @version 1.0
 * @see
 * 
 */
public class LoginVO extends UserVO implements Serializable {

	private static final long serialVersionUID = -8274004534207618049L;
	public static final String LOGIN_STATUS_SUCCESS = "0"; // 정상
	public static final String LOGIN_STATUS_USER_NOTFOUND = "1"; // 사용자 미존재
	public static final String LOGIN_STATUS_FAIL = "2"; // 실패
	public static final String LOGIN_STATUS_USER_NOTCONFM = "3"; // 미승인
	
	public static final String SESSION_AUTHOR_LIST = "authorList";
	public static final String SESSION_AUTHOR_CODE = "authorCode";
	public static final String SESSION_AUTHOR_NAME = "authorName";
	public static final String SESSION_AUTHOR_CODE_ARRAY = "auhtorCodes";
	
	public static final String SESSION_USER_UNIQ_ID = "uniqID";
	public static final String SESSION_USER_ID = "userID";
	public static final String SESSION_USER_NM = "userNm";
	public static final String SESSION_USER_IP = "userIP";
	public static final String SESSION_USER_MENULIST = "menuList";

	/** 아이디 */
	private String id;
	/** 이름 */
	private String userNm;
	/** 주민등록번호 */
	private String ihidNum;
	/** 이메일주소 */
	private String email;
	/** 비밀번호 */
	private String password;
	/** 비밀번호 힌트 */
	private String passwordHint;
	/** 비밀번호 정답 */
	private String passwordCnsr;
	/** 사용자구분 */
	private String userSe;
	/** 조직(부서)ID */
	private String orgnztId;
	/** 조직(부서)명 */
	private String orgnztNm;
	/** 고유아이디 */
	private String uniqId;
	/** 로그인 후 이동할 페이지 */
	private String url;
	/** 사용자 IP정보 */
	private String ip;
	/** GPKI인증 DN */
	private String dn;
	private String passwordText;
	
	private String groupId;
	private String groupNm;
	private String esntlId;
	private String emplyrId;
	private String emplNo;
	private String pstinstCode;
	private String emplyrSttusCode;
	private String ofcpsNm;
	private String clsfNm;
	private String upperOrgnztId;
	private String branchId;
	private String branchNm;
	private String useAt;
	private String authorCode;
	private String authorNm;
	private String upperOrgnztNm;
	private String offmTelno;
	private String humanRegistNo;
	private String rrno;
	
	/** GPKI 인증서 관련 변수 */
	private String challenge;
	
	/** GPKI 인증서 로그인 or 등록 구분 */
	private String gpkiMode;
	
	public String getPasswordText() {
		return passwordText;
	}

	public void setPasswordText(String passwordText) {
		this.passwordText = passwordText;
	}

	public String getHumanRegistNo() {
		return humanRegistNo;
	}

	public void setHumanRegistNo(String humanRegistNo) {
		this.humanRegistNo = humanRegistNo;
	}

	public String getGpkiMode() {
		return gpkiMode;
	}

	public void setGpkiMode(String gpkiMode) {
		this.gpkiMode = gpkiMode;
	}

	public String getChallenge() {
		return challenge;
	}

	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}

	public String getOffmTelno() {
		return offmTelno;
	}

	public void setOffmTelno(String offmTelno) {
		this.offmTelno = offmTelno;
	}

	public String getUpperOrgnztNm() {
		return upperOrgnztNm;
	}

	public void setUpperOrgnztNm(String upperOrgnztNm) {
		this.upperOrgnztNm = upperOrgnztNm;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupNm() {
		return groupNm;
	}

	public void setGroupNm(String groupNm) {
		this.groupNm = groupNm;
	}

	public String getEsntlId() {
		return esntlId;
	}

	public void setEsntlId(String esntlId) {
		this.setUniqId(esntlId);
		this.esntlId = esntlId;
	}

	public String getEmplyrId() {
		return emplyrId;
	}

	public void setEmplyrId(String emplyrId) {
		this.emplyrId = emplyrId;
	}

	public String getEmplNo() {
		return emplNo;
	}

	public void setEmplNo(String emplNo) {
		this.emplNo = emplNo;
	}

	public String getPstinstCode() {
		return pstinstCode;
	}

	public void setPstinstCode(String pstinstCode) {
		this.pstinstCode = pstinstCode;
	}

	public String getEmplyrSttusCode() {
		return emplyrSttusCode;
	}

	public void setEmplyrSttusCode(String emplyrSttusCode) {
		this.emplyrSttusCode = emplyrSttusCode;
	}

	public String getOfcpsNm() {
		return ofcpsNm;
	}

	public void setOfcpsNm(String ofcpsNm) {
		this.ofcpsNm = ofcpsNm;
	}

	public String getClsfNm() {
		return clsfNm;
	}

	public void setClsfNm(String clsfNm) {
		this.clsfNm = clsfNm;
	}

	public String getUpperOrgnztId() {
		return upperOrgnztId;
	}

	public void setUpperOrgnztId(String upperOrgnztId) {
		this.upperOrgnztId = upperOrgnztId;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchNm() {
		return branchNm;
	}

	public void setBranchNm(String branchNm) {
		this.branchNm = branchNm;
	}

	public String getUseAt() {
		return useAt;
	}

	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	public String getAuthorCode() {
		return authorCode;
	}

	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}

	public String getAuthorNm() {
		return authorNm;
	}

	public void setAuthorNm(String authorNm) {
		this.authorNm = authorNm;
	}

	/**
	 * id attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * id attribute 값을 설정한다.
	 * 
	 * @param id
	 *            String
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.setName(userNm);
		this.userNm = userNm;
	}

	/**
	 * ihidNum attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getIhidNum() {
		return ihidNum;
	}

	/**
	 * ihidNum attribute 값을 설정한다.
	 * 
	 * @param ihidNum
	 *            String
	 */
	public void setIhidNum(String ihidNum) {
		this.ihidNum = ihidNum;
	}

	/**
	 * email attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * email attribute 값을 설정한다.
	 * 
	 * @param email
	 *            String
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * password attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * password attribute 값을 설정한다.
	 * 
	 * @param password
	 *            String
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * passwordHint attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getPasswordHint() {
		return passwordHint;
	}

	/**
	 * passwordHint attribute 값을 설정한다.
	 * 
	 * @param passwordHint
	 *            String
	 */
	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}

	/**
	 * passwordCnsr attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getPasswordCnsr() {
		return passwordCnsr;
	}

	/**
	 * passwordCnsr attribute 값을 설정한다.
	 * 
	 * @param passwordCnsr
	 *            String
	 */
	public void setPasswordCnsr(String passwordCnsr) {
		this.passwordCnsr = passwordCnsr;
	}

	/**
	 * userSe attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getUserSe() {
		return userSe;
	}

	/**
	 * userSe attribute 값을 설정한다.
	 * 
	 * @param userSe
	 *            String
	 */
	public void setUserSe(String userSe) {
		this.userSe = userSe;
	}

	/**
	 * orgnztId attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getOrgnztId() {
		return orgnztId;
	}

	/**
	 * orgnztId attribute 값을 설정한다.
	 * 
	 * @param orgnztId
	 *            String
	 */
	public void setOrgnztId(String orgnztId) {
		this.orgnztId = orgnztId;
	}

	/**
	 * uniqId attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getUniqId() {
		return uniqId;
	}

	/**
	 * uniqId attribute 값을 설정한다.
	 * 
	 * @param uniqId
	 *            String
	 */
	public void setUniqId(String uniqId) {
		this.uniqId = uniqId;
	}

	/**
	 * url attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * url attribute 값을 설정한다.
	 * 
	 * @param url
	 *            String
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * ip attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * ip attribute 값을 설정한다.
	 * 
	 * @param ip
	 *            String
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * dn attribute 를 리턴한다.
	 * 
	 * @return String
	 */
	public String getDn() {
		return dn;
	}

	/**
	 * dn attribute 값을 설정한다.
	 * 
	 * @param dn
	 *            String
	 */
	public void setDn(String dn) {
		this.dn = dn;
	}

	/**
	 * @return the orgnztNm
	 */
	public String getOrgnztNm() {
		return orgnztNm;
	}

	/**
	 * @param orgnztNm
	 *            the orgnztNm to set
	 */
	public void setOrgnztNm(String orgnztNm) {
		this.orgnztNm = orgnztNm;
	}

	public String getRrno() {
		return rrno;
	}

	public void setRrno(String rrno) {
		this.rrno = rrno;
	}

}
