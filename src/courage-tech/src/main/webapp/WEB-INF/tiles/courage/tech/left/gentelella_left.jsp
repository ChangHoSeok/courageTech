<%@page import="kr.pe.courage.common.prop.PropertiesMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<%
	//SessionKey 설정
	pageContext.setAttribute("sessionKey", PropertiesMap.getInstance().getValue("system.session.key"));
	pageContext.setAttribute("authorAnonymous", PropertiesMap.getInstance().getValue("system.author.anonymous"));
%>

<div class="left_col scroll-view">
	<div class="navbar nav_title" style="border: 0;">
		<a href="${ctxPath }" class="site_title"><i class="fa fa-code"></i><span>Courage Tech</span></a>
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
		
		<div class="clearfix"></div>
		
		<c:if test="${sessionScope['authorCode'] ne authorAnonymous }">
			<div style="padding: 10px;">
				<form name="authorForm" id="authorForm" action="${ctxPath }/uat/login/loginUserAuthorChange.tech" method="post">
					<input type="hidden" name="authorId" id="authorId">
					
					<select name="authorList" id="authorList" class="form-control">
						<c:forEach var="author" items="${sessionScope['authorList'] }">
							<option value="${author.authorCode }" ${sessionScope['authorCode'] eq author.authorCode ? 'selected="selected"' : '' }>${author.authorNm}</option>
						</c:forEach>
					</select>
				</form>
			</div>
		</c:if>
	</div>
	<!-- /menu profile quick info -->

	<br />
	
	<!-- sidebar menu -->
	<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
		<div class="menu_section">
			<h3>접근메뉴</h3>
			<ul id="accrodionMenu" class="nav side-menu"></ul>
		</div>
	</div>
	<!-- /sidebar menu -->
</div>

<script type="text/javascript" charset="utf-8" src="${ctxPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/courage/tech/leftMenu.js"></script>
<script type="text/javascript">
<!--
	$(document).ready(function() {
		LeftMenu.menuInit();
		
		$("#authorList").on("change", function() {
			$("#authorId").val($(this).val());
			document.authorForm.submit();
		});
	});
//-->
</script>