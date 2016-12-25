<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
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
			<span class="firstLine">요청하신 주소의 웹 페이지를 찾을 수 없습니다.</span>
			해당 웹 페이지의 주소가 변경되었거나, 존재하지 않습니다.<br />
			웹 페이지 주소를 정확히 입력하셨는지 다시 확인해 주시기 바랍니다.<br />
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