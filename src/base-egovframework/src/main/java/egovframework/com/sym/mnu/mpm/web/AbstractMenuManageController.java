
package egovframework.com.sym.mnu.mpm.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.pe.courage.common.core.CommonDefaultVO;
import kr.pe.courage.common.utils.BeanUtils;
import kr.pe.courage.common.utils.Util;
import kr.pe.courage.common.web.utils.WebUtils;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.sec.ram.service.AuthorManageVO;
import egovframework.com.sec.ram.service.EgovAuthorManageService;
import egovframework.com.sym.mnu.mpm.service.EgovMenuManageService;
import egovframework.com.sym.mnu.mpm.service.MenuManageVO;
import egovframework.com.sym.prm.service.EgovProgrmManageService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.CouragePaginationInfo;

/**
 * <pre>
 * egovframework.com.sym.mnu.mpm.web
 * AbstractMenuManageController.java
 * </pre>
 *
 * @Author	: Choi Moon Seok
 * @Date	: 2013. 11. 8.
 * @Version	: 
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2013-11-08, 수정자 : 최문석, 수정내용 : 최초등록
 * 2. 수정일 : 2014-12-08, 수정자 : 석창호, 수정내용 : 네이밍 변경 및 문석이가 싸지른 똥 치움
 * </pre>
 */
public abstract class AbstractMenuManageController {

	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	@Resource(name = "MeunManageService")
	protected EgovMenuManageService menuManageService;

	@Resource(name = "ProgrmManageService")
	protected EgovProgrmManageService progrmManageService;
	
	@Resource(name = "authorManageService")
	private EgovAuthorManageService authorManageService;
	
	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    protected EgovMessageSource egovMessageSource;
    
	/*
	 * 메뉴 목록조회 view 페이지
	 */
	protected abstract String getMenuManageListView();

	/*
	 * 메뉴 목록조회 view 팝업 페이지
	 */
	protected abstract String getMenuManageListPopupView();
	
	/*
	 * 메뉴 목록조회 view include 페이지
	 */
	protected abstract String getMenuManageIncludeListView();
	
	/*
	 * 메뉴 상세조회 view 페이지
	 */
	protected abstract String getMenuManageDetailView();

	/*
	 * 메뉴 등록 view 페이지
	 */
	protected abstract String getMenuManageCreateView();

	/*
	 * 메뉴 수정 view 페이지
	 */
	protected abstract String getMenuManageModifyView();
	
	/*
	 * 메뉴프로그램 목록조회  view 페이지
	 */
	protected abstract String getMenuProgrmManageListView();
	
	/*
	 * 공통메뉴 목록 model attribute name
	 */
	protected abstract String getMenuManageListAttributeName();
	
	/*
	 * 페이지 기본 설정정보
	 */
	protected abstract CommonDefaultVO getCommonDefaultVO();
	
	/*
	 * 팝업 페이지 기본 설정정보
	 */
	protected abstract CommonDefaultVO getCommonDefaultPopupVO();

	/**
	 * <pre>
	 * 1. 개요 : 메뉴목록 조회
	 * </pre>
	 * 
	 * @Author : Choi Moon Seok
	 * @Date : 2013. 11. 8.
	 * @Method Name : selectMenuManageList
	 * @param menuManageVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveMenuList.*")
	public ModelAndView selectMenuList(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("menuVO") MenuManageVO menuManageVO, ModelMap model) throws Exception {
		// 정렬 기본값 설정
		menuManageVO.setCondOrder(Util.isNotBlank(request.getParameter("condOrder")) ? menuManageVO.getCondOrder() : getCommonDefaultVO().getCondOrder());
		menuManageVO.setCondAlign(Util.isNotBlank(request.getParameter("condAlign")) ? menuManageVO.getCondAlign() : getCommonDefaultVO().getCondAlign());
		
		CouragePaginationInfo pagination = new CouragePaginationInfo(menuManageVO,
				getCommonDefaultVO().getPagingEnable(),
				getCommonDefaultVO().getRecordCountPerPage(),
				getCommonDefaultVO().getPageSize());
		menuManageVO = (MenuManageVO) pagination.createCustomVo(request);
		
		int totalCnt = menuManageService.selectMenuManageListTotCnt(menuManageVO);
		List<MenuManageVO> menuManageList = menuManageService.selectMenuManageList(menuManageVO);
		
		pagination.setTotalRecordCount(totalCnt);
		
		model.addAttribute(getMenuManageListAttributeName(), menuManageList);
		model.addAttribute("pagination", pagination);
		
		return new ModelAndView(getMenuManageListView(), model);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 메뉴목록 조회 (팝업)
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 14.
	 * @Method Name : retrieveMenuListPopup
	 * @param request
	 * @param response
	 * @param menuManageVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveMenuListPopup.*")
	public ModelAndView retrieveMenuListPopup(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("menuVO") MenuManageVO menuManageVO, ModelMap model) throws Exception {
		// 정렬 기본값 설정
		menuManageVO.setCondOrder(Util.isNotBlank(request.getParameter("condOrder")) ? menuManageVO.getCondOrder() : getCommonDefaultPopupVO().getCondOrder());
		menuManageVO.setCondAlign(Util.isNotBlank(request.getParameter("condAlign")) ? menuManageVO.getCondAlign() : getCommonDefaultPopupVO().getCondAlign());
		
		CouragePaginationInfo pagination = new CouragePaginationInfo(menuManageVO,
				getCommonDefaultPopupVO().getPagingEnable(),
				getCommonDefaultPopupVO().getRecordCountPerPage(),
				getCommonDefaultPopupVO().getPageSize());
		menuManageVO = (MenuManageVO) pagination.createCustomVo(request);
		
		int totalCnt = menuManageService.selectMenuManageListTotCnt(menuManageVO);
		List<MenuManageVO> menuManageList = menuManageService.selectMenuManageList(menuManageVO);
		
		pagination.setTotalRecordCount(totalCnt);
		
		model.addAttribute(getMenuManageListAttributeName(), menuManageList);
		model.addAttribute("pagination", pagination);
		
		return new ModelAndView(getMenuManageListPopupView(), model);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 메뉴 상세조회
	 * </pre>
	 * 
	 * @Author	: Choi Moon Seok
	 * @Date	: 2013. 11. 9.
	 * @Method Name : selectMenuManage
	 * @param request
	 * @param response
	 * @param menuManageVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveMenuDetail.*")
	public ModelAndView selectMenuDetail(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("menuVO") MenuManageVO menuManageVO, ModelMap model) throws Exception {
		
		MenuManageVO resultVO = menuManageService.selectMenuManage(menuManageVO);
		BeanUtils.copyCondProperties(menuManageVO, resultVO);
		
		model.addAttribute("menuVO", resultVO);

		return new ModelAndView(getMenuManageDetailView(), model);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 메뉴 등록 화면이동
	 * </pre>
	 * 
	 * @Author	: Administrator
	 * @Date	: 2013. 11. 9.
	 * @Method Name : insertMenuManage
	 * @param request
	 * @param response
	 * @param commandMap
	 * @param menuManageVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createMenu.* ")
	public ModelAndView insertMenu(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("menuVO") MenuManageVO menuManageVO, ModelMap model) throws Exception {
		menuManageVO.setMode(CommonDefaultVO.MODE_CREATE);
		
		MenuManageVO upperMenuVO = new MenuManageVO();
		upperMenuVO.setMenuId(menuManageVO.getUpperMenuId());
		upperMenuVO = menuManageService.selectMenuManage(upperMenuVO);
		
		menuManageVO.setUpperMenuNm(upperMenuVO.getMenuNm());
		
		return new ModelAndView(getMenuManageCreateView(), model);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 메뉴 등록
	 * </pre>
	 * 
	 * @Author	: Administrator
	 * @Date	: 2013. 11. 9.
	 * @Method Name : insertMenuManage
	 * @param request
	 * @param response
	 * @param commandMap
	 * @param menuManageVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createMenuProc.* ")
	public ModelAndView insertMenuProc(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("menuVO") MenuManageVO menuManageVO, BindingResult bindingResult, ModelMap model) throws Exception {
		
		//ServerSide Validation Check
		beanValidator.validate(menuManageVO, bindingResult);
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("validateCheck", "error");
			model.addAttribute("menuVO", menuManageVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);
			return new ModelAndView(getMenuManageCreateView(), model);
		}
		
		if (menuManageService.selectMenuNoByPk(menuManageVO) > 0) {
			WebUtils.setActionMessage(request, "메뉴ID가 중복되었습니다.");
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_FAILED);
			model.addAttribute("menuVO", menuManageVO);
			return new ModelAndView(getMenuManageCreateView(), model);
		}
		
		menuManageService.insertMenuManage(menuManageVO);
		
		// view page 설정
		String params = "?" + WebUtils.ACTION_STATUS_PARAM + "=" + WebUtils.ACTION_STATUS_SUCCESS;
		String returnUrl = "retrieveMenuDetail." + WebUtils.getUrlExtension(request);
		
		if (menuManageVO.getSaveLaterView().equals(MenuManageVO.SAVE_LATER_VIEW_CREATE)) {
			params += "&upperMenuId=" + menuManageVO.getUpperMenuId();
			returnUrl = "createMenu." + WebUtils.getUrlExtension(request);
		} else if (menuManageVO.getSaveLaterView().equals(MenuManageVO.SAVE_LATER_VIEW_LIST)) {
			returnUrl = "retrieveMenuList." + WebUtils.getUrlExtension(request);
		} else if (menuManageVO.getSaveLaterView().equals(MenuManageVO.SAVE_LATER_VIEW_DETAIL)) {
			returnUrl = "retrieveMenuDetail." + WebUtils.getUrlExtension(request);
			params += "&menuId=" + menuManageVO.getMenuId();
		} else {
			params += "&menuId=" + menuManageVO.getMenuId();
		}
		
		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl + params);
		
		return new ModelAndView(redirectView);
	}
	
    /**
     * <pre>
     * 1. 개요 : 메뉴정보를 수정 화면이동
     * </pre>
     * 
     * @Author	: Choi Moon Seok
     * @Date	: 2013. 11. 11.
     * @Method Name : updateMenuManage
     * @param request
     * @param response
     * @param menuManageVO
     * @param bindingResult
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="modifyMenu.* ")
    public ModelAndView updateMenu(HttpServletRequest request, HttpServletResponse response, 
    		@ModelAttribute("menuVO") MenuManageVO menuManageVO, BindingResult bindingResult, ModelMap model) throws Exception {
    	
		MenuManageVO resultVO = menuManageService.selectMenuManage(menuManageVO);
		BeanUtils.copyCondProperties(menuManageVO, resultVO);
		resultVO.setMode(CommonDefaultVO.MODE_MODIFY);
		
		model.addAttribute("menuVO", resultVO);
		
    	return new ModelAndView(getMenuManageModifyView(), model);
    }
	
    /**
     * <pre>
     * 1. 개요 : 메뉴정보를 수정
     * </pre>
     * 
     * @Author	: Choi Moon Seok
     * @Date	: 2013. 11. 11.
     * @Method Name : updateMenuManage
     * @param request
     * @param response
     * @param menuManageVO
     * @param bindingResult
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="modifyMenuProc.* ")
    public ModelAndView updateMenuProc(HttpServletRequest request, HttpServletResponse response, 
    		@ModelAttribute("menuVO") MenuManageVO menuManageVO, BindingResult bindingResult, ModelMap model) throws Exception {
    	
		//ServerSide Validation Check
		beanValidator.validate(menuManageVO, bindingResult);
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("validateCheck", "error");
			model.addAttribute("menuVO", menuManageVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);
			return new ModelAndView(getMenuManageCreateView(), model);
		}
    	
		menuManageService.updateMenuManage(menuManageVO);
		WebUtils.setActionMessage(request, egovMessageSource.getMessage("success.common.update"));
    		
    	String condParams = BeanUtils.getConditionParameter(menuManageVO);
		String params = "?menuId=" + menuManageVO.getMenuId() + (condParams.equals("") ? "" : "&" + condParams);
		String returnUrl = "retrieveMenuDetail." + WebUtils.getUrlExtension(request);
		
		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl + params);
		
		return new ModelAndView(redirectView);
    }
    
    /**
     * <pre>
     * 1. 개요 : 메뉴목록 삭제 (단일)
     * </pre>
     * 
     * @Author	: Seok Chang Ho
     * @Date	: 2014. 12. 10.
     * @Method Name : deleteMenu
     * @param request
     * @param response
     * @param menuManageVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "deleteMenu.*")
	public ModelAndView deleteMenu(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("menuVO") MenuManageVO menuManageVO, ModelMap model) throws Exception {
		
    	if (Util.isNotEmpty(menuManageVO.getUpperMenuId())) {
    		menuManageService.deleteMenuManage(menuManageVO);
    	}
		
		String returnUrl = "retrieveMenuList." + WebUtils.getUrlExtension(request);
		
		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl);
		
		return new ModelAndView(redirectView);
	}
    
	/**
	 * <pre>
	 * 1. 개요 : 메뉴 삭제 (다중)
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2014. 12. 10.
	 * @Method Name : deleteMenuList
	 * @param request
	 * @param response
	 * @param menuManageVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteMenuList.*")
	public ModelAndView deleteMenuList(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("menuVO") MenuManageVO menuManageVO, ModelMap model) throws Exception {
		
		if (Util.isNotEmpty(menuManageVO.getUpperMenuId())) {
			menuManageService.deleteMenuManage(menuManageVO);
			WebUtils.setActionMessage(request, egovMessageSource.getMessage("success.common.delete"));
		}
		
		String returnUrl = "retrieveMenuList." + WebUtils.getUrlExtension(request);
		
		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl);
		
		return new ModelAndView(redirectView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 권한 하위 메뉴 목록조회
	 * </pre>
	 * 
	 * @Author	: Administrator
	 * @Date	: 2013. 12. 10.
	 * @Method Name : selectMenuManageIncludeList
	 * @param request
	 * @param response
	 * @param menuManageVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveMenuIncludeList.*")
	public ModelAndView selectMenuIncludeList(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("menuVO") MenuManageVO menuManageVO, ModelMap model) throws Exception {
		
		List<MenuManageVO> menuManageList = menuManageService.selectMenuManageIncludeList(menuManageVO);
		model.addAttribute(getMenuManageListAttributeName(), menuManageList);
		
		return new ModelAndView(getMenuManageIncludeListView(), model);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 권한 하위 메뉴 목록조회 수정화면
	 * </pre>
	 * 
	 * @Author	: Administrator
	 * @Date	: 2013. 12. 10.
	 * @Method Name : retrieveAuthorMenuManageList
	 * @param request
	 * @param response
	 * @param menuManageVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveAuthorMenuList.*")
	public ModelAndView retrieveAuthorMenuList(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("authorManageVO") AuthorManageVO authorManageVO, ModelMap model) throws Exception {
		
		List<AuthorManageVO> authorMenuList = authorManageService.selectAuthorMenuList(authorManageVO);
		model.addAttribute(getMenuManageListAttributeName(), authorMenuList);
		
		return new ModelAndView(getMenuManageIncludeListView(), model);
	}
}