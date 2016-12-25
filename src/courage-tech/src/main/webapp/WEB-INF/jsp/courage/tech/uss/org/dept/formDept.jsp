<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/courage/tech/uss/org/dept/dept.js"></script>
<script type="text/javascript">
<!--
	$jquery(document).ready(function () {
		$jquery(window).hashchange();
		
		var url = location.hash;
		Dept.MENU_NO = "${param.menuNo }";
		
		if (url == "") {
			location.hash = "AC=/uss/org/dept/retrieveDeptTabView.tech&VA=content_body";
		}
	});
//-->
</script>
<div id="content_body"></div>