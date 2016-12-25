package kr.pe.courage.common.utils;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.security.MessageDigest;

/**
 * <pre>
 * kr.pe.courage.common.utils
 * FileHash.java
 * </pre>
 *
 * @Author : ChangHo Seok
 * @Date : 2015. 8. 3.
 * @Version : 1.0
 * @see
 * 
 * 		<pre>
 * << 개정이력 >>
 * 1. 수정일 2015. 8. 3.: , 수정자 : ChangHo Seok, 수정내용 : 최초등록
 *      </pre>
 */
public class FileHash {
	private final static String ALG_SHA256 = "SHA-256";
	private final static String ALG_SHA512 = "SHA-512";
	
	/**
	 * <pre>
	 * 1. 개요 : SHA256 해쉬 알고리즘을 이용한 파일 해쉬값 추출
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2015. 8. 3.
	 * @Method Name : etractFileHashSHA256
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	public static String etractFileHashSHA256(String filename) throws Exception  {
		return extractFileHash(filename, FileHash.ALG_SHA256);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : SHA512 해쉬 알고리즘을 이용한 파일 해쉬값 추출
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2015. 8. 3.
	 * @Method Name : etractFileHashSHA512
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	public static String etractFileHashSHA512(String filename) throws Exception  {
		return extractFileHash(filename, FileHash.ALG_SHA512);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 파일 해쉬값 추출
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2015. 8. 3.
	 * @Method Name : extractFileHash
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	private static String extractFileHash(String filename, String algorism) throws Exception {

		String SHA = "";
		int buff = 16384;

		try {
			RandomAccessFile file = new RandomAccessFile(filename, "r");
			MessageDigest hashSum = MessageDigest.getInstance(algorism);

			byte[] buffer = new byte[buff];
			byte[] partialHash = null;

			long read = 0;

			long offset = file.length();
			int unitsize;
			while (read < offset) {
				unitsize = (int) (((offset - read) >= buff) ? buff : (offset - read));
				file.read(buffer, 0, unitsize);

				hashSum.update(buffer, 0, unitsize);

				read += unitsize;
			}

			file.close();
			partialHash = new byte[hashSum.getDigestLength()];
			partialHash = hashSum.digest();

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < partialHash.length; i++) {
				sb.append(Integer.toString((partialHash[i] & 0xff) + 0x100, 16).substring(1));
			}
			SHA = sb.toString();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return SHA;
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(etractFileHashSHA512("D:\\WebtoB_4.1_Admin_Guide.pdf"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
