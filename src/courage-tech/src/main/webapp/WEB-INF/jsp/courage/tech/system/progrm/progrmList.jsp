<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>
<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		Progrm.TOTAL_CNT = '<c:out value="${pagination.totalRecordCount }" />';
		Progrm.formInitList();
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
	
	<form:form name="formProgrm" id="formProgrm" action="retrieveProgrmList.do" method="post" commandName="progrmVO">
		<!-- 페이지 유지정보 : begin -->
		<form:hidden path="pageIndex"/>
		<form:hidden path="pagingEnable"/>
		<form:hidden path="recordCountPerPage"/>
		<form:hidden path="pageSize"/>
		<!-- 페이지 유지정보 : end -->
		
		<form:hidden path="progrmFileNm"/>
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
	                   		<col style="width:110px;"/>
	                   		<col style="width:130px;"/>
	                   		<col style="width:110px;"/>
	                        <col />
						</colgroup>
						
						<tbody>
							<tr>
								<th>파일명</th>
								<td><form:input path="condProgrmFileNm" cssClass="_enter[Progrm.search]" cssStyle="width: 95%;"/></td>
								<th>프로그램구분</th>
								<td><cmmCd:cdSelect codeId="CURG002" name="condProgrmSe" showAll="true" defaultCode="${progrmVO.condProgrmSe }" styleClass="_enter[Progrm.search]" style="width: 120px;" use="true" /></td>
								<th>URL</th>
								<td><form:input path="condUrl" cssClass="_enter[Progrm.search]" cssStyle="width: 95%;"/></td>
	                        </tr>
							<tr>
								<th>프로그램명</th>
								<td><form:input path="condProgrmKoreanNm" cssClass="_enter[Progrm.search]" cssStyle="width: 95%;"/></td>
								<th>기능구분</th>
								<td><cmmCd:cdSelect codeId="CURG003" name="condProgrmFncSe" showAll="true" defaultCode="${progrmVO.condProgrmFncSe }" styleClass="_enter[Progrm.search]" style="width: 120px;" use="true" /></td>
								<th>로그인확인여부</th>
								<td><cmmCd:cdSelect codeId="COM038" name="condLoginCheckAt" showAll="true" defaultCode="${progrmVO.condLoginCheckAt }" styleClass="_enter[Progrm.search]" use="true" /></td>
							</tr>
						</tbody>
					</table>
					
					<div class="tbl_search">
						<button type="button" id="search" class="gen_btn btn_srch_submit _command[Progrm.search]"><spring:message code="button.search" /></button>
					</div>
				</div>	
			</fieldset>
		</div>
		<!-- 상세조회 : end -->
		
		<div id="contentBtnFix" class="button_area staticHeightArea">
			<div class="buttonSet left">
				<button type="button" class="gen_btn needCreate _command[Progrm.createView]" accesskey="2"><spring:message code="button.new.create" /></button>
				<button type="button" class="gen_btn _command[Progrm.excelDownload]" accesskey="3"><spring:message code="button.exel" /></button>
			</div>
		</div>
	</form:form>
</div>

<div id="data_list_area" class="type_list">
	<div id="listTableFix" class="list_table_header">
		<table class="header" cellspacing="0" cellpadding="0" summary="목록 항목입니다.">
			<colgroup>
				<col style="width:200px;"/>
				<col style="width:100px;"/>
				<col style="width:110px;"/>
				<col/>
				<col style="width:300px;"/>
				<col style="width:110px;"/>
			</colgroup>
			
			<thead>
				<tr>
					<th><a id="progrm_file_nm" class="_command[_listHeaderToggle|Progrm.refreshList]">파일명</a></th>
					<th class="separator"><a id="progrm_se" class="_command[_listHeaderToggle|Progrm.refreshList]">프로그램 구분</a></th>
					<th class="separator">기능 구분</th>
					<th class="separator"><a id="progrm_korean_nm" class="_command[_listHeaderToggle|Progrm.refreshList]">프로그램명</a></th>
					<th class="separator"><a id="url" class="_command[_listHeaderToggle|Progrm.refreshList]">URL</a></th>
					<th class="separator"><a id="login_check_at" class="_command[_listHeaderToggle|Progrm.refreshList]">로그인확인여부</a></th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div id="table_list" class="list_table_box">
		<table cellspacing="0" cellpadding="0">
			<colgroup>
				<col style="width:200px;"/>
				<col style="width:100px;"/>
				<col style="width:110px;"/>
				<col/>
				<col style="width:300px;"/>
				<col style="width: 92px;#width:110px;"/><!-- Header size보다 18px 작게 잡아야함. 스크롤 영역 (동적처리는 IE 미지원으로 고정값 설정) -->
			</colgroup>
			
			<tbody>
				<c:choose>
					<c:when test="${fn:length(progrmList) <= 0 }">
						<tr>
							<td colspan="6"><spring:message code="info.nodata.msg" /></td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="progrmListVO" items="${progrmList }" varStatus="sttus">
							<tr>
								<td class="Ltxt">
									<div class="ellipsis" title="<c:out value="${progrmListVO.progrmFileNm }" />">
										<a id="${progrmListVO.progrmFileNm }" class="_command[Progrm.detailView]"><c:out value="${progrmListVO.progrmFileNm }" /></a>&nbsp;
									</div>
								</td>
								<td><cmmCd:cdValue code="${progrmListVO.progrmSe }" codeId="CURG002"/>&nbsp;</td>
								<td><cmmCd:cdValue code="${progrmListVO.progrmFnctSe }" codeId="CURG003"/>&nbsp;</td>
								<td class="Ltxt">
									<div class="ellipsis" title="<c:out value="${progrmListVO.progrmKoreanNm }" />">
										<c:out value="${progrmListVO.progrmKoreanNm }" />&nbsp;
									</div>
								</td>
								<td class="Ltxt">
									<div class="ellipsis" title="<c:out value="${progrmListVO.url }" />">
										<c:out value="${progrmListVO.url }" />&nbsp;
									</div>
								</td>
								<td>${progrmListVO.loginCnfirmAt }&nbsp;</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	
	<c:if test="${fn:length(progrmList) > 0 && progrmVO.pagingEnable eq '1' }">
		<div id="pagination" class="pagination">
			<div class="listPaging">
				<ui:pagination paginationInfo="${pagination }" type="image" jsFunction="Progrm.fn_egov_link_page" />
			</div>
		</div>
	</c:if>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />