<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$(document).ready(function() {
		Login.ACTION_STATUS = "${actionStatus }";
		Login.subFormInit();
	});
//-->
</script>

<div class="modal-dialog">
	<form:form name="formLogin" id="formLogin" action="${ctxPath }/uat/login/userLogin.tech" method="post" commandName="loginVO">
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
				<p>
					<span style="display: block; margin: 5px; font-weight: bold; color: #969696;">아이디</span>
					<form:input path="emplyrId" placeholder="Username" cssClass="validate[required]" maxlength="50"/>
				</p>
				<p>
					<span style="display: block; margin: 5px; font-weight: bold; color: #969696;">비밀번호</span>
					<input type="password" name="password" id="password" value="" class="validate[required]" maxlength="30" placeholder="Password">
				</p>
				
				<p class="remember_me">
					<label>
						<input type="checkbox" name="rememberMe" id="rememberMe" value="true" ${loginVO.rememberMe ? 'checked="checked"' : '' }> 아이디 기억하기
					</label>
				</p>
				
				<p class="submit">
					<input type="submit" name="login" id="layerPopup" value="Login" class="_command[Login.onLogin]">
				</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</form:form>
</div>