package kr.pe.courage.tech.uss.org.dept.service;

import kr.pe.courage.common.core.CommonDefaultVO;

/**
 * <pre>
 * kr.pe.courage.tech.uss.org.dept.service
 * DeptBatchVO.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 10. 14.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 10. 14., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class DeptBatchVO extends CommonDefaultVO {
	private static final long serialVersionUID = 4691906636660178928L;
	
	private String uploadAtchFile;
	private String deleteFlag;
	
	public String getUploadAtchFile() {
		return uploadAtchFile;
	}
	public void setUploadAtchFile(String uploadAtchFile) {
		this.uploadAtchFile = uploadAtchFile;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
