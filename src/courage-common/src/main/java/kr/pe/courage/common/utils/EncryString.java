package kr.pe.courage.common.utils;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

public class EncryString {
	private static final String ENC_PREFIX_STRING = "COURAGE PASSWORD GEN LAKJSDLJ123";
	private static final String ALG_SHA512 = "SHA-512";
	private static final String ALG_SHA256 = "SHA-256";
	private static final String ALG_MD5 = "MD5";
	
	/**
	 * <pre>
	 * 1. 개요 : SHA512 문자 암호화
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2014. 12. 18.
	 * @Method Name : encSHA512
	 * @param str
	 * @return
	 */
	public static String encSHA512(String str) {
		return encProc(str, ALG_SHA512, null);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : SHA512 문자 암호화 (SALT 추가)
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 12. 2.
	 * @Method Name : encSHA512
	 * @param str
	 * @param salt
	 * @return
	 */
	public static String encSHA512(String str, String salt) {
		return encProc(str, ALG_SHA512, salt);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : SHA256 문자 암호화
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 4. 3.
	 * @Method Name : encSHA256
	 * @param str
	 * @return SHA-256 암호화 hash
	 */
	public static String encSHA256(String str) {
		return encProc(str, ALG_SHA256, null);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : SHA256 문자 암호화 (SALT 추가)
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 12. 2.
	 * @Method Name : encSHA256
	 * @param str
	 * @param salt
	 * @return
	 */
	public static String encSHA256(String str, String salt) {
		return encProc(str, ALG_SHA256, salt);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : MD5 문자 암호화
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 4. 3.
	 * @Method Name : encMD5
	 * @param str
	 * @return MD5 암호화 hash
	 */
	@Deprecated
	public static String encMD5(String str) {
		return encProc(str, ALG_MD5, null);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : MD5 문자 암호화 (SAlT 추가)
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 12. 2.
	 * @Method Name : encMD5
	 * @param str
	 * @param salt
	 * @return
	 */
	public static String encMD5(String str, String salt) {
		return encProc(str, ALG_MD5, salt);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 문자열 암호화 처리
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 4. 3.
	 * @Method Name : encProc
	 * @param str
	 * @param algorithm
	 * @return 암호화 hash
	 */
	private static String encProc(String str, String algorithm, String salt) {
		String resultStr = "";
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.update(ENC_PREFIX_STRING.getBytes());
			messageDigest.update(str.getBytes());
			
			if (salt != null && salt.length() > 0) {
				messageDigest.update(salt.getBytes());
			}
			
			byte byteData[] = messageDigest.digest();
			
			// key stretching
			for (int i = 0; i < 1004; i++) {
				messageDigest.reset();
				messageDigest.update(byteData);
				
				byteData = messageDigest.digest();
			}
			
			resultStr = Base64.encodeBase64String(byteData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultStr;
	}
	
	public static void main(String[] args) {
		String salt = PasswordGenerater.generateRandomPasswd(32);
		System.out.println(salt);
		System.out.println(encSHA512("@", salt));
	}
}
