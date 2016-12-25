<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script type="text/javascript">
<!--
	$jquery(document).ready(function () {
		$jquery('#btnMain').click(function () {
			document.location.href = "${pageContext.request.contextPath }";
		});
		
		$jquery(":button[class*=gen_btn]").button();
	});
//-->
</script>
<div class="wrapper">
	<div class="errorMemoArea">
		<div class="errorMemo">
			<span class="firstLine">연결하려는 페이지에 문제가 있어 페이지를 표시할 수 없습니다.</span>
			조속한 시일 이내에 해당 오류를 확인하여 수정하도록 하겠습니다.<br />
			서비스 이용에 불편을 끼쳐 드려 죄송합니다.
		</div>
		
		<div class="btnArea">
			<button type="button" class="gen_btn" id="btnMain">
				메인화면
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