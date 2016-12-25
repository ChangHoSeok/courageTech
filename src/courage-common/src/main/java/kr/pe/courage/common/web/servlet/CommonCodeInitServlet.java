
package kr.pe.courage.common.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.pe.courage.common.code.CommonCodeMap;
import kr.pe.courage.common.code.CommonCodeVO;
import kr.pe.courage.common.code.ICommonCodeService;
import kr.pe.courage.common.code.ICommonDetailCodeService;
import kr.pe.courage.common.core.BeanLoader;
import kr.pe.courage.common.utils.Util;

import org.apache.log4j.Logger;

/**
 * 공통코드 초기 설정 서블릿
 * 
 * <pre>
 * kr.pe.courage.common.web.servlet
 * CommonCodeInitServlet.java
 * </pre>
 * 
 * @Author : Chang Ho Seok
 * @Date : 2013. 11. 4.
 * @Version : 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * ================================================================
 * 		수정일		|		수정자			|			수정내용
 * 2013-11-04		석창호					최초등록
 * ================================================================
 * </pre>
 */
public class CommonCodeInitServlet extends HttpServlet {
	
	private static final long serialVersionUID = 991901376199090979L;
	private static Logger logger = Logger.getLogger(CommonCodeInitServlet.class);
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// 프로퍼티 초기화
			initCommonCode();

			// alert 메시지 표시할때 백그라운드 한글깨짐 문제로 삽입
			resp.setHeader("Content-Type", "text/html; charset=UTF-8");

			PrintWriter out = resp.getWriter();
			out.println("<script>alert('공통코드 설정이 초기화되었습니다.');history.back();</script>");
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
	}

	@Override
	public void init() throws ServletException {
		// 공통코드 초기화
		initCommonCode();
	}

	private void initCommonCode() {
		String commonCodeImplClass = Util.isNotBlank(getInitParameter("commonCodeServiceImplId"))? getInitParameter("commonCodeServiceImplId") : "courageCommonCodeService";
		String commonDetailCodeImplClass = Util.isNotBlank(getInitParameter("commonDetailCodeServiceImplId"))? getInitParameter("commonDetailCodeServiceImplId") : "courageCommonDetailCodeService";
		logger.info("########################################");
		logger.info("## 공통코드 설정값을 초기화 합니다.");
		
		try {
			logger.info("## init-param name : commonCodeServiceImplId = " + commonCodeImplClass);
			logger.info("## init-param name : commonDetailCodeServiceImplId = " + commonDetailCodeImplClass);
			
			HashMap<Object, List<CommonCodeVO>> tempMap = null;
			CommonCodeMap commonCodeMap = CommonCodeMap.getInstance();
			BeanLoader beanLoader = new BeanLoader(getServletContext());
			
			ICommonCodeService commonCodeServic = (ICommonCodeService)beanLoader.getBean(commonCodeImplClass);
			ICommonDetailCodeService commonDetailCodeService = (ICommonDetailCodeService)beanLoader.getBean(commonDetailCodeImplClass);
			
			commonCodeMap.setCommonCodeImplClass(commonCodeImplClass);
			commonCodeMap.setCommonDetailCodeImplClass(commonDetailCodeImplClass);
			
			if (commonCodeServic != null && commonDetailCodeService != null) {
				try {
					String codeId = "";
					List<CommonCodeVO> detailCodeList = null;
					CommonCodeVO condCommonCodeVO = new CommonCodeVO();
					
					CommonCodeVO tempCodeVO = new CommonCodeVO();
					tempMap = new HashMap<Object, List<CommonCodeVO>>();
					List<CommonCodeVO> codeIdList = commonCodeServic.selectCommonCodeList(tempCodeVO);
					
					Iterator<CommonCodeVO> codeIdIter = codeIdList.iterator();
					
					while (codeIdIter.hasNext()) {
						CommonCodeVO commonCodeVO = (CommonCodeVO) codeIdIter.next();

						codeId = commonCodeVO.getCodeId();
						condCommonCodeVO.setCodeId(codeId);
						
						detailCodeList = commonDetailCodeService.selectCommonDetailCodeList(condCommonCodeVO);
						tempMap.put(codeId, detailCodeList);
						
						logger.info("## " + commonCodeVO.getCodeIdNm() + "(" + commonCodeVO.getCodeId() + ") - 상세코드 로딩 갯수 : " + detailCodeList.size() + "개 loaded");
					}
					
					commonCodeMap.setCommonCode(tempMap);
				} catch (Exception e) {
					logger.error(e);
				}
			} else {
				logger.warn("## 공통코드 구현체 클래스를 로딩할 수 없습니다.");
			}
		} catch (Exception ex) {
			logger.error(ex);
		}
		
		logger.info("## 공통코드 설정값을 정상적으로 초기화 하였습니다.");
		logger.info("########################################");
	}
}
