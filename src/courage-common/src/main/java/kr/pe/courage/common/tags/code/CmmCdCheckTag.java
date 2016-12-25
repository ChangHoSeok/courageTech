
package kr.pe.courage.common.tags.code;

import java.io.IOException;
import java.util.LinkedList;
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
 * 공통코드 check box 조회 클래스
 * 
 * <pre>
 * kr.pe.courage.common.tags.code
 * CmmCdCheckTag.java
 * </pre>
 * 
 * @Author : Seok Chang Ho
 * @Date : 2013. 11. 7.
 * @Version :
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * ================================================================
 * 		수정일		|		수정자			|			수정내용
 * 2013-11-07		석창호					최초등록
 * ================================================================
 * </pre>
 */
public class CmmCdCheckTag extends TagSupport {

	private static final long serialVersionUID = 7679008491561406746L;

	public CmmCdCheckTag() {
		super();
	}

	private String codeId;
	private String operation;
	private boolean use = false;
	private String defaultChecked;
	private String skipCode;
	private String name = "";
	private String id = "";
	private int columnCnt = 4;
	private boolean disabled = false;
	private String onClick = "";
	private String styleClass = "";

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public int getColumnCnt() {
		return columnCnt;
	}

	public void setColumnCnt(int columnCnt) {
		this.columnCnt = columnCnt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		try {
			this.name = (String) ExpressionEvaluatorManager.evaluate("name", name, String.class, this, pageContext);
		} catch (JspException e) {
			e.printStackTrace();
		};
	}

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

	public String getDefaultChecked() {
		return defaultChecked;
	}

	public void setDefaultChecked(String defaultChecked) {
		try {
			this.defaultChecked = (String) ExpressionEvaluatorManager.evaluate("defaultChecked", defaultChecked, String.class, this,
					pageContext);
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

	public int doStartTag() throws JspException {
		try {
			if (Util.nvl(this.id).equals("")) {
				this.id = this.name;
			}

			StringBuffer html = new StringBuffer();
			makeCheckTagHead(html);
			makeCheckTagBody(html);
			makeCheckTagTail(html);

			this.pageContext.getOut().println(html.toString());
			this.id = "";
		} catch (IOException ex) {
			throw new JspException(ex);
		}

		return SKIP_BODY;
	}

	private void makeCheckTagBody(StringBuffer html) throws JspException {
		int index = 0;
		List<CommonCodeVO> cmmnDetailCodeList = null;

		try {
			cmmnDetailCodeList = findComCdList();
		} catch (Exception e) {
			throw new JspException(e.getMessage());
		}
		
		if (cmmnDetailCodeList != null) {
			html.append("<tr>");
			for (int i = 0; i < cmmnDetailCodeList.size(); i++) {
				CommonCodeVO commonCodeVO = (CommonCodeVO) cmmnDetailCodeList.get(i);

				if (isSkipCode(commonCodeVO.getCode())) {
					continue;
				}

				if (isUse() && commonCodeVO.getUseAt().toUpperCase().equals("N")) {
					continue;
				}

				if (index > 0 && index % getColumnCnt() == 0) {
					html.append("</tr><tr>");
				}

				makeInputTag(html, commonCodeVO);
				++index;
			}
			html.append("</tr>");
		}
	}

	private List<CommonCodeVO> findComCdList() throws Exception {
		List<CommonCodeVO> resultList = null;
		CommonCodeVO commonCodeVO = new CommonCodeVO();
		commonCodeVO.setCodeId(this.getCodeId());
		commonCodeVO.setOperation(getOperation());

		if (Util.isNotBlank(getOperation())) {
			BeanLoader beanLoader = new BeanLoader(pageContext.getServletContext());
			ICommonDetailCodeService commonDetailCodeService = (ICommonDetailCodeService) beanLoader.getBean(CommonCodeMap.getInstance()
					.getCommonDetailCodeImplClass());
			resultList = commonDetailCodeService.selectOperationCodeList(commonCodeVO);
		} else {
			resultList = CommonCodeMap.getInstance().getCode(commonCodeVO.getCodeId());
		}

		return resultList;
	}

	private void makeInputTag(StringBuffer html, CommonCodeVO cmmnDetailCodeVO) throws JspException {
		html.append("<td><input type=\"checkbox\" name=\"" + getName() + "\" id=\"" + getId() + cmmnDetailCodeVO.getCode() + "\" value=\"" + cmmnDetailCodeVO.getCode()
				+ "\" ");

		String[] defaultCheckedValue = commaDelimitedListToStringArray(getDefaultChecked());
		for (int i = 0; i < defaultCheckedValue.length; i++) {
			if (defaultCheckedValue[i].trim().equals(cmmnDetailCodeVO.getCode().trim())) {
				html.append(" checked=\"checked\"");
				break;
			}
		}

		if (isDisabled()) {
			html.append(" disabled=\"disabled\"");
		}

		if (!getOnClick().equals("")) {
			html.append(" onclick=\"" + getOnClick() + "\"");
		}

		html.append("><label for=\"" + getId() + cmmnDetailCodeVO.getCode() + "\">" + cmmnDetailCodeVO.getCodeNm() + "</label></td>");
	}

	private void makeCheckTagHead(StringBuffer html) {
		html.append("<table width=\"100%\"");

		if (!getStyleClass().trim().equals("")) {
			html.append(" class=\"" + getStyleClass() + "\" ");
		}

		html.append(" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
	}

	private void makeCheckTagTail(StringBuffer html) {
		html.append("</table>");
	}

	private boolean isSkipCode(String code) {
		if (getSkipCode() == null) {
			return false;
		}

		String[] skipCodes = commaDelimitedListToStringArray(getSkipCode());
		for (int loop = 0; loop < skipCodes.length; loop++) {
			if (code.equals(skipCodes[loop])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <pre>
	 * 1. 媛쒖슂 : CSV 由ъ뒪�몃� �ㅽ듃留�諛곗뿴濡�蹂�솚�쒕떎.
	 * </pre>
	 * 
	 * @Author : chseok82
	 * @Date : 2012. 8. 15.
	 * @Method Name : commaDelimitedListToStringArray
	 * @param str
	 * @return
	 */
	public static String[] commaDelimitedListToStringArray(String str) {
		return delimitedListToStringArray(str, ",");
	}

	/**
	 * <pre>
	 * 1. 媛쒖슂 : �뱀젙 臾몄옄濡�援щ텇��臾몄옄�댁쓣 痍⑦빐���ㅽ듃留�諛곗뿴濡�蹂�솚�쒕떎.
	 * </pre>
	 * 
	 * @Author : chseok82
	 * @Date : 2012. 8. 15.
	 * @Method Name : delimitedListToStringArray
	 * @param string
	 * @param delimiter
	 * @return
	 */
	public static String[] delimitedListToStringArray(String string, String delimiter) {
		if (string == null) {
			return new String[0];
		}

		if (delimiter == null) {
			return new String[] { string };
		}

		List<String> list = new LinkedList();
		int pos = 0;
		int delpos = 0;
		while ((delpos = string.indexOf(delimiter, pos)) != -1) {
			list.add(string.substring(pos, delpos));
			pos = delpos + delimiter.length();
		}

		if (pos <= string.length()) {
			list.add(string.substring(pos));
		}

		return (String[]) list.toArray(new String[0]);
	}
}
