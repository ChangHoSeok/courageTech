<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		MessagePush.ACTION_STATUS = "${actionStatus}";
		MessagePush.formInitCreate();
	});
//-->
</script>

<div class="popup_title_sm">
	<h1 id="progrmNm"></h1>
</div>

<div class="popup_contents">
	<form:form name="formMessagePush" id="formMessagePush" method="post" commandName="messagePushVO">
		<input type="hidden" name="openerKey" id="openerKey" value="<c:out value="${param.openerKey }" />">
		<input type="hidden" name="returnFunction" id="returnFunction" value="<c:out value="${param.returnFunction }" />">
		<form:hidden path="mode"/>
		<cmmUtil:ajaxToken name="_dt"/>
		
		<c:if test="${validateCheck eq 'error' }">
			<div id="errorBox" class="errormsgbox staticHeightArea">
				<form:errors path="mssageCn" />
			</div>
		</c:if>
		
		<div class="tbl_result_list">
			<table cellspacing="0" cellpadding="0">
				<colgroup>
					<col style="width: 100px;" />
					<col />
				</colgroup>
				
				<tbody>
					<tr>
						<th><span class="required">*</span>&nbsp;메시지 내용</th>
						<td class="text_left">
							<form:textarea path="mssageCn" cssClass="input-text required_textbox validate[required,maxSize[512]]" cssStyle="height: 100px; width: 450px;"></form:textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</form:form>
</div>

<div class="popup_bottom">
	<div class="popup_btnGroup">
		<button type="button" class="gen_btn needCreate _command[MessagePush.save]" accesskey="1"><spring:message code="button.save" /></button>
		<button type="button" class="gen_btn needCreate _command[MessagePush.preview]" accesskey="2">미리보기</button>
		<button type="button" class="gen_btn _command[MessagePush.popupClose]" accesskey="0"><spring:message code="button.close" /></button>
	</div>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />