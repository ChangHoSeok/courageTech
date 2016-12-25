package kr.pe.courage.tech.cop.bbs.exception;

import org.springframework.context.MessageSource;

import egovframework.rte.fdl.cmmn.exception.BaseException;

/**
 * <pre>
 * kr.pe.courage.tech.cop.bbs.exception
 * ExistsReplyNTTException.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 5. 31.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 5. 31., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class ExistsReplyNTTException extends BaseException {
	private static final long serialVersionUID = -8138253849735510884L;
	
	public ExistsReplyNTTException() {
		super();
	}
	
	public ExistsReplyNTTException(String message) {
		super(message);
	}
	
	public ExistsReplyNTTException(Throwable throwable) {
		super(throwable);
	}
	
	public ExistsReplyNTTException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public ExistsReplyNTTException(MessageSource messageSource, String messageKey) {
		super(messageSource, messageKey);
	}
	
	public ExistsReplyNTTException(MessageSource messageSource, String messageKey, Throwable throwable) {
		super(messageSource, messageKey, throwable);
	}
}
