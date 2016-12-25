
package egovframework.com.cmm.interceptor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.pe.courage.common.utils.Util;
import kr.pe.courage.common.web.utils.WebUtils;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.com.sym.prm.service.EgovProgrmManageService;
import egovframework.com.sym.prm.service.ProgrmManageVO;

/**
 * <pre>
 * egovframework.com.cmm.interceptor
 * CourageRequestCheckInterceptor.java
 * </pre>
 * 
 * @Author : ChangHo Seok
 * @Date : 2016. 7. 1.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 7. 1., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class CourageRequestCheckInterceptor extends HandlerInterceptorAdapter {
	public static final String HTTP_REQUEST = "1";
	public static final String XML_HTTP_REQUEST = "2";

	@Resource(name = "ProgrmManageService")
	private EgovProgrmManageService progrmManageService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		boolean resultFlag = false;
		String requestURL = WebUtils.getUrlNotExtension(request) + ".";
		String requestMethod = request.getMethod();
		String requestSe = WebUtils.isAjaxRequest(request) ? XML_HTTP_REQUEST : HTTP_REQUEST;
		
		ProgrmManageVO condVO = new ProgrmManageVO();
		condVO.setUrl(requestURL);
		
		List<ProgrmManageVO> progrmList = progrmManageService.selectProgrmRequestCheckList(condVO);
		
		if (progrmList == null || progrmList.size() <= 0) {
			resultFlag = true;
		} else {
			for (ProgrmManageVO progrmManageVO : progrmList) {
				if (("".equals(Util.nvl(progrmManageVO.getRequestMethodList())) || progrmManageVO.getRequestMethodList().toUpperCase().contains(requestMethod.toUpperCase()))
						&& ("".equals(Util.nvl(progrmManageVO.getRequestSe())) || progrmManageVO.getRequestSe().equals(requestSe))) {
					resultFlag = true;
					break;
				}
			}
		}
		
		
		if (!resultFlag) {
			if (WebUtils.isAjaxRequest(request)) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			} else {
				response.sendRedirect(request.getContextPath() + "/error/errorInvalidAccess.tech");
			}
		}
		
		return resultFlag;
	}
}
