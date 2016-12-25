
package kr.pe.courage.common.tags;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import kr.pe.courage.common.annotation.KeepCondition;
import kr.pe.courage.common.utils.BeanUtils;

/**
 * Keep Condition input tag
 * <pre>
 * kr.pe.courage.common.tags
 * ConditionInputTag.java
 * </pre>
 *
 * @Author	: Seok Chang Ho
 * @Date	: 2013. 11. 8.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * ================================================================
 * 		수정일		|		수정자			|			수정내용
 * 2013-11-08		석창호					최초등록
 * ================================================================
 * </pre>
 */
public class ConditionInputTag extends TagSupport {

	private static final long serialVersionUID = 2717861746634839566L;

	public ConditionInputTag() {
		condObject = new Object();
	}

	private Object condObject;

	public Object getCondObject() {
		return condObject;
	}

	public void setCondObject(Object condObject) {
		this.condObject = condObject;
	}

	public int doStartTag() throws JspException {

		try {
			StringBuffer html = new StringBuffer();
			makeSelectTagBody(html);
			this.pageContext.getOut().println(html.toString());
		} catch (IOException ie) {
			throw new JspException(ie);
		} catch (Exception e) {
			throw new JspException(e);
		}

		return SKIP_BODY;
	}

	private void makeSelectTagBody(StringBuffer html) throws Exception {
		BeanUtils.getAttributeNullToEmpty(getCondObject());
		if (getCondObject() != null) {
			Field fields[] = getCondObject().getClass().getDeclaredFields();
			
			html.append(getFieldHtml(fields, getCondObject()));
			getSuperClassFieldHtml(getCondObject().getClass().getSuperclass(), getCondObject(), html);
		}
	}
	
	private boolean getSuperClassFieldHtml(Class superClass, Object obj, StringBuffer resultStr) throws Exception {
		boolean isLoop = true;
		
		while(isLoop) {
			if (superClass != null) {
				Field fields[] = superClass.getDeclaredFields();
				resultStr.append(getFieldHtml(fields, obj));
				isLoop = getSuperClassFieldHtml(superClass.getSuperclass(), obj, resultStr);
			} else {
				isLoop = false;
			}
		}
		
		return isLoop;
	}
	
	private String getFieldHtml(Field[] fields, Object obj) throws Exception {
		StringBuilder resultStr = new StringBuilder();
		boolean isCondProperty = false;
		Object valueObj = null;
		
		
		for (Field field : fields) {
			String valueStr = "";
			String fieldName = field.getName();
			String fieldType = field.getType().getName();
			String methodName = getMethodName(fieldName, "get");

			if (field.getAnnotation(KeepCondition.class) != null) {
				isCondProperty = true;
			} else {
				isCondProperty = false;
			}

			if (isCondProperty) {
				if (fieldType.equals("java.lang.String")) {
					Method getMethod = getCondObject().getClass().getMethod(methodName);
					valueObj = (String) getMethod.invoke(getCondObject());
				} else if (fieldType.equals("boolean")) {
					Method getMethod = getCondObject().getClass().getMethod(methodName);
					valueObj = (Boolean) getMethod.invoke(getCondObject());
				} else if (fieldType.equals("int")) {
					Method getMethod = getCondObject().getClass().getMethod(methodName);
					valueObj = (Integer) getMethod.invoke(getCondObject());
				}
				
				if (valueObj != null) {
					valueStr = String.valueOf(valueObj);
				}
				
				resultStr.append("<input type=\"hidden\" id=\"" + fieldName + "\" name=\"" + fieldName + "\" value=\"" + valueStr + "\"/>").append("\r\n");
			}
		}
		
		return resultStr.toString();
	}

	private String getMethodName(String fieldName, String type) {
		String resultStr = "";
		String upperFieldName = fieldName.toUpperCase();

		resultStr = new StringBuilder().append(type).append(upperFieldName.substring(0, 1)).append(fieldName.substring(1)).toString();

		return resultStr;
	}
}
