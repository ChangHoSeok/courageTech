
package kr.pe.courage.common.hiercode;

import kr.pe.courage.common.annotation.KeepCondition;
import kr.pe.courage.common.core.CommonDefaultVO;

/**
 * <pre>
 * kr.pe.courage.common.hiercode
 * HierCodeVO.java
 * </pre>
 * 
 * @Author : Seok Chang Ho
 * @Date : 2013. 11. 21.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * ================================================================
 * 		수정일		|		수정자			|			수정내용
 *  2013.11.21    석창호                                           최초등록
 * ================================================================
 * </pre>
 */
public class HierCodeVO extends CommonDefaultVO {

	public HierCodeVO() {
		setCondOrder("ORDR");
		setCondAlign("ASC");
	}

	private static final long serialVersionUID = -2415234318275932310L;
	
	private String groupId; // 그룹아이디
	private String groupNm; // 그룹명
	private String groupDc; // 그룹 설명
	private String groupUseAt; // 그룹 사용여부
	private String groupOrdr; // 그룹 정렬순서
	private String code; // 코드
	private String codeNm; // 코드명
	private String codeDc; // 코드설명
	private String codeUseAt; // 코드사용여부
	private String codeOrdr; // 코드 정렬순서
	private String upperCode; // 상위코드

	private String registerId; // 등록자
	private String registerNm; // 등록자명
	private String registDt; // 등록일시
	private String updusrId; // 수정자
	private String updusrNm; // 수정자명
	private String updtDt; // 수정일시

	@KeepCondition
	private String groupCdFlag; // 그룹 또는 상세코드 구분 true : 그룹코드, false : 상세코드
	@KeepCondition
	private String condGroupId;
	@KeepCondition
	private String condGroupNm;
	@KeepCondition
	private String condGroupUseAt;
	@KeepCondition
	private String condCode;
	@KeepCondition
	private String condCodeNm;
	@KeepCondition
	private String condCodeUseAt;

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

	public String getGroupDc() {
		return groupDc;
	}

	public void setGroupDc(String groupDc) {
		this.groupDc = groupDc;
	}

	public String getGroupUseAt() {
		return groupUseAt;
	}

	public void setGroupUseAt(String groupUseAt) {
		this.groupUseAt = groupUseAt;
	}

	public String getGroupOrdr() {
		return groupOrdr;
	}

	public void setGroupOrdr(String groupOrdr) {
		this.groupOrdr = groupOrdr;
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

	public String getCodeUseAt() {
		return codeUseAt;
	}

	public void setCodeUseAt(String codeUseAt) {
		this.codeUseAt = codeUseAt;
	}

	public String getCodeOrdr() {
		return codeOrdr;
	}

	public void setCodeOrdr(String codeOrdr) {
		this.codeOrdr = codeOrdr;
	}

	public String getUpperCode() {
		return upperCode;
	}

	public void setUpperCode(String upperCode) {
		this.upperCode = upperCode;
	}

	public String getRegisterId() {
		return registerId;
	}

	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}

	public String getRegisterNm() {
		return registerNm;
	}

	public void setRegisterNm(String registerNm) {
		this.registerNm = registerNm;
	}

	public String getRegistDt() {
		return registDt;
	}

	public void setRegistDt(String registDt) {
		this.registDt = registDt;
	}

	public String getUpdusrId() {
		return updusrId;
	}

	public void setUpdusrId(String updusrId) {
		this.updusrId = updusrId;
	}

	public String getUpdusrNm() {
		return updusrNm;
	}

	public void setUpdusrNm(String updusrNm) {
		this.updusrNm = updusrNm;
	}

	public String getUpdtDt() {
		return updtDt;
	}

	public void setUpdtDt(String updtDt) {
		this.updtDt = updtDt;
	}

	public String getGroupCdFlag() {
		return groupCdFlag;
	}

	public void setGroupCdFlag(String groupCdFlag) {
		this.groupCdFlag = groupCdFlag;
	}

	public String getCondGroupId() {
		return condGroupId;
	}

	public void setCondGroupId(String condGroupId) {
		this.condGroupId = condGroupId;
	}

	public String getCondGroupNm() {
		return condGroupNm;
	}

	public void setCondGroupNm(String condGroupNm) {
		this.condGroupNm = condGroupNm;
	}

	public String getCondGroupUseAt() {
		return condGroupUseAt;
	}

	public void setCondGroupUseAt(String condGroupUseAt) {
		this.condGroupUseAt = condGroupUseAt;
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

	public String getCondCodeUseAt() {
		return condCodeUseAt;
	}

	public void setCondCodeUseAt(String condCodeUseAt) {
		this.condCodeUseAt = condCodeUseAt;
	}
}
