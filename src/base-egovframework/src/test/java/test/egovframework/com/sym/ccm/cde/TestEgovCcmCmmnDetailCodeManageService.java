
package test.egovframework.com.sym.ccm.cde;

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

import egovframework.com.sym.ccm.cde.service.CmmnDetailCodeVO;
import egovframework.com.sym.ccm.cde.service.EgovCcmCmmnDetailCodeManageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:test/egovframework/spring/context-*.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
public class TestEgovCcmCmmnDetailCodeManageService extends TestCase {
	
	@Resource(name = "CmmnDetailCodeManageService")
	private EgovCcmCmmnDetailCodeManageService cmmDetailCodeManageService;
	
	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}
	
	@Test
	public void testSelectCmmnCodeList() {
		CmmnDetailCodeVO cmmnDetailCodeVO = new CmmnDetailCodeVO();
		cmmnDetailCodeVO.setPagingEnable("0");
		cmmnDetailCodeVO.setCondUseAt("Y");
		cmmnDetailCodeVO.setCondDetailUseAt("Y");
		cmmnDetailCodeVO.setCondCodeId("RI018");
		
		try {
			List cmmnCodeList = cmmDetailCodeManageService.selectCmmnDetailCodeList(cmmnDetailCodeVO);
			assertNotNull(cmmnCodeList);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testSelectOperationCodeList() {
		CmmnDetailCodeVO cmmnDetailCodeVO = new CmmnDetailCodeVO();
		cmmnDetailCodeVO.setOperation("CmmnDetailCodeManage.selectOperationTestList");
		
		try {
			List cmmnCodeList = cmmDetailCodeManageService.selectCmmnDetailCodeList(cmmnDetailCodeVO);
			assertNotNull(cmmnCodeList);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
