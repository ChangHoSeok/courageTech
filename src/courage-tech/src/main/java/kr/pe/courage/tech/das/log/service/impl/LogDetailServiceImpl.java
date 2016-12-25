package kr.pe.courage.tech.das.log.service.impl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import kr.pe.courage.tech.das.log.service.LogDetailService;
import kr.pe.courage.tech.das.log.service.LogDetailVO;

import org.springframework.stereotype.Service;

/**
 * <pre>
 * kr.pe.courage.tech.das.log.service.impl
 * LogDetailServiceImpl.java
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
@Service("logDetailService")
public class LogDetailServiceImpl implements LogDetailService {
	
	@Resource(name = "logDetailDAO")
	private LogDetailDAO logDetailDAO;
	
	@Override
	public List<Object> selectDetailChartDataGroupId(LogDetailVO logDetailVO) {
		return logDetailDAO.selectDetailChartDataGroupId(logDetailVO);
	}

	@Override
	public long[][] selectDetailChartData(LogDetailVO logDetailVO) {
		return mapToArray(logDetailDAO.selectDetailChartDataMap(logDetailVO));
	}

	@Override
	public List<LogDetailVO> selectStdrDataList(LogDetailVO logDetailVO) {
		return logDetailDAO.selectStdrDataList(logDetailVO);
	}

	@Override
	public void insert(LogDetailVO logDetailVO) {
		logDetailDAO.insert(logDetailVO);
	}

	@Override
	public void deleteLogDetailForRceptUnit(LogDetailVO logDetailVO) {
		logDetailDAO.deleteLogDetailForRceptUnit(logDetailVO);
	}

	@Override
	public void truncateLogDetail(LogDetailVO logDetailVO) {
		logDetailDAO.truncateLogDetail(logDetailVO);
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
