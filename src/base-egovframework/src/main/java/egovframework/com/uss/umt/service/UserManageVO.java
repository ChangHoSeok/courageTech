
package egovframework.com.uss.umt.service;

import java.io.Serializable;

import kr.pe.courage.common.annotation.KeepCondition;
import kr.pe.courage.common.core.CommonDefaultVO;

/**
 * 업무사용자VO클래스로서 업무사용자관리 비지니스로직 처리용 항목을 구성한다.
 * 
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  조재영          최초 생성
 * 
 * </pre>
 */
public class UserManageVO extends CommonDefaultVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String esntlId; // 고유ID
	private String[] esntlIds; // 고유ID 수정시 사용
	private String groupId; // 그룹ID
	private String groupNm; // 그룹명
	private String orgnztId; // 조직ID
	private String orgnztNm; // 조직명
	private String upperOrgnztNm; // 상위조직명
	private String emplyrId; // 업무사용자ID
	private String userNm; // 사용자명
	private String password; // 비밀번호
	private String emplNo; // 사원번호
	private String ihidnum; // 주민등록번호
	private String sexdstnCode; // 성별코드
	private String brthdy; // 생일
	private String fxnum; // 팩스번호
	private String houseAdres; // 주택주소
	private String passwordHint; // 비밀번호힌트
	private String passwordCnsr; // 비밀번호정답
	private String houseEndTelno; // 주택끝전화번호
	private String areaNo; // 지역번호
	private String detailAdres; // 상세주소
	private String zip; // 우편번호
	private String offmTelno; // 사무실전화번호
	private String mbtlnum; // 이동전화번호
	private String emailAdres; // 이메일주소
	private String ofcpsNm; // 직위명
	private String ofcpsCd; // 직위코드
	private String houseMiddleTelno;// 주택중간전화번호
	private String pstinstCode; // 소속기관코드
	private String emplyrSttusCode; // 사용자상태코드
	private String crtfcDnValue; // 인증DN값
	private String sbscrbDe; // 가입일자
	private String emplRegistSe; // 사용자등록구분_CYL001
	private String clsfNm; // 직급명
	private String clsfCd; // 직급코드
	private String passwordText;
	private String deptNm;
	private String mberSe;
	private String frntnAt;
	private String rrcsRegistNo;
	private String userNo;
	private String nlty;
	private String befoPassword; //기존비밀번호
	private String authorNm; // 권한명
	
	private String condSystemFlag;	// 직급코드

	/** 사용자명 */
	@KeepCondition
	private String condUserNm;
	@KeepCondition
	private String condUpperOrgnztId;
	@KeepCondition
	private String condOrgnztId;
	@KeepCondition
	private String condEmplyrSttusCode;

	public String getAuthorNm() {
		return authorNm;
	}

	public void setAuthorNm(String authorNm) {
		this.authorNm = authorNm;
	}

	public String getPasswordText() {
		return passwordText;
	}

	public void setPasswordText(String passwordText) {
		this.passwordText = passwordText;
	}

	public String getCondSystemFlag() {
		return condSystemFlag;
	}

	public void setCondSystemFlag(String condSystemFlag) {
		this.condSystemFlag = condSystemFlag;
	}

	public String getEsntlId() {
		return esntlId;
	}

	public void setEsntlId(String esntlId) {
		this.esntlId = esntlId;
	}

	public String[] getEsntlIds() {
		return esntlIds;
	}

	public void setEsntlIds(String[] esntlIds) {
		this.esntlIds = esntlIds;
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

	public String getOrgnztId() {
		return orgnztId;
	}

	public void setOrgnztId(String orgnztId) {
		this.orgnztId = orgnztId;
	}

	public String getOrgnztNm() {
		return orgnztNm;
	}

	public void setOrgnztNm(String orgnztNm) {
		this.orgnztNm = orgnztNm;
	}

	public String getUpperOrgnztNm() {
		return upperOrgnztNm;
	}

	public void setUpperOrgnztNm(String upperOrgnztNm) {
		this.upperOrgnztNm = upperOrgnztNm;
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

	public String getEmplNo() {
		return emplNo;
	}

	public void setEmplNo(String emplNo) {
		this.emplNo = emplNo;
	}

	public String getIhidnum() {
		return ihidnum;
	}

	public void setIhidnum(String ihidnum) {
		this.ihidnum = ihidnum;
	}

	public String getSexdstnCode() {
		return sexdstnCode;
	}

	public void setSexdstnCode(String sexdstnCode) {
		this.sexdstnCode = sexdstnCode;
	}

	public String getBrthdy() {
		return brthdy;
	}

	public void setBrthdy(String brthdy) {
		this.brthdy = brthdy;
	}

	public String getFxnum() {
		return fxnum;
	}

	public void setFxnum(String fxnum) {
		this.fxnum = fxnum;
	}

	public String getHouseAdres() {
		return houseAdres;
	}

	public void setHouseAdres(String houseAdres) {
		this.houseAdres = houseAdres;
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

	public String getHouseEndTelno() {
		return houseEndTelno;
	}

	public void setHouseEndTelno(String houseEndTelno) {
		this.houseEndTelno = houseEndTelno;
	}

	public String getAreaNo() {
		return areaNo;
	}

	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	public String getDetailAdres() {
		return detailAdres;
	}

	public void setDetailAdres(String detailAdres) {
		this.detailAdres = detailAdres;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getOffmTelno() {
		return offmTelno;
	}

	public void setOffmTelno(String offmTelno) {
		this.offmTelno = offmTelno;
	}

	public String getMbtlnum() {
		return mbtlnum;
	}

	public void setMbtlnum(String mbtlnum) {
		this.mbtlnum = mbtlnum;
	}

	public String getEmailAdres() {
		return emailAdres;
	}

	public void setEmailAdres(String emailAdres) {
		this.emailAdres = emailAdres;
	}

	public String getOfcpsNm() {
		return ofcpsNm;
	}

	public void setOfcpsNm(String ofcpsNm) {
		this.ofcpsNm = ofcpsNm;
	}

	public String getOfcpsCd() {
		return ofcpsCd;
	}

	public void setOfcpsCd(String ofcpsCd) {
		this.ofcpsCd = ofcpsCd;
	}

	public String getHouseMiddleTelno() {
		return houseMiddleTelno;
	}

	public void setHouseMiddleTelno(String houseMiddleTelno) {
		this.houseMiddleTelno = houseMiddleTelno;
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

	public String getCrtfcDnValue() {
		return crtfcDnValue;
	}

	public void setCrtfcDnValue(String crtfcDnValue) {
		this.crtfcDnValue = crtfcDnValue;
	}

	public String getSbscrbDe() {
		return sbscrbDe;
	}

	public void setSbscrbDe(String sbscrbDe) {
		this.sbscrbDe = sbscrbDe;
	}

	public String getEmplRegistSe() {
		return emplRegistSe;
	}

	public void setEmplRegistSe(String emplRegistSe) {
		this.emplRegistSe = emplRegistSe;
	}

	public String getClsfNm() {
		return clsfNm;
	}

	public void setClsfNm(String clsfNm) {
		this.clsfNm = clsfNm;
	}

	public String getClsfCd() {
		return clsfCd;
	}

	public void setClsfCd(String clsfCd) {
		this.clsfCd = clsfCd;
	}

	public String getCondUserNm() {
		return condUserNm;
	}

	public void setCondUserNm(String condUserNm) {
		this.condUserNm = condUserNm;
	}

	public String getCondUpperOrgnztId() {
		return condUpperOrgnztId;
	}

	public void setCondUpperOrgnztId(String condUpperOrgnztId) {
		this.condUpperOrgnztId = condUpperOrgnztId;
	}

	public String getCondOrgnztId() {
		return condOrgnztId;
	}

	public void setCondOrgnztId(String condOrgnztId) {
		this.condOrgnztId = condOrgnztId;
	}

	public String getCondEmplyrSttusCode() {
		return condEmplyrSttusCode;
	}

	public void setCondEmplyrSttusCode(String condEmplyrSttusCode) {
		this.condEmplyrSttusCode = condEmplyrSttusCode;
	}

	public String getDeptNm() {
		return deptNm;
	}

	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}

	public String getMberSe() {
		return mberSe;
	}

	public void setMberSe(String mberSe) {
		this.mberSe = mberSe;
	}

	public String getFrntnAt() {
		return frntnAt;
	}

	public void setFrntnAt(String frntnAt) {
		this.frntnAt = frntnAt;
	}

	public String getRrcsRegistNo() {
		return rrcsRegistNo;
	}

	public void setRrcsRegistNo(String rrcsRegistNo) {
		this.rrcsRegistNo = rrcsRegistNo;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getNlty() {
		return nlty;
	}

	public void setNlty(String nlty) {
		this.nlty = nlty;
	}

	public String getBefoPassword() {
		return befoPassword;
	}

	public void setBefoPassword(String befoPassword) {
		this.befoPassword = befoPassword;
	}
}