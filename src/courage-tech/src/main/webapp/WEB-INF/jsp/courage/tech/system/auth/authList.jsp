<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		Auth.TOTAL_CNT = '<c:out value="${pagination.totalRecordCount }" />';
		Auth.formInitList();
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
	
	<form:form name="formAuth" id="formAuth" action="retrieveAuthList.do" method="post" commandName="authVO">
		<!-- 페이지 유지정보 : begin -->
		<form:hidden path="pageIndex"/>
		<form:hidden path="pagingEnable"/>
		<form:hidden path="recordCountPerPage"/>
		<form:hidden path="pageSize"/>
		<!-- 페이지 유지정보 : end -->
		
		<form:hidden path="authorCode"/>
		<form:hidden path="condOrder"/>
		<form:hidden path="condAlign"/>
		<cmmUtil:ajaxToken name="_dt"/>
		
		<!-- 상세조회 : begin -->
		<div id="detail_search" class="section_detail_search staticHeightArea">
			<fieldset>
				<div class="item_row">
					<table>
						<colgroup>
							<col style="width: 80px;" />
							<col style="width: 150px;" />
							<col style="width: 80px;" />
							<col />
						</colgroup>
						
						<tbody>
							<tr>
								<th>권한ID</th>
								<td><form:input path="condAuthorCode" cssClass="_enter[Auth.refreshList]" cssStyle="width: 95%;"/></td>
								<th>권한명</th>
								<td><form:input path="condAuthorNm" cssClass="_enter[Auth.refreshList]" cssStyle="width: 95%;"/></td>
							</tr>
						</tbody>
					</table>
					
					<div class="tbl_search">
						<button type="button" id="search" class="gen_btn btn_srch_submit _command[Auth.search]"><spring:message code="button.search" /></button>
					</div>
				</div>
			</fieldset>
		</div>
		<!-- 상세조회 : end -->
		
		<div id="contentBtnFix" class="button_area staticHeightArea">
			<div class="buttonSet left">
				<button type="button" class="gen_btn needCreate _command[Auth.createView]" accesskey="2"><spring:message code="button.new.create" /></button>
				<button type="button" class="gen_btn _command[Auth.excelDownload]" accesskey="3"><spring:message code="button.exel" /></button>
			</div>
		</div>
	</form:form>
</div>

<div id="data_list_area" class="type_list">
	<div id="listTableFix" class="list_table_header">
		<table class="header" cellspacing="0" cellpadding="0" summary="목록 항목입니다.">
			<colgroup>
				<col style="width: 200px;" />
				<col style="width: 250px;" />
				<col style="width: 130px;" />
				<col />
				<col style="width: 120px;" />
			</colgroup>
			
			<thead>
				<tr>
					<th><a id="author_code" class="_command[_listHeaderToggle|Auth.refreshList]">권한ID</a></th>
					<th class="separator"><a id="author_nm" class="_command[_listHeaderToggle|Auth.refreshList]">권한명</a></th>
					<th class="separator">사용자 기본권한</th>
					<th class="separator">권한설명</th>
					<th class="separator"><a id="author_creat_de" class="_command[_listHeaderToggle|Auth.refreshList]">등록일자</a></th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div id="table_list" class="list_table_box">
		<table cellspacing="0" cellpadding="0">
			<colgroup>
				<col style="width: 200px;" />
				<col style="width: 250px;" />
				<col style="width: 130px;" />
				<col />
				<col style="width: 102px;#width:120px;" /><!-- Header size보다 18px 작게 잡아야함. 스크롤 영역 (동적처리는 IE 미지원으로 고정값 설정) -->
			</colgroup>
			
			<tbody>
				<c:choose>
					<c:when test="${fn:length(authList) <= 0 }">
						<tr>
							<td colspan="5"><spring:message code="info.nodata.msg" /></td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="authListVO" items="${authList }" varStatus="sttus">
							<tr>
								<td class="Ltxt">
		                    		<div class="ellipsis" title="<c:out value="${authListVO.authorCode }" />">
		                    			<a id="${authListVO.authorCode }" class="_command[Auth.detailView]"><c:out value="${authListVO.authorCode }" /></a>
		                    		</div>
		                    	</td>
		                    	<td class="Ltxt">
		                    		<div class="ellipsis" title="<c:out value="${authListVO.authorNm }" />">
		                    			<c:out value="${authListVO.authorNm }" />&nbsp;
		                    		</div>
		                    	</td>
		                    	<td>
	                    			<c:out value="${authListVO.mberBassAuthorYn }" />&nbsp;
		                    	</td>
		                    	<td class="Ltxt">
		                    		<div class="ellipsis" title="<c:out value="${authListVO.authorDc }" />">
		                    			<c:out value="${authListVO.authorDc }" />&nbsp;
		                    		</div>
		                    	</td>
		                        <td><cmmUtil:strFormat pattern="-" value="${authListVO.authorCreatDe }" format="####-##-##"/></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	
	<c:if test="${fn:length(authList) > 0 && authVO.pagingEnable eq '1' }">
		<div id="pagination" class="pagination">
			<div class="listPaging">
				<ui:pagination paginationInfo="${pagination }" type="image" jsFunction="Auth.fn_egov_link_page" />
			</div>
		</div>
	</c:if>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />