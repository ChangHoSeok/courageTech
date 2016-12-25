package kr.pe.courage.tech.uss.mpe.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.pe.courage.common.prop.PropertiesMap;
import kr.pe.courage.common.web.utils.WebUtils;
import kr.pe.courage.tech.uat.login.service.LoginVO;
import kr.pe.courage.tech.uss.mpe.service.AvatarVO;
import kr.pe.courage.tech.uss.mpe.service.MyPageService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

/**
 * <pre>
 * kr.pe.courage.tech.uss.mypage.web
 * MyPageController.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 10. 21.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 10. 21., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
@Controller("myPageController")
@RequestMapping("/uss/mpe/*")
public class MyPageController {
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
	@Resource(name = "myPageService")
	private MyPageService myPageService;
	
	@Value("#{ussView['avatarListView']}")
	private String avatarListView;
	
	/**
	 * <pre>
	 * 1. 개요 : 
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 10. 23.
	 * @Method Name : retrieveAvatarList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("retrieveAvatarList.*")
	public ModelAndView retrieveAvatarList(ModelMap model) throws Exception {
		List<AvatarVO> avatarList = myPageService.selectAvatarList();
		model.addAttribute("avatarList", avatarList);
		
		return new ModelAndView(avatarListView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : avatar icon 변경
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 11. 3.
	 * @Method Name : modifyAvatarProc
	 * @param avatarVO
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("modifyAvatarProc.*")
	public Map<String, Object> modifyAvatarProc(HttpServletRequest request, AvatarVO avatarVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		avatarVO.setEsntlId(WebUtils.getUserUniqID(request));
		myPageService.updateAvatar(avatarVO);
		
		// Session 설정 값 변경
		LoginVO loginVO = (LoginVO) WebUtils.getSessionAttribute(request, PropertiesMap.getInstance().getValue("system.session.key"));
		loginVO.setAvatarUrl(avatarVO.getIconPath());
		
		resultMap.put(WebUtils.ACTION_MESSAGE, WebUtils.ACTION_STATUS_SUCCESS);
		
		return resultMap;
	}
}
