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
<script type="text/javascript" charset="utf-8" src="${ctxPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/courage/tech/uat/login/login.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctxPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/courage/tech/uss/mpe/myPage.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctxPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/courage/tech/system/user/userSbscrb.js"></script>
<script type="text/javascript">
<!--
	$jquery("#scrollMenu").ready(function() {
		LeftMenu.menuInit();
		MyPage.avatarInit();
		Login.loginPopupFormInit();
		UserSbscrb.userSbscrlPopupFormInit();
		
		$jquery("#authorList").on("change", function() {
			$jquery("#authorId").val($jquery(this).val());
			document.authorForm.submit();
		});
	});
//-->
</script>

<div class="subMenuArea">
	<!-- 로그인 정보 : begin -->
	<div id="leftBtnFix">
		<div id="userPhoto">
			<a href="#" class="${!empty sessionScope['userID'] ? '_command[MyPage.showAvatar]' : '' }">
				<c:set var="avatarUrl" value="${ctxPath }/images/courage/tech/icon/feeling/anonymous.png" />
				<c:if test="${!empty sessionScope[sessionKey].avatarUrl }">
					<c:set var="avatarUrl" value="${sessionScope[sessionKey].avatarUrl }" />
				</c:if>
				
				<img class="rotate" alt="사진" src="${avatarUrl }">
			</a>
		</div>
		
		<c:choose>
			<c:when test="${sessionScope['authorCode'] eq authorAnonymous }">
				<div id="userInfo">
					<p style="color: #717171">Anonymous</p>
					<p>환영합니다.</p>
				</div>
				<div id="authList">
					<button type="button" id="join" class="btn_srch_submit _command[UserSbscrb.showUserSbscrbForm]" style="width: 45%; float: left;">회원가입</button>
					<button type="button" id="login" class="btn_srch_submit _command[Login.showLoginPopupForm]" style="width: 45%; float: right;">로그인</button>
				</div>
			</c:when>
			<c:otherwise>
				<div id="userInfo">
					<p style="color: #717171">${sessionScope['userNm'] }</p>
					<p>환영합니다.</p>
				</div>
				<div id="userAction">
					<img title="설정" alt="설정" src="${ctxPath }/images/courage/tech/icon/gear.png" class="_command[LeftMenu.myInfo]">&nbsp;
					<img title="로그아웃" alt="로그아웃" src="${ctxPath }/images/courage/tech/icon/exit.png" class="_command[LeftMenu.logout]">
				</div>
				<div id="authList">
					<form name="authorForm" id="authorForm" action="${ctxPath }/uat/login/loginUserAuthorChange.tech" method="post">
						<input type="hidden" name="authorId" id="authorId">
						
						<select name="authorList" id="authorList" style="width: 100%;">
							<c:forEach var="author" items="${sessionScope['authorList'] }">
								<option value="${author.authorCode }" ${sessionScope['authorCode'] eq author.authorCode ? 'selected="selected"' : '' }>${author.authorNm}</option>
							</c:forEach>
						</select>
					</form>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<!-- 로그인 정보 : end -->
	
	<div id="scrollMenu" class="sectionArea fixLeft scrl">
		<ul id="accrodionMenu" class="topnav"></ul>
	</div>
	
	<div id="dialog-userIcons" title="사용자 아이콘 설정" style="display: none;"></div>
	<div id="dialog-login" title="Login to Courage Tech" style="display: none;"></div>
	<div id="dialog-userSbscrb" title="회원가입" style="display: none;"></div>
</div>
