package kr.pe.courage.common.utils;

public class XSSTagChecker {
	public static boolean isXSSTag(String str) {
		boolean resultFlag = false;
		
		if (str.contains("<script")) {
			resultFlag = true;
		} else if (str.contains("<iframe")) {
			resultFlag = true;
		}
		
		return resultFlag;
	}
}
