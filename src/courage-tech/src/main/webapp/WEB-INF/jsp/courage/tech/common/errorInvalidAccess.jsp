<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>
<script type="text/javascript">
<!--
	$jquery(document).ready(function () {
		$jquery('#btnHome').click(function () {
			document.location.href = "${pageContext.request.contextPath }";
		});
		
		$jquery(":button[class*=gen_btn]").button();
	});
//-->
</script>
<div class="wrapper">
	<div class="errorMemoArea">
		<div class="errorMemo">
			<span class="firstLine">연결하려는 페이지에 접근할 수 없습니다.</span>
			해당 페이지는 접근이 허용된 방법으로 연결해야 접근이 가능합니다.<br />
			서비스 이용에 불편을 끼쳐 드려 죄송합니다.
		</div>
		
		<div class="btnArea">
			<button type="button" class="gen_btn" id="btnHome">
				<span>홈페이지</span>
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