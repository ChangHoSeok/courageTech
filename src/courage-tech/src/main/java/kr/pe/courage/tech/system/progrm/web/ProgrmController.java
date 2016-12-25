
package kr.pe.courage.tech.system.progrm.web;

import java.util.List;

import javax.annotation.Resource;

import kr.pe.courage.common.core.CommonDefaultVO;
import kr.pe.courage.common.vo.JxlsParam;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.sec.ram.service.EgovAuthorManageService;
import egovframework.com.sym.prm.service.ProgrmManageVO;
import egovframework.com.sym.prm.web.AbstractProgrmManageController;

/**
 * <pre>
 * kr.pe.courage.tech.system.progrm.web
 * ProgrmController.java
 * </pre>
 * 
 * @Author : Seok Chang Ho
 * @Date : 2014. 12. 8.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2014-12-08, 수정자 : 석창호, 수정내용 : 최초등록
 * </pre>
 */
@Controller("progrmController")
@RequestMapping(value = "/system/progrm/*")
public class ProgrmController extends AbstractProgrmManageController {

	// 권한 서비스
	@Resource(name = "authorManageService")
	private EgovAuthorManageService authorManageService;

	@Value("#{systemView['formProgrmView']}")
	private String formProgrmView;

	@Value("#{systemView['progrmListView']}")
	private String progrmListView;

	@Value("#{systemView['progrmDetailView']}")
	private String progrmDetailView;

	@Value("#{systemView['progrmCreateView']}")
	private String progrmCreateView;

	@Value("#{systemView['progrmModifyView']}")
	private String progrmModifyView;

	@Value("#{systemView['formProgrmPopupView']}")
	private String formProgrmPopupView;

	@Value("#{systemView['progrmListPopupView']}")
	private String progrmListPopupView;

	@Value("#{pageConfig['progrm.page.recordCount']}")
	private int recordCount;

	@Value("#{pageConfig['progrm.page.size']}")
	private int pageSize;

	@Value("#{pageConfig['progrm.page.enable']}")
	private String pageEnable;

	@Value("#{pageConfig['progrmPopup.page.recordCount']}")
	private int popupRecordCount;

	@Value("#{pageConfig['progrmPopup.page.size']}")
	private int popupPageSize;

	@Value("#{pageConfig['progrmPopup.page.enable']}")
	private String popupPageEnable;

	protected String getProgrmManageListView() {
		return progrmListView;
	}

	public String getProgrmManageListPopupView() {
		return progrmListPopupView;
	}

	protected String getProgrmManageDetailView() {
		return progrmDetailView;
	}

	protected String getProgrmManageCreateView() {
		return progrmCreateView;
	}

	protected String getProgrmManageModifyView() {
		return progrmModifyView;
	}

	protected String getProgrmManageListAttributeName() {
		return "progrmList";
	}

	protected CommonDefaultVO getCommonDefaultVO() {
		CommonDefaultVO commonDefaultVO = new CommonDefaultVO(pageEnable, "URL", "ASC");
		commonDefaultVO.setRecordCountPerPage(recordCount);
		commonDefaultVO.setPageSize(pageSize);

		return commonDefaultVO;
	}

	@Override
	protected CommonDefaultVO getCommonDefaultPopupVO() {
		CommonDefaultVO commonDefaultVO = new CommonDefaultVO(popupPageEnable, "URL", "ASC");
		commonDefaultVO.setRecordCountPerPage(popupRecordCount);
		commonDefaultVO.setPageSize(popupPageSize);

		return commonDefaultVO;
	}

	/**
	 * <pre>
	 * 1. 개요 : 프로그램 목록 정보 조회
	 * </pre>
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8.
	 * @Method Name : formProgmManage
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "formProgrm.*")
	public ModelAndView formProgrm(ModelMap model) throws Exception {
		return new ModelAndView(formProgrmView, model);
	}

	/**
	 * <pre>
	 * 1. 개요 : 프로그램 목록 정보 조회 (팝업)
	 * </pre>
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 9.
	 * @Method Name : formProgrmPopup
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "formProgrmPopup.*")
	public ModelAndView formProgrmPopup() throws Exception {
		return new ModelAndView(formProgrmPopupView);
	}

	/**
	 * <pre>
	 * 1. 개요 : 엑셀 다운로드
	 * </pre>
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 9.
	 * @Method Name : downloadProgrmExcel
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "downloadProgrmExcel.*")
	public ModelAndView downloadProgrmExcel(@ModelAttribute("progrmManageVO") ProgrmManageVO progrmManageVO, ModelMap model)
			throws Exception {

		progrmManageVO.setPagingEnable(ProgrmManageVO.PAGING_ENABLE_OFF);

		int totalCnt = progrmManageService.selectProgrmListTotCnt(progrmManageVO);
		List<ProgrmManageVO> progrmManageList = progrmManageService.selectProgrmList(progrmManageVO);

		JxlsParam jxlsParam = new JxlsParam(JxlsParam.EXCEL_EXT_XLS, "progrmExcel", "프로그램 목록", "프로그램", totalCnt);

		model.addAttribute("list", progrmManageList);
		model.addAttribute("jxlsParam", jxlsParam);

		return new ModelAndView("jxlsView", model);
	}
}
