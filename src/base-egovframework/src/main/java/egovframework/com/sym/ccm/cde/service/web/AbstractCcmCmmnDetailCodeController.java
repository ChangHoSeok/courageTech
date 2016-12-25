
package egovframework.com.sym.ccm.cde.service.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.sym.ccm.cca.service.CmmnCodeVO;
import egovframework.com.sym.ccm.cca.service.EgovCcmCmmnCodeManageService;
import egovframework.com.sym.ccm.cde.service.CmmnDetailCodeVO;
import egovframework.com.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.CouragePaginationInfo;
import kr.pe.courage.common.core.CommonDefaultVO;
import kr.pe.courage.common.core.CommonPopupFormVO;
import kr.pe.courage.common.prop.PropertiesMap;
import kr.pe.courage.common.user.UserVO;
import kr.pe.courage.common.utils.BeanUtils;
import kr.pe.courage.common.utils.Util;
import kr.pe.courage.common.web.utils.WebUtils;

/**
 * 공통 상세코드 Controller
 * 
 * <pre>
 * egovframework.com.sym.ccm.cde.service.web
 * AbstractCcmCmmnDetailCodeController.java
 * </pre>
 * 
 * @Author : Seok Chang Ho
 * @Date : 2013. 11. 9.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * ================================================================
 * 		수정일		|		수정자			|			수정내용
 * 2013-11-09		석창호					최초등록
 * ================================================================
 * </pre>
 */
public abstract class AbstractCcmCmmnDetailCodeController {
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	@Resource(name = "cmmnDetailCodeManageService")
	protected EgovCcmCmmnDetailCodeManageService cmmnDetailCodeManageService;

	@Resource(name = "cmmnCodeManageService")
	protected EgovCcmCmmnCodeManageService cmmnCodeManageService;
	
	@Resource(name = "egovMessageSource")
	protected EgovMessageSource egovMessageSource;

	/*
	 * 공통 상세코드 목록조회 View 페이지
	 */
	protected abstract String getDetailCodeManageListView();

	/*
	 * 공통 상세코드 상세조회 View 페이지
	 */
	protected abstract String getDetailCodeManageDetailView();

	/*
	 * 공통 상세코드 등록 View 페이지
	 */
	protected abstract String getDetailCodeManageCreateView();

	/*
	 * 공통 상세코드 수정 View 페이지
	 */
	protected abstract String getDetailCodeManageModifyView();
	
	/*
	 * 팝업 공통 상세코드 목록조회 View 페이지
	 */
	protected abstract String getDetailCodeManageListPopupView();

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
	 * 1. 개요 : 공통 상세코드 목록 조회
	 * </pre>
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 9.
	 * @Method Name : selectCmmnDetailCodeList
	 * @param request
	 * @param response
	 * @param cmmnDetailCodeVO
	 * @param model
	 * @return - 사용자 정의 view getDetailCodeManageListView <br/>
	 *         - cmmnDetailCodeList
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveCmmnDetailCodeList.*")
	public ModelAndView selectCmmnDetailCodeList(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("cmmnDetailCodeVO") CmmnDetailCodeVO cmmnDetailCodeVO, ModelMap model) throws Exception {
		// 정렬필드 기본값 설정
		cmmnDetailCodeVO.setCondOrder(Util.isNotBlank(request.getParameter("condOrder")) ? cmmnDetailCodeVO.getCondOrder() : getCommonDefaultVO().getCondOrder());

		// 정렬방법 기본값 설정
		cmmnDetailCodeVO.setCondAlign(Util.isNotBlank(request.getParameter("condAlign")) ? cmmnDetailCodeVO.getCondAlign() : getCommonDefaultVO().getCondAlign());

		CouragePaginationInfo pagination = new CouragePaginationInfo(cmmnDetailCodeVO,
				getCommonDefaultVO().getPagingEnable(),
				getCommonDefaultVO().getRecordCountPerPage(),
				getCommonDefaultVO().getPageSize());
		cmmnDetailCodeVO = (CmmnDetailCodeVO) pagination.createCustomVo(request);

		int totalCnt = cmmnDetailCodeManageService.selectCmmnDetailCodeListTotCnt(cmmnDetailCodeVO);
		List<CmmnDetailCodeVO> cmmnDetailCodeList = cmmnDetailCodeManageService.selectCmmnDetailCodeList(cmmnDetailCodeVO);

		pagination.setTotalRecordCount(totalCnt);

		model.addAttribute("cmmnDetailCodeList", cmmnDetailCodeList);
		model.addAttribute("pagination", pagination);
		WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_SUCCESS);

		return new ModelAndView(getDetailCodeManageListView(), model);
	}

	/**
	 * <pre>
	 * 1. 개요 : 공통 상세코드 상세정보 조회
	 * </pre>
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 9.
	 * @Method Name : selectCmmnDetailCodeDetail
	 * @param request
	 * @param response
	 * @param cmmnDetailCodeVO
	 * @param model
	 * @return - 사용자 정의 view getDetailCodeManageDetailView <br/>
	 *         - cmmnDetailCodeVO
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveCmmnDetailCodeDetail.*")
	public ModelAndView selectCmmnDetailCodeDetail(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("cmmnDetailCodeVO") CmmnDetailCodeVO cmmnDetailCodeVO, ModelMap model) throws Exception {

		CmmnDetailCodeVO resultVO = cmmnDetailCodeManageService.selectCmmnDetailCodeDetail(cmmnDetailCodeVO);
		BeanUtils.copyCondProperties(cmmnDetailCodeVO, resultVO);

		model.addAttribute("cmmnDetailCodeVO", resultVO);
		WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_SUCCESS);

		return new ModelAndView(getDetailCodeManageDetailView(), model);
	}

	/**
	 * <pre>
	 * 1. 개요 : 공통 상세코드 등록정보 조회
	 * </pre>
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 10.
	 * @Method Name : insertCmmnDetailCode
	 * @param request
	 * @param response
	 * @param cmmnDetailCodeVO
	 * @param model
	 * @return - 사용자 정의 view getDetailCodeManageCreateView <br/>
	 *         - cmmnDetailCodeVO
	 * @throws Exception
	 */
	@RequestMapping(value = "createCmmnDetailCode.*")
	public ModelAndView insertCmmnDetailCode(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("cmmnCodeVO") CmmnCodeVO cmmnCodeVO, ModelMap model) throws Exception {

		// 공통코드 정보 View 처리
		CmmnCodeVO tempVO = cmmnCodeManageService.selectCmmnCodeDetail(cmmnCodeVO);
		CmmnDetailCodeVO resultVO = new CmmnDetailCodeVO();
		resultVO.setCodeId(tempVO.getCodeId());
		resultVO.setCodeIdNm(tempVO.getCodeIdNm());
		resultVO.setMode(CommonDefaultVO.MODE_CREATE);

		model.addAttribute("cmmnDetailCodeVO", resultVO);
		WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_SUCCESS);

		return new ModelAndView(getDetailCodeManageCreateView(), model);
	}

	/**
	 * <pre>
	 * 1. 개요 : 공통 상세코드 등록
	 * </pre>
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 11.
	 * @Method Name : insertCmmnDetailCodeProc
	 * @param request
	 * @param response
	 * @param cmmnDetailCodeVO
	 * @param bindingResult
	 * @param model
	 * @return - redirectView (retrieveCmmnDetailCodeDetail) <br/>
	 *         - parameters [codeId, code, actionStatus]
	 * @throws Exception
	 */
	@RequestMapping(value = "createCmmnDetailCodeProc.*")
	public ModelAndView insertCmmnDetailCodeProc(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("cmmnDetailCodeVO") CmmnDetailCodeVO cmmnDetailCodeVO,
			BindingResult bindingResult,
			@ModelAttribute("commonPopupFormVO") CommonPopupFormVO commonPopupFormVO,
			ModelMap model) throws Exception {

		// ServerSide Validation Check
		beanValidator.validate(cmmnDetailCodeVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("validateCheck", "error");
			model.addAttribute("cmmnDetailCodeVO", cmmnDetailCodeVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);
			return new ModelAndView(getDetailCodeManageCreateView(), model);
		}
		
		// 코드 중복성 검사
		CmmnDetailCodeVO duplicateVO = cmmnDetailCodeManageService.selectCmmnDetailCodeDetail(cmmnDetailCodeVO);
		if (duplicateVO != null) {
			WebUtils.setActionMessage(request, egovMessageSource.getMessage("cmmnDetailCodeVO.ordr.msg.duplicate"));
			model.addAttribute("cmmnDetailCodeVO", cmmnDetailCodeVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);
			return new ModelAndView(getDetailCodeManageCreateView(), model);
		}

		BeanUtils.getAttributeEmptyToNull(cmmnDetailCodeVO);
		UserVO uesrVO = (UserVO) session.getAttribute(PropertiesMap.getInstance().getValue("system.session.key"));
		cmmnDetailCodeVO.setFrstRegisterId(uesrVO.getUniqId());
		cmmnDetailCodeVO.setJsessionId(WebUtils.getSessionId(request));
		cmmnDetailCodeManageService.insertCmmnDetailCode(cmmnDetailCodeVO);

		// view page 설정
		String condPopupParams = BeanUtils.getConditionParameter(commonPopupFormVO);
		String params = "?codeId=" + cmmnDetailCodeVO.getCodeId() + "&code=" + cmmnDetailCodeVO.getCode()
				+ "&" + WebUtils.ACTION_STATUS_PARAM + "=" + WebUtils.ACTION_STATUS_SUCCESS
				+ (condPopupParams.equals("") ? "" : "&" + condPopupParams);
		String returnUrl = "retrieveCmmnDetailCodeDetail." + WebUtils.getUrlExtension(request);

		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl + params);

		return new ModelAndView(redirectView);
	}

	/**
	 * <pre>
	 * 1. 개요 : 공통 상세코드 수정정보 조회
	 * </pre>
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 10.
	 * @Method Name : updateCmmnDetailCode
	 * @param request
	 * @param response
	 * @param cmmnDetailCodeVO
	 * @param model
	 * @return - 사용자 정의 view getDetailCodeManageModifyView <br/>
	 *         - cmmnDetailCodeVO
	 * @throws Exception
	 */
	@RequestMapping(value = "modifyCmmnDetailCode.*")
	public ModelAndView updateCmmnDetailCode(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("cmmnDetailCodeVO") CmmnDetailCodeVO cmmnDetailCodeVO, ModelMap model) throws Exception {

		CmmnDetailCodeVO resultVO = cmmnDetailCodeManageService.selectCmmnDetailCodeDetail(cmmnDetailCodeVO);
		BeanUtils.copyCondProperties(cmmnDetailCodeVO, resultVO);
		resultVO.setMode(CommonDefaultVO.MODE_MODIFY);

		model.addAttribute("cmmnDetailCodeVO", resultVO);
		WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_SUCCESS);

		return new ModelAndView(getDetailCodeManageModifyView(), model);
	}

	/**
	 * <pre>
	 * 1. 개요 : 공통 상세코드 등록
	 * </pre>
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 11.
	 * @Method Name : updateCmmnDetailCodeProc
	 * @param request
	 * @param response
	 * @param cmmnDetailCodeVO
	 * @param bindingResult
	 * @param model
	 * @return - redirectView (retrieveCmmnDetailCodeDetail) <br/>
	 *         - parameters [codeId, code, actionStatus, KeepCondition]
	 * @throws Exception
	 */
	@RequestMapping(value = "modifyCmmnDetailCodeProc.*")
	public ModelAndView updateCmmnDetailCodeProc(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("cmmnDetailCodeVO") CmmnDetailCodeVO cmmnDetailCodeVO,
			BindingResult bindingResult,
			@ModelAttribute("commonPopupFormVO") CommonPopupFormVO commonPopupFormVO,
			ModelMap model) throws Exception {

		// ServerSide Validation Check
		beanValidator.validate(cmmnDetailCodeVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("validateCheck", "error");
			model.addAttribute("cmmnDetailCodeVO", cmmnDetailCodeVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);
			return new ModelAndView(getDetailCodeManageCreateView(), model);
		}

		BeanUtils.getAttributeEmptyToNull(cmmnDetailCodeVO);
		UserVO uesrVO = (UserVO) session.getAttribute(PropertiesMap.getInstance().getValue("system.session.key"));
		cmmnDetailCodeVO.setLastUpdusrId(uesrVO.getUniqId());
		cmmnDetailCodeVO.setJsessionId(WebUtils.getSessionId(request));
		cmmnDetailCodeManageService.updateCmmnDetailCode(cmmnDetailCodeVO);

		// view page 설정
		String condParams = BeanUtils.getConditionParameter(cmmnDetailCodeVO);
		String condPopupParams = BeanUtils.getConditionParameter(commonPopupFormVO);
		String params = "?codeId=" + cmmnDetailCodeVO.getCodeId() + "&code=" + cmmnDetailCodeVO.getCode() + "&"
				+ WebUtils.ACTION_STATUS_PARAM + "=" + WebUtils.ACTION_STATUS_SUCCESS
				+ (condParams.equals("") ? "" : "&" + condParams)
				+ (condPopupParams.equals("") ? "" : "&" + condPopupParams);
		String returnUrl = "retrieveCmmnDetailCodeDetail." + WebUtils.getUrlExtension(request);
		
		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl + params);

		return new ModelAndView(redirectView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 공통 상세코드 목록 조회 (팝업)
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2014. 12. 10.
	 * @Method Name : retrieveCmmnDetailCodeListPopup
	 * @param request
	 * @param response
	 * @param cmmnDetailCodeVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveCmmnDetailCodeListPopup.*")
	public ModelAndView retrieveCmmnDetailCodeListPopup(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("cmmnDetailCodeVO") CmmnDetailCodeVO cmmnDetailCodeVO, ModelMap model) throws Exception {
		
		// 코드아이디 조건 필수
		if (Util.isNotEmpty(cmmnDetailCodeVO.getCondCodeId())) {
			// 정렬필드 기본값 설정
			cmmnDetailCodeVO.setCondOrder(Util.isNotBlank(request.getParameter("condOrder")) ? cmmnDetailCodeVO.getCondOrder() : getCommonDefaultPopupVO().getCondOrder());
			
			// 정렬방법 기본값 설정
			cmmnDetailCodeVO.setCondAlign(Util.isNotBlank(request.getParameter("condAlign")) ? cmmnDetailCodeVO.getCondAlign() : getCommonDefaultPopupVO().getCondAlign());
			
			CouragePaginationInfo pagination = new CouragePaginationInfo(cmmnDetailCodeVO,
					getCommonDefaultPopupVO().getPagingEnable(),
					getCommonDefaultPopupVO().getRecordCountPerPage(),
					getCommonDefaultPopupVO().getPageSize());
			cmmnDetailCodeVO = (CmmnDetailCodeVO) pagination.createCustomVo(request);
			
			pagination.setTotalRecordCount(cmmnDetailCodeManageService.selectCmmnDetailCodeListTotCnt(cmmnDetailCodeVO));
			List<CmmnDetailCodeVO> cmmnDetailCodeList = cmmnDetailCodeManageService.selectCmmnDetailCodeList(cmmnDetailCodeVO);
			
			model.addAttribute("cmmnDetailCodeList", cmmnDetailCodeList);
			model.addAttribute("pagination", pagination);
		}
		

		return new ModelAndView(getDetailCodeManageListPopupView(), model);
	}
}
