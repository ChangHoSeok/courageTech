package kr.pe.courage.tech.uss.org.dept.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.pe.courage.common.dao.AbstractDAO;
import kr.pe.courage.tech.uss.org.dept.service.DeptVO;

/**
 * <pre>
 * kr.pe.courage.tech.uss.org.dept.service.impl
 * DeptDAO.java
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
@Repository("deptDAO")
public class DeptDAO extends AbstractDAO<DeptVO> {

	@Override
	protected String getNameSpace() {
		return "deptDAO";
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 부서 목록 조회 (트리 생성용)
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 10. 5.
	 * @Method Name : selectDeptTreeList
	 * @param deptVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<DeptVO> selectDeptTreeList(DeptVO deptVO) {
		return this.selectList("selectDeptTreeList", deptVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 부서 정보 일괄등록
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 10. 10.
	 * @Method Name : insertDeptList
	 * @param list
	 * @return
	 */
	public Integer insertOrReplaceDeptList(List<Object> list) {
		return this.batchInsert("insertOrReplace", list);
	}

	/**
	 * <pre>
	 * 1. 개요 : 부서 정보 일괄등록
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 10. 10.
	 * @Method Name : insertDeptList
	 * @param list
	 * @return
	 */
	public Integer insertDeptList(List<Object> list) {
		return this.batchInsert("insert", list);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 부서 정보 일괄삭제
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 10. 10.
	 * @Method Name : truncateDept
	 */
	public void truncateDept() {
		this.delete("truncateDept");
	}
}
