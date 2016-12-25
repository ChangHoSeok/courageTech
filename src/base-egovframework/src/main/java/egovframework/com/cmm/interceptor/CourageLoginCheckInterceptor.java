package egovframework.com.cmm.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.pe.courage.common.prop.PropertiesMap;
import kr.pe.courage.common.web.utils.WebUtils;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.com.sym.prm.service.EgovProgrmManageService;
import egovframework.com.sym.prm.service.ProgrmManageVO;

/**
 * <pre>
 * egovframework.com.cmm.interceptor
 * CourageLoginCheckInterceptor.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 4. 11.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2014-10-27, 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class CourageLoginCheckInterceptor extends HandlerInterceptorAdapter {
	private static final String LOGIN_CHECK_LIST = "LOGIN_CHECK_LIST";
	
	@Resource(name = "ProgrmManageService")
	private EgovProgrmManageService progrmManageService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		boolean resultFlag = false;
		String requestURI = WebUtils.getUrlNotExtension(request);
		HttpSession session = request.getSession();
		
		@SuppressWarnings("unchecked")
		List<ProgrmManageVO> loginCheckUrlList = (ArrayList<ProgrmManageVO>) session.getAttribute(LOGIN_CHECK_LIST);
		
		// 접근권한 정보 조회 (최초 1회 조회)
		if (loginCheckUrlList == null || loginCheckUrlList.size() <= 0) {
			loginCheckUrlList = progrmManageService.selectProgrmLoginCheckList();
			session.setAttribute(LOGIN_CHECK_LIST, loginCheckUrlList);
		}
		
		if (loginCheckUrlList.size() > 0) {
			resultFlag = true;
		}
		
		if (session.getAttribute(PropertiesMap.getInstance().getValue("system.session.key")) == null) {
			for (ProgrmManageVO progrmManageVO : loginCheckUrlList) {
				String uri = WebUtils.getUrlNotExtension(progrmManageVO.getUrl().split("\\?")[0]);
				if (uri.equals(requestURI)) {
					resultFlag = false;
					break;
				}
			}
		}
		
		if (!resultFlag) {
			if (WebUtils.isAjaxRequest(request)) {
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			} else {
				response.sendRedirect(request.getContextPath() + "/error/errorSession.do");
			}
		}
		
		return resultFlag;
	}
}
