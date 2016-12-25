package kr.pe.courage.common.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

public abstract class AbstractErrorController {
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
	/*
	 * 시스템 에러 view (default 오류)
	 */
	protected abstract String getErrorSystemView();
	
	/*
	 * 404 에러 view
	 */
	protected abstract String getError404View();
	
	/*
	 * 505 에러 view
	 */
	protected abstract String getError505View();
	
	/*
	 * 데이터 접근 에러 view
	 */
	protected abstract String getErrorAccessView();
	
	/*
	 * 세션 종료 에러 view
	 */
	protected abstract String getErrorSessionView();
	
	/*
	 * 권한 에러 view
	 */
	protected abstract String getErrorAuthView();

	/*
	 * 유효하지 않은 접근 방식 에러 view
	 */
	protected abstract String getErrorInvalidAccessView();
	
	@RequestMapping(value = "errorSystem.*")
	public ModelAndView errorSystem() throws Exception {
		return new ModelAndView(getErrorSystemView());
	}

	@RequestMapping(value = "error404.*")
	public ModelAndView error404() throws Exception {
		return new ModelAndView(getError404View());
	}

	@RequestMapping(value = "error505.*")
	public ModelAndView error505() throws Exception {
		return new ModelAndView(getError505View());
	}

	@RequestMapping(value = "errorAccess.*")
	public ModelAndView errorAccess() throws Exception {
		return new ModelAndView(getErrorAccessView());
	}
	
	@RequestMapping(value = "errorInvalidAccess.*")
	public ModelAndView errorInvalidAccess() throws Exception {
		return new ModelAndView(getErrorInvalidAccessView());
	}

	@RequestMapping(value = "errorSession.*")
	public ModelAndView errorSession() throws Exception {
		return new ModelAndView(getErrorSessionView());
	}

	@RequestMapping(value = "errorAuth.*")
	public ModelAndView errorAuth() throws Exception {
		return new ModelAndView(getErrorAuthView());
	}
}
