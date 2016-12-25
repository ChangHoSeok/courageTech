package kr.pe.courage.tech.system.code.web;

import java.util.List;

import kr.pe.courage.common.core.CommonDefaultVO;
import kr.pe.courage.common.vo.JxlsParam;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.sym.ccm.cde.service.CmmnDetailCodeVO;
import egovframework.com.sym.ccm.cde.service.web.AbstractCcmCmmnDetailCodeController;

@Controller("commonDetailCodeController")
@RequestMapping(value = "/system/code/*")
public class CommonDetailCodeController extends AbstractCcmCmmnDetailCodeController {
	
	@Value("#{systemView['formCommonDetailCodePopupView']}")
	private String formCommonDetailCodePopupView;
	
	@Value("#{systemView['commonDetailCodeListView']}")
	private String commonDetailCodeListView;

	@Value("#{systemView['commonDetailCodeDetailView']}")
	private String commonDetailCodeDetailView;

	@Value("#{systemView['commonDetailCodeCreateView']}")
	private String commonDetailCodeCreateView;

	@Value("#{systemView['commonDetailCodeModifyView']}")
	private String commonDetailCodeModifyView;

	@Value("#{systemView['commonDetailCodeListPopupView']}")
	private String commonDetailCodeListPopupView;

	@Value("#{pageConfig['commonDetailCode.page.enable']}")
	private int recordCount;

	@Value("#{pageConfig['commonDetailCode.page.size']}")
	private int pageSize;

	@Value("#{pageConfig['commonDetailCode.page.recordCount']}")
	private String pageEnable;
	
	@Value("#{pageConfig['commonDetailCodePopup.page.enable']}")
	private int popupRecordCount;

	@Value("#{pageConfig['commonDetailCodePopup.page.size']}")
	private int popupPageSize;

	@Value("#{pageConfig['commonDetailCodePopup.page.recordCount']}")
	private String popupPageEnable;

	protected String getDetailCodeManageListView() {
		return commonDetailCodeListView;
	}

	protected String getDetailCodeManageDetailView() {
		return commonDetailCodeDetailView;
	}

	protected String getDetailCodeManageCreateView() {
		return commonDetailCodeCreateView;
	}

	protected String getDetailCodeManageModifyView() {
		return commonDetailCodeModifyView;
	}

	@Override
	protected String getDetailCodeManageListPopupView() {
		return commonDetailCodeListPopupView;
	}

	protected CommonDefaultVO getCommonDefaultVO() {
		CommonDefaultVO commonDefaultVO = new CommonDefaultVO(pageEnable, "ORDR", "ASC");
		commonDefaultVO.setPageSize(pageSize);
		commonDefaultVO.setRecordCountPerPage(recordCount);

		return commonDefaultVO;
	}

	@Override
	protected CommonDefaultVO getCommonDefaultPopupVO() {
		CommonDefaultVO commonDefaultVO = new CommonDefaultVO(popupPageEnable, "ORDR", "ASC");
		commonDefaultVO.setPageSize(popupPageSize);
		commonDefaultVO.setRecordCountPerPage(popupRecordCount);

		return commonDefaultVO;
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 공통상세코드 조회 팝업
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 2. 12.
	 * @Method Name : formCommonDetailCodePopup
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "formCommonDetailCodePopup.*")
	public ModelAndView formCommonDetailCodePopup(ModelMap model) throws Exception {
		return new ModelAndView(formCommonDetailCodePopupView, model);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 공통코드 상세 엑셀다운로드
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2014. 12. 10.
	 * @Method Name : downloadCommonDetailCodeExcel
	 * @param request
	 * @param response
	 * @param cmmnDetailCodeVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "downloadCommonDetailCodeExcel.*")
	public ModelAndView downloadCommonDetailCodeExcel(@ModelAttribute("cmmnDetailCodeVO") CmmnDetailCodeVO cmmnDetailCodeVO, ModelMap model) throws Exception {

		cmmnDetailCodeVO.setPagingEnable(CmmnDetailCodeVO.PAGING_ENABLE_OFF);

		int totalCnt = cmmnDetailCodeManageService.selectCmmnDetailCodeListTotCnt(cmmnDetailCodeVO);
		
		List<CmmnDetailCodeVO> cmmnDetailCodeList = cmmnDetailCodeManageService.selectCmmnDetailCodeList(cmmnDetailCodeVO);
		
		JxlsParam jxlsParam = new JxlsParam(JxlsParam.EXCEL_EXT_XLS, "commonDetailCodeExcel", "공통 상세코드 목록", "공통 상세코드", totalCnt);

		model.addAttribute("list", cmmnDetailCodeList);
		model.addAttribute("jxlsParam", jxlsParam);

		return new ModelAndView("jxlsView", model);
	}
}
