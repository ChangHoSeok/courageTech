<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript" charset="utf-8" src="${ctxPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/courage/tech/das/log/logFileRceptPopup.js"></script>
<script type="text/javascript">
<!--
	$jquery(document).ready(function () {
		if (!isEmpty('<c:out value="${param.openerKey }" />')) {
			LogFileRceptPopup.OPENER_KEY = '<c:out value="${param.openerKey }" />';
		}
		
		if (!isEmpty('<c:out value="${param.returnFunction }" />')) {
			LogFileRceptPopup.CALLBACK_FUNCTION = '<c:out value="${param.returnFunction }" />';
		}
		
		$jquery(window).hashchange();
		
		if (location.hash == "") {
			<c:choose>
				<c:when test="${param.mode eq 'create'}">
					location.hash = "AC=/das/log/createLogFileRceptPopup.tech&VA=content_body";
				</c:when>
				<c:when test="${param.mode eq 'detail'}">
					location.hash = "AC=/das/log/retrieveLogFileRceptDetailPopup.tech&VA=content_body&groupId=${param.groupId }&rceptId=${param.rceptId }";
				</c:when>
			</c:choose>
		}
	});
//-->
</script>
<div id="content_body">
	
</div>