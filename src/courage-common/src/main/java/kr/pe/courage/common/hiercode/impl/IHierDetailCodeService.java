
package kr.pe.courage.common.hiercode.impl;

import java.util.List;

import kr.pe.courage.common.hiercode.HierCodeVO;

public interface IHierDetailCodeService {

	List<HierCodeVO> selectHiercodeDetailList(
			HierCodeVO condHierCodeVO) throws Exception;
	
}
