package kr.pe.courage.common.utils.xls;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.pe.courage.common.prop.PropertiesMap;
import kr.pe.courage.common.storage.Storage;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.log4j.Logger;

/**
 * <pre>
 * kr.pe.courage.common.xls
 * ExportExcel.java
 * </pre>
 * 
 * @Author : jbm0409
 * @Date : 2014. 01. 08.
 * @Version : 1.0
 * 
 *          엑셀파일경로 추가
 */
public class ExportTemplateExcel {
	private final static Logger logger = Logger.getLogger(ExportTemplateExcel.class);

	/**
	 * <pre>
	 * 1. 개요 : 엑셀파일 생성
	 * 2. 처리내용 : 엑셀파일 생성
	 * </pre>
	 * 
	 * @Author : chseok82
	 * @Date : 2012. 8. 20.
	 * @Method Name : createExcel
	 * @param exportExcelVo
	 * @return 생성된 엑셀파일 위치(경로)
	 * @throws Exception
	 */
	public static String createExcel(ExportTemplateExcelVo exportExcelVo)
			throws Exception {
		logger.info("## ExcelExport 시작");

		String contextPath = Storage.getInstance().getContextRealPath();
		String templatePath = PropertiesMap.getInstance().getValue("system.storage.pblancPath");
		String templateFileNm = PropertiesMap.getInstance().getValue("system.storage.pblanc");
		String tempPath = Storage.getInstance().getTempPath();

		String srcPath = contextPath + templatePath + templateFileNm;
		String descPath = tempPath + File.separator + exportExcelVo.getFileNm();

		logger.info("###########################################");
		logger.info("## contextPath : " + contextPath);
		logger.info("## templatePath : " + templatePath);
		logger.info("## templateFileNm : " + templateFileNm);
		logger.info("## tempPath : " + tempPath);
		logger.info("###########################################");
		logger.info("## srcPath : " + srcPath);
		logger.info("## descPath : " + descPath);
		logger.info("###########################################");
		logger.info("## dataList size : " + exportExcelVo.getDataList().size());
		logger.info("###########################################");

		// 제목 셋팅
		Map<String, String> info = new HashMap<String, String>();
		info.put("title", exportExcelVo.getTitle());

		// 내용(리스트) 셋팅
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (exportExcelVo.getDataList().size() <= 0) {
			// 넘어온 데이터가 없을경우
		} else {
			// 넘어온 데이터가 있을경우
			Map<String, Object> item = null;

			ArrayList<Object> dataList = (ArrayList<Object>) exportExcelVo.getDataList();
			int cnt = 0;
			for (Object vo : exportExcelVo.getDataList()) {
				System.out.println("## vo " + vo.getClass());
				vo.getClass();

				Class clazz = vo.getClass();

				Field[] selfFields = clazz.getDeclaredFields();
				Field[] parentFields = clazz.getSuperclass().getDeclaredFields();
				Field[] fs = new Field[selfFields.length + parentFields.length];

				item = new HashMap<String, Object>();
				for (int x = 0; x < selfFields.length; x++) {
					fs[x] = selfFields[x];
					String id = fs[x].getName().toString();

					// logger.info("## id : " + id);
					item.put(id, id);
				}
				list.add(item);
			}
		}

		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("info", info);
		beans.put("list", list);

		File excelTempFile = new File(tempPath);
		if (!excelTempFile.isDirectory()) {
			excelTempFile.mkdirs();
		}

		XLSTransformer transformer = new XLSTransformer();
		transformer.transformXLS(srcPath, beans, descPath);

		// String srcFilePath = "";
		// String destFilePath = "";
		//
		// Map<String, Object> beans = new HashMap<String, Object>();
		//
		// File excelTempFile = new File(destFilePath);
		// if (!excelTempFile.isDirectory()) {
		// excelTempFile.mkdirs();
		// }

		// XLSTransformer transformer = new XLSTransformer();
		// transformer.transformXLS(srcFilePath, beans, destFilePath);

		/*
		 * Map<String, String> info = new HashMap<String, String>();
		 * info.put("title", exportExcelVo.getTitle()); info.put("sheetNm",
		 * exportExcelVo.getSheetNm());
		 * 
		 * 
		 * Map<String, Object> header = new HashMap<String, Object>();
		 * header.put("header", exportExcelVo.getHeader());
		 * 
		 * Map<String, Object> beans = new HashMap<String, Object>();
		 * beans.put("info", info); // beans.put("header", header);
		 * beans.put("list", exportExcelVo.getDataList());
		 * 
		 * String realPath = Storage.getInstance().getContextRealPath() +
		 * PropertiesMap.getInstance().getValue("system.storage.pblancPath");
		 * String templateNm =
		 * PropertiesMap.getInstance().getValue("system.storage.pblanc");
		 * 
		 * String tempPath =
		 * PropertiesMap.getInstance().getValue("system.storage.tempPath");
		 * String storagePath =
		 * PropertiesMap.getInstance().getValue("system.storage1.path") +
		 * tempPath;
		 * 
		 * File excelTempFile = new File(storagePath); if
		 * (!excelTempFile.isDirectory()) { excelTempFile.mkdirs(); }
		 * 
		 * XLSTransformer transformer = new XLSTransformer();
		 * transformer.transformXLS(realPath + templateNm, beans, storagePath +
		 * Storage.FILE_SEPARAT0R + exportExcelVo.getFileNm());
		 * 
		 * logger.info("## excelFile ::: " + exportExcelVo.getFileNm());
		 * logger.info("## excelTitle ::: " + exportExcelVo.getTitle());
		 * logger.info("## excelSheetNm ::: " + exportExcelVo.getSheetNm());
		 * logger.info("## excelPath ::: " + storagePath);
		 * 
		 * logger.info("## ExcelExport 종료");
		 */

		String excelFile = tempPath + File.separator;
		return excelFile;
	}
}
