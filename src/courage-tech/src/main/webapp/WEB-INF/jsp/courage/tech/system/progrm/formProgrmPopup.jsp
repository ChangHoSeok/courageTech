<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript" charset="utf-8" src="${ctxPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/courage/tech/system/progrm/progrmPopup.js"></script>
<script type="text/javascript">
<!--
	$jquery(document).ready(function () {
		if (!isEmpty('<c:out value="${param.openerKey }" />')) {
			ProgrmPopup.OPENER_KEY = '<c:out value="${param.openerKey }" />';
		}
		
		if (!isEmpty('<c:out value="${param.returnFunction }" />')) {
			ProgrmPopup.CALLBACK_FUNCTION = '<c:out value="${param.returnFunction }" />';
		}
		
		if (!isEmpty('${param.disableProgrmFileNm }')) {
			ProgrmPopup.DISABLE_PROGRM_FILE_NM = '<c:out value="${param.disableProgrmFileNm }" />';
		}
		
		$jquery(window).hashchange();
		
		if (location.hash == "") {
			location.hash = "AC=/system/progrm/retrieveProgrmListPopup.tech&VA=content_body";
		}
	});
//-->
</script>
<div id="content_body"></div>