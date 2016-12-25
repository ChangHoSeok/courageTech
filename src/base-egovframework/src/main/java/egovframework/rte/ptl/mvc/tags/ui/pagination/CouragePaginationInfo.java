
package egovframework.rte.ptl.mvc.tags.ui.pagination;

import javax.servlet.http.HttpServletRequest;

import kr.pe.courage.common.core.CommonDefaultVO;
import kr.pe.courage.common.utils.Util;

/**
 * 전자정부 프레임워크 페이징 처리 정보처리 간편화 클랙스
 *
 * <pre>
 * egovframework.rte.ptl.mvc.tags.ui.pagination
 * CylPaginationInfo.java
 * </pre>
 *
 * @Author : Chang Ho Seok
 * @Date : 2013. 11. 4.
 * @Version : 1.0
 * @see
 *
 *      <pre>
 * << 개정이력 >>
 * ================================================================
 * 		수정일		|		수정자			|			수정내용
 * 2013-11-04		석창호					최초등록
 * ================================================================
 * </pre>
 */
public class CouragePaginationInfo extends PaginationInfo {

	private CommonDefaultVO commonDefaultVo;
	private String defaultPageEnable;
	private int defaultRecordCountPerPage;
	private int defaultPageSize;

	public CouragePaginationInfo(Object obj) {
		this(obj, "1", 30, 10);
	}

	public CouragePaginationInfo(Object obj, String defaultPageEnable, int defaultRecordCountPerPage, int defaultPageSize) {
		this.defaultPageEnable = defaultPageEnable;
		this.defaultRecordCountPerPage = defaultRecordCountPerPage;
		this.defaultPageSize = defaultPageSize;

		this.commonDefaultVo = (CommonDefaultVO) obj;
		this.setCurrentPageNo(this.commonDefaultVo.getPageIndex());

		if (this.commonDefaultVo.getRecordCountPerPage() <= 0) {
			this.setRecordCountPerPage(this.defaultRecordCountPerPage);
		} else {
			this.setRecordCountPerPage(this.commonDefaultVo.getRecordCountPerPage());
		}

		if (this.commonDefaultVo.getPageSize() <= 0) {
			this.setPageSize(this.defaultPageSize);
		} else {
			this.setPageSize(this.commonDefaultVo.getPageSize());
		}
	}

	public Object createPaginationInfo() {
		return this;
	}

	public Object createCustomVo() {
		this.commonDefaultVo.setFirstIndex(this.getFirstRecordIndex());
		this.commonDefaultVo.setLastIndex(this.getLastRecordIndex());

		if (this.commonDefaultVo.getRecordCountPerPage() <= 0) {
			this.commonDefaultVo.setRecordCountPerPage(this.defaultRecordCountPerPage);
		}

		if (this.commonDefaultVo.getPageSize() <= 0) {
			this.commonDefaultVo.setPageSize(this.defaultPageSize);
		}

		return this.commonDefaultVo;
	}

	public Object createCustomVo(HttpServletRequest request) {
		if (!Util.isNotBlank(request.getParameter("pagingEnable"))) {
			commonDefaultVo.setPagingEnable(this.defaultPageEnable);
		} else {
			commonDefaultVo.setPagingEnable(request.getParameter("pagingEnable"));
		}

		if (!Util.isNotBlank(request.getParameter("recordCountPerPage"))
				|| !Util.isNumber(request.getParameter("recordCountPerPage"))
				|| "0".equals(request.getParameter("recordCountPerPage"))) {
			commonDefaultVo.setRecordCountPerPage(this.defaultRecordCountPerPage);
		} else {
			commonDefaultVo.setRecordCountPerPage(Integer.parseInt(request.getParameter("recordCountPerPage")));
		}

		if (!Util.isNotBlank(request.getParameter("pageSize"))
				|| !Util.isNumber(request.getParameter("pageSize"))
				|| "0".equals(request.getParameter("pageSize"))) {
			commonDefaultVo.setPageSize(this.defaultPageSize);
		} else {
			commonDefaultVo.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
		}

		commonDefaultVo.setFirstIndex(this.getFirstRecordIndex());
		commonDefaultVo.setLastIndex(this.getLastRecordIndex());

		return commonDefaultVo;
	}
}
