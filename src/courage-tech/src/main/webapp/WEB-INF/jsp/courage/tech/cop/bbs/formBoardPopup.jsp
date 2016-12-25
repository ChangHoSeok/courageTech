<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/courage/tech/cop/bbs/boardPopup.js"></script>
<script type="text/javascript">
<!--
	$jquery(document).ready(function () {
		if (!isEmpty('<c:out value="${param.openerKey }" />')) {
			BoardPopup.OPENER_KEY = '<c:out value="${param.openerKey }" />';
		}
		
		if (!isEmpty('<c:out value="${param.returnFunction }" />')) {
			BoardPopup.CALLBACK_FUNCTION = '<c:out value="${param.returnFunction }" />';
		}
		
		$jquery(window).hashchange();
		
		if (location.hash == "") {
			if (isEmpty("${param.nttId}")) {
				location.hash = "AC=/cop/bbs/retrieveBoardList.tech&VA=content_body&viewTy=popup&bbsId=${param.bbsId}";
			} else {
				location.hash = "AC=/cop/bbs/retrieveBoardDetail.tech&VA=content_body&viewTy=popup&bbsId=${param.bbsId}&nttId=${param.nttId}";
			}
		}
	});
//-->
</script>
<div id="content_body"></div>