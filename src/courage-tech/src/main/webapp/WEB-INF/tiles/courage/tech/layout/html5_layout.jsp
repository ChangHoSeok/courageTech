<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
<%@include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>
<!DOCTYPE html>

<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="ko"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="ko"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9" lang="ko"> <![endif]-->
<!--[if gt IE 8]><!--> <html lang="ko"> <!--<![endif]-->

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Expires" content="1" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<link href="${pageContext.request.contextPath}/index.ico" rel="shortcut icon" type="image/x-icon" />

<tiles:insertAttribute name="resource" />
<title><spring:message code="courage.tech.title" /></title>

<!--[if lt IE 9]><script src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/html5.js"></script><![endif]-->

</head>

<body>
	<tiles:insertAttribute name="body" />
	
	<!-- dialog:Begin -->
	<div id="dialog-message" title="Message"></div>
	
	<div id="dialog-confirm" title="Confirm"></div>
	<!-- dialog:End -->
</body>
</html>