
package kr.pe.courage.tech.cop.bbs.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.LoginVO;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.CouragePaginationInfo;
import kr.pe.courage.common.utils.BeanUtils;
import kr.pe.courage.common.utils.Util;
import kr.pe.courage.common.web.utils.WebUtils;
import kr.pe.courage.tech.cop.bbs.exception.ExistsAnswerException;
import kr.pe.courage.tech.cop.bbs.exception.ExistsReplyNTTException;
import kr.pe.courage.tech.cop.bbs.service.BoardAuthorCheckVO;
import kr.pe.courage.tech.cop.bbs.service.BoardAuthorService;
import kr.pe.courage.tech.cop.bbs.service.BoardMasterService;
import kr.pe.courage.tech.cop.bbs.service.BoardMasterVO;
import kr.pe.courage.tech.cop.bbs.service.BoardService;
import kr.pe.courage.tech.cop.bbs.service.BoardVO;

/**
 * <pre>
 * kr.pe.courage.tech.cop.bbs.web
 * BoardController.java
 * </pre>
 * 
 * @Author : ChangHo Seok
 * @Date : 2016. 5. 31.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 5. 31., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
@Controller("boardController")
@RequestMapping(value = "/cop/bbs/*")
@SessionAttributes("condBoardVO")
public class BoardController {
	@Resource(name = "egovNttIdGnrService")
	private EgovIdGnrService idgenService;

	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	@Resource(name = "boardService")
	private BoardService boardService;

	@Resource(name = "boardMasterService")
	private BoardMasterService boardMasterService;
	
	@Resource(name = "boardAuthorService")
	private BoardAuthorService boardAuthorService;

	@Value("#{copView['formBoardView']}")
	private String formBoardView;

	@Value("#{copView['formBoardPopupView']}")
	private String formBoardPopupView;

	@Value("#{copView['boardListView']}")
	private String boardListView;

	@Value("#{copView['boardListPopupView']}")
	private String boardListPopupView;

	@Value("#{copView['boardDetailView']}")
	private String boardDetailView;

	@Value("#{copView['boardDetailPopupView']}")
	private String boardDetailPopupView;

	@Value("#{copView['boardContentsView']}")
	private String boardContentsView;

	@Value("#{copView['boardCreateView']}")
	private String boardCreateView;

	@Value("#{copView['boardModifyView']}")
	private String boardModifyView;

	@Value("#{copView['noticeRollingListView']}")
	private String noticeRollingListView;

	@Value("#{pageConfig['board.page.recordCount']}")
	private int recordCount;

	@Value("#{pageConfig['board.page.size']}")
	private int pageSize;

	@Value("#{pageConfig['board.page.enable']}")
	private String pageEnable;
	
	@ModelAttribute("condBoardVO")
	private BoardVO boardVO() {
		return new BoardVO();
	}
	
	@InitBinder
	private void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields(BeanUtils.getDisallowedFields(BoardVO.class));
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 게시글 메인 form
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 5. 31.
	 * @Method Name : formBoard
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "formBoard.*")
	public ModelAndView formBoard(@RequestParam(value="viewTy",defaultValue="main") String viewTy) throws Exception {
		if (viewTy.equals("popup")) {
			return new ModelAndView(formBoardPopupView);
		} else {
			return new ModelAndView(formBoardView);
		}
	}

	/**
	 * <pre>
	 * 1. 개요 : 게시글 목록 조회
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 6. 1.
	 * @Method Name : retrieveBoardList
	 * @param request
	 * @param boardVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveBoardList.*")
	public ModelAndView retrieveBoardList(HttpServletRequest request, HttpServletResponse response,
			BoardMasterVO boardMasterVO, BoardVO boardVO, SessionStatus sessionStatus,
			@RequestParam(value="viewTy",defaultValue="main") String viewTy,
			ModelMap model) throws Exception {
		
		// 게시판 유효상태 확인
		if (!boardMasterService.validateBoardMaster(boardVO.getBbsId())) {
			if (WebUtils.isAjaxRequest(request)) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return null;
			} else {
				return new ModelAndView("redirect:/error/error404.tech");
			}
		}
		
		// 게시판 권한정보 조회
		BoardAuthorCheckVO boardAuthorCheckVO = getBoardAuthorCheckVO(request, boardVO.getBbsId());
		if (!isAuthenticate(boardAuthorCheckVO, BoardAuthorCheckVO.CHECK_LIST)) {
			if (WebUtils.isAjaxRequest(request)) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return null;
			} else {
				return new ModelAndView("redirect:/error/errorAuth.tech");
			}
		}
		
		// 게시판 마스터 정보 조회
		boardMasterVO = boardMasterService.selectBoardMaster(boardMasterVO);

		CouragePaginationInfo pagination = new CouragePaginationInfo(boardVO, pageEnable, recordCount, pageSize);
		boardVO = (BoardVO) pagination.createCustomVo(request);

		pagination.setTotalRecordCount(boardService.selectBoardListCount(boardVO));
		List<BoardVO> boardList = null;

		if (pagination.getTotalRecordCount() > 0) {
			boardList = boardService.selectBoardList(boardVO);
		}

		model.addAttribute("pagination", pagination);
		model.addAttribute("boardMasterVO", boardMasterVO);
		model.addAttribute("boardAuthorCheckVO", boardAuthorCheckVO);
		model.addAttribute("boardList", boardList);
		
		sessionStatus.setComplete();

		if (viewTy.equals("popup")) {
			return new ModelAndView(boardListPopupView);
		} else {
			return new ModelAndView(boardListView);
		}
	}

	/**
	 * <pre>
	 * 1. 개요 : 게시물 상세조회
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 6. 3.
	 * @Method Name : retrieveBoardDetail
	 * @param boardMasterVO
	 * @param boardVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveBoardDetail.*")
	public ModelAndView retrieveBoardDetail(HttpServletRequest request, HttpServletResponse response,
			BoardMasterVO boardMasterVO, BoardVO boardVO, @ModelAttribute("condBoardVO") BoardVO condBoardVO,
			@RequestParam(value="viewTy",defaultValue="main") String viewTy,
			ModelMap model) throws Exception {
		
		// 게시판 유효상태 확인
		if (!boardMasterService.validateBoardMaster(boardVO.getBbsId())) {
			if (WebUtils.isAjaxRequest(request)) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return null;
			} else {
				return new ModelAndView("redirect:/error/error404.tech");
			}
		}
		
		// 게시판 권한정보 조회
		BoardAuthorCheckVO boardAuthorCheckVO = getBoardAuthorCheckVO(request, boardVO.getBbsId());
		if (!isAuthenticate(boardAuthorCheckVO, BoardAuthorCheckVO.CHECK_READNG)) {
			if (WebUtils.isAjaxRequest(request)) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return null;
			} else {
				return new ModelAndView("redirect:/error/errorAuth.tech");
			}
		}
		
		// 게시판 마스터 정보 조회
		boardMasterVO = boardMasterService.selectBoardMaster(boardMasterVO);
		
		BoardVO resultVO = boardService.selectBoard(boardVO);
		BeanUtils.copyCondProperties(boardVO, resultVO);
		
		// 조회수 증가
		boardService.updateBoardRdcntIncrs(boardVO);

		model.addAttribute("boardMasterVO", boardMasterVO);
		model.addAttribute("boardAuthorCheckVO", boardAuthorCheckVO);
		model.addAttribute("boardVO", resultVO);
		
		// 기준값 DB 조회 값으로 저장
		model.addAttribute("condBoardVO", resultVO);
		
		if (viewTy.equals("popup")) {
			return new ModelAndView(boardDetailPopupView);
		} else {
			return new ModelAndView(boardDetailView);
		}
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 게시물 상세조회 (웹 컨텐츠 형식으로 조회)
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 11. 13.
	 * @Method Name : retrieveBoardContent
	 * @param request
	 * @param response
	 * @param boardMasterVO
	 * @param boardVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveBoardContents.*")
	public ModelAndView retrieveBoardContents(HttpServletRequest request, HttpServletResponse response, BoardMasterVO boardMasterVO,
			BoardVO boardVO, ModelMap model) throws Exception {
		// 게시판 유효상태 확인
		if (!boardMasterService.validateBoardMaster(boardVO.getBbsId())) {
			if (WebUtils.isAjaxRequest(request)) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return null;
			} else {
				return new ModelAndView("redirect:/error/error404.tech");
			}
		}
		
		// 게시판 권한정보 조회
		BoardAuthorCheckVO boardAuthorCheckVO = getBoardAuthorCheckVO(request, boardVO.getBbsId());
		if (!isAuthenticate(boardAuthorCheckVO, BoardAuthorCheckVO.CHECK_READNG)) {
			if (WebUtils.isAjaxRequest(request)) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return null;
			} else {
				return new ModelAndView("redirect:/error/errorAuth.tech");
			}
		}
		
		BoardVO resultVO = boardService.selectBoard(boardVO);
		
		// 조회수 증가
		boardService.updateBoardRdcntIncrs(boardVO);

		// 게시판 마스터 정보 조회
		boardMasterVO = boardMasterService.selectBoardMaster(boardMasterVO);

		model.addAttribute("boardMasterVO", boardMasterVO);
		model.addAttribute("boardAuthorCheckVO", boardAuthorCheckVO);
		model.addAttribute("boardVO", resultVO);
		
		return new ModelAndView(boardContentsView);
	}

	/**
	 * <pre>
	 * 1. 개요 : 게시글 등록 화면
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 6. 1.
	 * @Method Name : createBoard
	 * @param request
	 * @param response
	 * @param boardVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createBoard.*")
	public ModelAndView createBoard(HttpServletRequest request, HttpServletResponse response, BoardMasterVO boardMasterVO, BoardVO boardVO,
			@ModelAttribute("condBoardVO") BoardVO condBoardVO, ModelMap model) throws Exception {
		
		// 게시판 유효상태 확인
		if (!boardMasterService.validateBoardMaster(boardVO.getBbsId())) {
			if (WebUtils.isAjaxRequest(request)) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return null;
			} else {
				return new ModelAndView("redirect:/error/error404.tech");
			}
		}
		
		// 게시판 권한정보 조회
		BoardAuthorCheckVO boardAuthorCheckVO = getBoardAuthorCheckVO(request, boardVO.getBbsId());
		if (!isAuthenticate(boardAuthorCheckVO, BoardAuthorCheckVO.CHECK_SNTNC_WRITNG)) {
			if (WebUtils.isAjaxRequest(request)) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return null;
			} else {
				return new ModelAndView("redirect:/error/errorAuth.tech");
			}
		}
		
		// 게시판 마스터 정보 조회
		boardMasterVO = boardMasterService.selectBoardMaster(boardMasterVO);
		
		// 답글설정
		if (condBoardVO.getNttId() > 0) {
			boolean replyCheck = false;
			String actionMessage = "";
			
			if (!boardMasterVO.getReplyPosblAt().equals(BoardMasterVO.AT_Y)) {
				actionMessage = "해당 게시판은 답글쓰기를 허용하지 않습니다.";
			} else if (condBoardVO.getParntsSntncNo() > 0) {
				actionMessage = "2 레벨의 답글쓰기는 허용하지 않습니다.";
			} else {
				replyCheck = true;
				condBoardVO.setParntsSntncNo(condBoardVO.getNttId());
			}
			
			if (!replyCheck) {
				String condParams = BeanUtils.getConditionParameter(boardVO);
				String params = "?bbsId=" + boardVO.getBbsId() + "&nttId=" + boardVO.getNttId() + "&" + WebUtils.ACTION_STATUS_PARAM + "=" + WebUtils.ACTION_STATUS_SUCCESS
						+ (StringUtils.isEmpty(condParams) ? "" : "&" + condParams) + "&" + WebUtils.getActionMessageParam(actionMessage);
				String returnUrl = "retrieveBoardDetail." + WebUtils.getUrlExtension(request);

				RedirectView redirectView = new RedirectView();
				redirectView.setContextRelative(true);
				redirectView.setUrl(returnUrl + params);
				
				return new ModelAndView(redirectView);
			}
		}

		boardVO.setMode(BoardVO.MODE_CREATE);
		model.addAttribute("boardMasterVO", boardMasterVO);
		model.addAttribute("boardAuthorCheckVO", boardAuthorCheckVO);

		return new ModelAndView(boardCreateView);
	}

	/**
	 * <pre>
	 * 1. 개요 : 게시글 저장 처리
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 6. 3.
	 * @Method Name : createBoardProc
	 * @param request
	 * @param boardMasterVO
	 * @param boardVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createBoardProc.*")
	public ModelAndView createBoardProc(HttpServletRequest request, HttpServletResponse response, BoardMasterVO boardMasterVO,
			BoardVO boardVO, BindingResult bindingResult, @ModelAttribute("condBoardVO") BoardVO condBoardVO, SessionStatus sessionStatus,
			ModelMap model) throws Exception {

		beanValidator.validate(boardVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("validateCheck", "error");
			model.addAttribute("boardVO", boardVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);

			return new ModelAndView(boardCreateView, model);
		}

		// 게시판 유효상태 확인
		if (!boardMasterService.validateBoardMaster(boardVO.getBbsId())) {
			if (WebUtils.isAjaxRequest(request)) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return null;
			} else {
				return new ModelAndView("redirect:/error/error404.tech");
			}
		}
		
		// 게시판 권한정보 조회
		BoardAuthorCheckVO boardAuthorCheckVO = getBoardAuthorCheckVO(request, boardVO.getBbsId());
		if (!isAuthenticate(boardAuthorCheckVO, BoardAuthorCheckVO.CHECK_SNTNC_WRITNG)) {
			if (WebUtils.isAjaxRequest(request)) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return null;
			} else {
				return new ModelAndView("redirect:/error/errorAuth.tech");
			}
		}
		
		if (Util.checkNull(boardVO.getNoticeAt())) {
			boardVO.setNoticeAt(BoardVO.USE_N);
		}
		
		boardVO.setNttId(idgenService.getNextIntegerId());
		boardVO.setUseAt(BoardVO.USE_Y);
		boardVO.setFrstRegisterId(WebUtils.getUserUniqID(request));
		boardVO.setLastUpdusrId(boardVO.getFrstRegisterId());
		boardVO.setJsessionId(WebUtils.getSessionId(request));
		
		boardVO.setGroupNo(condBoardVO.getGroupNo()); // 그룹 번호
		boardVO.setParntsSntncNo(condBoardVO.getParntsSntncNo()); // 부모글 번호
		boardService.insertBoard(boardVO);

		String params = "?bbsId=" + boardVO.getBbsId() + "&nttId=" + boardVO.getNttId() + "&" + WebUtils.ACTION_STATUS_PARAM + "="
				+ WebUtils.ACTION_STATUS_SUCCESS;
		String returnUrl = "retrieveBoardDetail." + WebUtils.getUrlExtension(request);

		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl + params);
		
		sessionStatus.setComplete();

		return new ModelAndView(redirectView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 게시글 수정 화면
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 6. 13.
	 * @Method Name : modifyBoard
	 * @param request
	 * @param response
	 * @param boardMasterVO
	 * @param boardVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modifyBoard.*")
	public ModelAndView modifyBoard(HttpServletRequest request, HttpServletResponse response,
			BoardMasterVO boardMasterVO, BoardVO boardVO, @ModelAttribute("condBoardVO") BoardVO condBoardVO, ModelMap model) throws Exception {
		
		boardMasterVO.setBbsId(condBoardVO.getBbsId());
		boardVO.setBbsId(condBoardVO.getBbsId());
		boardVO.setNttId(condBoardVO.getNttId());
		
		// 게시판 유효상태 확인
		if (!boardMasterService.validateBoardMaster(boardVO.getBbsId())) {
			if (WebUtils.isAjaxRequest(request)) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return null;
			} else {
				return new ModelAndView("redirect:/error/error404.tech");
			}
		}
		
		// 게시판 권한정보 조회
		BoardAuthorCheckVO boardAuthorCheckVO = getBoardAuthorCheckVO(request, boardVO.getBbsId());
		if (!isAuthenticate(boardAuthorCheckVO, BoardAuthorCheckVO.CHECK_SNTNC_WRITNG)) {
			if (WebUtils.isAjaxRequest(request)) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return null;
			} else {
				return new ModelAndView("redirect:/error/errorAuth.tech");
			}
		}
		
		// 게시판 관리자가 아닌경우 본인 작성글 여부 확인
		if (boardAuthorCheckVO.getMngrAuthor() <= 0) {
			if (condBoardVO.getFrstRegisterId() == null || !condBoardVO.getFrstRegisterId().equals(WebUtils.getUserUniqID(request))) {
				if (WebUtils.isAjaxRequest(request)) {
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					return null;
				} else {
					return new ModelAndView("redirect:/error/errorAuth.tech");
				}
			}
		}
		
		BoardVO resultVO = boardService.selectBoard(boardVO);
		BeanUtils.copyCondProperties(boardVO, resultVO);
		resultVO.setMode(BoardVO.MODE_MODIFY);
		
		// 게시판 마스터 정보 조회
		boardMasterVO = boardMasterService.selectBoardMaster(boardMasterVO);

		model.addAttribute("boardMasterVO", boardMasterVO);
		model.addAttribute("boardAuthorCheckVO", boardAuthorCheckVO);
		model.addAttribute("boardVO", resultVO);
		
		return new ModelAndView(boardModifyView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 게시글 수정 처리
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 6. 14.
	 * @Method Name : modifyBoardProc
	 * @param request
	 * @param response
	 * @param boardMasterVO
	 * @param boardVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modifyBoardProc.*")
	public ModelAndView modifyBoardProc(HttpServletRequest request, HttpServletResponse response, BoardMasterVO boardMasterVO,
			BoardVO boardVO, BindingResult bindingResult, SessionStatus sessionStatus, ModelMap model,
			@ModelAttribute("condBoardVO") BoardVO condBoardVO) throws Exception {

		boardMasterVO.setBbsId(condBoardVO.getBbsId());
		boardVO.setBbsId(condBoardVO.getBbsId());
		boardVO.setNttId(condBoardVO.getNttId());
		
		beanValidator.validate(boardVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("validateCheck", "error");
			model.addAttribute("boardVO", boardVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);

			return new ModelAndView(boardCreateView, model);
		}

		// 게시판 유효상태 확인
		if (!boardMasterService.validateBoardMaster(boardVO.getBbsId())) {
			if (WebUtils.isAjaxRequest(request)) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return null;
			} else {
				return new ModelAndView("redirect:/error/error404.tech");
			}
		}
		
		// 게시판 권한정보 조회
		BoardAuthorCheckVO boardAuthorCheckVO = getBoardAuthorCheckVO(request, boardVO.getBbsId());
		if (!isAuthenticate(boardAuthorCheckVO, BoardAuthorCheckVO.CHECK_SNTNC_WRITNG)) {
			if (WebUtils.isAjaxRequest(request)) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return null;
			} else {
				return new ModelAndView("redirect:/error/errorAuth.tech");
			}
		}
		
		// 게시판 관리자가 아닌경우 본인 작성글 여부 확인
		if (boardAuthorCheckVO.getMngrAuthor() <= 0) {
			if (condBoardVO.getFrstRegisterId() == null || !condBoardVO.getFrstRegisterId().equals(WebUtils.getUserUniqID(request))) {
				if (WebUtils.isAjaxRequest(request)) {
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					return null;
				} else {
					return new ModelAndView("redirect:/error/errorAuth.tech");
				}
			}
		}
		
		if (Util.checkNull(boardVO.getNoticeAt())) {
			boardVO.setNoticeAt(BoardVO.USE_N);
		}
		
		// Parameter 보안항목 (Parameter 값을 이용하지 않음)
		boardVO.setAtchFileId(condBoardVO.getAtchFileId());
		boardVO.setMvpFileId(condBoardVO.getMvpFileId());
		boardVO.setLastUpdusrId(WebUtils.getUserUniqID(request));
		boardVO.setJsessionId(WebUtils.getSessionId(request));
		boardService.updateBoard(boardVO);
		
		String condParams = BeanUtils.getConditionParameter(boardVO);
		String params = "?bbsId=" + boardVO.getBbsId() + "&nttId=" + boardVO.getNttId() + "&" + WebUtils.ACTION_STATUS_PARAM + "=" + WebUtils.ACTION_STATUS_SUCCESS
				+ (StringUtils.isEmpty(condParams) ? "" : "&" + condParams);
		String returnUrl = "retrieveBoardDetail." + WebUtils.getUrlExtension(request);

		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl + params);
		
		sessionStatus.setComplete();

		return new ModelAndView(redirectView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 게시글 정보 삭제
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 6. 15.
	 * @Method Name : deleteBoard
	 * @param request
	 * @param boardMasterVO
	 * @param boardVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteBoard.*")
	public ModelAndView deleteBoard(HttpServletRequest request, HttpServletResponse response, BoardMasterVO boardMasterVO, BoardVO boardVO,
			@ModelAttribute("condBoardVO") BoardVO condBoardVO) throws Exception {
		boolean deleteFlag = true;
		
		String params = "";
		String returnUrl = "";
		String actionMessage = "";
		
		// 게시판 권한정보 조회
		BoardAuthorCheckVO boardAuthorCheckVO = getBoardAuthorCheckVO(request, boardVO.getBbsId());
		if (!isAuthenticate(boardAuthorCheckVO, BoardAuthorCheckVO.CHECK_SNTNC_WRITNG)) {
			if (WebUtils.isAjaxRequest(request)) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return null;
			} else {
				return new ModelAndView("redirect:/error/errorAuth.tech");
			}
		}
		
		// 게시판 관리자가 아닌경우 본인 작성글 여부 확인
		if (boardAuthorCheckVO.getMngrAuthor() <= 0) {
			if (condBoardVO.getFrstRegisterId() == null || !condBoardVO.getFrstRegisterId().equals(WebUtils.getUserUniqID(request))) {
				if (WebUtils.isAjaxRequest(request)) {
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					return null;
				} else {
					return new ModelAndView("redirect:/error/errorAuth.tech");
				}
			}
		}

		try {
			boardService.deleteBoard(boardVO);
		} catch (ExistsAnswerException e) {
			actionMessage = WebUtils.getActionMessageParam("댓글이 존재하여 삭제할 수 없습니다.");
			deleteFlag = false;
		} catch (ExistsReplyNTTException e) {
			actionMessage = WebUtils.getActionMessageParam("답글이 존재하여 삭제할 수 없습니다.");
			deleteFlag = false;
		}
		
		if (deleteFlag) {
			params = "?bbsId=" + boardVO.getBbsId() + "&" + WebUtils.ACTION_STATUS_PARAM + "=" + WebUtils.ACTION_STATUS_SUCCESS;
			returnUrl = "retrieveBoardList." + WebUtils.getUrlExtension(request);
		} else {
			String condParams = BeanUtils.getConditionParameter(boardMasterVO);
			params = "?bbsId=" + boardVO.getBbsId() + "&nttId=" + boardVO.getNttId() + "&" + WebUtils.ACTION_STATUS_PARAM + "=" + WebUtils.ACTION_STATUS_FAILED
					+ (StringUtils.isEmpty(condParams) ? "" : "&" + condParams) + "&" + actionMessage;
			returnUrl = "retrieveBoardDetail." + WebUtils.getUrlExtension(request);
		}
		
		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl + params);
		
		return new ModelAndView(redirectView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 게시판 권한 확인
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 12.
	 * @Method Name : isAuthenticate
	 * @param request
	 * @param bbsId
	 * @param checkFlag
	 * @return
	 * @throws EgovBizException 
	 */
	private boolean isAuthenticate(BoardAuthorCheckVO boardAuthorCheckVO, String checkFlag) throws EgovBizException {
		boolean resultFlag = false;
		
		if (boardAuthorCheckVO != null) {
			
			// 관리자 권한이 있으면 모두 true
			if (boardAuthorCheckVO.getMngrAuthor() > 0) {
				resultFlag = true;
			} else {
				if (checkFlag.equals(BoardAuthorCheckVO.CHECK_LIST)) {
					if (boardAuthorCheckVO.getList() > 0) {
						resultFlag = true;
					}
				} else if (checkFlag.equals(BoardAuthorCheckVO.CHECK_READNG)) {
					if (boardAuthorCheckVO.getReadng() > 0) {
						resultFlag = true;
					}
				} else if (checkFlag.equals(BoardAuthorCheckVO.CHECK_SNTNC_WRITNG)) {
					if (boardAuthorCheckVO.getSntncWritng() > 0) {
						resultFlag = true;
					}
				} else if (checkFlag.equals(BoardAuthorCheckVO.CHECK_ANSWER_WRITNG)) {
					if (boardAuthorCheckVO.getAnswerWritng() > 0) {
						resultFlag = true;
					}
				}
			}
			
		}
		
		return resultFlag;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 게시판 권한정보 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 12.
	 * @Method Name : getBoardAuthorCheckVO
	 * @param request
	 * @param bbsId
	 * @return
	 * @throws EgovBizException
	 */
	private BoardAuthorCheckVO getBoardAuthorCheckVO(HttpServletRequest request, String bbsId) throws EgovBizException {
		String[] authorCodes = (String[]) WebUtils.getSessionAttribute(request, LoginVO.SESSION_AUTHOR_CODE_ARRAY);
		
		if (authorCodes == null || authorCodes.length <= 0) {
			authorCodes = new String[1];
			
			authorCodes[0] = (String) WebUtils.getSessionAttribute(request, LoginVO.SESSION_AUTHOR_CODE);
		}
		
		BoardAuthorCheckVO boardAuthorCheckVO = new BoardAuthorCheckVO();
		
		boardAuthorCheckVO.setBbsId(bbsId);
		boardAuthorCheckVO.setAuhtorCodes(authorCodes);
		boardAuthorCheckVO = boardAuthorService.selectBoardAuthorCheck(boardAuthorCheckVO);
		
		return boardAuthorCheckVO;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 공지사항 목록 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 11. 27.
	 * @Method Name : retrieveNoticeRollingList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("retrieveNoticeRollingList.*")
	public ModelAndView retrieveNoticeRollingList(ModelMap model) throws Exception {
		model.addAttribute("noticeRollingList", boardService.selectNoticeRollingList());
		
		return new ModelAndView(noticeRollingListView);
	}
}
