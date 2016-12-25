<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript" src="${ctxPath}/dwr/interface/userService.js"></script>
<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		$jquery("#emailDomainSelect").on("change", function() {
			UserSbscrb.setEmailDomain(this, "emailDomain");
		});
		
		$jquery("#" + UserSbscrb.FORM_ID + " .idCheck").on("focusout", function() {
			var emailId = $jquery("#" + UserSbscrb.FORM_ID + " #emailId").val();
			var emailDomain = $jquery("#" + UserSbscrb.FORM_ID + " #emailDomain").val();

			if (!isEmpty(emailId) && !isEmpty(emailDomain)) {
				UserSbscrb.duplicateCheck(emailId + "@" + emailDomain);
			}
		});
		
		$jquery("#" + UserSbscrb.FORM_ID + " #emailId").focus();
	});
//-->
</script>

<div id="loginForm">
	<form:form name="formUser" id="formUser" action="${ctxPath }/system/user/createUserSbscrb.tech" method="post" commandName="userVO">
		<input type="hidden" name="emplyrId" value="dummy">
		
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
				<p>
					<span style="display: block; margin: 5px; font-weight: bold; color: #969696;">아이디</span>
					<form:input path="emailId" placeholder="이메일 아이디" cssStyle="width: 137px;" cssClass="idCheck validate[required,custom[email1]]" maxlength="50"/>&nbsp;@&nbsp;
					<form:input path="emailDomain" placeholder="이메일 도메인" cssStyle="width: 137px;" cssClass="idCheck validate[required,custom[email2]]" maxlength="50"/>
					<cmmCd:cdSelect codeId="CURG004" name="emailDomainSelect" styleClass="idCheck" style="height: 34px;" use="true"/>
					<span id="idValidMsg" style="display: none; color: #B32C2C; padding: 5px; font-style: italic;">사용할 수 없는 아이디 입니다.</span>
				</p>
				<p>
					<span style="display: block; margin: 5px; font-weight: bold; color: #969696;">닉네임</span>
					<form:input path="userNm" placeholder="닉네임" cssClass="validate[required]" maxlength="50"/>
				</p>
				<p>
					<span style="display: block; margin: 5px; font-weight: bold; color: #969696;">비밀번호</span>
					<input type="password" name="password" id="password" value="" class="validate[required,custom[password]]" maxlength="30" placeholder="비밀번호">
				</p>
				<p>
					<span style="display: block; margin: 5px; font-weight: bold; color: #969696;">비밀번호 확인</span>
					<input type="password" name="passwordConfirm" id="passwordConfirm" value="" class="validate[required,custom[password],equals[password]]" maxlength="30" placeholder="비밀번호 확인">
				</p>
				
				<p class="submit" style="margin: 40px 0 0; text-align: center;">
					<input type="submit" name="sbscrb" value="회원가입" class="_command[UserSbscrb.sbscrbSave]">
				</p>
			</div>
		</div>
		
	</form:form>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />