package kr.pe.courage.tech.das.log.service;

import java.util.List;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * <pre>
 * kr.pe.courage.tech.das.log.service
 * LogClGroupService.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 7. 18.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 7. 18., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public interface LogClGroupService {
	/**
	 * <pre>
	 * 1. 개요 : 로그분류그룹 목록 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 18.
	 * @Method Name : selectLogClGroupList
	 * @param logClGroupVO
	 * @return
	 * @throws EgovBizException
	 */
	public List<LogClGroupVO> selectLogClGroupList(LogClGroupVO logClGroupVO) throws EgovBizException;
	
	/**
	 * <pre>
	 * 1. 개요 : 로그분류그룹 목록 갯수 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 18.
	 * @Method Name : selectLogClGroupListCount
	 * @param logClGroupVO
	 * @return
	 * @throws EgovBizException
	 */
	public int selectLogClGroupListCount(LogClGroupVO logClGroupVO) throws EgovBizException;
	
	/**
	 * <pre>
	 * 1. 개요 : 로그분류그룹 정보 상세 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 19.
	 * @Method Name : selectLogClGroupDetail
	 * @param logClGroupVO
	 * @return
	 * @throws EgovBizException
	 */
	public LogClGroupVO selectLogClGroupDetail(LogClGroupVO logClGroupVO) throws EgovBizException;
	
	/**
	 * <pre>
	 * 1. 개요 : 로그분류그룹 그룹아이디 존재여부 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 18.
	 * @Method Name : selectLogClGroupIdExists
	 * @param logClGroupVO
	 * @return
	 * @throws EgovBizException
	 */
	public boolean selectLogClGroupIdExists(LogClGroupVO logClGroupVO) throws EgovBizException;
	
	/**
	 * <pre>
	 * 1. 개요 : 로그분류그룹 정보 등록
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 19.
	 * @Method Name : insertLogClgroup
	 * @param logClGroupVO
	 * @throws EgovBizException
	 */
	public void insertLogClgroup(LogClGroupVO logClGroupVO) throws EgovBizException;
	
	/**
	 * <pre>
	 * 1. 개요 : 로그분류그룹 정보 수정
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 20.
	 * @Method Name : updateLogClGroup
	 * @param logClGroupVO
	 * @throws EgovBizException
	 */
	public void updateLogClGroup(LogClGroupVO logClGroupVO) throws EgovBizException;
	
	/**
	 * <pre>
	 * 1. 개요 : 
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 20.
	 * @Method Name : deleteLogClGroup
	 * @param logClGroupVO
	 * @throws EgovBizException
	 */
	public void deleteLogClGroup(LogClGroupVO logClGroupVO) throws EgovBizException;
}
