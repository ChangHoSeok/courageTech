<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>
<script type="text/javascript">
<!--
	$jquery(document).ready(function () {
		$jquery('#btnLogin').click(function () {
			document.location.href = "${pageContext.request.contextPath }/uat/login/formLogin.tech";
		});
		
		$jquery(":button[class*=gen_btn]").button();
	});
//-->
</script>
<div class="wrapper">
	<div class="errorMemoArea">
		<div class="errorMemo">
			<span class="firstLine">세션이 만료되어 로그인 상태를 유지할 수 없습니다.</span>
			정상적인 서비스 이용을 위해 다시 로그인하여 주시기 바랍니다.<br />
			서비스 이용에 불편을 끼쳐 드려 죄송합니다.
		</div>
		
		<div class="btnArea">
			<button type="button" class="gen_btn" id="btnLogin">
				<span>로그인</span>
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