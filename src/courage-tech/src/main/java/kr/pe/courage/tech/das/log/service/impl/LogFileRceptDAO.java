package kr.pe.courage.tech.das.log.service.impl;

import org.springframework.stereotype.Repository;

import kr.pe.courage.common.dao.AbstractDAO;
import kr.pe.courage.tech.das.log.service.LogFileRceptVO;

/**
 * <pre>
 * kr.pe.courage.tech.das.log.service.impl
 * LogFileRceptDAO.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 7. 22.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 7. 22., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
@Repository("logFileRceptDAO")
public class LogFileRceptDAO extends AbstractDAO<LogFileRceptVO> {

	@Override
	protected String getNameSpace() {
		return "logFileRceptDAO";
	}
	
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
	public void updateProcessSttus(LogFileRceptVO logFileRceptVO) {
		this.update("updateProcessSttus", logFileRceptVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 로그파일접수 정보 그룹단위 삭제
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 8. 24.
	 * @Method Name : truncateLogFileRcept
	 * @param logFileRceptVO
	 */
	public void truncateLogFileRcept(LogFileRceptVO logFileRceptVO) {
		this.delete("truncateLogFileRcept", logFileRceptVO);
	}
}
