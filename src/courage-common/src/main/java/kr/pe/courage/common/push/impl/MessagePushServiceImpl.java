
package kr.pe.courage.common.push.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import kr.pe.courage.common.prop.PropertiesMap;
import kr.pe.courage.common.push.IMessagePushService;
import kr.pe.courage.common.push.MessagePushVO;

import org.apache.log4j.Logger;
import org.directwebremoting.Container;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ServerContextFactory;
import org.directwebremoting.extend.ScriptSessionManager;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * kr.pe.courage.common.push.impl
 * MessagePushServiceImpl.java
 * </pre>
 * 
 * @Author : ChangHo Seok
 * @Date : 2016. 4. 27.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 4. 27., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
@Service("messagePushService")
public class MessagePushServiceImpl implements IMessagePushService {
	private final Logger logger = Logger.getLogger(MessagePushServiceImpl.class);

	@Resource(name = "messagePushDAO")
	private MessagePushDAO messagePushDAO;

	@Override
	public void doPushMessage() throws Exception {
		String jsFunction = PropertiesMap.getInstance().getValue("js.function.pushMsg");
		
		Container container = ServerContextFactory.get().getContainer();
		ScriptSessionManager manager = container.getBean(ScriptSessionManager.class);
		
		Collection<ScriptSession> sessionList = manager.getAllScriptSessions();

		long currentTime = System.currentTimeMillis();
		
		if (logger.isDebugEnabled()) {
			logger.debug("scriptSessionTimeout = " + manager.getScriptSessionTimeout());
			logger.debug("sessionCount = " + sessionList.size());
		}
		
		List<MessagePushVO> messageList = messagePushDAO.selectPushMessageList();
		
		if (messageList != null && messageList.size() > 0) {
			for (ScriptSession scriptSession : sessionList) {
				
				if (logger.isDebugEnabled()) {
					logger.debug("===================================================");
					logger.debug("scriptSession id = " + scriptSession.getId());
					logger.debug("scriptSession page = " + scriptSession.getPage());
					logger.debug("scriptSession lastAccessedTime = " + scriptSession.getLastAccessedTime());
					logger.debug("scriptSession currentTime = " + currentTime);
					logger.debug("scriptSession elapseTime = " + (currentTime - scriptSession.getLastAccessedTime()) + "ms");
					
					Iterator<String> attrNames = scriptSession.getAttributeNames();
					while (attrNames.hasNext()) {
						String attrNm = (String) attrNames.next();
						logger.debug(attrNm + " = " + scriptSession.getAttribute(attrNm));
					}
					logger.debug("===================================================");
				}
				
				// 1분 주기로 호출하므로 1분 이상유지된 세션은 종료
				if (currentTime - scriptSession.getLastAccessedTime() > 61000) {
					scriptSession.invalidate();
				} else {
					for (MessagePushVO messagePushVO : messageList) {
						ScriptBuffer sb = new ScriptBuffer();
						sb.appendCall(jsFunction, messagePushVO); // DWR 설정에 등록된 Object 사용
						
						scriptSession.addScript(sb);
					}
				}
			}
			
			// 메시지 발송상태 변경
			for (MessagePushVO messagePushVO : messageList) {
				messagePushVO.setMssageSndngAt("Y");
				messagePushDAO.updateSndngAt(messagePushVO);
			}
		}		
	}

	@Override
	public int selectMessagePushListCount(MessagePushVO messagePushVO) throws Exception {
		return messagePushDAO.selectListCount(messagePushVO);
	}

	@Override
	public List<MessagePushVO> selectMessagePushList(MessagePushVO messagePushVO) throws Exception {
		return messagePushDAO.selectList(messagePushVO);
	}

	@Override
	public void insertMessagePush(MessagePushVO messagePushVO) throws Exception {
		messagePushDAO.insert(messagePushVO);
	}
}