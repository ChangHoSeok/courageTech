package test.egovframework.com.utl.sim;


import org.junit.Before;
import org.junit.Test;

import egovframework.com.utl.sim.service.EgovFileScrty;

import junit.framework.TestCase;

public class TestUtilSimService extends TestCase {

	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() {
		try {
			System.out.println(EgovFileScrty.encryptPassword("1234"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
