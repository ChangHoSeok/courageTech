
package kr.pe.courage.common.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import kr.pe.courage.common.prop.PropertiesMap;
import kr.pe.courage.common.utils.Util;

/**
 * <pre>
 * kr.pe.courage.cmm.tags
 * PropertiesTag.java
 * </pre>
 * 
 * @Author : Seok Chang Ho
 * @Date : 2013. 4. 11.
 * @Version : 1.0
 */
public class PropertiesTag extends TagSupport {
	private static final long serialVersionUID = 8973937366692308669L;

	public PropertiesTag() {
		super();
	}

	private String key = "";

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		try {
			this.key = (String) ExpressionEvaluatorManager.evaluate("key", key, String.class, this, pageContext);
		} catch (JspException e) {
			e.printStackTrace();
		}
	}

	public int doStartTag() throws JspException {

		try {
			this.pageContext.getOut().print(Util.nvl(PropertiesMap.getInstance().getValue(getKey())));
		} catch (IOException e) {
			throw new JspException(e);
		}

		return SKIP_BODY;
	}

}
