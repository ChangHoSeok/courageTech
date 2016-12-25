package kr.pe.courage.common.web.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.pe.courage.common.storage.StorageFile;
import kr.pe.courage.common.storage.StorageUtils;
import kr.pe.courage.common.utils.Util;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

/**
 * 클래스설명 : 
 * @version : 2011. 12. 26.
 * @author : ChangHo Seok
 * @분류 : 
 * eims / package kdic.eims.common.file;
 */

public class FileDownView extends AbstractView {
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	public static final String FILE_PARAM = "storageFile";
	public static final String FILE_PARAM_UNIQ_FILE_NAME = "uniqFileNm";
	public static final String FILE_PARAM_FILE_PATH = "filePath";
	public static final String FILE_PARAM_FILE_LOCATION = "fileLocation";
	
	@Override
	protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fileNM = "";
    	String uniqFileNM = "";
    	String filePath = "";
    	String fileNotFoundMsg = "The file does not exist";
    	String filePathErrorMsg = "File information is incorrect";
    	
    	StorageFile storageFile = (StorageFile)model.get(FILE_PARAM);
    	ServletOutputStream outputStream = null;
    	FileInputStream fis = null;
    	
    	if (storageFile == null) {
    		storageFile = new StorageFile();
        	storageFile.setUniqFileNm(request.getParameter(FILE_PARAM_UNIQ_FILE_NAME));
        	storageFile.setFilePath(request.getParameter(FILE_PARAM_FILE_PATH));
        	storageFile.setFileLocation(request.getParameter(FILE_PARAM_FILE_LOCATION));
        }
    	
    	fileNM = storageFile.getFileNm();
    	uniqFileNM = storageFile.getUniqFileNm();
    	filePath = storageFile.getFilePath() + File.separator;
        
        logger.info("##fileDownView [fileName] = " + fileNM);
        logger.info("##fileDownView [uniqFileName] = " + uniqFileNM);
        logger.info("##fileDownView [filePath] = " + filePath);
        
        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        
        if (!Util.isNotBlank(fileNM) || !Util.isNotBlank(uniqFileNM) || !Util.isNotBlank(filePath)) {
        	PrintWriter out = response.getWriter();
            out.println("<script>alert('" + fileNotFoundMsg + "');history.back();</script>");
            return;
        }
        
        //웹 취약점을 노린 파일다운로드 방지
        if(fileNM.lastIndexOf("../") > 0) {
        	PrintWriter out = response.getWriter();
            out.println("<script>alert('" + filePathErrorMsg + "');history.back();</script>");
            return;
        }
        
        try {
        	File tempFile = StorageUtils.get(storageFile);
            
            int filesize = (int) tempFile.length();
            logger.info("## fullFilePath = " + tempFile.getPath());
            logger.info("## fileExists = " + tempFile.exists());
            
            try {
            	logger.info("tempFile.canRead() = " + tempFile.canRead());
                if (!tempFile.exists() || !tempFile.canRead()) {
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

            String fileExt = FilenameUtils.getExtension(fileNM);
            
            if (fileExt.trim().equalsIgnoreCase("txt")){
            	response.setContentType("text/plain" );
            } else if (fileExt.trim().equalsIgnoreCase("doc")){
            	response.setContentType("application/msword" );
            } else if (fileExt.trim().equalsIgnoreCase("docx")){
            	response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document" );
            } else if (fileExt.trim().equalsIgnoreCase("xls")){
            	response.setContentType("application/vnd.ms-excel" );
            } else if (fileExt.trim().equalsIgnoreCase("xlsx")){
            	response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" );
            } else if (fileExt.trim().equalsIgnoreCase("pdf")){
            	response.setContentType("application/pdf" );
            } else if (fileExt.trim().equalsIgnoreCase("ppt")){
            	response.setContentType("application/vnd.ms-powerpoint");
            } else if (fileExt.trim().equalsIgnoreCase("pptx")){
            	response.setContentType("application/vnd.openxmlformats-officedocument.presentationml.presentation");
            } else if (fileExt.trim().equalsIgnoreCase("hwp")){
            	response.setContentType("application/hwp");
            } else{
            	response.setContentType("application/octet-stream" );
            }
            
            fileNM = new String(fileNM.getBytes("euc-kr"),"ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment; filename=\""+fileNM+"\"");
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setContentLength((filesize));
            response.setHeader("cache-control", "no-cache");
            
            fis = new FileInputStream(tempFile);
            outputStream = response.getOutputStream();
            FileCopyUtils.copy(fis, outputStream);
        } catch (Exception e) {
        	PrintWriter out = response.getWriter();
        	response.setHeader("Content-Type", "text/html; charset=UTF-8");
        	out.println("<script>alert('" + fileNotFoundMsg + "');history.back();</script>");
        	
        	logger.error(e);
        	logger.info("## " + fileNotFoundMsg);
        } finally {
        	if(fis != null) {
            	fis.close();
            }
        	
        	if (outputStream != null) {
        		outputStream.flush();
        	}
        }
	}
}
