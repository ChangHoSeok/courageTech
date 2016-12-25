<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="kr.pe.courage.tech.uat.login.service.LoginVO"%>
<%@page import="java.util.List"%>

<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		LoginSttus.TOTAL_CNT = '<c:out value="${pagination.totalRecordCount }" />';
		LoginSttus.formInitList();
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
	
	<form:form name="formLoginSttus" id="formLoginSttus" action="retrieveLoginSttusList.do" method="post">
		<cmmUtil:ajaxToken name="_dt"/>
		<div id="detail_search" class="section_detail_search staticHeightArea">
			<fieldset>
				<div class="tbl_search">
					<button type="button" id="search" class="gen_btn btn_srch_submit _command[LoginSttus.refreshList]">새로고침</button>
				</div>
			</fieldset>
		</div>
	</form:form>
	
	<div id="contentBtnFix" class="button_area staticHeightArea">
		<strong style="position: absolute; padding: 10px; color: #FF9436;">※. 로그인 현황 오차 (<cmmUtil:property key="system.session.timeout" /> 초)</strong>
	</div>
</div>

<div id="data_list_area" class="type_list">
	<div id="listTableFix" class="list_table_header">
		<table class="header" cellspacing="0" cellpadding="0" summary="목록 항목입니다.">
			<colgroup>
				<col />
				<col style="width: 200px;" />
				<col style="width: 180px;" />
				<col style="width: 180px;" />
				<col style="width: 200px;" />
			</colgroup>
			
			<thead>
				<tr>
					<th>사용자ID</th>
					<th class="separator">사용자명</th>
					<th class="separator">접속IP</th>
					<th class="separator">로그인 일시</th>
					<th class="separator">최근활동 일시</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div id="table_list" class="list_table_box">
		<table cellspacing="0" cellpadding="0">
			<colgroup>
				<col />
				<col style="width: 200px;" />
				<col style="width: 180px;" />
				<col style="width: 180px;" />
				<col style="width: 182px;#width:200px;" /><!-- Header size보다 18px 작게 잡아야함. 스크롤 영역 (동적처리는 IE 미지원으로 고정값 설정) -->
			</colgroup>
			
			<tbody>
				<c:choose>
					<c:when test="${fn:length(sessionList) <= 0 }">
						<tr>
							<td colspan="4"><spring:message code="info.nodata.msg" /></td>
						</tr>
					</c:when>
					<c:otherwise>
						<%
							List<HttpSession> sessionList = (List<HttpSession>) request.getAttribute("sessionList");
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						
							for (HttpSession httpSession : sessionList) {
						%>
								<tr>
									<td><%=httpSession.getAttribute(LoginVO.SESSION_USER_ID) %>&nbsp;</td>
									<td><%=httpSession.getAttribute(LoginVO.SESSION_USER_NM) %>&nbsp;</td>
									<td><%=httpSession.getAttribute(LoginVO.SESSION_USER_IP) %>&nbsp;</td>
									<td><%=sdf.format(httpSession.getCreationTime()) %>&nbsp;</td>
									<td><%=sdf.format(httpSession.getLastAccessedTime()) %>&nbsp;</td>
								</tr>
						<%
							}
						%>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />