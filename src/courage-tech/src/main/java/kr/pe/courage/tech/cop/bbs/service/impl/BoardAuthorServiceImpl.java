package kr.pe.courage.tech.cop.bbs.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import kr.pe.courage.tech.cop.bbs.service.BoardAuthorCheckVO;
import kr.pe.courage.tech.cop.bbs.service.BoardAuthorService;
import kr.pe.courage.tech.cop.bbs.service.BoardAuthorVO;

import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;

/**
 * <pre>
 * kr.pe.courage.tech.cop.bbs.service.impl
 * BoardAuthorServiceImpl.java
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
@Service("boardAuthorService")
public class BoardAuthorServiceImpl implements BoardAuthorService {
	
	@Resource(name = "boardAuthorDAO")
	private BoardAuthorDAO boardAuthorDAO;

	@Override
	public List<BoardAuthorVO> selectListForBoardMaster(BoardAuthorVO boardAuthorVO) throws EgovBizException {
		List<BoardAuthorVO> resultList = null;
		
		try {
			resultList = boardAuthorDAO.selectListForBoardMaster(boardAuthorVO);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new EgovBizException("DB 오류");
		}
		
		return resultList;
	}

	@Override
	public int selectListCountForBoardMaster(BoardAuthorVO boardAuthorVO) throws EgovBizException {
		int resultCount = 0;
		
		try {
			resultCount = boardAuthorDAO.selectListCountForBoardMaster(boardAuthorVO);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new EgovBizException("DB 오류");
		}
		
		return resultCount;
	}

	@Override
	public List<BoardAuthorVO> selectListForAuthorInfo(BoardAuthorVO boardAuthorVO) throws EgovBizException {
		List<BoardAuthorVO> resultList = null;
		
		try {
			resultList = boardAuthorDAO.selectListForAuthorInfo(boardAuthorVO);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new EgovBizException("DB 오류");
		}
		
		return resultList;
	}

	@Override
	public int selectListCountForAuthorInfo(BoardAuthorVO boardAuthorVO) throws EgovBizException {
		int resultCount = 0;
		
		try {
			resultCount = boardAuthorDAO.selectListCountForAuthorInfo(boardAuthorVO);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new EgovBizException("DB 오류");
		}
		
		return resultCount;
	}

	@Override
	@TriggersRemove(cacheName = "boardAuthorCache", removeAll = true, when = When.AFTER_METHOD_INVOCATION)
	public void insertBoardAuthor(BoardAuthorVO boardAuthorVO) throws EgovBizException {
		for (int i = 0; i < boardAuthorVO.getAuthorCodes().length; i++) {
			BoardAuthorVO insertVO = new BoardAuthorVO();
			
			insertVO.setAuthorCode(boardAuthorVO.getAuthorCodes()[i]);
			insertVO.setBbsId(boardAuthorVO.getBbsIds()[i]);
			insertVO.setList(boardAuthorVO.getLists()[i]);
			insertVO.setReadng(boardAuthorVO.getReadngs()[i]);
			insertVO.setSntncWritng(boardAuthorVO.getSntncWritngs()[i]);
			insertVO.setAnswerWritng(boardAuthorVO.getAnswerWritngs()[i]);
			insertVO.setMngrAuthor(boardAuthorVO.getMngrAuthors()[i]);
			
			boardAuthorDAO.insert(insertVO);
		}
	}

	@Override
	@TriggersRemove(cacheName = "boardAuthorCache", removeAll = true, when = When.AFTER_METHOD_INVOCATION)
	public void deleteBoardAuthor(BoardAuthorVO boardAuthorVO) throws EgovBizException {
		if (boardAuthorVO == null || (boardAuthorVO.getBbsId() == null && boardAuthorVO.getAuthorCode() == null)) {
			throw new EgovBizException("삭제대상 데이터가 없습니다.");
		}
		
		boardAuthorDAO.delete(boardAuthorVO);
	}

	@Override
	@Cacheable(cacheName = "boardAuthorCache")
	public BoardAuthorCheckVO selectBoardAuthorCheck(BoardAuthorCheckVO boardAuthorCheckVO) throws EgovBizException {
		BoardAuthorCheckVO resultVO = null;
		
		try {
			resultVO = boardAuthorDAO.selectBoardAuthorCheck(boardAuthorCheckVO);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new EgovBizException("DB 오류");
		}
		
		return resultVO;
	}

}
