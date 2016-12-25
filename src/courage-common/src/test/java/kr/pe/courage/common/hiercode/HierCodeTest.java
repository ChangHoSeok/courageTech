package kr.pe.courage.common.hiercode;

import java.util.List;

import javax.annotation.Resource;

import junit.framework.TestCase;
import kr.pe.courage.common.core.CommonDefaultVO;
import kr.pe.courage.common.hiercode.impl.IHierCodeService;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:courage/spring/common/context-*.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
public class HierCodeTest extends TestCase {
	
	@Resource(name = "courageHierCodeService")
	private IHierCodeService hierCodeService;

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testHierGroupCodeList() {
		HierCodeVO hierCodeVO = new HierCodeVO();
		hierCodeVO.setPagingEnable(CommonDefaultVO.PAGING_ENABLE_OFF);
		hierCodeVO.setCondGroupNm("분야");
		
		try {
			List<HierCodeVO> reusltList = hierCodeService.selectHierGroupCodeList(hierCodeVO);
			assertNotNull(reusltList);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testHierDetailCodeList() {
		HierCodeVO hierCodeVO = new HierCodeVO();
		hierCodeVO.setPagingEnable(CommonDefaultVO.PAGING_ENABLE_OFF);
		hierCodeVO.setCondGroupId("RESULT");
		
		try {
			List<HierCodeVO> reusltList = hierCodeService.selectHierDetailCodeList(hierCodeVO);
			assertNotNull(reusltList);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testHierGroupCodeListCnt() {
		HierCodeVO hierCodeVO = new HierCodeVO();
		hierCodeVO.setCondGroupNm("분야");
		
		try {
			int count = hierCodeService.selectHierGroupCodeListCnt(hierCodeVO);
			assertTrue(count > 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testHierDetailCodeListCnt() {
		HierCodeVO hierCodeVO = new HierCodeVO();
		hierCodeVO.setGroupId("RESULT");
		
		try {
			int count = hierCodeService.selectHierDetailCodeListCnt(hierCodeVO);
			assertTrue(count > 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testHierGroupCodeDetail() {
		HierCodeVO hierCodeVO = new HierCodeVO();
		hierCodeVO.setGroupId("APPLCREALM");
		
		try {
			HierCodeVO resultVO = hierCodeService.selectHierGroupCode(hierCodeVO);
			assertNotNull(resultVO);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testHierDetailCodeDetail() {
		HierCodeVO hierCodeVO = new HierCodeVO();
		hierCodeVO.setGroupId("RESULT");
		hierCodeVO.setCode("R1");
		
		try {
			HierCodeVO resultVO = hierCodeService.selectHierDetailCode(hierCodeVO);
			assertNotNull(resultVO);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testHierGroupCodeInert() {
		HierCodeVO hierCodeVO = new HierCodeVO();
		hierCodeVO.setGroupId("TESTGROUP");
		hierCodeVO.setGroupNm("테스트그룹");
		hierCodeVO.setGroupDc("테스트");
		hierCodeVO.setGroupUseAt("Y");
		hierCodeVO.setGroupOrdr("999");
		hierCodeVO.setRegisterId("USRCNFRM_00000000000");
		
		try {
			hierCodeService.insertHierGroupCode(hierCodeVO);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testHierDetailCodeInert() {
		HierCodeVO hierCodeVO = new HierCodeVO();
		hierCodeVO.setGroupId("TESTGROUP");
		hierCodeVO.setCode("TEST");
		hierCodeVO.setCodeNm("TEST");
		hierCodeVO.setCodeDc("DC");
		hierCodeVO.setCodeUseAt("Y");
		hierCodeVO.setCodeOrdr("123");
		hierCodeVO.setUpperCode("0");
		hierCodeVO.setRegisterId("USRCNFRM_00000000000");
		
		try {
			hierCodeService.insertHierDetailCode(hierCodeVO);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testHierGroupCodeUpdate() {
		HierCodeVO hierCodeVO = new HierCodeVO();
		hierCodeVO.setGroupId("TESTGROUP");
		hierCodeVO.setGroupNm("테스트그룹_수정");
		hierCodeVO.setGroupDc("테스트_수정");
		hierCodeVO.setGroupUseAt("Y");
		hierCodeVO.setGroupOrdr("9999");
		hierCodeVO.setUpdusrId("USRCNFRM_00000000000");
		
		try {
			hierCodeService.updateHierGroupCode(hierCodeVO);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testHierDetailCodeUpdate() {
		HierCodeVO hierCodeVO = new HierCodeVO();
		hierCodeVO.setGroupId("TESTGROUP");
		hierCodeVO.setCode("TEST");
		hierCodeVO.setCodeNm("TEST_");
		hierCodeVO.setCodeDc("DC_");
		hierCodeVO.setCodeUseAt("Y");
		hierCodeVO.setCodeOrdr("1");
		hierCodeVO.setUpperCode("0");
		hierCodeVO.setRegisterId("USRCNFRM_00000000000");
		
		try {
			hierCodeService.updateHierDetailCode(hierCodeVO);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testHierGroupCodeIsExists() {
		HierCodeVO hierCodeVO = new HierCodeVO();
		hierCodeVO.setGroupId("TESTGROUP");
		
		try {
			boolean result = hierCodeService.isHierGroupCodeExists(hierCodeVO);
			assertTrue(result);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testHierDetailCodeIsExists() {
		HierCodeVO hierCodeVO = new HierCodeVO();
		hierCodeVO.setGroupId("TESTGROUP");
		hierCodeVO.setCode("TEST");
		
		try {
			boolean result = hierCodeService.isHierDetailCodeExists(hierCodeVO);
			assertTrue(result);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testHierDetailCodeDelete() {
		HierCodeVO hierCodeVO = new HierCodeVO();
		hierCodeVO.setGroupId("TESTGROUP");
		hierCodeVO.setCode("TEST");
		
		try {
			hierCodeService.deleteHierDetailCode(hierCodeVO);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testHierGroupCodeDelete() {
		HierCodeVO hierCodeVO = new HierCodeVO();
		hierCodeVO.setGroupId("TESTGROUP");
		
		try {
			hierCodeService.deleteHierGroupCode(hierCodeVO);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
