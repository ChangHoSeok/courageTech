<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<div id="loginForm">
	<form:form name="formUser" id="formUser" action="${ctxPath }/system/user/createUserSbscrb.tech" method="post" commandName="userVO">
		<div class="container">
			<div class="login">

				<p><span style="margin: 10px 0; font-size: 1.2em; font-weight: bold;">개인정보처리방침</span></p>
				
				<div style="border: 1px solid #BBBBBB; width: 100%; height: 200px; margin-top: 20px; overflow: auto; -webkit-overflow-scrolling: touch;">
					<iframe name="sbscrbStplat" id="sbscrbStplat" src="${ctxPath }/html/privacy.html" scrolling="yes" frameborder="0" style="width: 100%; height: 100%;"></iframe>
				</div>
				
				<p class="submit" style="margin: 40px 0 0; text-align: center;">
					<input type="submit" name="agre" value="동  의" class="_command[UserSbscrb.sbscrbAgre]">
					<input type="submit" name="disagre" style="margin-left: 15px;" value="비동의" class="_command[UserSbscrb.sbscrbAgre]">
				</p>
			</div>
		</div>
		
	</form:form>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />