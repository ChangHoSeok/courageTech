<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@page import="kr.pe.courage.common.prop.PropertiesMap"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<%
	//SessionKey 설정
	pageContext.setAttribute("sessionKey", PropertiesMap.getInstance().getValue("system.session.key"));
	pageContext.setAttribute("authorAnonymous", PropertiesMap.getInstance().getValue("system.author.anonymous"));
%>

<script type="text/javascript">
<!--
		
//-->
</script>

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
					
					<span class=" fa fa-angle-down"></span>
				</a>
				
				<ul class="dropdown-menu dropdown-usermenu pull-right">
					<li><a href="javascript:;"> Profile</a></li>
					<li>
						<a href="javascript:;">
							<span class="badge bg-red pull-right">50%</span>
							<span>Settings</span>
						</a>
					</li>
					<li><a href="javascript:;">Help</a></li>
					<li><a href="login.html"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
				</ul>
			</li>

			<li role="presentation" class="dropdown">
				<a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" aria-expanded="false">
					<i class="fa fa-envelope-o"></i>
					<span class="badge bg-green">6</span>
				</a>
				
				<ul id="menu1" class="dropdown-menu list-unstyled msg_list" role="menu">
					<li>
						<a>
							<span class="image"><img src="${ctxPath }/images/courage/tech/icon/feeling/anonymous.png" alt="Profile Image" /></span>
							<span>
								<span>John Smith</span>
								<span class="time">3 mins ago</span>
							</span>
							
							<span class="message"> Film festivals used to be do-or-die moments for movie makers. They were where... </span>
						</a>
					</li>
					<li>
						<div class="text-center">
							<a>
								<strong>See All Alerts</strong>
								<i class="fa fa-angle-right"></i>
							</a>
						</div>
					</li>
				</ul>
			</li>
		</ul>
	</nav>
</div>
