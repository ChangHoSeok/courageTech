<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/courage/tech/cop/bbs/answer.js"></script>
<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		Answer.formInit();
	});
//-->
</script>

<%-- 댓글 목록 영역 --%>
<div id="answerList"></div>

<%-- 댓글 등록 영역 --%>
<div id="answerCreateForm"></div>