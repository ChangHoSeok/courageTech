
package kr.pe.courage.tech.das.log.service;

import java.util.List;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * <pre>
 * kr.pe.courage.tech.das.log.service
 * LogFileRceptService.java
 * </pre>
 * 
 * @Author : ChangHo Seok
 * @Date : 2016. 7. 22.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 7. 22., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public interface LogFileRceptService {
	/**
	 * <pre>
	 * 1. 개요 : 로그파일 접수 목록 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 22.
	 * @Method Name : selectLogFileRceptList
	 * @param logFileRceptVO
	 * @return
	 * @throws EgovBizException
	 */
	public List<LogFileRceptVO> selectLogFileRceptList(LogFileRceptVO logFileRceptVO) throws EgovBizException;

	/**
	 * <pre>
	 * 1. 개요 : 로그파일 접수 목록 갯수 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 22.
	 * @Method Name : selectLogFileRceptListCount
	 * @param logFileRceptVO
	 * @return
	 * @throws EgovBizException
	 */
	public int selectLogFileRceptListCount(LogFileRceptVO logFileRceptVO) throws EgovBizException;

	/**
	 * <pre>
	 * 1. 개요 : 로그파일 접수 상세정보 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 22.
	 * @Method Name : selectLogFileRceptDetail
	 * @param logFileRceptVO
	 * @return
	 * @throws EgovBizException
	 */
	public LogFileRceptVO selectLogFileRceptDetail(LogFileRceptVO logFileRceptVO) throws EgovBizException;

	/**
	 * <pre>
	 * 1. 개요 : 로그파일 접수 정보 등록
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 22.
	 * @Method Name : insertLogFileRcept
	 * @param logFileRceptVO
	 * @throws EgovBizException
	 */
	public void insertLogFileRcept(LogFileRceptVO logFileRceptVO) throws EgovBizException;

	/**
	 * <pre>
	 * 1. 개요 : 로그파일 접수 정보 삭제
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 22.
	 * @Method Name : deleteLogFileRcept
	 * @param logFileRceptVO
	 * @throws EgovBizException
	 */
	public void deleteLogFileRcept(LogFileRceptVO logFileRceptVO) throws EgovBizException;
	
	/**
	 * <pre>
	 * 1. 개요 : 
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 8. 24.
	 * @Method Name : truncateLogFileRcept
	 * @param logFileRceptVO
	 */
	public void truncateLogFileRcept(LogFileRceptVO logFileRceptVO);
	
	/**
	 * <pre>
	 * 1. 개요 : 처리상태 변경
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 8. 12.
	 * @Method Name : updateProcessSttus
	 * @param logFileRceptVO
	 */
	public void updateProcessSttus(LogFileRceptVO logFileRceptVO);
}
