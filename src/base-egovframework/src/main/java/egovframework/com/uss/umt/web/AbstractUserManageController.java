
package egovframework.com.uss.umt.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.pe.courage.common.core.CommonDefaultVO;
import kr.pe.courage.common.utils.BeanUtils;
import kr.pe.courage.common.utils.Util;
import kr.pe.courage.common.web.utils.WebUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.sym.ccm.cde.service.CmmnDetailCodeVO;
import egovframework.com.uss.umt.service.EgovUserManageService;
import egovframework.com.uss.umt.service.UserManageVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.CouragePaginationInfo;

/**
 * 업무사용자관련 요청을 비지니스 클래스로 전달하고 처리된결과를 해당 웹 화면으로 전달하는 Controller를 정의한다
 * 
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  조재영          최초 생성
 *   2011.8.26	정진오			IncludedInfo annotation 추가
 * 
 * </pre>
 */

@Controller
public abstract class AbstractUserManageController {

	@Resource(name = "userManageService")
	private EgovUserManageService userManageService;

	/** 사용자 목록조회 view 페이지*/
	protected abstract String getUserManageListView();

	/** 사용자 목록 팝업 조회 view 페이지*/
	protected abstract String getUserManageListPopupView();

	/** 사용자 상세조회 view 페이지*/
	protected abstract String getUserManageDetailView();

	/** 공통 사용자목록 model attribute name*/
	protected abstract String getUserManageListAttributeName();

	/** 공통 사용자목록 model attribute name*/
	protected abstract String getPagingEnable();

	/** 공통 사용자목록 model attribute name*/
	protected abstract String getPopupPagingEnable();

	/** 페이지 기본 설정정보*/
	protected abstract CommonDefaultVO getCommonDefaultVO();

	/**
	 * <pre>
	 * 1. 개요 : 사용자 목록조회
	 * </pre>
	 * 
	 * @Author : Choi Moon Seok
	 * @Date : 2013. 11. 24.
	 * @Method Name : selectUserList
	 * @param userManageVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveUserList.*")
	public ModelAndView selectUserList(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("userManageVO") UserManageVO userManageVO, ModelMap model) throws Exception {

		// 정렬 기본값 설정
		userManageVO.setCondOrder(Util.isNotBlank(request.getParameter("condOrder")) ? userManageVO.getCondOrder() : getCommonDefaultVO().getCondOrder());
		userManageVO.setCondAlign(Util.isNotBlank(request.getParameter("condAlign")) ? userManageVO.getCondAlign() : getCommonDefaultVO().getCondAlign());
		
		CouragePaginationInfo pagination = new CouragePaginationInfo(userManageVO,
				getCommonDefaultVO().getPagingEnable(),
				getCommonDefaultVO().getRecordCountPerPage(),
				getCommonDefaultVO().getPageSize());
		userManageVO = (UserManageVO) pagination.createCustomVo();

		int totalCnt = userManageService.selectUserListTotCnt(userManageVO);
		List<UserManageVO> userList = userManageService.selectUserList(userManageVO);
		
		pagination.setTotalRecordCount(totalCnt);
		
		model.addAttribute(getUserManageListAttributeName(), userList);
		model.addAttribute("pagination", pagination);
		WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_SUCCESS);
		
		if (Util.nvl(userManageVO.getCustomView()).equals("")) {
			return new ModelAndView(getUserManageListView(), model);
		} else {
			return new ModelAndView(userManageVO.getCustomView(), model);
		}
	}

	/**
	 * <pre>
	 * 1. 개요 : 사용자 상세조회
	 * </pre>
	 * 
	 * @Author	: Choi Moon Seok
	 * @Date	: 2013. 11. 24.
	 * @Method Name : selectUserDetail
	 * @param request
	 * @param response
	 * @param userManageVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveUserDetail.*")
	public ModelAndView selectUserDetail(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute("userManageVO") UserManageVO userManageVO, ModelMap model) throws Exception {

		UserManageVO resultVO = userManageService.selectUser(userManageVO);
    	BeanUtils.copyCondProperties(userManageVO, resultVO);
    	
        model.addAttribute("userManageVO", resultVO);
        WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_SUCCESS);

		return new ModelAndView(getUserManageDetailView(), model);
	}
	
}
