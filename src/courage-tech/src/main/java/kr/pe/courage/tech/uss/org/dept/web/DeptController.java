package kr.pe.courage.tech.uss.org.dept.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rte.ptl.mvc.tags.ui.pagination.CouragePaginationInfo;
import kr.pe.courage.common.utils.BeanUtils;
import kr.pe.courage.common.vo.JxlsParam;
import kr.pe.courage.common.web.helper.ITreeHelper;
import kr.pe.courage.common.web.utils.WebUtils;
import kr.pe.courage.tech.uss.org.dept.service.DeptBatchVO;
import kr.pe.courage.tech.uss.org.dept.service.DeptService;
import kr.pe.courage.tech.uss.org.dept.service.DeptVO;

/**
 * <pre>
 * kr.pe.courage.tech.uss.org.dept.web
 * DeptController.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 9. 29.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 9. 29., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
@Controller("deptController")
@RequestMapping("/uss/org/dept/*")
@SessionAttributes("errorList")
public class DeptController {
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
	@Resource(name = "deptService")
	private DeptService deptService;
	
	@Resource(name = "defaultTreeHelper")
	private ITreeHelper treeHelper;
	
	@Value("#{ussView['formDeptView']}")
	private String formDeptView;

	@Value("#{ussView['deptTabView']}")
	private String deptTabView;
	
	@Value("#{ussView['deptListView']}")
	private String deptListView;

	@Value("#{ussView['deptTreeView']}")
	private String deptTreeView;

	@Value("#{ussView['deptDetailView']}")
	private String deptDetailView;

	@Value("#{ussView['deptCreateView']}")
	private String deptCreateView;

	@Value("#{ussView['deptModifyView']}")
	private String deptModifyView;
	
	@Value("#{ussView['formDeptPopupView']}")
	private String formDeptPopupView;

	@Value("#{ussView['deptPopupView']}")
	private String deptPopupView;

	@Value("#{ussView['deptCreateBatchPopupView']}")
	private String deptCreateBatchPopupView;

	@Value("#{ussView['deptBatchErrorListPopupView']}")
	private String deptBatchErrorListPopupView;
	
	@Value("#{pageConfig['dept.page.recordCount']}")
	private int recordCount;

	@Value("#{pageConfig['dept.page.size']}")
	private int pageSize;

	@Value("#{pageConfig['dept.page.enable']}")
	private String pageEnable;
	
	@ModelAttribute("errorList")
	private List<DeptVO> errorList() {
		return new ArrayList<DeptVO>();
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 부서관리 메인
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 9. 29.
	 * @Method Name : formDept
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("formDept.*")
	public ModelAndView formDept() throws Exception {
		return new ModelAndView(formDeptView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 부서관리 탭 화면 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 11. 24.
	 * @Method Name : retrieveDeptTabView
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("retrieveDeptTabView.*")
	public ModelAndView retrieveDeptTabView() throws Exception {
		return new ModelAndView(deptTabView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 부서관리 목록 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 9. 29.
	 * @Method Name : retrieveDeptList
	 * @param request
	 * @param deptVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("retrieveDeptList.*")
	public ModelAndView retrieveDeptList(HttpServletRequest request, DeptVO deptVO, ModelMap model) throws Exception {
		CouragePaginationInfo pagination = new CouragePaginationInfo(deptVO, pageEnable, recordCount, pageSize);
		deptVO = (DeptVO) pagination.createCustomVo(request);
		
		pagination.setTotalRecordCount(deptService.selectDeptListCount(deptVO));
		List<DeptVO> deptList = null;

		if (pagination.getTotalRecordCount() > 0) {
			deptList = deptService.selectDeptList(deptVO);
		}

		model.addAttribute("pagination", pagination);
		model.addAttribute("deptList", deptList);
		
		return new ModelAndView(deptListView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 부서관리 정보 조회 (트리형식)
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 11. 24.
	 * @Method Name : retrieveDeptTree
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("retrieveDeptTree.*")
	public ModelAndView retrieveDeptTree() throws Exception {
		return new ModelAndView(deptTreeView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 부서관리 정보 상세조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 10. 4.
	 * @Method Name : retrieveDeptDetail
	 * @param request
	 * @param deptVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("retrieveDeptDetail.*")
	public ModelAndView retrieveDeptDetail(HttpServletRequest request, DeptVO deptVO, ModelMap model) throws Exception {
		
		DeptVO resultVO = deptService.selectDeptDetail(deptVO);
		BeanUtils.copyCondProperties(deptVO, resultVO);
		
		model.addAttribute("deptVO", resultVO);
		
		return new ModelAndView(deptDetailView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 부서관리 정보 등록 화면
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 9. 29.
	 * @Method Name : createDept
	 * @param deptVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("createDept.*")
	public ModelAndView createDept(DeptVO deptVO) throws Exception {
		deptVO.setMode(DeptVO.MODE_CREATE);
		
		return new ModelAndView(deptCreateView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 부서관리 정보 등록 처리
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 10. 4.
	 * @Method Name : createDeptProc
	 * @param request
	 * @param deptVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("createDeptProc.*")
	public ModelAndView createDeptProc(HttpServletRequest request, DeptVO deptVO, BindingResult bindingResult, ModelMap model) throws Exception {
		
		beanValidator.validate(deptVO, bindingResult);
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("validateCheck", "error");
			model.addAttribute("deptVO", deptVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);
			
			return new ModelAndView(deptCreateView, model);
		}
		
		deptService.createDept(deptVO);
		
		String params = "?" + WebUtils.ACTION_STATUS_PARAM + "=" + WebUtils.ACTION_STATUS_SUCCESS;
		String returnUrl = "retrieveDeptDetail." + WebUtils.getUrlExtension(request);
		
		if (deptVO.getSaveLaterView().equals(DeptVO.SAVE_LATER_VIEW_DETAIL)) {
			params += "&deptCode=" + deptVO.getDeptCode();
		} else if (deptVO.getSaveLaterView().equals(DeptVO.SAVE_LATER_VIEW_LIST)) {
			returnUrl = "retrieveDeptList." + WebUtils.getUrlExtension(request);
		} else if (deptVO.getSaveLaterView().equals(DeptVO.SAVE_LATER_VIEW_CREATE)) {
			returnUrl = "createDept." + WebUtils.getUrlExtension(request);
		} else {
			params += "&deptCode=" + deptVO.getDeptCode();
		}
		
		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl + params);
		
		return new ModelAndView(redirectView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 부서관리 정보 수정 화면
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 10. 4.
	 * @Method Name : modifyDept
	 * @param request
	 * @param deptVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("modifyDept.*")
	public ModelAndView modifyDept(HttpServletRequest request, DeptVO deptVO, ModelMap model) throws Exception {
		DeptVO resultVO = deptService.selectDeptDetail(deptVO);
		BeanUtils.copyCondProperties(deptVO, resultVO);
		
		resultVO.setMode(DeptVO.MODE_MODIFY);
		model.addAttribute("deptVO", resultVO);
		
		return new ModelAndView(deptModifyView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 부서관리 정보 수정 처리
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 10. 4.
	 * @Method Name : modifyDeptProc
	 * @param request
	 * @param deptVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("modifyDeptProc.*")
	public ModelAndView modifyDeptProc(HttpServletRequest request, DeptVO deptVO, BindingResult bindingResult, ModelMap model) throws Exception {
		beanValidator.validate(deptVO, bindingResult);
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("validateCheck", "error");
			model.addAttribute("deptVO", deptVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);
			
			return new ModelAndView(deptModifyView, model);
		}
		
		deptService.updateDept(deptVO);
		
		String condParams = BeanUtils.getConditionParameter(deptVO);
		String params = "?deptCode=" + deptVO.getDeptCode() + (StringUtils.isEmpty(condParams) ? "" : "&" + condParams);
		String returnUrl = "retrieveDeptDetail." + WebUtils.getUrlExtension(request);
		
		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl + params);
		
		return new ModelAndView(redirectView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 부서관리 정보 삭제
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 10. 4.
	 * @Method Name : deleteDept
	 * @param request
	 * @param deptVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("deleteDept.*")
	public ModelAndView deleteDept(HttpServletRequest request, DeptVO deptVO) throws Exception {
		deptService.deleteDept(deptVO);
		
		String params = "?" + WebUtils.ACTION_STATUS_FAILED + "=" + WebUtils.ACTION_STATUS_SUCCESS;
		String returnUrl = "retrieveDeptList." + WebUtils.getUrlExtension(request);
		
		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl + params);
		
		return new ModelAndView(redirectView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 부서코드 존재 유무
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 10. 4.
	 * @Method Name : isExistDeptCode
	 * @param deptVO
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("isExistDeptCode.*")
	public Map<String, Object> isExistDeptCode(DeptVO deptVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("result", deptService.isExist(deptVO));
		
		return resultMap;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 부서관리 엑셀 저장
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 10. 4.
	 * @Method Name : downloadDeptExcel
	 * @param deptVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("downloadDeptExcel.*")
	public ModelAndView downloadDeptExcel(DeptVO deptVO, ModelMap model) throws Exception {
		deptVO.setPagingEnable(DeptVO.PAGING_ENABLE_OFF);
		int totalRecordCount = deptService.selectDeptListCount(deptVO);
		List<DeptVO> deptList = null;
		
		if (totalRecordCount > 0) {
			deptList = deptService.selectDeptList(deptVO);
		}
		
		JxlsParam jxlsParam = new JxlsParam(JxlsParam.EXCEL_EXT_XLS, "deptExcel", "부서코드 목록", "부서코드 목록", totalRecordCount);
		
		model.addAttribute("list", deptList);
		model.addAttribute("jxlsParam", jxlsParam);
		
		return new ModelAndView("jxlsView");
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 부서 조회 팝업 메인
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 10. 5.
	 * @Method Name : formDeptPopup
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("formDeptPopup.*")
	public ModelAndView formDeptPopup() throws Exception {
		return new ModelAndView(formDeptPopupView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 부서 조회 팝업
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 10. 5.
	 * @Method Name : retrieveDeptPopup
	 * @param deptVO
	 * @return
	 */
	@RequestMapping("retrieveDeptPopup.*")
	public ModelAndView retrieveDeptPopup(DeptVO deptVO) {
		return new ModelAndView(deptPopupView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 부서 트리 데이터 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 10. 5.
	 * @Method Name : retrieveDeptTreeData
	 * @param deptVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("retrieveDeptTreeData.*")
	public List<Map<String, Object>> retrieveDeptTreeData(DeptVO deptVO) throws Exception {
		List<DeptVO> deptList = null;
		List<Map<String, Object>> treeMapList = null;;
		
		if (deptVO.getCondAtmbUpperDeptCode() == null) {
			deptVO.setCondAtmbUpperDeptCode("");
		}
		
		deptList = deptService.selectDeptTreeList(deptVO);
		treeMapList = new ArrayList<Map<String,Object>>();
		
		for (DeptVO treeVO : deptList) {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			Map<String, Object> metadataMap = new HashMap<String, Object>();
			
			metadataMap.put("deptCode", treeVO.getDeptCode());
			metadataMap.put("VO", treeVO);
			
			dataMap.put("metadata", metadataMap);
			
			dataMap.put("id", treeVO.getDeptCode());
			dataMap.put("title", treeVO.getLowestDeptNm());
			
			if (treeVO.getAblSe().equals("1")) {
				dataMap.put("class", "abl");
			}
			
			if (treeVO.getSubDeptCnt() > 0) {
				dataMap.put("state", "closed");
			} else {
				dataMap.put("state", "leaf");
			}
			
			treeMapList.add(dataMap);
		}
		
		return treeHelper.process(treeMapList, deptVO.getDeptCode());
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 부서 일괄등록
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 10. 9.
	 * @Method Name : createDeptBatchPopup
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("createDeptBatchPopup.*")
	public ModelAndView createDeptBatchPopup(DeptBatchVO deptBatchVO) throws Exception {
		return new ModelAndView(deptCreateBatchPopupView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 부서 일괄등록 처리
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 10. 9.
	 * @Method Name : createDeptBatchProcPopup
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("createDeptBatchProcPopup.*")
	public ModelAndView createDeptBatchProcPopup(HttpServletRequest request, DeptBatchVO deptBatchVO,
			BindingResult bindingResult, SessionStatus sessionStatus, ModelMap model) throws Exception {
		
		beanValidator.validate(deptBatchVO, bindingResult);
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("validateCheck", "error");
			model.addAttribute("deptBatchVO", deptBatchVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);
			
			return new ModelAndView(deptCreateBatchPopupView, model);
		}
		
		deptBatchVO.setJsessionId(WebUtils.getSessionId(request));
		
		List<DeptVO> errorList = deptService.createDeptBatch(deptBatchVO);
		sessionStatus.isComplete();
		
		if (errorList.size() <= 0) {
			model.addAttribute(WebUtils.ACTION_STATUS_PARAM, WebUtils.ACTION_STATUS_SUCCESS);
			
			return new ModelAndView(deptCreateBatchPopupView);
		} else {
			model.addAttribute("errorList", errorList);
			model.addAttribute(WebUtils.ACTION_STATUS_PARAM, WebUtils.ACTION_STATUS_FAILED);
			
			return new ModelAndView(deptBatchErrorListPopupView);
		}
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 일괄등록 오류 엑셀 저장
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 10. 14.
	 * @Method Name : downloadDeptBatchErrorExcel
	 * @param deptVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("downloadDeptBatchErrorExcel.*")
	public ModelAndView downloadDeptBatchErrorExcel(HttpSession session, ModelMap model) throws Exception {
		List<DeptVO> errorList = (List<DeptVO>) session.getAttribute("errorList");
		JxlsParam jxlsParam = new JxlsParam(JxlsParam.EXCEL_EXT_XLS, "deptBatchErrorExcel", "일괄등록 오류 내역", "일괄등록 오류 내역", errorList.size());
		
		model.addAttribute("list", errorList);
		model.addAttribute("jxlsParam", jxlsParam);
		
		return new ModelAndView("jxlsView");
	}
}
