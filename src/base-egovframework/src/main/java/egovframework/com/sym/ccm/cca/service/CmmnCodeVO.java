
package egovframework.com.sym.ccm.cca.service;

import java.io.Serializable;

import kr.pe.courage.common.annotation.KeepCondition;
import kr.pe.courage.common.core.CommonDefaultVO;

/**
 * <pre>
 * egovframework.com.sym.ccm.cca.service
 * CmmnCodeVo.java
 * 공통코드 VO
 * </pre>
 * 
 * @Author : Seok Chang Ho
 * @Date : 2013. 11. 1.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * ================================================================
 * 		수정일		|		수정자			|			수정내용
 * 2013-11-01		석창호					최초등록
 * ================================================================
 * </pre>
 */
public class CmmnCodeVO extends CommonDefaultVO implements Serializable {

	private static final long serialVersionUID = -7338736811532692450L;

	/*
	 * 코드ID
	 */
	private String codeId = "";

	/*
	 * 코드ID명
	 */
	private String codeIdNm = "";

	/*
	 * 상세코드 갯수
	 */
	private int detailCodeCount;

	/*
	 * 코드ID설명
	 */
	private String codeIdDc = "";

	/*
	 * 사용여부
	 */
	private String useAt = "";
	
	/*
	 * 엑셀일괄등록 파일
	 */
	private String excelUploadFile;
	
	/*
	 * 최초등록자ID
	 */
	private String frstRegisterId = "";

	/*
	 * 최초등록자명
	 */
	private String frstRegisterNm = "";

	/*
	 * 최초등록일시
	 */
	private String frstRegistPnttm = "";

	/*
	 * 최종수정자ID
	 */
	private String lastUpdusrId = "";

	/*
	 * 최종수정자명
	 */
	private String lastUpdusrNm = "";

	/*
	 * 최종수정일시
	 */
	private String lastUpdtPnttm = "";
	
	private boolean validateError;
	private StringBuilder validateErrorStr;

	@KeepCondition
	private String condCodeId;
	@KeepCondition
	private String condCodeIdNm;
	@KeepCondition
	private String condUseAt;
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

	public int getDetailCodeCount() {
		return detailCodeCount;
	}

	public void setDetailCodeCount(int detailCodeCount) {
		this.detailCodeCount = detailCodeCount;
	}

	public String getCodeIdDc() {
		return codeIdDc;
	}

	public void setCodeIdDc(String codeIdDc) {
		this.codeIdDc = codeIdDc;
	}

	public String getUseAt() {
		return useAt;
	}

	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	public String getExcelUploadFile() {
		return excelUploadFile;
	}

	public void setExcelUploadFile(String excelUploadFile) {
		this.excelUploadFile = excelUploadFile;
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

	public boolean isValidateError() {
		return validateError;
	}

	public void setValidateError(boolean validateError) {
		this.validateError = validateError;
	}

	public StringBuilder getValidateErrorStr() {
		return validateErrorStr;
	}

	public void setValidateErrorStr(StringBuilder validateErrorStr) {
		this.validateErrorStr = validateErrorStr;
	}

	public String getCondCodeId() {
		return condCodeId;
	}

	public void setCondCodeId(String condCodeId) {
		this.condCodeId = condCodeId;
	}

	public String getCondCodeIdNm() {
		return condCodeIdNm;
	}

	public void setCondCodeIdNm(String condCodeIdNm) {
		this.condCodeIdNm = condCodeIdNm;
	}

	public String getCondUseAt() {
		return condUseAt;
	}

	public void setCondUseAt(String condUseAt) {
		this.condUseAt = condUseAt;
	}

	public String getCondFrstRegisterId() {
		return condFrstRegisterId;
	}

	public void setCondFrstRegisterId(String condFrstRegisterId) {
		this.condFrstRegisterId = condFrstRegisterId;
	}
}
