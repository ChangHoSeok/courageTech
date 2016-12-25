
package kr.pe.courage.tech.das.log.service;

import kr.pe.courage.common.annotation.KeepCondition;
import kr.pe.courage.common.core.CommonDefaultVO;

/**
 * <pre>
 * kr.pe.courage.tech.das.log.service
 * LogClGroupVO.java
 * </pre>
 * 
 * @Author : ChangHo Seok
 * @Date : 2016. 7. 18.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 7. 18., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class LogClGroupVO extends CommonDefaultVO {
	private static final long serialVersionUID = 1945122252980006110L;

	private String groupId;
	private String groupNm;
	private String groupDc;

	@KeepCondition private String condGroupId;
	@KeepCondition private String condGroupNm;
	@KeepCondition private String condGroupDc;
	
	public LogClGroupVO() {
		this.setCondOrder("GROUP_ID");
		this.setCondAlign("ASC");
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

	public String getGroupDc() {
		return groupDc;
	}

	public void setGroupDc(String groupDc) {
		this.groupDc = groupDc;
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

	public String getCondGroupDc() {
		return condGroupDc;
	}

	public void setCondGroupDc(String condGroupDc) {
		this.condGroupDc = condGroupDc;
	}
}
