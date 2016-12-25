package kr.pe.courage.tech.das.log.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.pe.courage.common.web.utils.WebUtils;
import kr.pe.courage.tech.das.log.service.LogDetailService;
import kr.pe.courage.tech.das.log.service.LogDetailVO;
import kr.pe.courage.tech.das.log.service.LogRPSService;
import kr.pe.courage.tech.das.log.service.LogRPSVO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * <pre>
 * kr.pe.courage.tech.das.log.web
 * LogViewController.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 8. 25.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 8. 25., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
@Controller("logView")
@RequestMapping("/das/log/*")
public class LogViewController {
	@Resource(name = "logDetailService")
	private LogDetailService logDetailService;
	
	@Resource(name = "logRPSService")
	private LogRPSService logRPSService;
	
	@Value("#{dasView['formLogDetailChartView']}")
	private String formLogDetailChartView;
	
	@Value("#{dasView['logDetailChartView']}")
	private String logDetailChartView;

	@Value("#{dasView['formLogRPSChartView']}")
	private String formLogRPSChartView;
	
	@Value("#{dasView['logRPSChartView']}")
	private String logRPSChartView;
	
	/**
	 * <pre>
	 * 1. 개요 : 상세로그 View 메인 Form
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 8. 25.
	 * @Method Name : formLogDeailChart
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("formLogDetailChart.*")
	public ModelAndView formLogDetailChart() throws Exception {
		return new ModelAndView(formLogDetailChartView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 상세로그 Chart 페이지 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 8. 25.
	 * @Method Name : retrieveLogDetailChart
	 * @param logDetailVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("retrieveLogDetailChart.*")
	public ModelAndView retrieveLogDetailChart(LogDetailVO logDetailVO) throws Exception {
		return new ModelAndView(logDetailChartView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : RPS View 메인 Form
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 9. 26.
	 * @Method Name : formLogRPSChart
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("formLogRPSChart.*")
	public ModelAndView formLogRPSChart() throws Exception {
		return new ModelAndView(formLogRPSChartView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : RPS Chart 페이지 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 9. 26.
	 * @Method Name : retrieveLogRPSChart
	 * @param logRPSVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("retrieveLogRPSChart.*")
	public ModelAndView retrieveLogRPSChart(LogRPSVO logRPSVO) throws Exception {
		return new ModelAndView(logRPSChartView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : Process 차트 데이터 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 8. 26.
	 * @Method Name : retrieveLogDetailChartData
	 * @param logDetailVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("retrieveLogDetailChartData.*")
	@ResponseBody
	public Map<String, Object> retrieveLogDetailChartData(LogDetailVO logDetailVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Object> seriesList = logDetailService.selectDetailChartDataGroupId(logDetailVO);
		List<Object> seriesDataList = new ArrayList<Object>();
		
		for(Object groupId : seriesList) {
			logDetailVO.setCondGroupId(groupId.toString());
			seriesDataList.add(logDetailService.selectDetailChartData(logDetailVO));
		}
		
		resultMap.put("seriesList", seriesList);
		resultMap.put("seriesDataList", seriesDataList);
		resultMap.put(WebUtils.ACTION_STATUS_PARAM, WebUtils.ACTION_STATUS_SUCCESS);
		
		return resultMap;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : RPS 차트 데이터 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 9. 26.
	 * @Method Name : retrieveLogRPSChartData
	 * @param logRPSVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("retrieveLogRPSChartData.*")
	@ResponseBody
	public Map<String, Object> retrieveLogRPSChartData(LogRPSVO logRPSVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Object> seriesList = logRPSService.selectRPSChartDataGroupId(logRPSVO);
		List<Object> seriesDataList = new ArrayList<Object>();
		
		for(Object groupId : seriesList) {
			logRPSVO.setCondGroupId(groupId.toString());
			seriesDataList.add(logRPSService.selectRPSChartDataMap(logRPSVO));
		}
		
		resultMap.put("seriesList", seriesList);
		resultMap.put("seriesDataList", seriesDataList);
		resultMap.put(WebUtils.ACTION_STATUS_PARAM, WebUtils.ACTION_STATUS_SUCCESS);
		
		return resultMap;
	}
}
