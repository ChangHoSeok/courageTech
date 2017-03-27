<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<div class="modal-dialog">
	<!-- Modal content-->
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">&times;</button>
			<h4 class="modal-title">회원가입</h4>
		</div>
		
		<div class="modal-body">
			<span class="section">회원가입 완료</span>
			
			<p class="text-center lead">
				감사합니다.
			</p>
			<p class="text-center lead">
				회원가입이 완료되었습니다
			</p>
		</div>
		
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
			<button type="button" id="btnLogin" class="btn btn-primary _command[UserSbscrb.showLoginPopupForm]" data-login-type="layerPopup">로그인</button>
		</div>
	</div>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonActionMessage.jsp" />