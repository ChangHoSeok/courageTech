package kr.pe.courage.tech.das.log.service.impl;

import java.util.List;

import javax.annotation.Resource;

import kr.pe.courage.tech.das.log.service.LogClGroupService;
import kr.pe.courage.tech.das.log.service.LogClGroupVO;
import kr.pe.courage.tech.das.log.service.LogFileRceptService;
import kr.pe.courage.tech.das.log.service.LogFileRceptVO;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * <pre>
 * kr.pe.courage.tech.das.log.service.impl
 * LogClGroupServiceImpl.java
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
@Service("logClGroupService")
public class LogClGroupServiceImpl implements LogClGroupService {
	
	@Resource(name = "logClGroupDAO")
	private LogClGroupDAO logClGroupDAO;
	
	@Resource(name = "logFileRceptService")
	private LogFileRceptService logFileRceptService;
	
	@Override
	public List<LogClGroupVO> selectLogClGroupList(LogClGroupVO logClGroupVO) throws EgovBizException {
		return logClGroupDAO.selectList(logClGroupVO);
	}

	@Override
	public int selectLogClGroupListCount(LogClGroupVO logClGroupVO) throws EgovBizException {
		return logClGroupDAO.selectListCount(logClGroupVO);
	}

	@Override
	public LogClGroupVO selectLogClGroupDetail(LogClGroupVO logClGroupVO) throws EgovBizException {
		return logClGroupDAO.getById(logClGroupVO);
	}

	@Override
	public boolean selectLogClGroupIdExists(LogClGroupVO logClGroupVO) throws EgovBizException {
		return logClGroupDAO.isExist(logClGroupVO);
	}

	@Override
	public void insertLogClgroup(LogClGroupVO logClGroupVO) throws EgovBizException {
		logClGroupDAO.insert(logClGroupVO);
	}

	@Override
	public void updateLogClGroup(LogClGroupVO logClGroupVO) throws EgovBizException {
		logClGroupDAO.update(logClGroupVO);
	}

	@Override
	public void deleteLogClGroup(LogClGroupVO logClGroupVO) throws EgovBizException {
		LogFileRceptVO logFileRceptVO = new LogFileRceptVO();
		logFileRceptVO.setGroupId(logClGroupVO.getGroupId());
		
		logFileRceptService.truncateLogFileRcept(logFileRceptVO);
		logClGroupDAO.delete(logClGroupVO);
	}

}
