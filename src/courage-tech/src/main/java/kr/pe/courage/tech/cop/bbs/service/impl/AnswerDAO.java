package kr.pe.courage.tech.cop.bbs.service.impl;

import org.springframework.stereotype.Repository;

import kr.pe.courage.common.dao.AbstractDAO;
import kr.pe.courage.tech.cop.bbs.service.AnswerVO;

/**
 * <pre>
 * kr.pe.courage.tech.cop.bbs.service.impl
 * AnswerDAO.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 6. 29.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 6. 29., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
@Repository("answerDAO")
public class AnswerDAO extends AbstractDAO<AnswerVO> {

	@Override
	protected String getNameSpace() {
		return "answerDAO";
	}
	
}
