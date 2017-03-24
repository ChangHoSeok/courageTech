<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<div class="modal-dialog modal-lg">
	<form:form name="formUser" id="formUser" action="${ctxPath }/system/user/createUserSbscrb.tech" method="post" commandName="userVO">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">회원가입</h4>
			</div>
			<div class="modal-body">
				<span class="section">개인정보처리방침</span>
				
				<div class="embed-responsive embed-responsive-16by9">
					<iframe name="sbscrbStplat" id="sbscrbStplat" class="embed-responsive-item ifrm_border" src="${ctxPath }/html/privacy.html"></iframe>
				</div>
				
				<p class="submit" style="margin: 40px 0 0; text-align: center;">
					<button type="button" class="btn btn-warning _command[UserSbscrb.sbscrbAgre]" data-agre="disagre">비동의</button>
					<button type="button" class="btn btn-success _command[UserSbscrb.sbscrbAgre]" data-agre="agre">동 의</button>
				</p>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</form:form>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonActionMessage.jsp" />