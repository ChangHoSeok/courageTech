package kr.pe.courage.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 * kr.pe.courage.common.utils
 * ValidationUtil.java
 * </pre>
 *
 * @Author	: Seok Chang Ho
 * @Date	: 2013. 11. 20.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * ================================================================
 * 		수정일		|		수정자			|			수정내용
 *  2013.11.20    석창호                                           최초등록
 * ================================================================
 * </pre>
 */
public class ValidationUtil {
	
	public static boolean isRequired(String str) {
		if(str == null || str.trim().length() == 0) {
			return false;
		}
		
		return true;
	}
	
	public static boolean isExists(String str, String[] values, boolean nullCheck) {
		if(nullCheck == false || isRequired(str)) {
			for (String value : values) {
				if (str.equals(value)) {
					return true;
				}
			}
			
			return false;
		} else {
			return false;
		}
	}
	
	public static boolean min(String str, int min, boolean nullCheck) {
		if(nullCheck == false && !isRequired(str)) {
			return true;
		} else if(nullCheck == false || isRequired(str)) {
			try {
				int intVal = Integer.parseInt(str);

				if (intVal >= min) {
					return true;
				} else {
					return false;
				}
			} catch (NumberFormatException e) {
				return false;
			}
			
		} else {
			return false;
		}
	}
	
	public static boolean max(String str, int max, boolean nullCheck) {
		if (nullCheck == false && !isRequired(str)) {
			return true;
		} else if(nullCheck == false || isRequired(str)) {
			try {
				int intVal = Integer.parseInt(str);

				if (intVal <= max) {
					return true;
				} else {
					return false;
				}
			} catch (NumberFormatException e) {
				return false;
			}
			
		} else {
			return false;
		}
	}
	
	public static boolean minSize(String str, int min, boolean nullCheck) {
		if(nullCheck == false || isRequired(str)) {
			
			if(str == null) {
				return true;
			}
			
			int len = str.trim().getBytes().length;
			
			if(len >= min) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public static boolean maxSize(String str, int max, boolean nullCheck) {
		if(nullCheck == false || isRequired(str)) {
			
			if(str == null) {
				return true;
			}
			
			int len = str.trim().getBytes().length;
			
			if(len <= max) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public static boolean sameSize(String str, int same, boolean nullCheck) {
		if(nullCheck == false || isRequired(str)) {
			
			if(str == null) {
				return true;
			}
			
			int len = str.trim().getBytes().length;
			
			if(len == same) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public static boolean isNumber(String str, boolean nullCheck) {
		if(nullCheck == false || isRequired(str)) {
			
			if(str == null) {
				return true;
			}
			
			try {
				Integer.parseInt(str.trim());
			} catch (NumberFormatException nfe) {
				return false;
			}
		} else {
			return false;
		}
		
		return true;
	}
	
	public static boolean isYear(String str, boolean nullCheck) {
		if(nullCheck == false || isRequired(str)) {
			
			if(str == null) {
				return true;
			}
			
			if(isNumber(str, false)) {
				int year = Integer.parseInt(str.trim());
				
				if(year < 1500 && year > 2999) {
					return false;
				}
			}
		} else {
			return false;
		}
		
		return true;
	}
	
	public static boolean isEmail(String str, boolean nullCheck) {
		String checkPattern = "^([A-Za-z0-9_\\-\\.'])+@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,6})$";
		
		if(nullCheck == false || isRequired(str)) {
			return isRegex(str, checkPattern, nullCheck);
		} else {
			return false;
		}
	}
	
	public static boolean isOnlyKorea(String str, boolean nullCheck) {
		String checkPattern = "^[ㄱ-힣]*$";
		
		if(nullCheck == false || isRequired(str)) {
			return isRegex(str, checkPattern, nullCheck);
		} else {
			return false;
		}
	}
	
	public static boolean isOnlyLetter(String str, boolean nullCheck) {
		String checkPattern = "^[a-zA-Z]*$";
		
		if(nullCheck == false || isRequired(str)) {
			return isRegex(str, checkPattern, nullCheck);
		} else {
			return false;
		}
	}
	
	public static boolean isOnlyLetterNumber(String str, boolean nullCheck) {
		String checkPattern = "^[0-9a-zA-Z]*$";
		
		if(nullCheck == false || isRequired(str)) {
			return isRegex(str, checkPattern, nullCheck);
		} else {
			return false;
		}
	}
	
	public static boolean isOnlyLetterNumberSp(String str, boolean nullCheck) {
		String checkPattern = "^[0-9a-zA-Z\\ \'!@#$%^*+=-_,&()-{}|<>\"~]*$";
		
		if(nullCheck == false || isRequired(str)) {
			return isRegex(str, checkPattern, nullCheck);
		} else {
			return false;
		}
	}
	
	public static boolean isPassword(String str, boolean nullCheck) {
		String checkPattern = "^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-]).*$";
		
		if(nullCheck == false || isRequired(str)) {
			return isRegex(str, checkPattern, nullCheck);
		} else {
			return false;
		}
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 정규표현식 검증
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 10. 11.
	 * @Method Name : isRegex
	 * @param str
	 * @param regex
	 * @return
	 */
	public static boolean isRegex(String str, String regex) {
		return isRegex(str, regex, false);
	}
	
	public static boolean isRegex(String str, String regex, boolean nullCheck) {
		if(nullCheck == false || isRequired(str)) {
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(str);

			return matcher.matches();
		} else {
			return false;
		}
	}
}