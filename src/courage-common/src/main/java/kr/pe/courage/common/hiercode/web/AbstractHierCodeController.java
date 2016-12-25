
package kr.pe.courage.common.hiercode.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.pe.courage.common.core.CommonDefaultVO;
import kr.pe.courage.common.hiercode.HierCodeVO;
import kr.pe.courage.common.hiercode.impl.IHierCodeService;
import kr.pe.courage.common.utils.BeanUtils;
import kr.pe.courage.common.utils.Util;
import kr.pe.courage.common.web.helper.ITreeHelper;
import kr.pe.courage.common.web.utils.WebUtils;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

public abstract class AbstractHierCodeController {
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	@Resource(name = "courageHierCodeService")
	protected IHierCodeService hierCodeService;

	@Resource(name = "defaultTreeHelper")
	private ITreeHelper treeHelper;

	/*
	 * 계층코드 상세정보 조회 view 페이지
	 */
	protected abstract String getHierCodeDetailView();

	/*
	 * 계층코드 하위 목록 조회 view 페이지
	 */
	protected abstract String getHierCodeSubListView();

	/**
	 * <pre>
	 * 1. 개요 : 계층코드 JsonResult 생성
	 * </pre>
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 30.
	 * @Method Name : retrieveHierCodeJsonResult
	 * @param request
	 * @param response
	 * @param hierCodeVO
	 * @param model
	 * @return - json 결과 jsonViewExtension
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveHierCodeJsonResult.*")
	public ModelAndView retrieveHierCodeJsonResult(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("hierCodeVO") HierCodeVO hierCodeVO, ModelMap model) throws Exception {

		String groupCdFlag = "0";
		List<HierCodeVO> hierCodeList = null;
		List<Map<String, Object>> treeMapList = new ArrayList<Map<String, Object>>();

		hierCodeVO.setPagingEnable(CommonDefaultVO.PAGING_ENABLE_OFF);

		if (Util.isNotBlank(hierCodeVO.getGroupId())) {
			if (!Util.isNotBlank(hierCodeVO.getUpperCode())) {
				hierCodeVO.setUpperCode("0"); // 최상위 아이디 조회
			}

			hierCodeList = hierCodeService.selectHierCodeTreeList(hierCodeVO);
		} else {
			groupCdFlag = "1";
			// 그룹아이디가 없을경우 전체 그룹목록 조회
			hierCodeList = hierCodeService.selectHierGroupCodeList(hierCodeVO);
		}

		for (HierCodeVO treeVO : hierCodeList) {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			Map<String, Object> metadataMap = new HashMap<String, Object>();

			metadataMap.put("groupId", treeVO.getGroupId());
			metadataMap.put("code", Util.nvl(treeVO.getCode(), "0"));
			metadataMap.put("groupCdFlag", groupCdFlag);

			dataMap.put("id", treeVO.getGroupId() + Util.nvl(treeVO.getCode(), "0"));
			dataMap.put("title", groupCdFlag.equals("1") ? treeVO.getGroupNm() : treeVO.getCodeNm());
			dataMap.put("state", "closed");
			dataMap.put("metadata", metadataMap);

			treeMapList.add(dataMap);
		}

		model.clear();
		model.addAttribute("jstreeList", treeHelper.process(treeMapList, hierCodeVO.getGroupId() + hierCodeVO.getCode()));

		return new ModelAndView("jsonViewExtension", model);
	}

	/**
	 * <pre>
	 * 1. 개요 : 계층코드 상세정보 조회
	 * </pre>
	 * 
	 * @Author	: Seok Chang Ho
	 * @Date	: 2013. 11. 30.
	 * @Method Name : retrieveHierCodeDetail
	 * @param request
	 * @param response
	 * @param hierCodeVO
	 * @param model
	 * @return - 사용자 정의  View getHierCodeDetailView
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveHierCodeDetail.*")
	public ModelAndView retrieveHierCodeDetail(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("hierCodeVO") HierCodeVO hierCodeVO,
			ModelMap model) throws Exception {
		
		HierCodeVO resultVO = null;
		
		if (hierCodeVO.getGroupCdFlag().equals("1")) {
			resultVO = hierCodeService.selectHierGroupCode(hierCodeVO);
		} else {
			resultVO = hierCodeService.selectHierDetailCode(hierCodeVO);
		}
		
		BeanUtils.copyCondProperties(hierCodeVO, resultVO);
		model.addAttribute("hierCodeVO", resultVO);
		WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_SUCCESS);
		
		return new ModelAndView(getHierCodeDetailView(), model);
	}
}
