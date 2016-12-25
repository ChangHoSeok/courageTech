package kr.pe.courage.common.hiercode.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.pe.courage.common.dao.AbstractDAO;
import kr.pe.courage.common.hiercode.HierCodeVO;

/**
 * <pre>
 * kr.pe.courage.common.hiercode.impl
 * HierCodeDAO.java
 * </pre>
 *
 * @Author	: Seok Chang Ho
 * @Date	: 2013. 11. 29.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * ================================================================
 * 		수정일		|		수정자			|			수정내용
 * 2013.11.29     석창호                                           최초등록
 * ================================================================
 * </pre>
 */
@Repository("courageHierDetailCodeDAO")
public class HierDetailCodeDAO extends AbstractDAO<HierCodeVO> {

	@Override
	protected String getNameSpace() {
		return "courageHierDetailCode";
	}

	@SuppressWarnings("unchecked")
	public List<HierCodeVO> selectHiercodeDetailList(HierCodeVO condHierCodeVO) {
		return selectList("selectHiercodeDetailList", condHierCodeVO);
	}
	
}
