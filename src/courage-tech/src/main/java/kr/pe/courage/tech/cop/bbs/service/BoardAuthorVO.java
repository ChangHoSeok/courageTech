
package kr.pe.courage.tech.cop.bbs.service;

import kr.pe.courage.common.annotation.KeepCondition;
import kr.pe.courage.common.core.CommonDefaultVO;

/**
 * <pre>
 * kr.pe.courage.tech.cop.bbs.service
 * BoardAuthorVO.java
 * </pre>
 * 
 * @Author : ChangHo Seok
 * @Date : 2016. 7. 8.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 7. 8., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class BoardAuthorVO extends CommonDefaultVO {
	private static final long serialVersionUID = -8323724552041588423L;

	private String authorCode;
	private String authorNm;
	private String bbsId;
	private String bbsNm;
	private String list;
	private String readng;
	private String sntncWritng;
	private String answerWritng;
	private String mngrAuthor;

	private String[] authorCodes;
	private String[] bbsIds;

	private String[] lists;
	private String[] readngs;
	private String[] sntncWritngs;
	private String[] answerWritngs;
	private String[] mngrAuthors;
	
	@KeepCondition private String condBbsId;
	@KeepCondition private String condAuthorCode;
	@KeepCondition private String condRetrieveStdr;
	
	public String getAuthorCode() {
		return authorCode;
	}

	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}

	public String getAuthorNm() {
		return authorNm;
	}

	public void setAuthorNm(String authorNm) {
		this.authorNm = authorNm;
	}

	public String getBbsId() {
		return bbsId;
	}

	public void setBbsId(String bbsId) {
		this.bbsId = bbsId;
	}

	public String getBbsNm() {
		return bbsNm;
	}

	public void setBbsNm(String bbsNm) {
		this.bbsNm = bbsNm;
	}

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

	public String getReadng() {
		return readng;
	}

	public void setReadng(String readng) {
		this.readng = readng;
	}

	public String getSntncWritng() {
		return sntncWritng;
	}

	public void setSntncWritng(String sntncWritng) {
		this.sntncWritng = sntncWritng;
	}

	public String getAnswerWritng() {
		return answerWritng;
	}

	public void setAnswerWritng(String answerWritng) {
		this.answerWritng = answerWritng;
	}

	public String getMngrAuthor() {
		return mngrAuthor;
	}

	public void setMngrAuthor(String mngrAuthor) {
		this.mngrAuthor = mngrAuthor;
	}

	public String[] getAuthorCodes() {
		return authorCodes;
	}

	public void setAuthorCodes(String[] authorCodes) {
		this.authorCodes = authorCodes;
	}

	public String[] getBbsIds() {
		return bbsIds;
	}

	public void setBbsIds(String[] bbsIds) {
		this.bbsIds = bbsIds;
	}

	public String[] getLists() {
		return lists;
	}

	public void setLists(String[] lists) {
		this.lists = lists;
	}

	public String[] getReadngs() {
		return readngs;
	}

	public void setReadngs(String[] readngs) {
		this.readngs = readngs;
	}

	public String[] getSntncWritngs() {
		return sntncWritngs;
	}

	public void setSntncWritngs(String[] sntncWritngs) {
		this.sntncWritngs = sntncWritngs;
	}

	public String[] getAnswerWritngs() {
		return answerWritngs;
	}

	public void setAnswerWritngs(String[] answerWritngs) {
		this.answerWritngs = answerWritngs;
	}

	public String[] getMngrAuthors() {
		return mngrAuthors;
	}

	public void setMngrAuthors(String[] mngrAuthors) {
		this.mngrAuthors = mngrAuthors;
	}

	public String getCondBbsId() {
		return condBbsId;
	}

	public void setCondBbsId(String condBbsId) {
		this.condBbsId = condBbsId;
	}

	public String getCondAuthorCode() {
		return condAuthorCode;
	}

	public void setCondAuthorCode(String condAuthorCode) {
		this.condAuthorCode = condAuthorCode;
	}

	public String getCondRetrieveStdr() {
		return condRetrieveStdr;
	}

	public void setCondRetrieveStdr(String condRetrieveStdr) {
		this.condRetrieveStdr = condRetrieveStdr;
	}
}
