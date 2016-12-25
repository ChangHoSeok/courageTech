package kr.pe.courage.common.web.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * <pre>
 * kr.pe.courage.common.servlet.filter
 * WebLoggingFilter.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2015. 6. 2.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2015-06-02, 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class WebLoggingFilter implements Filter {
	private final static String INIT_PARAM_OS_LOGGING = "os-logging";
	private final static String INIT_PARAM_MEM_LOGGING = "mem-logging";
	private final static String INIT_PARAM_REQUEST_LOGGING = "param-logging";
	private final static String INIT_PARAM_JAVA_PROPERTIES_LOGGING = "javaProperties-logging";
	
	private final static String LOGGING_TRUE = "true";
	private final static String LOGGING_FALSE = "false";
	
	private FilterConfig webLoggingConfig = null;
	private final Logger logger = Logger.getLogger(this.getClass());
	
	private boolean isOSLogging = false;
	private boolean isMemLogging = false;
	private boolean isJavaPropertiesLogging = false;
	private boolean isRequestLogging = true;

	public void init(FilterConfig filterConfig) throws ServletException {
		webLoggingConfig = filterConfig;
		
		if (webLoggingConfig.getInitParameter(INIT_PARAM_OS_LOGGING) != null && webLoggingConfig.getInitParameter(INIT_PARAM_OS_LOGGING).equals(LOGGING_TRUE)) {
			isOSLogging = true;
		} else if (webLoggingConfig.getInitParameter(INIT_PARAM_OS_LOGGING) != null && webLoggingConfig.getInitParameter(INIT_PARAM_OS_LOGGING).equals(LOGGING_FALSE)) {
			isOSLogging = false;
		}
		
		if (webLoggingConfig.getInitParameter(INIT_PARAM_MEM_LOGGING) != null && webLoggingConfig.getInitParameter(INIT_PARAM_MEM_LOGGING).equals(LOGGING_TRUE)) {
			isMemLogging = true;
		} else if (webLoggingConfig.getInitParameter(INIT_PARAM_MEM_LOGGING) != null && webLoggingConfig.getInitParameter(INIT_PARAM_MEM_LOGGING).equals(LOGGING_FALSE)) {
			isMemLogging = false;
		}
		
		if (webLoggingConfig.getInitParameter(INIT_PARAM_REQUEST_LOGGING) != null && webLoggingConfig.getInitParameter(INIT_PARAM_REQUEST_LOGGING).equals(LOGGING_TRUE)) {
			isRequestLogging = true;
		} else if (webLoggingConfig.getInitParameter(INIT_PARAM_REQUEST_LOGGING) != null && webLoggingConfig.getInitParameter(INIT_PARAM_REQUEST_LOGGING).equals(LOGGING_FALSE)) {
			isRequestLogging = false;
		}

		if (webLoggingConfig.getInitParameter(INIT_PARAM_JAVA_PROPERTIES_LOGGING) != null && webLoggingConfig.getInitParameter(INIT_PARAM_JAVA_PROPERTIES_LOGGING).equals(LOGGING_TRUE)) {
			isJavaPropertiesLogging = true;
		} else if (webLoggingConfig.getInitParameter(INIT_PARAM_JAVA_PROPERTIES_LOGGING) != null && webLoggingConfig.getInitParameter(INIT_PARAM_JAVA_PROPERTIES_LOGGING).equals(LOGGING_FALSE)) {
			isJavaPropertiesLogging = false;
		}
	}
	
	public void destroy() {
		webLoggingConfig = null;
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		
		logger.info("=========================== Courage Logging ===========================");
		logger.info("");
		
		if (isOSLogging) {
			logger.info("################ OS Info ################");
			logger.info("OS Name :: " + System.getProperty("os.name"));
			logger.info("OS Arch :: " + System.getProperty("os.arch"));
			logger.info("OS Version :: " + System.getProperty("os.version"));
			logger.info("################ OS Info ################");
			logger.info("");
		}
		
		if (isJavaPropertiesLogging) {
			logger.info("################ Java System Properties ################");
			logger.info("java.runtime.name :: " + System.getProperty("java.runtime.name"));
			logger.info("java.version :: " + System.getProperty("java.version"));
			logger.info("java.home :: " + System.getProperty("java.home"));
			logger.info("file.encoding :: " + System.getProperty("file.encoding"));
			logger.info("user.language :: " + System.getProperty("user.language"));
			logger.info("################ Java System Properties ################");
			logger.info("");
		}
		
		if (isMemLogging) {
			long totalMem = Runtime.getRuntime().totalMemory();
			long freeMem = Runtime.getRuntime().freeMemory();
			long activeMem = totalMem - freeMem;
			
			logger.info("################ Memory Info ################");
			logger.info("totalMemory (Heap) :: " + totalMem + " (byte)");
			logger.info("freeMemory (Heap) :: " + freeMem + " (byte)");
			logger.info("activeMemory Rate (Heap) :: " + (Math.round(((float)activeMem / (float)totalMem) * 100f)) + " %");
			logger.info("################ Memory Info ################");
			logger.info("");
		}
		
		if (isRequestLogging) {
			logger.info("################ Request Info ################");
			logger.info("request.getProtocol() :: " + request.getProtocol());
			logger.info("request.isSecure() :: " + request.isSecure());
			logger.info("request.getRequestURI() :: " + request.getRequestURI());
			logger.info("request.getRequestURL() :: " + request.getRequestURL());
			logger.info("request.getServletPath() :: " + request.getServletPath());
			logger.info("request.getContextPath() :: " + request.getContextPath());
			logger.info("request.getPathInfo() :: " + request.getPathInfo());
			logger.info("request.getMethod() :: " + request.getMethod());
			logger.info("request.getRemoteAddr() :: " + request.getRemoteAddr());
			logger.info("request.getCharacterEncoding() :: " + request.getCharacterEncoding());
			
			Enumeration enums = request.getParameterNames();
			
			while (enums.hasMoreElements()) {
				String paramName = (String) enums.nextElement();
				String[] parameters = request.getParameterValues(paramName);
				
				for(String paramVal : parameters) {
					logger.info("parameter ::: [" + paramName + "] " + paramVal);
				}
			}
			
			logger.info("################ Request Info ################");
		}
		
		logger.info("");
		logger.info("=========================== Courage Logging ===========================");
		
		chain.doFilter(req, res);
	}
}
