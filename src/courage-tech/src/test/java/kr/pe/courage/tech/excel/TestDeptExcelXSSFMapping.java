package kr.pe.courage.tech.excel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import egovframework.rte.fdl.excel.CourageExcelXSSFMapping;
import egovframework.rte.fdl.excel.util.EgovExcelUtil;
import kr.pe.courage.tech.uss.org.dept.service.DeptVO;

public class TestDeptExcelXSSFMapping extends CourageExcelXSSFMapping {

	@Override
	public Object mappingColumn(XSSFRow row) throws Exception {
		XSSFCell cell0 = row.getCell(0);
		XSSFCell cell1 = row.getCell(1);
		XSSFCell cell2 = row.getCell(2);
		XSSFCell cell3 = row.getCell(3);
		XSSFCell cell4 = row.getCell(4);
		XSSFCell cell5 = row.getCell(5);
		XSSFCell cell6 = row.getCell(6);
		XSSFCell cell7 = row.getCell(7);
		XSSFCell cell8 = row.getCell(8);
		
		DeptVO deptVO = new DeptVO();
		
		deptVO.setDeptCode(EgovExcelUtil.getValue(cell0));
		deptVO.setAllDeptNm(EgovExcelUtil.getValue(cell1));
		deptVO.setLowestDeptNm(EgovExcelUtil.getValue(cell2));
		deptVO.setOdr(EgovExcelUtil.getValue(cell3));
		deptVO.setOrd(EgovExcelUtil.getValue(cell4));
		
		return deptVO;
	}

	@Override
	public void validateData(Object paramObject) {
		// TODO Auto-generated method stub
	}

}
