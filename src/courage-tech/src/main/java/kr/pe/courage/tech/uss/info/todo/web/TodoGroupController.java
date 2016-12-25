
package kr.pe.courage.tech.uss.info.todo.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.pe.courage.tech.uss.info.todo.service.TodoGroupService;
import kr.pe.courage.tech.uss.info.todo.service.TodoGroupVO;

import org.codehaus.jackson.map.exc.UnrecognizedPropertyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import egovframework.rte.ptl.mvc.tags.ui.pagination.CouragePaginationInfo;

/**
 * <pre>
 * kr.pe.courage.tech.uss.info.todo.web
 * TodoGroupController.java
 * </pre>
 *
 * @Author : chseok82
 * @Date : 2015. 8. 10.
 * @Version : 0.1
 * @see
 *
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2015. 8. 10., 수정자 : chseok82, 수정내용 : 최초등록
 * </pre>
 */
@Controller("todoGroupController")
@RequestMapping(value = "/uss/info/*")
public class TodoGroupController {
	@Resource(name = "todoGroupService")
	private TodoGroupService todoGroupService;

	@Value("#{pageConfig['uss.info.todogroup.page.recordCount']}")
	private int recordCount;

	@Value("#{pageConfig['uss.info.todogroup.page.size']}")
	private int pageSize;

	@Value("#{pageConfig['uss.info.todogroup.page.enable']}")
	private String pageEnable;

	/**
	 * <pre>
	 * 1. 개요 : To-do Group 목록 조회
	 * </pre>
	 *
	 * @Author	: chseok82
	 * @Date	: 2015. 8. 18.
	 * @Method Name : list
	 * @param todoGroupVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "todogroups", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> list(HttpServletRequest request, HttpServletResponse response, TodoGroupVO todoGroupVO) throws Exception {
		CouragePaginationInfo pagination = new CouragePaginationInfo(todoGroupVO, pageEnable, recordCount, pageSize);
		todoGroupVO = (TodoGroupVO) pagination.createCustomVo(request);

		List<TodoGroupVO> dataList = todoGroupService.selectTodoGroupList(todoGroupVO);
		int dataCnt = todoGroupService.selectTodoGroupListCnt(todoGroupVO);
		pagination.setTotalRecordCount(dataCnt);

		if (dataCnt <= 0 || dataList == null || dataList.size() <= 0) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", Boolean.TRUE);
		result.put("data", dataList);
		result.put("dataCnt", dataCnt);
		result.put("pagination", pagination);

		return result;
	}

	/**
	 * <pre>
	 * 1. 개요 : To-do Group 상세 조회
	 * </pre>
	 *
	 * @Author	: chseok82
	 * @Date	: 2015. 8. 20.
	 * @Method Name : viewDetail
	 * @param todoGroupVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "todogroups/{groupId}", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> viewDetail(HttpServletResponse response, @PathVariable String groupId) throws Exception {
		TodoGroupVO todoGroupVO = new TodoGroupVO(groupId);
		todoGroupVO = todoGroupService.selectTodoGroup(todoGroupVO);

		if (todoGroupVO == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", Boolean.TRUE);
		result.put("data", todoGroupVO);

		return result;
	}

	/**
	 * <pre>
	 * 1. 개요 : To-do Group 정보 등록
	 * </pre>
	 *
	 * @Author	: chseok82
	 * @Date	: 2015. 8. 21.
	 * @Method Name : regist
	 * @param response
	 * @param todoGroupVO
	 * @throws Exception
	 */
	@RequestMapping(value = "todogroups", method = RequestMethod.POST)
	public void regist(HttpServletResponse response, @RequestBody TodoGroupVO todoGroupVO) throws Exception {
		todoGroupService.insertTodoGroup(todoGroupVO);
		response.setStatus(HttpServletResponse.SC_CREATED);
	}

	/**
	 * <pre>
	 * 1. 개요 : To-do Group 정보 수정
	 * </pre>
	 *
	 * @Author	: chseok82
	 * @Date	: 2015. 8. 21.
	 * @Method Name : modify
	 * @param todoGroupVO
	 * @throws Exception
	 */
	@RequestMapping(value = "todogroups", method = RequestMethod.PUT)
	public void modify(HttpServletResponse response, @RequestBody TodoGroupVO todoGroupVO) throws Exception {
		if (todoGroupService.isExistTodoGroup(todoGroupVO)) {
			todoGroupService.updateTodoGroup(todoGroupVO);
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}
	}

	/**
	 * <pre>
	 * 1. 개요 : To-do Group 정보 삭제
	 * </pre>
	 *
	 * @Author	: chseok82
	 * @Date	: 2015. 8. 21.
	 * @Method Name : delete
	 * @param response
	 * @param todoGroupVO
	 * @throws Exception
	 */
	@RequestMapping(value = "todogroups", method = RequestMethod.DELETE)
	public void delete(HttpServletResponse response, @RequestBody TodoGroupVO todoGroupVO) throws Exception {
		if (todoGroupService.isExistTodoGroup(todoGroupVO)) {
			todoGroupService.deleteTodoGroup(todoGroupVO);
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}
	}

	/**
	 * <pre>
	 * 1. 개요 : 405 ExceptionHandler
	 * </pre>
	 *
	 * @Author	: chseok82
	 * @Date	: 2015. 8. 18.
	 * @Method Name : handleEmployeeNotFoundException
	 * @param ex
	 */
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED, reason = "지원하지 않는 Request Method 형식입니다.")
	public void handleEmployeeNotFoundException(Exception ex) {
		ex.printStackTrace();
	}

	/**
	 * <pre>
	 * 1. 개요 : 415 지원하지 않는 미디어 유형
	 * </pre>
	 *
	 * @Author	: chseok82
	 * @Date	: 2015. 8. 21.
	 * @Method Name : httpMediaTypeNotSupportedException
	 * @param ex
	 */
	@ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
	@ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE, reason = "지원하지 않는 Media 형식입니다.")
	public void httpMediaTypeNotSupportedException(Exception ex) {
		ex.printStackTrace();
	}

	/**
	 * <pre>
	 * 1. 개요 : 400 잘못된 형식의 값이 전달되었을 경우 발생
	 * </pre>
	 *
	 * @Author	: chseok82
	 * @Date	: 2015. 8. 21.
	 * @Method Name : unrecognizedPropertyException
	 * @param ex
	 */
	@ExceptionHandler(value = UnrecognizedPropertyException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "형식이 맞지 않습니다.")
	public @ResponseBody void unrecognizedPropertyException(Exception ex) {
		ex.printStackTrace();
	}
}
