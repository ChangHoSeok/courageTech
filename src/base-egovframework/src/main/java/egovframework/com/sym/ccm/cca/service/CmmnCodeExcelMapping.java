package egovframework.com.sym.ccm.cca.service;

import kr.pe.courage.common.utils.ValidationUtil;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

import egovframework.rte.fdl.excel.CourageExcelMapping;
import egovframework.rte.fdl.excel.util.EgovExcelUtil;

public class CmmnCodeExcelMapping extends CourageExcelMapping {
	
	@Override
	public Object mappingColumn(HSSFRow row) throws Exception {
		HSSFCell cell0 = row.getCell(0);
		HSSFCell cell1 = row.getCell(1);
		HSSFCell cell2 = row.getCell(2);
		HSSFCell cell3 = row.getCell(3);
		
		CmmnCodeVO cmmnCodeVO = new CmmnCodeVO();
		
		cmmnCodeVO.setCodeId(EgovExcelUtil.getValue(cell0));
		cmmnCodeVO.setCodeIdNm(EgovExcelUtil.getValue(cell1));
		cmmnCodeVO.setCodeIdDc(EgovExcelUtil.getValue(cell2));
		cmmnCodeVO.setUseAt(EgovExcelUtil.getValue(cell3));
		
		validateData(cmmnCodeVO); // 유효성검사
		
		return cmmnCodeVO;
	}

	@Override
	public String validateData(Object obj) throws Exception {
		boolean validateError = false;
		StringBuilder errMsg = new StringBuilder();
		CmmnCodeVO cmmnCodeVO = (CmmnCodeVO) obj;
		
		if (!ValidationUtil.maxSize(cmmnCodeVO.getCodeId(), 10, true)) {
			errMsg.append("코드ID가 잘못되었습니다.[" + cmmnCodeVO.getCodeId() + "]\r\n");
			validateError = true;
		}
		
		if (!ValidationUtil.maxSize(cmmnCodeVO.getCodeIdNm(), 50, true)) {
			errMsg.append("코드ID명이 잘못되었습니다.[" + cmmnCodeVO.getCodeIdNm() + "]\r\n");
			validateError = true;
		}
		
		if (!ValidationUtil.maxSize(cmmnCodeVO.getCodeIdDc(), 100, false)) {
			errMsg.append("코드설명이 잘못되었습니다.[" + cmmnCodeVO.getCodeIdDc() + "]\r\n");
			validateError = true;
		}
		
		if (!ValidationUtil.maxSize(cmmnCodeVO.getUseAt(), 1, true)) {
			errMsg.append("사용여부가 잘못되었습니다.[" + cmmnCodeVO.getUseAt() + "]");
			validateError = true;
		}
		
		cmmnCodeVO.setValidateError(validateError);
		cmmnCodeVO.setValidateErrorStr(errMsg);
		
		return errMsg.toString();
	}
}
