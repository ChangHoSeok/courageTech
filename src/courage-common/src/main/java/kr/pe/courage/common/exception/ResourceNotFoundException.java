package kr.pe.courage.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <pre>
 * kr.pe.courage.common.exception
 * ResourceNotFoundException.java
 * </pre>
 *
 * @Author	: chseok82
 * @Date	: 2015. 8. 21.
 * @Version	: 1.0
 * @see
 *
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2015. 8. 21., 수정자 : chseok82, 수정내용 : 최초등록
 * </pre>
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 4163673090393527062L;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException(Throwable throwable) {
		super(throwable);
	}

	public ResourceNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
