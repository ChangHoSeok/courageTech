
package kr.pe.courage.common.hiercode.impl;

import java.util.List;

import kr.pe.courage.common.code.CommonCodeVO;
import kr.pe.courage.common.hiercode.HierCodeVO;

/**
 * <pre>
 * kr.pe.courage.common.hiercode.impl
 * IHierCodeService.java
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
 *  2013.11.29   석창호                                             최초등록
 * ================================================================
 * </pre>
 */
public interface IHierCodeService {
	/**
	 * <pre>
	 * 1. 개요 : 계층 그룹코드 목록 조회
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 29.
	 * @Method Name : selectHierGroupCodeList
	 * @param hierCodeVO
	 * @return
	 * @throws Exception
	 */
	public List<HierCodeVO> selectHierGroupCodeList(HierCodeVO hierCodeVO) throws Exception;
	
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
	public int selectHierGroupCodeListCnt(HierCodeVO hierCodeVO) throws Exception;
	
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
	public HierCodeVO selectHierGroupCode(HierCodeVO hierCodeVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 계층 그룹코드 존재여부 확인
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 29.
	 * @Method Name : isHierGroupCodeExists
	 * @param hierCodeVO
	 * @return 계층 그룹코드 존재여부 boolean
	 * @throws Exception
	 */
	public boolean isHierGroupCodeExists(HierCodeVO hierCodeVO) throws Exception;
	
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
	public void insertHierGroupCode(HierCodeVO hierCodeVO) throws Exception;
	
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
	public void updateHierGroupCode(HierCodeVO hierCodeVO) throws Exception;
	
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
	public void deleteHierGroupCode(HierCodeVO hierCodeVO) throws Exception;
	
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
	public void truncateHierGroupCode() throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 계층 상세코드 목록 조회
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 29.
	 * @Method Name : selectHierDetailCodeList
	 * @param hierCodeVO
	 * @return
	 * @throws Exception
	 */
	public List<HierCodeVO> selectHierDetailCodeList(HierCodeVO hierCodeVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 계층 상세코드 목록 갯수 조회
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 29.
	 * @Method Name : selectHierDetailCodeListCnt
	 * @param hierCodeVO
	 * @return 계층 상세코드 목록 갯수 int
	 * @throws Exception
	 */
	public int selectHierDetailCodeListCnt(HierCodeVO hierCodeVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 계층 상세코드 상세정보 조회
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 29.
	 * @Method Name : selectHierDetailCode
	 * @param hierCodeVO
	 * @return 계층 상세코드 상세정보 HierCodeVO
	 * @throws Exception
	 */
	public HierCodeVO selectHierDetailCode(HierCodeVO hierCodeVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 계층 상세코드 존재여부 확인
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 29.
	 * @Method Name : isHierDetailCodeExists
	 * @param hierCodeVO
	 * @return 계층 상세코드 존재여부 boolean
	 * @throws Exception
	 */
	public boolean isHierDetailCodeExists(HierCodeVO hierCodeVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 계층 상세코드 등록
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 29.
	 * @Method Name : insertHierDetailCode
	 * @param hierCodeVO
	 * @throws Exception
	 */
	public void insertHierDetailCode(HierCodeVO hierCodeVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 계층 상세코드 수정
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 29.
	 * @Method Name : updateHierDetailCode
	 * @param hierCodeVO
	 * @throws Exception
	 */
	public void updateHierDetailCode(HierCodeVO hierCodeVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 계층 상세코드 삭제
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 29.
	 * @Method Name : deleteHierDetailCode
	 * @param hierCodeVO
	 * @throws Exception
	 */
	public void deleteHierDetailCode(HierCodeVO hierCodeVO) throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 계층 그룹코드에 해당되는 계층 상세코드 전체 삭제
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 29.
	 * @Method Name : deleteGroupHierDetailCode
	 * @param hierCodeVO
	 * @throws Exception
	 */
	public void deleteGroupHierDetailCode(HierCodeVO hierCodeVO) throws Exception;
	
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
	public void truncateHierDetailCode() throws Exception;
	
	/**
	 * <pre>
	 * 1. 개요 : 계층코드 트리 목록 조회
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 29.
	 * @Method Name : selectHierCodeTreeList
	 * @param hierCodeVO
	 * @throws Exception
	 */
	public List<HierCodeVO> selectHierCodeTreeList(HierCodeVO hierCodeVO) throws Exception;
	
	public List<CommonCodeVO> selectHiercodeList(CommonCodeVO commonCodeVO) throws Exception;

	public List<HierCodeVO> selectHierGroupIdList(HierCodeVO tempCodeVO) throws Exception;
}
