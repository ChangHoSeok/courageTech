
package kr.pe.courage.common.storage;

import java.io.File;
import java.io.Serializable;

/**
 * <pre>
 * kr.pe.courage.cmm.storage
 * StorageFileVo.java
 * </pre>
 * 
 * @Author : chseok82
 * @Date : 2013. 2. 4.
 * @Version : 1.0
 */
public class StorageFile implements Serializable {
	private static final long serialVersionUID = -8465724579857840772L;

	private File uploadFile;
	private String atchFileId; // 파일아이디
	private String fileSn; // 파일 이련번호
	private String fileNm; // 원문 파일명
	private String uniqFileNm; // 저장 파일명
	private String filePath; // 저장 경로
	private String deleteFlag; // 삭제 여부 (1 : 삭제)
	private String fileLocation; // 파일 위치
	private String fileSize; // 파일사이즈
	private String fileCn; // 파일설명
	private String fileExtsn; // 파일 확장자명
	private String useAt; // 사용구분
	
	public StorageFile() {
		super();
	}
	
	public StorageFile(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getAtchFileId() {
		return atchFileId;
	}

	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	public String getFileSn() {
		return fileSn;
	}

	public void setFileSn(String fileSn) {
		this.fileSn = fileSn;
	}

	public String getFileNm() {
		return fileNm;
	}

	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}

	public String getUniqFileNm() {
		return uniqFileNm;
	}

	public void setUniqFileNm(String uniqFileNm) {
		this.uniqFileNm = uniqFileNm;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileCn() {
		return fileCn;
	}

	public void setFileCn(String fileCn) {
		this.fileCn = fileCn;
	}

	public String getFileExtsn() {
		return fileExtsn;
	}

	public void setFileExtsn(String fileExtsn) {
		this.fileExtsn = fileExtsn;
	}

	public String getUseAt() {
		return useAt;
	}

	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}
}
