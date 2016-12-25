<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript" charset="utf-8" src="${ctxPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/courage/tech/system/userauth/userAuthPopup.js"></script>
<script type="text/javascript">
<!--
	$jquery(document).ready(function () {
		if (!isEmpty('<c:out value="${param.openerKey }" />')) {
			UserAuthPopup.OPENER_KEY = '<c:out value="${param.openerKey }" />';
		}
		
		if (!isEmpty('<c:out value="${param.returnFunction }" />')) {
			UserAuthPopup.CALLBACK_FUNCTION = '<c:out value="${param.returnFunction }" />';
		}
		
		if (!isEmpty('${param.disableProgrmFileNm }')) {
			UserAuthPopup.DISABLE_PROGRM_FILE_NM = '<c:out value="${param.disableProgrmFileNm }" />';
		}
		
		$jquery(window).hashchange();
		
		if (location.hash == "") {
			location.hash = "AC=/system/userauth/createUserAuthPopup.tech&VA=content_body&esntlId=<c:out value="${param.esntlId }" />";
		}
	});
//-->
</script>
<div id="content_body">
	
</div>