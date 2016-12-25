
package kr.pe.courage.tech.common.web;

import kr.pe.courage.common.controller.AbstractErrorController;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <pre>
 * kr.pe.courage.cmm.web
 * ErrorController.java
 * </pre>
 * 
 * @Author : Seok Chang Ho
 * @Date : 2013. 4. 4.
 * @Version : 1.0
 */
@Controller("errorController")
@RequestMapping(value = "/error/*")
public class ErrorController extends AbstractErrorController {
	
	@Value("#{commonView['errorSystemView']}")
	private String errorSystemView;
	
	@Value("#{commonView['error404View']}")
	private String error404View;
	
	@Value("#{commonView['error505View']}")
	private String error505View;
	
	@Value("#{commonView['errorAccessView']}")
	private String errorAccessView;
	
	@Value("#{commonView['errorSessionView']}")
	private String errorSessionView;
	
	@Value("#{commonView['errorAuthView']}")
	private String errorAuthView;

	@Value("#{commonView['errorInvalidAccessView']}")
	private String errorInvalidAccessView;

	@Override
	protected String getErrorSystemView() {
		return errorSystemView;
	}

	@Override
	protected String getError404View() {
		return error404View;
	}

	@Override
	protected String getError505View() {
		return error505View;
	}

	@Override
	protected String getErrorAccessView() {
		return errorAccessView;
	}

	@Override
	protected String getErrorSessionView() {
		return errorSessionView;
	}

	@Override
	protected String getErrorAuthView() {
		return errorAuthView;
	}

	@Override
	protected String getErrorInvalidAccessView() {
		return errorInvalidAccessView;
	}
	
	
}
