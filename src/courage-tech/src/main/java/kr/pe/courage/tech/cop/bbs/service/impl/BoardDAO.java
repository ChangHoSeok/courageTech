package kr.pe.courage.tech.cop.bbs.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.pe.courage.common.dao.AbstractDAO;
import kr.pe.courage.tech.cop.bbs.service.BoardVO;

@Repository("boardDAO")
public class BoardDAO extends AbstractDAO<BoardVO> {

	@Override
	protected String getNameSpace() {
		return "boardDAO";
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 게시판 게시물 존재 유무 확인
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 6. 3.
	 * @Method Name : isExistsNTT
	 * @param bbsId
	 * @return
	 * @throws SQLException
	 */
	public boolean isExistsNTT(String bbsId) throws SQLException {
		return this.isExist("isExistsNTT", bbsId);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 게시물 그룹 번호 조회 (부모글과 답변글의 그룹번호)
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 6. 3.
	 * @Method Name : selectBoardGroupNo
	 * @param boardVO
	 * @return
	 * @throws SQLException
	 */
	public int selectBoardGroupNo(BoardVO boardVO) throws SQLException {
		return (Integer) this.getById("selectBoardGroupNo", boardVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 그룹 내 게시물 정렬 순번 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 6. 3.
	 * @Method Name : selectBoardGroupSortOrdr
	 * @param boardVO
	 * @return
	 * @throws SQLException
	 */
	public int selectBoardGroupSortOrdr(BoardVO boardVO) throws SQLException {
		return (Integer) this.getById("selectBoardGroupSortOrdr", boardVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 게시글 조회 수 증가
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 6. 13.
	 * @Method Name : updateBoardRdcntIncrs
	 * @param boardVO
	 * @throws SQLException
	 */
	public void updateBoardRdcntIncrs(BoardVO boardVO) throws SQLException {
		this.update("updateBoardRdcntIncrs", boardVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 공지사항 목록 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 11. 27.
	 * @Method Name : selectNoticeRollingList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<BoardVO> selectNoticeRollingList() {
		return this.selectList("selectNoticeRollingList", null);
	}
}
