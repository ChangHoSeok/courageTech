
package kr.pe.courage.common.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 공통코드 Ajax Token 테그  클래스
 * <pre>
 * kr.pe.courage.common.tags
 * CmmAjaxTokenTag.java
 * </pre>
 *
 * @Author	: Seok Chang Ho
 * @Date	: 2013. 11. 6.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * ================================================================
 * 		수정일		|		수정자			|			수정내용
 * 2013-11-06		석창호					최초등록
 * ================================================================
 * </pre>
 */
public class CmmAjaxTokenTag extends TagSupport {

	private static final long serialVersionUID = -4384333219515326289L;

	public CmmAjaxTokenTag() {
		super();
	}

	private String name;
	private String id = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int doStartTag() throws JspException {
		try {
			StringBuilder html = new StringBuilder();
			makeTagBody(html);
			
			this.pageContext.getOut().println(html.toString());
		} catch (IOException e) {
			throw new JspException(e);
		}

		return SKIP_BODY;
	}
	
	public void makeTagBody(StringBuilder html) {
		html.append("<input type=\"hidden\" name=\"" + this.name + "\" id=\"" + this.id + "\" value=\"" + System.currentTimeMillis() + "\"/>");
	}
}
