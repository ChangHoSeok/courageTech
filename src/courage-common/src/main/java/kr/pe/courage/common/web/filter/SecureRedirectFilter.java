
package kr.pe.courage.common.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 * kr.pe.courage.common.servlet.filter
 * SecureRedirectFilter.java
 * </pre>
 * 
 * @Author : ChangHo Seok
 * @Date : 2015. 7. 10.
 * @Version : 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2015-07-10, 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class SecureRedirectFilter implements Filter {

	private final static String PARAM_SECURE_PORT = "securePort";
	private final static String PARAM_SECURE_REDIRECT_URI = "secureRedirectUri";

	private FilterConfig webLoggingConfig = null;

	private int securePort = 443;
	private String secureRedirectUri = "";

	public void destroy() {
		webLoggingConfig = null;
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.webLoggingConfig = filterConfig;

		if (webLoggingConfig.getInitParameter(PARAM_SECURE_PORT) != null) {
			try {
				this.securePort = Integer.parseInt(webLoggingConfig.getInitParameter(PARAM_SECURE_PORT));
			} catch (NumberFormatException e) {
				this.securePort = 443;
			}
		}

		if (webLoggingConfig.getInitParameter(PARAM_SECURE_REDIRECT_URI) != null) {
			this.secureRedirectUri = webLoggingConfig.getInitParameter(PARAM_SECURE_REDIRECT_URI);
		}
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		String uri = request.getRequestURI();
		String domain = request.getServerName();

		if (!request.isSecure()) {
			res.setContentType("text/html");

			StringBuilder httpsPath = new StringBuilder();
			httpsPath.append("https://");
			httpsPath.append(domain);

			if (this.securePort != 443) {
				httpsPath.append(":" + this.securePort);
			}

			if ("".equals(this.secureRedirectUri)) {
				httpsPath.append(uri);
			} else {
				httpsPath.append(this.secureRedirectUri);
			}

			String site = new String(httpsPath);
			response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site);
		}

		chain.doFilter(request, response);

	}
}