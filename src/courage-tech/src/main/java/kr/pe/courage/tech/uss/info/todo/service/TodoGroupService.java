package kr.pe.courage.tech.uss.info.todo.service;

import java.util.List;

/**
 * <pre>
 * kr.pe.courage.tech.uss.info.todo.service
 * TodoGroupService.java
 * </pre>
 *
 * @Author : Seok Chang Ho
 * @Date : 2015. 7. 26.
 * @Version : 1.0
 * @see
 *
 * 		<pre>
 * << 개정이력 >>
 * 1. 수정일 2015. 7. 26.: , 수정자 : Seok Chang Ho, 수정내용 : 최초등록
 * 1. 수정일 2015. 8. 21.: , 수정자 : Seok Chang Ho, 수정내용 : 데이터 존재유무 확인 method 추가 isExistTodoGroup
 *      </pre>
 */
public interface TodoGroupService {
	/**
	 * <pre>
	 * 1. 개요 : To-do 그룹 목록 조회
	 * </pre>
	 *
	 * @Author	: Seok Chang Ho
	 * @Date	: 2015. 7. 27.
	 * @Method Name : selectTodoGroupList
	 * @param todoGroupVO
	 * @return
	 * @throws Exception
	 */
	public List<TodoGroupVO> selectTodoGroupList(TodoGroupVO todoGroupVO) throws Exception;

	/**
	 * <pre>
	 * 1. 개요 : To-do 그룹 목록 갯수 조회
	 * </pre>
	 *
	 * @Author	: Seok Chang Ho
	 * @Date	: 2015. 7. 27.
	 * @Method Name : selectTodoGroupListCnt
	 * @param todoGroupVO
	 * @return
	 * @throws Exception
	 */
	public int selectTodoGroupListCnt(TodoGroupVO todoGroupVO) throws Exception;

	/**
	 * <pre>
	 * 1. 개요 : To-do 그룹 상세정보 조회
	 * </pre>
	 *
	 * @Author	: Seok Chang Ho
	 * @Date	: 2015. 7. 27.
	 * @Method Name : selectTodoGroup
	 * @param todoGroupVO
	 * @return
	 * @throws Exception
	 */
	public TodoGroupVO selectTodoGroup(TodoGroupVO todoGroupVO) throws Exception;

	/**
	 * <pre>
	 * 1. 개요 : To-do 그룹 정보 존재유무 확인
	 * </pre>
	 *
	 * @Author	: chseok82
	 * @Date	: 2015. 8. 21.
	 * @Method Name : isExistTodoGroup
	 * @param todoGroupVO
	 * @return
	 * @throws Exception
	 */
	public boolean isExistTodoGroup(TodoGroupVO todoGroupVO) throws Exception;

	/**
	 * <pre>
	 * 1. 개요 : To-do 그룹 등록
	 * </pre>
	 *
	 * @Author	: Seok Chang Ho
	 * @Date	: 2015. 7. 27.
	 * @Method Name : insertTodoGroup
	 * @param todoGroupVO
	 * @throws Exception
	 */
	public void insertTodoGroup(TodoGroupVO todoGroupVO) throws Exception;

	/**
	 * <pre>
	 * 1. 개요 : To-do 그룹 수정
	 * </pre>
	 *
	 * @Author	: Seok Chang Ho
	 * @Date	: 2015. 7. 27.
	 * @Method Name : updateTodoGroup
	 * @param todoGroupVO
	 * @throws Exception
	 */
	public void updateTodoGroup(TodoGroupVO todoGroupVO) throws Exception;

	/**
	 * <pre>
	 * 1. 개요 : To-do 그룹 삭제
	 * </pre>
	 *
	 * @Author	: Seok Chang Ho
	 * @Date	: 2015. 7. 27.
	 * @Method Name : deleteTodoGroup
	 * @param todoGroupVO
	 * @throws Exception
	 */
	public void deleteTodoGroup(TodoGroupVO todoGroupVO) throws Exception;

	/**
	 * <pre>
	 * 1. 개요 : To-do 그룹 삭제 (계층 및 종속관계에 있는 정보 모두 삭제)
	 * </pre>
	 *
	 * @Author	: Seok Chang Ho
	 * @Date	: 2015. 7. 27.
	 * @Method Name : truncateTodoGroup
	 * @param todoGroupVO
	 * @throws Exception
	 */
	public void truncateTodoGroup(TodoGroupVO todoGroupVO) throws Exception;
}
