
package kr.pe.courage.common.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import kr.pe.courage.common.utils.Util;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

/**
 * <pre>
 * kr.pe.courage.cmm.tags
 * ConvHtmlTag.java
 * </pre>
 * 
 * @Author : Seok Chang Ho
 * @Date : 2013. 4. 25.
 * @Version : 1.0
 */
public class ConvHtmlTag extends TagSupport {

	private static final long serialVersionUID = -9203656605503998041L;

	public ConvHtmlTag() {
		super();
	}

	private String source = "";

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		try {
			this.source = (String) ExpressionEvaluatorManager.evaluate("source", source, String.class, this, pageContext);
		} catch (JspException e) {
			e.printStackTrace();
		}
	}

	public int doStartTag() throws JspException {
		try {
			this.pageContext.getOut().print(convHtmlTag(this.source));
		} catch (IOException e) {
			throw new JspException(e);
		}

		return SKIP_BODY;
	}

	private String convHtmlTag(String source) {
		String resultStr = "";

		if (!Util.nvl(source).equals("")) {
			resultStr = source;
			resultStr = resultStr.replaceAll("&lt;", "<");
			resultStr = resultStr.replaceAll("&gt;", ">");
			resultStr = resultStr.replaceAll("&amp;", "&");
			resultStr = resultStr.replaceAll("&quot;", "\"");
			resultStr = resultStr.replaceAll("&apos;", "\'");
		}

		return resultStr;
	}

}
