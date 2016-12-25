
package kr.pe.courage.tech.system.push.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.pe.courage.common.push.IMessagePushService;
import kr.pe.courage.common.push.MessagePushVO;
import kr.pe.courage.common.vo.JxlsParam;
import kr.pe.courage.common.web.utils.WebUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.CouragePaginationInfo;

/**
 * <pre>
 * kr.pe.courage.tech.system.push.web
 * MessagePushController.java
 * </pre>
 * 
 * @Author : ChangHo Seok
 * @Date : 2016. 5. 17.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 5. 17., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
@Controller("messagePushContorller")
@RequestMapping(value = "/system/push/*")
public class MessagePushController {
	@Resource(name = "couragePushMssageIdGnrService")
	private EgovIdGnrService idGenService;

	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	@Resource(name = "messagePushService")
	private IMessagePushService messagePushService;

	@Value("#{systemView['formMessagePushView']}")
	private String formMessagePushView;

	@Value("#{systemView['messagePushListView']}")
	private String messagePushListView;
	
	@Value("#{systemView['formMessagePushPopupView']}")
	private String formMessagePushPopupView;
	
	@Value("#{systemView['messagePushCreateView']}")
	private String messagePushCreateView;

	@Value("#{pageConfig['messagePush.page.recordCount']}")
	private int recordCount;

	@Value("#{pageConfig['messagePush.page.size']}")
	private int pageSize;

	@Value("#{pageConfig['messagePush.page.enable']}")
	private String pageEnable;

	/**
	 * <pre>
	 * 1. 개요 : Push 메시지 메인 form 조회
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 5. 17.
	 * @Method Name : formMessagePush
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "formMessagePush.*")
	public ModelAndView formMessagePush() throws Exception {
		return new ModelAndView(formMessagePushView);
	}

	/**
	 * <pre>
	 * 1. 개요 : Push 메시지 목록 조회
	 * </pre>
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 5. 17.
	 * @Method Name : retrieveMessagePushList
	 * @param request
	 * @param messagePushVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "retrieveMessagePushList.*")
	public ModelAndView retrieveMessagePushList(HttpServletRequest request, MessagePushVO messagePushVO, ModelMap model) throws Exception {
		CouragePaginationInfo pagination = new CouragePaginationInfo(messagePushVO, pageEnable, recordCount, pageSize);
		messagePushVO = (MessagePushVO) pagination.createCustomVo();
		
		pagination.setTotalRecordCount(messagePushService.selectMessagePushListCount(messagePushVO));
		List<MessagePushVO> messagePushList = null;
		
		if (pagination.getTotalRecordCount() > 0) {
			messagePushList = messagePushService.selectMessagePushList(messagePushVO);
		}
		
		model.addAttribute("pagination", pagination);
		model.addAttribute("messagePushList", messagePushList);
		
		return new ModelAndView(messagePushListView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : Push 메시지 메인 팝압 form
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 5. 17.
	 * @Method Name : formMessagePushPopup
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "formMessagePushPopup.*")
	public ModelAndView formMessagePushPopup() throws Exception {
		return new ModelAndView(formMessagePushPopupView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : Push 메시지 등록 화면
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 5. 17.
	 * @Method Name : createMessagePush
	 * @param messagePushVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createMessagePush.*")
	public ModelAndView createMessagePush(MessagePushVO messagePushVO) throws Exception {
		messagePushVO.setMode(MessagePushVO.MODE_CREATE);
		
		return new ModelAndView(messagePushCreateView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : Push 메시지 등록 처리
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 5. 18.
	 * @Method Name : createMessagePushProc
	 * @param request
	 * @param messagePushVO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "createMessagePushProc.*")
	public ModelAndView createMessagePushProc(HttpServletRequest request, MessagePushVO messagePushVO, BindingResult bindingResult, ModelMap model) throws Exception {
		beanValidator.validate(messagePushVO, bindingResult);
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("validateCheck", "error");
			WebUtils.setActionStatus(request, WebUtils.ACTION_STATUS_VALIDATOR_ERROR);
			
			return new ModelAndView(messagePushCreateView, model);
		}
		
		messagePushVO.setMssageId(idGenService.getNextStringId());
		messagePushVO.setFrstRegisterId(WebUtils.getUserUniqID(request));
		messagePushVO.setMssageSndngAt("N");
		messagePushService.insertMessagePush(messagePushVO);
		
		model.addAttribute(WebUtils.ACTION_STATUS_PARAM, WebUtils.ACTION_STATUS_SUCCESS);
		messagePushVO.setMssageCn("");
		
		return new ModelAndView(messagePushCreateView);
	}
	
	/**
	 * <pre>
	 * 1. 개요 : Push 메시지 엑셀 다운로드
	 * </pre>
	 * 
	 * @Author	: ChangHo Seok
	 * @Date	: 2016. 5. 20.
	 * @Method Name : downloadMessagePushExcel
	 * @param messagePushVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "downloadMessagePushExcel.*")
	public ModelAndView downloadMessagePushExcel(MessagePushVO messagePushVO, ModelMap model) throws Exception {
		messagePushVO.setPagingEnable(MessagePushVO.PAGING_ENABLE_OFF);
		
		int totalRecordCount = messagePushService.selectMessagePushListCount(messagePushVO);
		List<MessagePushVO> messagePushList = null;

		if (totalRecordCount > 0) {
			messagePushList = messagePushService.selectMessagePushList(messagePushVO);
		}

		JxlsParam jxlsParam = new JxlsParam(JxlsParam.EXCEL_EXT_XLS, "messagePushExcel", "Push 메시지 목록", "Push 메시지 목록", totalRecordCount);
		
		model.addAttribute("list", messagePushList);
		model.addAttribute("jxlsParam", jxlsParam);
		
		return new ModelAndView("jxlsView");
	}
}
