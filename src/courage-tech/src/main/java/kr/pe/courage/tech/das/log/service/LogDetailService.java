
package kr.pe.courage.tech.das.log.service;

import java.util.List;

/**
 * <pre>
 * kr.pe.courage.tech.das.log.service
 * LogDetailService.java
 * </pre>
 * 
 * @Author : ChangHo Seok
 * @Date : 2016. 8. 11.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 8. 11., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public interface LogDetailService {
	/**
	 * <pre>
	 * 1. 개요 : Chart 데이터 기준 GroupId 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 8. 29.
	 * @Method Name : selectDetailChartDataGroupId
	 * @param logDetailVO
	 * @return
	 */
	public List<Object> selectDetailChartDataGroupId(LogDetailVO logDetailVO);
	
	/**
	 * <pre>
	 * 1. 개요 : 상세 차트 데이터 조회
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 8. 26.
	 * @Method Name : selectDetailChartData
	 * @param logDetailVO
	 * @return
	 */
	public long[][] selectDetailChartData(LogDetailVO logDetailVO);
	
	/**
	 * <pre>
	 * 1. 개요 : 데이터 기준일자 목록 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 9. 20.
	 * @Method Name : selectStdrDataList
	 * @param logDetailVO
	 * @return
	 */
	public List<LogDetailVO> selectStdrDataList(LogDetailVO logDetailVO);

	/**
	 * <pre>
	 * 1. 개요 : 상세로그 등록
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 8. 11.
	 * @Method Name : insert
	 * @param logDetailVO
	 */
	public void insert(LogDetailVO logDetailVO);

	/**
	 * <pre>
	 * 1. 개요 : 상세로그 접수단위 데이터 삭제
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 8. 24.
	 * @Method Name : deleteLogDetailForRceptUnit
	 * @param logDetailVO
	 */
	public void deleteLogDetailForRceptUnit(LogDetailVO logDetailVO);

	/**
	 * <pre>
	 * 1. 개요 : 상세로그 그룹단위 데이터 삭제
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 8. 11.
	 * @Method Name : truncateLogDetail
	 * @param logDetailVO
	 */
	public void truncateLogDetail(LogDetailVO logDetailVO);
}
