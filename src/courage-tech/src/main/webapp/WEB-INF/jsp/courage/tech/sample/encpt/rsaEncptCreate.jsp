<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		RSAEncpt.formInitCreate();
	});
//-->
</script>

<form:form name="formRSAEncpt" id="formRSAEncpt" action="createRSAEncptProc.tech" method="post">
	<cmmUtil:ajaxToken name="_dt"/>
	
	<div class="ct_area">
		<div id="contentInfo" class="path_search">
			<div id="progrmNm" class="left_path"></div>
			<div class="right_path">
				<span id="menuNavi"></span>
			</div>
		</div>
		
		<div id="contentBtnFix" class="button_area topLine staticHeightArea">
			<div class="buttonSet left">
				<button type="button" class="gen_btn _command[RSAEncpt.encrypt]" accesskey="1">암호화</button>
				<button type="button" class="gen_btn _command[RSAEncpt.submit]" accesskey="2">서버전송</button>
			</div>
		</div>
	</div>
	
	<div id="infomation" class="type_write staticHeightArea">
		<h3 class="subTitle">정보</h3>
		<table cellspacing="0" cellpadding="0">
			<colgroup>
				<col style="width: 140px;" />
				<col style="width: 350px;" />
				<col style="width: 140px;" />
				<col />
			</colgroup>
			
			<tbody>
				<tr>
					<th>공개키</th>
					<td colspan="3">
						<textarea name="publicKey" id="publicKey" style="height: 100px; width: 600px;" class="input-text">${publicKey }</textarea>
					</td>
				</tr>
				<tr>
					<th>평문데이터</th>
					<td colspan="3">
						<input type="text" name="data1" id="data1" class="input-text" style="width: 600px;">
					</td>
				</tr>
				<tr>
					<th>암호문</th>
					<td colspan="3">
						<textarea name="data2" id="data2" style="height: 50px; width: 600px;" class="input-text"></textarea>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</form:form>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />