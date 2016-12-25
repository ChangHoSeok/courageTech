package kr.pe.courage.tech.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

import egovframework.rte.fdl.excel.CourageExcelMapping;
import egovframework.rte.fdl.excel.util.EgovExcelUtil;
import kr.pe.courage.tech.uss.org.dept.service.DeptVO;

public class TestDeptExcelMapping extends CourageExcelMapping {

	@Override
	public Object mappingColumn(HSSFRow row) throws Exception {
		HSSFCell cell0 = row.getCell(0);
		HSSFCell cell1 = row.getCell(1);
		HSSFCell cell2 = row.getCell(2);
		HSSFCell cell3 = row.getCell(3);
		HSSFCell cell4 = row.getCell(4);
		HSSFCell cell5 = row.getCell(5);
		HSSFCell cell6 = row.getCell(6);
		HSSFCell cell7 = row.getCell(7);
		HSSFCell cell8 = row.getCell(8);
		
		DeptVO deptVO = new DeptVO();
		
		deptVO.setDeptCode(EgovExcelUtil.getValue(cell0));
		deptVO.setAllDeptNm(EgovExcelUtil.getValue(cell1));
		deptVO.setLowestDeptNm(EgovExcelUtil.getValue(cell2));
		deptVO.setOdr(EgovExcelUtil.getValue(cell3));
		deptVO.setOrd(EgovExcelUtil.getValue(cell4));
		
		return deptVO;
	}
	
	@Override
	public String validateData(Object paramObject) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
