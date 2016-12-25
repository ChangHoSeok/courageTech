
package kr.pe.courage.tech.cop.bbs.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.pe.courage.common.utils.BeanUtils;
import kr.pe.courage.common.utils.Util;
import kr.pe.courage.common.web.utils.WebUtils;
import kr.pe.courage.tech.cop.bbs.service.BoardAuthorService;
import kr.pe.courage.tech.cop.bbs.service.BoardAuthorVO;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rte.ptl.mvc.tags.ui.pagination.CouragePaginationInfo;

/**
 * <pre>
 * kr.pe.courage.tech.cop.bbs.web
 * BoardAuthorController.java
 * </pre>
 * 
 * @Author : ChangHo Seok
 * @Date : 2016. 7. 11.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 7. 11., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
@RequestMapping("/cop/bbs/*")
@Controller("boardAuthorController")
public class BoardAuthorController {
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	@Resource(name = "boardAuthorService")
	private BoardAuthorService boardAuthorService;

	@Value("#{copView['formBoardAuthorView']}")
	private String formBoardAuthorView;

	@Value("#{copView['boardAuthorListView']}")
	private String boardAuthorListView;

	@Value("#{pageConfig['boardAuthor.page.recordCount']}")
	private int recordCount;

	@Value("#{pageConfig['boardAuthor.page.size']}")
	private int pageSize;

	@Value("#{pageConfig['boardAuthor.page.enable']}")
	private String pageEnable;

	/**
	 * <pre>
	 * 1. 개요 : 게시판 권한관리 메인 form
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 7. 11.
	 * @Method Name : formBoardAuthor
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "formBoardAuthor.*")
	public ModelAndView formBoardAuthor() throws Exception {
		return new ModelAndView(formBoardAuthorView);
	}

	/**
	 * <pre>
	 * 1. 개요 : 게시판 권한관리 목록 조회
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 7. 11.
	 * @Method Name : retrieveBoardAuthorList
	 * @param request
	 * @param boardAuthorVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveBoardAuthorList.*")
	public ModelAndView retrieveBoardAuthorList(HttpServletRequest request, BoardAuthorVO boardAuthorVO, ModelMap model) throws Exception {
		CouragePaginationInfo pagination = new CouragePaginationInfo(boardAuthorVO, pageEnable, recordCount, pageSize);
		boardAuthorVO = (BoardAuthorVO) pagination.createCustomVo(request);

		// 권한관리 목록조회 기준 값 초기 설정
		if ("".equals(Util.nvl(boardAuthorVO.getCondRetrieveStdr()))) {
			boardAuthorVO.setCondRetrieveStdr("01");
		}
		
		if (!"".equals(Util.nvl(boardAuthorVO.getCondBbsId())) && boardAuthorVO.getCondRetrieveStdr().equals("01")) {
			pagination.setTotalRecordCount(boardAuthorService.selectListCountForBoardMaster(boardAuthorVO));
		} else if (!"".equals(Util.nvl(boardAuthorVO.getCondAuthorCode())) && boardAuthorVO.getCondRetrieveStdr().equals("02")) {
			pagination.setTotalRecordCount(boardAuthorService.selectListCountForAuthorInfo(boardAuthorVO));
		}

		List<BoardAuthorVO> boardAuthorList = null;

		if (pagination.getTotalRecordCount() > 0) {
			if (boardAuthorVO.getCondRetrieveStdr().equals("01")) {
				boardAuthorList = boardAuthorService.selectListForBoardMaster(boardAuthorVO);
			} else {
				boardAuthorList = boardAuthorService.selectListForAuthorInfo(boardAuthorVO);
			}
		}

		model.addAttribute("pagination", pagination);
		model.addAttribute("boardAuthorList", boardAuthorList);

		return new ModelAndView(boardAuthorListView);
	}

	/**
	 * <pre>
	 * 1. 개요 : 게시판 권한관리 정보 등록/수정
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 11.
	 * @Method Name : createBoardAuthorProc
	 * @param request
	 * @param boardAuthorVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createBoardAuthorProc.*")
	public ModelAndView createBoardAuthorProc(HttpServletRequest request, BoardAuthorVO boardAuthorVO, BindingResult bindingResult,
			ModelMap model) throws Exception {
		
		beanValidator.validate(boardAuthorVO, bindingResult);
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("validateCheck", "error");
			model.addAttribute("boardAuthorVO", boardAuthorVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);

			return retrieveBoardAuthorList(request, boardAuthorVO, model);
		}
		
		boardAuthorService.insertBoardAuthor(boardAuthorVO);
		
		String condParams = BeanUtils.getConditionParameter(boardAuthorVO);
		String params = "?" + WebUtils.ACTION_STATUS_PARAM + "=" + WebUtils.ACTION_STATUS_SUCCESS + (StringUtils.isEmpty(condParams) ? "" : "&" + condParams);
		String returnUrl = "retrieveBoardAuthorList." + WebUtils.getUrlExtension(request);
		
		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl + params);
		
		return new ModelAndView(redirectView);
	}
}