
package kr.pe.courage.common.excellog;

import kr.pe.courage.common.core.CommonDefaultVO;

/**
 * <pre>
 * kr.pe.courage.common.excellog
 * ExcelLogVO.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2013. 11. 20.
 * @Version	: 
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2013. 11. 20., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class ExcelLogVO extends CommonDefaultVO {

	private static final long serialVersionUID = -1253029054853645517L;
	
	public final static String STATUS_SUCCESS = "0";
	public final static String STATUS_FAILED = "1";
	public final static String DELETE_FLAG_N = "0";
	public final static String DELETE_FLAG_Y = "1";

	private String sessionId; // 세션아이디
	private String deleteFlag; // 기존정보 삭제 여부
	private String excelUploadFile; // 엑셀업로드 파일
	private String excelUploadSttus; // 엑셀일괄등록 상태 (0 : 정상등록, 1 : 비정상등록)
	private String excelUploadTotalCnt; // 엑셀전체 데이터 수
	private String excelUploadSuccessCnt; // 엑셀일괄등록 성공 갯수
	private String excelUploadFailCnt; // 엑셀일괄등록 실패 갯수
	private String registerId; // 등록자 아이디

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getExcelUploadFile() {
		return excelUploadFile;
	}

	public void setExcelUploadFile(String excelUploadFile) {
		this.excelUploadFile = excelUploadFile;
	}

	public String getExcelUploadSttus() {
		return excelUploadSttus;
	}

	public void setExcelUploadSttus(String excelUploadSttus) {
		this.excelUploadSttus = excelUploadSttus;
	}

	public String getExcelUploadTotalCnt() {
		return excelUploadTotalCnt;
	}

	public void setExcelUploadTotalCnt(String excelUploadTotalCnt) {
		this.excelUploadTotalCnt = excelUploadTotalCnt;
	}

	public String getExcelUploadSuccessCnt() {
		return excelUploadSuccessCnt;
	}

	public void setExcelUploadSuccessCnt(String excelUploadSuccessCnt) {
		this.excelUploadSuccessCnt = excelUploadSuccessCnt;
	}

	public String getExcelUploadFailCnt() {
		return excelUploadFailCnt;
	}

	public void setExcelUploadFailCnt(String excelUploadFailCnt) {
		this.excelUploadFailCnt = excelUploadFailCnt;
	}

	public String getRegisterId() {
		return registerId;
	}

	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}
}
