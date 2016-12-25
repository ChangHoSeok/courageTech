
package egovframework.com.sym.prm.web;

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
import egovframework.com.sym.prm.service.EgovProgrmManageService;
import egovframework.com.sym.prm.service.ProgrmManageVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.CouragePaginationInfo;

/**
 * <pre>
 * egovframework.com.sym.prm.web
 * AbstractProgrmManageController.java
 * </pre>
 * 
 * @Author : Choi Moon Seok
 * @Date : 2013. 11. 9.
 * @Version :
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2013-11-09, 수정자 : 최문석, 수정내용 : 최초등록
 * 2. 수정일 : 2014-12-08, 수정자 : 석창호, 수정내용 : 네이밍 변경 및 문석이가 싸지른 똥 치움
 * </pre>
 */
public abstract class AbstractProgrmManageController {

	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	@Resource(name = "ProgrmManageService")
	protected EgovProgrmManageService progrmManageService;

	@Resource(name = "egovMessageSource")
	protected EgovMessageSource egovMessageSource;

	/*
	 * 프로그램 목록조회 view 페이지
	 */
	protected abstract String getProgrmManageListView();

	/*
	 * 프로그램 목록 팝업 조회 view 페이지
	 */
	protected abstract String getProgrmManageListPopupView();

	/*
	 * 프로그램 상세조회 view 페이지
	 */
	protected abstract String getProgrmManageDetailView();

	/*
	 * 프로그램 등록 view 페이지
	 */
	protected abstract String getProgrmManageCreateView();

	/*
	 * 프로그램 수정 view 페이지
	 */
	protected abstract String getProgrmManageModifyView();

	/*
	 * 공통 프로그램목록 model attribute name
	 */
	protected abstract String getProgrmManageListAttributeName();

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
	 * 1. 개요 : 프로그램 목록조회
	 * </pre>
	 * 
	 * @Author : Choi Moon Seok
	 * @Date : 2013. 11. 9.
	 * @Method Name : selectProgrmList
	 * @param searchVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveProgrmList.*")
	public ModelAndView selectProgrmList(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("progrmVO") ProgrmManageVO progrmManageVO, ModelMap model) throws Exception {
		// 정렬 기본값 설정 (사용자 설정 값이 있으면 설정값으로하며, 사용자 설정값이 없으면 기본 값으로 정렬)
		progrmManageVO.setCondOrder(Util.isNotBlank(request.getParameter("condOrder")) ? progrmManageVO.getCondOrder() : getCommonDefaultVO().getCondOrder());
		progrmManageVO.setCondAlign(Util.isNotBlank(request.getParameter("condAlign")) ? progrmManageVO.getCondAlign() : getCommonDefaultVO().getCondAlign());
		
		// 페이지 정보 설정
		CouragePaginationInfo pagination = new CouragePaginationInfo(progrmManageVO,
				getCommonDefaultVO().getPagingEnable(),
				getCommonDefaultVO().getRecordCountPerPage(),
				getCommonDefaultVO().getPageSize());
		progrmManageVO = (ProgrmManageVO) pagination.createCustomVo(request);

		int totalCnt = progrmManageService.selectProgrmListTotCnt(progrmManageVO);
		List<ProgrmManageVO> progrmManageList = progrmManageService.selectProgrmList(progrmManageVO);

		pagination.setTotalRecordCount(totalCnt);

		model.addAttribute(getProgrmManageListAttributeName(), progrmManageList);
		model.addAttribute("pagination", pagination);

		if (Util.nvl(progrmManageVO.getCustomView()).equals("")) {
			return new ModelAndView(getProgrmManageListView(), model);
		} else {
			return new ModelAndView(progrmManageVO.getCustomView(), model);
		}
	}

	/**
	 * <pre>
	 * 1. 개요 : 프로그램 상세조회
	 * </pre>
	 * 
	 * @Author : Choi Moon Seok
	 * @Date : 2013. 11. 9.
	 * @Method Name : selectProgrm
	 * @param request
	 * @param response
	 * @param progrmManageVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveProgrmDetail.*")
	public ModelAndView selectProgrmDetail(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("progrmVO") ProgrmManageVO progrmManageVO, ModelMap model) throws Exception {

		ProgrmManageVO resultVO = progrmManageService.selectProgrm(progrmManageVO);
		BeanUtils.copyCondProperties(progrmManageVO, resultVO);

		model.addAttribute("progrmVO", resultVO);
		WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_SUCCESS);

		return new ModelAndView(getProgrmManageDetailView(), model);

	}

	/**
	 * <pre>
	 * 1. 개요 : 프로그램 등록화면 이동
	 * </pre>
	 * 
	 * @Author : Choi Moon Seok
	 * @Date : 2013. 11. 9.
	 * @Method Name : insertProgrmList
	 * @param request
	 * @param response
	 * @param commandMap
	 * @param progrmManageVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createProgrm.*")
	public ModelAndView insertProgrm(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("progrmVO") ProgrmManageVO progrmManageVO, BindingResult bindingResult, ModelMap model) throws Exception {

		progrmManageVO.setMode(CommonDefaultVO.MODE_CREATE);
		WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_SUCCESS);

		return new ModelAndView(getProgrmManageCreateView(), model);
	}

	/**
	 * <pre>
	 * 1. 개요 : 프로그램 등록
	 * </pre>
	 * 
	 * @Author : Choi Moon Seok
	 * @Date : 2013. 11. 9.
	 * @Method Name : insertProgrmListProc
	 * @param request
	 * @param response
	 * @param commandMap
	 * @param progrmManageVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createProgrmProc.*")
	public ModelAndView insertProgrmProc(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("progrmVO") ProgrmManageVO progrmManageVO, BindingResult bindingResult, ModelMap model) throws Exception {

		// ServerSide Validation Check
		beanValidator.validate(progrmManageVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("validateCheck", "error");
			model.addAttribute("progrmManageVO", progrmManageVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);
			return new ModelAndView(getProgrmManageCreateView(), model);
		}

		if (progrmManageVO.getProgrmDc() == null || progrmManageVO.getProgrmDc().equals("")) {
			progrmManageVO.setProgrmDc(" ");
		}

		// 프로그램 중보 확인
		ProgrmManageVO resultVO = progrmManageService.selectProgrm(progrmManageVO);
		if (resultVO != null) {
			WebUtils.setActionMessage(request, egovMessageSource.getMessage("progrmManageVO.error.msg.duplicate"));
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);
			return new ModelAndView(getProgrmManageCreateView(), model);
		}

		progrmManageService.insertProgrm(progrmManageVO);
		WebUtils.setActionMessage(request, egovMessageSource.getMessage("success.common.insert"));

		// view page 설정
		String params = "?" + WebUtils.ACTION_STATUS_PARAM + "=" + WebUtils.ACTION_STATUS_SUCCESS;
		String returnUrl = "retrieveProgrmDetail." + WebUtils.getUrlExtension(request);
		
		if (progrmManageVO.getSaveLaterView().equals(ProgrmManageVO.SAVE_LATER_VIEW_CREATE)) {
			returnUrl = "createProgrm." + WebUtils.getUrlExtension(request);
		} else if (progrmManageVO.getSaveLaterView().equals(ProgrmManageVO.SAVE_LATER_VIEW_LIST)) {
			returnUrl = "retrieveProgrmList." + WebUtils.getUrlExtension(request);
		} else if (progrmManageVO.getSaveLaterView().equals(ProgrmManageVO.SAVE_LATER_VIEW_DETAIL)) {
			params += "&progrmFileNm=" + progrmManageVO.getProgrmFileNm();
			returnUrl = "retrieveProgrmDetail." + WebUtils.getUrlExtension(request);
		} else {
			params += "&progrmFileNm=" + progrmManageVO.getProgrmFileNm();
		}
		
		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl + params);

		return new ModelAndView(redirectView);
	}

	/**
	 * <pre>
	 * 1. 개요 : 프로그램 수정화면 이동
	 * </pre>
	 * 
	 * @Author : Choi Moon Seok
	 * @Date : 2013. 11. 9.
	 * @Method Name : updateProgrmList
	 * @param progrmManageVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modifyProgrm.*")
	public ModelAndView updateProgrm(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("progrmVO") ProgrmManageVO progrmManageVO, BindingResult bindingResult, ModelMap model) throws Exception {

		ProgrmManageVO resultVO = progrmManageService.selectProgrm(progrmManageVO);
		BeanUtils.copyCondProperties(progrmManageVO, resultVO);
		resultVO.setMode(CommonDefaultVO.MODE_MODIFY);

		model.addAttribute("progrmVO", resultVO);
		WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_SUCCESS);

		return new ModelAndView(getProgrmManageModifyView(), model);

	}

	/**
	 * <pre>
	 * 1. 개요 : 프로그램 수정화면
	 * </pre>
	 * 
	 * @Author : Choi Moon Seok
	 * @Date : 2013. 11. 9.
	 * @Method Name : updateProgrmListProc
	 * @param progrmManageVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modifyProgrmProc.*")
	public ModelAndView updateProgrmProc(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("progrmVO") ProgrmManageVO progrmManageVO, BindingResult bindingResult, ModelMap model) throws Exception {

		if (bindingResult.hasErrors()) {
			model.addAttribute("validateCheck", "error");
			model.addAttribute("progrmVO", progrmManageVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);
			return new ModelAndView(getProgrmManageCreateView(), model);
		}

		if (progrmManageVO.getProgrmDc() == null || progrmManageVO.getProgrmDc().equals("")) {
			progrmManageVO.setProgrmDc(" ");
		}

		progrmManageService.updateProgrm(progrmManageVO);

		String condParams = BeanUtils.getConditionParameter(progrmManageVO);
		String params = "?progrmFileNm=" + progrmManageVO.getProgrmFileNm() + "&" + WebUtils.ACTION_STATUS_PARAM + "=" + WebUtils.ACTION_STATUS_SUCCESS + (condParams.equals("") ? "" : "&" + condParams);
		String returnUrl = "retrieveProgrmDetail." + WebUtils.getUrlExtension(request);

		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl + params);

		return new ModelAndView(redirectView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 프로그램 삭제(단일정보)
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2014. 12. 8.
	 * @Method Name : deleteProgrm
	 * @param progrmManageVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteProgrm.*")
	public ModelAndView deleteProgrm(HttpServletRequest request, @ModelAttribute("progrmVO") ProgrmManageVO progrmManageVO) throws Exception {
		progrmManageService.deleteProgrm(progrmManageVO);
		
		// view page 설정
		String condParams = BeanUtils.getConditionParameter(progrmManageVO);
		String params = "?" + WebUtils.ACTION_STATUS_PARAM + "=" + WebUtils.ACTION_STATUS_SUCCESS + (condParams.equals("") ? "" : "&" + condParams);
		
		String returnUrl = "retrieveProgrmList." + WebUtils.getUrlExtension(request);
		
		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl + params);

		return new ModelAndView(redirectView);
	}

	/**
	 * <pre>
	 * 1. 개요 : 프로그램 삭제
	 * </pre>
	 * 
	 * @Author : Choi Moon Seok
	 * @Date : 2013. 11. 9.
	 * @Method Name : deleteProgrmList
	 * @param progrmManageVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteProgrmList.*")
	public ModelAndView deleteProgrmList(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("progrmVO") ProgrmManageVO progrmManageVO, ModelMap model) throws Exception {

		progrmManageService.deleteProgrmList(progrmManageVO);
		WebUtils.setActionMessage(request, egovMessageSource.getMessage("success.common.delete"));

		String returnUrl = "retrieveProgrmList." + WebUtils.getUrlExtension(request);

		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl);

		return new ModelAndView(redirectView);
	}

	/**
	 * <pre>
	 * 1. 개요 : 프로그램 목록 팝업 조회
	 * </pre>
	 * 
	 * @Author : Choi Moon Seok
	 * @Date : 2013. 11. 13.
	 * @Method Name : selectProgrmListPopup
	 * @param searchVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveProgrmListPopup.*")
	public ModelAndView selectProgrmListPopup(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("progrmVO") ProgrmManageVO progrmManageVO, ModelMap model) throws Exception {
		// 정렬필드 기본값 설정
		progrmManageVO.setCondOrder(Util.isNotBlank(request.getParameter("condOrder")) ? progrmManageVO.getCondOrder() : getCommonDefaultPopupVO().getCondOrder());
		progrmManageVO.setCondAlign(Util.isNotBlank(request.getParameter("condAlign")) ? progrmManageVO.getCondAlign() : getCommonDefaultPopupVO().getCondAlign());
		
		CouragePaginationInfo pagination = new CouragePaginationInfo(progrmManageVO,
				getCommonDefaultPopupVO().getPagingEnable(),
				getCommonDefaultPopupVO().getRecordCountPerPage(),
				getCommonDefaultPopupVO().getPageSize());
		progrmManageVO = (ProgrmManageVO) pagination.createCustomVo(request);

		int totalCnt = progrmManageService.selectProgrmListTotCnt(progrmManageVO);
		List<ProgrmManageVO> progrmManageList = progrmManageService.selectProgrmList(progrmManageVO);

		pagination.setTotalRecordCount(totalCnt);

		model.addAttribute(getProgrmManageListAttributeName(), progrmManageList);
		model.addAttribute("pagination", pagination);

		return new ModelAndView(getProgrmManageListPopupView(), model);
	}
}
