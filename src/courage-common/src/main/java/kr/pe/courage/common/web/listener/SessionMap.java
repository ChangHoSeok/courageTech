
package kr.pe.courage.common.web.listener;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

import kr.pe.courage.common.prop.PropertiesMap;
import kr.pe.courage.common.user.UserVO;

import org.apache.log4j.Logger;

/**
 * <pre>
 * kr.pe.courage.common.web.listener
 * SessionMap.java
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
public class SessionMap {
	static private SessionMap m_SessionMap;
	static private ConcurrentHashMap<HttpSession, Object> sessionMap = new ConcurrentHashMap<HttpSession, Object>();
	
	private final Logger logger = Logger.getLogger(this.getClass());

	private SessionMap() {
		super();
	}

	static public SessionMap getInstance() {
		if (m_SessionMap == null) {
			synchronized (SessionMap.class) {
				if (m_SessionMap == null) {
					m_SessionMap = new SessionMap();
				}
			}
		}

		return m_SessionMap;
	}
	
	public void put(HttpSession session, String loginId) {
		sessionMap.put(session, loginId);
		printSessionList();
	}
	
	public HttpSession get(String loginId) {
		String sessionKey = PropertiesMap.getInstance().getValue("system.session.key");
		Enumeration<HttpSession> keys = sessionMap.keys();
		
		while (keys.hasMoreElements()) {
			HttpSession storeSession = (HttpSession) keys.nextElement();
			UserVO storeUserVO = (UserVO) storeSession.getAttribute(sessionKey);
			
			if (loginId.equals(storeUserVO.getUniqId())) {
				return storeSession;
			}
		}
		
		return null;
	}
	
	public boolean isExists(String loginId) {
		boolean resultFlag = false;
		String sessionKey = PropertiesMap.getInstance().getValue("system.session.key");
		Enumeration<HttpSession> keys = sessionMap.keys();
		
		while (keys.hasMoreElements()) {
			HttpSession storeSession = (HttpSession) keys.nextElement();
			UserVO storeUserVO = (UserVO) storeSession.getAttribute(sessionKey);
			
			if (loginId.equals(storeUserVO.getUniqId())) {
				resultFlag = true;
				break;
			}
		}
		
		return resultFlag;
	}
	
	public void logout(String loginId) {
		String sessionKey = PropertiesMap.getInstance().getValue("system.session.key");
		Enumeration<HttpSession> keys = sessionMap.keys();
		
		while (keys.hasMoreElements()) {
			HttpSession storeSession = (HttpSession) keys.nextElement();
			UserVO storeUserVO = (UserVO) storeSession.getAttribute(sessionKey);
			
			if (loginId.equals(storeUserVO.getUniqId())) {
				storeSession.invalidate();
				break;
			}
		}
	}
	
	public void remove(HttpSession session) {
		sessionMap.remove(session);
		printSessionList();
	}
	
	public List<HttpSession> toList() {
		return new ArrayList<HttpSession>(sessionMap.keySet());
	}
	
	private void printSessionList() {
		if (logger.isDebugEnabled()) {
			String sessionKey = PropertiesMap.getInstance().getValue("system.session.key");
			Enumeration<HttpSession> keys = sessionMap.keys();
			
			logger.debug("## sessionMap List ##");
			
			while (keys.hasMoreElements()) {
				HttpSession session = (HttpSession) keys.nextElement();
				UserVO userVO = (UserVO) session.getAttribute(sessionKey);
				
				logger.debug("## user = " + userVO.getUniqId() + ", " + userVO.getName());
			}
		}
	}
}
