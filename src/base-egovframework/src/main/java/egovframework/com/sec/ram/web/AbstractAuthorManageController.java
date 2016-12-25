
package egovframework.com.sec.ram.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.pe.courage.common.core.CommonDefaultVO;
import kr.pe.courage.common.prop.PropertiesMap;
import kr.pe.courage.common.utils.BeanUtils;
import kr.pe.courage.common.utils.Util;
import kr.pe.courage.common.web.utils.WebUtils;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.sec.ram.service.AuthorManageVO;
import egovframework.com.sec.ram.service.EgovAuthorManageService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.CouragePaginationInfo;

/**
 * 권한관리에 관한 controller 클래스를 정의한다.
 * 
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.11  이문준          최초 생성
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 * 
 * </pre>
 */

public abstract class AbstractAuthorManageController {

	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	@Resource(name = "authorManageService")
	private EgovAuthorManageService authorManageService;

	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/*
	 * 권한 목록 view 페이지
	 */
	protected abstract String getAuthorManageListView();

	/*
	 * 권한 상세조회 view 페이지
	 */
	protected abstract String getAuthorManageDetailView();

	/*
	 * 권한 등록 view 페이지
	 */
	protected abstract String getAuthorManageCreateView();

	/*
	 * 권한 수정 view 페이지
	 */
	protected abstract String getAuthorManageModifyView();

	/*
	 * 권한 메뉴 목록 view 페이지
	 */
	protected abstract String getAuthorMenuManageListView();

	/*
	 * 권한 기본 설정정보
	 */
	protected abstract CommonDefaultVO getCommonDefaultVO();

	protected abstract String getAuthorManageListAttributeName();

	/**
	 * <pre>
	 * 1. 개요 : 권한 목록
	 * </pre>
	 * 
	 * @Author : Administrator
	 * @Date : 2013. 12. 6.
	 * @Method Name : selectAuthorList
	 * @param authorManageVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveAuthList.*")
	public ModelAndView selectAuthorList(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("authVO") AuthorManageVO authorManageVO, ModelMap model) throws Exception {

		// 정렬필드 기본값 설정
		authorManageVO.setCondOrder(Util.isNotBlank(request.getParameter("condOrder")) ? authorManageVO.getCondOrder() : getCommonDefaultVO().getCondOrder());

		// 정렬방법 기본값 설정
		authorManageVO.setCondAlign(Util.isNotBlank(request.getParameter("condAlign")) ? authorManageVO.getCondAlign() : getCommonDefaultVO().getCondAlign());

		CouragePaginationInfo pagination = new CouragePaginationInfo(authorManageVO,
				getCommonDefaultVO().getPagingEnable(),
				getCommonDefaultVO().getRecordCountPerPage(),
				getCommonDefaultVO().getPageSize());
		authorManageVO = (AuthorManageVO) pagination.createCustomVo(request);

		pagination.setTotalRecordCount(authorManageService.selectAuthorListTotCnt(authorManageVO));
		List<AuthorManageVO> authorManageList = authorManageService.selectAuthorList(authorManageVO);

		model.addAttribute(getAuthorManageListAttributeName(), authorManageList);
		model.addAttribute("pagination", pagination);

		return new ModelAndView(getAuthorManageListView(), model);

	}

	/**
	 * <pre>
	 * 1. 개요 : 권한 상세조회
	 * </pre>
	 * 
	 * @Author : Administrator
	 * @Date : 2013. 12. 6.
	 * @Method Name : selectAuthor
	 * @param request
	 * @param response
	 * @param authorManageVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveAuthDetail.*")
	public ModelAndView selectAuthorDetail(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("authVO") AuthorManageVO authorManageVO, ModelMap model) throws Exception {
		AuthorManageVO resultVO = authorManageService.selectAuthor(authorManageVO);
		BeanUtils.copyCondProperties(authorManageVO, resultVO);

		model.addAttribute("authVO", resultVO);

		return new ModelAndView(getAuthorManageDetailView(), model);

	}

	/**
	 * <pre>
	 * 1. 개요 : 등록화면 이동
	 * </pre>
	 * 
	 * @Author : Administrator
	 * @Date : 2013. 12. 6.
	 * @Method Name : insertAuthorView
	 * @param request
	 * @param response
	 * @param authorManageVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createAuth.*")
	public ModelAndView insertAuthor(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("authVO") AuthorManageVO authorManageVO, BindingResult bindingResult, ModelMap model) throws Exception {
		authorManageVO.setMode(CommonDefaultVO.MODE_CREATE);
		return new ModelAndView(getAuthorManageCreateView(), model);
	}

	/**
	 * <pre>
	 * 1. 개요 : 등록
	 * </pre>
	 * 
	 * @Author : Administrator
	 * @Date : 2013. 12. 6.
	 * @Method Name : insertAuthorProc
	 * @param request
	 * @param response
	 * @param authorManageVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createAuthProc.*")
	public ModelAndView insertAuthorProc(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("authVO") AuthorManageVO authorManageVO, BindingResult bindingResult, ModelMap model) throws Exception {

		// ServerSide Validation Check
		beanValidator.validate(authorManageVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("validateCheck", "error");
			model.addAttribute("authVO", authorManageVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);
			return new ModelAndView(getAuthorManageCreateView(), model);
		}
		
		if (authorManageService.selectAuthor(authorManageVO) != null) {
			WebUtils.setActionMessage(request, egovMessageSource.getMessage("authorManage.error.msg.duplicate"));
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);
			model.addAttribute("authVO", authorManageVO);
			return new ModelAndView(getAuthorManageCreateView(), model);
		}

		authorManageService.insertAuthor(authorManageVO);

		// view page 설정
		String params = "?authorCode=" + authorManageVO.getAuthorCode();
		String returnUrl = "retrieveAuthDetail." + WebUtils.getUrlExtension(request);

		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl + params);

		return new ModelAndView(redirectView);
	}

	/**
	 * <pre>
	 * 1. 개요 : 수정화면 이동
	 * </pre>
	 * 
	 * @Author : Administrator
	 * @Date : 2013. 12. 6.
	 * @Method Name : updateAuthor
	 * @param request
	 * @param response
	 * @param authorManageVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modifyAuth.*")
	public ModelAndView updateAuthor(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("authVO") AuthorManageVO authorManageVO, BindingResult bindingResult, ModelMap model) throws Exception {

		AuthorManageVO resultVO = authorManageService.selectAuthor(authorManageVO);
		BeanUtils.copyCondProperties(authorManageVO, resultVO);
		resultVO.setMode(CommonDefaultVO.MODE_MODIFY);

		model.addAttribute("authVO", resultVO);

		return new ModelAndView(getAuthorManageModifyView(), model);
	}

	/**
	 * <pre>
	 * 1. 개요 : 수정
	 * </pre>
	 * 
	 * @Author : Administrator
	 * @Date : 2013. 12. 6.
	 * @Method Name : updateAuthorProc
	 * @param request
	 * @param response
	 * @param authorManageVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modifyAuthProc.*")
	public ModelAndView updateAuthorProc(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("authorManageVO") AuthorManageVO authorManageVO, BindingResult bindingResult, ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			model.addAttribute("validateCheck", "error");
			model.addAttribute("authVO", authorManageVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);
			return new ModelAndView(getAuthorManageCreateView(), model);
		}

		authorManageService.updateAuthor(authorManageVO);

		String condParams = BeanUtils.getConditionParameter(authorManageVO);
		String params = "?authorCode=" + authorManageVO.getAuthorCode() + (condParams.equals("") ? "" : "&" + condParams);
		String returnUrl = "retrieveAuthDetail." + WebUtils.getUrlExtension(request);

		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl + params);

		return new ModelAndView(redirectView);
	}

	/**
	 * <pre>
	 * 1. 개요 : 삭제
	 * </pre>
	 * 
	 * @Author : Administrator
	 * @Date : 2013. 12. 6.
	 * @Method Name : deleteAuthor
	 * @param request
	 * @param response
	 * @param authorManageVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteAuth.*")
	public ModelAndView deleteAuthor(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("authVO") AuthorManageVO authorManageVO, ModelMap model) throws Exception {

		authorManageService.deleteAuthor(authorManageVO);

		String returnUrl = "retrieveAuthList." + WebUtils.getUrlExtension(request);

		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl);

		return new ModelAndView(redirectView);

	}

	/**
	 * <pre>
	 * 1. 개요 : 메뉴 프로그램 목록조회
	 * </pre>
	 * 
	 * @Author : Administrator
	 * @Date : 2013. 12. 9.
	 * @Method Name : retrieveAuthorMenuList
	 * @param request
	 * @param response
	 * @param progrmManageVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveAuthMenuList.*")
	public ModelAndView selectAuthorMenuList(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("authVO") AuthorManageVO authorManageVO, ModelMap model) throws Exception {

		List<AuthorManageVO> authorMenuList = authorManageService.selectAuthorMenuList(authorManageVO);

		model.addAttribute("authMenuList", authorMenuList);

		return new ModelAndView(getAuthorMenuManageListView(), model);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 메뉴에 해당된는 권한정보 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 11. 23.
	 * @Method Name : retrieveMenuAuthor
	 * @param session
	 * @param authorManageVO
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "retrieveMenuAuthor.*")
	public AuthorManageVO retrieveMenuAuthor(HttpSession session, AuthorManageVO authorManageVO) throws Exception {
		LoginVO loginVO = (LoginVO) session.getAttribute(PropertiesMap.getInstance().getValue("system.session.key"));
		
		String authorCode = "";

		if (loginVO != null) {
			authorCode = loginVO.getAuthorCode();
		} else {
			// 익명사용자
			authorCode = (String) session.getAttribute(LoginVO.SESSION_AUTHOR_CODE);
		}
		
		authorManageVO.setAuthorCode(authorCode);
		
		return authorManageService.selectMenuAuthor(authorManageVO);
	}
}
