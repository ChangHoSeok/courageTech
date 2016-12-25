package kr.pe.courage.tech.excel;

import egovframework.rte.fdl.excel.CourageExcelJXLMapping;
import jxl.Sheet;
import kr.pe.courage.tech.uss.org.dept.service.DeptVO;

public class TestDeptExcelJXLMappting extends CourageExcelJXLMapping<DeptVO> {

	@Override
	public DeptVO mappingColumn(Sheet sheet, int row) {
		String cell0 = sheet.getCell(0, row).getContents();
		String cell1 = sheet.getCell(1, row).getContents();
		String cell2 = sheet.getCell(2, row).getContents();
		String cell3 = sheet.getCell(3, row).getContents();
		String cell4 = sheet.getCell(4, row).getContents();
		String cell5 = sheet.getCell(5, row).getContents();
		String cell6 = sheet.getCell(6, row).getContents();
		String cell7 = sheet.getCell(7, row).getContents();
		
		DeptVO deptVO = new DeptVO();
		
		deptVO.setDeptCode(cell0);
		deptVO.setAllDeptNm(cell1);
		deptVO.setLowestDeptNm(cell2);
		deptVO.setOdr(cell3);
		deptVO.setOrd(cell4);
		
		return deptVO;
	}

	@Override
	public void validateData(DeptVO paramObject) {
		// TODO Auto-generated method stub
	}
}
