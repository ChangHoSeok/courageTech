
package kr.pe.courage.tech.uat.login.web;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.pe.courage.common.prop.PropertiesMap;
import kr.pe.courage.common.utils.Util;
import kr.pe.courage.common.web.listener.CourageHttpSessionBindingListener;
import kr.pe.courage.common.web.listener.SessionMap;
import kr.pe.courage.common.web.utils.WebUtils;
import kr.pe.courage.tech.uat.login.service.LoginService;
import kr.pe.courage.tech.uat.login.service.LoginVO;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.interceptor.CourageAuthenticationInterceptor;
import egovframework.com.sec.ram.service.AuthorManageVO;
import egovframework.com.sec.ram.service.EgovAuthorManageService;
import egovframework.com.sym.mnu.mpm.service.EgovMenuManageService;
import egovframework.com.sym.mnu.mpm.service.MenuManageVO;
import egovframework.com.utl.cas.service.EgovSessionCookieUtil;
import egovframework.com.utl.sim.service.EgovClntInfo;
import egovframework.rte.fdl.cryptography.CourageCryptoService;

/**
 * <pre>
 * kr.pe.courage.tech.uat.login.web
 * LoginController.java
 * </pre>
 *
 * @Author : ChangHo Seok
 * @Date : 2016. 1. 6.
 * @Version : 1.0
 * @see
 *
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 1. 6., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
@Controller("loginController")
@RequestMapping("/uat/login/*")
@SessionAttributes("loginForm")
public class LoginController {
	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	@Resource(name = "courageARIACryptoService")
	private CourageCryptoService cryptoService;;
	
	@Resource(name = "authorManageService")
	private EgovAuthorManageService authorManageService;
	
	@Resource(name = "MeunManageService")
	private EgovMenuManageService menuService;

	@Resource(name = "loginService")
	private LoginService loginService;

	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	@Value("#{uatView['formLoginView']}")
	private String formLoginView;

	@Value("#{uatView['subFormLoginView']}")
	private String subFormLoginView;
	
	@ModelAttribute("loginForm")
	private String loginForm() {
		return "";
	}

	/**
	 * <pre>
	 * 1. 개요 : 로그인 페이지
	 * </pre>
	 *
	 * @Author : ChangHo Seok
	 * @Date : 2016. 1. 14.
	 * @Method Name : formLogin
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"formLogin.*", "subFormLogin.*"})
	public ModelAndView formLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session, LoginVO loginVO, ModelMap model) throws Exception {
		boolean isLayerPopup = request.getServletPath().endsWith("subFormLogin." + WebUtils.getUrlExtension(request));
		
		// 로그인 여부 조회
		LoginVO userLoginVO = (LoginVO)request.getSession().getAttribute(PropertiesMap.getInstance().getValue("system.session.key"));
		
		if (userLoginVO == null) {
			// Cookie 조회
			String userID = EgovSessionCookieUtil.getCookie(request, "/", LoginVO.COOKIE_USER_ID);
			
			if (!Util.nvl(userID).equals("")) {
				try {
					// 데이터 복호화
					userID = new String(cryptoService.decrypt(Base64.decodeBase64(userID)));
				} catch (NullPointerException ex) {
					userID = "";
					EgovSessionCookieUtil.setCookie(response, "/", LoginVO.COOKIE_USER_ID);
				}
			}
			
			if (!Util.nvl(userID).equals("")) {
				loginVO.setRememberMe(true);
				loginVO.setEmplyrId(userID);
			}
			model.addAttribute("loginForm", isLayerPopup ? subFormLoginView : formLoginView);
			
			return new ModelAndView(isLayerPopup ? subFormLoginView : formLoginView);
		} else {
			if (isLayerPopup) {
				WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_SUCCESS);
				
				return new ModelAndView(subFormLoginView);
			} else {
				String returnUrl = PropertiesMap.getInstance().getValue("system.login.home");
				
				RedirectView redirectView = new RedirectView();
				redirectView.setContextRelative(true);
				redirectView.setUrl(returnUrl);
				
				return new ModelAndView(redirectView);
			}
		}
	}

	/**
	 * <pre>
	 * 1. 개요 : 사용자 로그인
	 * </pre>
	 *
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 1. 18.
	 * @Method Name : userLogin
	 * @param request
	 * @param session
	 * @param loginVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="userLogin.*", method=RequestMethod.POST)
	public ModelAndView userLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			SessionStatus sessionStatus, @ModelAttribute("loginForm") String loginForm, LoginVO loginVO, BindingResult bindingResult,
			ModelMap model) throws Exception {
		beanValidator.validate(loginVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("validateCheck", "error");
			model.addAttribute("loginVO", loginVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);

			return new ModelAndView(loginForm, model);
		}
		
		LoginVO loginUserInfo = loginService.selectLoginUserInfo(loginVO);

		if (loginUserInfo != null && loginUserInfo.getLoginStat().equals(LoginVO.LOGIN_STATUS_SUCCESS)) {
			// 사용자 로그인여부 확인
			boolean duplecateLoginCheck = PropertiesMap.getInstance().getValue("system.login.duplecateCheck").equals("true") ? true : false;
			
			logger.debug("## 사용자 중복 로그인 여부");
			if (SessionMap.getInstance().isExists(loginUserInfo.getEsntlId())) {
				logger.debug("## 중복 로그인된 사용자 = " + loginUserInfo.getEsntlId());
				
				if (duplecateLoginCheck) {
					if (loginVO.isEnfrcLogin()) {
						logger.debug("## 기존 로그인된 사용자 로그아웃");
						SessionMap.getInstance().logout(loginUserInfo.getEsntlId());
					} else {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						HttpSession beforeSession = SessionMap.getInstance().get(loginUserInfo.getEsntlId());
						
						model.addAttribute(WebUtils.ACTION_MESSAGE, "이미 로그인된 사용자 입니다.\\n기존 접속을 종료하고 로그인 하려면 다시 로그인을 시도하세요.\\n로그인일시 : " + sdf.format(beforeSession.getCreationTime())
								+ "\\n접속IP : " + beforeSession.getAttribute(LoginVO.SESSION_USER_IP));
						
						loginVO.setEnfrcLogin(true); // 다시 로그인시도시 강제 로그인 설정
						model.addAttribute("loginVO", loginVO);
						WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_FAILED);

						return new ModelAndView(loginForm, model);
					}
				}
			} else {
				logger.debug("## 최초 로그인된 사용자 = " + loginUserInfo.getEsntlId());
			}
			
			// 사용자 권한 설정
			AuthorManageVO authorManageVO = new AuthorManageVO();
			authorManageVO.setEsntlId(loginUserInfo.getEsntlId());
			loginUserInfo.setAuthList(authorManageService.selectLoginUserAuthorList(authorManageVO));
			
			String[] auhtorCodes = new String[loginUserInfo.getAuthList().size()];
			for (int i = 0; i < loginUserInfo.getAuthList().size(); i++) {
				auhtorCodes[i] = loginUserInfo.getAuthList().get(i).getAuthorCode();
			}
			
			// 메뉴 설정
			MenuManageVO menuManageVO = new MenuManageVO();
			menuManageVO.setEsntlId(loginUserInfo.getEsntlId());
			menuManageVO.setAuthorCode(loginUserInfo.getAuthorCode());
			List<MenuManageVO> menuList = menuService.selectHierMenuManageList(menuManageVO);
			
			session.setAttribute(PropertiesMap.getInstance().getValue("system.session.key"), loginUserInfo);

			session.setAttribute(LoginVO.SESSION_USER_IP, EgovClntInfo.getClntIP(request));
			session.setAttribute(LoginVO.SESSION_USER_UNIQ_ID, loginUserInfo.getEsntlId());
			session.setAttribute(LoginVO.SESSION_USER_ID, loginUserInfo.getEmplyrId());
			session.setAttribute(LoginVO.SESSION_USER_NM, loginUserInfo.getName());
			session.setAttribute(LoginVO.SESSION_USER_MENULIST, menuList);
			session.setAttribute(LoginVO.SESSION_AUTHOR_LIST, loginUserInfo.getAuthList());
			session.setAttribute(LoginVO.SESSION_AUTHOR_CODE, loginUserInfo.getAuthorCode());
			session.setAttribute(LoginVO.SESSION_AUTHOR_NAME, loginUserInfo.getAuthorNm());
			session.setAttribute(LoginVO.SESSION_AUTHOR_CODE_ARRAY, auhtorCodes);
			
			// 익명사용자 권한 제거
			session.removeAttribute(CourageAuthenticationInterceptor.AUTHOR_CHECK_LIST);
			
			logger.info("isRememberMe : " + loginVO.isRememberMe());
			if (loginVO.isRememberMe()) {
				// 데이터 암호화
				String cryptoId = Base64.encodeBase64String(cryptoService.encrypt(loginUserInfo.getEmplyrId().getBytes()));
				
				EgovSessionCookieUtil.setCookie(response, "/", LoginVO.COOKIE_USER_ID, cryptoId, (60 * 24) * 7);
			} else {
				EgovSessionCookieUtil.setCookie(response, "/", LoginVO.COOKIE_USER_ID);
			}
			
			// Session Timeout 설정
			session.setMaxInactiveInterval(Integer.parseInt(PropertiesMap.getInstance().getValue("system.session.timeout")));
			
			CourageHttpSessionBindingListener sessionListener = new CourageHttpSessionBindingListener();
			session.setAttribute("Listener", sessionListener);
		} else if (loginUserInfo != null && loginUserInfo.getLoginStat().equals(LoginVO.LOGIN_STATUS_USER_NOTCONFM)) {
			model.addAttribute(WebUtils.ACTION_MESSAGE, "미승인 사용자입니다. 관리자에게 문의하세요.");
			model.addAttribute("loginVO", loginVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_FAILED);

			return new ModelAndView(loginForm, model);
		} else {
			model.addAttribute(WebUtils.ACTION_MESSAGE, "등록되지 않은 사용자 이거나 정보 입력이 잘못되었습니다.");
			model.addAttribute("loginVO", loginVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_FAILED);

			return new ModelAndView(loginForm, model);
		}
		
		sessionStatus.setComplete();
		
		if (!WebUtils.isAjaxRequest(request)) {
			String returnUrl = PropertiesMap.getInstance().getValue("system.login.home");

			RedirectView redirectView = new RedirectView();
			redirectView.setContextRelative(true);
			redirectView.setUrl(returnUrl);

			return new ModelAndView(redirectView);
		} else {
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_SUCCESS);
			return new ModelAndView(loginForm);
		}
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 로그인 사용자 접근메뉴 정보 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 3. 4.
	 * @Method Name : retrieveLoginUserMenuListJson
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveLoginUserMenuListJson.*")
	public @ResponseBody Map<String, Object> retrieveLoginUserMenuListJson(HttpSession session) throws Exception {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", Boolean.TRUE);
		result.put("menuList", session.getAttribute(LoginVO.SESSION_USER_MENULIST));

		return result;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 로그아웃
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 4. 22.
	 * @Method Name : logout
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "logout.*")
	public ModelAndView logout(HttpSession session) throws Exception {
		session.removeAttribute(PropertiesMap.getInstance().getValue("system.session.key"));
		session.removeAttribute(LoginVO.SESSION_AUTHOR_NAME);
		session.removeAttribute(LoginVO.SESSION_AUTHOR_CODE);
		session.removeAttribute(LoginVO.SESSION_AUTHOR_LIST);
		session.removeAttribute(LoginVO.SESSION_USER_MENULIST);
		session.removeAttribute(LoginVO.SESSION_USER_NM);
		session.removeAttribute(LoginVO.SESSION_USER_ID);
		session.removeAttribute(LoginVO.SESSION_USER_IP);
		session.invalidate();

		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl("/");
		
		return new ModelAndView(redirectView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 권한 변경 (다중권한 사용시)
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 4. 22.
	 * @Method Name : loginUserAuthorChange
	 * @param session
	 * @param authorId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "loginUserAuthorChange.*", method = RequestMethod.POST)
	public ModelAndView loginUserAuthorChange(HttpSession session, @RequestParam("authorId") String authorId) throws Exception {
		LoginVO loginVO = (LoginVO) session.getAttribute(PropertiesMap.getInstance().getValue("system.session.key"));
		
		if (loginVO != null) {
			List<AuthorManageVO> authList = loginVO.getAuthList();

			for (AuthorManageVO authInfo : authList) {
				if (authInfo.getAuthorCode().equals(authorId)) {
					loginVO.setAuthorCode(authInfo.getAuthorCode());
					loginVO.setAuthorNm(authInfo.getAuthorNm());

					// 메뉴설정
					MenuManageVO menuManageVO = new MenuManageVO();
					menuManageVO.setEsntlId(loginVO.getEsntlId());
					menuManageVO.setAuthorCode(loginVO.getAuthorCode());

					List<MenuManageVO> menuList = menuService.selectHierMenuManageList(menuManageVO);

					session.setAttribute(LoginVO.SESSION_AUTHOR_CODE, loginVO.getAuthorCode());
					session.setAttribute(LoginVO.SESSION_AUTHOR_NAME, loginVO.getAuthorNm());
					session.setAttribute(LoginVO.SESSION_USER_MENULIST, menuList);
					
					// 이전 권한체크 정보 제거
					session.removeAttribute(CourageAuthenticationInterceptor.AUTHOR_CHECK_LIST);
					
					// 권한 확인 캐시 제거
					authorManageService.removeAuthorCache();

					break;
				}
			}
		}
		
		String returnUrl = PropertiesMap.getInstance().getValue("system.login.home");

		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl);
		
		return new ModelAndView(redirectView);
	}
}
