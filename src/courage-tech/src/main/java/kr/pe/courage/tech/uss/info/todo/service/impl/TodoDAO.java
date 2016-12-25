package kr.pe.courage.tech.uss.info.todo.service.impl;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import kr.pe.courage.common.dao.AbstractDAO;
import kr.pe.courage.tech.uss.info.todo.service.TodoVO;

/**
 * <pre>
 * kr.pe.courage.tech.uss.info.todo.service.impl
 * TodoDAO.java
 * </pre>
 *
 * @Author	: chseok82
 * @Date	: 2015. 8. 10.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2015. 8. 10., 수정자 : chseok82, 수정내용 : 최초등록
 * </pre>
 */
@Repository("todoDAO")
public class TodoDAO extends AbstractDAO<TodoVO> {

	@Override
	protected String getNameSpace() {
		return "todoDAO";
	}
	
	/**
	 * <pre>
	 * 1. 개요 : To-do GroupId에 포함된 모든 데이터 삭제
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2015. 7. 29.
	 * @Method Name : deleteTodoGroup
	 * @param groupId
	 * @throws SQLException
	 */
	public void deleteTodoGroup(String groupId) throws SQLException {
		this.delete("deleteTodoGroup", groupId);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : Todo 완료상태 변경
	 * </pre>
	 * 
	 * @Author	: chseok82
	 * @Date	: 2015. 8. 10.
	 * @Method Name : updateTodoCompt
	 * @param todoVO
	 * @throws SQLException
	 */
	public void updateTodoCompt(TodoVO todoVO) throws SQLException {
		this.update("updateTodoCompt", todoVO);
	}
}
