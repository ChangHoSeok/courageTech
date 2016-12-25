package kr.pe.courage.tech.das.log.exception;

import org.springframework.context.MessageSource;

import egovframework.rte.fdl.cmmn.exception.BaseException;

/**
 * <pre>
 * kr.pe.courage.tech.das.log.exception
 * ExistsLogFileRceptException.java
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
public class ExistsLogFileRceptException extends BaseException {
	private static final long serialVersionUID = -7393378806837541111L;

	public ExistsLogFileRceptException() {
		super();
	}
	
	public ExistsLogFileRceptException(String message) {
		super(message);
	}
	
	public ExistsLogFileRceptException(Throwable throwable) {
		super(throwable);
	}
	
	public ExistsLogFileRceptException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public ExistsLogFileRceptException(MessageSource messageSource, String messageKey) {
		super(messageSource, messageKey);
	}
	
	public ExistsLogFileRceptException(MessageSource messageSource, String messageKey, Throwable throwable) {
		super(messageSource, messageKey, throwable);
	}
}
