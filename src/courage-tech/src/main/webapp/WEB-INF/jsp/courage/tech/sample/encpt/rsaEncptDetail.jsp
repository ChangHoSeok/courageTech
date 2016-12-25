<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		RSAEncpt.formInitDetail();
	});
//-->
</script>

<div class="ct_area">
	<div id="contentInfo" class="path_search">
		<div id="progrmNm" class="left_path"></div>
		<div class="right_path">
			<span id="menuNavi"></span>
		</div>
	</div>
	
	<form:form name="formRSAEncpt" id="formRSAEncpt" action="retrieveRSAEncptDetail.tech" method="post">
		<cmmUtil:ajaxToken name="_dt"/>
		
		<div id="contentBtnFix" class="button_area topLine staticHeightArea">
			<div class="buttonSet left">
				<button type="button" class="gen_btn needCreate _command[RSAEncpt.back]" accesskey="1">이전</button>
			</div>
		</div>
	</form:form>
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
				<th>평문데이터</th>
				<td colspan="3"><c:out value="${param.data1 }" />&nbsp;</td>
			</tr>
			<tr>
				<th>암호문</th>
				<td colspan="3"><c:out value="${param.data2 }" />&nbsp;</td>
			</tr>
		</tbody>
	</table>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />