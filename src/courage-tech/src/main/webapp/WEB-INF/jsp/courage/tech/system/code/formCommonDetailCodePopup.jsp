<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript" charset="utf-8" src="${ctxPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/courage/tech/system/code/commonDetailCode.js"></script>
<script type="text/javascript" src="${ctxPath}/dwr/interface/cmmnDetailCodeManageService.js"></script>
<script type="text/javascript">
<!--
	$jquery(document).ready(function () {
		if (!isEmpty('<c:out value="${param.openerKey }" />')) {
			CommonDetailCode.OPENER_KEY = '<c:out value="${param.openerKey }" />';
		}
		
		if (!isEmpty('<c:out value="${param.returnFunction }" />')) {
			CommonDetailCode.CALLBACK_FUNCTION = '<c:out value="${param.returnFunction }" />';
		}
		
		$jquery(window).hashchange();
		
		if (location.hash == "") {
			<c:choose>
				<c:when test="${param.mode eq 'create'}">
					location.hash = "AC=/system/code/createCmmnDetailCode.tech&VA=content_body&codeId=${param.codeId}";
				</c:when>
				<c:when test="${param.mode eq 'detail'}">
					location.hash = "AC=/system/code/retrieveCmmnDetailCodeDetail.tech&VA=content_body&code=${param.code}&codeId=${param.codeId}";
				</c:when>
			</c:choose>
		}
	});
//-->
</script>
<div id="content_body">
	
</div>