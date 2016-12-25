package kr.pe.courage.common.push.impl;

import java.sql.SQLException;
import java.util.List;

import kr.pe.courage.common.dao.AbstractDAO;
import kr.pe.courage.common.push.MessagePushVO;

import org.springframework.stereotype.Repository;

/**
 * <pre>
 * kr.pe.courage.common.push.impl
 * MessagePushDAO.java
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
@Repository("messagePushDAO")
public class MessagePushDAO extends AbstractDAO<MessagePushVO> {

	@Override
	protected String getNameSpace() {
		return "messagePushDAO";
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 메시지 발송상태 변경
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 5. 12.
	 * @Method Name : updateSndngAt
	 * @param messagePushVO
	 * @throws SQLException
	 */
	public void updateSndngAt(MessagePushVO messagePushVO) throws SQLException {
		this.update("updateSndngAt", messagePushVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : Push 메시지 목록 조회 (발송할 메시지)
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 5. 17.
	 * @Method Name : selectPushMessageList
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<MessagePushVO> selectPushMessageList() throws SQLException {
		return this.selectList("selectPushMessageList", null);
	}
}
