
package egovframework.com.sym.prm.service;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import kr.pe.courage.common.annotation.KeepCondition;
import kr.pe.courage.common.core.CommonDefaultVO;

/**
 * 프로그램목록 처리를 위한 VO 클래스르를 정의한다
 * 
 * @author 개발환경 개발팀 이용
 * @since 2009.06.01
 * @version 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이  용          최초 생성
 * 
 * </pre>
 */

public class ProgrmManageVO extends CommonDefaultVO implements Serializable {

	private static final long serialVersionUID = 6545074530034412589L;

	/** 프로그램파일명 */
	private String progrmFileNm;
	/** 프로그램저장경로 */
	private String progrmStrePath;
	/** 프로그램한글명 */
	private String progrmKoreanNm;
	/** URL */
	private String url;
	/** 프로그램설명 */
	private String progrmDc;
	/** 프로그램구분 */
	private String progrmSe;
	/** 프로그램 기능 구분 */
	private String progrmFnctSe;
	/** 로그인 확인 여부 */
	private String loginCnfirmAt;
	/** 메뉴 연결 개수 */
	private String menuLinkCnt;
	/** Request 구분 */
	private String requestSe;
	/** Request Method 목록 */
	private String requestMethodList;

	private String[] deletelProgrmFileNmList;

	@KeepCondition
	private String condProgrmFileNm;
	@KeepCondition
	private String condProgrmFileNmArray;
	@KeepCondition
	private String condProgrmStrePath;
	@KeepCondition
	private String condProgrmKoreanNm;
	@KeepCondition
	private String condUrl;
	@KeepCondition
	private String condProgrmDc;
	@KeepCondition
	private String condProgrmSe;
	@KeepCondition
	private String condProgrmFncSe;
	@KeepCondition
	private String condMenuNo;
	@KeepCondition
	private String condLoginCheckAt;

	public String getProgrmFileNm() {
		return progrmFileNm;
	}

	public void setProgrmFileNm(String progrmFileNm) {
		this.progrmFileNm = progrmFileNm;
	}

	public String getProgrmStrePath() {
		return progrmStrePath;
	}

	public void setProgrmStrePath(String progrmStrePath) {
		this.progrmStrePath = progrmStrePath;
	}

	public String getProgrmKoreanNm() {
		return progrmKoreanNm;
	}

	public void setProgrmKoreanNm(String progrmKoreanNm) {
		this.progrmKoreanNm = progrmKoreanNm;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getProgrmDc() {
		return progrmDc;
	}

	public void setProgrmDc(String progrmDc) {
		this.progrmDc = progrmDc;
	}

	public String getProgrmSe() {
		return progrmSe;
	}

	public void setProgrmSe(String progrmSe) {
		this.progrmSe = progrmSe;
	}

	public String getProgrmFnctSe() {
		return progrmFnctSe;
	}

	public void setProgrmFnctSe(String progrmFnctSe) {
		this.progrmFnctSe = progrmFnctSe;
	}

	public String getLoginCnfirmAt() {
		return loginCnfirmAt;
	}

	public void setLoginCnfirmAt(String loginCnfirmAt) {
		this.loginCnfirmAt = loginCnfirmAt;
	}

	public String getMenuLinkCnt() {
		return menuLinkCnt;
	}

	public void setMenuLinkCnt(String menuLinkCnt) {
		this.menuLinkCnt = menuLinkCnt;
	}

	public String getRequestSe() {
		return requestSe;
	}

	public void setRequestSe(String requestSe) {
		this.requestSe = requestSe;
	}

	public String getRequestMethodList() {
		return requestMethodList;
	}

	public void setRequestMethodList(String requestMethodList) {
		this.requestMethodList = requestMethodList;
	}

	public String[] getDeletelProgrmFileNmList() {
		return deletelProgrmFileNmList;
	}

	public void setDeletelProgrmFileNmList(String[] deletelProgrmFileNmList) {
		this.deletelProgrmFileNmList = deletelProgrmFileNmList;
	}

	public String getCondProgrmFileNm() {
		return condProgrmFileNm;
	}

	public void setCondProgrmFileNm(String condProgrmFileNm) {
		this.condProgrmFileNm = condProgrmFileNm;
	}

	public String getCondProgrmFileNmArray() {
		return condProgrmFileNmArray;
	}

	public void setCondProgrmFileNmArray(String condProgrmFileNmArray) {
		this.condProgrmFileNmArray = condProgrmFileNmArray;
	}

	public String getCondProgrmStrePath() {
		return condProgrmStrePath;
	}

	public void setCondProgrmStrePath(String condProgrmStrePath) {
		this.condProgrmStrePath = condProgrmStrePath;
	}

	public String getCondProgrmKoreanNm() {
		return condProgrmKoreanNm;
	}

	public void setCondProgrmKoreanNm(String condProgrmKoreanNm) {
		this.condProgrmKoreanNm = condProgrmKoreanNm;
	}

	public String getCondUrl() {
		return condUrl;
	}

	public void setCondUrl(String condUrl) {
		this.condUrl = condUrl;
	}

	public String getCondProgrmDc() {
		return condProgrmDc;
	}

	public void setCondProgrmDc(String condProgrmDc) {
		this.condProgrmDc = condProgrmDc;
	}

	public String getCondProgrmSe() {
		return condProgrmSe;
	}

	public void setCondProgrmSe(String condProgrmSe) {
		this.condProgrmSe = condProgrmSe;
	}

	public String getCondProgrmFncSe() {
		return condProgrmFncSe;
	}

	public void setCondProgrmFncSe(String condProgrmFncSe) {
		this.condProgrmFncSe = condProgrmFncSe;
	}

	public String getCondMenuNo() {
		return condMenuNo;
	}

	public void setCondMenuNo(String condMenuNo) {
		this.condMenuNo = condMenuNo;
	}

	public String getCondLoginCheckAt() {
		return condLoginCheckAt;
	}

	public void setCondLoginCheckAt(String condLoginCheckAt) {
		this.condLoginCheckAt = condLoginCheckAt;
	}
	
	/*
	 * EHCache 동일성을 구분하기 위한 equals method
	 */
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	/*
	 * EHCache 동일성을 구분하기 위한 hashCode method
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}