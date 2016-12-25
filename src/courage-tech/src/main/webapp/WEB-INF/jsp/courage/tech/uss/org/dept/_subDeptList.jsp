<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>
<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		Dept.TOTAL_CNT = '<c:out value="${pagination.totalRecordCount }" />';
		Dept.formInitList();
	});
//-->
</script>

<form:form name="formDept" id="formDept" action="retrieveDeptList.do" method="post" commandName="deptVO">
	<!-- 페이지 유지정보 : begin -->
	<form:hidden path="pageIndex"/>
	<form:hidden path="pagingEnable"/>
	<form:hidden path="recordCountPerPage"/>
	<form:hidden path="pageSize"/>
	<!-- 페이지 유지정보 : end -->
	
	<form:hidden path="deptCode"/>
	<form:hidden path="condOrder"/>
	<form:hidden path="condAlign"/>
	<cmmUtil:ajaxToken name="_dt"/>
	
	<!-- 상세조회 : begin -->
	<div id="detail_search" class="section_detail_search staticHeightArea">
		<fieldset>
			<div class="item_row">
				<table>
					<colgroup>
						<col style="width:110px;"/>
                   		<col style="width:150px;"/>
                   		<col style="width:120px;"/>
                   		<col style="width:150px;"/>
                   		<col style="width:100px;"/>
                        <col />
					</colgroup>
					
					<tbody>
						<tr>
							<th>부서코드</th>
							<td><form:input path="condDeptCode" cssClass="_enter[Dept.search]" cssStyle="width: 90%;"/></td>
							<th>차상위 부서코드</th>
							<td><form:input path="condAtmbUpperDeptCode" cssClass="_enter[Dept.search]" cssStyle="width: 90%;"/></td>
							<th>부서명</th>
							<td><form:input path="condAllDeptNm" cssClass="_enter[Dept.search]" cssStyle="width: 90%;"/></td>
                        </tr>
                        <tr>
                        	<th>최상위 부서코드</th>
							<td><form:input path="condBestDeptCode" cssClass="_enter[Dept.search]" cssStyle="width: 90%;"/></td>
							<th>폐지여부</th>
							<td>
								<cmmCd:cdSelect name="condAblSe" id="condAblSe"
									codeId="CURG009"
									defaultCode="${deptVO.condAblSe }"
									showAll="true"
									use="true"/>
							</td>
                        </tr>
					</tbody>
				</table>
				
				<div class="tbl_search">
					<button type="button" id="search" class="gen_btn btn_srch_submit _command[Dept.search]"><spring:message code="button.search" /></button>
				</div>
			</div>	
		</fieldset>
	</div>
	<!-- 상세조회 : end -->
	
	<div id="contentBtnFix" class="button_area staticHeightArea">
		<div class="buttonSet left">
			<button type="button" class="gen_btn needCreate _command[Dept.createView]" accesskey="2"><spring:message code="button.new.create" /></button>
			<button type="button" class="gen_btn needCreate _command[Dept.batchCreateView]" accesskey="3">일괄등록</button>
			<button type="button" class="gen_btn needCreate _command[Dept.excelDownload]" accesskey="4"><spring:message code="button.exel" /></button>
		</div>
	</div>
</form:form>

<div id="data_list_area" class="type_list">
	<div id="listTableFix" class="list_table_header">
		<table class="header" cellspacing="0" cellpadding="0" summary="목록 항목입니다.">
			<colgroup>
				<col style="width:120px;"/>
				<col/>
				<col style="width:120px;"/>
				<col style="width:120px;"/>
				<col style="width:100px;"/>
			</colgroup>
			
			<thead>
				<tr>
					<th><a id="DEPT_CODE" class="_command[_listHeaderToggle|Dept.refreshList]">부서코드</a></th>
					<th class="separator"><a id="LOWEST_DEPT_NM" class="_command[_listHeaderToggle|Dept.refreshList]">부서명</a></th>
					<th class="separator"><a id="ATMB_UPPER_DEPT_CODE" class="_command[_listHeaderToggle|Dept.refreshList]">차상위 부서코드</a></th>
					<th class="separator"><a id="BEST_DEPT_CODE" class="_command[_listHeaderToggle|Dept.refreshList]">최상위 부서코드</a></th>
					<th class="separator"><a id="ABL_SE" class="_command[_listHeaderToggle|Dept.refreshList]">폐지구분</a></th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div id="table_list" class="list_table_box">
		<table cellspacing="0" cellpadding="0">
			<colgroup>
				<col style="width:120px;"/>
				<col/>
				<col style="width:120px;"/>
				<col style="width:120px;"/>
				<col style="width:82px;#width:100px;"/><!-- Header size보다 18px 작게 잡아야함. 스크롤 영역 (동적처리는 IE 미지원으로 고정값 설정) -->
			</colgroup>
			
			<tbody>
				<c:choose>
					<c:when test="${fn:length(deptList) <= 0 }">
						<tr>
							<td colspan="5"><spring:message code="info.nodata.msg" /></td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="deptListVO" items="${deptList }" varStatus="sttus">
							<tr>
								<td>${deptListVO.deptCode }&nbsp;</td>
								<td>
									<div class="ellipsis" title="<c:out value="${deptListVO.allDeptNm }" />">
										<a id="${deptListVO.deptCode }" class="_command[Dept.detailView]"><c:out value="${deptListVO.lowestDeptNm }" /></a>&nbsp;
									</div>
								</td>
								<td>${deptListVO.atmbUpperDeptCode }&nbsp;</td>
								<td>${deptListVO.bestDeptCode }&nbsp;</td>
								<td><cmmCd:cdValue code="${deptListVO.ablSe }" codeId="CURG009" /></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	
	<c:if test="${fn:length(deptList) > 0 && deptVO.pagingEnable eq '1' }">
		<div id="pagination" class="pagination">
			<div class="listPaging">
				<ui:pagination paginationInfo="${pagination }" type="image" jsFunction="Dept.fn_egov_link_page" />
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