package kr.pe.courage.tech.cop.bbs.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.pe.courage.common.dao.AbstractDAO;
import kr.pe.courage.tech.cop.bbs.service.BoardAuthorCheckVO;
import kr.pe.courage.tech.cop.bbs.service.BoardAuthorVO;

/**
 * <pre>
 * kr.pe.courage.tech.cop.bbs.service.impl
 * BoardAuthorDAO.java
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
@Repository("boardAuthorDAO")
public class BoardAuthorDAO extends AbstractDAO<BoardAuthorVO> {

	@Override
	protected String getNameSpace() {
		return "boardAuthorDAO";
	}
	
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
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<BoardAuthorVO> selectListForBoardMaster(BoardAuthorVO boardAuthorVO) throws SQLException {
		return this.selectList("selectListForBoardMaster", boardAuthorVO);
	}
	
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
	 * @throws SQLException
	 */
	public int selectListCountForBoardMaster(BoardAuthorVO boardAuthorVO) throws SQLException {
		return this.selectListCount("selectListCountForBoardMaster", boardAuthorVO);
	}
	
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
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<BoardAuthorVO> selectListForAuthorInfo(BoardAuthorVO boardAuthorVO) throws SQLException {
		return this.selectList("selectListForAuthorInfo", boardAuthorVO);
	}
	
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
	 * @throws SQLException
	 */
	public int selectListCountForAuthorInfo(BoardAuthorVO boardAuthorVO) throws SQLException {
		return this.selectListCount("selectListCountForAuthorInfo", boardAuthorVO);
	}
	
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
	 * @throws SQLException
	 */
	public BoardAuthorCheckVO selectBoardAuthorCheck(BoardAuthorCheckVO boardAuthorCheckVO) throws SQLException {
		return (BoardAuthorCheckVO) this.getById("selectBoardAuthorCheck", boardAuthorCheckVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 게시판 권한 정보 삭제
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 11. 23.
	 * @Method Name : deleteBoardAuthor
	 * @param boardAuthorCheckVO
	 */
	public void deleteBoardAuthor(BoardAuthorCheckVO boardAuthorCheckVO) {
		this.delete(boardAuthorCheckVO);
	}
}
