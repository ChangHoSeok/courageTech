
package kr.pe.courage.common.core;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * <pre>
 * kr.pe.courage.common.core
 * BeanLoader.java
 * </pre>
 *
 * @Author	: Seok Chang Ho
 * @Date	: 2013. 10. 31.
 * @Version	: 1.0
 */
public class BeanLoader {
	private WebApplicationContext wac;
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	public BeanLoader(ServletContext context) {
		initWAC(context);
	}

	private void initWAC(ServletContext context) {
		wac = WebApplicationContextUtils.getWebApplicationContext(context);

	}

	public Object getBean(String beanName) {
		return wac.getBean(beanName);
	}
}
