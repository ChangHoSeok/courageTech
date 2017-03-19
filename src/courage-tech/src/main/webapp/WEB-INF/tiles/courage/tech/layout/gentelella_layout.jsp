<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
<%@include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<!-- Meta, title, CSS, favicons, etc. -->
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title><spring:message code="courage.tech.title" /></title>
	
	<tiles:insertAttribute name="resourcesCSS" />
	<tiles:insertAttribute name="resourcesJS" />
	
	<link href="${pageContext.request.contextPath}/index.ico" rel="shortcut icon" type="image/x-icon" />
</head>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<!-- left area -->
			<div class="col-md-3 left_col footer_fixed">
				<tiles:insertAttribute name="leftArea" />
			</div>
			
			<!-- top navigation -->
        	<div class="top_nav">
        		<tiles:insertAttribute name="topArea" />
        	</div>
        	
        	<!-- page content -->
        	<div class="right_col" role="main">
        		<tiles:insertAttribute name="contentArea" />
        	</div>
        	
        	<!-- footer content -->
        	<footer>
        		<tiles:insertAttribute name="footerArea" />
        	</footer>
		</div>
	</div>
	
	<!-- template 관련 공통 모듈 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/vendors/gentelella-master/js/custom_courage.js"></script>
</body>