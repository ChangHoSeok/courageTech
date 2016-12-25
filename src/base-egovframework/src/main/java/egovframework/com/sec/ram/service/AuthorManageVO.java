
package egovframework.com.sec.ram.service;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;

import kr.pe.courage.common.annotation.KeepCondition;
import kr.pe.courage.common.core.CommonDefaultVO;

/**
 * <pre>
 * egovframework.com.sec.ram.service
 * AuthorManageVO.java
 * </pre>
 *
 * @Author	: Seok Chang Ho
 * @Date	: 2014. 12. 11.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2014-12-11, 수정자 : 석창호, 수정내용 : 최초등록
 * </pre>
 */
public class AuthorManageVO extends CommonDefaultVO implements Serializable {
	private static final long serialVersionUID = 4028933733601514518L;
	
	@JsonIgnore
	private String authorCode;
	@JsonIgnore
	private String authorCreatDe;
	@JsonIgnore
	private String authorDc;
	@JsonIgnore
	private String authorNm;
	@JsonIgnore
	private String progrmFileNm;
	@JsonIgnore
	private String mberBassAuthorYn;
	@JsonIgnore
	private String authorIndexUrl;

	@JsonIgnore
	private String emplyrId;
	@JsonIgnore
	private String defaultAuthorYn;
	@JsonIgnore
	private String esntlId;

	@JsonIgnore
	private String[] authorCodeArray;
	@JsonIgnore
	private String[] defaultAuthorYnArray;

	private String searchFlag;
	private String createFlag;
	private String customFlag1;
	private String customFlag2;
	private String customFlag3;
	@JsonIgnore
	private String[] authorMenuArray;
	@JsonIgnore
	private String[] searchFlagArray;
	@JsonIgnore
	private String[] createFlagArray;
	@JsonIgnore
	private String[] customFlag1Array;
	@JsonIgnore
	private String[] customFlag2Array;
	@JsonIgnore
	private String[] customFlag3Array;

	@JsonIgnore
	@KeepCondition
	private String condAuthorCode;
	@JsonIgnore
	@KeepCondition
	private String condAuthorNm;
	@JsonIgnore
	@KeepCondition
	private String condMberBassAuthorYn;

	public String getAuthorCode() {
		return authorCode;
	}

	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}

	public String getAuthorCreatDe() {
		return authorCreatDe;
	}

	public void setAuthorCreatDe(String authorCreatDe) {
		this.authorCreatDe = authorCreatDe;
	}

	public String getAuthorDc() {
		return authorDc;
	}

	public void setAuthorDc(String authorDc) {
		this.authorDc = authorDc;
	}

	public String getAuthorNm() {
		return authorNm;
	}

	public void setAuthorNm(String authorNm) {
		this.authorNm = authorNm;
	}

	public String getProgrmFileNm() {
		return progrmFileNm;
	}

	public void setProgrmFileNm(String progrmFileNm) {
		this.progrmFileNm = progrmFileNm;
	}

	public String getMberBassAuthorYn() {
		return mberBassAuthorYn;
	}

	public void setMberBassAuthorYn(String mberBassAuthorYn) {
		this.mberBassAuthorYn = mberBassAuthorYn;
	}

	public String getAuthorIndexUrl() {
		return authorIndexUrl;
	}

	public void setAuthorIndexUrl(String authorIndexUrl) {
		this.authorIndexUrl = authorIndexUrl;
	}

	public String getEmplyrId() {
		return emplyrId;
	}

	public void setEmplyrId(String emplyrId) {
		this.emplyrId = emplyrId;
	}

	public String getDefaultAuthorYn() {
		return defaultAuthorYn;
	}

	public void setDefaultAuthorYn(String defaultAuthorYn) {
		this.defaultAuthorYn = defaultAuthorYn;
	}

	public String getEsntlId() {
		return esntlId;
	}

	public void setEsntlId(String esntlId) {
		this.esntlId = esntlId;
	}

	public String[] getAuthorCodeArray() {
		return authorCodeArray;
	}

	public void setAuthorCodeArray(String[] authorCodeArray) {
		this.authorCodeArray = authorCodeArray;
	}

	public String[] getDefaultAuthorYnArray() {
		return defaultAuthorYnArray;
	}

	public void setDefaultAuthorYnArray(String[] defaultAuthorYnArray) {
		this.defaultAuthorYnArray = defaultAuthorYnArray;
	}

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

	public String getCreateFlag() {
		return createFlag;
	}

	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
	}

	public String getCustomFlag1() {
		return customFlag1;
	}

	public void setCustomFlag1(String customFlag1) {
		this.customFlag1 = customFlag1;
	}

	public String getCustomFlag2() {
		return customFlag2;
	}

	public void setCustomFlag2(String customFlag2) {
		this.customFlag2 = customFlag2;
	}

	public String getCustomFlag3() {
		return customFlag3;
	}

	public void setCustomFlag3(String customFlag3) {
		this.customFlag3 = customFlag3;
	}

	public String[] getAuthorMenuArray() {
		return authorMenuArray;
	}

	public void setAuthorMenuArray(String[] authorMenuArray) {
		this.authorMenuArray = authorMenuArray;
	}

	public String[] getSearchFlagArray() {
		return searchFlagArray;
	}

	public void setSearchFlagArray(String[] searchFlagArray) {
		this.searchFlagArray = searchFlagArray;
	}

	public String[] getCreateFlagArray() {
		return createFlagArray;
	}

	public void setCreateFlagArray(String[] createFlagArray) {
		this.createFlagArray = createFlagArray;
	}

	public String[] getCustomFlag1Array() {
		return customFlag1Array;
	}

	public void setCustomFlag1Array(String[] customFlag1Array) {
		this.customFlag1Array = customFlag1Array;
	}

	public String[] getCustomFlag2Array() {
		return customFlag2Array;
	}

	public void setCustomFlag2Array(String[] customFlag2Array) {
		this.customFlag2Array = customFlag2Array;
	}

	public String[] getCustomFlag3Array() {
		return customFlag3Array;
	}

	public void setCustomFlag3Array(String[] customFlag3Array) {
		this.customFlag3Array = customFlag3Array;
	}

	public String getCondAuthorCode() {
		return condAuthorCode;
	}

	public void setCondAuthorCode(String condAuthorCode) {
		this.condAuthorCode = condAuthorCode;
	}

	public String getCondAuthorNm() {
		return condAuthorNm;
	}

	public void setCondAuthorNm(String condAuthorNm) {
		this.condAuthorNm = condAuthorNm;
	}

	public String getCondMberBassAuthorYn() {
		return condMberBassAuthorYn;
	}

	public void setCondMberBassAuthorYn(String condMberBassAuthorYn) {
		this.condMberBassAuthorYn = condMberBassAuthorYn;
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