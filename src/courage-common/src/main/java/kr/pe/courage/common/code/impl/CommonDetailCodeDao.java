package kr.pe.courage.common.code.impl;

import java.sql.SQLException;
import java.util.List;

import kr.pe.courage.common.code.CommonCodeVO;
import kr.pe.courage.common.dao.AbstractDAO;

import org.springframework.stereotype.Repository;

@Repository("courageCommonDetailCodeDAO")
public class CommonDetailCodeDao extends AbstractDAO<CommonCodeVO> {

	@Override
	protected String getNameSpace() {
		return "courageCommonDetailCode";
	}
	
	public List<CommonCodeVO> selectOperationCodeList(CommonCodeVO commonCodeVO) throws SQLException {
		return list(commonCodeVO.getOperation(), commonCodeVO);
	}
}
