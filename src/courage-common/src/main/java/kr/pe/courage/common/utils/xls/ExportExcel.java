
package kr.pe.courage.common.utils.xls;

import java.io.File;

import kr.pe.courage.common.prop.PropertiesMap;
import kr.pe.courage.common.utils.Util;

import org.apache.log4j.Logger;

/**
 * <pre>
 * kr.pe.courage.common.xls
 * ExportExcel.java
 * </pre>
 * 
 * @Author : chseok82
 * @Date : 2012. 8. 20.
 * @Version : 1.0
 * 
 * 엑셀파일경로 추가
 */
@Deprecated
public class ExportExcel {
	private final static Logger logger = Logger.getLogger(ExportExcel.class);

	/**
	 * <pre>
	 * 1. 개요 : 엑셀파일 생성
	 * 2. 처리내용 : 엑셀파일 생성
	 * </pre>
	 * 
	 * @Author	: chseok82
	 * @Date	: 2012. 8. 20.
	 * @Method Name : createExcel
	 * @param exportExcelVo
	 * @return 생성된 엑셀파일 위치(경로)
	 * @throws Exception
	 */
	public static String createExcel(ExportExcelVo exportExcelVo) throws Exception {
		logger.info("## ExcelExport 시작");
		WriteExcelParameter wep = null;
		String excelFile = exportExcelVo.getFileNm();
		String excelTitle = exportExcelVo.getTitle();
		String excelSheetNm = exportExcelVo.getSheetNm();
		String[] excelHeader = exportExcelVo.getHeader();
		String filePath = exportExcelVo.getFilePath();
		
		wep = new WriteExcelParameter();
		
		/** 2014.01.08 jbm0409
		  * 엑셀파일 다운로드시 temp에 저장
		  **/
		if (Util.nvl(exportExcelVo.getFilePath()).equals("")) {
			wep.setFilePath(PropertiesMap.getInstance().getValue("system.storage1.path") + PropertiesMap.getInstance().getValue("system.storage.tempPath") + File.separator);
		} else {
			wep.setFilePath(exportExcelVo.getFilePath());
		}
		
//		wep.setFilePath(filePath + File.separator);
		wep.setFileNm(excelFile);
		wep.setTitle(excelTitle);
		wep.setSheetNm(excelSheetNm);
		wep.setHeader(excelHeader);
		wep.setRowData(exportExcelVo.getDataList());
		
		logger.info("## excelFile ::: " + exportExcelVo.getFileNm());
		logger.info("## excelTitle ::: " + exportExcelVo.getTitle());
		logger.info("## excelSheetNm ::: " + exportExcelVo.getSheetNm());
		logger.info("## excelSheetNm ::: " + exportExcelVo.getSheetNm());
		logger.info("## excelPath ::: " + wep.getFilePath());

		WriteExcelHandler.defaultWriteExcel(wep);
		logger.info("## ExcelExport 종료");
		
//		excelFile = wep.getFilePath() + wep.getFileNm();
		excelFile = PropertiesMap.getInstance().getValue("system.storage.tempPath");

		return excelFile;
	}
}
