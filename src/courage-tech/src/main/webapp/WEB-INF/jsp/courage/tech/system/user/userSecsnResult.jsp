<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp"%>

<link href="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.css.basePath']" />/courage/tech/loginStyle.css" rel="stylesheet" type="text/css" media="screen" />

<section class="container">
	<form action="${ctxPath }">
		<div class="login" style="width: 450px;">
			<h1>회원탈퇴 처리 안내</h1>
			
			<p>그동안 CourateTech를 이용해주셔서 감사합니다.</p>
			<p>회원탈퇴가 정상적으로 처리되었습니다.</p>
			
			<p class="etcBtn">
				<input type="submit" name="home" value="메인으로 돌아가기">
			</p>
		</div>
	</form>
</section>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />