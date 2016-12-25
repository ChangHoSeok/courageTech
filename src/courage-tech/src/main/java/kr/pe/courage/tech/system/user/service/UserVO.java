
package kr.pe.courage.tech.system.user.service;

import kr.pe.courage.common.annotation.KeepCondition;
import kr.pe.courage.common.core.CommonDefaultVO;

/**
 * <pre>
 * kr.pe.courage.tech.system.user.service
 * UserVO.java
 * </pre>
 *
 * @Author : ChangHo Seok
 * @Date : 2016. 2. 18.
 * @Version : 1.0
 * @see
 *
 * 		<pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 2. 18., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 *      </pre>
 */
public class UserVO extends CommonDefaultVO {
	private static final long serialVersionUID = 772346139802112007L;

	public UserVO() {
		setCondOrder("SBSCRB_DE");
		setCondAlign("DESC");
	}

	private String esntlId; // 고유아이디
	private String mberSe; // 회원구분
	private String emplyrId; // 사용자ID
	private String userNm; // 성명
	private String password; // 비밀번호
	private String passwordHint; // 비밀번호 힌트
	private String passwordCnsr; // 비밀번호 답변
	private String salt; // 패스워드 salt
	private String emailAdres; // 이메일주소
	private String emailId; // 이메일 아이디
	private String emailDomain; // 이메일 도메인
	private String ncnm; // 별명
	private String emplyrSttusCode; // 사용자 상태 코드
	private String secsnResn; // 탈퇴사유
	private String secsnDe; // 탈퇴일자
	private String sbscrbDe; // 가입일자
	private String deptCode; // 부서코드
	private String deptNm; // 부서명
	private String newPassword; // 새비밀번호 (비밀번호 변경 시 이용)

	@KeepCondition
	private String condMberSe;
	@KeepCondition
	private String condEmplyrId;
	@KeepCondition
	private String condUserNm;
	@KeepCondition
	private String condEmplyrSttusCode;
	@KeepCondition
	private String condDeptNm;
	@KeepCondition
	private String condDeptCode;

	public String getEsntlId() {
		return esntlId;
	}

	public void setEsntlId(String esntlId) {
		this.esntlId = esntlId;
	}

	public String getMberSe() {
		return mberSe;
	}

	public void setMberSe(String mberSe) {
		this.mberSe = mberSe;
	}

	public String getEmplyrId() {
		return emplyrId;
	}

	public void setEmplyrId(String emplyrId) {
		this.emplyrId = emplyrId;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordHint() {
		return passwordHint;
	}

	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}

	public String getPasswordCnsr() {
		return passwordCnsr;
	}

	public void setPasswordCnsr(String passwordCnsr) {
		this.passwordCnsr = passwordCnsr;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmailAdres() {
		return emailAdres;
	}

	public void setEmailAdres(String emailAdres) {
		this.emailAdres = emailAdres;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getEmailDomain() {
		return emailDomain;
	}

	public void setEmailDomain(String emailDomain) {
		this.emailDomain = emailDomain;
	}

	public String getNcnm() {
		return ncnm;
	}

	public void setNcnm(String ncnm) {
		this.ncnm = ncnm;
	}

	public String getEmplyrSttusCode() {
		return emplyrSttusCode;
	}

	public void setEmplyrSttusCode(String emplyrSttusCode) {
		this.emplyrSttusCode = emplyrSttusCode;
	}

	public String getSecsnResn() {
		return secsnResn;
	}

	public void setSecsnResn(String secsnResn) {
		this.secsnResn = secsnResn;
	}

	public String getSecsnDe() {
		return secsnDe;
	}

	public void setSecsnDe(String secsnDe) {
		this.secsnDe = secsnDe;
	}

	public String getSbscrbDe() {
		return sbscrbDe;
	}

	public void setSbscrbDe(String sbscrbDe) {
		this.sbscrbDe = sbscrbDe;
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

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getCondMberSe() {
		return condMberSe;
	}

	public void setCondMberSe(String condMberSe) {
		this.condMberSe = condMberSe;
	}

	public String getCondEmplyrId() {
		return condEmplyrId;
	}

	public void setCondEmplyrId(String condEmplyrId) {
		this.condEmplyrId = condEmplyrId;
	}

	public String getCondUserNm() {
		return condUserNm;
	}

	public void setCondUserNm(String condUserNm) {
		this.condUserNm = condUserNm;
	}

	public String getCondEmplyrSttusCode() {
		return condEmplyrSttusCode;
	}

	public void setCondEmplyrSttusCode(String condEmplyrSttusCode) {
		this.condEmplyrSttusCode = condEmplyrSttusCode;
	}

	public String getCondDeptNm() {
		return condDeptNm;
	}

	public void setCondDeptNm(String condDeptNm) {
		this.condDeptNm = condDeptNm;
	}

	public String getCondDeptCode() {
		return condDeptCode;
	}

	public void setCondDeptCode(String condDeptCode) {
		this.condDeptCode = condDeptCode;
	}
}
