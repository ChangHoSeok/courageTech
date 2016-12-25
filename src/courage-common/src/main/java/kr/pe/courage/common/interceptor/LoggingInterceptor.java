package kr.pe.courage.common.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggingInterceptor extends HandlerInterceptorAdapter {

	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("====================Request Info====================");
		logger.info("request.getRequestURI() : " + request.getRequestURI());
		logger.info("request.getRequestURL() : " + request.getRequestURL());
		logger.info("request.getServletPath() : " + request.getServletPath());
		logger.info("request.getContextPath() : " + request.getContextPath());
		logger.info("request.getPathInfo() : " + request.getPathInfo());
		logger.info("request.getMethod() : " + request.getMethod());
		logger.info("this.getClass().getName() : " + handler.getClass().getName());
		logger.info("request.getRemoteAddr() : " + request.getRemoteAddr());
		
		Enumeration enums = request.getParameterNames();
		
		while (enums.hasMoreElements()) {
			String paramName = (String) enums.nextElement();
			String[] parameters = request.getParameterValues(paramName);

			for (int i = 0; i < parameters.length; i++) {
				logger.info("parameter ::: [" + paramName + "] " + parameters[i]);
			}
		}
		logger.info("====================Request Info====================");
		return true;
	}
}
