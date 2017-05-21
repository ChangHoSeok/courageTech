<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@page import="kr.pe.courage.common.prop.PropertiesMap"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<%
	//SessionKey 설정
	pageContext.setAttribute("sessionKey", PropertiesMap.getInstance().getValue("system.session.key"));
	pageContext.setAttribute("authorAnonymous", PropertiesMap.getInstance().getValue("system.author.anonymous"));
%>

<div class="nav_menu">
	<nav>
		<div class="nav toggle">
			<a id="menu_toggle"><i class="fa fa-bars"></i></a>
		</div>

		<ul class="nav navbar-nav navbar-right">
			<li class="">
				<a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
					<c:set var="avatarUrl" value="${ctxPath }/images/courage/tech/icon/feeling/anonymous.png" />
					<c:if test="${!empty sessionScope[sessionKey].avatarUrl }">
						<c:set var="avatarUrl" value="${sessionScope[sessionKey].avatarUrl }" />
					</c:if>
					
					<c:choose>
						<c:when test="${sessionScope['authorCode'] eq authorAnonymous }">
							<img alt="사진" src="${avatarUrl }">Anonymous
						</c:when>
						<c:otherwise>
							<img alt="사진" src="${avatarUrl }">${sessionScope['userNm'] }
						</c:otherwise>
					</c:choose>
					
					<span class="fa fa-angle-down"></span>
				</a>
				
				<ul class="dropdown-menu dropdown-usermenu pull-right">
					<c:choose>
						<c:when test="${sessionScope['authorCode'] eq authorAnonymous }">
							<li>
								<a href="${ctxPath }/system/user/retrieveUserSbscrbAgre.tech" data-toggle="modal" data-target="#dialog-userSbscrb"><i class="fa fa-user pull-right"></i>회원가입</a>
							</li>
							<li>
								<a href="${ctxPath }/uat/login/subFormLogin.tech" data-toggle="modal" data-target="#dialog-login"><i class="fa fa-sign-in pull-right"></i>Log In</a>
							</li>
						</c:when>
						<c:otherwise>
							<li><a data-toggle="modal" href="${ctxPath }/system/user/retrieveUserSbscrbDetail.tech" data-target="#dialog-profile"><i class="fa fa-cog pull-right"></i>Profile</a></li>
							<li><a href="#" class="_command[LeftMenu.logout]"><i class="fa fa-sign-out pull-right"></i>Log Out</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</li>
		</ul>
	</nav>
</div>

<!-- loginModal -->
<div id="dialog-login" class="modal fade" role="dialog" aria-labelledby="Login to CourageTech" data-backdrop="static" aria-hidden="true">
	<div class="modal-dialog modal-sm"><div class="modal-content"></div></div>
</div>

<!-- joinModal -->
<div id="dialog-userSbscrb" class="modal fade" role="dialog" aria-labelledby="Join" data-backdrop="static" aria-hidden="true">
	<div class="modal-dialog"><div class="modal-content"></div></div>
</div>

<!-- userProfile -->
<div id="dialog-profile" class="modal fade" role="dialog" aria-labelledby="User Profile" data-backdrop="static" aria-hidden="true">
	<div class="modal-dialog"><div class="modal-content"></div></div>
</div>

<!-- userConfirm -->
<div id="dialog-userConfirm" class="modal fade fadeup" role="dialog" aria-labelledby="User Confirm" data-backdrop="static" aria-hidden="true">
	<div class="modal-dialog"><div class="modal-content"></div></div>
</div>

<script type="text/javascript" charset="utf-8" src="${ctxPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/courage/tech/system/user/userSbscrb.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctxPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/courage/tech/uat/login/login.js"></script>