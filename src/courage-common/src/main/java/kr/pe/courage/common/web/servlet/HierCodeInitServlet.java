
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

import kr.pe.courage.common.hiercode.HierCodeMap;
import kr.pe.courage.common.hiercode.HierCodeVO;
import kr.pe.courage.common.hiercode.impl.IHierCodeService;
import kr.pe.courage.common.hiercode.impl.IHierDetailCodeService;
import kr.pe.courage.common.core.BeanLoader;
import kr.pe.courage.common.utils.Util;

import org.apache.log4j.Logger;

public class HierCodeInitServlet extends HttpServlet {
	
	private static final long serialVersionUID = 991901376199090979L;
	private static Logger logger = Logger.getLogger(HierCodeInitServlet.class);
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			initHierCode();

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
		// 계층 코드 초기화
		initHierCode();
	}

	private void initHierCode() {
		String hierCodeImplClass = Util.isNotBlank(getInitParameter("hierCodeServiceImplId"))? getInitParameter("hierCodeServiceImplId") : "cylHierCodeService";
		String hierDetailCodeImplClass = Util.isNotBlank(getInitParameter("hierDetailCodeServiceImplId"))? getInitParameter("hierDetailCodeServiceImplId") : "cylHierDetailCodeService";
		logger.info("########################################");
		logger.info("## 계층 코드 설정값을 초기화 합니다.");
		
		try {
			logger.info("## init-param name : herCodeServiceImplId = " + hierCodeImplClass);
			logger.info("## init-param name : hierDetailCodeServiceImplId = " + hierDetailCodeImplClass);
			
			HashMap<Object, List<HierCodeVO>> tempMap = null;
			HierCodeMap hierCodeMap = HierCodeMap.getInstance();
			BeanLoader beanLoader = new BeanLoader(getServletContext());
			
			IHierCodeService hierCodeService = (IHierCodeService)beanLoader.getBean(hierCodeImplClass);
			IHierDetailCodeService hierDetailCodeService = (IHierDetailCodeService)beanLoader.getBean(hierDetailCodeImplClass);
			
			hierCodeMap.setHierCodeImplClass(hierCodeImplClass);
			hierCodeMap.setHierDetailCodeImplClass(hierDetailCodeImplClass);
			
			if (hierCodeService != null && hierDetailCodeService != null) {
				try {
					String groupId = "";
					List<HierCodeVO> detailCodeList = null;
					HierCodeVO condHierCodeVO = new HierCodeVO();
					
					HierCodeVO tempCodeVO = new HierCodeVO();
					tempMap = new HashMap<Object, List<HierCodeVO>>();
					List<HierCodeVO> codeIdList = hierCodeService.selectHierGroupIdList(tempCodeVO);
					
					Iterator<HierCodeVO> codeIdIter = codeIdList.iterator();
					
					while (codeIdIter.hasNext()) {
						HierCodeVO HierCodeVO = (HierCodeVO) codeIdIter.next();

						groupId = HierCodeVO.getGroupId();
						condHierCodeVO.setGroupId(groupId);
						
						detailCodeList = hierDetailCodeService.selectHiercodeDetailList(condHierCodeVO);
						tempMap.put(groupId, detailCodeList);
						
						logger.info("## " + HierCodeVO.getCodeNm() + "(" + HierCodeVO.getCode() + ") - 상세코드 로딩 갯수 : " + detailCodeList.size() + "개 loaded");
					}
					
					hierCodeMap.setHierCode(tempMap);
				} catch (Exception e) {
					logger.error(e);
				}
			} else {
				logger.warn("## 계층 코드 구현체 클래스를 로딩할 수 없습니다.");
			}
		} catch (Exception ex) {
			logger.error(ex);
		}
		
		logger.info("## 계층 코드 설정값을 정상적으로 초기화 하였습니다.");
		logger.info("########################################");
	}
}
