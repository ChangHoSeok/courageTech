package kr.pe.courage.tech.uss.info.todo;

import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import junit.framework.TestCase;
import kr.pe.courage.tech.uss.info.todo.service.TodoGroupService;
import kr.pe.courage.tech.uss.info.todo.service.TodoGroupVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath*:kr/pe/courage/spring/common/context-*.xml",
		"classpath*:egovframework/spring/context-*.xml",
		"classpath*:kr/pe/courage/spring/context-*.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class TestTodoGroup extends TestCase {
	
	@Resource(name = "todoGroupService")
	private TodoGroupService todoGroupService;

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testSelectList() {
		TodoGroupVO todoGroupVO = new TodoGroupVO();
		
		try {
			List<TodoGroupVO> todoGroupList = todoGroupService.selectTodoGroupList(todoGroupVO);
		} catch (Exception e) {
			fail("testSelectList fail");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsertTodoGroup() {
		TodoGroupVO todoGroupVO = new TodoGroupVO();
		todoGroupVO.setGroupName("CTSA");
		todoGroupVO.setGroupDesc("Courage TimeStamp Agent");
		todoGroupVO.setRegisterId("00000000000000000001");

		try {
			todoGroupService.insertTodoGroup(todoGroupVO);
		} catch (Exception e) {
			fail("testInsertTodoGroup fail");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateTodoGroup() {
		TodoGroupVO todoGroupVO = new TodoGroupVO();
		todoGroupVO.setGroupId("TODOGROUP_0000000000");
		todoGroupVO.setGroupName("testUpdateTodoGroup");
		todoGroupVO.setGroupDesc("Courage TimeStamp Agent");
		todoGroupVO.setUpdusrId("00000000000000000001");

		try {
			todoGroupService.updateTodoGroup(todoGroupVO);
		} catch (Exception e) {
			fail("testUpdateTodoGroup fail");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSelectTodoGroup() {
		TodoGroupVO todoGroupVO = new TodoGroupVO("TODOGROUP_0000000000");
		
		try {
			assertNotNull(todoGroupService.selectTodoGroup(todoGroupVO));
		} catch (Exception e) {
			fail("testSelectTodoGroup fail");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteTodoGroup() {
		TodoGroupVO todoGroupVO = new TodoGroupVO("TODOGROUP_0000000000");
		
		try {
			todoGroupService.deleteTodoGroup(todoGroupVO);
			
			assertNull(todoGroupService.selectTodoGroup(todoGroupVO));
		} catch (Exception e) {
			fail("testDeleteTodoGroup fail");
			e.printStackTrace();
		}
	}
}
