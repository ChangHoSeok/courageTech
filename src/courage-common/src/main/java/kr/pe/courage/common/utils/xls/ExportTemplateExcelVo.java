package kr.pe.courage.common.utils.xls;

import java.util.ArrayList;

/**
 * <pre>
 * kr.pe.courage.common.xls
 * ExportTemplateExcelVo.java
 * </pre>
 * 
 * @Author : jbm0409
 * @Date : 2013. 01. 08.
 * @Version : 1.0
 */
public class ExportTemplateExcelVo {
	private String fileNm;
	private String title;
	private String sheetNm;
	private String[] header;
	private String filePath;
	private ArrayList dataList;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileNm() {
		return fileNm;
	}

	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSheetNm() {
		return sheetNm;
	}

	public void setSheetNm(String sheetNm) {
		this.sheetNm = sheetNm;
	}

	public String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		this.header = header;
	}

	public ArrayList getDataList() {
		return dataList;
	}

	public void setDataList(ArrayList dataList) {
		this.dataList = dataList;
	}
}
