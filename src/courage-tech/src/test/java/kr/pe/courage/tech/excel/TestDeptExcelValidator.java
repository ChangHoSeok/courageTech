package kr.pe.courage.tech.excel;


import java.util.Locale;

import javax.annotation.Resource;

import junit.framework.TestCase;
import kr.pe.courage.tech.uss.org.dept.service.DeptVO;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springmodules.validation.commons.DefaultBeanValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:test/spring/context-messages.xml",
									"classpath*:test/spring/context-validator.xml"})
public class TestDeptExcelValidator extends TestCase {
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;
	
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testValidator() {
		DeptVO deptVO = new DeptVO();
		deptVO.setDeptCode("1asdas");
		deptVO.setOdr("asd");
		deptVO.setAblSe("2");
//		deptVO.setLowestDeptNm("Asd");
		
		DataBinder dataBinder = new DataBinder(deptVO);
		dataBinder.setValidator(beanValidator);
		dataBinder.validate();
		
		BindingResult bindingResult = dataBinder.getBindingResult();
		
		assertTrue(bindingResult.hasErrors());
		
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			System.out.println(fieldError.getField() + " - " + messageSource.getMessage(fieldError.getCode(), fieldError.getArguments(), Locale.KOREA));
		}
	}

}
