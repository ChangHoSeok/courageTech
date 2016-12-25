
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

import egovframework.com.sym.ccm.cca.service.CmmnCodeVO;
import egovframework.com.sym.ccm.cca.web.AbstractCcmCmmnCodeController;

/**
 * <pre>
 * kr.pe.courage.tech.system.code.web
 * CommonCodeController.java
 * </pre>
 * 
 * @Author : Seok Chang Ho
 * @Date : 2013. 11. 6.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * ================================================================
 * 		수정일		|		수정자			|			수정내용
 * 2013-11-06		석창호					최초등록
 * ================================================================
 * </pre>
 */
@Controller("commonCodeController")
@RequestMapping(value = "/system/code/*")
public class CommonCodeController extends AbstractCcmCmmnCodeController {

	@Value("#{systemView['formCommonCodeView']}")
	private String formCommonCodeView;

	@Value("#{systemView['commonCodeListView']}")
	private String commonCodeListView;

	@Value("#{systemView['commonCodeDetailView']}")
	private String commonCodeDetailView;

	@Value("#{systemView['commonCodeCreateView']}")
	private String commonCodeCreateView;

	@Value("#{systemView['commonCodeModifyView']}")
	private String commonCodeModifyView;

	@Value("#{systemView['excelLogView']}")
	private String excelLogView;

	@Value("#{pageConfig['commonCode.page.recordCount']}")
	private int recordCount;

	@Value("#{pageConfig['commonCode.page.size']}")
	private int pageSize;

	@Value("#{pageConfig['commonCode.page.enable']}")
	private String pageEnable;

	protected String getCodeManageListView() {
		return commonCodeListView;
	}

	protected String getCodeManageDetailView() {
		return commonCodeDetailView;
	}

	protected String getCodeManageCreateView() {
		return commonCodeCreateView;
	}

	protected String getCodeManageModifyView() {
		return commonCodeModifyView;
	}

	protected String getExcelLogView() {
		return excelLogView;
	}

	protected CommonDefaultVO getCommonDefaultVO() {
		CommonDefaultVO commonDefaultVO = new CommonDefaultVO(pageEnable, "CODE_ID", "ASC");
		commonDefaultVO.setRecordCountPerPage(recordCount);
		commonDefaultVO.setPageSize(pageSize);

		return commonDefaultVO;
	}

	/**
	 * <pre>
	 * 1. 개요 : 공통코드 기본 폼 호출
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 2. 1.
	 * @Method Name : formCommonCode
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "formCommonCode.*")
	public ModelAndView formCommonCode(ModelMap model) throws Exception {
		return new ModelAndView(formCommonCodeView, model);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : 공통코드 엑셀 다운로드
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 2. 1.
	 * @Method Name : downloadCommonCodeExcel
	 * @param cmmnCodeVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "downloadCommonCodeExcel.*")
	public ModelAndView downloadCommonCodeExcel(@ModelAttribute("cmmnCodeVO") CmmnCodeVO cmmnCodeVO, ModelMap model) throws Exception {
		
		cmmnCodeVO.setPagingEnable(CmmnCodeVO.PAGING_ENABLE_OFF);

		int totalCnt = cmmnCodeManageService.selectCmmnCodeListTotCnt(cmmnCodeVO);
		List<CmmnCodeVO> cmmnCodeList = cmmnCodeManageService.selectCmmnCodeList(cmmnCodeVO);
		JxlsParam jxlsParam = new JxlsParam(JxlsParam.EXCEL_EXT_XLS, "commonCodeExcel", "공통코드 목록", "공통코드", totalCnt);

		model.addAttribute("list", cmmnCodeList);
		model.addAttribute("jxlsParam", jxlsParam);

		return new ModelAndView("jxlsView", model);
	}
}
