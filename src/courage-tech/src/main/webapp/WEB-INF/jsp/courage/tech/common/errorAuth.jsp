<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>
<script type="text/javascript">
<!--
	$jquery(document).ready(function () {
		$jquery('#btnBack').click(function () {
			document.location.href = "${pageContext.request.contextPath }";
		});
		
		$jquery(":button[class*=gen_btn]").button();
	});
//-->
</script>
<div class="wrapper">
	<div class="errorMemoArea">
		<div class="errorMemo">
			<span class="firstLine">요청하신 주소의 웹 페이지 또는 첨부파일 다운로드의 권한이 없습니다.</span>
			해당 페이지의 접근권한이 필요한 경우 연구전략기획과로 문의하여 주시기 바랍니다.<br />
			서비스 이용에 불편을 끼쳐 드려 죄송합니다.
		</div>
		
		<div class="btnArea">
			<button type="button" class="gen_btn" id="btnBack">
				<span>메인화면</span>
			</button>
		</div>
	</div>
	
	<div class="csCenter">
		<dl>
			<dt>문의</dt>
			<dd>schkkh@naver.com</dd>
		</dl>
		<div class="clear"></div>
	</div>
</div>