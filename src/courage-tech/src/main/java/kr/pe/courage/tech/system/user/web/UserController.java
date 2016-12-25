package kr.pe.courage.tech.system.user.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.CouragePaginationInfo;
import kr.pe.courage.common.utils.BeanUtils;
import kr.pe.courage.common.utils.Util;
import kr.pe.courage.common.vo.JxlsParam;
import kr.pe.courage.common.web.utils.WebUtils;
import kr.pe.courage.tech.system.user.service.UserService;
import kr.pe.courage.tech.system.user.service.UserVO;
import kr.pe.courage.tech.uat.login.service.LoginVO;

/**
 * <pre>
 * kr.pe.courage.tech.system.user.web
 * UserController.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 2. 18.
 * @Version	: 1.0
 * @see
 *
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 2. 18., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
@Controller("userController")
@RequestMapping(value = "/system/user/*")
@SessionAttributes("userSbscrbInfo")
public class UserController {
	private final static String USER_CERTIFICATED = "userCertificated";
	
	@Resource(name = "egovUsrCnfrmIdGnrService")
	private EgovIdGnrService idGenService;
	
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	@Resource(name = "userService")
	private UserService userService;

	@Value("#{systemView['formUserView']}")
	private String formUserView;

	@Value("#{systemView['userListView']}")
	private String userListView;

	@Value("#{systemView['userDetailView']}")
	private String userDetailView;

	@Value("#{systemView['userCreateView']}")
	private String userCreateView;
	
	@Value("#{systemView['userModifyView']}")
	private String userModifyView;

	@Value("#{systemView['userSbscrbAgreView']}")
	private String userSbscrbAgreView;

	@Value("#{systemView['userSbscrbCreateView']}")
	private String userSbscrbCreateView;

	@Value("#{systemView['userSbscrbResultView']}")
	private String userSbscrbResultView;

	@Value("#{systemView['userSbscrbDetailView']}")
	private String userSbscrbDetailView;

	@Value("#{systemView['userConfirmView']}")
	private String userConfirmView;

	@Value("#{systemView['userSecsnView']}")
	private String userSecsnView;

	@Value("#{systemView['userSecsnResultView']}")
	private String userSecsnResultView;

	@Value("#{pageConfig['user.page.recordCount']}")
	private int recordCount;

	@Value("#{pageConfig['user.page.size']}")
	private int pageSize;

	@Value("#{pageConfig['user.page.enable']}")
	private String pageEnable;
	
	@ModelAttribute("userSbscrbInfo")
	private UserVO userSbscrbInfo() {
		return new UserVO();
	}
	
	@InitBinder("userSbscrbInfo")
	private void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields("esntlId", "emplyrId", "mberSe", "emailAdres", "deptCode");
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 관리 메인 form 조회
	 * </pre>
	 *
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 2. 18.
	 * @Method Name : formUser
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "formUser.*")
	public ModelAndView formUser(ModelMap model) throws Exception {
		return new ModelAndView(formUserView, model);
	}

	/**
	 * <pre>
	 * 1. 개요 : 사용자 목록 조회
	 * </pre>
	 *
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 2. 18.
	 * @Method Name : retrieveUserList
	 * @param request
	 * @param userVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveUserList.*")
	public ModelAndView retrieveUserList(HttpServletRequest request, UserVO userVO, ModelMap model) throws Exception {
		CouragePaginationInfo pagination = new CouragePaginationInfo(userVO, pageEnable, recordCount, pageSize);
		userVO = (UserVO) pagination.createCustomVo(request);

		pagination.setTotalRecordCount(userService.selectUserListCount(userVO));
		List<UserVO> userList = null;
		
		if (pagination.getTotalRecordCount() > 0) {
			userList = userService.selectUserList(userVO);
		}

		model.addAttribute("pagination", pagination);
		model.addAttribute("userList", userList);

		return new ModelAndView(userListView);
	}

	/**
	 * <pre>
	 * 1. 개요 : 사용자 상세 조회
	 * </pre>
	 *
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 2. 18.
	 * @Method Name : retrieveUserDetail
	 * @param userVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveUserDetail.*")
	public ModelAndView retrieveUserDetail(UserVO userVO, ModelMap model) throws Exception {
		UserVO resultVO = userService.selectUserDetail(userVO);
		BeanUtils.copyCondProperties(userVO, resultVO);
		model.addAttribute("userVO", resultVO);

		return new ModelAndView(userDetailView);
	}

	/**
	 * <pre>
	 * 1. 개요 : 사용자 등록 화면
	 * </pre>
	 *
	 * @Author	: ChangHo
	 * @Date	: 2016. 2. 22.
	 * @Method Name : createUser
	 * @param request
	 * @param userVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createUser.*")
	public ModelAndView createUser(HttpServletRequest request, UserVO userVO, ModelMap model) throws Exception {
		userVO.setMode(UserVO.MODE_CREATE);

		return new ModelAndView(userCreateView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 등록 처리
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 3. 1.
	 * @Method Name : createUserProc
	 * @param request
	 * @param userVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createUserProc.*")
	public ModelAndView createUserProc(HttpServletRequest request, UserVO userVO, BindingResult bindingResult, ModelMap model) throws Exception {
		beanValidator.validate(userVO, bindingResult);
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("validateCheck", "error");
			model.addAttribute("userVO", userVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);
			
			return new ModelAndView(userCreateView, model);
		}
		
		if (userService.selectUserIdExists(userVO)) {
			model.addAttribute(WebUtils.ACTION_MESSAGE, "이미 등록된 아이디 입니다.");
			
			return new ModelAndView(userCreateView, model);
		}
		
		// 회원구분 (미선택 시 일반사용자)
		if (Util.checkNull(userVO.getMberSe())) {
			userVO.setMberSe("USR02");
		}
		
		userVO.setEmplyrSttusCode("A"); // 사용자 상태 '신청'
		userVO.setEsntlId(idGenService.getNextStringId());
		userService.insertUser(userVO);
		
		String params = "?" + WebUtils.ACTION_STATUS_PARAM + "=" + WebUtils.ACTION_STATUS_SUCCESS;
		String returnUrl = "retrieveUserDetail." + WebUtils.getUrlExtension(request);
		
		if (userVO.getSaveLaterView().equals(UserVO.SAVE_LATER_VIEW_DETAIL)) {
			params += "&esntlId=" + userVO.getEsntlId();
		} else if (userVO.getSaveLaterView().equals(UserVO.SAVE_LATER_VIEW_LIST)) {
			returnUrl = "retrieveUserList." + WebUtils.getUrlExtension(request);
		} else if (userVO.getSaveLaterView().equals(UserVO.SAVE_LATER_VIEW_CREATE)) {
			returnUrl = "createUser." + WebUtils.getUrlExtension(request);
		} else {
			params += "&esntlId=" + userVO.getEsntlId();
		}

		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl + params);
		
		return new ModelAndView(redirectView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 가입 승인 처리
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 3. 1.
	 * @Method Name : modifyUserSbscrbConfm
	 * @param esntlId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modifyUserSbscrbConfm.*")
	public ModelAndView modifyUserSbscrbConfm(@RequestParam("esntlId") String esntlId, ModelMap model) throws Exception {
		UserVO userVO = new UserVO();
		userVO.setEsntlId(esntlId);
		
		try {
			userService.updateUserSbscrbConfm(userVO);
			model.addAttribute(WebUtils.ACTION_STATUS_PARAM, WebUtils.ACTION_STATUS_SUCCESS);
		} catch (EgovBizException e) {
			model.addAttribute(WebUtils.ACTION_STATUS_PARAM, WebUtils.ACTION_STATUS_FAILED);
			e.printStackTrace();
		}
		
		return new ModelAndView("jsonView");
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 정보 수정 화면
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 3. 1.
	 * @Method Name : modifyUser
	 * @param request
	 * @param userVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modifyUser.*")
	public ModelAndView modifyUser(HttpServletRequest request, UserVO userVO, ModelMap model) throws Exception {
		UserVO resultVO = userService.selectUserDetail(userVO);
		BeanUtils.copyCondProperties(userVO, resultVO);
		resultVO.setMode(UserVO.MODE_MODIFY);
		
		model.addAttribute("userVO", resultVO);
		
		return new ModelAndView(userModifyView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 정보 수정 처리
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 3. 1.
	 * @Method Name : modifyUserProc
	 * @param request
	 * @param userVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modifyUserProc.*")
	public ModelAndView modifyUserProc(HttpServletRequest request, UserVO userVO, BindingResult bindingResult, ModelMap model) throws Exception {
		beanValidator.validate(userVO, bindingResult);
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("validateCheck", "error");
			model.addAttribute("userVO", userVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);
			
			return new ModelAndView(userCreateView, model);
		}
		
		// 회원구분 (미선택 시 일반사용자)
		if (Util.checkNull(userVO.getMberSe())) {
			userVO.setMberSe("USR02");
		}
		userService.updateUser(userVO);
		
		String condParams = BeanUtils.getConditionParameter(userVO);
		String params = "?esntlId=" + userVO.getEsntlId() + (StringUtils.isEmpty(condParams) ? "" : "&" + condParams);
		String returnUrl = "retrieveUserDetail." + WebUtils.getUrlExtension(request);
		
		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl + params);
		
		return new ModelAndView(redirectView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 정보 삭제
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 3. 20.
	 * @Method Name : deleteUser
	 * @param request
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteUser.*")
	public ModelAndView deleteUser(HttpServletRequest request, UserVO userVO) throws Exception {
		userService.deleteUser(userVO);
		
		String params = "?" + WebUtils.ACTION_STATUS_FAILED + "=" + WebUtils.ACTION_STATUS_SUCCESS;
		String returnUrl = "retrieveUserList." + WebUtils.getUrlExtension(request);
		
		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl(returnUrl + params);
		
		return new ModelAndView(redirectView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 비밀번호 초기화
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 3. 1.
	 * @Method Name : modifyUserInitPassword
	 * @param esntlId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modifyUserInitPassword.*")
	public ModelAndView modifyUserInitPassword(@RequestParam("esntlId") String esntlId, ModelMap model) throws Exception {
		UserVO userVO = new UserVO();
		userVO.setEsntlId(esntlId);
		
		try {
			userService.updateUserInitPassword(userVO);
			model.addAttribute(WebUtils.ACTION_STATUS_PARAM, WebUtils.ACTION_STATUS_SUCCESS);
			model.addAttribute("password", userVO.getPassword());
		} catch (EgovBizException e) {
			model.addAttribute(WebUtils.ACTION_STATUS_PARAM, WebUtils.ACTION_STATUS_FAILED);
			e.printStackTrace();
		}
		
		return new ModelAndView("jsonView");
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 목록 엑셀 저장
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 3. 2.
	 * @Method Name : downloadUserExcel
	 * @param userVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "downloadUserExcel.*")
	public ModelAndView downloadUserExcel(UserVO userVO, ModelMap model) throws Exception {
		userVO.setPagingEnable(UserVO.PAGING_ENABLE_OFF);
		int totalRecordCount = userService.selectUserListCount(userVO);
		List<UserVO> userList = null;

		if (totalRecordCount > 0) {
			userList = userService.selectUserList(userVO);
		}

		JxlsParam jxlsParam = new JxlsParam(JxlsParam.EXCEL_EXT_XLS, "userExcel", "사용자 목록", "사용자 목록", totalRecordCount);
		
		model.addAttribute("list", userList);
		model.addAttribute("jxlsParam", jxlsParam);
		
		return new ModelAndView("jxlsView");
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 가입 동의 화면 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 11. 29.
	 * @Method Name : retrieveUserSbscrbAgre
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveUserSbscrbAgre.*")
	public ModelAndView retrieveUserSbscrbAgre(UserVO userVO) throws Exception {
		return new ModelAndView(userSbscrbAgreView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 가입 화면
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 11. 30.
	 * @Method Name : createUserSbscrb
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createUserSbscrb.*")
	public ModelAndView createUserSbscrb(UserVO userVO, @RequestParam(value = "sbscrbAgre", defaultValue = "disAgre") String sbscrbAgre,
			ModelMap model) throws Exception {
		
		if (!sbscrbAgre.equals("agre")) {
			model.addAttribute(WebUtils.ACTION_MESSAGE, "개인정보처리방침 동의 후 회원가입을 할 수 있습니다.");
			return new ModelAndView(userSbscrbAgreView, model);
		}
		
		return new ModelAndView(userSbscrbCreateView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 가입 처리
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 11. 30.
	 * @Method Name : createUserSbscrbProc
	 * @param request
	 * @param userVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createUserSbscrbProc.*")
	public ModelAndView createUserSbscrbProc(HttpServletRequest request, UserVO userVO, BindingResult bindingResult, ModelMap model) throws Exception {
		beanValidator.validate(userVO, bindingResult);
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("validateCheck", "error");
			model.addAttribute("userVO", userVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);
			
			return new ModelAndView(userSbscrbCreateView, model);
		}
		
		// 사용자 ID는 Email로 대체
		userVO.setEmplyrId(userVO.getEmailId() + "@" + userVO.getEmailDomain());
		userVO.setNcnm(userVO.getUserNm());
		
		if (userService.selectUserIdExists(userVO)) {
			model.addAttribute(WebUtils.ACTION_MESSAGE, "이미 등록된 아이디 입니다.");
			
			return new ModelAndView(userSbscrbCreateView, model);
		}
		
		// 회원구분 (미선택 시 일반사용자)
		if (Util.checkNull(userVO.getMberSe())) {
			userVO.setMberSe("USR02");
		}
		
		userVO.setEmplyrSttusCode("P"); // 사용자 상태 '승인'
		userVO.setEsntlId(idGenService.getNextStringId());
		userService.insertUser(userVO);
		
		return new ModelAndView(userSbscrbResultView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 회원가입 정보 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 12. 2.
	 * @Method Name : retrieveUserSbscrbDetail
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveUserSbscrbDetail.*")
	public ModelAndView retrieveUserSbscrbDetail(HttpServletRequest request, ModelMap model) throws Exception {
		UserVO userVO = new UserVO();
		userVO.setEsntlId(WebUtils.getUserUniqID(request));
		
		UserVO resultVO = userService.selectUserDetail(userVO);
		model.addAttribute("userVO", resultVO);
		model.addAttribute("userSbscrbInfo", resultVO); // 사용자 정보 공유값 설정 (SessionAttribute)
		
		return new ModelAndView(userSbscrbDetailView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 회원가입 정보 수정 처리
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 12. 3.
	 * @Method Name : modifyUserSbscrbProc
	 * @param request
	 * @param userVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modifyUserSbscrbProc.*")
	public ModelAndView modifyUserSbscrbProc(HttpServletRequest request, UserVO userVO, BindingResult bindingResult,
			@ModelAttribute("userSbscrbInfo") UserVO userSbscrbInfo, ModelMap model) throws Exception {
		beanValidator.validate(userVO, bindingResult);
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("validateCheck", "error");
			model.addAttribute("userVO", userVO);
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);
			
			return new ModelAndView(userSbscrbDetailView, model);
		}
		
		userSbscrbInfo.setUserNm(userVO.getUserNm());
		userSbscrbInfo.setNcnm(userVO.getUserNm());
		
		userService.updateUser(userSbscrbInfo);
		WebUtils.setSessionAttribute(request, LoginVO.SESSION_USER_NM, userSbscrbInfo.getUserNm());
		
		if (!Util.checkNull(userVO.getNewPassword())) {
			userSbscrbInfo.setPassword(userVO.getNewPassword());
			userService.updateUserInitPassword(userSbscrbInfo);
		}
		
		model.addAttribute("userVO", userSbscrbInfo);
		WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_SUCCESS);
		
		return new ModelAndView(userSbscrbDetailView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 인증 화면 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 12. 2.
	 * @Method Name : retrieveUserConfirm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveUserConfirm.*")
	public ModelAndView retrieveUserConfirm(HttpServletRequest request) throws Exception {
		// 사용자 인증 상태 초기화
		WebUtils.setSessionAttribute(request, UserController.USER_CERTIFICATED, null);
		return new ModelAndView(userConfirmView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 인증 처리
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 12. 4.
	 * @Method Name : retrieveUserConfirmProc
	 * @param request
	 * @param userVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "retrieveUserConfirmProc.*")
	public boolean retrieveUserConfirmProc(HttpServletRequest request, UserVO userVO, ModelMap model) throws Exception {
		userVO.setEsntlId(WebUtils.getUserUniqID(request));
		boolean userCertificated = userService.userPasswordCertificate(userVO);
		WebUtils.setSessionAttribute(request, UserController.USER_CERTIFICATED, userCertificated);
		
		return userCertificated;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 회원탈퇴 안내 페이지
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 12. 4.
	 * @Method Name : retrieveSecsnInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveSecsnInfo.*")
	public ModelAndView retrieveSecsnInfo() throws Exception {
		return new ModelAndView(userSecsnView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 회원탈퇴 처리
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 12. 4.
	 * @Method Name : userSecsnProc
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "userSecsnProc.*")
	public ModelAndView userSecsnProc(HttpServletRequest request, UserVO userVO) throws Exception {
		userVO.setEsntlId(WebUtils.getUserUniqID(request));
		userService.deleteUser(userVO);
		
		request.getSession().invalidate();
		
		return new ModelAndView(userSecsnResultView);
	}
}
