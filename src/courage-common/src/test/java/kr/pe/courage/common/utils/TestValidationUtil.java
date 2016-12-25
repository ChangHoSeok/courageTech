package kr.pe.courage.common.utils;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Test;

public class TestValidationUtil extends TestCase {

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testLetter() {
		assertTrue(ValidationUtil.isRegex("asdadasd", "^[a-zA-Z]*$"));
		assertTrue(ValidationUtil.isRegex("", "^[a-zA-Z]*$"));
		assertFalse(ValidationUtil.isRegex("가나다", "^[a-zA-Z]*$"));
		assertFalse(ValidationUtil.isRegex("324123", "^[a-zA-Z]*$"));
		assertFalse(ValidationUtil.isRegex("asdadasd123", "^[a-zA-Z]*$"));
		assertFalse(ValidationUtil.isRegex("1asdadasd", "^[a-zA-Z]*$"));
	}
	
	@Test
	public void testLetterNumber() {
		assertTrue(ValidationUtil.isRegex("asdasd123", "^[0-9a-zA-Z]*$"));
		assertTrue(ValidationUtil.isRegex("123asd", "^[0-9a-zA-Z]*$"));
		assertFalse(ValidationUtil.isRegex("가나다", "^[0-9a-zA-Z]*$"));
	}
	
	@Test
	public void testLetterNumberSp() {
		assertTrue(ValidationUtil.isRegex("asdasd123!@#!$@E~ asd\\", "^[0-9a-zA-Z\\ \'!@#$%^*+=-_,&()-{}|<>\"~]*$"));
		assertTrue(ValidationUtil.isRegex("123asd", "^[0-9a-zA-Z\\ \'!@#$%^*+=-_,&()-{}|<>\"~]*$"));
		assertFalse(ValidationUtil.isRegex("가나다", "^[0-9a-zA-Z\\ \'!@#$%^*+=-_,&()-{}|<>\"~]*$"));
	}
	
	@Test
	public void testEmail() {
		assertTrue(ValidationUtil.isEmail("schkkh.chseok@naver.com", true));
	}
	
	@Test
	public void testMin() {
		assertTrue(ValidationUtil.min("11", 10, false));
		assertTrue(ValidationUtil.min("0999", 10, true));
		assertTrue(ValidationUtil.min("999", 10, true));
		assertFalse(ValidationUtil.min("9", 10, true));
		assertTrue(ValidationUtil.min("10", 10, true));
		assertFalse(ValidationUtil.max("가나", 1000, true));
	}
	
	@Test
	public void testMax() {
		assertTrue(ValidationUtil.max("", 1000, false));
		assertTrue(ValidationUtil.max("1000", 1000, true));
		assertFalse(ValidationUtil.max("1001", 1000, true));
		assertFalse(ValidationUtil.max("가나", 1000, true));
	}
	
	@Test
	public void testPasswork() {
		assertTrue(ValidationUtil.isPassword("asd123@", true));
		assertTrue(ValidationUtil.isPassword("@123asd", true));
		assertFalse(ValidationUtil.isPassword("", true));
		assertFalse(ValidationUtil.isPassword("asd123", true));
		assertFalse(ValidationUtil.isPassword("asd", true));
		assertFalse(ValidationUtil.isPassword("asd@", true));
		assertFalse(ValidationUtil.isPassword("가나123@", true));
	}
}
