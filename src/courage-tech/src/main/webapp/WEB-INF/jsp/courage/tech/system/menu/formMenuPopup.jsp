<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript" charset="utf-8" src="${ctxPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/courage/tech/system/menu/menuPopup.js"></script>
<script type="text/javascript">
<!--
	$jquery(document).ready(function () {
		if (!isEmpty('<c:out value="${param.openerKey }" />')) {
			MenuPopup.OPENER_KEY = '<c:out value="${param.openerKey }" />';
		}
		
		if (!isEmpty('<c:out value="${param.returnFunction }" />')) {
			MenuPopup.CALLBACK_FUNCTION = '<c:out value="${param.returnFunction }" />';
		}
		
		MenuPopup.SUB_MENU_ID = '<c:out value="${param.subMenuId }" />';
		$jquery(window).hashchange();
		
		if (location.hash == "") {
			location.hash = "AC=/system/menu/retrieveMenuListPopup.tech&VA=content_body";
		}
	});
//-->
</script>
<div id="content_body">
	
</div>