
package kr.pe.courage.tech.uss.info.todo;

import java.util.List;

import javax.annotation.Resource;

import junit.framework.TestCase;
import kr.pe.courage.tech.uss.info.todo.service.TodoService;
import kr.pe.courage.tech.uss.info.todo.service.TodoVO;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:kr/pe/courage/spring/common/context-*.xml",
		"classpath*:egovframework/spring/context-*.xml", "classpath*:kr/pe/courage/spring/context-*.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class TestTodo extends TestCase {

	@Resource(name = "todoService")
	private TodoService todoService;

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testSelectList() {
		TodoVO todoVO = new TodoVO();

		try {
			List<TodoVO> todoList = todoService.selectTodoList(todoVO);
		} catch (Exception e) {
			fail("testSelectList fail");
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertTodo() {
		TodoVO todoVO = new TodoVO();
		todoVO.setGroupId("TODOGROUP_0000000000");
		todoVO.setSj("CTSA 만들기");
		todoVO.setDc("CTSA 만들기... 상세설명");
		todoVO.setComptAt(TodoVO.TODO_UNCOMPLETED);
		todoVO.setRegisterId("00000000000000000001");

		try {
			todoService.insertTodo(todoVO);
		} catch (Exception e) {
			fail("testInsertTodo fail");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSelectTodo() {
		TodoVO todoVO = new TodoVO();
		todoVO.setGroupId("TODOGROUP_0000000000");
		todoVO.setTodoId("TODO_000000000000000");
		
		try {
			assertNotNull(todoService.selectTodo(todoVO));
		} catch (Exception e) {
			fail("testSelectTodo fail");
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateTodo() {
		TodoVO todoVO = new TodoVO();
		todoVO.setGroupId("TODOGROUP_0000000000");
		todoVO.setTodoId("TODO_000000000000000");
		todoVO.setSj("CTSA 만들기_수정");
		todoVO.setDc("CTSA 만들기... 상세설명");
		todoVO.setComptAt(TodoVO.TODO_COMPLEATED);
		todoVO.setUpdusrId("00000000000000000001");

		try {
			todoService.updateTodo(todoVO);
		} catch (Exception e) {
			fail("testUpdateTodo fail");
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateTodoCompt() {
		TodoVO todoVO = new TodoVO();
		todoVO.setGroupId("TODOGROUP_0000000000");
		todoVO.setTodoId("TODO_000000000000000");

		try {
			todoService.updateTodoCompt(todoVO);
			assertEquals(todoService.selectTodo(todoVO).getComptAt(), TodoVO.TODO_COMPLEATED);
		} catch (Exception e) {
			fail("testUpdateTodoCompt fail");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateTodoUnCompt() {
		TodoVO todoVO = new TodoVO();
		todoVO.setGroupId("TODOGROUP_0000000000");
		todoVO.setTodoId("TODO_000000000000000");

		try {
			todoService.updateTodoUnCompt(todoVO);
			assertEquals(todoService.selectTodo(todoVO).getComptAt(), TodoVO.TODO_UNCOMPLETED);
		} catch (Exception e) {
			fail("testUpdateTodoUnCompt fail");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteTodo() {
		TodoVO todoVO = new TodoVO();
		todoVO.setGroupId("TODOGROUP_0000000000");
		todoVO.setTodoId("TODO_000000000000000");

		try {
			todoService.deleteTodo(todoVO);
			assertNull(todoService.selectTodo(todoVO));
		} catch (Exception e) {
			fail("testDeleteTodo fail");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteTodoGroup() {
		TodoVO todoVO = new TodoVO();
		todoVO.setGroupId("TODOGROUP_0000000000");
		
		try {
			todoService.deleteTodoGroup(todoVO);
			
			todoVO.setCondGroupId("TODOGROUP_0000000000");
			assertEquals(todoService.selectTodoListCnt(todoVO), 0);
		} catch (Exception e) {
			fail("testDeleteTodoGroup fail");
			e.printStackTrace();
		}
	}
}
