
package kr.pe.courage.common.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

public class FileUtil extends org.apache.commons.io.FileUtils {

	private static final Logger logger = Logger.getLogger(FileUtil.class);

	/**
	 * <p>
	 * 폴더 존재 유무를 리턴한다.
	 * <p/>
	 * 
	 * @param strPathName
	 *            - 폴더 path
	 * 
	 * @return isExist - 폴더 존재 유(true)무(false)를 리턴
	 */
	public static boolean fileExist(String strPathName) throws Exception {
		File f = null;
		try {
			f = new File(strPathName);
			if (!f.exists())
				return false;
		} catch (Exception e) {
			logger.error("fileExist Exception : " + e.getMessage());
			throw e;
		}
		return true;
	}

	/**
	 * <pre>
	 * 1. 처리내용 : 폴더 생성
	 * </pre>
	 * 
	 * @Author : Chang Ho Seok
	 * @Date : 2013. 2. 12.
	 * @Method Name : folderCreate
	 * @param path
	 * @return true : 생성성공, false : 생성실패
	 * @throws Exception
	 */
	public static boolean folderCreate(String path) throws Exception {
		boolean resultFlag = false;
		String folderPath = FilenameUtils.getFullPath(path);

		File dirCheck = new File(folderPath);
		if (!dirCheck.isDirectory()) {
			dirCheck.mkdirs();
		}
		resultFlag = true;

		return resultFlag;
	}

	/**
	 * <p>
	 * 대상 경로의 모든파일을 담은 Iterator를 리턴한다.
	 * </p>
	 * 
	 * @param path
	 *            : 대상경로
	 * @return : 파일객체를 담은 Iterator
	 */
	public static Iterator<File> iteratorDir(String path) {
		File filePath = new File(path);
		List<File> fileList = new ArrayList<File>();

		if (filePath.exists()) {
			File[] files = filePath.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					fileList.add(files[i]);
				}
			}
		}
		return fileList.iterator();
	}

	/**
	 * <p>
	 * file copy
	 * <p/>
	 * 
	 * @param from
	 *            - from file
	 * @param to
	 *            - to file
	 * 
	 */
	public static void fileCopy(String from, String to) throws IOException {
		FileInputStream fis = null;
		FileOutputStream fos = null;

		try {
			fis = new FileInputStream(from);
			fos = new FileOutputStream(to);

			final int BUFSIZ = 1024;
			byte buf[] = new byte[BUFSIZ];
			int len = 0;

			while ((len = fis.read(buf)) > 0)
				fos.write(buf, 0, len);
		} catch (Exception e) {
			// log.error("FileUtil, fileCopy, Exception error.", e);
		} finally {
			if (fis != null)
				fis.close();
			if (fos != null)
				fos.close();
		}
	}

	/**
	 * <p>
	 * 해당경로의 하위 파일 및 폴더까지 삭제한다.
	 * <p/>
	 * 
	 * @param filePath
	 *            : 삭제할 파일
	 * @return isDelete : 삭제여부
	 **/
	public static boolean deleteFile(String filePath) {
		File file = null;
		File delFile = null;
		boolean isDelete = false;
		
		try {
			file = new File(filePath);
			if(!file.exists()) 
				return false;
			
			if(file.isFile()){
				file.delete();
				return true;
			}
			// 파일체크
			String files[] = file.list();

			for (int i = 0; i < files.length; i++) {
				delFile = new File(filePath, files[i]);
				delFile.delete();
			}

			if (file.exists()) {
				if (file.delete()) {
					isDelete = true;
				} else {
					logger.info("File deletion failed : " + filePath);
				}
			} else {
				logger.info("Not exist File : " + filePath);
			}
		} catch (Exception e) {
			logger.error("File Delete Exception : " + filePath, e);
		}

		return isDelete;
	}
	
	/**
	 * <pre>
	 * 1. 처리내용 : 파일의 확장자를 리턴한다.
	 * </pre>
	 * 
	 * @Author : jong jin, Park
	 * @Date : 2014. 3. 14.
	 * @Method Name : getFileExtention
	 * @param fileName 대상파일명
	 * @return 파일 확장자
	 * @throws Exception
	 */
	public static String getFileExtention(String fileName) {
		return fileName.substring( fileName.lastIndexOf(".")+1, fileName.length() );
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 파일 형식 확인
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 10. 23.
	 * @Method Name : getContentType
	 * @param fileName
	 * @return
	 */
	public static String getContentType(String fileName) {
		return URLConnection.guessContentTypeFromName(fileName);
	}
}
