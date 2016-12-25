<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript" charset="utf-8" src="${ctxPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/courage/tech/uss/org/dept/deptPopup.js"></script>
<script type="text/javascript">
<!--
	$jquery(document).ready(function () {
		if (!isEmpty('<c:out value="${param.openerKey }" />')) {
			DeptPopup.OPENER_KEY = '<c:out value="${param.openerKey }" />';
		}
		
		if (!isEmpty('<c:out value="${param.returnFunction }" />')) {
			DeptPopup.CALLBACK_FUNCTION = '<c:out value="${param.returnFunction }" />';
		}
		
		$jquery(window).hashchange();
		
		if (location.hash == "") {
			if ("${param.mode}" == "treeView") {
				location.hash = "AC=/uss/org/dept/retrieveDeptPopup.tech&VA=content_body";
			} else if ("${param.mode}" == "createBatchView") {
				location.hash = "AC=/uss/org/dept/createDeptBatchPopup.tech&VA=content_body";
			}
		}
	});
//-->
</script>
<div id="content_body">
	
</div>