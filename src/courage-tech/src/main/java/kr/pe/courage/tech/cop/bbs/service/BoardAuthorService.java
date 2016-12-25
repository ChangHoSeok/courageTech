package kr.pe.courage.tech.cop.bbs.service;

import java.util.List;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * <pre>
 * kr.pe.courage.tech.cop.bbs.service
 * BoardAuthorService.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 7. 8.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 7. 8., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public interface BoardAuthorService {
	/**
	 * <pre>
	 * 1. 개요 : 게시판 권한관리 목록 조회 (조회 기준 값 게시판 마스터)
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 8.
	 * @Method Name : selectListForBoardMaster
	 * @param boardAuthorVO
	 * @return
	 * @throws EgovBizException
	 */
	public List<BoardAuthorVO> selectListForBoardMaster(BoardAuthorVO boardAuthorVO) throws EgovBizException;
	
	/**
	 * <pre>
	 * 1. 개요 : 게시판 권한관리 목록 갯수 조회 (조회 기준 값 게시판 마스터)
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 11.
	 * @Method Name : selectListCountForBoardMaster
	 * @param boardAuthorVO
	 * @return
	 * @throws EgovBizException
	 */
	public int selectListCountForBoardMaster(BoardAuthorVO boardAuthorVO) throws EgovBizException;
	
	/**
	 * <pre>
	 * 1. 개요 : 게시판 권한관리 목록 조회 (조회 기준 값 권한 정보)
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 8.
	 * @Method Name : selectListForAuthorInfo
	 * @param boardAuthorVO
	 * @return
	 * @throws EgovBizException
	 */
	public List<BoardAuthorVO> selectListForAuthorInfo(BoardAuthorVO boardAuthorVO) throws EgovBizException;
	
	/**
	 * <pre>
	 * 1. 개요 : 게시판 권한관리 목록 갯수 조회 (조회 기준 값 권한 정보)
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 11.
	 * @Method Name : selectListCountForAuthorInfo
	 * @param boardAuthorVO
	 * @return
	 * @throws EgovBizException
	 */
	public int selectListCountForAuthorInfo(BoardAuthorVO boardAuthorVO) throws EgovBizException;
	
	/**
	 * <pre>
	 * 1. 개요 : 게시판 권한관리 정보 등록/수정
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 11.
	 * @Method Name : insertBoardAuthor
	 * @param boardAuthorVO
	 * @throws EgovBizException
	 */
	public void insertBoardAuthor(BoardAuthorVO boardAuthorVO) throws EgovBizException;
	
	/**
	 * <pre>
	 * 1. 개요 : 게시판 권한정보 삭제
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 11. 23.
	 * @Method Name : deleteBoardAuthor
	 * @param boardAuthorVO
	 * @throws EgovBizException
	 */
	public void deleteBoardAuthor(BoardAuthorVO boardAuthorVO) throws EgovBizException;
	
	/**
	 * <pre>
	 * 1. 개요 : 게시판 권한 정보 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 12.
	 * @Method Name : selectBoardAuthorCheck
	 * @param boardAuthorCheckVO
	 * @return
	 * @throws EgovBizException
	 */
	public BoardAuthorCheckVO selectBoardAuthorCheck(BoardAuthorCheckVO boardAuthorCheckVO) throws EgovBizException;
}
