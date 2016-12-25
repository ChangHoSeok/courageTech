
package kr.pe.courage.tech.das.log.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.pe.courage.common.exception.SQLiteBusyException;
import kr.pe.courage.common.web.utils.WebUtils;
import kr.pe.courage.tech.das.log.service.LogFileRceptService;
import kr.pe.courage.tech.das.log.service.LogFileRceptVO;
import kr.pe.courage.tech.das.log.service.impl.LogFileRceptProcess;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.service.Globals;
import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.CouragePaginationInfo;

/**
 * <pre>
 * kr.pe.courage.tech.das.log.web
 * LogFileRceptController.java
 * </pre>
 * 
 * @Author : ChangHo Seok
 * @Date : 2016. 7. 22.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 7. 22., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
@Controller("logFileRceptController")
@RequestMapping("/das/log/*")
public class LogFileRceptController {
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	@Resource(name = "logFileRceptService")
	private LogFileRceptService logFileRceptService;
	
	@Resource(name = "courageLogFileRceptIdGnrService")
	private EgovIdGnrService idgenService;
	
	@Resource(name = "logFileRceptProcess")
	private LogFileRceptProcess logFileRceptProcess;
	
	@Value("#{dasView['formLogFileRceptView']}")
	private String formLogFileRceptView;

	@Value("#{dasView['formLogFileRceptPopupView']}")
	private String formLogFileRceptPopupView;

	@Value("#{dasView['logFileRceptListView']}")
	private String logFileRceptListView;

	@Value("#{dasView['logFileRceptCreatePopupView']}")
	private String logFileRceptCreatePopupView;

	@Value("#{dasView['logFileRceptDetailPopupView']}")
	private String logFileRceptDetailPopupView;

	@Value("#{pageConfig['logFileRcept.page.recordCount']}")
	private int recordCount;

	@Value("#{pageConfig['logFileRcept.page.size']}")
	private int pageSize;

	@Value("#{pageConfig['logFileRcept.page.enable']}")
	private String pageEnable;

	/**
	 * <pre>
	 * 1. 개요 : 로그파일접수 메인 Form
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 7. 22.
	 * @Method Name : formLogFileRcept
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "formLogFileRcept.*")
	public ModelAndView formLogFileRcept() throws Exception {
		return new ModelAndView(formLogFileRceptView);
	}

	/**
	 * <pre>
	 * 1. 개요 : 로그파일접수 팝업 메인 Form
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 8. 10.
	 * @Method Name : formLogFileRceptPopup
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "formLogFileRceptPopup.*")
	public ModelAndView formLogFileRceptPopup() throws Exception {
		return new ModelAndView(formLogFileRceptPopupView);
	}

	/**
	 * <pre>
	 * 1. 개요 : 로그파일접수 목록 조회
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 8. 9.
	 * @Method Name : retrieveLogFileRceptList
	 * @param request
	 * @param logFileRceptVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveLogFileRceptList.*")
	public ModelAndView retrieveLogFileRceptList(HttpServletRequest request, LogFileRceptVO logFileRceptVO, ModelMap model)
			throws Exception {
		CouragePaginationInfo pagination = new CouragePaginationInfo(logFileRceptVO, pageEnable, recordCount, pageSize);
		logFileRceptVO = (LogFileRceptVO) pagination.createCustomVo(request);

		pagination.setTotalRecordCount(logFileRceptService.selectLogFileRceptListCount(logFileRceptVO));
		List<LogFileRceptVO> logFileRceptList = null;

		if (pagination.getTotalRecordCount() > 0) {
			logFileRceptList = logFileRceptService.selectLogFileRceptList(logFileRceptVO);
		}

		model.addAttribute("pagination", pagination);
		model.addAttribute("logFileRceptList", logFileRceptList);

		return new ModelAndView(logFileRceptListView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 로그파일접수 상세정보 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 8. 23.
	 * @Method Name : retrieveLogFileRceptDetailPopup
	 * @param logFileRceptVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveLogFileRceptDetailPopup.*")
	public ModelAndView retrieveLogFileRceptDetailPopup(LogFileRceptVO logFileRceptVO, ModelMap model) throws Exception {
		
		LogFileRceptVO resultVO = logFileRceptService.selectLogFileRceptDetail(logFileRceptVO);
		model.addAttribute("logFileRceptVO", resultVO);
		
		return new ModelAndView(logFileRceptDetailPopupView);
	}

	/**
	 * <pre>
	 * 1. 개요 : 로그파일접수 등록 화면
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 8. 10.
	 * @Method Name : createLogFileRceptPopup
	 * @param logFileRceptVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createLogFileRceptPopup.*")
	public ModelAndView createLogFileRceptPopup(LogFileRceptVO logFileRceptVO) throws Exception {
		logFileRceptVO.setMode(LogFileRceptVO.MODE_CREATE);

		return new ModelAndView(logFileRceptCreatePopupView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 로그파일접수 등록 처리
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 8. 11.
	 * @Method Name : createLogFileRceptProcPopup
	 * @param request
	 * @param logFileRceptVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createLogFileRceptProcPopup.*")
	public ModelAndView createLogFileRceptProcPopup(HttpServletRequest request, LogFileRceptVO logFileRceptVO, BindingResult bindingResult,
			ModelMap model) throws Exception {
		
		beanValidator.validate(logFileRceptVO, bindingResult);
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("validateCheck", "error");
			model.addAttribute("logFileRceptVO", logFileRceptVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);

			return new ModelAndView(logFileRceptCreatePopupView, model);
		}
		
		try {
			logFileRceptVO.setRceptId(idgenService.getNextStringId());
			logFileRceptVO.setFrstRegisterId(WebUtils.getUserUniqID(request));
			logFileRceptVO.setLastUpdusrId(logFileRceptVO.getFrstRegisterId());
			logFileRceptVO.setJsessionId(WebUtils.getSessionId(request));
			
			logFileRceptService.insertLogFileRcept(logFileRceptVO);
			
			// 로그파일 분석 및 상세정보 등록 프로세스 실행
			logFileRceptProcess.setLogFileRceptVO(logFileRceptVO);
			Thread thread = new Thread(logFileRceptProcess);
			thread.start();
		} catch(FdlException e) {
			e.printStackTrace();
			
			// SQLite MultiTransaction 미지원으로 인한 오류 처리
			if (Globals.DB_TYPE.toUpperCase().equals("SQLITE")) {
				throw new SQLiteBusyException("DB Busy...");
			}
		}
		
		model.addAttribute(WebUtils.ACTION_STATUS_PARAM, WebUtils.ACTION_STATUS_SUCCESS);
		
		return new ModelAndView(logFileRceptCreatePopupView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 로그파일접수 정보 삭제
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 8. 24.
	 * @Method Name : deleteLogFileRcept
	 * @param request
	 * @param logFileRceptVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteLogFileRcept.*")
	public ModelAndView deleteLogFileRcept(HttpServletRequest request, LogFileRceptVO logFileRceptVO) throws Exception {
		
		logFileRceptService.deleteLogFileRcept(logFileRceptVO);
		
		String params = "?" + WebUtils.ACTION_STATUS_FAILED + "=" + WebUtils.ACTION_STATUS_SUCCESS;
		String returnUrl = "retrieveLogFileRceptList." + WebUtils.getUrlExtension(request);
		
		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl + params);
		
		return new ModelAndView(redirectView);
	}
}
