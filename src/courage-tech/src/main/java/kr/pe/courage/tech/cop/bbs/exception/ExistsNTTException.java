package kr.pe.courage.tech.cop.bbs.exception;

import org.springframework.context.MessageSource;

import egovframework.rte.fdl.cmmn.exception.BaseException;

/**
 * <pre>
 * kr.pe.courage.tech.cop.bbs.exception
 * ExistsNTTException.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 5. 30.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 5. 30., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class ExistsNTTException extends BaseException {
	private static final long serialVersionUID = -8138253849735510884L;
	
	public ExistsNTTException() {
		super();
	}
	
	public ExistsNTTException(String message) {
		super(message);
	}
	
	public ExistsNTTException(Throwable throwable) {
		super(throwable);
	}
	
	public ExistsNTTException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public ExistsNTTException(MessageSource messageSource, String messageKey) {
		super(messageSource, messageKey);
	}
	
	public ExistsNTTException(MessageSource messageSource, String messageKey, Throwable throwable) {
		super(messageSource, messageKey, throwable);
	}
}
