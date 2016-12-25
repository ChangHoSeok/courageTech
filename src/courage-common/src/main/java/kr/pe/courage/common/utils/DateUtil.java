
package kr.pe.courage.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * Date Utility Class This is used to convert Strings to Dates and Timestamps
 * 
 * <p>
 * <a href="DateUtil.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Modified by
 *         <a href="mailto:dan@getrolling.com">Dan Kibler </a> to correct time
 *         pattern. Minutes should be mm not MM (MM is month).
 * @version $Revision: 1.1 $ $Date: 2011/01/17 01:48:55 $
 */
public class DateUtil {
	// ~ Static fields/initializers
	// =============================================
	private static Logger logger = Logger.getLogger(DateUtil.class);
	private static String defaultDatePattern = null;
	private static String timePattern = "HH:mm";

	// ~ Methods
	// ================================================================

	/**
	 * Return default datePattern (MM/dd/yyyy)
	 * 
	 * @return a string representing the date pattern on the UI
	 */
	public static String getDatePattern() {
		// Locale locale = LocaleContextHolder.getLocale();
		// try {
		// defaultDatePattern = ResourceBundle.getBundle(Constants.BUNDLE_KEY,
		// locale)
		// .getString("date.format");
		// } catch (MissingResourceException mse) {
		// defaultDatePattern = "MM/dd/yyyy";
		// }

		defaultDatePattern = "yyyyMMdd";
		return defaultDatePattern;
	}

	public static String getDateTimePattern() {
		return DateUtil.getDatePattern() + " HH:mm:ss.S";
	}

	/**
	 * This method attempts to convert an Oracle-formatted date in the form
	 * dd-MMM-yyyy to mm/dd/yyyy.
	 * 
	 * @param aDate
	 *            date from database as a string
	 * @return formatted string for the ui
	 */
	public static final String getDate(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(getDatePattern());
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * This method generates a string representation of a date/time in the
	 * format you specify on input
	 * 
	 * @param aMask
	 *            the date pattern the string is in
	 * @param strDate
	 *            a string representation of a date
	 * @return a converted Date object
	 * @see java.text.SimpleDateFormat
	 * @throws ParseException
	 */
	public static final Date convertStringToDate(String aMask, String strDate) throws ParseException {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);

		if (logger.isDebugEnabled()) {
			logger.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
		}

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			// log.error("ParseException: " + pe);
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return (date);
	}

	/**
	 * This method returns the current date time in the format: MM/dd/yyyy HH:MM
	 * a
	 * 
	 * @param theTime
	 *            the current time
	 * @return the current date/time
	 */
	public static String getTimeNow(Date theTime) {
		return getDateTime(timePattern, theTime);
	}

	/**
	 * This method returns the current date in the format: MM/dd/yyyy
	 * 
	 * @return the current date
	 * @throws ParseException
	 */
	public static Calendar getToday() throws ParseException {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

		// This seems like quite a hack (date -> string -> date),
		// but it works ;-)
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));

		return cal;
	}

	/**
	 * This method generates a string representation of a date's date/time in
	 * the format you specify on input
	 * 
	 * @param aMask
	 *            the date pattern the string is in
	 * @param aDate
	 *            a date object
	 * @return a formatted string representation of the date
	 * 
	 * @see java.text.SimpleDateFormat
	 */
	public static final String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			logger.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * This method generates a string representation of a date based on the
	 * System Property 'dateFormat' in the format you specify on input
	 * 
	 * @param aDate
	 *            A date to convert
	 * @return a string representation of the date
	 */
	public static final String convertDateToString(Date aDate) {
		return getDateTime(getDatePattern(), aDate);
	}

	/**
	 * This method converts a String to a date using the datePattern
	 * 
	 * @param strDate
	 *            the date to convert (in format MM/dd/yyyy)
	 * @return a date object
	 * 
	 * @throws ParseException
	 */
	public static Date convertStringToDate(String strDate) throws ParseException {
		Date aDate = null;

		try {
			if (logger.isDebugEnabled()) {
				logger.debug("converting date with pattern: " + getDatePattern());
			}

			aDate = convertStringToDate(getDatePattern(), strDate);
		} catch (ParseException pe) {
			logger.error("Could not convert '" + strDate + "' to a date, throwing exception");
			pe.printStackTrace();
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());

		}

		return aDate;
	}

	/**
	 * 현재 년월일(YYYYMMDD)을 리턴
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static String getTodayYMD() throws ParseException {
		Date date = new Date();
		SimpleDateFormat formatter;
		String pattern = "yyyyMMdd";
		formatter = new SimpleDateFormat(pattern, new Locale("ko", "KOREA"));

		return formatter.format(date);
	}

	/**
	 * 현재 연도(YYYY)를 리턴
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static String getYear() throws ParseException {
		Date date = new Date();
		SimpleDateFormat formatter;
		String pattern = "yyyy";
		formatter = new SimpleDateFormat(pattern, new Locale("ko", "KOREA"));

		return formatter.format(date);
	}

	/**
	 * 현재 월(MM)를 리턴
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static String getMonth() throws ParseException {
		Date date = new Date();
		SimpleDateFormat formatter;
		String pattern = "MM";
		formatter = new SimpleDateFormat(pattern, new Locale("ko", "KOREA"));

		return formatter.format(date);
	}
	
	/**
	 * 현재 일(dd)를 리턴
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static String getDay() throws ParseException {
		Date date = new Date();
		SimpleDateFormat formatter;
		String pattern = "dd";
		formatter = new SimpleDateFormat(pattern, new Locale("ko", "KOREA"));

		return formatter.format(date);
	}
	
	/**
	 * 현재 시간(HH)를 리턴
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static String getHour() throws ParseException {
		Date date = new Date();
		SimpleDateFormat formatter;
		String pattern = "HH";
		formatter = new SimpleDateFormat(pattern, new Locale("ko", "KOREA"));

		return formatter.format(date);
	}

	/**
	 * 입력한 yyyy, mm의 시작날짜를 구함
	 * 
	 * @return YYYYMMDD
	 * @throws ParseException
	 */
	public static String getMonthStartDay(String yyyy, String mm) throws ParseException {

		if (yyyy == null || yyyy.trim().equals("")) {
			yyyy = getYear();
		}

		if (mm == null || mm.trim().equals("")) {
			mm = getMonth();
		}

		int year = Integer.parseInt(yyyy);
		int month = Integer.parseInt(mm);
		int day = 0;

		String startDay = "";

		Calendar cal = new GregorianCalendar();
		cal.set(year, month, 0);

		year = cal.get(cal.YEAR);
		month = cal.get(cal.MONTH) + 1;
		day = cal.getActualMinimum(cal.DAY_OF_MONTH);

		startDay = year + String.format("%02d", month) + String.format("%02d", day);

		return startDay;
	}

	/**
	 * 입력한 yyyy, mm의 끝날짜를 구함
	 * 
	 * @return YYYYMMDD
	 * @throws ParseException
	 */
	public static String getMonthEndDay(String yyyy, String mm) throws ParseException {
		if (yyyy == null || yyyy.trim().equals("")) {
			yyyy = getYear();
		}

		if (mm == null || mm.trim().equals("")) {
			mm = getMonth();
		}

		int year = Integer.parseInt(yyyy);
		int month = Integer.parseInt(mm);
		int day = 0;

		String endDay = "";

		Calendar cal = new GregorianCalendar();
		cal.set(year, month, 0);

		year = cal.get(cal.YEAR);
		month = cal.get(cal.MONTH) + 1;
		day = cal.getActualMaximum(cal.DAY_OF_MONTH);

		endDay = year + String.format("%02d", month) + String.format("%02d", day);

		return endDay;
	}

	/**
	 * 입력한 날짜(YYYYMMDD)의 요일을 반환한다
	 * 
	 * @return 월, 화, 수, 목, 금, 토, 일
	 * @throws ParseException
	 */
	public static final String getDayOfWeek(String date) throws ParseException {
		String dayOfWeek = "";
		Calendar cal = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		cal.setTime(sdf.parse(date));

		switch (cal.get(cal.DAY_OF_WEEK)) {
		case 1:
			dayOfWeek = "일";
			break;
		case 2:
			dayOfWeek = "월";
			break;
		case 3:
			dayOfWeek = "화";
			break;
		case 4:
			dayOfWeek = "수";
			break;
		case 5:
			dayOfWeek = "목";
			break;
		case 6:
			dayOfWeek = "금";
			break;
		case 7:
			dayOfWeek = "토";
			break;
		default:
			break;
		}

		return dayOfWeek;
	}

	/**
	 * 입력한 날짜가 오늘로 부터 몇일 지났는지 일수를 반환한다(yyyyMMdd 입력)
	 * 
	 * @return int (일수)
	 */
	public static final int getTodayDiff(String diffDate) {
		int diffCnt = 0;
		int yyyy = 0;
		int mm = 0;
		int dd = 0;
		Date today = new Date();

		yyyy = Integer.parseInt(diffDate.substring(0, 4));
		mm = Integer.parseInt(diffDate.substring(4, 6));
		dd = Integer.parseInt(diffDate.substring(6, 8));

		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.MONTH, 1);
		cal2.set(yyyy, mm, dd);

		if (cal2.before(cal)) {
			while (!cal2.after(cal)) {
				++diffCnt;
				cal2.add(Calendar.DATE, 1);
			}
		} else {
			while (!cal2.before(cal)) {
				--diffCnt;
				cal2.add(Calendar.DATE, -1);
			}
		}

		return diffCnt;
	}

	/**
	 * Method ID : getMonthDiff Method 설명 : 입력한 시작날짜와 종료날짜의 월 간격을 int로 반환한다.
	 * 최초작성일 : 2011. 6. 23. 작성자 : ChangHo Seok 변경이력 :
	 * 
	 * @param startMonth
	 * @param endMonth
	 * @return int
	 */
	public static final int getMonthDiff(String startMonth, String endMonth) {
		int diffCnt = 0;
		int yyyy = 0;
		int mm = 0;
		int dd = 0;

		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();

		yyyy = Integer.parseInt(startMonth.substring(0, 4));
		mm = Integer.parseInt(startMonth.substring(4, 6));
		dd = 1;
		cal.set(yyyy, mm, dd);

		yyyy = Integer.parseInt(endMonth.substring(0, 4));
		mm = Integer.parseInt(endMonth.substring(4, 6));
		dd = 1;
		cal2.set(yyyy, mm, dd);

		while (cal.before(cal2)) {
			++diffCnt;
			cal.add(Calendar.MONTH, 1);
		}

		return diffCnt;
	}

	/**
	 * <pre>
	 * 1. 개요 : 입력한 날짜로 부터 calcDate만큼 더하거나 뺀 날짜를 반환한다.
	 * 2. 처리내용 : 입력한 날짜로 부터 calcDate만큼 더하거나 뺀 날짜를 반환한다.
	 * </pre>
	 * 
	 * @Author : chseok82
	 * @Date : 2012. 8. 23.
	 * @Method Name : getCalcDate
	 * @param yyyyMMdd
	 * @param minusDate
	 * @return yyyyMMdd 20120102 calcDate -1 = 20120101, 20120102 calcDate 1 =
	 *         20120103
	 */
	public static final String getCalcDate(String yyyyMMdd, int calcDate) {
		String resultStr = "";
		Calendar cal = Calendar.getInstance();
		int yyyy = 0;
		int mm = 0;
		int dd = 0;

		if (isRegex(yyyyMMdd, "^\\d{4}\\d{2}\\d{2}$")) {
			yyyy = Integer.parseInt(yyyyMMdd.substring(0, 4));
			mm = Integer.parseInt(yyyyMMdd.substring(4, 6));
			dd = Integer.parseInt(yyyyMMdd.substring(6, 8));
			cal.set(yyyy, mm - 1, dd);

			cal.add(Calendar.DAY_OF_MONTH, calcDate);

			yyyy = cal.get(cal.YEAR);
			mm = cal.get(cal.MONTH) + 1;
			dd = cal.get(cal.DAY_OF_MONTH);

			resultStr = yyyy + String.format("%02d", mm) + String.format("%02d", dd);
		}

		return resultStr;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 현재 Timestamp 문자열 반환
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 4. 3.
	 * @Method Name : getTimestamp
	 * @return 현재 Timestamp 문자열
	 */
	public static final String getTimestamp() {
		return new Timestamp(System.currentTimeMillis()).toString();
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 현재 Timestamp 문자열 반환 (oracle 형식)
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 4. 3.
	 * @Method Name : getTimestamp
	 * @return 현재 Timestamp 문자열
	 */
	public static final String getTimestampToOracle() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(System.currentTimeMillis());
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 입력한 날짜 주의 시작 일을 조회 (주의 시작은 일요일)
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2014. 5. 6.
	 * @Method Name : firstDayOfWeek
	 * @param calendar
	 * @return
	 */
	public static final Calendar firstDayOfWeek(Calendar calendar) {
		Calendar cal = (Calendar) calendar.clone();
		int day = cal.get(Calendar.DAY_OF_YEAR);
		
		while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
			cal.set(Calendar.DAY_OF_YEAR, --day);
		}
		
		return cal;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 입력한 날짜의 주의 끝일을 조회 (주의 끝일은 토요일)
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2014. 5. 6.
	 * @Method Name : lastDayOfWeek
	 * @param calendar
	 * @return
	 */
	public static final Calendar lastDayOfWeek(Calendar calendar) {
		Calendar cal = (Calendar) calendar.clone();
		int day = cal.get(Calendar.DAY_OF_YEAR);
		
		while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
			cal.set(Calendar.DAY_OF_YEAR, ++day);
		}
		
		return cal;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : Calendar 형식을 String 형식으로 변환(YYYYMMDD)
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2014. 5. 6.
	 * @Method Name : calendatToString
	 * @param calendar
	 * @return
	 */
	public static final String calendatToString(Calendar calendar) {
		int year = calendar.get(calendar.YEAR);
		int month = calendar.get(calendar.MONTH) + 1;
		int day = calendar.get(calendar.DAY_OF_MONTH);
		
		return year + String.format("%02d", month) + String.format("%02d", day);
	}

	/**
	 * <pre>
	 * 1. 개요 : 정규표현식 확인
	 * 2. 처리내용 : 정규표현식 확인
	 * </pre>
	 * 
	 * @Author : chseok82
	 * @Date : 2012. 8. 23.
	 * @Method Name : isRegex
	 * @param str
	 * @param regex
	 * @return
	 */
	private static final boolean isRegex(String str, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);

		return matcher.matches();
	}
}
