
package kr.pe.courage.common.core;

import java.io.Serializable;

import kr.pe.courage.common.annotation.KeepCondition;

/**
 * <pre>
 * kr.pe.courage.common.core
 * CommonPopupFormVO.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2014. 10. 14.
 * @Version	: 
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2014. 10. 14., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class CommonPopupFormVO implements Serializable {
	private static final long serialVersionUID = 7128514431611290763L;

	@KeepCondition
	private String openerKey;

	@KeepCondition
	private String returnFunction;

	public String getOpenerKey() {
		return openerKey;
	}

	public void setOpenerKey(String openerKey) {
		this.openerKey = openerKey;
	}

	public String getReturnFunction() {
		return returnFunction;
	}

	public void setReturnFunction(String returnFunction) {
		this.returnFunction = returnFunction;
	}
}
