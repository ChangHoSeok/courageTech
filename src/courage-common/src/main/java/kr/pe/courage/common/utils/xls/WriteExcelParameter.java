/**========================================================
 *@FileName       : WriteExcelParameter.java
 *Open Issues     : N/A
 *
 *@LastModifyDate : 2009. 04. 14.
 *@LastModifier   : 석창호 
 *@LastVersion    : 1.0
 *Change History
 *    2009. 04. 14.    석창호    1.0    최초생성 
 =========================================================*/
package kr.pe.courage.common.utils.xls;

import java.util.ArrayList;
import java.util.Hashtable;

import jxl.write.WritableCellFormat;

@Deprecated
public class WriteExcelParameter {
	private ArrayList<Hashtable<String, Object>> rowData;                  // 실제 데이터
    private String[] header;                    // 데이터 타이틀
    private String title;                       // 전체 타이틀
    private WritableCellFormat titleFormat;     //타이틀 포멧
    private WritableCellFormat headerFormat;    //헤더 포멧
    private WritableCellFormat rowFormat;       //데이터 포멧
    private String filePath;                    //생성 파일 경로
    private String fileNm;						//생성 파일명
    private String sheetNm;                     //sheet 이름 
    
	public String getFileNm() {
		return fileNm;
	}
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String[] getHeader() {
		return header;
	}
	public void setHeader(String[] header) {
		this.header = header;
	}
	public WritableCellFormat getHeaderFormat() {
		return headerFormat;
	}
	public void setHeaderFormat(WritableCellFormat headerFormat) {
		this.headerFormat = headerFormat;
	}
	public ArrayList<Hashtable<String, Object>> getRowData() {
		return rowData;
	}
	public void setRowData(ArrayList<Hashtable<String, Object>> rowData) {
		this.rowData = rowData;
	}
	public WritableCellFormat getRowFormat() {
		return rowFormat;
	}
	public void setRowFormat(WritableCellFormat rowFormat) {
		this.rowFormat = rowFormat;
	}
	public String getSheetNm() {
		return sheetNm;
	}
	public void setSheetNm(String sheetNm) {
		this.sheetNm = sheetNm;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public WritableCellFormat getTitleFormat() {
		return titleFormat;
	}
	public void setTitleFormat(WritableCellFormat titleFormat) {
		this.titleFormat = titleFormat;
	}
}
