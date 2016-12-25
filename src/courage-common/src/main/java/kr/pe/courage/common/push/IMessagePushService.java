package kr.pe.courage.common.push;

import java.util.List;

/**
 * <pre>
 * kr.pe.courage.common.push
 * IMessagePushService.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 4. 27.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 4. 27., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public interface IMessagePushService {
	/**
	 * <pre>
	 * 1. 개요 : Push 메시지 목록 갯수 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 5. 17.
	 * @Method Name : selectMessagePushListCount
	 * @param messagePushVO
	 * @return
	 * @throws Exception
	 */
	public int selectMessagePushListCount(MessagePushVO messagePushVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : Push 메시지 목록 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 5. 17.
	 * @Method Name : selectMessagePushList
	 * @param messagePushVO
	 * @return
	 * @throws Exception
	 */
	public List<MessagePushVO> selectMessagePushList(MessagePushVO messagePushVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : Push 메시지 등록
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 5. 18.
	 * @Method Name : insertMessagePush
	 * @param messagePushVO
	 * @throws Exception
	 */
	public void insertMessagePush(MessagePushVO messagePushVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 서버 사이드 메시지 전달
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 4. 27.
	 * @Method Name : doPushMessage
	 * @throws Exception
	 */
	public void doPushMessage() throws Exception;
}
