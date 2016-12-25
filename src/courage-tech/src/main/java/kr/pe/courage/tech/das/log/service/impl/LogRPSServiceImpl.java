package kr.pe.courage.tech.das.log.service.impl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import kr.pe.courage.tech.das.log.service.LogRPSService;
import kr.pe.courage.tech.das.log.service.LogRPSVO;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * kr.pe.courage.tech.das.log.service.impl
 * LogRPSServiceImpo.java
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
@Service("logRPSService")
public class LogRPSServiceImpl implements LogRPSService {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Resource(name = "logRPSDAO")
	private LogRPSDAO logRPSDAO;

	@Override
	public void deleteLogRPS(LogRPSVO logRPSVO) {
		logRPSDAO.delete(logRPSVO);
	}

	@Override
	public void insertLogRPS(LogRPSVO logRPSVO) {
		logRPSDAO.insert(logRPSVO);
	}
	
	@Override
	public List<Object> selectRPSChartDataGroupId(LogRPSVO logRPSVO) {
		return logRPSDAO.selectRPSChartDataGroupId(logRPSVO);
	}

	@Override
	public long[][] selectRPSChartDataMap(LogRPSVO logRPSVO) {
		return mapToArray(logRPSDAO.selectRPSChartDataMap(logRPSVO));
	}
	
	private long[][] mapToArray(Map<Object, Object> dataMap) {
		long [][] dataArray = null;
		
		if (dataMap != null && dataMap.size() > 0) {
			dataArray = new long[dataMap.size()][2];
			
			// Map 데이터 정렬
			TreeMap<Object, Object> treeMap = new TreeMap<Object, Object>(dataMap);
			
			Object[] keyArray = treeMap.keySet().toArray();
			Object[] valueArray = treeMap.values().toArray();
			
			for(int i = 0; i < dataArray.length; i++) {
				dataArray[i][0] = Long.parseLong(keyArray[i].toString());
				dataArray[i][1] = Long.parseLong(valueArray[i].toString());
			}
		}
		
		return dataArray;
	}
}
