
package egovframework.com.sym.ccm.cca.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.pe.courage.common.core.CommonDefaultVO;
import kr.pe.courage.common.excellog.ExcelLogVO;
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
import egovframework.com.sym.ccm.cca.service.CmmnCodeVO;
import egovframework.com.sym.ccm.cca.service.EgovCcmCmmnCodeManageService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.CouragePaginationInfo;

/**
 * 공통코드 Controller
 *
 * <pre>
 * egovframework.com.sym.ccm.cca.web
 * AbstractCcmCmmnCodeController.java
 * </pre>
 *
 * @Author : Seok Chang Ho
 * @Date : 2013. 11. 6.
 * @Version : 1.0
 * @see
 *
 *      <pre>
 * << 개정이력 >>
 * ================================================================
 * 		수정일		|		수정자			|			수정내용
 * 2013-11-06		석창호					최초등록
 * ================================================================
 * </pre>
 */
public abstract class AbstractCcmCmmnCodeController {

	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	@Resource(name = "cmmnCodeManageService")
	protected EgovCcmCmmnCodeManageService cmmnCodeManageService;

	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/*
	 * 공통코드 목록조회 view 페이지
	 */
	protected abstract String getCodeManageListView();

	/*
	 * 공통코드 상세조회 view 페이지
	 */
	protected abstract String getCodeManageDetailView();

	/*
	 * 공통코드 등록 view 페이지
	 */
	protected abstract String getCodeManageCreateView();

	/*
	 * 공통코드 수정 view 페이지
	 */
	protected abstract String getCodeManageModifyView();

	/*
	 * 엑셀일괄등록 로그 view 페이지
	 */
	protected abstract String getExcelLogView();

	/*
	 * 페이지 기본 설정정보
	 */
	protected abstract CommonDefaultVO getCommonDefaultVO();

	/**
	 * <pre>
	 * 1. 개요 : 공통코드 목록 조회
	 * </pre>
	 *
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 6.
	 * @Method Name : retrieveCmmnCodeList
	 * @param request
	 * @param response
	 * @param cmmnCodeVO
	 * @param model
	 * @return - 사용자 정의 view getCodeManageListView <br/>
	 *         - cmmnCodeList <br/>
	 *         - pagination
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveCmmnCodeList.*")
	public ModelAndView retrieveCmmnCodeList(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("cmmnCodeVO") CmmnCodeVO cmmnCodeVO,
			ModelMap model) throws Exception {

		// 정렬 기본값 설정
		cmmnCodeVO.setCondOrder(Util.isNotBlank(request.getParameter("condOrder")) ? cmmnCodeVO.getCondOrder() : getCommonDefaultVO().getCondOrder());
		cmmnCodeVO.setCondAlign(Util.isNotBlank(request.getParameter("condAlign")) ? cmmnCodeVO.getCondAlign() : getCommonDefaultVO().getCondAlign());
		
		CouragePaginationInfo pagination = new CouragePaginationInfo(cmmnCodeVO,
				getCommonDefaultVO().getPagingEnable(),
				getCommonDefaultVO().getRecordCountPerPage(),
				getCommonDefaultVO().getPageSize());
		cmmnCodeVO = (CmmnCodeVO) pagination.createCustomVo(request);

		int totalCnt = cmmnCodeManageService.selectCmmnCodeListTotCnt(cmmnCodeVO);
		List<CmmnCodeVO> cmmnCodeList = cmmnCodeManageService.selectCmmnCodeList(cmmnCodeVO);

		pagination.setTotalRecordCount(totalCnt);

		model.addAttribute("cmmnCodeList", cmmnCodeList);
		model.addAttribute("pagination", pagination);
		WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_SUCCESS);

		return new ModelAndView(getCodeManageListView(), model);
	}

	/**
	 * <pre>
	 * 1. 개요 : 공통코드 상세정보 조회
	 * </pre>
	 *
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 8.
	 * @Method Name : retrieveCmmnCodeDetail
	 * @param request
	 * @param response
	 * @param cmmnCodeVO
	 * @param model
	 * @return - 사용자 정의 view getCodeManageDetailView <br/>
	 *         - cmmnCodeVO
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveCmmnCodeDetail.*")
	public ModelAndView retrieveCmmnCodeDetail(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("cmmnCodeVO") CmmnCodeVO cmmnCodeVO,
			ModelMap model) throws Exception {
		CmmnCodeVO resultVO = cmmnCodeManageService.selectCmmnCodeDetail(cmmnCodeVO);
		BeanUtils.copyCondProperties(cmmnCodeVO, resultVO);

		model.addAttribute("cmmnCodeVO", resultVO);
		WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_SUCCESS);

		return new ModelAndView(getCodeManageDetailView(), model);
	}

	/**
	 * <pre>
	 * 1. 개요 : 공통코드 등록정보 조회
	 * </pre>
	 *
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 11.
	 * @Method Name : insertCmmnCode
	 * @param request
	 * @param response
	 * @param cmmnCodeVO
	 * @param model
	 * @return - 사용자 정의 view getCodeManageCreateView <br/>
	 *         - cmmnCodeVO
	 * @throws Exception
	 */
	@RequestMapping(value = "createCmmnCode.*")
	public ModelAndView createCmmnCode(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("cmmnCodeVO") CmmnCodeVO cmmnCodeVO,
			ModelMap model) throws Exception {

		cmmnCodeVO.setMode(CommonDefaultVO.MODE_CREATE);
		WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_SUCCESS);

		return new ModelAndView(getCodeManageCreateView(), model);
	}

	/**
	 * <pre>
	 * 1. 개요 : 공통코드 등록
	 * </pre>
	 *
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 11.
	 * @Method Name : insertCmmnCodeProc
	 * @param request
	 * @param response
	 * @param cmmnCodeVO
	 * @param bindingResult
	 * @param model
	 * @return - redirectView (retrieveCmmnCodeDetail) <br/>
	 *         - parameters [codeId, actionStatus]
	 * @throws Exception
	 */
	@RequestMapping(value = "createCmmnCodeProc.*")
	public ModelAndView createCmmnCodeProc(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("cmmnCodeVO") CmmnCodeVO cmmnCodeVO, BindingResult bindingResult, ModelMap model) throws Exception {

		// ServerSide Validation Check
		beanValidator.validate(cmmnCodeVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("validateCheck", "error");
			model.addAttribute("cmmnCodeVO", cmmnCodeVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);
			return new ModelAndView(getCodeManageCreateView(), model);
		}

		// 코드 중복성 검사
		CmmnCodeVO duplicateVO = cmmnCodeManageService.selectCmmnCodeDetail(cmmnCodeVO);
		if (duplicateVO != null) {
			WebUtils.setActionMessage(request, egovMessageSource.getMessage("cmmnDetailCodeVO.ordr.msg.duplicate"));
			model.addAttribute("cmmnCodeVO", cmmnCodeVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);
			return new ModelAndView(getCodeManageCreateView(), model);
		}

		BeanUtils.getAttributeEmptyToNull(cmmnCodeVO);
		cmmnCodeVO.setFrstRegisterId(WebUtils.getUserUniqID(request));
		cmmnCodeVO.setJsessionId(WebUtils.getSessionId(request));
		cmmnCodeManageService.insertCmmnCode(cmmnCodeVO);

		// view page 설정
		String params = "?codeId=" + cmmnCodeVO.getCodeId() + "&" + WebUtils.ACTION_STATUS_PARAM + "=" + WebUtils.ACTION_STATUS_SUCCESS;
		String returnUrl = "retrieveCmmnCodeDetail." + WebUtils.getUrlExtension(request);

		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl + params);

		return new ModelAndView(redirectView);
	}

	/**
	 * <pre>
	 * 1. 개요 : 공통코드 수정정보 조회
	 * </pre>
	 *
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 12.
	 * @Method Name : updateCmmdCode
	 * @param request
	 * @param response
	 * @param cmmnCodeVO
	 * @param model
	 * @return - 사용자 정의 view getCodeManageModifyView <br/>
	 *         - cmmnCodeVO
	 * @throws Exception
	 */
	@RequestMapping(value = "modifyCmmnCode.*")
	public ModelAndView modifyCmmnCode(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("cmmnCodeVO") CmmnCodeVO cmmnCodeVO,
			ModelMap model) throws Exception {

		CmmnCodeVO resultVO = cmmnCodeManageService.selectCmmnCodeDetail(cmmnCodeVO);
		BeanUtils.copyCondProperties(cmmnCodeVO, resultVO);
		resultVO.setMode(CommonDefaultVO.MODE_MODIFY);

		model.addAttribute("cmmnCodeVO", resultVO);
		WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_SUCCESS);

		return new ModelAndView(getCodeManageModifyView(), model);
	}

	/**
	 * <pre>
	 * 1. 개요 : 공통코드 수정
	 * </pre>
	 *
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 12.
	 * @Method Name : updateCmmnCodeProc
	 * @param request
	 * @param response
	 * @param cmmnCodeVO
	 * @param bindingResult
	 * @param model
	 * @return - redirectView (retrieveCmmnCodeDetail) <br/>
	 *         - parameters [codeId, actionStatus, KeepCondition]
	 * @throws Exception
	 */
	@RequestMapping(value = "modifyCmmnCodeProc.*")
	public ModelAndView modifyCmmnCodeProc(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("cmmnCodeVO") CmmnCodeVO cmmnCodeVO, BindingResult bindingResult, ModelMap model) throws Exception {
		// ServerSide Validation Check
		beanValidator.validate(cmmnCodeVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("validateCheck", "error");
			model.addAttribute("cmmnCodeVO", cmmnCodeVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);
			return new ModelAndView(getCodeManageCreateView(), model);
		}

		BeanUtils.getAttributeEmptyToNull(cmmnCodeVO);
		cmmnCodeVO.setLastUpdusrId(WebUtils.getUserUniqID(request));
		cmmnCodeVO.setJsessionId(WebUtils.getSessionId(request));
		cmmnCodeManageService.updateCmmnCode(cmmnCodeVO);

		// view page 설정
		String condParams = BeanUtils.getConditionParameter(cmmnCodeVO);
		String params = "?codeId=" + cmmnCodeVO.getCodeId() + "&" + WebUtils.ACTION_STATUS_PARAM + "=" + WebUtils.ACTION_STATUS_SUCCESS + (condParams.equals("") ? "" : "&" + condParams);
		String returnUrl = "retrieveCmmnCodeDetail." + WebUtils.getUrlExtension(request);

		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl + params);

		return new ModelAndView(redirectView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 공통코드 엑셀 일괄등록
	 * </pre>
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 20.
	 * @Method Name : insertCmmnCodeExcelUpload
	 * @param request
	 * @param response
	 * @param excelRegistVO
	 * @param model
	 * @return - 사용자 정의 view getExcelLogView <br/>
	 *         - excelLogVO
	 * @throws Exception
	 */
	@RequestMapping(value = "createCmmnCodeExcelUpload.*")
	public ModelAndView insertCmmnCodeExcelUpload(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("excelLogVO") ExcelLogVO excelRegistVO, ModelMap model) throws Exception {

		// 첨부파일 정보 존재여부 확인
		if (!Util.isNotBlank(excelRegistVO.getExcelUploadFile())) {
			WebUtils.setActionMessage(request, egovMessageSource.getMessage("cmmnCodeVO.msg.excelNotFound"));
			model.addAttribute("excelRegistVO", excelRegistVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);
			return new ModelAndView(getExcelLogView(), model);
		}

		excelRegistVO.setRegisterId(WebUtils.getUserUniqID(request));
		excelRegistVO.setSessionId(WebUtils.getSessionId(request));
		cmmnCodeManageService.insertCmmnCodeExcelUpload(excelRegistVO);
		WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_SUCCESS);

		return new ModelAndView(getExcelLogView(), model);
	}
}