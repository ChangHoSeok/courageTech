<%@page import="kr.pe.courage.common.prop.PropertiesMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<%
	//SessionKey 설정
	pageContext.setAttribute("sessionKey", PropertiesMap.getInstance().getValue("system.session.key"));
	pageContext.setAttribute("authorAnonymous", PropertiesMap.getInstance().getValue("system.author.anonymous"));
%>

<script type="text/javascript" charset="utf-8" src="${ctxPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/courage/tech/leftMenu.js"></script>
<script type="text/javascript">
<!--
	$(document).ready(function() {
		LeftMenu.menuInit();
	});
//-->
</script>
<div class="left_col scroll-view">
	<div class="navbar nav_title" style="border: 0;">
		<a href="index.html" class="site_title"><i class="fa fa-code"></i><span>Courage Tech</span></a>
	</div>

	<div class="clearfix"></div>

	<!-- menu profile quick info -->
	<div class="profile clearfix">
		<div class="profile_pic">
			<a href="#" class="${!empty sessionScope['userID'] ? '_command[MyPage.showAvatar]' : '' }">
				<c:set var="avatarUrl" value="${ctxPath }/images/courage/tech/icon/feeling/anonymous.png" />
				<c:if test="${!empty sessionScope[sessionKey].avatarUrl }">
					<c:set var="avatarUrl" value="${sessionScope[sessionKey].avatarUrl }" />
				</c:if>
				
				<img alt="사진" src="${avatarUrl }" class="img-circle profile_img">
			</a>
		</div>
		<c:choose>
			<c:when test="${sessionScope['authorCode'] eq authorAnonymous }">
				<div class="profile_info">
					<span>Welcome,</span>
					<h2>Anonymous</h2>
				</div>
			</c:when>
			<c:otherwise>
				<div class="profile_info">
					<span>Welcome,</span>
					<h2>${sessionScope['userNm'] }</h2>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<!-- /menu profile quick info -->

	<br />
	
	<!-- sidebar menu -->
	<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
		<div class="menu_section">
			<ul id="accrodionMenu" class="nav side-menu"></ul>
		</div>
	</div>
	<!-- /sidebar menu -->

	<!-- /menu footer buttons -->
	<!-- 
	<div class="sidebar-footer hidden-small">
		<a data-toggle="tooltip" data-placement="top" title="Settings">
			<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
		</a>
		<a data-toggle="tooltip" data-placement="top" title="FullScreen">
			<span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
		</a>
		<a data-toggle="tooltip" data-placement="top" title="Lock">
			<span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
		</a>
		<a data-toggle="tooltip" data-placement="top" title="Logout" href="login.html">
			<span class="glyphicon glyphicon-off" aria-hidden="true"></span>
		</a>
	</div>
	 -->
	<!-- /menu footer buttons -->
</div>