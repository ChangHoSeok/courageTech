
package kr.pe.courage.tech.system.userauth.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.sec.ram.service.AuthorManageVO;
import egovframework.com.sec.ram.service.EgovAuthorManageService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.CouragePaginationInfo;
import kr.pe.courage.common.utils.BeanUtils;
import kr.pe.courage.common.vo.JxlsParam;
import kr.pe.courage.common.web.utils.WebUtils;
import kr.pe.courage.tech.system.user.service.UserService;
import kr.pe.courage.tech.system.user.service.UserVO;
import kr.pe.courage.tech.system.userauth.service.UserAuthService;
import kr.pe.courage.tech.system.userauth.service.UserAuthVO;

/**
 * <pre>
 * kr.pe.courage.tech.system.userauth.web
 * UserAuthController.java
 * </pre>
 * 
 * @Author : ChangHo Seok
 * @Date : 2016. 3. 2.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 3. 2., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
@Controller("userAuthController")
@RequestMapping(value = "/system/userauth/*")
public class UserAuthController {
	@Resource(name = "authorManageService")
	private EgovAuthorManageService authorManageService;

	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	@Resource(name = "userAuthService")
	private UserAuthService userAuthService;

	@Resource(name = "userService")
	private UserService userService;

	@Value("#{systemView['formUserAuthView']}")
	private String formUserAuthView;

	@Value("#{systemView['userAuthListView']}")
	private String userAuthListView;

	@Value("#{systemView['formUserAuthPopupView']}")
	private String formUserAuthPopupView;

	@Value("#{systemView['userAuthCreatePopupView']}")
	private String userAuthCreatePopupView;

	@Value("#{pageConfig['userauth.page.recordCount']}")
	private int recordCount;

	@Value("#{pageConfig['userauth.page.size']}")
	private int pageSize;

	@Value("#{pageConfig['userauth.page.enable']}")
	private String pageEnable;

	/**
	 * <pre>
	 * 1. 개요 : 사용자 권한 관리 메인 form 조회
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 3. 2.
	 * @Method Name : formUserAuth
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "formUserAuth.*")
	public ModelAndView formUserAuth(ModelMap model) throws Exception {
		return new ModelAndView(formUserAuthView, model);
	}

	/**
	 * <pre>
	 * 1. 개요 : 사용자 권한관리 목록 조회
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 3. 2.
	 * @Method Name : retrieveUserAuthList
	 * @param request
	 * @param userAuthVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveUserAuthList.*")
	public ModelAndView retrieveUserAuthList(HttpServletRequest request, UserAuthVO userAuthVO, ModelMap model) throws Exception {
		CouragePaginationInfo pagination = new CouragePaginationInfo(userAuthVO, pageEnable, recordCount, pageSize);
		userAuthVO = (UserAuthVO) pagination.createCustomVo(request);

		pagination.setTotalRecordCount(userAuthService.selectUserAuthListCount(userAuthVO));
		List<UserAuthVO> userAuthList = null;

		if (pagination.getTotalRecordCount() > 0) {
			userAuthList = userAuthService.selectUserAuthList(userAuthVO);
		}

		model.addAttribute("pagination", pagination);
		model.addAttribute("userAuthList", userAuthList);

		return new ModelAndView(userAuthListView);
	}

	/**
	 * <pre>
	 * 1. 개요 : 사용자 권한 설정 팝업 메인 form
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 3. 2.
	 * @Method Name : formUserAuthPopup
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "formUserAuthPopup.*")
	public ModelAndView formUserAuthPopup(ModelMap model) throws Exception {
		return new ModelAndView(formUserAuthPopupView);
	}

	/**
	 * <pre>
	 * 1. 개요 : 사용자 권한 설정 팝업
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 3. 2.
	 * @Method Name : createUserAuthPopup
	 * @param userAuthVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createUserAuthPopup.*")
	public ModelAndView createUserAuthPopup(UserAuthVO userAuthVO, ModelMap model) throws Exception {
		// 사용자 기본정보 조회
		UserVO userVO = userService.selectUserDetail(userAuthVO);
		BeanUtils.copyCondProperties(userAuthVO, userVO);

		// 사용자 권한정보 조회
		AuthorManageVO authorManageVO = new AuthorManageVO();
		authorManageVO.setEsntlId(userAuthVO.getEsntlId());
		List<AuthorManageVO> authList = authorManageService.selectUserAuthorList(authorManageVO);

		model.addAttribute("userVO", userVO);
		model.addAttribute("authList", authList);

		return new ModelAndView(userAuthCreatePopupView, model);
	}

	/**
	 * <pre>
	 * 1. 개요 : 사용자 권한 저장 처리
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 3. 2.
	 * @Method Name : createUserAuthPopupProc
	 * @param request
	 * @param userAuthVO
	 * @param authorManageVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createUserAuthPopupProc.*")
	public ModelAndView createUserAuthPopupProc(HttpServletRequest request, UserAuthVO userAuthVO, AuthorManageVO authorManageVO)
			throws Exception {

		authorManageService.insertUserAuthManage(authorManageVO);

		String params = "?esntlId=" + authorManageVO.getEsntlId() + "&" + WebUtils.ACTION_STATUS_PARAM + "="
				+ WebUtils.ACTION_STATUS_SUCCESS;
		String returnUrl = "createUserAuthPopup." + WebUtils.getUrlExtension(request);

		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl + params);

		return new ModelAndView(redirectView);
	}

	/**
	 * <pre>
	 * 1. 개요 : 사용자 권한 목록 엑셀 다운
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 3. 2.
	 * @Method Name : downloadUserAuthExcel
	 * @param userAuthVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "downloadUserAuthExcel.*")
	public ModelAndView downloadUserAuthExcel(UserAuthVO userAuthVO, ModelMap model) throws Exception {

		userAuthVO.setPagingEnable(UserAuthVO.PAGING_ENABLE_OFF);

		int totalCnt = userAuthService.selectUserAuthListCount(userAuthVO);
		List<UserAuthVO> userAuthList = userAuthService.selectUserAuthList(userAuthVO);

		JxlsParam jxlsParam = new JxlsParam(JxlsParam.EXCEL_EXT_XLS, "userAuthExcel", "사용자 권한 목록", "사용자 권한", totalCnt);

		model.addAttribute("list", userAuthList);
		model.addAttribute("jxlsParam", jxlsParam);

		return new ModelAndView("jxlsView", model);
	}
}
