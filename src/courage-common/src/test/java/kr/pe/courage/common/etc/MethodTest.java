package kr.pe.courage.common.etc;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.Test;

public class MethodTest {

	@Test
	public void testCallReflectionMethod(){
		TestVO vo = new TestVO();
		Class<?> cls = vo.getClass();
		try {
			Method method = cls.getMethod("getLangTypeList", new Class[] {});
			
			List<String> list = (List<String>)method.invoke(vo, new Object[] {});
			System.out.println(list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
