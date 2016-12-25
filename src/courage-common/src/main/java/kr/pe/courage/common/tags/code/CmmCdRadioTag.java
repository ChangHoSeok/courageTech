
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
 * 공통코드 radio button 조회 커스텀 테그 클래스
 * 
 * <pre>
 * egovframework.com.sym.ccm.tags
 * CmmCdRadioTag.java
 * </pre>
 * 
 * @Author : Chang Ho Seok
 * @Date : 2013. 11. 4.
 * @Version :
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
public class CmmCdRadioTag extends TagSupport {

	private static final long serialVersionUID = -7697745818226957503L;

	public CmmCdRadioTag() {
		super();
	}

	private String codeId;
	private String operation;
	private boolean use = false;
	private String defaultCode = "";
	private String skipCode;
	private String name = "";
	private String id = "";
	private String style = "";
	private String styleClass = "";
	private boolean disabled = false;
	private String onClick = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
		}
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
		this.skipCode = skipCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public String getOnClick() {
		return onClick;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}

	public int doStartTag() throws JspException {

		try {
			if (Util.nvl(this.id).equals("")) {
				this.id = this.name;
			}
			StringBuffer html = new StringBuffer();
			makeSelectTagBody(html);

			this.pageContext.getOut().println(html.toString());
			this.id = "";
		} catch (IOException ex) {
			throw new JspException(ex);
		}

		return SKIP_BODY;
	}

	private void makeSelectTagBody(StringBuffer html) throws JspException {
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
		
		if (Util.isNotBlank(getOperation())) {
			BeanLoader beanLoader = new BeanLoader(pageContext.getServletContext());
			ICommonDetailCodeService commonDetailCodeService = (ICommonDetailCodeService)beanLoader.getBean(CommonCodeMap.getInstance().getCommonDetailCodeImplClass());
			resultList = commonDetailCodeService.selectOperationCodeList(commonCodeVO);
		} else {
			resultList = CommonCodeMap.getInstance().getCode(commonCodeVO.getCodeId());
		}
		
		return resultList;
	}

	private void makeOptionTag(StringBuffer html, CommonCodeVO cmmnDetailCodeVO) throws JspException {
		html.append("<input type='radio' name='" + this.getName() + "' value='" + cmmnDetailCodeVO.getCode() + "'");
		html.append(" id='" + this.getId() + cmmnDetailCodeVO.getCode() + "'");
		html.append(" style='" + this.getStyle() + "'");
		html.append(" class='" + this.getStyleClass() + "'");

		if (isDisabled()) {
			html.append(" disabled='disabled'");
		}

		if (!getOnClick().equals("")) {
			html.append(" onClick=\"" + getOnClick() + "\"");
		}

		if (getDefaultCode().trim().equals(cmmnDetailCodeVO.getCode().trim())) {
			html.append(" checked='checked'");
		}

		html.append(">");
		html.append("<label for='" + this.getId() + cmmnDetailCodeVO.getCode() + "'>");
		html.append(cmmnDetailCodeVO.getCodeNm());
		html.append("</label>");
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
