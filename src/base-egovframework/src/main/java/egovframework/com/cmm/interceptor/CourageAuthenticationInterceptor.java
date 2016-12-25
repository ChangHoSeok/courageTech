
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

import egovframework.com.cmm.LoginVO;
import egovframework.com.sec.ram.service.EgovAuthorManageService;
import egovframework.com.sym.mnu.mpm.service.EgovMenuManageService;
import egovframework.com.sym.mnu.mpm.service.MenuManageVO;
import egovframework.com.utl.sim.service.EgovClntInfo;

/**
 * <pre>
 * egovframework.com.cmm.interceptor
 * CourageAuthenticationInterceptor.java
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
public class CourageAuthenticationInterceptor extends HandlerInterceptorAdapter {
	public static final String AUTHOR_CHECK_LIST = "COURAGE_AUTHOR_CHECK_LIST";
	public static final String PAGE_AUTHOR_READ = "R";
	public static final String PAGE_AUTHOR_WRITE = "CUD";
	
	@Resource(name = "authorManageService")
	private EgovAuthorManageService authorManageService;
	@Resource(name = "MeunManageService")
	private EgovMenuManageService menuManageService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		boolean resultFlag = false;
		HttpSession session = request.getSession();
		String requestURI = WebUtils.getUrlNotExtension(request);
		String sessionUserIP = session.getAttribute(LoginVO.SESSION_USER_IP) == null ? "" : (String) session.getAttribute(LoginVO.SESSION_USER_IP);
		String realUserIP = EgovClntInfo.getClntIP(request);

		// 기존 로그인 한 IP와 동일하지 않은 IP 접근 시 logout처리
		if (session.getAttribute(PropertiesMap.getInstance().getValue("system.session.key")) != null && !realUserIP.equals(sessionUserIP)) {
			session.removeAttribute(PropertiesMap.getInstance().getValue("system.session.key"));
			session.removeAttribute(LoginVO.SESSION_USER_IP);
			session.removeAttribute(LoginVO.SESSION_USER_MENULIST);
			session.removeAttribute(LoginVO.SESSION_USER_ID);
			session.removeAttribute(LoginVO.SESSION_USER_NM);
			
			session.invalidate();
		}

		// 접근메뉴 목록이 없으면 (비로그인 사용자)
		if (session.getAttribute(PropertiesMap.getInstance().getValue("system.session.key")) == null && session.getAttribute(LoginVO.SESSION_USER_MENULIST) == null) {
			MenuManageVO menuManageVO = new MenuManageVO();
			menuManageVO.setAuthorCode(PropertiesMap.getInstance().getValue("system.author.anonymous"));
			
			session.setAttribute(LoginVO.SESSION_AUTHOR_CODE, menuManageVO.getAuthorCode());
			session.setAttribute(LoginVO.SESSION_USER_MENULIST, menuManageService.selectHierMenuManageList(menuManageVO));
		}

		@SuppressWarnings("unchecked")
		List<MenuManageVO> authorUrlList = (ArrayList<MenuManageVO>) session.getAttribute(AUTHOR_CHECK_LIST);

		// 접근권한 정보 조회 (최초 1회 조회)
		if (authorUrlList == null || authorUrlList.size() <= 0) {
			MenuManageVO menuManageVO = new MenuManageVO();
			menuManageVO.setAuthorCode((String) session.getAttribute(LoginVO.SESSION_AUTHOR_CODE));

			authorUrlList = authorManageService.selectAuthorUrlList(menuManageVO);
			session.setAttribute(AUTHOR_CHECK_LIST, authorUrlList);
		}

		for (MenuManageVO menuManageVO : authorUrlList) {
			String uri = WebUtils.getUrlNotExtension(menuManageVO.getUrl().split("\\?")[0]);
			if (uri.equals(requestURI)) {
				if (menuManageVO.getProgrmFnctSe().equals(PAGE_AUTHOR_READ) && menuManageVO.getSearchFlag().equals("Y")) { // 조회기능 프로그램
					resultFlag = true;
					break;
				} else if (menuManageVO.getProgrmFnctSe().equals(PAGE_AUTHOR_WRITE) && menuManageVO.getCreateFlag().equals("Y")) { // 등록,수정,삭제 기능 프로그램
					resultFlag = true;
					break;
				}
			}
		}

		if (!resultFlag) {
			if (WebUtils.isAjaxRequest(request)) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			} else {
				response.sendRedirect(request.getContextPath() + "/error/errorAuth.do");
			}
		}

		return resultFlag;
	}
}
