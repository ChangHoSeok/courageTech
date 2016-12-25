
package egovframework.com.sym.ccm.cde.service;

import java.io.Serializable;

import kr.pe.courage.common.annotation.KeepCondition;
import kr.pe.courage.common.core.CommonDefaultVO;

/**
 * 
 * 공통상세코드 VO 클래스
 * 
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 * 
 * </pre>
 */
public class CmmnDetailCodeVO extends CommonDefaultVO implements Serializable {

	private static final long serialVersionUID = 9137280036724974467L;

	/*
	 * 코드ID
	 */
	private String codeId;

	/*
	 * 코드ID명
	 */
	private String codeIdNm;

	/*
	 * 코드
	 */
	private String code;

	/*
	 * 코드명
	 */
	private String codeNm;

	/*
	 * 코드설명
	 */
	private String codeDc;

	/*
	 * 사용여부
	 */
	private String useAt;

	/*
	 * 비고
	 */
	private String rm;

	/*
	 * 정렬순서
	 */
	private String ordr;

	/*
	 * 최초등록자ID
	 */
	private String frstRegisterId;

	/*
	 * 최초등록자명
	 */
	private String frstRegisterNm;

	/*
	 * 최초등록일시
	 */
	private String frstRegistPnttm;

	/*
	 * 최종수정자ID
	 */
	private String lastUpdusrId;

	/*
	 * 최종수정자명
	 */
	private String lastUpdusrNm;

	/*
	 * 최종수정일시
	 */
	private String lastUpdtPnttm;

	private String operation;

	/*
	 * 조회조건
	 */
	@KeepCondition
	private String condCodeId;
	@KeepCondition
	private String condCode;
	@KeepCondition
	private String condCodeNm;
	@KeepCondition
	private String condUseAt;
	@KeepCondition
	private String condDetailUseAt;
	@KeepCondition
	private String condFrstRegisterId;

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getCodeIdNm() {
		return codeIdNm;
	}

	public void setCodeIdNm(String codeIdNm) {
		this.codeIdNm = codeIdNm;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeNm() {
		return codeNm;
	}

	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}

	public String getCodeDc() {
		return codeDc;
	}

	public void setCodeDc(String codeDc) {
		this.codeDc = codeDc;
	}

	public String getUseAt() {
		return useAt;
	}

	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	public String getRm() {
		return rm;
	}

	public void setRm(String rm) {
		this.rm = rm;
	}

	public String getOrdr() {
		return ordr;
	}

	public void setOrdr(String ordr) {
		this.ordr = ordr;
	}

	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	public String getFrstRegisterNm() {
		return frstRegisterNm;
	}

	public void setFrstRegisterNm(String frstRegisterNm) {
		this.frstRegisterNm = frstRegisterNm;
	}

	public String getFrstRegistPnttm() {
		return frstRegistPnttm;
	}

	public void setFrstRegistPnttm(String frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}

	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	public String getLastUpdusrNm() {
		return lastUpdusrNm;
	}

	public void setLastUpdusrNm(String lastUpdusrNm) {
		this.lastUpdusrNm = lastUpdusrNm;
	}

	public String getLastUpdtPnttm() {
		return lastUpdtPnttm;
	}

	public void setLastUpdtPnttm(String lastUpdtPnttm) {
		this.lastUpdtPnttm = lastUpdtPnttm;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getCondCodeId() {
		return condCodeId;
	}

	public void setCondCodeId(String condCodeId) {
		this.condCodeId = condCodeId;
	}

	public String getCondCode() {
		return condCode;
	}

	public void setCondCode(String condCode) {
		this.condCode = condCode;
	}

	public String getCondCodeNm() {
		return condCodeNm;
	}

	public void setCondCodeNm(String condCodeNm) {
		this.condCodeNm = condCodeNm;
	}

	public String getCondUseAt() {
		return condUseAt;
	}

	public void setCondUseAt(String condUseAt) {
		this.condUseAt = condUseAt;
	}

	public String getCondDetailUseAt() {
		return condDetailUseAt;
	}

	public void setCondDetailUseAt(String condDetailUseAt) {
		this.condDetailUseAt = condDetailUseAt;
	}

	public String getCondFrstRegisterId() {
		return condFrstRegisterId;
	}

	public void setCondFrstRegisterId(String condFrstRegisterId) {
		this.condFrstRegisterId = condFrstRegisterId;
	}
}
