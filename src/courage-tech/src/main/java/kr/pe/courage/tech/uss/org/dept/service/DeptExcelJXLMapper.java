package kr.pe.courage.tech.uss.org.dept.service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springmodules.validation.commons.DefaultBeanValidator;

import jxl.Sheet;
import egovframework.rte.fdl.excel.CourageExcelJXLMapping;

@Component("deptExcelJXLMapper")
public class DeptExcelJXLMapper extends CourageExcelJXLMapping<DeptVO> {
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;
	
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

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
		String cell8 = sheet.getCell(8, row).getContents();
		
		DeptVO deptVO = new DeptVO();
		
		deptVO.setRowNum(row + 1);
		deptVO.setDeptCode(cell0);
		deptVO.setAllDeptNm(cell1);
		deptVO.setLowestDeptNm(cell2);
		deptVO.setOdr(cell3);
		deptVO.setOrd(cell4);
		deptVO.setAtmbUpperDeptCode(cell5);
		deptVO.setBestDeptCode(cell6);
		deptVO.setPsitnDeptOdr(cell7);
		deptVO.setAblSe(cell8);
		
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
