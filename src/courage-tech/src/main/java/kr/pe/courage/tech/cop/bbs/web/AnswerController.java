
package kr.pe.courage.tech.cop.bbs.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.pe.courage.common.utils.BeanUtils;
import kr.pe.courage.common.web.utils.WebUtils;
import kr.pe.courage.tech.cop.bbs.service.AnswerService;
import kr.pe.courage.tech.cop.bbs.service.AnswerVO;
import kr.pe.courage.tech.cop.bbs.service.BoardAuthorCheckVO;
import kr.pe.courage.tech.cop.bbs.service.BoardAuthorService;
import kr.pe.courage.tech.cop.bbs.service.BoardVO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.LoginVO;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.ptl.mvc.tags.ui.pagination.CouragePaginationInfo;

/**
 * <pre>
 * kr.pe.courage.tech.cop.bbs.web
 * AnswerController.java
 * </pre>
 * 
 * @Author : ChangHo Seok
 * @Date : 2016. 6. 29.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 6. 29., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
@Controller("answerController")
@RequestMapping("/cop/bbs/*")
@SessionAttributes({"condBoardVO", "condAnswerVO"})
public class AnswerController {
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	@Resource(name = "answerService")
	private AnswerService answerService;
	
	@Resource(name = "boardAuthorService")
	private BoardAuthorService boardAuthorService;

	@Value("#{copView['formAnswerView']}")
	private String formAnswerView;

	@Value("#{copView['answerListView']}")
	private String answerListView;

	@Value("#{copView['answerCreateView']}")
	private String answerCreateView;

	@Value("#{copView['answerModifyView']}")
	private String answerModifyView;

	@Value("#{pageConfig['answer.page.recordCount']}")
	private int recordCount;

	@Value("#{pageConfig['answer.page.size']}")
	private int pageSize;

	@Value("#{pageConfig['answer.page.enable']}")
	private String pageEnable;
	
	@SuppressWarnings("unused")
	@ModelAttribute("condAnswerVO")
	private AnswerVO answerVO() {
		return new AnswerVO();
	}

	@SuppressWarnings("unused")
	@InitBinder
	private void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields(BeanUtils.getDisallowedFields(AnswerVO.class));
	}

	/**
	 * <pre>
	 * 1. 개요 : 댓글 메인 form
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 7. 6.
	 * @Method Name : formAnswer
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "formAnswer.*")
	public ModelAndView formAnswer() throws Exception {
		return new ModelAndView(formAnswerView);
	}

	/**
	 * <pre>
	 * 1. 개요 : 댓글 목록 조회
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 6. 29.
	 * @Method Name : retrieveAnswerList
	 * @param request
	 * @param response
	 * @param answerVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveAnswerList.*")
	public ModelAndView retrieveAnswerList(HttpServletRequest request, HttpServletResponse response, AnswerVO answerVO,
			@ModelAttribute("condBoardVO") BoardVO condBoardVO, ModelMap model) throws Exception {

		// Parameter 변조 보안처리 (BoardController과 강한 의존관계)
		answerVO.setBbsId(condBoardVO.getBbsId());
		answerVO.setNttId(condBoardVO.getNttId());
		
		// 게시판 권한정보 조회
		BoardAuthorCheckVO boardAuthorCheckVO = getBoardAuthorCheckVO(request, answerVO.getBbsId());
		if (!isAuthenticate(boardAuthorCheckVO, BoardAuthorCheckVO.CHECK_READNG)) {
			if (WebUtils.isAjaxRequest(request)) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return null;
			} else {
				return new ModelAndView("redirect:/error/errorAuth.tech");
			}
		}

		CouragePaginationInfo pagination = new CouragePaginationInfo(answerVO, pageEnable, recordCount, pageSize);
		answerVO = (AnswerVO) pagination.createCustomVo(request);

		pagination.setTotalRecordCount(answerService.selectAnswerListCount(answerVO));
		List<AnswerVO> answerList = null;

		if (pagination.getTotalRecordCount() > 0) {
			answerList = answerService.selectAnswerList(answerVO);
		}

		model.addAttribute("pagination", pagination);
		model.addAttribute("boardAuthorCheckVO", boardAuthorCheckVO);
		model.addAttribute("answerList", answerList);

		return new ModelAndView(answerListView);
	}

	/**
	 * <pre>
	 * 1. 개요 : 댓글 등록 화면 조회
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 7. 6.
	 * @Method Name : createAnswer
	 * @param answerVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createAnswer.*")
	public ModelAndView createAnswer(HttpServletRequest request, HttpServletResponse response, AnswerVO answerVO,
			@ModelAttribute("condBoardVO") BoardVO condBoardVO, ModelMap model) throws Exception {
		
		// 게시판 권한정보 조회
		BoardAuthorCheckVO boardAuthorCheckVO = getBoardAuthorCheckVO(request, condBoardVO.getBbsId());
		
		answerVO.setMode(AnswerVO.MODE_CREATE);
		model.addAttribute("boardAuthorCheckVO", boardAuthorCheckVO);

		return new ModelAndView(answerCreateView);
	}

	/**
	 * <pre>
	 * 1. 개요 : 댓글 저장 처리
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 7. 5.
	 * @Method Name : createAnswerProc
	 * @param request
	 * @param response
	 * @param answerVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createAnswerProc.*")
	public ModelAndView createAnswerProc(HttpServletRequest request, HttpServletResponse response, AnswerVO answerVO,
			BindingResult bindingResult, @ModelAttribute("condBoardVO") BoardVO condBoardVO, ModelMap model) throws Exception {

		// Parameter 변조 보안처리 (BoardController과 강한 의존관계)
		answerVO.setBbsId(condBoardVO.getBbsId());
		answerVO.setNttId(condBoardVO.getNttId());
		
		// 게시판 권한정보 조회
		BoardAuthorCheckVO boardAuthorCheckVO = getBoardAuthorCheckVO(request, answerVO.getBbsId());
		if (!isAuthenticate(boardAuthorCheckVO, BoardAuthorCheckVO.CHECK_ANSWER_WRITNG)) {
			if (WebUtils.isAjaxRequest(request)) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return null;
			} else {
				return new ModelAndView("redirect:/error/errorAuth.tech");
			}
		}

		beanValidator.validate(answerVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("validateCheck", "error");
			model.addAttribute("answerVO", answerVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);

			return new ModelAndView(answerCreateView);
		}

		answerVO.setUseAt(AnswerVO.USE_AT_Y);
		answerVO.setFrstRegisterId(WebUtils.getUserUniqID(request));

		answerService.insertAnswer(answerVO);

		// 등록 완료 후 초기화
		model.addAttribute(WebUtils.ACTION_STATUS_PARAM, WebUtils.ACTION_STATUS_SUCCESS);
		model.addAttribute("boardAuthorCheckVO", boardAuthorCheckVO);
		model.addAttribute("answerVO", new AnswerVO());

		return new ModelAndView(answerCreateView);
	}

	/**
	 * <pre>
	 * 1. 개요 : 댓글 수정화면
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 7. 6.
	 * @Method Name : modifyAnswer
	 * @param answerVO
	 * @param condBoardVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modifyAnswer.*")
	public ModelAndView modifyAnswer(HttpServletRequest request, HttpServletResponse response, AnswerVO answerVO,
			@ModelAttribute("condBoardVO") BoardVO condBoardVO, ModelMap model) throws Exception {
		// Parameter 변조 보안처리 (BoardController과 강한 의존관계)
		answerVO.setBbsId(condBoardVO.getBbsId());
		answerVO.setNttId(condBoardVO.getNttId());
		
		// 게시판 권한정보 조회
		BoardAuthorCheckVO boardAuthorCheckVO = getBoardAuthorCheckVO(request, answerVO.getBbsId());
		if (!isAuthenticate(boardAuthorCheckVO, BoardAuthorCheckVO.CHECK_ANSWER_WRITNG)) {
			if (WebUtils.isAjaxRequest(request)) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return null;
			} else {
				return new ModelAndView("redirect:/error/errorAuth.tech");
			}
		}
		
		answerVO = answerService.selectAnswer(answerVO);
		answerVO.setMode(AnswerVO.MODE_MODIFY);
		
		// 게시판 관리자가 아닌경우 본인 작성글 여부 확인
		if (boardAuthorCheckVO.getMngrAuthor() <= 0) {
			if (answerVO.getFrstRegisterId() == null || !answerVO.getFrstRegisterId().equals(WebUtils.getUserUniqID(request))) {
				if (WebUtils.isAjaxRequest(request)) {
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					return null;
				} else {
					return new ModelAndView("redirect:/error/errorAuth.tech");
				}
			}
		}

		model.addAttribute("boardAuthorCheckVO", boardAuthorCheckVO);
		model.addAttribute("condAnswerVO", answerVO);
		model.addAttribute("answerVO", answerVO);

		return new ModelAndView(answerModifyView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 댓글 수정처리
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 13.
	 * @Method Name : modifyAnswerProc
	 * @param request
	 * @param response
	 * @param answerVO
	 * @param bindingResult
	 * @param condBoardVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modifyAnswerProc.*")
	public ModelAndView modifyAnswerProc(HttpServletRequest request, HttpServletResponse response, AnswerVO answerVO, BindingResult bindingResult,
			@ModelAttribute("condBoardVO") BoardVO condBoardVO,
			@ModelAttribute("condAnswerVO") AnswerVO condAnswerVO,
			SessionStatus sessionStatus, ModelMap model) throws Exception {

		// Parameter 변조 보안처리 (BoardController과 강한 의존관계)
		answerVO.setBbsId(condBoardVO.getBbsId());
		answerVO.setNttId(condBoardVO.getNttId());
		
		// 게시판 권한정보 조회
		BoardAuthorCheckVO boardAuthorCheckVO = getBoardAuthorCheckVO(request, answerVO.getBbsId());
		if (!isAuthenticate(boardAuthorCheckVO, BoardAuthorCheckVO.CHECK_ANSWER_WRITNG)) {
			if (WebUtils.isAjaxRequest(request)) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return null;
			} else {
				return new ModelAndView("redirect:/error/errorAuth.tech");
			}
		}
		
		// 게시판 관리자가 아닌경우 본인 작성글 여부 확인
		if (boardAuthorCheckVO.getMngrAuthor() <= 0) {
			if (condAnswerVO.getFrstRegisterId() == null || !condAnswerVO.getFrstRegisterId().equals(WebUtils.getUserUniqID(request))) {
				if (WebUtils.isAjaxRequest(request)) {
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					return null;
				} else {
					return new ModelAndView("redirect:/error/errorAuth.tech");
				}
			}
		}

		beanValidator.validate(answerVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("validateCheck", "error");
			model.addAttribute("answerVO", answerVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);

			return new ModelAndView(answerCreateView);
		}

		answerVO.setLastUpdusrId(WebUtils.getUserUniqID(request));
		answerService.updateAnswer(answerVO);

		// 등록 완료 후 초기화
		model.addAttribute(WebUtils.ACTION_STATUS_PARAM, WebUtils.ACTION_STATUS_SUCCESS);
		model.addAttribute("boardAuthorCheckVO", boardAuthorCheckVO);
		model.addAttribute("answerVO", new AnswerVO());

		return new ModelAndView(answerCreateView);
	}

	/**
	 * <pre>
	 * 1. 개요 : 댓글 삭제
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 8.
	 * @Method Name : deleteAnswer
	 * @param answerVO
	 * @param boardVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteAnswer.*")
	public @ResponseBody Map<String, Object> deleteAnswer(HttpServletRequest request, HttpServletResponse response, AnswerVO answerVO,
			@ModelAttribute("condBoardVO") BoardVO condBoardVO, @ModelAttribute("condAnswerVO") AnswerVO condAnswerVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		// Parameter 변조 보안처리 (BoardController과 강한 의존관계)
		answerVO.setBbsId(condBoardVO.getBbsId());
		answerVO.setNttId(condBoardVO.getNttId());
		
		// 게시판 권한정보 조회
		BoardAuthorCheckVO boardAuthorCheckVO = getBoardAuthorCheckVO(request, answerVO.getBbsId());
		if (!isAuthenticate(boardAuthorCheckVO, BoardAuthorCheckVO.CHECK_ANSWER_WRITNG)) {
			resultMap.put(WebUtils.ACTION_STATUS_PARAM, WebUtils.ACTION_STATUS_FAILED);
			return resultMap;
		}
		
		// 게시판 관리자가 아닌경우 본인 작성글 여부 확인
		if (boardAuthorCheckVO.getMngrAuthor() <= 0) {
			if (condAnswerVO.getFrstRegisterId() == null || !condAnswerVO.getFrstRegisterId().equals(WebUtils.getUserUniqID(request))) {
				resultMap.put(WebUtils.ACTION_STATUS_PARAM, WebUtils.ACTION_STATUS_FAILED);
				return resultMap;
			}
		}
		
		try {
			answerService.deleteAnswer(answerVO);
			resultMap.put(WebUtils.ACTION_STATUS_PARAM, WebUtils.ACTION_STATUS_SUCCESS);
		} catch (EgovBizException e) {
			resultMap.put(WebUtils.ACTION_STATUS_PARAM, WebUtils.ACTION_STATUS_FAILED);
		}
		
		return resultMap;
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
}
