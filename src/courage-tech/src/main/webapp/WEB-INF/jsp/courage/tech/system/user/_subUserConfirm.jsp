<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>
<script type="text/javascript">
<!--
	$(document).ready(function() {
		$("#" + UserSbscrb.CONFIRM_FORM_ID + " #password").focus();
	});
//-->
</script>

<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">&times;</button>
			<h4 class="modal-title">사용자 인증</h4>
		</div>
		
		<div class="modal-body">
			<form:form name="formUserConfirm" id="formUserConfirm" action="${ctxPath }/system/user/retrieveUserConfirm.tech" method="post" commandName="userVO">
				<div class="form-group">
					<label for="emailId" class="col-xs-12 col-sm-3 control-label">비밀번호</label>
					<div class="col-xs-12 col-sm-9">
						<input type="password" name="password" id="password" value="" class="form-control validate[required,custom[password]]" maxlength="30" placeholder="비밀번호">
					</div>
				</div>
			</form:form>
		</div>
		
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
			<button type="button" id="btnLogin" class="btn btn-primary _command[UserSbscrb.userConfirm]">사용자 인증</button>
		</div>
	</div>
</div>
<c:import url="/WEB-INF/jsp/courage/tech/commonActionMessage.jsp" />