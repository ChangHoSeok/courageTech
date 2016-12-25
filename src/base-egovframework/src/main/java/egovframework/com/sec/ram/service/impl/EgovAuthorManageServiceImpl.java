
package egovframework.com.sec.ram.service.impl;

import java.util.List;

import javax.annotation.Resource;

import kr.pe.courage.common.utils.Util;

import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;

import egovframework.com.sec.ram.service.AuthorManageVO;
import egovframework.com.sec.ram.service.EgovAuthorManageService;
import egovframework.com.sym.mnu.mpm.service.MenuManageVO;
import egovframework.com.sym.mnu.mpm.service.impl.MenuManageDAO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/**
 * 권한관리에 관한 ServiceImpl 클래스를 정의한다.
 * 
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.11  이문준          최초 생성
 * 
 * </pre>
 */

@Service("authorManageService")
public class EgovAuthorManageServiceImpl extends AbstractServiceImpl implements EgovAuthorManageService {

	@Resource(name = "authorManageDAO")
	private AuthorManageDAO authorManageDAO;

	@Resource(name = "MenuManageDAO")
	private MenuManageDAO menuManageDAO;

	/**
	 * 권한 목록 카운트를 조회한다.
	 * 
	 * @param authorManageVO
	 *            AuthorManageVO
	 * @return int
	 * @exception Exception
	 */
	public int selectAuthorListTotCnt(AuthorManageVO authorManageVO) throws Exception {
		return authorManageDAO.selectAuthorListTotCnt(authorManageVO);
	}

	/**
	 * 권한 목록을 조회한다.
	 * 
	 * @param authorManageVO
	 *            AuthorManageVO
	 * @return List<AuthorManageVO>
	 * @exception Exception
	 */
	public List<AuthorManageVO> selectAuthorList(AuthorManageVO authorManageVO) throws Exception {
		return authorManageDAO.selectAuthorList(authorManageVO);
	}

	/**
	 * 권한을 조회한다.
	 * 
	 * @param authorManageVO
	 *            AuthorManageVO
	 * @return AuthorManageVO
	 * @exception Exception
	 */
	public AuthorManageVO selectAuthor(AuthorManageVO authorManageVO) throws Exception {
		return authorManageDAO.selectAuthor(authorManageVO);
	}

	/**
	 * 권한을 등록한다.
	 * 
	 * @param authorManage
	 *            AuthorManage
	 * @exception Exception
	 */
	@TriggersRemove(cacheName = "authCheckCache", removeAll = true, when = When.AFTER_METHOD_INVOCATION)
	public void insertAuthor(AuthorManageVO authorManageVO) throws Exception {
		if (Util.nvl(authorManageVO.getMberBassAuthorYn()).equals("Y")) {
			authorManageDAO.updateMberBassAuthorInit();
		}
		
		authorManageDAO.insertAuthor(authorManageVO);
		insertMenuCreatDtls(authorManageVO);
	}

	/**
	 * 권한을 수정한다.
	 * 
	 * @param authorManage
	 *            AuthorManage
	 * @exception Exception
	 */
	@TriggersRemove(cacheName = "authCheckCache", removeAll = true, when = When.AFTER_METHOD_INVOCATION)
	public void updateAuthor(AuthorManageVO authorManageVO) throws Exception {
		if (Util.nvl(authorManageVO.getMberBassAuthorYn()).equals("Y")) {
			authorManageDAO.updateMberBassAuthorInit();
		}
		
		authorManageDAO.updateAuthor(authorManageVO);

		authorManageDAO.deleteMenuCreatDtls(authorManageVO);
		insertMenuCreatDtls(authorManageVO);
	}

	/**
	 * <pre>
	 * 1. 개요 : 메뉴생성 내역 정보 등록
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 4. 12.
	 * @Method Name : insertMenuCreatDtls
	 * @param authorManageVO
	 * @throws Exception
	 */
	private void insertMenuCreatDtls(AuthorManageVO authorManageVO) throws Exception {
		for (int i = 0; i < authorManageVO.getAuthorMenuArray().length; i++) {
			authorManageVO.setMenuNo(authorManageVO.getAuthorMenuArray()[i]);
			authorManageVO.setSearchFlag(authorManageVO.getSearchFlagArray()[i]);
			authorManageVO.setCreateFlag(authorManageVO.getCreateFlagArray()[i]);
			authorManageVO.setCustomFlag1(authorManageVO.getCustomFlag1Array()[i]);
			authorManageVO.setCustomFlag2(authorManageVO.getCustomFlag2Array()[i]);
			authorManageVO.setCustomFlag3(authorManageVO.getCustomFlag3Array()[i]);

			authorManageDAO.insertMenuCreatDtls(authorManageVO);
		}

		// 상위권한 정리 (하위에 포함된 메뉴가 조회권한이 있으면 상위 메뉴도 조회 권한을 가짐)
		MenuManageVO tempVO = new MenuManageVO();
		tempVO.setAuthorCode(authorManageVO.getAuthorCode());
		List<MenuManageVO> searchAuthMenuList = menuManageDAO.selectSearchAuthMenuList(tempVO); // 조회권한이 있는 메뉴의 목록 조회

		for (MenuManageVO menuManageVO : searchAuthMenuList) {
			menuManageVO.setAuthorCode(authorManageVO.getAuthorCode());
			updateUpperMenuAuth(menuManageVO);
		}
	}

	/**
	 * <pre>
	 * 1. 개요 : 계층 구조의 메뉴 구조에서 상위메뉴 권한 정보를 수정한다.
	 *           (하위메뉴의 조회권한이 있으면 상위 메뉴도 조회 권한을 가짐)
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 4. 12.
	 * @Method Name : updateUpperMenuAuth
	 * @param menuManageVO
	 * @throws Exception
	 */
	private void updateUpperMenuAuth(MenuManageVO menuManageVO) throws Exception {
		MenuManageVO tempVO = new MenuManageVO();
		tempVO.setMenuId(menuManageVO.getUpperMenuId());
		tempVO.setAuthorCode(menuManageVO.getAuthorCode());

		MenuManageVO upperMenuVO = menuManageDAO.selectMenuManage(tempVO);

		if (upperMenuVO != null && !"".equals(Util.nvl(upperMenuVO.getUpperMenuId()))) {
			upperMenuVO.setAuthorCode(menuManageVO.getAuthorCode());
			updateUpperMenuAuth(upperMenuVO);
		}

		authorManageDAO.updateSearchAuthorMenu(tempVO);
	}

	/**
	 * 권한을 삭제한다.
	 * 
	 * @param authorManage
	 *            AuthorManage
	 * @exception Exception
	 */
	@TriggersRemove(cacheName = "authCheckCache", removeAll = true, when = When.AFTER_METHOD_INVOCATION)
	public void deleteAuthor(AuthorManageVO authorManageVO) throws Exception {
		authorManageDAO.deleteMenuCreatDtls(authorManageVO);
		authorManageDAO.deleteEmplyrScrtyEstbs(authorManageVO);
		authorManageDAO.deleteAuthor(authorManageVO);
	}
	
	@Override
	@TriggersRemove(cacheName = "authCheckCache", removeAll = true, when = When.AFTER_METHOD_INVOCATION)
	public void removeAuthorCache() throws Exception {
		
	}

	/**
	 * 권한 메뉴 목록조회
	 * 
	 * @param authorManage
	 *            AuthorManage
	 * @exception Exception
	 */
	public List<AuthorManageVO> selectAuthorMenuList(AuthorManageVO authorManageVO) throws Exception {
		return authorManageDAO.selectAuthorMenuList(authorManageVO);
	}
	
	@Cacheable(cacheName = "authCheckCache")
	public AuthorManageVO selectMenuAuthor(AuthorManageVO authorManageVO) throws Exception {
		return authorManageDAO.selectMenuAuthor(authorManageVO);
	}

	public List<AuthorManageVO> selectUserAuthorList(AuthorManageVO authorManageVO) throws Exception {
		return authorManageDAO.selectUserAuthorList(authorManageVO);
	}
	
	public void insertUserAuthManage(AuthorManageVO authorManageVO) throws Exception {
		authorManageDAO.deleteEmplyrScrtyEstbs(authorManageVO);
		
		if (authorManageVO.getAuthorCodeArray().length > 0) {
			for (int i = 0; i < authorManageVO.getAuthorCodeArray().length; i++) {
				authorManageVO.setAuthorCode(authorManageVO.getAuthorCodeArray()[i]);
				authorManageVO.setDefaultAuthorYn(authorManageVO.getDefaultAuthorYnArray()[i]);
				authorManageDAO.insertUserAuthManage(authorManageVO);
			}
		}
	}

	public List<AuthorManageVO> selectLoginUserAuthorList(AuthorManageVO authorManageVO) throws Exception {
		return authorManageDAO.selectLoginUserAuthorList(authorManageVO);
	}

	public List<MenuManageVO> selectAuthorUrlList(MenuManageVO menuManageVO) throws Exception {
		return authorManageDAO.selectAuthorUrlList(menuManageVO);
	}
}
