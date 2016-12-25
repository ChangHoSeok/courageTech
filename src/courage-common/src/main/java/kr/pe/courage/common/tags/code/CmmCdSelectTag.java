
package kr.pe.courage.common.tags.code;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import kr.pe.courage.common.code.CommonCodeMap;
import kr.pe.courage.common.code.CommonCodeVO;
import kr.pe.courage.common.code.ICommonDetailCodeService;
import kr.pe.courage.common.core.BeanLoader;
import kr.pe.courage.common.utils.Util;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

/**
 * 공통코드 select box 조회 커스텀 테그 클래스
 * 
 * <pre>
 * egovframework.com.sym.ccm.tags
 * CmmCdSelectTag.java
 * </pre>
 * 
 * @Author : Chang Ho Seok
 * @Date : 2013. 11. 3.
 * @Version : 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * ================================================================
 * 		수정일		|		수정자			|			수정내용
 * 2013-11-03		석창호					최초등록
 * ================================================================
 * </pre>
 */
public class CmmCdSelectTag extends TagSupport {

	private static final long serialVersionUID = 1322357477310355244L;
	
	public CmmCdSelectTag() {
		super();
	}
	
	private String codeId;
	private String operation;
	private boolean use = false;
	private boolean showAll = false;
	private boolean showSelect = false;
	private String defaultCode = "";
	private String skipCode;
	private String name = "";
	private String id = "";
	private String style = "";
	private String styleClass = "";
	private String size = "1";
	private boolean multiple = false;
	private boolean disabled = false;
	private String onChange = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		try {
			this.id = (String) ExpressionEvaluatorManager.evaluate("id", id, String.class, this, pageContext);
		} catch (JspException e) {
			e.printStackTrace();
		}
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		try {
			this.codeId = (String) ExpressionEvaluatorManager.evaluate("codeId", codeId, String.class, this, pageContext);
		} catch (JspException e) {
			e.printStackTrace();
		};
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public boolean isUse() {
		return use;
	}

	public void setUse(boolean use) {
		this.use = use;
	}

	public boolean isShowAll() {
		return showAll;
	}

	public void setShowAll(boolean showAll) {
		this.showAll = showAll;
	}

	public boolean isShowSelect() {
		return showSelect;
	}

	public void setShowSelect(boolean showSelect) {
		this.showSelect = showSelect;
	}

	public String getDefaultCode() {
		return defaultCode;
	}

	public void setDefaultCode(String defaultCode) {
		try {
			this.defaultCode = (String) ExpressionEvaluatorManager.evaluate("defaultCode", defaultCode, String.class, this, pageContext);
		} catch (JspException e) {
			e.printStackTrace();
		}
	}

	public String getSkipCode() {
		return skipCode;
	}

	public void setSkipCode(String skipCode) {
		try {
			this.skipCode = (String) ExpressionEvaluatorManager.evaluate("skipCode", skipCode, String.class, this, pageContext);
		} catch (JspException e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public boolean isMultiple() {
		return multiple;
	}

	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public String getOnChange() {
		return onChange;
	}

	public void setOnChange(String onChange) {
		this.onChange = onChange;
	}

	public int doStartTag() throws JspException {
		try {
			if (Util.nvl(this.id).equals("")) {
				this.id = this.name;
			}

			StringBuffer html = new StringBuffer();
			makeSelectTagHead(html);
			makeSelectTagBody(html);
			makeSelectTagTail(html);

			this.pageContext.getOut().println(html.toString());
			this.id = "";
		} catch (IOException ex) {
			throw new JspException(ex);
		}

		return SKIP_BODY;
	}

	private void makeSelectTagBody(StringBuffer html) throws JspException {
		if (isShowAll()) {
			html.append("<option value=''>전체</option>");
		}

		if (isShowSelect()) {
			html.append("<option value=''>선택</option>");
		}

		List<CommonCodeVO> cmmnDetailCodeList = null;
		try {
			cmmnDetailCodeList = findComCdList();
		} catch (Exception e) {
			throw new JspException(e.getMessage());
		}
		
		if (cmmnDetailCodeList != null) {
			for (int i = 0; i < cmmnDetailCodeList.size(); i++) {
				CommonCodeVO commonCodeVO = (CommonCodeVO) cmmnDetailCodeList.get(i);
				
				if (isSkipCode(commonCodeVO.getCode())) {
					continue;
				}
				
				if (isUse() && commonCodeVO.getUseAt().toUpperCase().equals("N")) {
					continue;
				}
				
				makeOptionTag(html, commonCodeVO);
			}
		}
	}

	private List<CommonCodeVO> findComCdList() throws Exception {
		List<CommonCodeVO> resultList = null;
		CommonCodeVO commonCodeVO = new CommonCodeVO();
		commonCodeVO.setCodeId(this.getCodeId());
		commonCodeVO.setOperation(getOperation());
		
		// 계층형 코드 리스트 
		if(getOperation() != null && getOperation().indexOf(",") > -1){
			String[] obj = getOperation().split(",");
			commonCodeVO.setOperation(obj[0]);
			commonCodeVO.setGroupId(obj[1]);
			commonCodeVO.setUpperCode(obj[2]);
		}
		
		if (Util.isNotBlank(getOperation())) {
			BeanLoader beanLoader = new BeanLoader(pageContext.getServletContext());
			ICommonDetailCodeService commonDetailCodeService = (ICommonDetailCodeService)beanLoader.getBean(CommonCodeMap.getInstance().getCommonDetailCodeImplClass());
			resultList = commonDetailCodeService.selectOperationCodeList(commonCodeVO);
		} else {
			resultList = CommonCodeMap.getInstance().getCode(commonCodeVO.getCodeId());
		}
		
		return resultList;
	}

	private void makeOptionTag(StringBuffer html, CommonCodeVO commonCodeVO) throws JspException {
		html.append("<option value='" + commonCodeVO.getCode() + "'");
		
		if (getDefaultCode().trim().equals(commonCodeVO.getCode().trim())) {
			html.append(" selected");
		}
		
		html.append(">" + commonCodeVO.getCodeNm() + "</option>");
	}

	private void makeSelectTagHead(StringBuffer html) {
		html.append("<select name='" + this.getName() + "'");
		html.append(" id='" + this.getId() + "'");
		html.append(" style='" + this.getStyle() + "'");
		html.append(" class='" + this.getStyleClass() + "'");

		if (!getSize().equals("")) {
			try {
				Integer.parseInt(getSize());
			} catch (NumberFormatException ex) {
				setSize("1");
			}

			html.append(" size=" + Integer.parseInt(getSize()) + "");
		}

		if (isMultiple()) {
			html.append(" multiple");
		}

		if (isDisabled()) {
			html.append(" disabled");
		}

		if (!getOnChange().equals("")) {
			html.append(" onChange=\"" + getOnChange() + "\"");
		}

		html.append(">");
	}

	private void makeSelectTagTail(StringBuffer html) {
		html.append("</select>");
	}

	private boolean isSkipCode(String code) {
		if (getSkipCode() == null) {
			return false;
		}

		String[] skipCodes = getSkipCode().split(",");
		for (int loop = 0; loop < skipCodes.length; loop++) {
			if (code.equals(skipCodes[loop])) {
				return true;
			}
		}
		return false;
	}
}
