
package kr.pe.courage.common.utils.xls;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * <pre>
 * kr.pe.courage.common.xls
 * ExportExcelVo.java
 * </pre>
 *
 * @Author	: chseok82
 * @Date	: 2012. 8. 20.
 * @Version	: 1.0
 */
@Deprecated
public class ExportExcelVo {
	private String fileNm;
	private String title;
	private String sheetNm;
	private String[] header;
	private String filePath;
	private ArrayList<Hashtable<String, Object>> dataList;

	
	
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

	public ArrayList<Hashtable<String, Object>> getDataList() {
		return dataList;
	}

	public void setDataList(ArrayList<Hashtable<String, Object>> dataList) {
		this.dataList = dataList;
	}
}
