<%@page import="kr.pe.courage.common.prop.PropertiesMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		$jquery(".noticeRollingList").on("click", function() {
			var params = $jquery(this).prop("id").split("|");
			var pUrl = jsContextPath + "/cop/bbs/formBoard.tech";
			var pName = "popupNotice";
			var pWidth = 500;
			var pHeight = 500;
			var PopupWindow = PopUpWindowOpenOnlyScroll(pName, pWidth, pHeight);
			
			$jquery('#noticeRollPopupForm input[name="bbsId"]').val(params[0]);
			$jquery('#noticeRollPopupForm input[name="nttId"]').val(params[1]);
			$jquery('#noticeRollPopupForm').attr('target', pName);
			$jquery('#noticeRollPopupForm').attr('action', pUrl).submit();
			
			PopupWindow.focus();
		});
	});
//->
</script>

<c:if test="${fn:length(noticeRollingList) > 0 }">
	<ul>
		<c:forEach var="noticeVO" items="${noticeRollingList }">
			<li id="${noticeVO.bbsId }|${noticeVO.nttId}" class="noticeRollingList">
				<img alt="news" src="${ctxPath }/images/courage/tech/icon/icon_news.png">
				<span>${noticeVO.nttSj }</span>
			</li>
		</c:forEach>
	</ul>
</c:if>

<form name="noticeRollPopupForm" id="noticeRollPopupForm" method="post" action="">
    <input type="hidden" name="bbsId" id="bbsId" value="" />
    <input type="hidden" name="nttId" id="nttId" value="" />
    <input type="hidden" name="viewTy" id="viewTy" value="popup" />
</form>