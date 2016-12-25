package kr.pe.courage.common.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import kr.pe.courage.common.utils.file.FileUtil;

public class TestFileUtils extends TestCase {

	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void test() {
		System.out.println(FileUtil.getContentType("C:\\courage\\project\\08.png"));
	}

}
