package kr.pe.courage.tech.cop.bbs.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import kr.pe.courage.tech.cop.bbs.exception.ExistsNTTException;
import kr.pe.courage.tech.cop.bbs.service.BoardAuthorService;
import kr.pe.courage.tech.cop.bbs.service.BoardAuthorVO;
import kr.pe.courage.tech.cop.bbs.service.BoardMasterService;
import kr.pe.courage.tech.cop.bbs.service.BoardMasterVO;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * <pre>
 * kr.pe.courage.tech.cop.bbs.service.impl
 * BoardMasterServiceImpl.java
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
@Service("boardMasterService")
public class BoardMasterServiceImpl implements BoardMasterService {
	
	@Resource(name = "boardMasterDAO")
	private BoardMasterDAO boardMasterDAO;
	
	@Resource(name = "boardDAO")
	private BoardDAO boardDAO;
	
	@Resource(name = "boardAuthorService")
	private BoardAuthorService boardAuthorService;

	@Override
	public int selectBoardMasterListCount(BoardMasterVO boardMasterVO) throws EgovBizException {
		return boardMasterDAO.selectListCount(boardMasterVO);
	}

	@Override
	public List<BoardMasterVO> selectBoardMasterList(BoardMasterVO boardMasterVO) throws EgovBizException {
		return boardMasterDAO.selectList(boardMasterVO);
	}

	@Override
	public BoardMasterVO selectBoardMaster(BoardMasterVO boardMasterVO) throws EgovBizException {
		BoardMasterVO resultVO = boardMasterDAO.getById(boardMasterVO);
		
		try {
			resultVO.setPosblDelete(!boardDAO.isExistsNTT(boardMasterVO.getBbsId()));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new EgovBizException("DB 오류발생");
		}
		
		return resultVO;
	}

	@Override
	public void insertBoardMaster(BoardMasterVO boardMasterVO) throws EgovBizException {
		boardMasterDAO.insert(boardMasterVO);
	}

	@Override
	public void updateBoardMaster(BoardMasterVO boardMasterVO) throws EgovBizException {
		boardMasterDAO.update(boardMasterVO);
	}

	@Override
	public void deleteBoardMaster(BoardMasterVO boardMasterVO) throws EgovBizException, ExistsNTTException {
		try {
			if (boardDAO.isExistsNTT(boardMasterVO.getBbsId())) {
				// 삭제 대상 게시판에 게시물이 존재할 경우
				throw new ExistsNTTException("삭제하려는 게시판에 게시물이 존재합니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		BoardAuthorVO boardAuthorVO = new BoardAuthorVO();
		boardAuthorVO.setBbsId(boardMasterVO.getBbsId());
		
		boardAuthorService.deleteBoardAuthor(boardAuthorVO);
		boardMasterDAO.delete(boardMasterVO);
	}

	@Override
	public boolean validateBoardMaster(String bbsId) throws EgovBizException {
		return boardMasterDAO.isExistsUseBoard(bbsId);
	}
}
