
package egovframework.com.sym.mnu.mpm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.sym.mnu.mpm.service.MenuManageVO;

/**
 * 메뉴관리, 메뉴생성, 사이트맵 생성에 대한 DAO 클래스를 정의한다.
 * 
 * @author 개발환경 개발팀 이용
 * @since 2009.06.01
 * @version 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이  용          최초 생성
 *   2011.07.01  서준식			자기 메뉴 정보를 상위메뉴 정보로 참조하는 메뉴정보가 있는지 조회하는 
 *   							selectUpperMenuNoByPk() 메서드 추가
 * 
 * </pre>
 */

@Repository("MenuManageDAO")
public class MenuManageDAO extends EgovComAbstractDAO {

	/**
	 * 메뉴목록관리 총건수를 조회한다.
	 * @param menuManageVO
	 * @exception Exception
	 */
	public int selectMenuManageListTotCnt(MenuManageVO menuManageVO) {
		return (Integer) getSqlMapClientTemplate().queryForObject("MenuManage.selectMenuManageListTotCnt_S", menuManageVO);
	}

	/**
	 * 메뉴목록을 조회
	 * @param menuManageVO
	 * @return List
	 * @exception Exception
	 */
	public List<MenuManageVO> selectMenuManageList(MenuManageVO menuManageVO) throws Exception {
		return list("MenuManage.selectMenuManageList_D", menuManageVO);
	}
	
	public List<MenuManageVO> selectSearchAuthMenuList(MenuManageVO menuManageVO) throws Exception {
		return list("MenuManage.selectSearchAuthMenuList", menuManageVO);
	}

	/**
	 * 메뉴목록관리 기본정보를 조회
	 * @param menuManageVO
	 * @return MenuManageVO
	 * @exception Exception
	 */
	public MenuManageVO selectMenuManage(MenuManageVO menuManageVO) throws Exception {
		return (MenuManageVO) selectByPk("MenuManage.selectMenuManage_D", menuManageVO);
	}
	
	
	/**
	 * <pre>
	 * 1. 개요 : 하위메뉴 상세정보 조회
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2014. 10. 7.
	 * @Method Name : selectSubMenuList
	 * @param menuManageVO
	 * @return
	 * @throws Exception
	 */
	public List<MenuManageVO> selectSubMenuList(MenuManageVO menuManageVO) throws Exception {
		return list("MenuManage.selectSubMenuList", menuManageVO);
	}

	/**
	 * 메뉴번호 존재여부를 조회
	 * @param menuManageVO
	 * @return int
	 * @exception Exception
	 */
	public int selectMenuNoByPk(MenuManageVO menuManageVO) throws Exception {
		return (Integer) selectByPk("MenuManage.selectMenuNoByPk", menuManageVO);
	}
	
	/**
	 * 메뉴목록 기본정보를 등록
	 * @param MenuManageVO
	 * @exception Exception
	 */
	public void insertMenuManage(MenuManageVO menuManageVO) {
		insert("MenuManage.insertMenuManage_S", menuManageVO);
	}

	/**
	 * 메뉴목록 기본정보를 수정
	 * @param MenuManageVO
	 * @exception Exception
	 */
	public void updateMenuManage(MenuManageVO menuManageVO) {
		update("MenuManage.updateMenuManage_S", menuManageVO);
	}

	/**
	 * 메뉴목록 기본정보를 삭제
	 * @param MenuManageVO
	 * @exception Exception
	 */
	public void deleteMenuManage(MenuManageVO menuManageVO) {
		delete("MenuManage.deleteMenuManage_S", menuManageVO);
	}

	public void deleteMenuCreatDtls(MenuManageVO menuManageVO) {
		delete("MenuManage.deleteMenuCreatDtls", menuManageVO);
	}
	
	/**
	 * 메뉴 전체목록을 조회
	 * 
	 * @return list
	 * @exception Exception
	 */
	public List selectMenuList() throws Exception {
		ComDefaultVO vo = new ComDefaultVO();
		return list("MenuManage.selectMenuListT_D", vo);
	}



	/**
	 * 메뉴번호를 상위메뉴로 참조하고 있는 메뉴 존재여부를 조회
	 * 
	 * @param vo
	 *            MenuManageVO
	 * @return int
	 * @exception Exception
	 */
	public int selectUpperMenuNoByPk(MenuManageVO vo) throws Exception {
		return (Integer) selectByPk("MenuManage.selectUpperMenuNoByPk", vo);
	}

	/**
	 * 메뉴정보 전체삭제 초기화
	 * 
	 * @return boolean
	 * @exception Exception
	 */
	public boolean deleteAllMenuList() {
		MenuManageVO vo = new MenuManageVO();
		insert("MenuManage.deleteAllMenuList", vo);
		return true;
	}

	/**
	 * 메뉴정보 존재여부 조회한다.
	 * 
	 * @return int
	 * @exception Exception
	 */
	public int selectMenuListTotCnt() {
		MenuManageVO vo = new MenuManageVO();
		return (Integer) getSqlMapClientTemplate().queryForObject("MenuManage.selectMenuListTotCnt", vo);
	}

	/* ### 메뉴관련 프로세스 ### */
	/**
	 * MainMenu Head Menu 조회
	 * 
	 * @param vo
	 *            MenuManageVO
	 * @return List
	 * @exception Exception
	 */
	public List selectMainMenuHead(MenuManageVO vo) throws Exception {
		return list("MenuManage.selectMainMenuHead", vo);
	}

	/**
	 * MainMenu Left Menu 조회
	 * 
	 * @param vo
	 *            MenuManageVO
	 * @return List
	 * @exception Exception
	 */
	public List selectMainMenuLeft(MenuManageVO vo) throws Exception {
		return list("MenuManage.selectMainMenuLeft", vo);
	}

	/**
	 * MainMenu Head MenuURL 조회
	 * 
	 * @param vo
	 *            MenuManageVO
	 * @return String
	 * @exception Exception
	 */
	public String selectLastMenuURL(MenuManageVO vo) throws Exception {
		return (String) getSqlMapClientTemplate().queryForObject("MenuManage.selectLastMenuURL", vo);
	}

	/**
	 * MainMenu Left Menu 조회
	 * 
	 * @param vo
	 *            MenuManageVO
	 * @return int
	 * @exception Exception
	 */
	public int selectLastMenuNo(MenuManageVO vo) throws Exception {
		return (Integer) getSqlMapClientTemplate().queryForObject("MenuManage.selectLastMenuNo", vo);
	}

	/**
	 * MainMenu Left Menu 조회
	 * 
	 * @param vo
	 *            MenuManageVO
	 * @return int
	 * @exception Exception
	 */
	public int selectLastMenuNoCnt(MenuManageVO vo) throws Exception {
		return (Integer) getSqlMapClientTemplate().queryForObject("MenuManage.selectLastMenuNoCnt", vo);
	}

	/**
	 * <pre>
	 * 1. 개요 : 메뉴 프로그램 등록
	 * </pre>
	 * 
	 * @Author	: Administrator
	 * @Date	: 2013. 12. 7.
	 * @Method Name : insertMenuProgrm
	 * @param menuManageVO
	 * @throws Exception
	 */
	public void insertMenuProgrm(MenuManageVO menuManageVO) throws Exception {
		insert("MenuManage.insertMenuProgrm", menuManageVO);
	}

	public void deleteMenuProgrm(MenuManageVO menuManageVO) throws Exception {
		delete("MenuManage.deleteMenuProgrm", menuManageVO);
	}

	@SuppressWarnings("unchecked")
	public List<MenuManageVO> selectMenuManageListPopup(MenuManageVO menuManageVO) throws Exception {
		return list("MenuManage.selectMenuManageListPopup", menuManageVO);
	}

	@SuppressWarnings("unchecked")
	public List<MenuManageVO> selectMenuManageIncludeList(MenuManageVO menuManageVO) throws Exception {
		return list("MenuManage.selectMenuManageIncludeList", menuManageVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 계층형 메뉴 조회
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2014. 10. 17.
	 * @Method Name : selectHierMenuManageList
	 * @param menuManageVO
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<MenuManageVO> selectHierMenuManageList(MenuManageVO menuManageVO) throws Exception {
		return list("MenuManage.selectHierMenuManageList", menuManageVO);
	}
	
	public String selectTopMenuNo() throws Exception {
		return (String) getSqlMapClientTemplate().queryForObject("MenuManage.selectTopMenuNo");
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 메뉴 아이디에 해당하는 네비게이션 목록 조회
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2015. 1. 8.
	 * @Method Name : selectMenuNaviList
	 * @param menuManageVO
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<MenuManageVO> selectMenuNaviList(MenuManageVO menuManageVO) throws Exception {
		return list("MenuManage.selectMenuNaviList", menuManageVO);
	}
}