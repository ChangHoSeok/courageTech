
package kr.pe.courage.common.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * <pre>
 * kr.pe.courage.common.web.filter
 * CharacterEncodingFilter.java
 * </pre>
 * 
 * @Author : Seok Chang Ho
 * @Date : 2015. 7. 18.
 * @Version :
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2015-07-19, 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class CharacterEncodingFilter implements Filter {
	private final static String INIT_PARAM_ENCODING = "encoding";

	private FilterConfig webLoggingConfig = null;

	private String encoding;

	public void init(FilterConfig filterConfig) throws ServletException {
		webLoggingConfig = filterConfig;

		if (filterConfig.getInitParameter(CharacterEncodingFilter.INIT_PARAM_ENCODING) != null) {
			encoding = filterConfig.getInitParameter(CharacterEncodingFilter.INIT_PARAM_ENCODING);
		}
	}

	public void destroy() {
		webLoggingConfig = null;
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;

		if (encoding != null && request.getCharacterEncoding() == null) {
			request.setCharacterEncoding(encoding);
		}

		chain.doFilter(req, res);
	}
}
