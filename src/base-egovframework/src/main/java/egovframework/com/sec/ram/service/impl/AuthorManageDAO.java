package egovframework.com.sec.ram.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sec.ram.service.AuthorManageVO;
import egovframework.com.sym.mnu.mpm.service.MenuManageVO;

/**
 * 권한관리에 대한 DAO 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.11  이문준          최초 생성
 *
 * </pre>
 */

@Repository("authorManageDAO")
public class AuthorManageDAO extends EgovComAbstractDAO {

    /**
	 * 권한목록 총 갯수를 조회한다.
	 * @param authorManageVO AuthorManageVO
	 * @return int
	 * @exception Exception
	 */
    public int selectAuthorListTotCnt(AuthorManageVO authorManageVO)  throws Exception {
        return (Integer)getSqlMapClientTemplate().queryForObject("authorManageDAO.selectAuthorListTotCnt", authorManageVO);
    }
    
    /**
	 * 권한목록 조회한다.
	 * @param authorManageVO AuthorManageVO
	 * @return List<AuthorManageVO>
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<AuthorManageVO> selectAuthorList(AuthorManageVO authorManageVO) throws Exception {
        return list("authorManageDAO.selectAuthorList", authorManageVO);
    }
	
    /**
	 * 권한을 조회한다.
	 * @param authorManageVO AuthorManageVO
	 * @return AuthorManageVO
	 * @exception Exception
	 */
    public AuthorManageVO selectAuthor(AuthorManageVO authorManageVO) throws Exception {
        return (AuthorManageVO) selectByPk("authorManageDAO.selectAuthor", authorManageVO);
    }
    
	/**
	 * 권한을 등록한다.
	 * @param authorManage AuthorManage
	 * @exception Exception
	 */
    public void insertAuthor(AuthorManageVO authorManageVO) throws Exception {
        insert("authorManageDAO.insertAuthor", authorManageVO);
    }

    /**
	 * 권한을 수정한다.
	 * @param authorManage AuthorManage
	 * @exception Exception
	 */
    public void updateAuthor(AuthorManageVO authorManageVO) throws Exception {
        update("authorManageDAO.updateAuthor", authorManageVO);
    }

    /**
	 * 권한을 삭제한다.
	 * @param authorManage AuthorManage
	 * @exception Exception
	 */
    public void deleteAuthor(AuthorManageVO authorManageVO) throws Exception {
        delete("authorManageDAO.deleteAuthor", authorManageVO);
    }

    /**
	 * 권한 메뉴매핑정보 등록
	 * @param authorManage AuthorManage
	 * @exception Exception
	 */
	public void insertMenuCreatDtls(AuthorManageVO authorManageVO) throws Exception {
		insert("authorManageDAO.insertMenuCreatDtls", authorManageVO);
	}

    /**
	 * 권한 메뉴매핑정보 삭제
	 * @param authorManage AuthorManage
	 * @exception Exception
	 */
	public void deleteMenuCreatDtls(AuthorManageVO authorManageVO) throws Exception {
		delete("authorManageDAO.deleteMenuCreatDtls", authorManageVO);
	}

    /**
	 * 권한 메뉴 목록조회
	 * @param authorManage AuthorManage
	 * @exception Exception
	 */
	@SuppressWarnings("unchecked")
	public List<AuthorManageVO> selectAuthorMenuList(AuthorManageVO authorManageVO) throws Exception {
		return list("authorManageDAO.selectAuthorMenuList", authorManageVO);
	}
	
	public AuthorManageVO selectMenuAuthor(AuthorManageVO authorManageVO) throws Exception {
		return (AuthorManageVO) selectByPk("authorManageDAO.selectMenuAuthor", authorManageVO);
	}

	@SuppressWarnings("unchecked")
	public List<AuthorManageVO> selectUserAuthorList(AuthorManageVO authorManageVO) throws Exception {
		return list("authorManageDAO.selectUserAuthorList", authorManageVO);
	}
	
	@SuppressWarnings("unchecked")
	public List<AuthorManageVO> selectLoginUserAuthorList(AuthorManageVO authorManageVO) throws Exception {
		return list("authorManageDAO.selectLoginUserAuthorList", authorManageVO);
	}

	public void deleteEmplyrScrtyEstbs(AuthorManageVO authorManageVO) throws Exception {
		delete("authorManageDAO.deleteEmplyrScrtyEstbs", authorManageVO);
	}

	public void insertUserAuthManage(AuthorManageVO authorManageVO) {
		insert("authorManageDAO.insertUserAuthManage", authorManageVO);
	}
	
	@SuppressWarnings("unchecked")
	public List<MenuManageVO> selectAuthorUrlList(MenuManageVO menuManageVO) throws Exception {
		return list("authorManageDAO.selectAuthorUrlList", menuManageVO);
	}
	
	public void updateSearchAuthorMenu(MenuManageVO menuManageVO) throws Exception {
		update("authorManageDAO.updateSearchAuthorMenu", menuManageVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 기본권한 설정 값 초기화 (모두 N 상태로 변경)
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 4. 12.
	 * @Method Name : updateMberBassAuthorInit
	 */
	public void updateMberBassAuthorInit() {
		getSqlMapClientTemplate().update("authorManageDAO.updateMberBassAuthorInit");
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 사용자 기본권한 조회
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 4. 12.
	 * @Method Name : selectMberBassAuthor
	 * @return
	 */
	public String selectMberBassAuthor() {
		return (String) getSqlMapClientTemplate().queryForObject("authorManageDAO.selectMberBassAuthor");
	}
}
