
package test.egovframework.com.sym.ccm.cca;

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

import egovframework.com.sym.ccm.cca.service.CmmnCodeVO;
import egovframework.com.sym.ccm.cca.service.EgovCcmCmmnCodeManageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:test/egovframework/spring/context-*.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
public class TestEgovCcmCmmnCodeManageService extends TestCase {
	
	@Resource(name = "CmmnCodeManageService")
	private EgovCcmCmmnCodeManageService cmmCodeManageService;
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}
	
	@Test
	public void testSelectCmmnCodeList() {
		CmmnCodeVO cmmnCodeVo = new CmmnCodeVO();
		cmmnCodeVo.setPageIndex(2);
		cmmnCodeVo.setPagingEnable("1");
		
		try {
			List cmmnCodeList = cmmCodeManageService.selectCmmnCodeList(cmmnCodeVo);
			assertNotNull(cmmnCodeList);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
