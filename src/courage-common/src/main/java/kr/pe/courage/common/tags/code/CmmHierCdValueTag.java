
package kr.pe.courage.common.tags.code;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import kr.pe.courage.common.hiercode.HierCodeMap;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

/**
 * 공통코드 코드명 조회
 * 
 * <pre>
 * egovframework.com.sym.ccm.tags
 * CmmCdValueTag.java
 * </pre>
 * 
 * @Author : Chang Ho Seok
 * @Date : 2013. 11. 4.
 * @Version : 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * ================================================================
 * 		수정일		|		수정자			|			수정내용
 * 2013-11-04		석창호					최초등록
 * ================================================================
 * </pre>
 */
public class CmmHierCdValueTag extends TagSupport {

	private static final long serialVersionUID = -8134079391182574135L;
	
	public CmmHierCdValueTag() {
		super();
	}

	private String groupId;
	private String code;
	private String upperCode;
	
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		try {
			this.groupId = (String) ExpressionEvaluatorManager.evaluate("groupId", groupId, String.class, this, pageContext);
		} catch (JspException e) {
			e.printStackTrace();
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		try {
			this.code = (String) ExpressionEvaluatorManager.evaluate("code", code, String.class, this, pageContext);
		} catch (JspException e) {
			e.printStackTrace();
		}
	}
	
	public String getUpperCode(){
		return upperCode;
	}
	
	public void setUpperCode(String upperCode){
		try {
			this.upperCode = (String) ExpressionEvaluatorManager.evaluate("upperCode", upperCode, String.class, this, pageContext);
		} catch (JspException e) {
			e.printStackTrace();
		}
	}

	public int doStartTag() throws JspException {
		try {
			this.pageContext.getOut().print(HierCodeMap.getInstance().getHierCodeName(getGroupId(), getCode()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return SKIP_BODY;
	}
}
