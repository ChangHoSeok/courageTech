
package kr.pe.courage.common.code;

import java.io.Serializable;

/**
 * 공통코드 VO 클랙스
 * 
 * <pre>
 * kr.pe.courage.common.code
 * CommonCodeVO.java
 * </pre>
 * 
 * @Author : Seok Chang Ho
 * @Date : 2013. 11. 4.
 * @Version : 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * ================================================================
 * 		수정일		|		수정자			|			수정내용
 * 2013-11-04		석창호					최초등록
 * ================================================================
 * </pre>
 */
public class CommonCodeVO implements Serializable {
	private static final long serialVersionUID = -8007000127795197509L;

	private String codeId; // 코드 아이디
	private String codeIdNm; // 코드 아이디 명칭
	private String codeIdDc; // 코드설명
	private String code; // 코드
	private String codeNm; // 코드명
	private String codeDc; // 코드성명 (상세코드)
	private String rm; // 비고
	private String frstRegisterId; // 최초등록자
	private String ordr; // 코드순서
	private String operation;
	private String useAt;
	private String groupId;
	private String upperCode;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getUpperCode() {
		return upperCode;
	}

	public void setUpperCode(String upperCode) {
		this.upperCode = upperCode;
	}

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

	public String getCodeIdDc() {
		return codeIdDc;
	}

	public void setCodeIdDc(String codeIdDc) {
		this.codeIdDc = codeIdDc;
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

	public String getRm() {
		return rm;
	}

	public void setRm(String rm) {
		this.rm = rm;
	}

	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	public String getOrdr() {
		return ordr;
	}

	public void setOrdr(String ordr) {
		this.ordr = ordr;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getUseAt() {
		return useAt;
	}

	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}
}
