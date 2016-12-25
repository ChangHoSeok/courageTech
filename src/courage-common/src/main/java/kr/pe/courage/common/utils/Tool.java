
package kr.pe.courage.common.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

@Deprecated
public class Tool {

	static Logger logger = Logger.getLogger(Tool.class);

	/**
	 * GMT를 기준으로 9시간 후를 off set으로 설정
	 */
	final static int rawOffset = 9 * 60 * 60 * 1000;
	final static Locale currentLocale = Locale.KOREA;
	final static NumberFormat numberFormatter = NumberFormat.getNumberInstance(currentLocale);

	/**
	 * 1일의 밀리세컨드.
	 */
	final static long DAY_SECOND = 1000 * 60 * 60 * 24;

	/**
	 * 텍스트 데이트를 Calendar 객체로 변환한다.
	 * 
	 * @param dateStr
	 *            데이트 스트링
	 * @param format
	 *            포맷 예 - 'yyyyMMdd'
	 * @return Calendar
	 * @exception java.text.ParseException
	 * @author 소태섭
	 * @since 2001-11-16
	 */
	public static Calendar toCalendar(String dateStr, String format) throws java.text.ParseException {
		Date date = toDate(dateStr, format);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	/**
	 * 텍스트 데이트를 Date 객체로 변환한다.
	 * 
	 * @param dateStr
	 *            데이트 스트링
	 * @param format
	 *            포맷 예 - 'yyyyMMdd'
	 * @return Date
	 * @exception java.text.ParseException
	 * @author 소태섭
	 * @since 2001-11-16
	 */
	public static Date toDate(String dateStr, String format) throws java.text.ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.KOREA);
		Date date = sdf.parse(dateStr);
		return date;
	}

	/**
	 * from date 부터 offset 만큼 더한 일을 리턴한다.
	 * 
	 * @param from
	 *            date. 'YYYYMMDD' 형식이다.
	 * @param offset
	 *            .
	 * @return to date. 'YYYYMMDD' 형식이다.
	 * @exception java.text.ParseException
	 */
	public static String addDate(String fromDt, int offset) throws java.text.ParseException {
		return addDate(fromDt, Calendar.DATE, offset);
	}

	/**
	 * from date에서 field 의 offset 만큼 떨어진 to date를 리턴한다.
	 * 
	 * @param from
	 *            date. 'YYYYMMDD' 형식이다.
	 * @param field
	 *            .
	 * @param offset
	 *            .
	 * @return to date. 'YYYYMMDD' 형식이다.
	 * @exception java.text.ParseException
	 */
	public static String addDate(String fromDt, int field, int offset) throws java.text.ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
		Date fromDate = sdf.parse(fromDt);
		Calendar cal = Calendar.getInstance();
		cal.setTime(fromDate);
		cal.add(field, offset);

		Date toDate = cal.getTime();

		return sdf.format(toDate);
	}

	/**
	 * 일 수를 구한다.
	 * 
	 * @param From
	 *            일. 형식은 'YYYYMMDD'이다.
	 * @param To
	 *            일. 형식은 'YYYYMMDD'이다.
	 * @return 일 수.
	 * @exception java.text.ParseException
	 */
	public static int getDays(String fromDt, String toDt) throws java.text.ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);

		Date d1 = sdf.parse(fromDt);
		Date d2 = sdf.parse(toDt);

		long from = d1.getTime();
		long to = d2.getTime();

		long times = to - from;

		int days = (int) (times / DAY_SECOND);

		return days;
	}

	/**
	 * format을 받아 알맞은 형태로 년월일시를 반환한다.
	 * 
	 * @param format
	 *            데이트 포맷
	 * @param field
	 * @param offset
	 * @return String 데이트 포맷에 따른 년월일시
	 */
	public static String getFormatDate(String format, int field, int offset) {
		SimpleTimeZone stz = new SimpleTimeZone(rawOffset, "KST");
		Calendar rightNow = Calendar.getInstance(stz);

		if (offset != 0) {
			rightNow.add(field, offset);
		}

		Date rightDate = rightNow.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(stz);

		return sdf.format(rightDate);
	}

	/**
	 * 현재일에서 offset 만큼 더하거나 뺀 년월일을 yyyyMMdd 포맷으로 리턴한다
	 * 
	 * @param offset
	 *            offset
	 * @return String yyyyMMdd 포맷의 년월일
	 */
	public static String getDate2(int offset) {
		return getFormatDate("yyyyMMdd", Calendar.DATE, offset);
	}

	public static String getMonth2(int offset) {
		return getFormatDate("yyyyMMdd", Calendar.MONTH, offset);
	}

	public static String getYear2(int offset) {
		return getFormatDate("yyyyMMdd", Calendar.YEAR, offset);
	}

	/**
	 * 현재 년월일을 yyyyMMdd 포맷으로 리턴한다
	 * 
	 * @return String yyyyMMdd 포맷의 년월일
	 */
	public static String getDate() {
		return getFormatDate("yyyyMMdd", 0, 0);
	}

	/**
	 * 현재 년월일을 yyyy-MM-dd 포맷으로 리턴한다
	 * 
	 * @return String yyyy-MM-dd 포맷의 년월일
	 */
	public static String getDate2() {
		return getFormatDate("yyyy-MM-dd", 0, 0);
	}

	/**
	 * 현재 년월일을 yyyy-MM-dd HH:mm 포맷으로 리턴한다
	 * 
	 * @return String yyyy-MM-dd HH:mm 포맷의 년월일
	 */
	public static String getDate3() {
		return getFormatDate("yyyy-MM-dd HH:mm", 0, 0);
	}

	/**
	 * 현재 년월일을 yyyyMMddHHmm 포맷으로 리턴한다
	 * 
	 * @return String yyyyMMddHHmm 포맷의 년월일
	 */
	public static String getDate4() {
		return getFormatDate("yyyyMMddHHmm", 0, 0);
	}

	/**
	 * 현재 년을 yyyy 포맷으로 리턴한다
	 * 
	 * @return String yyyy 포맷의 년
	 */
	public static String getYear() {
		return getFormatDate("yyyy", 0, 0);
	}

	/**
	 * 현재 월을 MM 포맷으로 리턴한다
	 * 
	 * @return String MM
	 */
	public static String getMonth() {
		return getFormatDate("MM", 0, 0);
	}

	/**
	 * 현재 월을 M 포맷으로 리턴한다
	 * 
	 * @return String M
	 */
	public static String getMonth2() {
		return getFormatDate("M", 0, 0);
	}

	/**
	 * 현재 년월을 yyyyMM 포맷으로 리턴한다
	 * 
	 * @return String yyyyMM 포맷의 년월
	 */
	public static String getYyyyMm() {
		return getFormatDate("yyyyMM", 0, 0);
	}

	/**
	 * 현재 시각을 HH:mm 포맷으로 리턴한다
	 * 
	 * @return String HH:mm 포맷의 시각
	 */
	public static String getTime() {
		return getFormatDate("HH:mm", 0, 0);
	}

	/**
	 * 숫자에 3자리마다 comma를 붙인다.
	 * 
	 * @param number
	 * @return String
	 */
	@Deprecated
	public static String comma(String number) {
		return numberFormatter.format(Long.parseLong(number));
	}

	/**
	 * 숫자에 3자리마다 comma를 붙인다.
	 * 
	 * @param number
	 * @return String
	 */
	@Deprecated
	public static String comma(int number) {
		return numberFormatter.format(number);
	}

	/**
	 * 숫자에 3자리마다 comma를 붙인다.
	 * 
	 * @param number
	 * @return String
	 */
	@Deprecated
	public static String comma(long number) {
		return numberFormatter.format(number);
	}

	/**
	 * 숫자에 3자리마다 comma를 붙인다.
	 * 
	 * @param number
	 * @return String
	 */
	@Deprecated
	public static String comma(double number) {
		DecimalFormat doubleFormatter = new DecimalFormat("###,##0.####");
		return doubleFormatter.format(number);
	}

	/**
	 * 숫자에 3자리마다 comma를 붙인다.
	 * 
	 * @param number
	 * @return String
	 */
	@Deprecated
	public static String comma(Object number) {
		DecimalFormat doubleFormatter = new DecimalFormat("###,##0.####");
		return doubleFormatter.format(number);
	}

	/**
	 * 숫자에 3자리마다 comma를 붙인다. 소수 2자리까지 표현한다.
	 * 
	 * @param number
	 * @return String
	 */
	@Deprecated
	public static String comma2(double number) {
		DecimalFormat doubleFormatter = new DecimalFormat("###,##0.00");
		return doubleFormatter.format(number);
	}

	/**
	 * 숫자에 3자리마다 comma를 붙인다. 소수 3자리까지 표현한다.
	 * 
	 * @param number
	 * @return String
	 */
	@Deprecated
	public static String comma3(double number) {
		DecimalFormat doubleFormatter = new DecimalFormat("###,##0.000");
		return doubleFormatter.format(number);
	}

	/**
	 * 숫자에 3자리마다 comma를 붙인다. 소수 4자리까지 표현한다.
	 * 
	 * @param number
	 * @return String
	 */
	@Deprecated
	public static String comma4(double number) {
		DecimalFormat doubleFormatter = new DecimalFormat("###,##0.0000");
		return doubleFormatter.format(number);
	}

	/**
	 * <pre>
	 * 금액문자열을 금액표시타입으로 변환한다. <BR>
	 * (예) 12345678 --> 12,345,678            <BR>
	 * </pre>
	 * 
	 * @param moneyString
	 *            금액문자열.
	 * @param delimeter
	 *            금액표시구분자.
	 * @return 변경된 금액 문자열.
	 */
	@Deprecated
	public static String makeMoneyType(String moneyString, String delimeter) {
		if (moneyString == null || moneyString.length() == 0)
			return "0";

		DecimalFormat df = new DecimalFormat();
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();

		dfs.setGroupingSeparator(delimeter.charAt(0));
		df.setGroupingSize(3);
		df.setDecimalFormatSymbols(dfs);

		return (df.format(Long.parseLong(moneyString))).toString();
	}

	/**
	 * number를 좌측에서 0으로 채움.
	 * 
	 * @param number
	 * @return String
	 */
	@Deprecated
	public static String lpad(int number) {
		DecimalFormat intFormatter = new DecimalFormat("000");
		return intFormatter.format(number);
	}

	/**
	 * String이 null인 경우 리턴할 문자로 바꾸어 준다.
	 * 
	 * @param string
	 * @param null 인 경우 리턴할 문자
	 * @return String
	 */
	@Deprecated
	public static String nvl(String str, String str2) {
		if (str == null) {
			return str2;
		}

		return str;
	}

	@Deprecated
	public static String nvl(String str) {
		if (str == null || str.equals("null")) {
			return "";
		}

		return str.trim();
	}

	@Deprecated
	public static String checkTextArea(String str, String str2) {
		StringBuffer tempStr = new StringBuffer("");
		char tempChar;
		if (str == null) {
			return str2;
		}
		for (int index = 0; index < str.length(); index++) {
			tempChar = str.charAt(index);
			if (tempChar == '\n')
				tempStr.append("<BR>");
			else
				tempStr.append(tempChar);
		}

		return tempStr.toString();
	}

	@Deprecated
	public static String checkTextArea(String str) {
		StringBuffer tempStr = new StringBuffer("");
		char tempChar;
		if (str == null) {
			return new String("&nbsp;");
		}
		for (int index = 0; index < str.length(); index++) {
			tempChar = str.charAt(index);
			if (tempChar == '\n')
				tempStr.append("<BR>");
			else
				tempStr.append(tempChar);
		}

		return tempStr.toString();
	}

	/**
	 * 여부(Y/N)에 대해 "예", "아니오"로 바꾸어 준다.
	 * 
	 * @param string
	 * @return String
	 */
	@Deprecated
	public static String convertYn(String str) {
		if (str == null) {
			return null;
		}

		if (str.equals("Y")) {
			return "예";
		}

		return "아니오";
	}

	/**
	 * 여부(Y/N)에 대해 str1, str2로 바꾸어 준다.
	 * 
	 * @param string
	 * @return String
	 */
	@Deprecated
	public static String convertYn(String str, String str1, String str2) {
		if (str == null) {
			return null;
		}

		if (str.equals("Y") || str.equals("1")) {
			return str1;
		}

		return str2;
	}

	@Deprecated
	public static String trimDate(String date) {

		if (date == null) {
			return "";
		}

		date = date.trim();

		if (date.length() == 8) {
			return date;
		}

		return date.substring(0, 4) + date.substring(5, 7) + date.substring(8);
	}

	/**
	 * 20000702 -- 2000/07/02
	 * 
	 * @param date
	 *            String
	 * @param division
	 *            String
	 * @return String
	 */
	@Deprecated
	public static String convertDate(String date, String division) {

		if (date == null) {
			return "";
		}

		date = date.trim();

		if (date.length() != 8) {
			return date;
		}

		return date.substring(0, 4) + division + date.substring(4, 6) + division + date.substring(6);
	}

	/**
	 * 20000702 -- 2000-07-02
	 * 
	 * @param date
	 *            String
	 * @param division
	 *            String
	 * @return String
	 */
	@Deprecated
	public static String convertDate(String date) {
		return convertDate(date, "-");
	}

	/**
	 * 20000702 -- 2000/07/02
	 * 
	 * @param date
	 *            String
	 * @return String
	 */
	@Deprecated
	public static String convertDate2(String date) {
		return convertDate(date, "/");
	}

	/**
	 * 20000702120135 -- 2000-07-02 12:01:35
	 * 
	 * @param date
	 *            String
	 * @return String
	 */
	@Deprecated
	public static String convertDate3(String date) {

		if (date == null) {
			return "";
		}

		date = date.trim();

		if (date.length() != 14) {
			return date;
		}

		return date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8) + " " + date.substring(8, 10) + ":"
				+ date.substring(10, 12) + ":" + date.substring(12, 14);
	}

	/**
	 * 시스템 날짜에 해당하는 분기를 리턴.
	 * 
	 * 
	 * @return String
	 */
	@Deprecated
	public static String getQuarter() {
		String yyyyMm = getYyyyMm();
		String mm = yyyyMm.substring(4, 6);
		String quarter = new String();
		if (mm.equals("01") || mm.equals("02") || mm.equals("03")) {
			quarter = "1";
		} else if (mm.equals("04") || mm.equals("05") || mm.equals("06")) {
			quarter = "2";
		} else if (mm.equals("07") || mm.equals("08") || mm.equals("09")) {
			quarter = "3";
		} else if (mm.equals("10") || mm.equals("11") || mm.equals("12")) {
			quarter = "4";
		}
		return quarter;
	}

	/**
	 * 두자리 숫자에 해당하는 분기를 리턴
	 * 
	 * 
	 * @return String
	 */
	@Deprecated
	public static String getQuarter(String mm) {

		String quarter = null;
		if (mm.equals("01") || mm.equals("02") || mm.equals("03")) {
			quarter = "1";
		} else if (mm.equals("04") || mm.equals("05") || mm.equals("06")) {
			quarter = "2";
		} else if (mm.equals("07") || mm.equals("08") || mm.equals("09")) {
			quarter = "3";
		} else if (mm.equals("10") || mm.equals("11") || mm.equals("12")) {
			quarter = "4";
		}
		return quarter;
	}

	@Deprecated
	public static String getHyphen(String str, int locate) {

		if (str == null) {
			return "";
		}

		str = str.trim();

		if (str.length() < locate)
			return str;
		return str.substring(0, locate) + "-" + str.substring(locate);
	}

	@Deprecated
	public static String setMaxLength(String str, int maxLength) {
		if (str == null) {
			return "";
		}
		if (str.length() <= maxLength)
			return str;
		if (maxLength < 3)
			return str.substring(0, 2);

		StringBuffer returnString = new StringBuffer();
		str = str.trim();

		returnString.append(str.substring(0, maxLength - 1)).append("..");

		return returnString.toString();
	}

	// 긴 문장 maxLength만큼 자르기(이재문)
	public static String setMaxLength2(String str, String maxLength) {
		return setMaxLength(str, Integer.parseInt(maxLength));
	}

	// 콤마달린 숫자를 문자열로써 받아서 콤마가 제거된 문자열로 넘긴다(김주현)
	public static String deComma(String NumberWithCommaAsString) {

		StringTokenizer st = new StringTokenizer(NumberWithCommaAsString, ",");
		StringBuffer temp = new StringBuffer();
		while (st.hasMoreTokens()) {
			temp.append(st.nextToken());

		}
		NumberWithCommaAsString = temp.toString();
		return NumberWithCommaAsString;
	}

	// 시간비교해야할 때가 있다.(김주현)
	public static java.sql.Timestamp checkNull(java.sql.Timestamp time1, java.sql.Timestamp time2) {
		if (time1 == null) {
			return time2;
		}

		return time1;
	}

	/**
	 * double형 숫자에 콤마를 붙이고 double형일 경우 뒤에 유효숫자를 붙여준다. 화면에 테이블로 출력시 자리수를 맞춰주기 위해
	 * 사용.
	 * 
	 * @param number
	 *            double 숫자
	 * @param fp
	 *            int 소수점 이하 자리수
	 * @return String
	 */
	@Deprecated
	public static String makeSF(double number, int fp) {

		String tNum = numberFormatter.format(number);

		int idx = tNum.indexOf(".");
		if (idx == -1) { // 소수점이 없는 double형 숫자일 때
			tNum = tNum + ".";
			for (int i = 0; i < fp; i++) {
				tNum += "0";
			}
		} else {
			int fpGap = fp - tNum.substring(idx + 1).length();

			if (fpGap > 0) {
				for (int i = 0; i < fpGap; i++) {
					tNum += "0";
				}
			} else if (fpGap < 0) {
				for (int i = fpGap; i < 0; i++) {
					tNum = tNum.substring(0, tNum.length() - 1);

				}
			}
		}

		return tNum;
	}

	/**
	 * 해당일이 속하는 달의 마지막일자를 리턴함.
	 * 
	 * @param String
	 *            "yyyymmdd"형태일자
	 * @return String "yyyymmdd"형태일자
	 */
	public static String getLastDay(String date) {
		int iyear = Integer.parseInt(date.substring(0, 4));
		int imonth = Integer.parseInt(date.substring(4, 6));
		int lastDayOfMonth = 0;
		String rDate = null;

		if (iyear < 1 || imonth < 1 || imonth > 12) {
			return "";
		}

		switch (imonth) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			lastDayOfMonth = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			lastDayOfMonth = 30;
			break;
		case 2:
			lastDayOfMonth = (((iyear % 4 == 0) && (iyear % 100 != 0)) || (iyear % 400 == 0)) ? 29 : 28;
			break;
		}

		rDate = ((iyear < 10) ? ("000" + String.valueOf(iyear)) : ((iyear < 100) ? ("00" + String.valueOf(iyear))
				: ((iyear < 1000) ? ("0" + String.valueOf(iyear)) : String.valueOf(iyear))))
				+ ((imonth < 10) ? ("0" + String.valueOf(imonth)) : String.valueOf(imonth))
				+ ((lastDayOfMonth < 10) ? ("0" + String.valueOf(lastDayOfMonth)) : String.valueOf(lastDayOfMonth));
		return rDate;
	}

	/**
	 * @param str
	 *            절단할 문자열
	 * @param index
	 *            절단된 문자열이 저장될 배열의 인덱스(리턴배열)
	 * @param cnt
	 *            절단된 토큰이 저장될 배열의 크기
	 * @return String
	 */
	@Deprecated
	public static String returnToken(String str, int index, int cnt) {

		String[] s = new String[cnt];
		int i = 0;
		StringTokenizer st = new StringTokenizer(str, ":");
		while (st.hasMoreTokens()) {
			s[i] = st.nextToken();
			i++;
		}
		return s[index];
	}

	/**
	 * from date 부터 offset개월수 만큼 더한 일을 리턴한다.
	 * 
	 * @param from
	 *            date. 'YYYYMMDD' 형식이다.
	 * @param offset
	 *            .
	 * @return to date. 'YYYYMMDD' 형식이다.
	 * @exception java.text.ParseException
	 */
	public static String addMonths(String fromDt, int offset) throws java.text.ParseException {
		return addMonths(fromDt, Calendar.MONTH, offset);
	}

	/**
	 * from date에서 field 의 offset 만큼 떨어진 to date를 리턴한다.
	 * 
	 * @param from
	 *            date. 'YYYYMMDD' 형식이다.
	 * @param field
	 *            .
	 * @param offset
	 *            .
	 * @return to date. 'YYYYMMDD' 형식이다.
	 * @exception java.text.ParseException
	 */
	public static String addMonths(String fromDt, int field, int offset) throws java.text.ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
		Date fromDate = sdf.parse(fromDt);
		Calendar cal = Calendar.getInstance();
		cal.setTime(fromDate);
		cal.add(field, offset);

		Date toDate = cal.getTime();

		return sdf.format(toDate);
	}

	/**
	 * 문자에서 Hyphen을 없앤다.
	 * 
	 * @param str
	 * @return String
	 * @author 김지영
	 * @since 2002-01-16
	 */
	public static String deleteHyphen(String str) {
		String temp = "";

		if (str == null || str.equals("")) {
			return str;
		}

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '-') {
				continue;
			} else {
				temp += str.charAt(i);
			}
		}

		return temp;
	}

	/**
	 * 주민등록번호&사업자번호에 '-'넣기
	 * 
	 * @param str
	 * @return String
	 * @author 김지영
	 * @since 2002-01-16
	 */
	public static String setJuminHyphen(String str) {

		if (str == null || str.equals("")) {
			return str;
		}

		String temp = deleteHyphen(str);

		if (temp.length() == 13) { // 주민등록번호 6-7
			temp = temp.substring(0, 6) + "-" + temp.substring(6);
		} else if (temp.length() == 10) { // 사업자번호 3-2-5
			temp = temp.substring(0, 3) + "-" + temp.substring(3, 5) + "-" + temp.substring(5);
		}
		return temp;
	}

	/**
	 * 유니크 파일명을 구한다.
	 * 
	 * @param String
	 *            fileName
	 * @return String uniqFileName
	 */
	public static String getUniqFileName(String fileName) {
		String uniqFileName = "";

		if (fileName != null) {

			// int index = fileName.indexOf('.');
			// uniqFileName = fileName.substring(0,index) + "_" + getDate4() +
			// fileName.substring(index);

			int index = fileName.lastIndexOf('.');
			if (index > -1) {
				uniqFileName = fileName.substring(0, index) + "_" + getTimeInMillisForString() + fileName.substring(index);
			} else {
				uniqFileName = fileName + "_" + getTimeInMillisForString();
			}

		}

		return uniqFileName;
	}

	public static boolean isNull(String str) {
		if (str != null && str.length() > 0)
			return false;
		else
			return true;
	}

	public static boolean isNotNull(String str) {
		return !isNull(str);
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static String trim(String s) {
		if (s == null)
			return "";

		int st = 0;
		char[] val = s.toCharArray();
		int count = val.length;
		int len = count;

		while ((st < len) && ((val[st] <= ' ') || (val[st] == '　')))
			st++;
		while ((st < len) && ((val[len - 1] <= ' ') || (val[len - 1] == '　')))
			len--;

		return ((st > 0) || (len < count)) ? s.substring(st, len) : s;
	}

	/**
	 * 오늘날짜에서 offset만큼의 일수를 더하여 Timestamp로 리턴
	 * 
	 * @param offset
	 * @return
	 * 
	 * @author Ryus
	 */
	public static java.sql.Timestamp getAddTimestamp(int offset) {
		SimpleTimeZone stz = new SimpleTimeZone(rawOffset, "KST");
		Calendar rightNow = Calendar.getInstance(stz);

		if (offset != 0) {
			rightNow.add(Calendar.DATE, offset);
		}

		return new java.sql.Timestamp(rightNow.getTimeInMillis());
	}

	public static String getTimeInMillisForString() {
		return String.valueOf(Calendar.getInstance().getTimeInMillis());
	}

	public static Object chkNull(Object chkObject, Object newObject) {

		if (chkObject != null) {
			return chkObject;
		} else {
			return newObject;
		}
	}

}