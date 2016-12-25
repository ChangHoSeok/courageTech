package kr.pe.courage.tech.cop.bbs.service;

import kr.pe.courage.common.annotation.KeepCondition;
import kr.pe.courage.common.core.CommonDefaultVO;

/**
 * <pre>
 * kr.pe.courage.tech.cop.bbs.service
 * BoardMasterVO.java
 * </pre>
 * 
 * @Author : ChangHo Seok
 * @Date : 2016. 5. 20.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 5. 20., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */

public class BoardMasterVO extends CommonDefaultVO {
	private static final long serialVersionUID = -7468691418647337403L;
	
	public static final String AT_Y = "Y";
	public static final String AT_N = "N";

	private String bbsId;
	private String bbsNm;
	private String bbsIntrcn;
	private String bbsTyCode;
	private String bbsAttrbCode;
	private String replyPosblAt;
	private String answerPosblAt;
	private String fileAtchPosblAt;
	private String atchPosblFileNumber;
	private String atchPosblFileSize;
	private String mvpPosblAt;
	private String mvpPosblFileNumber;
	private String mvpPosblFileSize;
	private String useAt;
	private String frstRegisterId;
	private String frstRegisterNm;
	private String frstRegistPnttm;
	private String lastUpdusrId;
	private String lastUpdusrNm;
	private String lastUpdtPnttm;
	
	private boolean posblDelete;

	@KeepCondition
	private String condBbsNm;
	@KeepCondition
	private String condBbsTyCode;
	@KeepCondition
	private String condAttrbCode;
	@KeepCondition
	private String condReplyPosblAt;
	@KeepCondition
	private String condAnswerPosblAt;
	@KeepCondition
	private String condFileAtchPosblAt;
	@KeepCondition
	private String condMvpPosblAt;
	@KeepCondition
	private String condUseAt;

	public BoardMasterVO() {
		setCondOrder("BBS_ID");
		setCondAlign("ASC");
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

	public String getBbsIntrcn() {
		return bbsIntrcn;
	}

	public void setBbsIntrcn(String bbsIntrcn) {
		this.bbsIntrcn = bbsIntrcn;
	}

	public String getBbsTyCode() {
		return bbsTyCode;
	}

	public void setBbsTyCode(String bbsTyCode) {
		this.bbsTyCode = bbsTyCode;
	}

	public String getBbsAttrbCode() {
		return bbsAttrbCode;
	}

	public void setBbsAttrbCode(String bbsAttrbCode) {
		this.bbsAttrbCode = bbsAttrbCode;
	}

	public String getReplyPosblAt() {
		return replyPosblAt;
	}

	public void setReplyPosblAt(String replyPosblAt) {
		this.replyPosblAt = replyPosblAt;
	}

	public String getAnswerPosblAt() {
		return answerPosblAt;
	}

	public void setAnswerPosblAt(String answerPosblAt) {
		this.answerPosblAt = answerPosblAt;
	}

	public String getFileAtchPosblAt() {
		return fileAtchPosblAt;
	}

	public void setFileAtchPosblAt(String fileAtchPosblAt) {
		this.fileAtchPosblAt = fileAtchPosblAt;
	}

	public String getAtchPosblFileNumber() {
		return atchPosblFileNumber;
	}

	public void setAtchPosblFileNumber(String atchPosblFileNumber) {
		this.atchPosblFileNumber = atchPosblFileNumber;
	}

	public String getAtchPosblFileSize() {
		return atchPosblFileSize;
	}

	public void setAtchPosblFileSize(String atchPosblFileSize) {
		this.atchPosblFileSize = atchPosblFileSize;
	}

	public String getMvpPosblAt() {
		return mvpPosblAt;
	}

	public void setMvpPosblAt(String mvpPosblAt) {
		this.mvpPosblAt = mvpPosblAt;
	}

	public String getMvpPosblFileNumber() {
		return mvpPosblFileNumber;
	}

	public void setMvpPosblFileNumber(String mvpPosblFileNumber) {
		this.mvpPosblFileNumber = mvpPosblFileNumber;
	}

	public String getMvpPosblFileSize() {
		return mvpPosblFileSize;
	}

	public void setMvpPosblFileSize(String mvpPosblFileSize) {
		this.mvpPosblFileSize = mvpPosblFileSize;
	}

	public String getUseAt() {
		return useAt;
	}

	public void setUseAt(String useAt) {
		this.useAt = useAt;
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

	public boolean isPosblDelete() {
		return posblDelete;
	}

	public void setPosblDelete(boolean posblDelete) {
		this.posblDelete = posblDelete;
	}

	public String getCondBbsNm() {
		return condBbsNm;
	}

	public void setCondBbsNm(String condBbsNm) {
		this.condBbsNm = condBbsNm;
	}

	public String getCondBbsTyCode() {
		return condBbsTyCode;
	}

	public void setCondBbsTyCode(String condBbsTyCode) {
		this.condBbsTyCode = condBbsTyCode;
	}

	public String getCondAttrbCode() {
		return condAttrbCode;
	}

	public void setCondAttrbCode(String condAttrbCode) {
		this.condAttrbCode = condAttrbCode;
	}

	public String getCondReplyPosblAt() {
		return condReplyPosblAt;
	}

	public void setCondReplyPosblAt(String condReplyPosblAt) {
		this.condReplyPosblAt = condReplyPosblAt;
	}

	public String getCondAnswerPosblAt() {
		return condAnswerPosblAt;
	}

	public void setCondAnswerPosblAt(String condAnswerPosblAt) {
		this.condAnswerPosblAt = condAnswerPosblAt;
	}

	public String getCondFileAtchPosblAt() {
		return condFileAtchPosblAt;
	}

	public void setCondFileAtchPosblAt(String condFileAtchPosblAt) {
		this.condFileAtchPosblAt = condFileAtchPosblAt;
	}

	public String getCondMvpPosblAt() {
		return condMvpPosblAt;
	}

	public void setCondMvpPosblAt(String condMvpPosblAt) {
		this.condMvpPosblAt = condMvpPosblAt;
	}

	public String getCondUseAt() {
		return condUseAt;
	}

	public void setCondUseAt(String condUseAt) {
		this.condUseAt = condUseAt;
	}
}
