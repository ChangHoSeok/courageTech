package kr.pe.courage.tech.das.log.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import kr.pe.courage.common.dao.AbstractDAO;
import kr.pe.courage.tech.das.log.service.LogRPSVO;

/**
 * <pre>
 * kr.pe.courage.tech.das.log.service.impl
 * LogRPSDAO.java
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
@Repository("logRPSDAO")
public class LogRPSDAO extends AbstractDAO<LogRPSVO> {
	
	private final Logger logger = Logger.getLogger(this.getClass());

	@Override
	protected String getNameSpace() {
		return "logRPSDAO";
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> selectRPSChartDataGroupId(LogRPSVO logRPSVO) {
		return this.selectList("selectRPSChartDataGroupId", logRPSVO);
	}
	
	@SuppressWarnings("unchecked")
	public Map<Object, Object> selectRPSChartDataMap(LogRPSVO logRPSVO) {
		Map<Object, Object> resultMap = this.map("selectRPSChartDataMap", logRPSVO, "STDR_TIME", "RPS");
		
		if (resultMap instanceof HashMap) {
			logger.debug("## resultMap Type HashMap");
		} else if (resultMap instanceof LinkedHashMap) {
			logger.debug("## resultMap Type LinkedHashMap");
		} else {
			logger.debug("## resultMap Type ...");
		}
		
		return resultMap;
	}

}
