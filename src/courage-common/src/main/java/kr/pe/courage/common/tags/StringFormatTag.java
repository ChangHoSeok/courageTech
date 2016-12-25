package kr.pe.courage.common.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import kr.pe.courage.common.utils.Util;

import org.apache.log4j.Logger;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

public class StringFormatTag extends TagSupport {
	private static final long serialVersionUID = -3396479328070073711L;
	
	private static final Logger logger = Logger.getLogger(StringFormatTag.class);

	public StringFormatTag() {
		super();
	}
	
	private String value;
	private String format;
	private char pattern;

	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		try {
			this.format = (String) ExpressionEvaluatorManager.evaluate("format", format, String.class, this, pageContext);
		} catch (JspException e) {
			e.printStackTrace();
		};
	}
	public char getPattern() {
		return pattern;
	}
	public void setPattern(char pattern) {
		this.pattern = pattern;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		try {
			this.value = (String) ExpressionEvaluatorManager.evaluate("value", value, String.class, this, pageContext);
		} catch (JspException e) {
			e.printStackTrace();
		}
	}
	
	public int doStartTag() throws JspException {
		String result = "";
		try {
			logger.debug("##value = " + getValue());
			logger.debug("##format = " + getFormat());
			logger.debug("##pattern = " + getPattern());
			
			if(!getValue().equals("")) {
				result = Util.strFormat(getValue(), getFormat(), getPattern());
			}
			
			this.pageContext.getOut().print(result);
		} catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
	}
}
