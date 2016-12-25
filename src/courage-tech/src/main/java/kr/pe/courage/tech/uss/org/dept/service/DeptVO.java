
package kr.pe.courage.tech.uss.org.dept.service;

import java.util.Map;

import kr.pe.courage.common.annotation.KeepCondition;
import kr.pe.courage.common.core.CommonDefaultVO;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import egovframework.rte.fdl.excel.CourageExcelVO;

/**
 * <pre>
 * kr.pe.courage.tech.uss.org.dept.service
 * DeptVO.java
 * </pre>
 * 
 * @Author : ChangHo Seok
 * @Date : 2016. 9. 29.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 9. 29., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class DeptVO extends CommonDefaultVO implements CourageExcelVO {
	private static final long serialVersionUID = -4171993823224684160L;

	private String deptCode;
	private String allDeptNm;
	private String lowestDeptNm;
	private String odr;
	private String ord;
	private String atmbUpperDeptCode;
	private String bestDeptCode;
	private String psitnDeptOdr;
	private String ablSe;
	private int subDeptCnt;
	
	private int rowNum;
	private boolean hasErrors;
	private Map<String, String> errors;
	
	@KeepCondition
	private String condDeptCode;
	@KeepCondition
	private String condAllDeptNm;
	@KeepCondition
	private String condAtmbUpperDeptCode;
	@KeepCondition
	private String condBestDeptCode;
	@KeepCondition
	private String condAblSe;
	
	public DeptVO() {
		setCondOrder("DEPT_CODE");
		setCondAlign("ASC");
	}
	
	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getAllDeptNm() {
		return allDeptNm;
	}

	public void setAllDeptNm(String allDeptNm) {
		this.allDeptNm = allDeptNm;
	}

	public String getLowestDeptNm() {
		return lowestDeptNm;
	}

	public void setLowestDeptNm(String lowestDeptNm) {
		this.lowestDeptNm = lowestDeptNm;
	}

	public String getOdr() {
		return odr;
	}

	public void setOdr(String odr) {
		this.odr = odr;
	}

	public String getOrd() {
		return ord;
	}

	public void setOrd(String ord) {
		this.ord = ord;
	}

	public String getAtmbUpperDeptCode() {
		return atmbUpperDeptCode;
	}

	public void setAtmbUpperDeptCode(String atmbUpperDeptCode) {
		this.atmbUpperDeptCode = atmbUpperDeptCode;
	}

	public String getBestDeptCode() {
		return bestDeptCode;
	}

	public void setBestDeptCode(String bestDeptCode) {
		this.bestDeptCode = bestDeptCode;
	}

	public String getPsitnDeptOdr() {
		return psitnDeptOdr;
	}

	public void setPsitnDeptOdr(String psitnDeptOdr) {
		this.psitnDeptOdr = psitnDeptOdr;
	}

	public String getAblSe() {
		return ablSe;
	}

	public void setAblSe(String ablSe) {
		this.ablSe = ablSe;
	}

	public int getSubDeptCnt() {
		return subDeptCnt;
	}

	public void setSubDeptCnt(int subDeptCnt) {
		this.subDeptCnt = subDeptCnt;
	}

	@Override
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	@Override
	public int getRowNum() {
		return this.rowNum;
	}

	@Override
	public boolean hasErrors() {
		return hasErrors;
	}
	
	@Override
	public void setHasErrors(boolean hasErrors) {
		this.hasErrors = hasErrors;
	}
	
	@Override
	public Map<String, String> getErrors() {
		return errors;
	}

	@Override
	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	public String getCondDeptCode() {
		return condDeptCode;
	}

	public void setCondDeptCode(String condDeptCode) {
		this.condDeptCode = condDeptCode;
	}

	public String getCondAllDeptNm() {
		return condAllDeptNm;
	}

	public void setCondAllDeptNm(String condAllDeptNm) {
		this.condAllDeptNm = condAllDeptNm;
	}

	public String getCondAtmbUpperDeptCode() {
		return condAtmbUpperDeptCode;
	}

	public void setCondAtmbUpperDeptCode(String condAtmbUpperDeptCode) {
		this.condAtmbUpperDeptCode = condAtmbUpperDeptCode;
	}

	public String getCondBestDeptCode() {
		return condBestDeptCode;
	}

	public void setCondBestDeptCode(String condBestDeptCode) {
		this.condBestDeptCode = condBestDeptCode;
	}

	public String getCondAblSe() {
		return condAblSe;
	}

	public void setCondAblSe(String condAblSe) {
		this.condAblSe = condAblSe;
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
