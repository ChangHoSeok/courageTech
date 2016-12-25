package kr.pe.courage.tech.das.log.exception;

import org.springframework.context.MessageSource;

import egovframework.rte.fdl.cmmn.exception.BaseException;

/**
 * <pre>
 * kr.pe.courage.tech.das.log.exception
 * ExistsLogDetailException.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 7. 18.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 7. 18., 수정자 : ChangHo Seok, 수정내용 : 1.0
 * </pre>
 */
public class ExistsLogDetailException extends BaseException {
	private static final long serialVersionUID = 4629600622229558272L;

	public ExistsLogDetailException() {
		super();
	}
	
	public ExistsLogDetailException(String message) {
		super(message);
	}
	
	public ExistsLogDetailException(Throwable throwable) {
		super(throwable);
	}
	
	public ExistsLogDetailException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public ExistsLogDetailException(MessageSource messageSource, String messageKey) {
		super(messageSource, messageKey);
	}
	
	public ExistsLogDetailException(MessageSource messageSource, String messageKey, Throwable throwable) {
		super(messageSource, messageKey, throwable);
	}
}
