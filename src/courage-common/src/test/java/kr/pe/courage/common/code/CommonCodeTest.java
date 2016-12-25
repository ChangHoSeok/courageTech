package kr.pe.courage.common.code;

import java.util.List;

import javax.annotation.Resource;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:courage/spring/common/context-*.xml",
									"classpath*:kr/pe/courage/spring/common/context-*.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
public class CommonCodeTest extends TestCase {
	
	@Resource(name = "courageCommonCodeService")
	private ICommonCodeService commonCodeService;

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testCommonCodeList() {
		CommonCodeVO commonCodeVO = new CommonCodeVO();
		
		try {
			List<CommonCodeVO> reusltList = commonCodeService.selectCommonCodeList(commonCodeVO);
			assertNotNull(reusltList);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testCommonCode() {
		CommonCodeVO commonCodeVO = new CommonCodeVO();
		commonCodeVO.setCodeId("COM001");
		
		try {
			CommonCodeVO resultVO = commonCodeService.selectCommonCode(commonCodeVO);
			assertNotNull(resultVO);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
