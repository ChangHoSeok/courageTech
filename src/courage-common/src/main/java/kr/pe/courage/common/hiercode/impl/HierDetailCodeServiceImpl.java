
package kr.pe.courage.common.hiercode.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.pe.courage.common.hiercode.HierCodeVO;

/**
 * <pre>
 * kr.pe.courage.common.hiercode.impl
 * HierCodeServiceImpl.java
 * </pre>
 * 
 * @Author : Seok Chang Ho
 * @Date : 2013. 11. 29.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * ================================================================
 * 		수정일		|		수정자			|			수정내용
 * 2013.11.29     석창호                                           최초등록
 * ================================================================
 * </pre>
 */
@Service("courageHierDetailCodeService")
public class HierDetailCodeServiceImpl implements IHierDetailCodeService {
	
	@Resource(name = "courageHierDetailCodeDAO")
	HierDetailCodeDAO hierDetailCodeDAO;

	public List<HierCodeVO> selectHiercodeDetailList(HierCodeVO condHierCodeVO)
			throws Exception {
		return hierDetailCodeDAO.selectHiercodeDetailList(condHierCodeVO);
	}

}
