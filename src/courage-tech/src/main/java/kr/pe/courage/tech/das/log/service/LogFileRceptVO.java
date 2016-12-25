
package kr.pe.courage.tech.das.log.service;

import java.util.Date;
import java.util.List;

import kr.pe.courage.common.annotation.KeepCondition;
import kr.pe.courage.common.core.CommonDefaultVO;
import kr.pe.courage.common.storage.StorageFile;

/**
 * <pre>
 * kr.pe.courage.tech.das.log.service
 * LogFileRceptVO.java
 * </pre>
 * 
 * @Author : ChangHo Seok
 * @Date : 2016. 7. 21.
 * @Version :
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 7. 21., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class LogFileRceptVO extends CommonDefaultVO {
	private static final long serialVersionUID = 7069889186828257242L;
	
	public static final String PROCESS_STTUS_WAIT = "1";
	public static final String PROCESS_STTUS_START = "2";
	public static final String PROCESS_STTUS_COMPT = "3";
	public static final String PROCESS_STTUS_ERROR = "4";
	public static final String PROCESS_STTUS_ETC = "5";
	
	public static final String RECPT_TYPE_NEW = "1";
	public static final String RECPT_TYPE_ADIT = "2";

	private String groupId;
	private String groupNm;
	private String rceptId;
	private Date rceptDt;
	private String rceptFileId;
	private String processSttus;
	private String rceptTy;
	private String frstRegisterId;
	private String frstRegisterNm;
	private Date frstRegistPnttm;
	private String lastUpdusrId;
	private Date lastUpdtPnttm;
	private int rceptDataCnt;
	
	private String[] uploadAtchFiles;
	private List<StorageFile> atchFileList;

	@KeepCondition
	private String condGroupId;
	
	public LogFileRceptVO() {
		setCondOrder("A.RCEPT_DT");
		setCondAlign("DESC");
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

	public String getRceptId() {
		return rceptId;
	}

	public void setRceptId(String rceptId) {
		this.rceptId = rceptId;
	}

	public Date getRceptDt() {
		return rceptDt;
	}

	public void setRceptDt(Date rceptDt) {
		this.rceptDt = rceptDt;
	}

	public String getRceptFileId() {
		return rceptFileId;
	}

	public void setRceptFileId(String rceptFileId) {
		this.rceptFileId = rceptFileId;
	}

	public String getProcessSttus() {
		return processSttus;
	}

	public void setProcessSttus(String processSttus) {
		this.processSttus = processSttus;
	}

	public String getRceptTy() {
		return rceptTy;
	}

	public void setRceptTy(String rceptTy) {
		this.rceptTy = rceptTy;
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

	public Date getLastUpdtPnttm() {
		return lastUpdtPnttm;
	}

	public void setLastUpdtPnttm(Date lastUpdtPnttm) {
		this.lastUpdtPnttm = lastUpdtPnttm;
	}

	public int getRceptDataCnt() {
		return rceptDataCnt;
	}

	public void setRceptDataCnt(int rceptDataCnt) {
		this.rceptDataCnt = rceptDataCnt;
	}

	public String[] getUploadAtchFiles() {
		return uploadAtchFiles;
	}

	public void setUploadAtchFiles(String[] uploadAtchFiles) {
		this.uploadAtchFiles = uploadAtchFiles;
	}

	public List<StorageFile> getAtchFileList() {
		return atchFileList;
	}

	public void setAtchFileList(List<StorageFile> atchFileList) {
		this.atchFileList = atchFileList;
	}

	public String getCondGroupId() {
		return condGroupId;
	}

	public void setCondGroupId(String condGroupId) {
		this.condGroupId = condGroupId;
	}
}
