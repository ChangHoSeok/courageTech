package kr.pe.courage.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.pe.courage.common.prop.PropertiesMap;
import kr.pe.courage.common.user.UserVO;
import kr.pe.courage.common.utils.Util;
import kr.pe.courage.common.web.utils.WebUtils;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * <pre>
 * kr.pe.courage.common.interceptor
 * LoginCheckInterceptor.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2012. 10. 29.
 * @Version	: 
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2012. 10. 29., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (!isSkip(request)) {
			UserVO userInfoVO = (UserVO)request.getSession().getAttribute(PropertiesMap.getInstance().getValue("system.session.key"));
			
			if (userInfoVO == null) {
				if (WebUtils.isAjaxRequest(request)) {
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				} else {
					response.sendRedirect(request.getContextPath() + "/error/errorSession.do");
				}
				
				return false;
			}
		}

		return true;
	}
	
	private boolean isSkip(HttpServletRequest request) {
		boolean resultFlag = false;
		String requestURI = request.getRequestURI();
		String systemPropertySkipURI = PropertiesMap.getInstance().getValue("system.author.check.skip");
		
		if (Util.isNotBlank(systemPropertySkipURI)) {
			String skipURIArray[] = systemPropertySkipURI.split(",");
			
			for (String skipURI : skipURIArray) {
				if (requestURI.contains(skipURI)) {
					resultFlag = true;
					break;
				}
			}
		}
		
		return resultFlag;
	}
}
