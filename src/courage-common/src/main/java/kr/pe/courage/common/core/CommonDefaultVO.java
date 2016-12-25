
package kr.pe.courage.common.core;

import java.io.Serializable;

import kr.pe.courage.common.annotation.KeepCondition;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * 공통VO
 *
 * <pre>
 * kr.pe.courage.common.core
 * CommonDefaultVO.java
 * </pre>
 *
 * @Author : Seok Chang Ho
 * @Date : 2013. 11. 1.
 * @Version : 1.0
 * @see
 *
 *      <pre>
 * << 개정이력 >>
 * ================================================================
 * 		수정일		|		수정자			|			수정내용
 * 2013-11-01		석창호					최초등록
 * ================================================================
 * </pre>
 */
public class CommonDefaultVO implements Serializable {

	private static final long serialVersionUID = -6062858939907510631L;

	public static final String PAGING_ENABLE_ON = "1";
	public static final String PAGING_ENABLE_OFF = "0";

	public static final String MODE_MODIFY = "modify";
	public static final String MODE_CREATE = "create";
	
	public static final String SAVE_LATER_VIEW_DETAIL = "detail";
	public static final String SAVE_LATER_VIEW_CREATE = "create";
	public static final String SAVE_LATER_VIEW_LIST = "list";

	public CommonDefaultVO() {
		super();
	}

	/**
	 * <pre>
	 * 1. 처리내용 : 목록 기본정보 설정
	 * </pre>
	 *
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 9.
	 * @param pagingEnable
	 *            페이지 사용여부 [0:사용안함, 1:사용]
	 * @param condOrder
	 *            기본정렬 필드명
	 * @param condAlign
	 *            기본정렬 조건 [ASC, DESC]
	 */
	public CommonDefaultVO(String pagingEnable, String condOrder, String condAlign) {
		this.pagingEnable = pagingEnable;
		this.condOrder = condOrder;
		this.condAlign = condAlign;
	}

	/** 현재페이지 */
	@KeepCondition
	@JsonIgnore
	private int pageIndex = 1;

	/** 페이지갯수 */
	@KeepCondition
	@JsonIgnore
	private int pageUnit;

	/** 페이지사이즈 */
	@KeepCondition
	@JsonIgnore
	private int pageSize;

	/** firstIndex */
	@KeepCondition
	@JsonIgnore
	private int firstIndex = 1;

	/** lastIndex */
	@KeepCondition
	@JsonIgnore
	private int lastIndex = 1;

	/** recordCountPerPage */
	@KeepCondition
	@JsonIgnore
	private int recordCountPerPage;

	/** pagination Flag */
	@KeepCondition
	@JsonIgnore
	private String pagingEnable; // 페이지 처리[0 : 페이징 않함, 1 : 페이징 처리]

	/** 정렬필드 */
	@KeepCondition
	@JsonIgnore
	private String condOrder = "";

	/** 정렬조건 */
	@KeepCondition
	@JsonIgnore
	private String condAlign = "";

	@KeepCondition
	@JsonIgnore
	private String menuNo;

	@KeepCondition
	@JsonIgnore
	private String upperMenuNo;

	/** session id */
	@JsonIgnore
	private String jsessionId;

	@JsonIgnore
	private String mode;

	@JsonIgnore
	private String customView;
	
	@JsonIgnore
	private String saveLaterView;

	public String getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}

	public String getUpperMenuNo() {
		return upperMenuNo;
	}

	public void setUpperMenuNo(String upperMenuNo) {
		this.upperMenuNo = upperMenuNo;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getPagingEnable() {
		return pagingEnable;
	}

	public void setPagingEnable(String pagingEnable) {
		this.pagingEnable = pagingEnable;
	}

	public String getCondOrder() {
		return condOrder;
	}

	public void setCondOrder(String condOrder) {
		this.condOrder = condOrder;
	}

	public String getCondAlign() {
		return condAlign;
	}

	public void setCondAlign(String condAlign) {
		this.condAlign = condAlign;
	}

	public String getJsessionId() {
		return jsessionId;
	}

	public void setJsessionId(String jsessionId) {
		this.jsessionId = jsessionId;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getCustomView() {
		return customView;
	}

	public void setCustomView(String customView) {
		this.customView = customView;
	}

	public String getSaveLaterView() {
		return saveLaterView;
	}

	public void setSaveLaterView(String saveLaterView) {
		this.saveLaterView = saveLaterView;
	}
}
