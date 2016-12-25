<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>
<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		$jquery("#" + UserSbscrb.CONFIRM_FORM_ID + " #password").focus();
	});
//-->
</script>
<div id="loginForm">
	<form:form name="formUserConfirm" id="formUserConfirm" action="${ctxPath }/system/user/retrieveUserConfirm.tech" method="post" commandName="userVO">
		
		<div class="container">
			<div class="login">
				<p>
					<span style="display: block; margin: 5px; font-weight: bold; color: #969696;">비밀번호</span>
					<input type="password" name="password" id="password" value="" class="required_textbox validate[required,custom[password]]" maxlength="30" placeholder="비밀번호">
				</p>
				
				<p class="submit" style="margin: 40px 0 0; text-align: center;">
					<input type="submit" name="sbscrb" value="사용자 인증" class="_command[UserSbscrb.userConfirm]">
				</p>
			</div>
		</div>
		
	</form:form>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />