<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<div id="photoList">
	<c:forEach var="icon" items="${avatarList }">
		<div class="faceIcon">
			<a class="_command[MyPage.setAvatarIcon]">
				<img class="rotate" alt="사진" src="${ctxPath }${icon.iconPath }/${icon.iconNm }">
			</a>
		</div>
	</c:forEach>
</div>