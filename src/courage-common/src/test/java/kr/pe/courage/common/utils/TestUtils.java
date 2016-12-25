package kr.pe.courage.common.utils;


import org.junit.Test;

import junit.framework.TestCase;

public class TestUtils extends TestCase {

	@Test
	public void test() {
		try {
			System.out.println(DateUtil.getYear());
			System.out.println(DateUtil.getMonth());
			System.out.println(DateUtil.getDay());
			System.out.println(DateUtil.getHour());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
