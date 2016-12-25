package kr.pe.courage.common.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import kr.pe.courage.common.utils.Util;

import org.apache.log4j.Logger;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

public class NumberFormatTag extends TagSupport {
	private static final long serialVersionUID = 4800290958334623769L;
	
	private static final Logger logger = Logger.getLogger(NumberFormatTag.class);

	public NumberFormatTag() {
		super();
	}
	
	private String value;
	private String format = ",";

	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) throws JspException {
		this.value = (String)ExpressionEvaluatorManager.evaluate("value", value, String.class, this, pageContext);
	}
	
	public int doStartTag() throws JspException {
		String result = "";
		
		try {
			result = Util.numFormat(value, format);
			
			this.pageContext.getOut().print(result);
		} catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
	}
}
