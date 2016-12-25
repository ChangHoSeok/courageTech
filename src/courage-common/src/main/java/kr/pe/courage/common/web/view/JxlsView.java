
package kr.pe.courage.common.web.view;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.pe.courage.common.code.CommonCodeMap;
import kr.pe.courage.common.hiercode.HierCodeMap;
import kr.pe.courage.common.storage.Storage;
import kr.pe.courage.common.utils.DateUtil;
import kr.pe.courage.common.utils.JxlsFormat;
import kr.pe.courage.common.vo.JxlsParam;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 * <pre>
 * kr.pe.courage.common.web.view
 * JxlsView.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 2. 1.
 * @Version	: 
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : , 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class JxlsView extends AbstractExcelView {
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	public static final String VIEW_NAME = "jxlsView";

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		this.setUtilFunc(model);

		XLSTransformer transformer = new XLSTransformer();
		JxlsParam jxlsParam = (JxlsParam) model.get("jxlsParam");

		String templateFileName = Storage.getInstance().getJxlsPath() + File.separator + jxlsParam.getTemplateFileNm() + "." + jxlsParam.getExt();
		String outputFilePath = Storage.getInstance().getTempPath();
		String tmpName = jxlsParam.getOutputFileNm() + "_" + System.currentTimeMillis() + "." + jxlsParam.getExt();
		String outputFileName = jxlsParam.getOutputFileNm() + "_" + DateUtil.getTodayYMD() + "." + jxlsParam.getExt();

		File folder = new File(outputFilePath);

		if (!folder.exists()) {
			folder.mkdirs();
		}

		File file = new File(outputFilePath + File.separator + tmpName);

		if (jxlsParam.isMultiSe()) {
			InputStream inputStream = new BufferedInputStream(new FileInputStream(templateFileName));

			Workbook workbook2 = transformer.transformMultipleSheetsList(inputStream, (List) model.get(jxlsParam.getListNm()),
					jxlsParam.getSheetNames(), jxlsParam.getListNm(), new HashMap(), 0);

			OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
			workbook2.write(outputStream);
		} else {
			transformer.transformXLS(templateFileName, model, outputFilePath + File.separator + tmpName);
		}

		String fileNM = "";
		String fileNotFoundMsg = "The file does not exist";

		ServletOutputStream outputStream = null;
		FileInputStream fis = null;

		try {
			int filesize = (int) file.length();

			try {
				if (!file.exists() || !file.canRead()) {
					PrintWriter out = response.getWriter();
					logger.info("## " + fileNotFoundMsg);
					out.println("<script>alert('" + fileNotFoundMsg + "');history.back();</script>");
					return;
				}
			} catch (Exception e) {
				PrintWriter out = response.getWriter();
				logger.error(e);
				logger.info("## " + fileNotFoundMsg);
				out.println("<script>alert('" + fileNotFoundMsg + "');history.back();</script>");
				return;
			}

			if (StringUtils.equals(jxlsParam.getExt(), JxlsParam.EXCEL_EXT_XLS)) {
				response.setContentType("application/vnd.ms-excel");
			} else if (StringUtils.equals(jxlsParam.getExt(), JxlsParam.EXCEL_EXT_XLSX)) {
				response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			}

			fileNM = new String(outputFileName.getBytes("euc-kr"), "ISO-8859-1");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileNM + "\"");
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setContentLength((filesize));
			response.setHeader("cache-control", "no-cache");

			fis = new FileInputStream(file);
			outputStream = response.getOutputStream();
			FileCopyUtils.copy(fis, outputStream);
		} catch (Exception e) {
			PrintWriter out = response.getWriter();
			response.setHeader("Content-Type", "text/html; charset=UTF-8");
			out.println("<script>alert('" + fileNotFoundMsg + "');history.back();</script>");

			logger.error(e);
			logger.info("## " + fileNotFoundMsg);
		} finally {
			if (fis != null) {
				fis.close();
			}

			if (outputStream != null) {
				outputStream.flush();
			}
		}

	}

	/**
	 * <pre>
	 * 1. 개요 : jxls view 내장함수 설정
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 2. 1.
	 * @Method Name : setUtilFunc
	 * @param model
	 */
	private void setUtilFunc(Map<String, Object> model) {

		// date -> string format.. ex) DB SYSDATEIME(CUBRID) -> 2014-12-23  , ${dateFormat.format(value)}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		model.put("dateFormat", dateFormat);

		// string -> string format.. ex) 20141223 -> 2014-12-23 , ${formatString.dateFormat(value)}
		JxlsFormat jxlsFormatString = new JxlsFormat("####-##-##", '-');
		model.put("formatString", jxlsFormatString);

		// number or string -> string format.. ex) 1234567 -> 1,234,567 , ${formatNum.commaFormat(value)}
		JxlsFormat jxlFormatNum = new JxlsFormat("#,###");
		model.put("formatNum", jxlFormatNum);

		// 계층코드 value ex) ${hierCodeMap.getHierCodeName(code, value)}
		model.put("hierCodeMap", HierCodeMap.getInstance());

		// 공통코드 value ex) ${commomCodeMap.getCodeName(code, value)}
		model.put("commomCodeMap", CommonCodeMap.getInstance());
	}
}
