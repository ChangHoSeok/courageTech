<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/courage/tech/system/progrm/progrm.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/dwr/interface/progrmService.js"></script>
<script type="text/javascript">
<!--
	$jquery(document).ready(function () {
		$jquery(window).hashchange();
		
		var url = location.hash;
		Progrm.MENU_NO = "${param.menuNo }";
		
		if (url == "") {
			location.hash = "AC=/system/progrm/retrieveProgrmList.tech&VA=content_body";
		}
	});
//-->
</script>
<div id="content_body">
	
</div>