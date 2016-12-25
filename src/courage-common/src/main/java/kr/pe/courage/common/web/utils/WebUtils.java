package kr.pe.courage.common.web.utils;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import kr.pe.courage.common.prop.PropertiesMap;
import kr.pe.courage.common.user.UserVO;

import org.apache.commons.io.FilenameUtils;

/**
 * <pre>
 * kr.pe.courage.common.web.utils
 * WebUtils.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2013. 11. 10.
 * @Version	: 
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2013. 11. 10., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class WebUtils extends org.springframework.web.util.WebUtils {
	
	public static final String ACTION_MESSAGE = "actionMessage";
	public static final String ACTION_STATUS_PARAM = "actionStatus";
	public static final String ACTION_STATUS_FAILED = "failed";
	public static final String ACTION_STATUS_SUCCESS = "success";
	public static final String ACTION_STATUS_VALIDATOR_ERROR = "validatorError";
	
	/**
	 * <pre>
	 * 1. 개요 : url 호출 확장자 조회 /test/page1/page11.do => do
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 10.
	 * @Method Name : getUrlExtension
	 * @param request
	 * @return
	 */
	public static String getUrlExtension(HttpServletRequest request) {
		return FilenameUtils.getExtension(request.getServletPath());
	}
	
	/**
	 * <pre>
	 * 1. 개요 : url 호출 확장자를 제외한 url 조회 /test/page1/page11.do => /test/page1/page11
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 4. 11.
	 * @Method Name : getUrlNotExtension
	 * @param request
	 * @return
	 */
	public static String getUrlNotExtension(HttpServletRequest request) {
		return FilenameUtils.getFullPath(request.getServletPath()) + FilenameUtils.getBaseName(request.getServletPath());
	}
	
	/**
	 * <pre>
	 * 1. 개요 : url 호출 확장자를 제외한 url 조회 /test/page1/page11.do => /test/page1/page11
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 4. 11.
	 * @Method Name : getUrlNotExtension
	 * @param url
	 * @return
	 */
	public static String getUrlNotExtension(String url) {
		return FilenameUtils.getFullPath(url) + FilenameUtils.getBaseName(url);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : url path 조회 /test/page1/page11.do => /test/page1/
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 10.
	 * @Method Name : getUrlFullPath
	 * @param request
	 * @return
	 */
	public static String getUrlFullPath(HttpServletRequest request) {
		return FilenameUtils.getFullPath(request.getServletPath());
	}
	
	/**
	 * <pre>
	 * 1. 개요 : actionStatus 설정
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 11.
	 * @Method Name : setActionStatus
	 * @param request
	 * @param actionStatus
	 */
	public static void setActionStatus(HttpServletRequest request, String actionStatus) {
		request.setAttribute(ACTION_STATUS_PARAM, actionStatus);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : action message 등록
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 13.
	 * @Method Name : setActionMessage
	 * @param request
	 * @param message
	 */
	public static void setActionMessage(HttpServletRequest request, String message) {
		request.setAttribute(ACTION_MESSAGE, message);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : action message param 문자열 생성 (문자셋 : UTF-8)
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2015. 1. 22.
	 * @Method Name : getActionMessageParam
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public static String getActionMessageParam(String message) throws Exception {
		return getActionMessageParam(message, "UTF-8");
	}
	
	/**
	 * <pre>
	 * 1. 개요 : action message param 문자열 생성
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2015. 1. 22.
	 * @Method Name : getActionMessageParam
	 * @param message
	 * @param enc
	 * @return
	 * @throws Exception
	 */
	public static String getActionMessageParam(String message, String enc) throws Exception {
		return ACTION_MESSAGE + "=" + URLEncoder.encode(message, enc);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : Ajax 요청인지 확인 한다.
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2014. 10. 28.
	 * @Method Name : isAjaxRequest
	 * @param request
	 * @return ajax request : true
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		if (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").toUpperCase().equals("XMLHTTPREQUEST")) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 이전 URL 정보 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 11. 22.
	 * @Method Name : getRefererURL
	 * @param request
	 * @return
	 */
	public static String getRefererURL(HttpServletRequest request) {
		return request.getHeader("referer");
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 로그인 사용자 고유ID 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 1. 21.
	 * @Method Name : getUserUniqID
	 * @param request
	 * @return
	 */
	public static String getUserUniqID(HttpServletRequest request) {
		String userUniqID = "";
		
		UserVO userVO = (UserVO) request.getSession().getAttribute(PropertiesMap.getInstance().getValue("system.session.key"));
		
		if (userVO != null) {
			userUniqID = userVO.getUniqId();
		} else {
			// 익명사용자 고유ID
			userUniqID = PropertiesMap.getInstance().getValue("system.anonymous.uniqid");
		}
		
		return userUniqID;
	}
}
