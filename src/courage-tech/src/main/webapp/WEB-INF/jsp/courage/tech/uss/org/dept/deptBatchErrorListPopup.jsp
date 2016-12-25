<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		DeptPopup.formInitErrorList();
		DeptPopup.TOTAL_CNT = Number("${fn:length(errorList)}");
	});
//-->
</script>

<div class="popup_title_sm">
	<h1 id="progrmNm"></h1>
</div>

<div class="popup_contents">
	<form:form name="formDeptPopup" id="formDeptPopup" action="createDeptBatchProcPopup.do" method="post" commandName="deptBatchVO">
		
	</form:form>
	
	<div class="result_txt">
		총<span id="dataCnt"></span>건이 조회 되었습니다. (1,000 건 이상은 조회되지 않습니다.)
	</div>

	<div class="tbl_result_list">
		<table cellspacing="0" cellpadding="0">
			<colgroup>
				<col style="width: 50px;" />
				<col style="width: 50px;" />
				<col />
				<col style="width: 300px;" />
			</colgroup>
			
			<thead>
				<tr>
					<th>순번</th>
					<th>행번호</th>
					<th>오류내역</th>
					<th>데이터</th>
				</tr>
			</thead>
			
			<tbody>
				<c:set var="dataCnt" value="${0 }" />
				<c:choose>
					<c:when test="${fn:length(errorList) <= 0 }">
						<tr>
							<td colspan="4"><spring:message code="info.nodata.msg" /></td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="deptVO" items="${errorList }" varStatus="sttus">
							<c:forEach var="errorData" items="${deptVO.errors }" varStatus="errorSttus">
								<c:if test="${dataCnt <= 1000 }">
									<tr>
										<td><fmt:formatNumber value="${dataCnt + 1 }" /></td>
										<td><c:out value="${deptVO.rowNum }" />&nbsp;</td>
										<td class="Ltxt"><c:out value="${errorData.value }" />&nbsp;</td>
										<td class="Ltxt"><c:out value="${deptVO[errorData.key] }" /></td>
									</tr>
									
									<c:set var="dataCnt" value="${dataCnt + 1 }" />
								</c:if>
							</c:forEach>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
</div>

<script type="text/javascript">
<!--
	// 데이터 수 표시
	$jquery("#dataCnt").html(getCommaMoney("${dataCnt }"));
//-->
</script>

<div class="popup_bottom">
	<div class="popup_btnGroup">
		<button type="button" class="gen_btn _command[DeptPopup.excelDownload]" accesskey="1"><spring:message code="button.exel" /></button>
		<button type="button" class="gen_btn _command[DeptPopup.popupClose]" accesskey="0">닫기</button>
	</div>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />