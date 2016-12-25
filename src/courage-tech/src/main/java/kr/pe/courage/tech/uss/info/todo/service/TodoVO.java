
package kr.pe.courage.tech.uss.info.todo.service;

import kr.pe.courage.common.annotation.KeepCondition;
import kr.pe.courage.common.core.CommonDefaultVO;

/**
 * <pre>
 * kr.pe.courage.tech.uss.info.todo.service
 * TodoVO.java
 * </pre>
 *
 * @Author : Seok Chang Ho
 * @String : 2015. 7. 26.
 * @Version :
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 2015. 7. 26.: , 수정자 : Seok Chang Ho, 수정내용 : 최초등록
 * </pre>
 */
public class TodoVO extends CommonDefaultVO {
	private static final long serialVersionUID = 3568511484511310059L;
	
	public static final String TODO_COMPLEATED = "Y";
	public static final String TODO_UNCOMPLETED = "N";

	private String groupId; // 그룹아이디
	private String todoId; // todo 아이디
	private String sj; // 제목
	private String dc; // 설명
	private String comptAt; // 완료여부
	private String comptDt; // 완료일시
	private String registerId; // 등록자
	private String registDt; // 등록일시
	private String updusrId; // 수정자
	private String updtDt; // 수정일시

	@KeepCondition
	private String condGroupId;

	@KeepCondition
	private String condSj;

	@KeepCondition
	private String condDc;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getTodoId() {
		return todoId;
	}

	public void setTodoId(String todoId) {
		this.todoId = todoId;
	}

	public String getSj() {
		return sj;
	}

	public void setSj(String sj) {
		this.sj = sj;
	}

	public String getDc() {
		return dc;
	}

	public void setDc(String dc) {
		this.dc = dc;
	}

	public String getComptAt() {
		return comptAt;
	}

	public void setComptAt(String comptAt) {
		this.comptAt = comptAt;
	}

	public String getComptDt() {
		return comptDt;
	}

	public void setComptDt(String comptDt) {
		this.comptDt = comptDt;
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

	public String getCondGroupId() {
		return condGroupId;
	}

	public void setCondGroupId(String condGroupId) {
		this.condGroupId = condGroupId;
	}

	public String getCondSj() {
		return condSj;
	}

	public void setCondSj(String condSj) {
		this.condSj = condSj;
	}

	public String getCondDc() {
		return condDc;
	}

	public void setCondDc(String condDc) {
		this.condDc = condDc;
	}
}
