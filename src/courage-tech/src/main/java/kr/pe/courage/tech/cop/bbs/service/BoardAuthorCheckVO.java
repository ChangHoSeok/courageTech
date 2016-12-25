
package kr.pe.courage.tech.cop.bbs.service;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * <pre>
 * kr.pe.courage.tech.cop.bbs.service
 * BoardAuthorCheckVO.java
 * </pre>
 * 
 * @Author : ChangHo Seok
 * @Date : 2016. 7. 12.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 7. 12., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class BoardAuthorCheckVO implements Serializable {
	private static final long serialVersionUID = 7267514078569055957L;
	
	public static final String CHECK_LIST = "LIST";
	public static final String CHECK_READNG = "READNG";
	public static final String CHECK_SNTNC_WRITNG = "SNTNC_WRITNG";
	public static final String CHECK_ANSWER_WRITNG = "ANSWER_WRITNG";
	public static final String CHECK_MNGR_AUTHOR = "MNGR_AUTHOR";
	
	private int list;
	private int readng;
	private int sntncWritng;
	private int answerWritng;
	private int mngrAuthor;

	private String bbsId;
	private String[] auhtorCodes;

	public int getList() {
		return list;
	}

	public void setList(int list) {
		this.list = list;
	}

	public int getReadng() {
		return readng;
	}

	public void setReadng(int readng) {
		this.readng = readng;
	}

	public int getSntncWritng() {
		return sntncWritng;
	}

	public void setSntncWritng(int sntncWritng) {
		this.sntncWritng = sntncWritng;
	}

	public int getAnswerWritng() {
		return answerWritng;
	}

	public void setAnswerWritng(int answerWritng) {
		this.answerWritng = answerWritng;
	}

	public int getMngrAuthor() {
		return mngrAuthor;
	}

	public void setMngrAuthor(int mngrAuthor) {
		this.mngrAuthor = mngrAuthor;
	}

	public String getBbsId() {
		return bbsId;
	}

	public void setBbsId(String bbsId) {
		this.bbsId = bbsId;
	}

	public String[] getAuhtorCodes() {
		return auhtorCodes;
	}

	public void setAuhtorCodes(String[] auhtorCodes) {
		this.auhtorCodes = auhtorCodes;
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
