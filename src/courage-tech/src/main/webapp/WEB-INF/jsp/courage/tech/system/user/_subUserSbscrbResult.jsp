<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>
<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		$jquery("#dialog-userSbscrb").dialog({
			height: 400
		});
	});
//-->
</script>
<div id="loginForm">
	<form:form name="formUser" id="formUser" action="${ctxPath }/system/user/createUserSbscrb.tech" method="post" commandName="userVO">
		
		<c:if test="${validateCheck eq 'error' }">
			<div id="errorBox" class="errormsgbox staticHeightArea">
				<form:errors path="emplyrId"/><br />
				<form:errors path="userNm"/><br />
				<form:errors path="emailId"/><br />
				<form:errors path="emailDomain"/><br />
				<form:errors path="password"/>
			</div>
		</c:if>
		
		<div class="container">
			<div class="login">
				<p style="text-align: center; font-size: 1.3em; font-weight: bold; color: #676767;">
					감사합니다.<br />회원가입이 완료되었습니다
				</p>
				
				<p class="submit" style="margin: 40px 0 0; text-align: center;">
					<input type="submit" name="sbscrb" value="로그인" class="_command[UserSbscrb.showLoginPopupForm]">
				</p>
			</div>
		</div>
		
	</form:form>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />