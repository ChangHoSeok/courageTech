
package kr.pe.courage.tech.das.log.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.pe.courage.common.web.utils.WebUtils;
import kr.pe.courage.tech.das.log.service.LogClGroupService;
import kr.pe.courage.tech.das.log.service.LogClGroupVO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.ptl.mvc.tags.ui.pagination.CouragePaginationInfo;

/**
 * <pre>
 * kr.pe.courage.tech.das.log.web
 * LogClGroupController.java
 * </pre>
 * 
 * @Author : ChangHo Seok
 * @Date : 2016. 7. 18.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 7. 18., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
@Controller("logClGroupController")
@RequestMapping("/das/log/*")
public class LogClGroupController {
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	@Resource(name = "logClGroupService")
	private LogClGroupService logClGroupService;
	
	@Value("#{dasView['formLogClGroupView']}")
	private String formLogClGroupView;

	@Value("#{dasView['logClGroupListView']}")
	private String logClGroupListView;

	@Value("#{pageConfig['logClGroup.page.recordCount']}")
	private int recordCount;

	@Value("#{pageConfig['logClGroup.page.size']}")
	private int pageSize;

	@Value("#{pageConfig['logClGroup.page.enable']}")
	private String pageEnable;
	
	/**
	 * <pre>
	 * 1. 개요 : 로그분류그룹 메인 form
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 18.
	 * @Method Name : formLogClGroup
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "formLogClGroup.*")
	public ModelAndView formLogClGroup() throws Exception {
		return new ModelAndView(formLogClGroupView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 로그분류그룹 목록 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 18.
	 * @Method Name : retrieveLogClGroupList
	 * @param request
	 * @param logClGroupVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveLogClGroupList.*")
	public ModelAndView retrieveLogClGroupList(HttpServletRequest request, LogClGroupVO logClGroupVO, ModelMap model) throws Exception {
		CouragePaginationInfo pagination = new CouragePaginationInfo(logClGroupVO, pageEnable, recordCount, pageSize);
		logClGroupVO = (LogClGroupVO) pagination.createCustomVo(request);

		pagination.setTotalRecordCount(logClGroupService.selectLogClGroupListCount(logClGroupVO));
		List<LogClGroupVO> logClGroupList = null;

		if (pagination.getTotalRecordCount() > 0) {
			logClGroupList = logClGroupService.selectLogClGroupList(logClGroupVO);
		}

		model.addAttribute("pagination", pagination);
		model.addAttribute("logClGroupList", logClGroupList);
		
		return new ModelAndView(logClGroupListView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 로그분류그룹 상세 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 19.
	 * @Method Name : retrieveLogClGroupDetial
	 * @param logClGroupVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveLogClGroupDetail.*")
	public @ResponseBody Map<String, Object> retrieveLogClGroupDetial(LogClGroupVO logClGroupVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		logClGroupVO = logClGroupService.selectLogClGroupDetail(logClGroupVO);
		resultMap.put("logClGroupVO", logClGroupVO);
		
		return resultMap;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 로그분류그룹 정보 등록
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 19.
	 * @Method Name : createLogClGroupProc
	 * @param logClGroupVO
	 * @param bindingResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createLogClGroupProc.*")
	public @ResponseBody Map<String, Object> createLogClGroupProc(LogClGroupVO logClGroupVO, BindingResult bindingResult) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		beanValidator.validate(logClGroupVO, bindingResult);
		
		if (bindingResult.hasErrors()) {
			resultMap.put(WebUtils.ACTION_STATUS_PARAM, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);
			resultMap.put(WebUtils.ACTION_MESSAGE, "입력값 검증 오류가 발생되었습니다.\n이력값을 확인하세요.");
			
			return resultMap;
		}
		
		try {
			logClGroupService.insertLogClgroup(logClGroupVO);
			resultMap.put(WebUtils.ACTION_STATUS_PARAM, WebUtils.ACTION_STATUS_SUCCESS);
		} catch (EgovBizException e) {
			e.printStackTrace();
			resultMap.put(WebUtils.ACTION_STATUS_PARAM, WebUtils.ACTION_STATUS_FAILED);
			resultMap.put(WebUtils.ACTION_MESSAGE, "저장처리 중 오류가 발생되었습니다.");
		}
		
		return resultMap;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 로그분류그룹 정보 수정
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 20.
	 * @Method Name : modifyLogClGroupProc
	 * @param logClGroupVO
	 * @param bindingResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modifyLogClGroupProc.*")
	public @ResponseBody Map<String, Object> modifyLogClGroupProc(LogClGroupVO logClGroupVO, BindingResult bindingResult) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		beanValidator.validate(logClGroupVO, bindingResult);
		
		if (bindingResult.hasErrors()) {
			resultMap.put(WebUtils.ACTION_STATUS_PARAM, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);
			resultMap.put(WebUtils.ACTION_MESSAGE, "입력값 검증 오류가 발생되었습니다.\n이력값을 확인하세요.");
			
			return resultMap;
		}
		
		try {
			logClGroupService.updateLogClGroup(logClGroupVO);
			resultMap.put(WebUtils.ACTION_STATUS_PARAM, WebUtils.ACTION_STATUS_SUCCESS);
		} catch (EgovBizException e) {
			e.printStackTrace();
			resultMap.put(WebUtils.ACTION_STATUS_PARAM, WebUtils.ACTION_STATUS_FAILED);
			resultMap.put(WebUtils.ACTION_MESSAGE, "저장처리 중 오류가 발생되었습니다.");
		}
		
		return resultMap;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 로그분류그룹 정보 삭제
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 20.
	 * @Method Name : deleteLogClGroup
	 * @param logClGroupVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteLogClGroup.*")
	public @ResponseBody Map<String, Object> deleteLogClGroup(LogClGroupVO logClGroupVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			logClGroupService.deleteLogClGroup(logClGroupVO);
			resultMap.put(WebUtils.ACTION_STATUS_PARAM, WebUtils.ACTION_STATUS_SUCCESS);
		} catch (EgovBizException e) {
			e.printStackTrace();
			resultMap.put(WebUtils.ACTION_STATUS_PARAM, WebUtils.ACTION_STATUS_FAILED);
			resultMap.put(WebUtils.ACTION_MESSAGE, "삭제처리 중 오류가 발생되었습니다.");
		}
		
		return resultMap;
	}
}
