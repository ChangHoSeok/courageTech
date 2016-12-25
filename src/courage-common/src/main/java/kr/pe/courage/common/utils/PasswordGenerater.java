
package kr.pe.courage.common.utils;

import java.util.Random;

/**
 * 패스워크 생성관련 클래스
 * 
 * <pre>
 * kr.pe.courage.common.utils
 * PasswordGenerater.java
 * </pre>
 * 
 * @Author : Chang Ho Seok
 * @Date : 2013. 11. 4.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * ================================================================
 * 		수정일		|		수정자			|			수정내용
 * 2013-11-04		석창호					최초등록
 * ================================================================
 * </pre>
 */
public class PasswordGenerater {
	/**
	 * <pre>
	 * 1. 개요 : 랜덤 패스워드 생성 (임시 패스워드 발급)
	 * </pre>
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 5. 14.
	 * @Method Name : generateRandomPasswd
	 * @param passwordLength
	 * @return passwordLength 의 자릿수 만큼 생성된 임시 패스워드
	 */
	public static String generateRandomPasswd(int passwordLength) {
		Random random = new Random();
		StringBuilder sb = new StringBuilder();

		while (true) {
			int randomInt = random.nextInt(122) + 1;

			if (randomInt >= 48 && randomInt <= 57) { // 숫자
				sb.append((char) randomInt);
			} else if (randomInt >= 65 && randomInt <= 90) { // 대문자
				sb.append((char) randomInt);
			} else if (randomInt >= 97 && randomInt <= 122) { // 소문자
				sb.append((char) randomInt);
			} else {
				continue;
			}

			if (sb.length() >= passwordLength) {
				break;
			}
		}

		return sb.toString();
	}
	
	/**
	 * <pre>
	 * 1. 개요 : MD5 암호화 임시 패스워드 생성
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 5. 14.
	 * @Method Name : generateRandomPasswdMD5
	 * @return MD5 암호화 임시 패스워드
	 */
	public static String generateRandomPasswdMD5() {
		return EncryString.encMD5(generateRandomPasswd(10));
	}
	
	/**
	 * <pre>
	 * 1. 개요 : SHA256 암호화 임시 패스워드 생성
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 5. 14.
	 * @Method Name : generateRandomPasswdSHA256
	 * @return SHA256 암호화 임시 패스워드
	 */
	public static String generateRandomPasswdSHA256() {
		return EncryString.encSHA256(generateRandomPasswd(10));
	}
}
