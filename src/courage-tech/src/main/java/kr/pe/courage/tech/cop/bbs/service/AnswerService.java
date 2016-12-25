
package kr.pe.courage.tech.cop.bbs.service;

import java.util.List;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * <pre>
 * kr.pe.courage.tech.cop.bbs.service
 * AnswerService.java
 * </pre>
 * 
 * @Author : ChangHo Seok
 * @Date : 2016. 6. 29.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 6. 29., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public interface AnswerService {
	/**
	 * <pre>
	 * 1. 개요 : 댓글 목록 갯수 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 6. 29.
	 * @Method Name : selectAnswerListCount
	 * @param answerVO
	 * @return
	 * @throws EgovBizException
	 */
	public int selectAnswerListCount(AnswerVO answerVO) throws EgovBizException;

	/**
	 * <pre>
	 * 1. 개요 : 댓글 목록 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 6. 29.
	 * @Method Name : selectAnswerList
	 * @param answerVO
	 * @return
	 * @throws EgovBizException
	 */
	public List<AnswerVO> selectAnswerList(AnswerVO answerVO) throws EgovBizException;

	/**
	 * <pre>
	 * 1. 개요 : 댓글 상세 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 6. 29.
	 * @Method Name : selectAnswer
	 * @param answerVO
	 * @return
	 * @throws EgovBizException
	 */
	public AnswerVO selectAnswer(AnswerVO answerVO) throws EgovBizException;

	/**
	 * <pre>
	 * 1. 개요 : 댓글 등록
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 6. 29.
	 * @Method Name : insertAnswer
	 * @param answerVO
	 * @throws EgovBizException
	 */
	public void insertAnswer(AnswerVO answerVO) throws EgovBizException;

	/**
	 * <pre>
	 * 1. 개요 : 댓글 수정
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 6. 29.
	 * @Method Name : updateAnswer
	 * @param answerVO
	 * @throws EgovBizException
	 */
	public void updateAnswer(AnswerVO answerVO) throws EgovBizException;

	/**
	 * <pre>
	 * 1. 개요 : 댓글 삭제
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 6. 29.
	 * @Method Name : deleteAnswer
	 * @param answerVO
	 * @throws EgovBizException
	 */
	public void deleteAnswer(AnswerVO answerVO) throws EgovBizException;
}
