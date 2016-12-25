package kr.pe.courage.tech.uss.info.todo.service.impl;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import kr.pe.courage.common.dao.AbstractDAO;
import kr.pe.courage.tech.uss.info.todo.service.TodoGroupVO;

/**
 * <pre>
 * kr.pe.courage.tech.uss.info.todo.service.impl
 * TodoGroupDAO.java
 * </pre>
 *
 * @Author	: Seok Chang Ho
 * @Date	: 2015. 7. 27.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 2015. 7. 27.: , 수정자 : Seok Chang Ho, 수정내용 : 최초등록 
 * </pre>
 */
@Repository("todoGroupDAO")
public class TodoGroupDAO extends AbstractDAO<TodoGroupVO> {

	@Override
	protected String getNameSpace() {
		return "todoGroupDAO";
	}
}
