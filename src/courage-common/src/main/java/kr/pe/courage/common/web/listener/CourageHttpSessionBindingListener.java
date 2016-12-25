package kr.pe.courage.common.web.listener;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.log4j.Logger;

/**
 * <pre>
 * kr.pe.courage.common.web.listener
 * CourageHttpSessionBindingListener.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 9. 22.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 9. 22., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class CourageHttpSessionBindingListener implements HttpSessionBindingListener {
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		logger.debug("## valueBound");
		SessionMap.getInstance().put(event.getSession(), event.getName());
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		logger.debug("## valueUnbound");
		SessionMap.getInstance().remove(event.getSession());
	}

}
