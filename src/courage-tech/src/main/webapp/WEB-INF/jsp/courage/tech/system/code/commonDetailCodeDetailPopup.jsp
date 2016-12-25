<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		CommonDetailCode.formInitDetail();
	});
//-->
</script>

<form:form name="formCommonDetailCode" id="formCommonDetailCode" action="retrieveCmmnDetailCodeDetail.tech" method="post" commandName="cmmnDetailCodeVO">
	<input type="hidden" name="openerKey" id="openerKey" value="<c:out value="${param.openerKey }" />">
	<input type="hidden" name="returnFunction" id="returnFunction" value="<c:out value="${param.returnFunction }" />">
	<form:hidden path="codeId"/>
	<form:hidden path="code"/>
	<cmmUtil:ajaxToken name="_dt"/>
</form:form>

<div class="popup_title_sm">
	<h1 id="progrmNm"></h1>
</div>

<div class="popup_contents">
	<div class="tbl_result_list">
		<table cellspacing="0" cellpadding="0">
			<colgroup>
				<col style="width: 150px;" />
				<col style="width: 215px;" />
				<col style="width: 150px;" />
				<col />
			</colgroup>
			
			<tbody>
				<tr>
					<th>코드ID</th>
					<td class="text_left"><c:out value="${cmmnDetailCodeVO.codeId }" />&nbsp;</td>
					<th>코드명</th>
					<td class="text_left"><c:out value="${cmmnDetailCodeVO.codeIdNm }" />&nbsp;</td>
				</tr>
				<tr>
					<th>공통 상세코드</th>
					<td class="text_left"><c:out value="${cmmnDetailCodeVO.code }" />&nbsp;</td>
					<th>공통 상세코드명</th>
					<td class="text_left"><c:out value="${cmmnDetailCodeVO.codeNm }" />&nbsp;</td>
				</tr>
				<tr>
					<th>공통 상세코드 설명</th>
					<td colspan="3" class="text_left"><c:out value="${cmmnDetailCodeVO.codeDc }" />&nbsp;</td>
				</tr>
				<tr>
					<th>비고</th>
					<td colspan="3" class="text_left"><cmmUtil:strToHtmlStyle source="${cmmnDetailCodeVO.rm }" />&nbsp;</td>
				</tr>
				<tr>
					<th>사용여부</th>
					<td class="text_left"><cmmCd:cdValue codeId="COM045" code="${cmmnDetailCodeVO.useAt }" /></td>
					<th>정렬순서</th>
					<td class="text_left">${cmmnDetailCodeVO.ordr }&nbsp;</td>
				</tr>
				<tr>
					<th>등록자</th>
					<td class="text_left"><c:out value="${cmmnDetailCodeVO.frstRegisterNm }" />&nbsp;</td>
					<th>등록일시</th>
					<td class="text_left">${cmmnDetailCodeVO.frstRegistPnttm }&nbsp;</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>

<div class="popup_bottom">
	<div class="popup_btnGroup">
		<button type="button" class="gen_btn needCreate _command[CommonDetailCode.modifyView]" accesskey="1">수정</button>
		<button type="button" class="gen_btn _command[CommonDetailCode.popupClose]" accesskey="0">닫기</button>
	</div>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />