<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resource/ckeditor4/ckeditor.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/courage/tech/cop/bbs/board.js"></script>
<script type="text/javascript">
<!--
	$jquery(document).ready(function () {
		$jquery(window).hashchange();
		
		var url = location.hash;
		Board.MENU_NO = "${param.menuNo }";
		
		if (url == "") {
			if (isEmpty("${param.nttId}")) {
				location.hash = "AC=/cop/bbs/retrieveBoardList.tech&VA=content_body&bbsId=${param.bbsId}";
			} else {
				location.hash = "AC=/cop/bbs/retrieveBoardContents.tech&VA=content_body&bbsId=${param.bbsId}&nttId=${param.nttId}";
			}
		}
	});
//-->
</script>
<div id="content_body"></div>