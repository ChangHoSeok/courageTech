<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		CommonDetailCode.formInitCreate();
		
		$jquery("#code").on("keyup", function() {
			CommonDetailCode.duplecateTextCheck($jquery(this));
		});
	});
//-->
</script>

<div class="popup_title_sm">
	<h1 id="progrmNm"></h1>
</div>

<div class="popup_contents">
	<form:form name="formCommonDetailCode" id="formCommonDetailCode" method="post" commandName="cmmnDetailCodeVO">
		<input type="hidden" name="openerKey" id="openerKey" value="<c:out value="${param.openerKey }" />">
		<input type="hidden" name="returnFunction" id="returnFunction" value="<c:out value="${param.returnFunction }" />">
		<form:hidden path="mode"/>
		<form:hidden path="codeId"/>
		<form:hidden path="codeIdNm"/>
		<cmmUtil:ajaxToken name="_dt"/>
		
		<c:if test="${validateCheck eq 'error' }">
			<div id="errorBox" class="errormsgbox staticHeightArea">
				<form:errors path="codeId" />
				<form:errors path="code" />
				<form:errors path="codeNm" />
				<form:errors path="useAt" />
			</div>
		</c:if>
		
		<div class="tbl_result_list">
			<table cellspacing="0" cellpadding="0">
				<colgroup>
					<col style="width: 150px;" />
					<col style="width: 215px;" />
					<col style="width: 100px;" />
					<col />
				</colgroup>
				
				<tbody>
					<tr>
						<th>코드ID</th>
						<td class="text_left">${cmmnDetailCodeVO.codeId }</td>
						<th>코드명</th>
						<td class="text_left">${cmmnDetailCodeVO.codeIdNm }</td>
					</tr>
					<tr>
						<th>
							<c:if test="${cmmnDetailCodeVO.mode eq 'create' }"><span class="red">*</span></c:if>공통 상세코드
						</th>
						<td colspan="3" class="text_left">
							<c:choose>
								<c:when test="${cmmnDetailCodeVO.mode eq 'create' }">
									<form:input path="code" cssClass="input-text required_textbox validate[required,maxSize[64]] _enter[CommonDetailCode.codeDuplicateCheck]" cssStyle="width: 100px;" maxlength="64"/>
									<button type="button" class="gen_btn _command[CommonDetailCode.codeDuplicateCheck]"><spring:message code="button.duplicate" /></button>
								</c:when>
								<c:otherwise>
									<form:hidden path="code"/>
									${cmmnDetailCodeVO.code }
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>공통 상세코드명</th>
						<td colspan="3" class="text_left">
							<form:input path="codeNm" cssClass="input-text required_textbox validate[required,maxSize[50]]" cssStyle="width: 500px;" maxlength="50"/>
						</td>
					</tr>
					<tr>
						<th>공통 상세코드 설명</th>
						<td colspan="3" class="text_left"><form:input path="codeDc" cssClass="input-text validate[maxSize[50]]" cssStyle="width: 500px;" maxlength="100"/></td>
					</tr>
					<tr>
						<th>비고</th>
						<td colspan="3" class="text_left"><form:textarea path="rm" cssClass="input-text validate[maxSize[500]]" cssStyle="height: 100px; width: 500px;"></form:textarea></td>
					</tr>
					<tr>
						<th><span class="red">*</span>사용여부</th>
						<td colspan="3" class="text_left">
							<cmmCd:cdSelect codeId="COM045" name="useAt" id="useAt" defaultCode="${cmmnDetailCodeVO.useAt }" showSelect="true" styleClass="validate[required]" use="true" style=""/>
						</td>
					</tr>
					<tr>
						<th>정렬순서</th>
						<td colspan="3" class="text_left"><form:input path="ordr" cssClass="input-text validate[custom[onlyNumberSp],max[99999]]" cssStyle="width: 80px;" maxlength="5"/></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form:form>
</div>

<div class="popup_bottom">
	<div class="popup_btnGroup">
		<button type="button" class="gen_btn needCreate _command[CommonDetailCode.save]" accesskey="1"><spring:message code="button.save" /></button>
		<c:if test="${cmmnDetailCodeVO.mode eq 'modify' }">
			<button type="button" class="gen_btn needCreate _command[CommonDetailCode.modifyCancel]" accesskey="2"><spring:message code="button.reset" /></button>
		</c:if>
		<button type="button" class="gen_btn _command[CommonDetailCode.popupClose]" accesskey="0"><spring:message code="button.close" /></button>
	</div>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />