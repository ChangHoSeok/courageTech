package kr.pe.courage.tech.uss.info.todo.service;

import org.codehaus.jackson.annotate.JsonIgnore;

import kr.pe.courage.common.annotation.KeepCondition;
import kr.pe.courage.common.core.CommonDefaultVO;

/**
 * <pre>
 * kr.pe.courage.tech.uss.info.todo.service
 * TodoGroupVO.java
 * </pre>
 *
 * @Author	: Seok Chang Ho
 * @Date	: 2015. 7. 26.
 * @Version	: 0.5
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 2015. 7. 26.: , 수정자 : Seok Chang Ho, 수정내용 : 최초등록
 * 2. 수정일 2015. 7. 28.: , 수정자 : Seok Chang Ho, 수정내용 : 조회조건 추가
 * </pre>
 */
public class TodoGroupVO extends CommonDefaultVO {
	private static final long serialVersionUID = 7129823846180334275L;
	private String groupId; // 그룹아이디
	private String groupName; // 그룹명
	private String groupDesc; // 그룹설명
	private String registerId; // 등록자
	private String registDt; // 등록일시
	private String updusrId; // 수정자
	private String updtDt; // 수정일시
	
	@KeepCondition
	@JsonIgnore
	private String condGroupName; // 그룹명
	
	public TodoGroupVO() {
		super();
	}
	
	public TodoGroupVO(String groupId) {
		this.groupId  = groupId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public String getRegisterId() {
		return registerId;
	}

	public void setRegisterId(String registerId) {
		this.registerId = registerId;
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

	public String getUpdtDt() {
		return updtDt;
	}

	public void setUpdtDt(String updtDt) {
		this.updtDt = updtDt;
	}

	public String getCondGroupName() {
		return condGroupName;
	}

	public void setCondGroupName(String condGroupName) {
		this.condGroupName = condGroupName;
	}
}
