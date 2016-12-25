package kr.pe.courage.common.code;


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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:courage/spring/common/context-*.xml",
									"classpath*:kr/pe/courage/spring/common/context-*.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
public class CommonDetailCodeTest extends TestCase {
	
	@Resource(name = "courageCommonDetailCodeService")
	ICommonDetailCodeService commonDetailCodeService;

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testCommonDetailCodeList() {
		CommonCodeVO commonCodeVO = new CommonCodeVO();
		
		try {
			List<CommonCodeVO> resultList = commonDetailCodeService.selectCommonDetailCodeList(commonCodeVO);
			assertNotNull(resultList);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testCommonDetailCodeConditionList() {
		CommonCodeVO commonCodeVO = new CommonCodeVO();
		commonCodeVO.setCodeId("COM001");
		commonCodeVO.setUseAt("Y");
		
		try {
			List<CommonCodeVO> resultList = commonDetailCodeService.selectCommonDetailCodeList(commonCodeVO);
			assertNotNull(resultList);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testCommonDetailCode() {
		CommonCodeVO commonCodeVO = new CommonCodeVO();
		commonCodeVO.setCodeId("COM001");
		commonCodeVO.setCode("REGC02");
		
		try {
			CommonCodeVO resultVO = commonDetailCodeService.selectCommonDetailCode(commonCodeVO);
			assertNotNull(resultVO);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
