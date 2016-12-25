<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>
<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		UserAuth.TOTAL_CNT = '<c:out value="${pagination.totalRecordCount }" />';
		UserAuth.formInitList();
	});
//-->
</script>

<div class="ct_area">
	<div id="contentInfo" class="path_search">
		<div id="progrmNm" class="left_path"></div>
		<div class="right_path">
			<span id="menuNavi"></span>
		</div>
	</div>
	
	<form:form name="formUserAuth" id="formUserAuth" action="retrieveUserAuthList.do" method="post" commandName="userAuthVO">
		<!-- 페이지 유지정보 : begin -->
		<form:hidden path="pageIndex"/>
		<form:hidden path="pagingEnable"/>
		<form:hidden path="recordCountPerPage"/>
		<form:hidden path="pageSize"/>
		<!-- 페이지 유지정보 : end -->
		
		<form:hidden path="esntlId"/>
		<form:hidden path="condOrder"/>
		<form:hidden path="condAlign"/>
		<cmmUtil:ajaxToken name="_dt"/>
		
		<!-- 상세조회 : begin -->
		<div id="detail_search" class="section_detail_search staticHeightArea">
			<fieldset>
				<div class="item_row">
					<table>
						<colgroup>
							<col style="width:100px;"/>
	                   		<col style="width:200px;"/>
	                   		<col style="width:100px;"/>
	                   		<col style="width:200px;"/>
	                   		<col style="width:100px;"/>
	                        <col />
						</colgroup>
						
						<tbody>
							<tr>
								<th>사용자ID</th>
								<td><form:input path="condEmplyrId" cssClass="_enter[UserAuth.search]" cssStyle="width: 95%;"/></td>
								<th>사용자명</th>
								<td><form:input path="condUserNm" cssClass="_enter[UserAuth.search]" cssStyle="width: 95%;"/></td>
								<th>회원구분</th>
								<td><cmmCd:cdSelect codeId="COM012" name="condMberSe" showAll="true" defaultCode="${userAuthVO.condMberSe }" styleClass="_enter[UserAuth.search]" use="true" /></td>
	                        </tr>
	                        <tr>
								<th>사용권한</th>
								<td colspan="5"><cmmCd:cdSelect operation="authDAO.selectAuthCommonCodeList" name="condAuthor" showAll="true" defaultCode="${userAuthVO.condAuthor }" styleClass="_enter[UserAuth.search]" use="true" /></td>
	                        </tr>
						</tbody>
					</table>
					
					<div class="tbl_search">
						<button type="button" id="search" class="gen_btn btn_srch_submit _command[UserAuth.search]"><spring:message code="button.search" /></button>
					</div>
				</div>	
			</fieldset>
		</div>
		<!-- 상세조회 : end -->
		
		<div id="contentBtnFix" class="button_area staticHeightArea">
			<div class="buttonSet left">
				<button type="button" class="gen_btn needCreate _command[UserAuth.excelDownload]" accesskey="2"><spring:message code="button.exel" /></button>
			</div>
		</div>
	</form:form>
</div>

<div id="data_list_area" class="type_list">
	<div id="listTableFix" class="list_table_header">
		<table class="header" cellspacing="0" cellpadding="0" summary="목록 항목입니다.">
			<colgroup>
				<col style="width:200px;"/>
				<col style="width:200px;"/>
				<col style="width:150px;"/>
				<col/>
				<col style="width:150px;"/>
			</colgroup>
			
			<thead>
				<tr>
					<th>고유ID</th>
					<th class="separator"><a id="emplyr_id" class="_command[_listHeaderToggle|UserAuth.refreshList]">사용자ID</a></th>
					<th class="separator"><a id="user_nm" class="_command[_listHeaderToggle|UserAuth.refreshList]">사용자명</a></th>
					<th class="separator">사용권한</th>
					<th class="separator"><a id="mber_se" class="_command[_listHeaderToggle|UserAuth.refreshList]">회원구분</a></th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div id="table_list" class="list_table_box">
		<table cellspacing="0" cellpadding="0">
			<colgroup>
				<col style="width:200px;"/>
				<col style="width:200px;"/>
				<col style="width:150px;"/>
				<col/>
				<col style="width:132px;#width:150px;"/><!-- Header size보다 18px 작게 잡아야함. 스크롤 영역 (동적처리는 IE 미지원으로 고정값 설정) -->
			</colgroup>
			
			<tbody>
				<c:choose>
					<c:when test="${fn:length(userAuthList) <= 0 }">
						<tr>
							<td colspan="5"><spring:message code="info.nodata.msg" /></td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="userAuthListVO" items="${userAuthList }" varStatus="sttus">
							<tr>
								<td>
									<div class="ellipsis" title="<c:out value="${userAuthListVO.esntlId }" />">
										<a id="${userAuthListVO.esntlId }" class="_command[UserAuth.createViewWindowOpen]"><c:out value="${userAuthListVO.esntlId }" /></a>&nbsp;
									</div>
								</td>
								<td>${userAuthListVO.emplyrId }&nbsp;</td>
								<td>${userAuthListVO.userNm }&nbsp;</td>
								<td class="Ltxt">
									<div class="ellipsis" title="<c:out value="${userAuthListVO.authorNm }" />">
										${userAuthListVO.authorNm }&nbsp;
									</div>
								</td>
								<td><cmmCd:cdValue code="${userAuthListVO.mberSe }" codeId="COM012" />&nbsp;</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	
	<c:if test="${fn:length(userAuthList) > 0 && userAuthVO.pagingEnable eq '1' }">
		<div id="pagination" class="pagination">
			<div class="listPaging">
				<ui:pagination paginationInfo="${pagination }" type="image" jsFunction="UserAuth.fn_egov_link_page" />
			</div>
		</div>
	</c:if>
</div>

<!-- commonForm:Begin -->
<form name="popupForm" id="popupForm" method="post" action="">
    <input type="hidden" name="openerKey" id="openerKey" value="" />
    <input type="hidden" name="returnFunction" id="returnFunction" value="" />
</form>
<!-- commonForm:End -->

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />