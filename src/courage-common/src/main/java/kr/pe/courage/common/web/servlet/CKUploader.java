
package kr.pe.courage.common.web.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.pe.courage.common.prop.PropertiesMap;
import kr.pe.courage.common.storage.Storage;
import kr.pe.courage.common.utils.Util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

/**
 * <pre>
 * kr.pe.courage.common.web.servlet
 * CKUploader.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2013. 2. 4.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2013. 2. 4., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * 2. 수정일 : 2016. 10. 21., 수정자 : ChangHo Seok, 수정내용 : 클래스명 변경
 * </pre>
 */
public class CKUploader extends HttpServlet {
	private static final long serialVersionUID = 8139463774668211394L;

	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			performTask(req, resp);
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
	}

	@SuppressWarnings({ "unchecked" })
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("## CKImageUploader Logging");
		logger.info("====================Request Info====================");
		logger.info("request.getRequestURI() : " + request.getRequestURI());
		logger.info("request.getRequestURL() : " + request.getRequestURL());
		logger.info("request.getMethod() : " + request.getMethod());
		logger.info("request.getRemoteAddr() : " + request.getRemoteAddr());
		
		String ckEditorFuncNum = Util.nvl(request.getParameter("CKEditorFuncNum"));
		String saveUrl = request.getContextPath();
		String resultMsg = "전송이 완료되었습니다.";
		String tempPath = PropertiesMap.getInstance().getValue("ckeditor.upload.path");
		
		boolean errorFlag = false;
		
		Enumeration enums = request.getParameterNames();
		
		while (enums.hasMoreElements()) {
			String paramName = (String) enums.nextElement();
			String[] parameters = request.getParameterValues(paramName);

			for (int i = 0; i < parameters.length; i++) {
				logger.info("parameter ::: [" + paramName + "] " + parameters[i]);
			}
		}
		logger.info("====================Request Info====================");

		ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
		sfu.setHeaderEncoding("utf-8");
		List<FileItem> fileItems = sfu.parseRequest(request);

		if (fileItems == null) {
			logger.info("## file not found");
			resultMsg = "업로드 파일이 존재하지 않습니다.";
			errorFlag = true;
		} else {
			if (Util.isNotBlank(tempPath)) {
				Iterator<FileItem> iter = fileItems.iterator();
				FileItem actual = null;
				File fichero = null;
				
				iter = fileItems.iterator();
				while (iter.hasNext()) {
					actual = (FileItem) iter.next();
					
					if (!actual.isFormField()) {
						String fileName = actual.getName();
						
						if (!extCheck(fileName)) {
							saveUrl = "";
							resultMsg = "등록할 수 없는 파일형식 입니다.";
							logger.info("## 등록할 수 없는 파일형식 입니다." + fileName);
							errorFlag = true;
						} else {
							saveUrl += tempPath + File.separator + request.getSession().getId();
							tempPath = request.getSession().getServletContext().getRealPath(tempPath) + File.separator + request.getSession().getId();
							
							File tempFilePath = new File(tempPath);
							if (!tempFilePath.isDirectory()) {
								tempFilePath.mkdirs();
							}
							
							fichero = new File(tempFilePath.getPath() + File.separator + Storage.getInstance().getFileSyncId(fileName));
							
							saveUrl += File.separator + fichero.getName();
							saveUrl = FilenameUtils.separatorsToUnix(saveUrl);
							
							actual.write(fichero);
							
							logger.info("## fileName = " + fileName);
							logger.info("## filePath = " + fichero.getPath());
						}
					}
				}
			} else {
				saveUrl = "";
				resultMsg = "업로드 대상결로가 설정되지 않았습니다 [ckeditor.upload.path]";
				logger.warn("## 업로드 대상결로가 설정되지 않았습니다 [ckeditor.upload.path]");
				errorFlag = true;
			}
		}
		
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		
		if (errorFlag) {
			response.getWriter().print("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction('" + ckEditorFuncNum + "', '" + saveUrl + "','" + resultMsg + "');</script>");
		} else {
			response.getWriter().print("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction('" + ckEditorFuncNum + "', '" + saveUrl + "');</script>");
		}
	}

	private boolean extCheck(String fileName) {
		boolean returnFlag = false;
		String fileExtension = PropertiesMap.getInstance().getValue("ckeditor.image.extension");
		
		if (Util.isNotBlank(fileExtension)) {
			String fileExts[] = fileExtension.split(",");
			String uploadFileExt = FilenameUtils.getExtension(fileName);
			
			for (String fileExt : fileExts) {
				if (uploadFileExt.toUpperCase().equals(fileExt.toUpperCase())) {
					returnFlag = true;
					break;
				}
			}
		} else {
			logger.warn("## 업로드 가능 파일이 설정되지 않았습니다. [ckeditor.image.extension]");
		}
		
		return returnFlag;
	}
}
