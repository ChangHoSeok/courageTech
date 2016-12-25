<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<div id="loginForm">
	<div class="container">
		<div class="login">
			<p>
				<strong style="font-size: 1.2em;">회원탈퇴 안내</strong>
				<span style="display: block; margin: 10px 0;">회원탈퇴를 할 수 있습니다. 다음의 안내사항을 확인 후 탈퇴하여 주시기 바랍니다.</span>
			</p>
			<div style="background-color: #FFF7D5; width: 100%; height: auto; border-top: 1px solid #FFBE00; border-bottom: 1px solid #FFBE00; margin-top: 20px;">
				<ul style="padding: 15px;">
					<li style="padding: 5px 0; list-style-type: decimal; list-style-position: inside;">회원탈퇴 시 회원님의 개인정보는 즉시 삭제됩니다.</li>
					<li style="padding: 5px 0; list-style-type: decimal; list-style-position: inside;">회원님이 작성하시 컨텐츠(게시글, 댓글, 답글 등)는 삭제되지 않습니다.</li>
					<li style="padding: 5px 0; list-style-type: decimal; list-style-position: inside;">회원탈퇴는 즉시 처리됩니다.</li>
					<li style="padding: 5px 0; list-style-type: decimal; list-style-position: inside;">회원탈퇴 후 재가입이 가능합니다. (기존 아이디 재활용 가능)</li>
					<li style="padding: 5px 0; list-style-type: decimal; list-style-position: inside;">회원탈퇴는 회원탈퇴 버튼을 클릭하면 탈퇴가 완료됩니다.</li>
				</ul>
			</div>
			<p style="padding-left: 10px;">
				<label><input type="checkbox" name="secsnAgre" id="secsnAgre" value="Y">&nbsp;위의 사항을 숙지했으며, 회원탈퇴에 동의 합니다.</label>
			</p>
		</div>
	</div>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />