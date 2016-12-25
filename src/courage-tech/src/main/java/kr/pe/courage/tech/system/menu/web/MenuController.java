
package kr.pe.courage.tech.system.menu.web;

import java.util.List;

import kr.pe.courage.common.core.CommonDefaultVO;
import kr.pe.courage.common.vo.JxlsParam;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.sym.mnu.mpm.service.MenuManageVO;
import egovframework.com.sym.mnu.mpm.web.AbstractMenuManageController;

/**
 * <pre>
 * kr.pe.courage.tech.system.menu.web
 * MenuController.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2014. 12. 8.
 * @Version	: 
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2014. 12. 8., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
@Controller("menuController")
@RequestMapping(value = "/system/menu/*")
public class MenuController extends AbstractMenuManageController {

	@Value("#{systemView['formMenuView']}")
	private String formMenuView;

	@Value("#{systemView['formMenuPopupView']}")
	private String formMenuPopupView;

	@Value("#{systemView['menuListView']}")
	private String menuListView;

	@Value("#{systemView['menuListPopupView']}")
	private String menuListPopupView;

	@Value("#{systemView['menuDetailView']}")
	private String menuDetailView;

	@Value("#{systemView['menuCreateView']}")
	private String menuCreateView;

	@Value("#{systemView['menuModifyView']}")
	private String menuModifyView;

	@Value("#{systemView['menuProgrmListView']}")
	private String menuProgrmListView;

	@Value("#{systemView['menuIncludeListView']}")
	private String menuIncludeListView;

	@Value("#{pageConfig['menu.page.recordCount']}")
	private int recordCount;

	@Value("#{pageConfig['menu.page.size']}")
	private int pageSize;

	@Value("#{pageConfig['menu.page.enable']}")
	private String pageEnable;

	@Value("#{pageConfig['menuPopup.page.recordCount']}")
	private int popupRecordCount;
	
	@Value("#{pageConfig['menuPopup.page.size']}")
	private int popupPageSize;
	
	@Value("#{pageConfig['menuPopup.page.enable']}")
	private String popupPageEnable;

	protected String getMenuManageListView() {
		return menuListView;
	}

	@Override
	protected String getMenuManageListPopupView() {
		return menuListPopupView;
	}

	protected String getMenuManageDetailView() {
		return menuDetailView;
	}

	protected String getMenuManageCreateView() {
		return menuCreateView;
	}

	protected String getMenuManageModifyView() {
		return menuModifyView;
	}

	protected String getMenuManageIncludeListView() {
		return menuIncludeListView;
	}

	protected String getMenuProgrmManageListView() {
		return menuProgrmListView;
	}

	protected String getMenuManageListAttributeName() {
		return "menuList";
	}

	protected CommonDefaultVO getCommonDefaultVO() {
		CommonDefaultVO commonDefaultVO = new CommonDefaultVO(pageEnable, "", "");
		commonDefaultVO.setRecordCountPerPage(recordCount);
		commonDefaultVO.setPageSize(pageSize);

		return commonDefaultVO;
	}
	
	@Override
	protected CommonDefaultVO getCommonDefaultPopupVO() {
		CommonDefaultVO commonDefaultVO = new CommonDefaultVO(popupPageEnable, "", "");
		commonDefaultVO.setRecordCountPerPage(popupRecordCount);
		commonDefaultVO.setPageSize(popupPageSize);

		return commonDefaultVO;
	}

	/**
	 * <pre>
	 * 1. 개요 : 메뉴 목록 정보 조회
	 * </pre>
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8.
	 * @Method Name : formMenuManage
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "formMenu.*")
	public ModelAndView formMenu(ModelMap model) throws Exception {
		return new ModelAndView(formMenuView, model);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 메뉴 목록 정보 조회 (팝업)
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 7. 14.
	 * @Method Name : formMenuPopup
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "formMenuPopup.*")
	public ModelAndView formMenuPopup(ModelMap model) throws Exception {
		return new ModelAndView(formMenuPopupView, model);
	}

	/**
	 * <pre>
	 * 1. 개요 : 엑셀 다운로드
	 * </pre>
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 9.
	 * @Method Name : downloadMenuExcel
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "downloadMenuExcel.*")
	public ModelAndView downloadMenuExcel(@ModelAttribute("menuManageVO") MenuManageVO menuManageVO, ModelMap model) throws Exception {

		menuManageVO.setPagingEnable(MenuManageVO.PAGING_ENABLE_OFF);

		int totalCnt = menuManageService.selectMenuManageListTotCnt(menuManageVO);
		List<MenuManageVO> menuManageList = menuManageService.selectMenuManageList(menuManageVO);

		JxlsParam jxlsParam = new JxlsParam(JxlsParam.EXCEL_EXT_XLS, "menuExcel", "메뉴 목록", "메뉴 목록", totalCnt);

		model.addAttribute("list", menuManageList);
		model.addAttribute("jxlsParam", jxlsParam);

		return new ModelAndView("jxlsView", model);
	}
}
