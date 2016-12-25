package kr.pe.courage.tech.uss.info.todo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import kr.pe.courage.tech.uss.info.todo.service.TodoGroupService;
import kr.pe.courage.tech.uss.info.todo.service.TodoGroupVO;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * <pre>
 * kr.pe.courage.tech.uss.info.todo.service.impl
 * TodoGroupServiceImpl.java
 * </pre>
 *
 * @Author : Seok Chang Ho
 * @Date : 2015. 7. 27.
 * @Version : 1.0
 * @see
 *
 * 		<pre>
 * << 개정이력 >>
 * 1. 수정일 2015. 7. 27.: , 수정자 : Seok Chang Ho, 수정내용 : 최초등록
 *      </pre>
 */
@Service("todoGroupService")
public class TodoGroupServiceImpl extends AbstractServiceImpl implements TodoGroupService {

	@Resource(name = "courageTodoGroupIdGnrService")
	private EgovIdGnrService idGnrService;

	@Resource(name = "todoGroupDAO")
	private TodoGroupDAO todoGroupDAO;

	@Resource(name = "todoDAO")
	private TodoDAO todoDAO;

	@Override
	public List<TodoGroupVO> selectTodoGroupList(TodoGroupVO todoGroupVO) throws Exception {
		return todoGroupDAO.selectList(todoGroupVO);
	}

	@Override
	public int selectTodoGroupListCnt(TodoGroupVO todoGroupVO) throws Exception {
		return todoGroupDAO.selectListCount(todoGroupVO);
	}

	@Override
	public TodoGroupVO selectTodoGroup(TodoGroupVO todoGroupVO) throws Exception {
		return todoGroupDAO.getById(todoGroupVO);
	}

	@Override
	public boolean isExistTodoGroup(TodoGroupVO todoGroupVO) throws Exception {
		return todoGroupDAO.isExist(todoGroupVO);
	}

	@Override
	public void insertTodoGroup(TodoGroupVO todoGroupVO) throws Exception {
		todoGroupVO.setGroupId(idGnrService.getNextStringId());
		todoGroupDAO.insert(todoGroupVO);
	}

	@Override
	public void updateTodoGroup(TodoGroupVO todoGroupVO) throws Exception {
		todoGroupDAO.update(todoGroupVO);
	}

	@Override
	public void deleteTodoGroup(TodoGroupVO todoGroupVO) throws Exception {
		todoGroupDAO.delete(todoGroupVO);
	}

	@Override
	public void truncateTodoGroup(TodoGroupVO todoGroupVO) throws Exception {
		todoDAO.deleteTodoGroup(todoGroupVO.getGroupId());
		todoGroupDAO.delete(todoGroupVO);
	}

}
