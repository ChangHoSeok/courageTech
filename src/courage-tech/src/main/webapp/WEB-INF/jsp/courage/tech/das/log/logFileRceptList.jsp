<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>
<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		LogFileRcept.TOTAL_CNT = '<c:out value="${pagination.totalRecordCount }" />';
		LogFileRcept.formInitList();
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
	
	<form:form name="formLogFileRcept" id="formLogFileRcept" action="retrieveLogfileRceptList.do" method="post" commandName="logFileRceptVO">
		<!-- 페이지 유지정보 : begin -->
		<form:hidden path="pageIndex"/>
		<form:hidden path="pagingEnable"/>
		<form:hidden path="recordCountPerPage"/>
		<form:hidden path="pageSize"/>
		<!-- 페이지 유지정보 : end -->
		
		<form:hidden path="groupId"/>
		<form:hidden path="rceptId"/>
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
	                   		<col style="width:150px;"/>
	                   		<col style="width:100px;"/>
	                   		<col style="width:150px;"/>
	                   		<col style="width:100px;"/>
	                        <col />
						</colgroup>
						
						<tbody>
							<tr>
								<th>로그분류그룹</th>
								<td colspan="5">
									<cmmCd:cdSelect name="condGroupId" id="condGroupId"
										operation="logClGroupDAO.selectCommonCodeListForLogClGroup"
										defaultCode="${logFileRceptVO.condGroupId }"
										showAll="true"
										use="true"/>
								</td>
	                        </tr>
						</tbody>
					</table>
					
					<div class="tbl_search">
						<button type="button" id="search" class="gen_btn btn_srch_submit _command[LogFileRcept.search]"><spring:message code="button.search" /></button>
					</div>
				</div>	
			</fieldset>
		</div>
		<!-- 상세조회 : end -->
		
		<div id="contentBtnFix" class="button_area staticHeightArea">
			<div class="buttonSet left">
				<button type="button" class="gen_btn needCreate _command[LogFileRcept.createView]" accesskey="2">접수</button>
			</div>
		</div>
	</form:form>
</div>

<div id="data_list_area" class="type_list">
	<div id="listTableFix" class="list_table_header">
		<table class="header" cellspacing="0" cellpadding="0" summary="목록 항목입니다.">
			<colgroup>
				<col/>
				<col style="width:200px;"/>
				<col style="width:120px;"/>
				<col style="width:120px;"/>
				<col style="width:100px;"/>
				<col style="width:120px;"/>
				<col style="width:110px;"/>
			</colgroup>
			
			<thead>
				<tr>
					<th><a id="A.GROUP_NM" class="_command[_listHeaderToggle|LogFileRcept.refreshList]">로그분류그룹</a></th>
					<th class="separator"><a id="A.RCEPT_ID" class="_command[_listHeaderToggle|LogFileRcept.refreshList]">접수ID</a></th>
					<th class="separator"><a id="A.RCEPT_DT" class="_command[_listHeaderToggle|LogFileRcept.refreshList]">접수일시</a></th>
					<th class="separator"><a id="A.RCEPT_TY" class="_command[_listHeaderToggle|LogFileRcept.refreshList]">접수구분</a></th>
					<th class="separator"><a id="A.PROCESS_STTUS" class="_command[_listHeaderToggle|LogFileRcept.refreshList]">처리상태</a></th>
					<th class="separator">등록자</th>
					<th class="separator">비고</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div id="table_list" class="list_table_box">
		<table cellspacing="0" cellpadding="0">
			<colgroup>
				<col/>
				<col style="width:200px;"/>
				<col style="width:120px;"/>
				<col style="width:120px;"/>
				<col style="width:100px;"/>
				<col style="width:120px;"/>
				<col style="width:92px;#width:110px;"/><!-- Header size보다 18px 작게 잡아야함. 스크롤 영역 (동적처리는 IE 미지원으로 고정값 설정) -->
			</colgroup>
			
			<tbody>
				<c:choose>
					<c:when test="${fn:length(logFileRceptList) <= 0 }">
						<tr>
							<td colspan="7"><spring:message code="info.nodata.msg" /></td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="logFileRceptListVO" items="${logFileRceptList }" varStatus="sttus">
							<tr>
								<td>${logFileRceptListVO.groupNm }&nbsp;(${logFileRceptListVO.groupId })</td>
								<td>
									<a id="${logFileRceptListVO.groupId }|${logFileRceptListVO.rceptId }" class="_command[LogFileRcept.detailView]">
										${logFileRceptListVO.rceptId }&nbsp;
									</a>
								</td>
								<td><fmt:formatDate value="${logFileRceptListVO.rceptDt }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td><cmmCd:cdValue code="${logFileRceptListVO.rceptTy }" codeId="CURG008" /></td>
								<td><cmmCd:cdValue code="${logFileRceptListVO.processSttus }" codeId="CURG007" /></td>
								<td>${logFileRceptListVO.frstRegisterNm }&nbsp;</td>
								<td>
									<c:set var="isDeletePosbl" value="${false }" />
									<c:if test="${logFileRceptListVO.processSttus eq '3' || logFileRceptListVO.processSttus eq '4' || logFileRceptListVO.processSttus eq '5' }">
										<c:set var="isDeletePosbl" value="${true }" />
									</c:if>
									
									<button type="button"
											id="${logFileRceptListVO.groupId }|${logFileRceptListVO.rceptId }"
											${isDeletePosbl ? '' : 'disabled="disabled"' }
											class="gen_btn needCreate _command[LogFileRcept.deleteLogFileRcept]">
										<spring:message code="button.delete" />
									</button>&nbsp;
								</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	
	<c:if test="${fn:length(logFileRceptList) > 0 && logFileRceptVO.pagingEnable eq '1' }">
		<div id="pagination" class="pagination">
			<div class="listPaging">
				<ui:pagination paginationInfo="${pagination }" type="image" jsFunction="LogFileRcept.fn_egov_link_page" />
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