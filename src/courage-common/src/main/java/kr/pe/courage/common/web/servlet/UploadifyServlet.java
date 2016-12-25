
package kr.pe.courage.common.web.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.pe.courage.common.prop.PropertiesMap;
import kr.pe.courage.common.storage.Storage;
import kr.pe.courage.common.utils.Util;
import kr.pe.courage.common.web.utils.WebUtils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

/**
 * <pre>
 * kr.pe.courage.cmm.web
 * UploadifyServlet.java
 * </pre>
 * 
 * @Author : chseok82
 * @Date : 2013. 2. 4.
 * @Version : 1.0
 */
public class UploadifyServlet extends HttpServlet {
	private static final String EXT_LIST_AT_BLACK = "black";
	private static final String EXT_LIST_AT_WHITE = "white";
	private static final long serialVersionUID = 7753031024709748085L;
	
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
		logger.info("## fileUploadServlet Logging");

		ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
		sfu.setHeaderEncoding("utf-8");
		List<FileItem> fileItems = sfu.parseRequest(request);

		if (fileItems == null) {
			logger.info("## file not found");
		} else {
			Storage storage = Storage.getInstance();
			Iterator<FileItem> iter = fileItems.iterator();
			FileItem uploadFile = null;
			File saveFile = null;
			
			// 임시 디렉토리 생성
			String tempPath = storage.getTempPath(WebUtils.getSessionId(request));
			File tempFilePath = new File(tempPath);
			
			if (!tempFilePath.isDirectory()) {
				tempFilePath.mkdirs();
			}

			iter = fileItems.iterator();
			while (iter.hasNext()) {
				uploadFile = (FileItem) iter.next();

				if (!uploadFile.isFormField()) {
					String fileName = uploadFile.getName();

					if (!extCheck(fileName)) {
						response.setStatus(405);
						logger.info("## 등록할 수 없는 파일입니다. = " + fileName);
					} else {
						saveFile = new File(tempFilePath.getPath() + File.separator + fileName);
						uploadFile.write(saveFile);

						logger.info("## fileName = " + fileName);
						logger.info("## filePath = " + saveFile.getPath());
					}
				}
			}
		}

		response.getWriter().println();
	}

	private boolean extCheck(String fileName) {
		boolean returnFlag = true;
		String fileExt = FilenameUtils.getExtension(fileName.toUpperCase());
		String extCheckAt = PropertiesMap.getInstance().getValue("system.storage.extListAt");
		String fileExtension = "";
		
		if (EXT_LIST_AT_BLACK.equals(extCheckAt)) {
			returnFlag = true;
			fileExtension = PropertiesMap.getInstance().getValue("system.storage.blackList");
		} else if (EXT_LIST_AT_WHITE.equals(extCheckAt)) {
			returnFlag = false;
			fileExtension = PropertiesMap.getInstance().getValue("system.storage.whiteList");
		}
		
		if (Util.isNotBlank(fileExtension)) {
			String [] checkExts = fileExtension.split(",");
			
			for (String notExt : checkExts) {
				if (fileExt.toUpperCase().equals(notExt.toUpperCase())) {
					returnFlag = !returnFlag;
					break;
				}
			}
		} else {
			logger.info("## 파일업로드 제한 설정이 존재하지 않습니다.");
		}
		
		return returnFlag;
	}
}
