package kr.pe.courage.common.hiercode.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.pe.courage.common.code.CommonCodeVO;
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
@Repository("courageHierCodeDAO")
public class HierCodeDAO extends AbstractDAO<HierCodeVO> {

	@Override
	protected String getNameSpace() {
		return "courageHierCode";
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 계층 그룹코드 목록 조회
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 29.
	 * @Method Name : selectHierGroupCodeList
	 * @param hierCodeVO
	 * @return 계층 그룹코드 목록 List<HierCodeVO>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<HierCodeVO> selectHierGroupCodeList(HierCodeVO hierCodeVO) throws Exception {
		return selectList("selectHierGroupCodeList", hierCodeVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 계층 그룹코드 목록 갯수 조회
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 29.
	 * @Method Name : selectHierGroupCodeListCnt
	 * @param hierCodeVO
	 * @return 계층 그룹코드 목록 갯수 int
	 * @throws Exception
	 */
	public int selectHierGroupCodeListCnt(HierCodeVO hierCodeVO) throws Exception {
		return selectListCount("selectHierGroupCodeListCnt", hierCodeVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 계층 그룹코드 상세정보 조회
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 29.
	 * @Method Name : selectHierGroupCode
	 * @param hierCodeVO
	 * @return 계층 그룹코드 상세정보 HierCodeVO
	 * @throws Exception
	 */
	public HierCodeVO selectHierGroupCode(HierCodeVO hierCodeVO) throws Exception {
		return (HierCodeVO) selectObject("selectHierGroupCode", hierCodeVO);
	}
	
	public boolean isHierGroupCodeExists(HierCodeVO hierCodeVO) throws Exception {
		return isExist("isHierGroupCodeExists", hierCodeVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 계층 그룹코드 등록
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 29.
	 * @Method Name : insertHierGroupCode
	 * @param hierCodeVO
	 * @throws Exception
	 */
	public void insertHierGroupCode(HierCodeVO hierCodeVO) throws Exception {
		insert("insertHierGroupCode", hierCodeVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 계층 그룹코드 수정
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 29.
	 * @Method Name : updateHierGroupCode
	 * @param hierCodeVO
	 * @throws Exception
	 */
	public void updateHierGroupCode(HierCodeVO hierCodeVO) throws Exception {
		update("updateHierGroupCode", hierCodeVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 계층 그룹코드 삭제
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 29.
	 * @Method Name : deleteHierGroupCode
	 * @param hierCodeVO
	 * @throws Exception
	 */
	public void deleteHierGroupCode(HierCodeVO hierCodeVO) throws Exception {
		delete("deleteHierGroupCode", hierCodeVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 계층 그룹코드 전체 삭제
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 29.
	 * @Method Name : truncateHierGroupCode
	 * @throws Exception
	 */
	public void truncateHierGroupCode() throws Exception {
		delete("truncateHierGroupCode");
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 계층 상세코드 전체 삭제
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 29.
	 * @Method Name : truncateHierDetailCode
	 * @throws Exception
	 */
	public void truncateHierDetailCode() throws Exception {
		delete("truncateHierDetailCode");
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 계층 그룹에 해당되는 계층 상세코드 전체 삭제
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 29.
	 * @Method Name : deleteGroupHierDetailCode
	 * @param hierCodeVO
	 * @throws Exception
	 */
	public void deleteGroupHierDetailCode(HierCodeVO hierCodeVO) throws Exception {
		delete("deleteGroupHierDetailCode", hierCodeVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 계층코드 
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 29.
	 * @Method Name : selectHierCodeTreeList
	 * @param hierCodeVO
	 * @return
	 * @throws Exception
	 */
	public List<HierCodeVO> selectHierCodeTreeList(HierCodeVO hierCodeVO) throws Exception {
		return selectList("selectHierCodeTreeList", hierCodeVO);
	}

	@SuppressWarnings("unchecked")
	public List<CommonCodeVO> selectHiercodeList(CommonCodeVO commonCodeVO) {
		return selectList("selectHiercodeList", commonCodeVO);
	}

	@SuppressWarnings("unchecked")
	public List<HierCodeVO> selectHierGroupIdList(HierCodeVO tempCodeVO) {
		return selectList("selectHierGroupIdList", tempCodeVO);
	}
}
