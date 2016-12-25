<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		CommonCode.TOTAL_CNT = '<c:out value="${pagination.totalRecordCount }" />';
		CommonCode.formInitList();
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
	
	<form:form name="formCommonCode" id="formCommonCode" action="retrieveCmmnCodeList.tech" method="post" commandName="cmmnCodeVO">
		<!-- 페이지 유지정보 : begin -->
		<form:hidden path="pageIndex"/>
		<form:hidden path="pagingEnable"/>
		<form:hidden path="recordCountPerPage"/>
		<form:hidden path="pageSize"/>
		<!-- 페이지 유지정보 : end -->
		
		<form:hidden path="codeId"/>
		<form:hidden path="condOrder"/>
		<form:hidden path="condAlign"/>
		<cmmUtil:ajaxToken name="_dt"/>
		
		<input type="text" name="ieInputFix" style="display: none;">
		
		<!-- 상세조회 : begin -->
		<div id="detail_search" class="section_detail_search staticHeightArea">
			<fieldset>
				<div class="item_row">
					<table>
						<colgroup>
							<col style="width: 80px" />
	                       	<col style="width: 150px" />
	                       	<col style="width: 80px" />
	                       	<col />
						</colgroup>
						
						<tbody>
							<tr>
								<th>코드ID</th>
								<td>
									<cmmCd:cdSelect operation="courageCommonCode.selectCommonCodeOperationList" name="condCodeId" showAll="true" defaultCode="${cmmnCodeVO.condCodeId }" styleClass="_enter[CommonCode.refreshList]" />
								</td>
								<th>코드명</th>
								<td>	
									<form:input path="condCodeIdNm" cssClass="input-text-srch _enter[CommonCode.refreshList]" cssStyle="width: 200px;"/>
								</td>	
							</tr>
							<tr>
								<th>사용여부</th>
								<td colspan="3">	
									<cmmCd:cdSelect codeId="COM045" name="condUseAt" showAll="true" defaultCode="${cmmnCodeVO.condUseAt }" use="true" styleClass=" _enter[CommonCode.refreshList]" />
								</td>	
							</tr>
						</tbody>
					</table>
					
					<div class="tbl_search">
						<button type="button" id="search" class="gen_btn btn_srch_submit _command[CommonCode.search]"><spring:message code="button.search" /></button>
					</div>
				</div>	
			</fieldset>
		</div>
		<!-- 상세조회 : end -->
		
		<div id="contentBtnFix" class="button_area staticHeightArea">
			<div class="buttonSet left">
				<button type="button" id="createNotice" class="gen_btn needCreate _command[CommonCode.createView]" accesskey="2">
					공통코드 등록
				</button>
				<button type="button" class="gen_btn needCreate _command[CommonCode.cacheReload]" accesskey="3">공통코드 적용</button>
				<button type="button" class="gen_btn _command[CommonCode.excelDownload]" accesskey="4"><spring:message code="button.exel" /></button>
			</div>
		</div>
	</form:form>
</div>

<div id="data_list_area" class="type_list">
	<div id="listTableFix" class="list_table_header">
		<table class="header" cellspacing="0" cellpadding="0" summary="목록 항목입니다.">
			<colgroup>
				<col style="width: 120px;">
				<col>
				<col style="width: 90px;">
				<col style="width: 100px;">
				<col style="width: 120px;">
				<col style="width: 160px;">
			</colgroup>
			
			<thead>
				<tr>
					<th><a id="code_id" class="_command[_listHeaderToggle|CommonCode.refreshList]"><spring:message code="cmmnCodeVO.codeId" /></a></th>
					<th class="separator"><a id="code_id_nm" class="_command[_listHeaderToggle|CommonCode.refreshList]"><spring:message code="cmmnCodeVO.codeIdNm" /></a></th>
					<th class="separator"><a id="use_at" class="_command[_listHeaderToggle|CommonCode.refreshList]"><spring:message code="cmmnCodeVO.useAt" /></a></th>
					<th class="separator"><a id="detail_code_count" class="_command[_listHeaderToggle|CommonCode.refreshList]"><spring:message code="cmmnCodeVO.detailCodeCount" /></a></th>
					<th class="separator"><spring:message code="cmmn.frstRegisterId" /></th>
					<th class="separator"><a id="frst_regist_pnttm" class="_command[_listHeaderToggle|CommonCode.refreshList]"><spring:message code="cmmn.frstRegistPnttm" /></a></th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div id="table_list" class="list_table_box">
		<table cellspacing="0" cellpadding="0" summary="코드 목록">
			<colgroup>
				<col style="width: 120px;">
				<col>
				<col style="width: 90px;">
				<col style="width: 100px;">
				<col style="width: 120px;">
				<col style="width: 142px;#width:160px;"><!-- Header size보다 18px 작게 잡아야함. 스크롤 영역 (동적처리는 IE 미지원으로 고정값 설정) -->
			</colgroup>
			
			<tbody>
				<c:choose>
					<c:when test="${fn:length(cmmnCodeList) <= 0 }">
						<tr>
							<td colspan="6"><spring:message code="info.nodata.msg" /></td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="cmmnCodeVO" items="${cmmnCodeList }" varStatus="sttus">
							<tr>
								<td>${cmmnCodeVO.codeId }</td>
								<td class="Ltxt">
									<div class="ellipsis" title="${cmmnCodeVO.codeIdNm }">
										<a id="${cmmnCodeVO.codeId }" class="_command[CommonCode.detailView]">${cmmnCodeVO.codeIdNm }</a>
									</div>
								</td>
								<td><cmmCd:cdValue codeId="COM045" code="${cmmnCodeVO.useAt }" /></td>
								<td><cmmUtil:numFormat value="${cmmnCodeVO.detailCodeCount }" /></td>
								<td>${cmmnCodeVO.frstRegisterNm }</td>
								<td>${cmmnCodeVO.frstRegistPnttm }</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	
	<c:if test="${fn:length(cmmnCodeList) > 0 && cmmnCodeVO.pagingEnable eq '1' }">
		<div id="pagination" class="pagination">
			<div class="listPaging">
				<ui:pagination paginationInfo="${pagination }" type="image" jsFunction="CommonCode.fn_egov_link_page" />
			</div>
		</div>
	</c:if>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />