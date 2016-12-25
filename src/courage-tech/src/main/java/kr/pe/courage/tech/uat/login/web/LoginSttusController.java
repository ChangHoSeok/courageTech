package kr.pe.courage.tech.uat.login.web;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import kr.pe.courage.common.web.listener.SessionMap;
import kr.pe.courage.tech.uat.login.service.impl.HttpSessionCerateTimeComparator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <pre>
 * kr.pe.courage.tech.uat.login.web
 * LoginSttusController.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 9. 28.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 9. 28., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
@Controller("loginSttusController")
@RequestMapping("/uat/login/*")
public class LoginSttusController {
	@Value("#{uatView['formLoginSttusView']}")
	private String formLoginSttusView;

	@Value("#{uatView['loginSttusListView']}")
	private String loginSttusListView;
	
	/**
	 * <pre>
	 * 1. 개요 : 로그인 현황 조회 메인
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 9. 28.
	 * @Method Name : formLoginSttus
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("formLoginSttus.*")
	public ModelAndView formLoginSttus() throws Exception {
		return new ModelAndView(formLoginSttusView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 로그인 현황 목록 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 9. 28.
	 * @Method Name : retrieveLoginSttusList
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("retrieveLoginSttusList.*")
	public ModelAndView retrieveLoginSttusList(ModelMap model) throws Exception {
		List<HttpSession> resultList = SessionMap.getInstance().toList();
		
		// 로그인 시각 오름차순 정렬
		Collections.sort(resultList, new HttpSessionCerateTimeComparator());
		
		model.addAttribute("sessionList", resultList);
		return new ModelAndView(loginSttusListView);
	}
}
