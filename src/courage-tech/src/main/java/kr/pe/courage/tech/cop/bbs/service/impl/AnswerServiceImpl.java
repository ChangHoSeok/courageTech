package kr.pe.courage.tech.cop.bbs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import kr.pe.courage.tech.cop.bbs.service.AnswerService;
import kr.pe.courage.tech.cop.bbs.service.AnswerVO;

/**
 * <pre>
 * kr.pe.courage.tech.cop.bbs.service.impl
 * AnswerServiceImpl.java
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
@Service("answerService")
public class AnswerServiceImpl implements AnswerService {
	
	@Resource(name = "answerDAO")
	private AnswerDAO answerDAO;

	@Override
	public int selectAnswerListCount(AnswerVO answerVO) throws EgovBizException {
		return answerDAO.selectListCount(answerVO);
	}

	@Override
	public List<AnswerVO> selectAnswerList(AnswerVO answerVO) throws EgovBizException {
		return answerDAO.selectList(answerVO);
	}

	@Override
	public AnswerVO selectAnswer(AnswerVO answerVO) throws EgovBizException {
		return answerDAO.getById(answerVO);
	}

	@Override
	public void insertAnswer(AnswerVO answerVO) throws EgovBizException {
		answerDAO.insert(answerVO);
	}

	@Override
	public void updateAnswer(AnswerVO answerVO) throws EgovBizException {
		answerDAO.update(answerVO);
	}

	@Override
	public void deleteAnswer(AnswerVO answerVO) throws EgovBizException {
		answerDAO.delete(answerVO);
	}

}
