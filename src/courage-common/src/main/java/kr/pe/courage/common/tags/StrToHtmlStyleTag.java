
package kr.pe.courage.common.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import kr.pe.courage.common.utils.Util;

public class StrToHtmlStyleTag extends TagSupport {
	private static final long serialVersionUID = -3880017913158230142L;

	private static final Logger logger = Logger.getLogger(StrToHtmlStyleTag.class);

	public StrToHtmlStyleTag() {
		super();
	}

	private String source = "";
	private boolean escapeXml = true;

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

	public boolean isEscapeXml() {
		return escapeXml;
	}

	public void setEscapeXml(boolean escapeXml) {
		this.escapeXml = escapeXml;
	}

	public int doStartTag() throws JspException {
		String result = "";
		try {
			logger.debug("##source = " + getSource());

			if (!getSource().equals("")) {
				result = getSource();
				
				if (escapeXml) {
					result = Util.clearXSS(result);
				}
				
				result = result.replaceAll(" ", "&nbsp;");
				result = result.replaceAll("\r\n", "<BR />");
				result = result.replaceAll("\n", "<BR />");
			}

			this.pageContext.getOut().print(result);
		} catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
	}

}
