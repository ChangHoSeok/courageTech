
package kr.pe.courage.common.hiercode.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.pe.courage.common.code.CommonCodeVO;
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
@Service("courageHierCodeService")
public class HierCodeServiceImpl implements IHierCodeService {
	
	@Resource(name = "courageHierCodeDAO")
	HierCodeDAO hierCodeDAO;

	public List<HierCodeVO> selectHierGroupCodeList(HierCodeVO hierCodeVO) throws Exception {
		return hierCodeDAO.selectHierGroupCodeList(hierCodeVO);
	}

	public int selectHierGroupCodeListCnt(HierCodeVO hierCodeVO) throws Exception {
		return hierCodeDAO.selectHierGroupCodeListCnt(hierCodeVO);
	}

	public HierCodeVO selectHierGroupCode(HierCodeVO hierCodeVO) throws Exception {
		return hierCodeDAO.selectHierGroupCode(hierCodeVO);
	}

	public boolean isHierGroupCodeExists(HierCodeVO hierCodeVO) throws Exception {
		return hierCodeDAO.isHierGroupCodeExists(hierCodeVO);
	}

	public void insertHierGroupCode(HierCodeVO hierCodeVO) throws Exception {
		hierCodeDAO.insertHierGroupCode(hierCodeVO);
	}

	public void updateHierGroupCode(HierCodeVO hierCodeVO) throws Exception {
		hierCodeDAO.updateHierGroupCode(hierCodeVO);
	}

	public void deleteHierGroupCode(HierCodeVO hierCodeVO) throws Exception {
		hierCodeDAO.deleteHierGroupCode(hierCodeVO);
	}

	public void truncateHierGroupCode() throws Exception {
		hierCodeDAO.truncateHierGroupCode();
	}

	public List<HierCodeVO> selectHierDetailCodeList(HierCodeVO hierCodeVO) throws Exception {
		return hierCodeDAO.selectList(hierCodeVO);
	}

	public int selectHierDetailCodeListCnt(HierCodeVO hierCodeVO) throws Exception {
		return hierCodeDAO.selectListCount(hierCodeVO);
	}

	public HierCodeVO selectHierDetailCode(HierCodeVO hierCodeVO) throws Exception {
		return hierCodeDAO.getById(hierCodeVO);
	}

	public boolean isHierDetailCodeExists(HierCodeVO hierCodeVO) throws Exception {
		return hierCodeDAO.isExist(hierCodeVO);
	}

	public void insertHierDetailCode(HierCodeVO hierCodeVO) throws Exception {
		hierCodeDAO.insert(hierCodeVO);
	}

	public void updateHierDetailCode(HierCodeVO hierCodeVO) throws Exception {
		hierCodeDAO.update(hierCodeVO);
	}

	public void deleteHierDetailCode(HierCodeVO hierCodeVO) throws Exception {
		hierCodeDAO.delete(hierCodeVO);
	}

	public void deleteGroupHierDetailCode(HierCodeVO hierCodeVO) throws Exception {
		hierCodeDAO.deleteGroupHierDetailCode(hierCodeVO);
	}

	public void truncateHierDetailCode() throws Exception {
		hierCodeDAO.truncateHierDetailCode();
	}

	public List<HierCodeVO> selectHierCodeTreeList(HierCodeVO hierCodeVO) throws Exception {
		return hierCodeDAO.selectHierCodeTreeList(hierCodeVO);
	}

	public List<CommonCodeVO> selectHiercodeList(CommonCodeVO commonCodeVO)
			throws Exception {
		return hierCodeDAO.selectHiercodeList(commonCodeVO);
	}

	public List<HierCodeVO> selectHierGroupIdList(HierCodeVO tempCodeVO)
			throws Exception {
		return hierCodeDAO.selectHierGroupIdList(tempCodeVO);
	}
}
