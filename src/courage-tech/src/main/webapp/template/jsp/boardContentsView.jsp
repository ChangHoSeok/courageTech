<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
<%@include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>
<!DOCTYPE html>

<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="ko"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="ko"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9" lang="ko"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="ko"> <!--<![endif]-->
<%
	// 외부 컨텐츠 삽입시 XSS 체크하지 않도록 설정
	response.addHeader("X-XSS-Protection", "0");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Expires" content="1" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<link href="${ctxPath}/index.ico" rel="shortcut icon" type="image/x-icon" />

<title></title>

<!--[if lt IE 9]><script src="${ctxPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/html5.js"></script><![endif]-->
<link href="${ctxPath}/resource/ckeditor4/contents.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${ctxPath}/resource/ckeditor4/plugins/codesnippet/lib/highlight/styles/monokai_sublime.css" rel="stylesheet" type="text/css" media="screen" />

<script type="text/javascript" charset="utf-8" src="${ctxPath}/resource/ckeditor4/plugins/codesnippet/lib/highlight/highlight.pack.js"></script>
</head>

<body class="cke_editable cke_contents_ltr">
	${param.nttCn }
</body>

<script type="text/javascript">
<!--
	hljs.initHighlightingOnLoad();
//-->
</script>
</html>