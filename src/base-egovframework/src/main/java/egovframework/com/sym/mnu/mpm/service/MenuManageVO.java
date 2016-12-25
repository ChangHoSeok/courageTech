
package egovframework.com.sym.mnu.mpm.service;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import kr.pe.courage.common.annotation.KeepCondition;
import kr.pe.courage.common.core.CommonDefaultVO;

/**
 * 메뉴목록관리 처리를 위한 VO 클래스르를 정의한다
 * 
 * @author 개발환경 개발팀 이용
 * @since 2009.06.01
 * @version 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이  용          최초 생성
 * 
 * </pre>
 */

public class MenuManageVO extends CommonDefaultVO implements Serializable {

	private static final long serialVersionUID = -5051036066010226041L;

	/** 메뉴아이디 */
	private String menuId;
	/** 메뉴순서 */
	private String menuOrdr;
	/** 메뉴명 */
	private String menuNm;
	/** 상위메뉴번호 */
	private String upperMenuId;
	/** 상위메뉴명 */
	private String upperMenuNm;
	/** 메뉴설명 */
	private String menuDc;
	/** 메뉴레벨 */
	private String menuLevel;
	/** 관련이미지경로 */
	private String relateImgPath;
	/** 관련이미지명 */
	private String relateImgNm;
	/** 프로그램파일명 */
	private String progrmFileNm;
	/** 프로그램기능구분 */
	private String progrmFnctSe;
	/** URL */
	private String url;
	/** 메뉴표시여부 */
	private String menuIndictAt;
	/** 하위메뉴 개수 */
	private String childMenuCnt;
	/** 하위메뉴 목록 */
	private List<MenuManageVO> subMenuList;
	/** 권한별 초기 URL */
	private String authorIndexUrl;
	/** 매뉴연결 시 전달 매개변수 */
	private String intrmdVriabl;

	private String searchFlag;
	private String createFlag;
	private String customFlag1;
	private String customFlag2;
	private String customFlag3;
	private String[] authorMenuArray;
	private String[] searchFlagArray;
	private String[] createFlagArray;
	private String[] customFlag1Array;
	private String[] customFlag2Array;
	private String[] customFlag3Array;

	private String[] menuProgrmList; // 메뉴에 등록되는 하위 프로그램 목록
	private String[] deleteMenuList; // 메뉴 삭제 목록

	/** 사이트맵 */
	/** 생성자ID **/
	private String creatPersonId;

	/** 권한정보설정 */
	/** 사용자아이디 */
	private String esntlId;
	/** 권한코드 */
	private String authorCode;

	/** 기타VO변수 */
	private String tempValue;
	private int tempInt;

	/** Login 메뉴관련 VO변수 */
	/** tmp_Id */
	private String tmpId;
	/** tmp_Password */
	private String tmpPassword;
	/** tmp_Name */
	private String tmpName;
	/** tmp_UserSe */
	private String tmpUserSe;
	/** tmp_Email */
	private String tmpEmail;
	/** tmp_OrgnztId */
	private String tmpOrgnztId;
	/** tmp_UniqId */
	private String tmpUniqId;
	/** tmp_Cmd */
	private String tmpCmd;

	@KeepCondition
	private String condMenuId;
	@KeepCondition
	private String condOrdr;
	@KeepCondition
	private String condMenuOrdr;
	@KeepCondition
	private String condMenuNm;
	@KeepCondition
	private String condProgrmFileNm;
	@KeepCondition
	private String condMenuDc;
	@KeepCondition
	private String condRelateImgPath;
	@KeepCondition
	private String condRelateImgNm;
	@KeepCondition
	private String condUpperMenuNo;
	@KeepCondition
	private String condMenuUseAt;
	@KeepCondition
	private String condSearchFlag;
	@KeepCondition
	private String condMenuIndictAt;
	
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuOrdr() {
		return menuOrdr;
	}

	public void setMenuOrdr(String menuOrdr) {
		this.menuOrdr = menuOrdr;
	}

	public String getMenuNm() {
		return menuNm;
	}

	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}

	public String getUpperMenuId() {
		return upperMenuId;
	}

	public void setUpperMenuId(String upperMenuId) {
		this.upperMenuId = upperMenuId;
	}

	public String getUpperMenuNm() {
		return upperMenuNm;
	}

	public void setUpperMenuNm(String upperMenuNm) {
		this.upperMenuNm = upperMenuNm;
	}

	public String getMenuDc() {
		return menuDc;
	}

	public void setMenuDc(String menuDc) {
		this.menuDc = menuDc;
	}

	public String getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getRelateImgPath() {
		return relateImgPath;
	}

	public void setRelateImgPath(String relateImgPath) {
		this.relateImgPath = relateImgPath;
	}

	public String getRelateImgNm() {
		return relateImgNm;
	}

	public void setRelateImgNm(String relateImgNm) {
		this.relateImgNm = relateImgNm;
	}

	public String getProgrmFileNm() {
		return progrmFileNm;
	}

	public void setProgrmFileNm(String progrmFileNm) {
		this.progrmFileNm = progrmFileNm;
	}

	public String getProgrmFnctSe() {
		return progrmFnctSe;
	}

	public void setProgrmFnctSe(String progrmFnctSe) {
		this.progrmFnctSe = progrmFnctSe;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMenuIndictAt() {
		return menuIndictAt;
	}

	public void setMenuIndictAt(String menuIndictAt) {
		this.menuIndictAt = menuIndictAt;
	}

	public String getChildMenuCnt() {
		return childMenuCnt;
	}

	public void setChildMenuCnt(String childMenuCnt) {
		this.childMenuCnt = childMenuCnt;
	}

	public List<MenuManageVO> getSubMenuList() {
		return subMenuList;
	}

	public void setSubMenuList(List<MenuManageVO> subMenuList) {
		this.subMenuList = subMenuList;
	}

	public String getAuthorIndexUrl() {
		return authorIndexUrl;
	}

	public void setAuthorIndexUrl(String authorIndexUrl) {
		this.authorIndexUrl = authorIndexUrl;
	}

	public String getIntrmdVriabl() {
		return intrmdVriabl;
	}

	public void setIntrmdVriabl(String intrmdVriabl) {
		this.intrmdVriabl = intrmdVriabl;
	}

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

	public String getCreateFlag() {
		return createFlag;
	}

	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
	}

	public String getCustomFlag1() {
		return customFlag1;
	}

	public void setCustomFlag1(String customFlag1) {
		this.customFlag1 = customFlag1;
	}

	public String getCustomFlag2() {
		return customFlag2;
	}

	public void setCustomFlag2(String customFlag2) {
		this.customFlag2 = customFlag2;
	}

	public String getCustomFlag3() {
		return customFlag3;
	}

	public void setCustomFlag3(String customFlag3) {
		this.customFlag3 = customFlag3;
	}

	public String[] getAuthorMenuArray() {
		return authorMenuArray;
	}

	public void setAuthorMenuArray(String[] authorMenuArray) {
		this.authorMenuArray = authorMenuArray;
	}

	public String[] getSearchFlagArray() {
		return searchFlagArray;
	}

	public void setSearchFlagArray(String[] searchFlagArray) {
		this.searchFlagArray = searchFlagArray;
	}

	public String[] getCreateFlagArray() {
		return createFlagArray;
	}

	public void setCreateFlagArray(String[] createFlagArray) {
		this.createFlagArray = createFlagArray;
	}

	public String[] getCustomFlag1Array() {
		return customFlag1Array;
	}

	public void setCustomFlag1Array(String[] customFlag1Array) {
		this.customFlag1Array = customFlag1Array;
	}

	public String[] getCustomFlag2Array() {
		return customFlag2Array;
	}

	public void setCustomFlag2Array(String[] customFlag2Array) {
		this.customFlag2Array = customFlag2Array;
	}

	public String[] getCustomFlag3Array() {
		return customFlag3Array;
	}

	public void setCustomFlag3Array(String[] customFlag3Array) {
		this.customFlag3Array = customFlag3Array;
	}

	public String[] getMenuProgrmList() {
		return menuProgrmList;
	}

	public void setMenuProgrmList(String[] menuProgrmList) {
		this.menuProgrmList = menuProgrmList;
	}

	public String[] getDeleteMenuList() {
		return deleteMenuList;
	}

	public void setDeleteMenuList(String[] deleteMenuList) {
		this.deleteMenuList = deleteMenuList;
	}

	public String getCreatPersonId() {
		return creatPersonId;
	}

	public void setCreatPersonId(String creatPersonId) {
		this.creatPersonId = creatPersonId;
	}

	public String getEsntlId() {
		return esntlId;
	}

	public void setEsntlId(String esntlId) {
		this.esntlId = esntlId;
	}

	public String getAuthorCode() {
		return authorCode;
	}

	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}

	public String getTempValue() {
		return tempValue;
	}

	public void setTempValue(String tempValue) {
		this.tempValue = tempValue;
	}

	public int getTempInt() {
		return tempInt;
	}

	public void setTempInt(int tempInt) {
		this.tempInt = tempInt;
	}

	public String getTmpId() {
		return tmpId;
	}

	public void setTmpId(String tmpId) {
		this.tmpId = tmpId;
	}

	public String getTmpPassword() {
		return tmpPassword;
	}

	public void setTmpPassword(String tmpPassword) {
		this.tmpPassword = tmpPassword;
	}

	public String getTmpName() {
		return tmpName;
	}

	public void setTmpName(String tmpName) {
		this.tmpName = tmpName;
	}

	public String getTmpUserSe() {
		return tmpUserSe;
	}

	public void setTmpUserSe(String tmpUserSe) {
		this.tmpUserSe = tmpUserSe;
	}

	public String getTmpEmail() {
		return tmpEmail;
	}

	public void setTmpEmail(String tmpEmail) {
		this.tmpEmail = tmpEmail;
	}

	public String getTmpOrgnztId() {
		return tmpOrgnztId;
	}

	public void setTmpOrgnztId(String tmpOrgnztId) {
		this.tmpOrgnztId = tmpOrgnztId;
	}

	public String getTmpUniqId() {
		return tmpUniqId;
	}

	public void setTmpUniqId(String tmpUniqId) {
		this.tmpUniqId = tmpUniqId;
	}

	public String getTmpCmd() {
		return tmpCmd;
	}

	public void setTmpCmd(String tmpCmd) {
		this.tmpCmd = tmpCmd;
	}

	public String getCondMenuId() {
		return condMenuId;
	}

	public void setCondMenuId(String condMenuId) {
		this.condMenuId = condMenuId;
	}

	public String getCondOrdr() {
		return condOrdr;
	}

	public void setCondOrdr(String condOrdr) {
		this.condOrdr = condOrdr;
	}

	public String getCondMenuOrdr() {
		return condMenuOrdr;
	}

	public void setCondMenuOrdr(String condMenuOrdr) {
		this.condMenuOrdr = condMenuOrdr;
	}

	public String getCondMenuNm() {
		return condMenuNm;
	}

	public void setCondMenuNm(String condMenuNm) {
		this.condMenuNm = condMenuNm;
	}

	public String getCondProgrmFileNm() {
		return condProgrmFileNm;
	}

	public void setCondProgrmFileNm(String condProgrmFileNm) {
		this.condProgrmFileNm = condProgrmFileNm;
	}

	public String getCondMenuDc() {
		return condMenuDc;
	}

	public void setCondMenuDc(String condMenuDc) {
		this.condMenuDc = condMenuDc;
	}

	public String getCondRelateImgPath() {
		return condRelateImgPath;
	}

	public void setCondRelateImgPath(String condRelateImgPath) {
		this.condRelateImgPath = condRelateImgPath;
	}

	public String getCondRelateImgNm() {
		return condRelateImgNm;
	}

	public void setCondRelateImgNm(String condRelateImgNm) {
		this.condRelateImgNm = condRelateImgNm;
	}

	public String getCondUpperMenuNo() {
		return condUpperMenuNo;
	}

	public void setCondUpperMenuNo(String condUpperMenuNo) {
		this.condUpperMenuNo = condUpperMenuNo;
	}

	public String getCondMenuUseAt() {
		return condMenuUseAt;
	}

	public void setCondMenuUseAt(String condMenuUseAt) {
		this.condMenuUseAt = condMenuUseAt;
	}

	public String getCondSearchFlag() {
		return condSearchFlag;
	}

	public void setCondSearchFlag(String condSearchFlag) {
		this.condSearchFlag = condSearchFlag;
	}

	public String getCondMenuIndictAt() {
		return condMenuIndictAt;
	}

	public void setCondMenuIndictAt(String condMenuIndictAt) {
		this.condMenuIndictAt = condMenuIndictAt;
	}
	
	/*
	 * EHCache 동일성을 구분하기 위한 equals method
	 */
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	/*
	 * EHCache 동일성을 구분하기 위한 hashCode method
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}