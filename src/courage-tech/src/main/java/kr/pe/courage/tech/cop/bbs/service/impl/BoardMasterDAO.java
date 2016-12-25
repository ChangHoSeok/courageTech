package kr.pe.courage.tech.cop.bbs.service.impl;

import org.springframework.stereotype.Repository;

import kr.pe.courage.common.dao.AbstractDAO;
import kr.pe.courage.tech.cop.bbs.service.BoardMasterVO;

/**
 * <pre>
 * kr.pe.courage.tech.cop.bbs.service.impl
 * BoardMasterDAO.java
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
@Repository("boardMasterDAO")
public class BoardMasterDAO extends AbstractDAO<BoardMasterVO> {

	@Override
	protected String getNameSpace() {
		return "boardMasterDAO";
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 게시판 존재여부 확인 (사용)
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 6. 1.
	 * @Method Name : isExistsUseBoard
	 * @param bbsId
	 * @return
	 */
	public boolean isExistsUseBoard(String bbsId) {
		return this.isExist("isExistsUseBoard", bbsId);
	}
	
}
