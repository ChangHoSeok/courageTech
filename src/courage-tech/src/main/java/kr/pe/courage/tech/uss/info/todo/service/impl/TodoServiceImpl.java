
package kr.pe.courage.tech.uss.info.todo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import kr.pe.courage.common.utils.DateUtil;
import kr.pe.courage.common.utils.Util;
import kr.pe.courage.tech.uss.info.todo.service.TodoService;
import kr.pe.courage.tech.uss.info.todo.service.TodoVO;

/**
 * <pre>
 * kr.pe.courage.tech.uss.info.todo.service.impl
 * TodoServiceImpl.java
 * </pre>
 *
 * @Author : Seok Chang Ho
 * @Date : 2015. 7. 26.
 * @Version :
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 2015. 7. 26.: , 수정자 : Seok Chang Ho, 수정내용 : 최초등록
 * </pre>
 */
@Service("todoService")
public class TodoServiceImpl implements TodoService {

	@Resource(name = "todoDAO")
	private TodoDAO todoDAO;

	@Resource(name = "courageTodoIdGnrService")
	private EgovIdGnrService idGnrService;

	@Override
	public List<TodoVO> selectTodoList(TodoVO todoVO) throws Exception {
		return todoDAO.selectList(todoVO);
	}

	@Override
	public int selectTodoListCnt(TodoVO todoVO) throws Exception {
		return todoDAO.selectListCount(todoVO);
	}

	@Override
	public TodoVO selectTodo(TodoVO todoVO) throws Exception {
		return todoDAO.getById(todoVO);
	}

	@Override
	public void insertTodo(TodoVO todoVO) throws Exception {
		todoVO.setTodoId(idGnrService.getNextStringId());
		todoDAO.insert(todoVO);
	}

	@Override
	public void updateTodo(TodoVO todoVO) throws Exception {
		if (todoVO != null && "Y".equals(Util.nvl(todoVO.getComptAt()))) {
			todoVO.setComptDt(DateUtil.getTimestamp());
		}

		todoDAO.update(todoVO);
	}

	@Override
	public void updateTodoCompt(TodoVO todoVO) throws Exception {
		todoVO.setComptAt(TodoVO.TODO_COMPLEATED);
		todoVO.setComptDt(DateUtil.getTimestamp());

		todoDAO.updateTodoCompt(todoVO);
	}

	@Override
	public void updateTodoUnCompt(TodoVO todoVO) throws Exception {
		todoVO.setComptAt(TodoVO.TODO_UNCOMPLETED);
		todoVO.setComptDt(null);

		todoDAO.updateTodoCompt(todoVO);
	}

	@Override
	public void deleteTodo(TodoVO todoVO) throws Exception {
		todoDAO.delete(todoVO);
	}

	@Override
	public void deleteTodoGroup(TodoVO todoVO) throws Exception {
		todoDAO.deleteTodoGroup(todoVO.getGroupId());
	}
}
