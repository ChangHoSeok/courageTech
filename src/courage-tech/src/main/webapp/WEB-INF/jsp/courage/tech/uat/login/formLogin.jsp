<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp"%>

<link href="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.css.basePath']" />/courage/tech/loginStyle.css" rel="stylesheet" type="text/css" media="screen" />

<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/courage/tech/uat/login/login.js"></script>
<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		$jquery(window).hashchange();
		Login.formInit();
	});
//-->
</script>

<form:form name="formLogin" id="formLogin" action="${ctxPath }/uat/login/userLogin.tech" method="post" commandName="loginVO">
	<form:hidden path="enfrcLogin"/>
	
	<section class="errorArea">
		<c:if test="${validateCheck eq 'error' }">
			<div id="errorBox" class="errormsgbox staticHeightArea">
				<form:errors path="emplyrId"/> <br />
				<form:errors path="password"/>
			</div>
		</c:if>
	</section>
	
	<section class="container">
		<div class="login">
			<h1>Login to Courage Tech</h1>
			
			<p><form:input path="emplyrId" placeholder="Username" cssClass="validate[required]" maxlength="50"/></p>
			<p><input type="password" name="password" id="password" value="" class="validate[required]" maxlength="30" placeholder="Password"></p>
			
			<p class="remember_me">
				<label>
					<input type="checkbox" name="rememberMe" id="rememberMe" value="true" ${loginVO.rememberMe ? 'checked="checked"' : '' }> 아이디 기억하기
				</label>
			</p>
			
			<p class="submit">
				<input type="submit" name="login" id="form" value="Login" class="_command[Login.onLogin]">
			</p>
			
			<p class="etcBtn">
				<input type="submit" name="home" value="Home" class="_command[Login.goHome]">
			</p>
		</div>
	</section>
</form:form>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />