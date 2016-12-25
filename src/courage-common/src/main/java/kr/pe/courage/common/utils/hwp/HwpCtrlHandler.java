
package kr.pe.courage.common.utils.hwp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import kr.pe.courage.common.utils.file.FileUtil;

import org.apache.commons.codec.binary.Base64;
/**
 * <pre>
 * kr.pe.courage.cmm.hwp
 * HwpCtrlHandler.java
 * </pre>
 * 
 * @Author : Chang Ho Seok
 * @Date : 2013. 2. 12.
 * @Version : 1.0
 */
public class HwpCtrlHandler {
	/**
	 * <pre>
	 * 1. 처리내용 : 아래한글 Control에서 읽은 바이너리 정보를 파일로 생성
	 * </pre>
	 * 
	 * @Author	: Chang Ho Seok
	 * @Date	: 2013. 2. 12.
	 * @Method Name : createHwpBinaryToFile
	 * @param filePath
	 * @param hwpBinary
	 * @return
	 */
	public static boolean createHwpBinaryToFile(String filePath, String hwpBinary) {
		return createHwpBinaryToFile(new File(filePath), hwpBinary);
	}

	/**
	 * <pre>
	 * 1. 처리내용 : 아래한글 Control에서 읽은 바이너리 정보를 파일로 생성
	 * </pre>
	 * 
	 * @Author	: Chang Ho Seok
	 * @Date	: 2013. 2. 12.
	 * @Method Name : createHwpBinaryToFile
	 * @param filePath
	 * @param binary
	 * @return
	 */
	public static boolean createHwpBinaryToFile(File filePath, String binary) {
		byte[] binaryBytes = Base64.decodeBase64(binary);
		boolean resultFlag = false;
		
		FileOutputStream fos = null;
		try {
			FileUtil.folderCreate(filePath.getPath());
			
			fos = new FileOutputStream(filePath);
			fos.write(binaryBytes);
			fos.close();
			resultFlag = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null)
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		return resultFlag;
	}

	/**
	 * <pre>
	 * 1. 처리내용 : 저장된 파일을 바이너리로 반환한다.
	 * </pre>
	 * 
	 * @Author	: Chang Ho Seok
	 * @Date	: 2013. 2. 12.
	 * @Method Name : createHwpFileToBinary
	 * @param filePath
	 * @return
	 */
	public static String createHwpFileToBinary(String filePath) {
		return createHwpFileToBinary(new File(filePath));
	}

	/**
	 * <pre>
	 * 1. 처리내용 : 저장된 파일을 바이너리로 반환한다.
	 * </pre>
	 * 
	 * @Author	: Chang Ho Seok
	 * @Date	: 2013. 2. 12.
	 * @Method Name : createHwpFileToBinary
	 * @param filePath
	 * @return
	 */
	public static String createHwpFileToBinary(File filePath) {
		String encryptBinary = null;
		FileInputStream fis = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {
			fis = new FileInputStream(filePath);
			int read = 0;
			byte[] b = new byte[1024];
			while ((read = fis.read(b)) > 0) {
				baos.write(b, 0, read);
			}
			
			encryptBinary = Base64.encodeBase64String(baos.toByteArray());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null)
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			try {
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return encryptBinary;
	}
}
