<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		Auth.formInitDetail();
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
	
	<form:form name="formAuth" id="formAuth" action="createAuthProc.do" method="post" commandName="authVO">
		<form:hidden path="authorCode"/>
		<input type="hidden" name="authorIndexUrlChkVal" id="authorIndexUrlChkVal" value="${authVO.authorIndexUrl }">
		<cmmUtil:conditionInput condObject="${authVO }"/>
		<cmmUtil:ajaxToken name="_dt"/>
		
		<div id="contentBtnFix" class="button_area topLine staticHeightArea">
			<div class="buttonSet left">
				<button type="button" class="gen_btn needCreate _command[Auth.modifyView]" accesskey="1"><spring:message code="button.modify" /></button>
				<button type="button" class="gen_btn needCreate _command[Auth.deleteAuth]" accesskey="2"><spring:message code="button.delete" /></button>
				<button type="button" class="gen_btn _command[Auth.listView]" accesskey="0"><spring:message code="button.list" /></button>
			</div>
		</div>
	</form:form>
</div>

<div id="infomation" class="type_write staticHeightArea">
	<h3 class="subTitle">권한 정보</h3>
	<table cellspacing="0" cellpadding="0">
		<colgroup>
			<col style="width: 200px;" />
			<col />
		</colgroup>
		
		<tbody>
			<tr>
				<th>권한ID</th>
				<td><c:out value="${authVO.authorCode }" />&nbsp;</td>
			</tr>
			<tr>
				<th>권한명</th>
				<td><c:out value="${authVO.authorNm }" />&nbsp;</td>
			</tr>
			<tr>
				<th>사용자 기본권한 설정</th>
				<td><c:out value="${authVO.mberBassAuthorYn }" />&nbsp;</td>
			</tr>
			<tr>
				<th>권한설명</th>
				<td><c:out value="${authVO.authorDc }" />&nbsp;</td>
			</tr>
		</tbody>
	</table>
</div>

<div id="detailMenuList"></div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />