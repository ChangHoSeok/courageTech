<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<div class="modal-dialog">
	<form:form name="formLogin" id="formLogin" action="${ctxPath }/uat/login/userLogin.tech" method="post" commandName="loginVO" cssClass="form-horizontal">
		<form:hidden path="enfrcLogin"/>
		
		<c:if test="${validateCheck eq 'error' }">
			<div id="errorBox" class="errormsgbox staticHeightArea">
				<form:errors path="emplyrId"/> <br />
				<form:errors path="password"/>
			</div>
		</c:if>
		
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Login to Courage Tech</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label for="emplyrId">아이디</label>
					<form:input path="emplyrId" placeholder="Username" cssClass="form-control validate[required]" maxlength="50"/>
				</div>
				<div class="form-group">
					<label for="password">비밀번호</label>
					<input type="password" name="password" id="password" value="" class="form-control validate[required]" maxlength="30" placeholder="Password">
				</div>
				<div class="checkbox">
					<label>
						<input type="checkbox" name="rememberMe" id="rememberMe" value="true" ${loginVO.rememberMe ? 'checked="checked"' : '' }> 아이디 기억하기
					</label>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				<button type="button" class="btn btn-primary _command[Login.onLogin]">로그인</button>
			</div>
		</div>
	</form:form>
</div>

<script type="text/javascript">
<!--
	$(document).ready(function() {
		Login.ACTION_STATUS = "${actionStatus }";
		Login.subFormInit();
	});
//-->
</script>