package kr.pe.courage.tech.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Test;

import egovframework.rte.fdl.excel.CourageExcelService;
import egovframework.rte.fdl.excel.impl.CourageExcelServiceImpl;
import junit.framework.TestCase;
import kr.pe.courage.tech.uss.org.dept.service.DeptVO;

public class TestDeptExcel extends TestCase {
	private final Log log = LogFactory.getLog(this.getClass());
	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}
	
	@Test
	public void testExcelParse() {
		log.info("## testExcelParse begin");
		
		CourageExcelService excelService = new CourageExcelServiceImpl();
		excelService.setMapClass("kr.pe.courage.tech.excel.TestDeptExcelMapping");
		
		try {
			List<Object> dataList = excelService.parseHSSFSheetExcel(new FileInputStream("C:\\Users\\schkk\\Downloads\\기관코드 조회자료.xls"), 1);
			
			for (Object obj : dataList) {
				DeptVO deptVO = (DeptVO) obj;
				log.info(deptVO.getDeptCode() + ", " + deptVO.getAllDeptNm() + ", " + deptVO.getOdr() + ", " + deptVO.getOrd());
			}
			
			assertNotNull(dataList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			fail("testExcelParse fail");
		}
		
		log.info("## testExcelParse end");
	}
	
	@Test
	public void testExcel2Parse() {
		log.info("## testExcel2Parse begin");
		
		CourageExcelService excelService = new CourageExcelServiceImpl();
		excelService.setMapClass("kr.pe.courage.tech.excel.TestDeptExcelXSSFMapping");
		
		try {
			List<Object> dataList = excelService.parseXSSFSheetExcel(new FileInputStream("C:\\Users\\schkk\\Downloads\\기관코드 조회자료.xlsx"), 1);
			
			for (Object obj : dataList) {
				DeptVO deptVO = (DeptVO) obj;
				log.info(deptVO.getDeptCode() + ", " + deptVO.getAllDeptNm() + ", " + deptVO.getOdr() + ", " + deptVO.getOrd());
			}
			
			assertNotNull(dataList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			fail("testExcelParse fail");
		}
		
		log.info("## testExcel2Parse end");
	}
	
	@Test
	public void testExcelJXLParse() {
		log.info("## testExcelJXLParse begin");
		
		CourageExcelService excelService = new CourageExcelServiceImpl();
		excelService.setMapClass("kr.pe.courage.tech.excel.TestDeptExcelJXLMappting");
		
		try {
			List<Object> dataList = excelService.parseJXLExcel(new File("C:\\Users\\schkk\\Downloads\\기관코드 조회자료.xls"), 1);
			
			for (Object obj : dataList) {
				DeptVO deptVO = (DeptVO) obj;
				log.info(deptVO.getDeptCode() + ", " + deptVO.getAllDeptNm() + ", " + deptVO.getOdr() + ", " + deptVO.getOrd());
			}
			
			assertNotNull(dataList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			fail("testExcelParse fail");
		}
		
		log.info("## testExcelJXLParse end");
	}
}
