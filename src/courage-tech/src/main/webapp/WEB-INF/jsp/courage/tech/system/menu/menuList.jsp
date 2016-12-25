<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>
<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		Menu.TOTAL_CNT = '<c:out value="${pagination.totalRecordCount }" />';
		Menu.formInitList();
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
	
	<form:form name="formMenu" id="formMenu" action="retrieveMenuList.tech" method="post" commandName="menuVO">
		<!-- 페이지 유지정보 : begin -->
		<form:hidden path="pageIndex"/>
		<form:hidden path="pagingEnable"/>
		<form:hidden path="recordCountPerPage"/>
		<form:hidden path="pageSize"/>
		<!-- 페이지 유지정보 : end -->
		
		<form:hidden path="menuId"/>
		<form:hidden path="upperMenuId" />
		<form:hidden path="condOrder"/>
		<form:hidden path="condAlign"/>
		<cmmUtil:ajaxToken name="_dt"/>
		
		<!-- 상세조회 : begin -->
		<div id="detail_search" class="section_detail_search staticHeightArea">
			<fieldset>
				<div class="item_row">
					<table>
						<colgroup>
							<col style="width: 70px;" />
							<col style="width: 150px;" />
							<col style="width: 130px;" />
							<col style="width: 150px;" />
							<col style="width: 140px;" />
							<col />
						</colgroup>
			
						<tbody>
							<tr>
								<th>메뉴ID</th>
								<td><form:input path="condMenuId" cssClass="_enter[Menu.refreshList]" cssStyle="width: 95%;" maxlength="20"/></td>
								<th>상위메뉴ID</th>
								<td><form:input path="condUpperMenuNo" cssClass="_enter[Menu.refreshList]" cssStyle="width: 95%;" maxlength="20"/></td>
								<th>메뉴표시여부</th>
								<td><cmmCd:cdSelect codeId="COM045" name="condMenuIndictAt" showAll="true" defaultCode="${menuVO.condMenuIndictAt }" styleClass="_enter[Menu.refreshList]" use="true" /></td>
							</tr>
							<tr>
								<th>메뉴명</th>
								<td colspan="5"><form:input path="condMenuNm" cssClass="_enter[Menu.refreshList]" cssStyle="width: 450px;" maxlength="30"/></td>
							</tr>
						</tbody>
					</table>
					
					<div class="tbl_search">
						<button type="button" id="search" class="gen_btn btn_srch_submit _command[Menu.search]"><spring:message code="button.search" /></button>
					</div>
				</div>
			</fieldset>
		</div>
		<!-- 상세조회 : end -->
		
		<div id="contentBtnFix" class="button_area staticHeightArea">
			<div class="buttonSet left">
				<button type="button" class="gen_btn needCreate _command[Menu.createView]" accesskey="2">상위메뉴 등록</button>
				<button type="button" class="gen_btn needCreate _command[Menu.excelDownload]" accesskey="3"><spring:message code="button.exel" /></button>
			</div>
		</div>
	</form:form>
</div>

<div id="data_list_area" class="type_list">
	<div id="listTableFix" class="list_table_header">
		<table class="header" cellspacing="0" cellpadding="0">
			<colgroup>
				<col style="width: 120px;" />
				<col />
				<col />
				<col style="width: 80px;" />
				<col style="width: 120px;" />
				<col style="width: 100px;" />
				<col style="width: 130px;" />
			</colgroup>
			
			<thead>
				<tr>
					<th>메뉴ID</th>
					<th class="separator">메뉴명</th>
					<th class="separator">메뉴설명</th>
					<th class="separator">정렬순서</th>
					<th class="separator">상위메뉴ID</th>
					<th class="separator">메뉴표시여부</th>
					<th class="separator">하위메뉴등록</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div id="table_list" class="list_table_box">
		<table cellspacing="0" cellpadding="0">
			<colgroup>
				<col style="width: 120px;" />
				<col />
				<col />
				<col style="width: 80px;" />
				<col style="width: 120px;" />
				<col style="width: 100px;" />
				<col style="width: 112px;#width:130px;"/><!-- Header size보다 18px 작게 잡아야함. 스크롤 영역 (동적처리는 IE 미지원으로 고정값 설정) -->
			</colgroup>
			
			<tbody>
				<c:choose>
					<c:when test="${fn:length(menuList) <= 0 }">
						<tr>
							<td colspan="7"><spring:message code="info.nodata.msg" /></td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="menuListVO" items="${menuList }" varStatus="sttus">
							<tr>
								<td><c:out value="${menuListVO.menuId }" />&nbsp;</td>
								<td class="Ltxt">
									<div class="ellipsis" title="<c:out value="${menuListVO.menuNm }" />">
										<c:forEach begin="1" end="${menuListVO.menuLevel }">
											&nbsp;&nbsp;&nbsp;
										</c:forEach>
										<a id="${menuListVO.menuId }" class="_command[Menu.detailView]"><c:out value="${menuListVO.menuNm }" /></a>&nbsp;
									</div>
								</td>
								<td class="Ltxt">
									<div class="ellipsis" title="<c:out value="${menuListVO.menuDc }" />">
										<c:out value="${menuListVO.menuDc }" />&nbsp;
									</div>
								</td>
								<td><cmmUtil:numFormat value="${menuListVO.menuOrdr }" />&nbsp;</td>
								<td><c:out value="${menuListVO.upperMenuId }" />&nbsp;</td>
								<td><cmmCd:cdValue code="${menuListVO.menuIndictAt }" codeId="COM045"/> &nbsp;</td>
								<td>
									<c:if test="${!empty menuListVO.upperMenuId }">
										<button type="button" id="${menuListVO.menuId }" class="gen_btn needCreate _command[Menu.createView]">하위메뉴 등록</button>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	
	<c:if test="${fn:length(menuList) > 0 && menuVO.pagingEnable eq '1' }">
		<div id="pagination" class="pagination">
			<div class="listPaging">
				<ui:pagination paginationInfo="${pagination }" type="image" jsFunction="Menu.fn_egov_link_page" />
			</div>
		</div>
	</c:if>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />