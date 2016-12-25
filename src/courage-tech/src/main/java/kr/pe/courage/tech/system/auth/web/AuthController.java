
package kr.pe.courage.tech.system.auth.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.pe.courage.common.core.CommonDefaultVO;
import kr.pe.courage.common.utils.Util;
import kr.pe.courage.common.vo.JxlsParam;
import kr.pe.courage.tech.cop.bbs.service.BoardAuthorService;
import kr.pe.courage.tech.cop.bbs.service.BoardAuthorVO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import egovframework.com.cmm.LoginVO;
import egovframework.com.sec.ram.service.AuthorManageVO;
import egovframework.com.sec.ram.service.EgovAuthorManageService;
import egovframework.com.sec.ram.web.AbstractAuthorManageController;

/**
 * <pre>
 * kr.pe.courage.tech.system.auth.web
 * AuthController.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2014. 12. 11.
 * @Version	: 
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2014. 12. 11., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
@Controller("authorController")
@RequestMapping(value = "/system/auth/*")
public class AuthController extends AbstractAuthorManageController {
	@Resource(name = "authorManageService")
	private EgovAuthorManageService authorManageService;
	
	@Resource(name = "boardAuthorService")
	private BoardAuthorService boardAuthorService;

	@Value("#{systemView['formAuthView']}")
	private String formAuthView;

	@Value("#{systemView['authListView']}")
	private String authListView;

	@Value("#{systemView['authDetailView']}")
	private String authDetailView;

	@Value("#{systemView['authCreateView']}")
	private String authCreateView;

	@Value("#{systemView['authModifyView']}")
	private String authModifyView;

	@Value("#{systemView['authMenuListView']}")
	private String authMenuListView;

	@Value("#{pageConfig['auth.page.recordCount']}")
	private int recordCount;

	@Value("#{pageConfig['auth.page.size']}")
	private int pageSize;

	@Value("#{pageConfig['auth.page.enable']}")
	private String pageEnable;

	protected String getAuthorMenuManageListView() {
		return authMenuListView;
	}

	protected String getAuthorManageListView() {
		return authListView;
	}

	protected String getAuthorManageDetailView() {
		return authDetailView;
	}

	protected String getAuthorManageCreateView() {
		return authCreateView;
	}

	protected String getAuthorManageModifyView() {
		return authModifyView;
	}

	protected String getAuthorManageListAttributeName() {
		return "authList";
	}

	protected CommonDefaultVO getCommonDefaultVO() {
		CommonDefaultVO commonDefaultVO = new CommonDefaultVO(pageEnable, "AUTHOR_CREAT_DE", "DESC");
		commonDefaultVO.setRecordCountPerPage(recordCount);
		commonDefaultVO.setPageSize(pageSize);

		return commonDefaultVO;
	}

	/**
	 * <pre>
	 * 1. 개요 : 권한관리 목록 조회
	 * </pre>
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 11.
	 * @Method Name : formAuth
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "formAuth.*")
	public ModelAndView formAuth(ModelMap model) throws Exception {
		return new ModelAndView(formAuthView, model);
	}

	/* (non-Javadoc)
	 * @see egovframework.com.sec.ram.web.AbstractAuthorManageController#deleteAuthor(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, egovframework.com.sec.ram.service.AuthorManageVO, org.springframework.ui.ModelMap)
	 * 
	 * 권한정보를 기준으로 게시판 권한관리 기능을 추가하여 권한 삭제 시 게시판 권한 정보를 삭제토록 확장
	 * TODO :: 삭제에 대한 트렌젝션 처리 우짜지??
	 */
	@Override
	@RequestMapping(value = "deleteAuth.*")
	public ModelAndView deleteAuthor(HttpServletRequest request, HttpServletResponse response, AuthorManageVO authorManageVO,
			ModelMap model) throws Exception {
		
		BoardAuthorVO boardAuthorVO = new BoardAuthorVO();
		boardAuthorVO.setAuthorCode(authorManageVO.getAuthorCode());
		
		boardAuthorService.deleteBoardAuthor(boardAuthorVO);
		
		return super.deleteAuthor(request, response, authorManageVO, model);
	}

	/**
	 * <pre>
	 * 1. 개요 : 엑셀 다운로드
	 * </pre>
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 11.
	 * @Method Name : downloadAuthExcel
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "downloadAuthExcel.*")
	public ModelAndView downloadAuthExcel(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("authorManageVO") AuthorManageVO authorManageVO, ModelMap model) throws Exception {

		authorManageVO.setPagingEnable(AuthorManageVO.PAGING_ENABLE_OFF);

		int totalCnt = authorManageService.selectAuthorListTotCnt(authorManageVO);
		List<AuthorManageVO> authorManageList = authorManageService.selectAuthorList(authorManageVO);
		JxlsParam jxlsParam = new JxlsParam(JxlsParam.EXCEL_EXT_XLS, "authExcel", "권한 목록", "권한 목록", totalCnt);

		model.addAttribute("list", authorManageList);
		model.addAttribute("jxlsParam", jxlsParam);

		return new ModelAndView("jxlsView", model);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 권한에 설정된 초기페이지 이동
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 4. 26.
	 * @Method Name : redirectAuthIndexPage
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "redirectAuthIndexPage.*")
	public ModelAndView redirectAuthIndexPage(HttpSession session, HttpServletResponse response) throws Exception {
		List<AuthorManageVO> authorList = (List<AuthorManageVO>) session.getAttribute(LoginVO.SESSION_AUTHOR_LIST);
		String userAuthorCode = (String) session.getAttribute(LoginVO.SESSION_AUTHOR_CODE);
		
		String authFirstPage = "/error/error404.tech";
		
		if (authorList == null) {
			// 미 로그인 사용자
			AuthorManageVO authorManageVO = new AuthorManageVO();
			authorManageVO.setAuthorCode(userAuthorCode);
			authorManageVO = authorManageService.selectAuthor(authorManageVO);
			
			if (!Util.nvl(authorManageVO.getAuthorIndexUrl()).equals("")) {
				authFirstPage = authorManageVO.getAuthorIndexUrl();
			}
		} else {
			// 로그인 사용자
			for (AuthorManageVO authorManageVO : authorList) {
				if (userAuthorCode.equals(authorManageVO.getAuthorCode())) {
					if (!Util.nvl(authorManageVO.getAuthorIndexUrl()).equals("")) {
						authFirstPage = authorManageVO.getAuthorIndexUrl();
					}
				}
			}
		}
		
		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(authFirstPage);
		
		return new ModelAndView(redirectView);
	}
}
