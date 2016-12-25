package egovframework.com.sym.mnu.mpm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import kr.pe.courage.common.utils.Util;

import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;

import egovframework.com.sym.mnu.mpm.service.EgovMenuManageService;
import egovframework.com.sym.mnu.mpm.service.MenuManageVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

/** 
 * 메뉴목록관리, 생성, 사이트맵을 처리하는 비즈니스 구현 클래스를 정의한다.
 * @author 개발환경 개발팀 이용
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
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

@Service("MeunManageService")
public class EgovMenuManageServiceImpl extends AbstractServiceImpl implements EgovMenuManageService{

	@Resource(name="MenuManageDAO")
    private MenuManageDAO menuManageDAO;
	
	/**
	 * 메뉴목록 총건수를 조회한다.
	 * @param menuManageVO
	 * @exception Exception
	 */
    public int selectMenuManageListTotCnt(MenuManageVO menuManageVO) throws Exception {
        return menuManageDAO.selectMenuManageListTotCnt(menuManageVO);
	}
    
	/**
	 * 메뉴 목록을 조회
	 * @param menuManageVO
	 * @exception Exception
	 */
	public List<MenuManageVO> selectMenuManageList(MenuManageVO menuManageVO) throws Exception {
   		return menuManageDAO.selectMenuManageList(menuManageVO);
	}
	
	
	/**
	 * 메뉴 상세정보를 조회
	 * @param vo MenuManageVO
	 * @return MenuManageVO
	 * @exception Exception
	 */
	public MenuManageVO selectMenuManage(MenuManageVO menuManageVO) throws Exception{
        return menuManageDAO.selectMenuManage(menuManageVO);
	}

    /**
	 * 메뉴번호 존재 여부를 조회한다.
	 * @param vo MenuManageVO 
	 * @return int
	 * @exception Exception
	 */
    public int selectMenuNoByPk(MenuManageVO menuManageVO) throws Exception {
        return menuManageDAO.selectMenuNoByPk(menuManageVO);
	}
	
	/**
	 * 메뉴 정보를 등록
	 * @param vo MenuManageVO
	 * @exception Exception
	 */
	public void insertMenuManage(MenuManageVO menuManageVO) throws Exception {
		menuManageDAO.insertMenuManage(menuManageVO);
		
		if (menuManageVO.getMenuProgrmList() != null) {
			for ( int i = 0; i < menuManageVO.getMenuProgrmList().length; i++ ){
				menuManageVO.setProgrmFileNm(menuManageVO.getMenuProgrmList()[i]);
				menuManageDAO.insertMenuProgrm(menuManageVO);
			}
		}
	}

	/**
	 * 메뉴 정보를 수정
	 * @param vo MenuManageVO 
	 * @exception Exception
	 */
	@TriggersRemove(cacheName = "naviCache", removeAll = true, when = When.AFTER_METHOD_INVOCATION)
	public void updateMenuManage(MenuManageVO menuManageVO) throws Exception {
		menuManageDAO.updateMenuManage(menuManageVO);
		
		menuManageDAO.deleteMenuProgrm(menuManageVO);
		
		if (menuManageVO.getMenuProgrmList() != null) {
			for ( int i = 0; i < menuManageVO.getMenuProgrmList().length; i++ ){
				menuManageVO.setProgrmFileNm(menuManageVO.getMenuProgrmList()[i]);
				menuManageDAO.insertMenuProgrm(menuManageVO);
			}
		}
	}

	/**
	 * 메뉴 정보를 삭제
	 * @param vo MenuManageVO
	 * @exception Exception
	 */
	public void deleteMenuManage(MenuManageVO menuManageVO) throws Exception {
		deleteSubMenuManage(menuManageVO);
	}
	
	public void deleteMenuManageList(MenuManageVO menuManageVO) throws Exception {
		for (String menuId : menuManageVO.getDeleteMenuList()) {
			menuManageVO.setMenuId(menuId);
			deleteSubMenuManage(menuManageVO);
		}
	}

	/**
	 * <pre>
	 * 1. 개요 : 하위메뉴 삭제 (삭제하는 메뉴의 하위메뉴까지 모두 삭제)
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2014. 10. 7.
	 * @Method Name : deleteSubMenuManage
	 * @param menuManageVO
	 * @throws Exception
	 */
	private void deleteSubMenuManage(MenuManageVO menuManageVO) throws Exception {
		List<MenuManageVO> deleteSubMenuList = menuManageDAO.selectSubMenuList(menuManageVO);
		
		// 하위메뉴 삭제 (재귀호출)
		for (MenuManageVO subMenuManageVO : deleteSubMenuList) {
			deleteSubMenuManage(subMenuManageVO);
		}
		
		// 자신의 메뉴 삭제
		menuManageDAO.deleteMenuCreatDtls(menuManageVO);
		menuManageDAO.deleteMenuProgrm(menuManageVO);
		menuManageDAO.deleteMenuManage(menuManageVO);
		
	}

	/**
	 * 메뉴번호를 상위메뉴로 참조하고 있는 메뉴 존재여부를 조회
	 * @return int
	 * @exception Exception
	 */
    public int selectUpperMenuNoByPk(MenuManageVO vo) throws Exception {
        return menuManageDAO.selectUpperMenuNoByPk(vo);
	}
    
	public List<MenuManageVO> selectMenuManageListPopup(MenuManageVO menuManageVO) throws Exception {
		return menuManageDAO.selectMenuManageListPopup(menuManageVO);
	}

	public List<MenuManageVO> selectMenuManageIncludeList(MenuManageVO menuManageVO) throws Exception {
		return menuManageDAO.selectMenuManageIncludeList(menuManageVO);
	}

	public List<MenuManageVO> selectHierMenuManageList(MenuManageVO menuManageVO) throws Exception {
		// 최상위 메뉴 목록 조회
		List<MenuManageVO> menuList = menuManageDAO.selectHierMenuManageList(menuManageVO);
		
		if (menuList.size() > 0) {
			for(MenuManageVO menuInfo : menuList) {
				// 하위 메뉴 등록
				menuInfo.setCondSearchFlag("Y"); // 조회조건 없는 하위메뉴 제외
				menuInfo.setAuthorCode(menuManageVO.getAuthorCode());
				menuInfo.setSubMenuList(selectSubMenuManageList(menuInfo));
				
				// 첫번째 접근 URL 조회
				if ("".equals(Util.nvl(menuInfo.getUrl())) && menuInfo.getSubMenuList() != null && menuInfo.getSubMenuList().size() > 0) {
					String topMenuURL = getMenuFirstURL(menuInfo.getSubMenuList()); // 상위메뉴 접근 URL 조회
					menuInfo.setUrl(topMenuURL);
				}
			}
		}
		
		return menuList;
	}
	
	private List<MenuManageVO> selectSubMenuManageList(MenuManageVO menuManageVO) throws Exception {
		MenuManageVO condMenuManageVO = new MenuManageVO();
		condMenuManageVO.setAuthorCode(menuManageVO.getAuthorCode());
		condMenuManageVO.setCondSearchFlag(menuManageVO.getCondSearchFlag());
		condMenuManageVO.setUpperMenuId(menuManageVO.getMenuId());
		
		List<MenuManageVO> subMenuList = menuManageDAO.selectHierMenuManageList(condMenuManageVO);
		
		if (subMenuList.size() > 0) {
			for(MenuManageVO menuInfo : subMenuList) {
				menuInfo.setCondSearchFlag("Y"); // 조회조건 없는 하위메뉴 제외
				menuInfo.setAuthorCode(menuManageVO.getAuthorCode());
				menuInfo.setSubMenuList(selectSubMenuManageList(menuInfo));
			}
		}
		
		return subMenuList;
	}
	
	private String getMenuFirstURL(List<MenuManageVO> subMenuList) throws Exception {
		String resultStr = "";
		
		for (MenuManageVO menuInfo : subMenuList) {
			if (!"".equals(Util.nvl(menuInfo.getUrl()))) {
				resultStr = menuInfo.getUrl() + "?menuNo=" + menuInfo.getMenuId();
			} else {
				resultStr = getMenuFirstURL(menuInfo.getSubMenuList());
			}
			
			if (!"".equals(resultStr)) {
				break;
			}
		}
		
		return resultStr;
	}
	
	public List<MenuManageVO> selectSingleMenuManageList(MenuManageVO menuManageVO) throws Exception {
		if ("".equals(Util.nvl(menuManageVO.getUpperMenuId()))) {
			String topMenuId = menuManageDAO.selectTopMenuNo();
			menuManageVO.setUpperMenuId(topMenuId);
		}
		
		menuManageVO.setCondSearchFlag("Y"); // 조회조건 없는 하위메뉴 제외
		
		return menuManageDAO.selectHierMenuManageList(menuManageVO);
	}
	
	@Cacheable(cacheName = "naviCache")
	public List<MenuManageVO> selectMenuNaviList(MenuManageVO menuManageVO) throws Exception {
		return menuManageDAO.selectMenuNaviList(menuManageVO);
	}
}