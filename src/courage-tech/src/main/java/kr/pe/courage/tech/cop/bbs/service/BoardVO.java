
package kr.pe.courage.tech.cop.bbs.service;

import java.util.Date;
import java.util.List;

import kr.pe.courage.common.annotation.DisallowedField;
import kr.pe.courage.common.annotation.KeepCondition;
import kr.pe.courage.common.core.CommonDefaultVO;
import kr.pe.courage.common.storage.StorageFile;

/**
 * <pre>
 * kr.pe.courage.tech.cop.bbs.service
 * BoardVO.java
 * </pre>
 * 
 * @Author : ChangHo Seok
 * @Date : 2016. 5. 27.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 5. 27., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class BoardVO extends CommonDefaultVO {
	private static final long serialVersionUID = -4030310566143426781L;
	
	public static final String USE_Y = "Y";
	public static final String USE_N = "N";

	private String bbsId;
	private int nttId = 0;
	@DisallowedField private int nttNo;
	private String nttSj;
	private String nttCn;
	private String nttCnText;
	@DisallowedField private int parntsSntncNo = -1;
	@DisallowedField private int groupNo;
	@DisallowedField private int sortOrdr;
	@DisallowedField private int rdcnt;
	private String useAt;
	private String ntcrNm;
	private String password;
	@DisallowedField private String atchFileId;
	@DisallowedField private String mvpRegistSe;
	@DisallowedField private String mvpFileId;
	private String mvpUrl;
	@DisallowedField private String mvpThumbId;
	@DisallowedField private String thumbUrl;
	private String noticeAt;
	private String noticeBgnDe;
	private String noticeEndDe;
	@DisallowedField private String frstRegisterId;
	@DisallowedField private String frstRegisterNm;
	@DisallowedField private Date frstRegistPnttm;
	@DisallowedField private String lastUpdusrId;
	@DisallowedField private String lastUpdusrNm;
	@DisallowedField private Date lastUpdtPnttm;
	
	@DisallowedField private int answerCnt;
	@DisallowedField private int replyCnt;
	
	// 첨부파일 관련 정보
	private String[] uploadAtchFiles;
	private String[] deleteAtchFiles;
	private List<StorageFile> listAtchFiles;
	
	// 동영상 파일 관련 정보
	private String[] uploadMvpFiles;
	private String[] deleteMvpFiles;
	private List<StorageFile> listMvpFiles;

	@KeepCondition private String condNttSj;
	@KeepCondition private String condNttCn;
	@KeepCondition private String condFrstRegisterNm;

	public String getBbsId() {
		return bbsId;
	}

	public void setBbsId(String bbsId) {
		this.bbsId = bbsId;
	}

	public int getNttId() {
		return nttId;
	}

	public void setNttId(int nttId) {
		this.nttId = nttId;
	}

	public int getNttNo() {
		return nttNo;
	}

	public void setNttNo(int nttNo) {
		this.nttNo = nttNo;
	}

	public String getNttSj() {
		return nttSj;
	}

	public void setNttSj(String nttSj) {
		this.nttSj = nttSj;
	}

	public String getNttCn() {
		return nttCn;
	}

	public void setNttCn(String nttCn) {
		this.nttCn = nttCn;
	}

	public String getNttCnText() {
		return nttCnText;
	}

	public void setNttCnText(String nttCnText) {
		this.nttCnText = nttCnText;
	}

	public int getParntsSntncNo() {
		return parntsSntncNo;
	}

	public void setParntsSntncNo(int parntsSntncNo) {
		this.parntsSntncNo = parntsSntncNo;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public int getSortOrdr() {
		return sortOrdr;
	}

	public void setSortOrdr(int sortOrdr) {
		this.sortOrdr = sortOrdr;
	}

	public int getRdcnt() {
		return rdcnt;
	}

	public void setRdcnt(int rdcnt) {
		this.rdcnt = rdcnt;
	}

	public String getUseAt() {
		return useAt;
	}

	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}

	public String getNtcrNm() {
		return ntcrNm;
	}

	public void setNtcrNm(String ntcrNm) {
		this.ntcrNm = ntcrNm;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAtchFileId() {
		return atchFileId;
	}

	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	public String getMvpRegistSe() {
		return mvpRegistSe;
	}

	public void setMvpRegistSe(String mvpRegistSe) {
		this.mvpRegistSe = mvpRegistSe;
	}

	public String getMvpFileId() {
		return mvpFileId;
	}

	public void setMvpFileId(String mvpFileId) {
		this.mvpFileId = mvpFileId;
	}

	public String getMvpUrl() {
		return mvpUrl;
	}

	public void setMvpUrl(String mvpUrl) {
		this.mvpUrl = mvpUrl;
	}

	public String getMvpThumbId() {
		return mvpThumbId;
	}

	public void setMvpThumbId(String mvpThumbId) {
		this.mvpThumbId = mvpThumbId;
	}

	public String getThumbUrl() {
		return thumbUrl;
	}

	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}

	public String getNoticeAt() {
		return noticeAt;
	}

	public void setNoticeAt(String noticeAt) {
		this.noticeAt = noticeAt;
	}

	public String getNoticeBgnDe() {
		return noticeBgnDe;
	}

	public void setNoticeBgnDe(String noticeBgnDe) {
		this.noticeBgnDe = noticeBgnDe;
	}

	public String getNoticeEndDe() {
		return noticeEndDe;
	}

	public void setNoticeEndDe(String noticeEndDe) {
		this.noticeEndDe = noticeEndDe;
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

	public Date getFrstRegistPnttm() {
		return frstRegistPnttm;
	}

	public void setFrstRegistPnttm(Date frstRegistPnttm) {
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

	public Date getLastUpdtPnttm() {
		return lastUpdtPnttm;
	}

	public void setLastUpdtPnttm(Date lastUpdtPnttm) {
		this.lastUpdtPnttm = lastUpdtPnttm;
	}

	public int getAnswerCnt() {
		return answerCnt;
	}

	public void setAnswerCnt(int answerCnt) {
		this.answerCnt = answerCnt;
	}

	public int getReplyCnt() {
		return replyCnt;
	}

	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}

	public String[] getUploadAtchFiles() {
		return uploadAtchFiles;
	}

	public void setUploadAtchFiles(String[] uploadAtchFiles) {
		this.uploadAtchFiles = uploadAtchFiles;
	}

	public String[] getDeleteAtchFiles() {
		return deleteAtchFiles;
	}

	public void setDeleteAtchFiles(String[] deleteAtchFiles) {
		this.deleteAtchFiles = deleteAtchFiles;
	}

	public List<StorageFile> getListAtchFiles() {
		return listAtchFiles;
	}

	public void setListAtchFiles(List<StorageFile> listAtchFiles) {
		this.listAtchFiles = listAtchFiles;
	}

	public String[] getUploadMvpFiles() {
		return uploadMvpFiles;
	}

	public void setUploadMvpFiles(String[] uploadMvpFiles) {
		this.uploadMvpFiles = uploadMvpFiles;
	}

	public String[] getDeleteMvpFiles() {
		return deleteMvpFiles;
	}

	public void setDeleteMvpFiles(String[] deleteMvpFiles) {
		this.deleteMvpFiles = deleteMvpFiles;
	}

	public List<StorageFile> getListMvpFiles() {
		return listMvpFiles;
	}

	public void setListMvpFiles(List<StorageFile> listMvpFiles) {
		this.listMvpFiles = listMvpFiles;
	}

	public String getCondNttSj() {
		return condNttSj;
	}

	public void setCondNttSj(String condNttSj) {
		this.condNttSj = condNttSj;
	}

	public String getCondNttCn() {
		return condNttCn;
	}

	public void setCondNttCn(String condNttCn) {
		this.condNttCn = condNttCn;
	}

	public String getCondFrstRegisterNm() {
		return condFrstRegisterNm;
	}

	public void setCondFrstRegisterNm(String condFrstRegisterNm) {
		this.condFrstRegisterNm = condFrstRegisterNm;
	}
}
