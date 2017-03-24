<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<div class="modal-dialog">
	<form:form name="formUser" id="formUser" cssClass="form-horizontal" action="${ctxPath }/system/user/createUserSbscrb.tech" method="post" commandName="userVO">
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
		
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">회원가입</h4>
			</div>
			
			<div class="modal-body">
				<span class="section">회원가입 정보입력</span>
				
				<div class="form-group">
					<label for="emailId" class="col-sm-2 control-label">아이디</label>
					<div class="col-sm-4">
						<form:input path="emailId" placeholder="이메일 아이디" cssClass="form-control idCheck validate[required,custom[email1]]" maxlength="50"/>
					</div>
					<div class="col-sm-4">
						<form:input path="emailId" placeholder="이메일 아이디" cssClass="form-control idCheck validate[required,custom[email1]]" maxlength="50"/>
					</div>
					<div class="col-sm-2">
						<form:input path="emailId" placeholder="이메일 아이디" cssClass="form-control idCheck validate[required,custom[email1]]" maxlength="50"/>
					</div>
				</div>
				<div class="form-group">
					<label for="emplyrId">닉네임</label>
					<div class="col-sm-10">
						
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-default _command[UserSbscrb.modalDestroy]" data-dismiss="modal">닫기</button>
				<button type="button" id="btnLogin" class="btn btn-primary _command[UserSbscrb.sbscrbSave]" data-login-type="layerPopup">회원가입</button>
			</div>
		</div>
	</form:form>
</div>

<script type="text/javascript" src="${ctxPath}/dwr/interface/userService.js"></script>
<script type="text/javascript">
<!--
	$(document).ready(function() {
		$("#emailDomainSelect").on("change", function() {
			UserSbscrb.setEmailDomain(this, "emailDomain");
		});
		
		$("#" + UserSbscrb.FORM_ID + " .idCheck").on("focusout", function() {
			var emailId = $("#" + UserSbscrb.FORM_ID + " #emailId").val();
			var emailDomain = $("#" + UserSbscrb.FORM_ID + " #emailDomain").val();

			if (!isEmpty(emailId) && !isEmpty(emailDomain)) {
				UserSbscrb.duplicateCheck(emailId + "@" + emailDomain);
			}
		});
		
		$("#" + UserSbscrb.FORM_ID + " #emailId").focus();
	});
//-->
</script>

<c:import url="/WEB-INF/jsp/courage/tech/commonActionMessage.jsp" />