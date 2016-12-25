package kr.pe.courage.tech.das.log.service.impl;

import org.springframework.stereotype.Repository;

import kr.pe.courage.common.dao.AbstractDAO;
import kr.pe.courage.tech.das.log.service.LogClGroupVO;

/**
 * <pre>
 * kr.pe.courage.tech.das.log.service.impl
 * LogClGroupDAO.java
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
@Repository("logClGroupDAO")
public class LogClGroupDAO extends AbstractDAO<LogClGroupVO> {

	@Override
	protected String getNameSpace() {
		return "logClGroupDAO";
	}

}
