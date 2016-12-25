package kr.pe.courage.tech.uss.org.dept.service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import kr.pe.courage.common.utils.ValidationUtil;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rte.fdl.excel.CourageExcelXSSFMapping;
import egovframework.rte.fdl.excel.util.EgovExcelUtil;

@Component("deptExcelXSSFMapper")
public class DeptExcelXSSFMapper extends CourageExcelXSSFMapping<DeptVO> {
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;
	
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
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
		
		deptVO.setRowNum(row.getRowNum());
		deptVO.setDeptCode(EgovExcelUtil.getValue(cell0));
		deptVO.setAllDeptNm(EgovExcelUtil.getValue(cell1));
		deptVO.setLowestDeptNm(EgovExcelUtil.getValue(cell2));
		deptVO.setOdr(EgovExcelUtil.getValue(cell3));
		deptVO.setOrd(EgovExcelUtil.getValue(cell4));
		deptVO.setAtmbUpperDeptCode(EgovExcelUtil.getValue(cell5));
		deptVO.setBestDeptCode(EgovExcelUtil.getValue(cell6));
		deptVO.setPsitnDeptOdr(EgovExcelUtil.getValue(cell7));
		deptVO.setAblSe(EgovExcelUtil.getValue(cell8));
		
		validateData(deptVO);
		
		return deptVO;
	}

	@Override
	public void validateData(DeptVO deptVO) {
		Map<String, String> errors = new HashMap<String, String>();
		
		DataBinder dataBinder = new DataBinder(deptVO);
		dataBinder.setValidator(beanValidator);
		dataBinder.validate();
		
		BindingResult bindingResult = dataBinder.getBindingResult();
		
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			errors.put(fieldError.getField(), messageSource.getMessage(fieldError.getCode(), fieldError.getArguments(), Locale.KOREA));
		}
		
		deptVO.setHasErrors(bindingResult.hasErrors());
		deptVO.setErrors(errors);
	}
}
