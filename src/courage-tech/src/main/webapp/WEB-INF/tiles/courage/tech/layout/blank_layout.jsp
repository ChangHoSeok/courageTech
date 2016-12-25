<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
<%@include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Expires" content="1" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<link href="${pageContext.request.contextPath}/index.ico" rel="shortcut icon" type="image/x-icon" />

<title><spring:message code="courage.tech.title" /></title>
</head>

<body>
<tiles:insertAttribute name="body" />

<!-- dialog:Begin -->
<div id="dialog-message" title="Message"></div>

<div id="dialog-confirm" title="Confirm"></div>
<!-- dialog:End -->
</body>
</html>