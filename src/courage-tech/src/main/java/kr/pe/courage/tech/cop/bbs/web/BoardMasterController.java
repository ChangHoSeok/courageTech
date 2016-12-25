
package kr.pe.courage.tech.cop.bbs.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.pe.courage.common.utils.BeanUtils;
import kr.pe.courage.common.vo.JxlsParam;
import kr.pe.courage.common.web.utils.WebUtils;
import kr.pe.courage.common.web.view.JxlsView;
import kr.pe.courage.tech.cop.bbs.exception.ExistsNTTException;
import kr.pe.courage.tech.cop.bbs.service.BoardMasterService;
import kr.pe.courage.tech.cop.bbs.service.BoardMasterVO;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.CouragePaginationInfo;

/**
 * <pre>
 * kr.pe.courage.tech.cop.bbs.web
 * BoardMasterController.java
 * </pre>
 * 
 * @Author : ChangHo Seok
 * @Date : 2016. 5. 20.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 5. 20., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
@Controller("boardMasterController")
@RequestMapping(value = "/cop/bbs/*")
public class BoardMasterController {
	@Resource(name = "egovBBSMstrIdGnrService")
	private EgovIdGnrService idGenService;

	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	@Resource(name = "boardMasterService")
	private BoardMasterService boardMasterService;

	@Value("#{copView['formBoardMasterView']}")
	private String formBoardMasterView;

	@Value("#{copView['boardMasterListView']}")
	private String boardMasterListView;
	
	@Value("#{copView['boardMasterDetailView']}")
	private String boardMasterDetailView;

	@Value("#{copView['boardMasterCreateView']}")
	private String boardMasterCreateView;

	@Value("#{copView['boardMasterModifyView']}")
	private String boardMasterModifyView;

	@Value("#{pageConfig['boardMaster.page.recordCount']}")
	private int recordCount;

	@Value("#{pageConfig['boardMaster.page.size']}")
	private int pageSize;

	@Value("#{pageConfig['boardMaster.page.enable']}")
	private String pageEnable;

	/**
	 * <pre>
	 * 1. 개요 : 게시판 마스터 메인 form
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 5. 20.
	 * @Method Name : formBoardMaster
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "formBoardMaster.*")
	public ModelAndView formBoardMaster() throws Exception {
		return new ModelAndView(formBoardMasterView);
	}

	/**
	 * <pre>
	 * 1. 개요 : 게시판 마스터 목록 조회
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 5. 23.
	 * @Method Name : retrieveBoardMasterList
	 * @param request
	 * @param boardMasterVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveBoardMasterList.*")
	public ModelAndView retrieveBoardMasterList(HttpServletRequest request, BoardMasterVO boardMasterVO, ModelMap model) throws Exception {
		CouragePaginationInfo pagination = new CouragePaginationInfo(boardMasterVO, pageEnable, recordCount, pageSize);
		boardMasterVO = (BoardMasterVO) pagination.createCustomVo(request);

		pagination.setTotalRecordCount(boardMasterService.selectBoardMasterListCount(boardMasterVO));
		List<BoardMasterVO> boardMasterList = null;

		if (pagination.getTotalRecordCount() > 0) {
			boardMasterList = boardMasterService.selectBoardMasterList(boardMasterVO);
		}

		model.addAttribute("pagination", pagination);
		model.addAttribute("boardMasterList", boardMasterList);

		return new ModelAndView(boardMasterListView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 게시판 마스터 정보 상세 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 5. 26.
	 * @Method Name : retrieveBoardMasterDetail
	 * @param boardMasterVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveBoardMasterDetail.*")
	public ModelAndView retrieveBoardMasterDetail(BoardMasterVO boardMasterVO, ModelMap model) throws Exception {
		BoardMasterVO resultVO = boardMasterService.selectBoardMaster(boardMasterVO);
		BeanUtils.copyCondProperties(boardMasterVO, resultVO);
		model.addAttribute("boardMasterVO", resultVO);
		
		return new ModelAndView(boardMasterDetailView);
	}

	/**
	 * <pre>
	 * 1. 개요 : 게시판 마스터 등록 화면
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 5. 25.
	 * @Method Name : createBoardMaster
	 * @param boardMasterVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createBoardMaster.*")
	public ModelAndView createBoardMaster(BoardMasterVO boardMasterVO) throws Exception {
		boardMasterVO.setMode(BoardMasterVO.MODE_CREATE);

		return new ModelAndView(boardMasterCreateView);
	}

	/**
	 * <pre>
	 * 1. 개요 : 게시판 마스터 정보 등록 처리
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 5. 26.
	 * @Method Name : createBoardMasterProc
	 * @param request
	 * @param boardMasterVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createBoardMasterProc.*")
	public ModelAndView createBoardMasterProc(HttpServletRequest request, BoardMasterVO boardMasterVO, BindingResult bindingResult,
			ModelMap model) throws Exception {
		beanValidator.validate(boardMasterVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("validateCheck", "error");
			model.addAttribute("boardMasterVO", boardMasterVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);

			return new ModelAndView(boardMasterCreateView, model);
		}

		boardMasterVO.setBbsId(idGenService.getNextStringId());
		boardMasterVO.setFrstRegisterId(WebUtils.getUserUniqID(request));
		boardMasterVO.setLastUpdusrId(boardMasterVO.getFrstRegisterId());
		boardMasterService.insertBoardMaster(boardMasterVO);

		String params = "?bbsId=" + boardMasterVO.getBbsId() + "&" + WebUtils.ACTION_STATUS_PARAM + "=" + WebUtils.ACTION_STATUS_SUCCESS;
		String returnUrl = "retrieveBoardMasterDetail." + WebUtils.getUrlExtension(request);

		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl + params);

		return new ModelAndView(redirectView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 게시판 마스터 수정 화면
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 5. 26.
	 * @Method Name : modifyBoardMaster
	 * @param boardMasterVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modifyBoardMaster.*")
	public ModelAndView modifyBoardMaster(BoardMasterVO boardMasterVO, ModelMap model) throws Exception {
		BoardMasterVO resultVO = boardMasterService.selectBoardMaster(boardMasterVO);
		BeanUtils.copyCondProperties(boardMasterVO, resultVO);
		resultVO.setMode(BoardMasterVO.MODE_MODIFY);
		
		model.addAttribute("boardMasterVO", resultVO);
		
		return new ModelAndView(boardMasterModifyView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 게시판 마스터 정보 수정 처리
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 5. 27.
	 * @Method Name : modifyBoardMasterProc
	 * @param request
	 * @param boardMasterVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modifyBoardMasterProc.*")
	public ModelAndView modifyBoardMasterProc(HttpServletRequest request, BoardMasterVO boardMasterVO, BindingResult bindingResult, ModelMap model) throws Exception {
		beanValidator.validate(boardMasterVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("validateCheck", "error");
			model.addAttribute("boardMasterVO", boardMasterVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);

			return new ModelAndView(boardMasterModifyView, model);
		}

		boardMasterVO.setLastUpdusrId(WebUtils.getUserUniqID(request));
		boardMasterService.updateBoardMaster(boardMasterVO);
		
		String condParams = BeanUtils.getConditionParameter(boardMasterVO);
		String params = "?bbsId=" + boardMasterVO.getBbsId() + "&" + WebUtils.ACTION_STATUS_PARAM + "=" + WebUtils.ACTION_STATUS_SUCCESS
				+ (StringUtils.isEmpty(condParams) ? "" : "&" + condParams);
		String returnUrl = "retrieveBoardMasterDetail." + WebUtils.getUrlExtension(request);

		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl + params);

		return new ModelAndView(redirectView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 게시판 마스터 정보 삭제
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 5. 27.
	 * @Method Name : deleteBoardMaster
	 * @param request
	 * @param boardMasterVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteBoardMaster.*")
	public ModelAndView deleteBoardMaster(HttpServletRequest request, BoardMasterVO boardMasterVO) throws Exception {
		
		String params = "?" + WebUtils.ACTION_STATUS_PARAM + "=" + WebUtils.ACTION_STATUS_SUCCESS;
		String returnUrl = "retrieveBoardMasterList." + WebUtils.getUrlExtension(request);
		
		try {
			boardMasterService.deleteBoardMaster(boardMasterVO);
		} catch (ExistsNTTException e) {
			String actionMessage = WebUtils.getActionMessageParam("게시물이 존재하여 삭제할 수 없습니다.");
			String condParams = BeanUtils.getConditionParameter(boardMasterVO);
			params = "?bbsId=" + boardMasterVO.getBbsId() + "&" + WebUtils.ACTION_STATUS_PARAM + "=" + WebUtils.ACTION_STATUS_FAILED
					+ (StringUtils.isEmpty(condParams) ? "" : "&" + condParams) + "&" + actionMessage;
			returnUrl = "retrieveBoardMasterDetail." + WebUtils.getUrlExtension(request);
		}
		
		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl + params);
		
		return new ModelAndView(redirectView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 게시판 관리 목록 엑셀 다운로드
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 5. 30.
	 * @Method Name : downloadBoardMasterExcel
	 * @param boardMasterVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "downloadBoardMasterExcel.*")
	public ModelAndView downloadBoardMasterExcel(BoardMasterVO boardMasterVO, ModelMap model) throws Exception {
		boardMasterVO.setPagingEnable(BoardMasterVO.PAGING_ENABLE_OFF);
		int totalRecordCount = boardMasterService.selectBoardMasterListCount(boardMasterVO);
		
		List<BoardMasterVO> boardMasterList = null;
		
		if (totalRecordCount > 0) {
			boardMasterList = boardMasterService.selectBoardMasterList(boardMasterVO);
		}
		
		JxlsParam jxlsParam = new JxlsParam(JxlsParam.EXCEL_EXT_XLS, "boardMasterExcel", "게시판 관리 목록", "게시판 관리 목록", totalRecordCount);
		
		model.addAttribute("list", boardMasterList);
		model.addAttribute("jxlsParam", jxlsParam);
		
		return new ModelAndView(JxlsView.VIEW_NAME);
	}
}