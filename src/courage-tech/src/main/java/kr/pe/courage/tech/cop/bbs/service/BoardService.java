
package kr.pe.courage.tech.cop.bbs.service;

import java.util.List;

import kr.pe.courage.tech.cop.bbs.exception.ExistsAnswerException;
import kr.pe.courage.tech.cop.bbs.exception.ExistsNTTException;
import kr.pe.courage.tech.cop.bbs.exception.ExistsReplyNTTException;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * <pre>
 * kr.pe.courage.tech.cop.bbs.service
 * BoardService.java
 * </pre>
 * 
 * @Author : ChangHo Seok
 * @Date : 2016. 5. 30.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 5. 30., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public interface BoardService {
	/**
	 * <pre>
	 * 1. 개요 : 게시물 목록 갯수 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 5. 30.
	 * @Method Name : selectBoardListCount
	 * @param boardVO
	 * @return
	 * @throws EgovBizException
	 */
	public int selectBoardListCount(BoardVO boardVO) throws EgovBizException;

	/**
	 * <pre>
	 * 1. 개요 : 게시물 목록 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 5. 30.
	 * @Method Name : selectBoardList
	 * @param boardVO
	 * @return
	 * @throws EgovBizException
	 */
	public List<BoardVO> selectBoardList(BoardVO boardVO) throws EgovBizException;

	/**
	 * <pre>
	 * 1. 개요 : 게시물 상세 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 5. 30.
	 * @Method Name : selectBoard
	 * @param boardVO
	 * @return
	 * @throws EgovBizException
	 */
	public BoardVO selectBoard(BoardVO boardVO) throws EgovBizException;
	
	/**
	 * <pre>
	 * 1. 개요 : 게시물 조회수 증가
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 6. 14.
	 * @Method Name : updateBoardRdcntIncrs
	 * @param boardVO
	 * @throws EgovBizException
	 */
	public void updateBoardRdcntIncrs(BoardVO boardVO) throws EgovBizException;

	/**
	 * <pre>
	 * 1. 개요 : 게시물 정보 등록
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 5. 30.
	 * @Method Name : insertBoard
	 * @param boardVO
	 * @throws EgovBizException
	 */
	public void insertBoard(BoardVO boardVO) throws EgovBizException;

	/**
	 * <pre>
	 * 1. 개요 : 게시물 정보 수정
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 5. 30.
	 * @Method Name : updateBoard
	 * @param boardVO
	 * @throws EgovBizException
	 */
	public void updateBoard(BoardVO boardVO) throws EgovBizException;

	/**
	 * <pre>
	 * 1. 개요 : 게시물 삭제
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 5. 30.
	 * @Method Name : deleteBoard
	 * @param boardVO
	 * @throws EgovBizException
	 * @throws ExistsNTTException
	 * @throws ExistsAnswerException
	 */
	public void deleteBoard(BoardVO boardVO) throws EgovBizException, ExistsReplyNTTException, ExistsAnswerException;
	
	/**
	 * <pre>
	 * 1. 개요 : 공지사항 목록 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 11. 27.
	 * @Method Name : selectNoticeRollingList
	 * @return
	 * @throws EgovBizException
	 */
	public List<BoardVO> selectNoticeRollingList() throws EgovBizException;
}
