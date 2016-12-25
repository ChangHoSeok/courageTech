<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		CommonCode.formInitDetail();
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
	
	<form:form name="formCommonCode" id="formCommonCode" action="retrieveCodeManageList.tech" method="post" commandName="cmmnCodeVO">
		<form:hidden path="codeId"/>
		<cmmUtil:conditionInput condObject="${cmmnCodeVO }" />
		<cmmUtil:ajaxToken name="_dt"/>
		
		<div id="contentBtnFix" class="button_area topLine staticHeightArea">
			<div class="buttonSet left">
				<button type="button" id="modifyCommonCode" class="gen_btn needCreate _command[CommonCode.modifyView]" accesskey="1">
					<spring:message code="button.modify" />
				</button>
				<button type="button" id="listCommonCode" class="gen_btn _command[CommonCode.listView]" accesskey="0">
					<spring:message code="button.list" />
				</button>
			</div>
		</div>
	</form:form>
</div>

<div id="infomation" class="type_write staticHeightArea">
	<h3 class="subTitle">공통코드 정보</h3>
	<table cellspacing="0" cellpadding="0" summary="공통코드 상세정보">
		<colgroup>
			<col style="width: 130px;" />
			<col style="width: 250px;" />
			<col style="width: 130px;" />
			<col />
		</colgroup>
		
		<tbody>
			<tr>
				<th>코드ID</th>
				<td>${cmmnCodeVO.codeId }&nbsp;</td>
				<th>코드명</th>
				<td>${cmmnCodeVO.codeIdNm }&nbsp;</td>
			</tr>
			<tr>
				<th>사용여부</th>
				<td><cmmCd:cdValue codeId="COM045" code="${cmmnCodeVO.useAt }"/></td>
				<th>상세코드 갯수</th>
				<td><cmmUtil:numFormat value="${cmmnCodeVO.detailCodeCount }" /></td>
			</tr>
			<tr>
				<th>코드설명</th>
				<td colspan="3">${cmmnCodeVO.codeIdDc }&nbsp;</td>
			</tr>
			<tr>
				<th>등록자</th>
				<td>${cmmnCodeVO.frstRegisterNm }&nbsp;</td>
				<th>등록일시</th>
				<td>${cmmnCodeVO.frstRegistPnttm }&nbsp;</td>
			</tr>
			
		</tbody>
	</table>
</div>

<div id="commonDetailCodeList"></div>

<!-- commonForm:Begin -->
<form name="popupForm" id="popupForm" method="post" action="">
    <input type="hidden" name="openerKey" id="openerKey" value="" />
    <input type="hidden" name="returnFunction" id="returnFunction" value="" />
</form>
<!-- commonForm:End -->

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />