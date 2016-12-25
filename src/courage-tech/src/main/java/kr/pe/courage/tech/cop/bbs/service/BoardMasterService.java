package kr.pe.courage.tech.cop.bbs.service;

import java.util.List;

import kr.pe.courage.tech.cop.bbs.exception.ExistsNTTException;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * <pre>
 * kr.pe.courage.tech.cop.bbs.service
 * BoardMasterService.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 5. 20.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 5. 20., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public interface BoardMasterService {
	/**
	 * <pre>
	 * 1. 개요 : 게시판 마스터 목록 갯수 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 5. 20.
	 * @Method Name : selectBoardMasterListCount
	 * @param boardMasterVO
	 * @return
	 * @throws EgovBizException
	 */
	public int selectBoardMasterListCount(BoardMasterVO boardMasterVO) throws EgovBizException;
	
	/**
	 * <pre>
	 * 1. 개요 : 게시판 마스터 목록 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 5. 20.
	 * @Method Name : selectBoardMasterList
	 * @param boardMasterVO
	 * @return
	 * @throws EgovBizException
	 */
	public List<BoardMasterVO> selectBoardMasterList(BoardMasterVO boardMasterVO) throws EgovBizException;
	
	/**
	 * <pre>
	 * 1. 개요 : 게시판 마스터 상세정보 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 5. 20.
	 * @Method Name : selectBoardMaster
	 * @param boardMasterVO
	 * @return
	 * @throws EgovBizException
	 */
	public BoardMasterVO selectBoardMaster(BoardMasterVO boardMasterVO) throws EgovBizException;
	
	/**
	 * <pre>
	 * 1. 개요 : 게시판 마스터 정보 등록
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 5. 20.
	 * @Method Name : insertBoardMaster
	 * @param boardMasterVO
	 * @throws EgovBizException
	 */
	public void insertBoardMaster(BoardMasterVO boardMasterVO) throws EgovBizException;
	
	/**
	 * <pre>
	 * 1. 개요 : 게시판 마스터 정보 수정
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 5. 20.
	 * @Method Name : updateBoardMaster
	 * @param boardMasterVO
	 * @throws EgovBizException
	 */
	public void updateBoardMaster(BoardMasterVO boardMasterVO) throws EgovBizException;
	
	/**
	 * <pre>
	 * 1. 개요 : 게시판 마스터 정보 삭제
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 5. 20.
	 * @Method Name : deleteBoardMaster
	 * @param boardMasterVO
	 * @throws EgovBizException
	 */
	public void deleteBoardMaster(BoardMasterVO boardMasterVO) throws EgovBizException, ExistsNTTException;
	
	/**
	 * <pre>
	 * 1. 개요 : 게시판 유효상태 확인
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 6. 1.
	 * @Method Name : validateBoardMaster
	 * @param bbsId
	 * @return
	 * @throws EgovBizException
	 */
	public boolean validateBoardMaster(String bbsId) throws EgovBizException;
}
