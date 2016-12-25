package kr.pe.courage.tech.das.log.service;

import java.util.List;

/**
 * <pre>
 * kr.pe.courage.tech.das.log.service
 * LogRPSService.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 9. 20.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 9. 20., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public interface LogRPSService {
	/**
	 * <pre>
	 * 1. 개요 : RPS 로그 삭제 (필수조건 : groupId, stdrDe)
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 9. 20.
	 * @Method Name : deleteLogRPS
	 * @param logRPSVO
	 */
	public void deleteLogRPS(LogRPSVO logRPSVO);
	
	/**
	 * <pre>
	 * 1. 개요 : RPS 로그 등록 (필수조건 : groupId, stdrDe) <br />
	 *  - 로그상세정보를 기준으로 Request Per Second 데이터 등록
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 9. 20.
	 * @Method Name : insertLogRPS
	 * @param logRPSVO
	 */
	public void insertLogRPS(LogRPSVO logRPSVO);
	
	/**
	 * <pre>
	 * 1. 개요 : RPS 데이터 그룹 정보 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 9. 26.
	 * @Method Name : selectRPSChartDataGroupId
	 * @param logRPSVO
	 * @return
	 */
	public List<Object> selectRPSChartDataGroupId(LogRPSVO logRPSVO);
	
	/**
	 * <pre>
	 * 1. 개요 : RPS Chart Data 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 9. 26.
	 * @Method Name : selectRPSChartDataMap
	 * @param logRPSVO
	 * @return
	 */
	public long[][] selectRPSChartDataMap(LogRPSVO logRPSVO);
}
