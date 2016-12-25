package kr.pe.courage.tech.das.log.service.impl;

import java.util.List;
import java.util.Map;

import kr.pe.courage.common.dao.AbstractDAO;
import kr.pe.courage.tech.das.log.service.LogDetailVO;

import org.springframework.stereotype.Repository;

/**
 * <pre>
 * kr.pe.courage.tech.das.log.service.impl
 * LogDetailDAO.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 8. 11.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 8. 11., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
@Repository("logDetailDAO")
public class LogDetailDAO extends AbstractDAO<LogDetailVO> {
	
	@Override
	protected String getNameSpace() {
		return "logDetailDAO";
	}
	
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
	@SuppressWarnings("unchecked")
	public List<Object> selectDetailChartDataGroupId(LogDetailVO logDetailVO) {
		return this.selectList("selectDetailChartDataGroupId", logDetailVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 상세 차트 데이터 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 8. 26.
	 * @Method Name : selectDetailChartDataMap
	 * @param logDetailVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<Object, Object> selectDetailChartDataMap(LogDetailVO logDetailVO) {
		return this.map("selectDetailChartDataMap", logDetailVO, "LOG_DATA_NUMBER_1", "LOG_DATA_NUMBER_3");
	}
	
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
	@SuppressWarnings("unchecked")
	public List<LogDetailVO> selectStdrDataList(LogDetailVO logDetailVO) {
		return this.selectList("selectStdrDataList", logDetailVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 로그파일접수 단위 데이터 삭제
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 8. 24.
	 * @Method Name : deleteLogDetailForRceptUnit
	 * @param logDetailVO
	 */
	public void deleteLogDetailForRceptUnit(LogDetailVO logDetailVO) {
		this.delete("deleteLogDetailForRceptUnit", logDetailVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : Group 단위 데이터 삭제
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 8. 24.
	 * @Method Name : truncateLogDetail
	 * @param logDetailVO
	 */
	public void truncateLogDetail(LogDetailVO logDetailVO) {
		this.delete("truncateLogDetail", logDetailVO);
	}
}
