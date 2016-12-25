
package kr.pe.courage.common.exception;

/**
 * <pre>
 * kr.pe.courage.common.exception
 * SQLiteBusyException.java
 * </pre>
 * 
 * @Author : ChangHo Seok
 * @Date : 2016. 8. 18.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 8. 18., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class SQLiteBusyException extends RuntimeException {
	private static final long serialVersionUID = 1052168577910333011L;

	public SQLiteBusyException() {
		super();
	}

	public SQLiteBusyException(String message) {
		super(message);
	}

	public SQLiteBusyException(Throwable throwable) {
		super(throwable);
	}

	public SQLiteBusyException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
