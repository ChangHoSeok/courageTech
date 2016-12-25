package kr.pe.courage.tech.uss.info.todo.service;

import java.util.List;

/**
 * <pre>
 * kr.pe.courage.tech.uss.info.todo.service
 * TodoService.java
 * </pre>
 *
 * @Author	: Seok Chang Ho
 * @Date	: 2015. 7. 26.
 * @Version	: 
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2015-07-26, 수정자 : Seok Chang Ho, 수정내용 : 최초등록
 * </pre>
 */
public interface TodoService {
	/**
	 * <pre>
	 * 1. 개요 : Todo 목록 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo
	 * @Date	: 2015. 8. 9.
	 * @Method Name : selectTodoList
	 * @param todoVO
	 * @return
	 * @throws Exception
	 */
	public List<TodoVO> selectTodoList(TodoVO todoVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : Todo 목록 갯수 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo
	 * @Date	: 2015. 8. 9.
	 * @Method Name : selectTodoListCnt
	 * @param todoVO
	 * @return
	 * @throws Exception
	 */
	public int selectTodoListCnt(TodoVO todoVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : Todo 정보 상세 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo
	 * @Date	: 2015. 8. 9.
	 * @Method Name : selectTodo
	 * @param todoVO
	 * @return
	 * @throws Exception
	 */
	public TodoVO selectTodo(TodoVO todoVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : Todo 정보 등록
	 * </pre>
	 * 
	 * @Author	: ChangHo
	 * @Date	: 2015. 8. 9.
	 * @Method Name : insertTodo
	 * @param todoVO
	 * @throws Exception
	 */
	public void insertTodo(TodoVO todoVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : Todo 정보 수정
	 * </pre>
	 * 
	 * @Author	: ChangHo
	 * @Date	: 2015. 8. 9.
	 * @Method Name : updateTodo
	 * @param todoVO
	 * @throws Exception
	 */
	public void updateTodo(TodoVO todoVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : Todo 상태 완료로 변경
	 * </pre>
	 * 
	 * @Author	: ChangHo
	 * @Date	: 2015. 8. 10.
	 * @Method Name : updateTodoCompt
	 * @param todoVO
	 * @throws Exception
	 */
	public void updateTodoCompt(TodoVO todoVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : Todo 상태 미완료로 변경
	 * </pre>
	 * 
	 * @Author	: chseok82
	 * @Date	: 2015. 8. 10.
	 * @Method Name : updateTodoUnCompt
	 * @param todoVO
	 * @throws Exception
	 */
	public void updateTodoUnCompt(TodoVO todoVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : Todo 정보 삭제
	 * </pre>
	 * 
	 * @Author	: ChangHo
	 * @Date	: 2015. 8. 9.
	 * @Method Name : deleteTodo
	 * @param todoVO
	 * @throws Exception
	 */
	public void deleteTodo(TodoVO todoVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : Todo 정보 삭제 (그룹 아이디에 해당되는 모든 Todo 정보 삭제)
	 * </pre>
	 * 
	 * @Author	: ChangHo
	 * @Date	: 2015. 8. 9.
	 * @Method Name : deleteTodoGroup
	 * @param todoVO
	 * @throws Exception
	 */
	public void deleteTodoGroup(TodoVO todoVO) throws Exception;
}
