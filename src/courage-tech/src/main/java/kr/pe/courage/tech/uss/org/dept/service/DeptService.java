package kr.pe.courage.tech.uss.org.dept.service;

import java.util.List;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * <pre>
 * kr.pe.courage.tech.uss.org.dept.service
 * DeptService.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 9. 29.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 9. 29., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public interface DeptService {
	/**
	 * <pre>
	 * 1. 개요 : 부서 목록 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 9. 29.
	 * @Method Name : selectDeptList
	 * @param deptVO
	 * @return
	 */
	public List<DeptVO> selectDeptList(DeptVO deptVO);
	
	/**
	 * <pre>
	 * 1. 개요 : 부서 목록 조회 (트리생성용)
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 10. 5.
	 * @Method Name : selectDeptTreeList
	 * @param deptVO
	 * @return
	 */
	public List<DeptVO> selectDeptTreeList(DeptVO deptVO);
	
	/**
	 * <pre>
	 * 1. 개요 : 부서 목록 갯수 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 9. 29.
	 * @Method Name : selectDeptListCount
	 * @param deptVO
	 * @return
	 */
	public int selectDeptListCount(DeptVO deptVO);
	
	/**
	 * <pre>
	 * 1. 개요 : 부서 상세조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 9. 29.
	 * @Method Name : selectDeptDetail
	 * @param deptVO
	 * @return
	 */
	public DeptVO selectDeptDetail(DeptVO deptVO);
	
	/**
	 * <pre>
	 * 1. 개요 : 부서정보 존재 유무
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 10. 4.
	 * @Method Name : isExist
	 * @param deptVO
	 * @return
	 */
	public boolean isExist(DeptVO deptVO);
	
	/**
	 * <pre>
	 * 1. 개요 : 부서 정보 등록
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 9. 29.
	 * @Method Name : createDept
	 * @param deptVO
	 */
	public void createDept(DeptVO deptVO);
	
	/**
	 * <pre>
	 * 1. 개요 : 부서 정보 수정
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 9. 29.
	 * @Method Name : updateDept
	 * @param deptVO
	 */
	public void updateDept(DeptVO deptVO);
	
	/**
	 * <pre>
	 * 1. 개요 : 부서 정보 삭제
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 9. 29.
	 * @Method Name : deleteDept
	 * @param deptVO
	 */
	public void deleteDept(DeptVO deptVO);
	
	/**
	 * <pre>
	 * 1. 개요 : 부서 정보 일괄등록
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 10. 14.
	 * @Method Name : createDeptBatch
	 * @param applicationContext
	 * @param deptBatchVO
	 * @return
	 */
	public List<DeptVO> createDeptBatch(DeptBatchVO deptBatchVO) throws EgovBizException;
}
