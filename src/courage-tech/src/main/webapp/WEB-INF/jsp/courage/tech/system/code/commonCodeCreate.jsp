<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		CommonCode.formInitCreate();
		
		$jquery("#codeId").on("keyup", function() {
			CommonCode.duplecateTextCheck($jquery(this));
		});
	});
//-->
</script>

<form:form name="formCommonCode" id="formCommonCode" action="createCmmnDetailCodeProc.tech" method="post" commandName="cmmnCodeVO">
	<input type="hidden" name="actionStatus" id="actionStatus" value="${actionStatus }">
	<form:hidden path="mode"/>
	<cmmUtil:conditionInput condObject="${cmmnCodeVO }" />
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
				<button type="button" id="modifyCommonCode" class="gen_btn _command[CommonCode.save]" accesskey="1">
					<spring:message code="button.save" />
				</button>
				<c:if test="${cmmnCodeVO.mode eq 'modify' }">
					<button type="button" id="modifyCommonCode" class="gen_btn _command[CommonCode.modifyCancel]" accesskey="2">
						<spring:message code="button.reset" />
					</button>
				</c:if>
				<button type="button" id="listCommonCode" class="gen_btn _command[CommonCode.listView]" accesskey="0">
					<spring:message code="button.list" />
				</button>
			</div>
		</div>
	</div>
	
	<c:if test="${validateCheck eq 'error' }">
		<div id="errorBox" class="errormsgbox staticHeightArea">
			<form:errors path="codeId" /><br/>
			<form:errors path="codeIdNm" /><br/>
			<form:errors path="useAt" /><br/>
		</div>
	</c:if>

	<div id="infomation" class="type_write staticHeightArea">
		<h3 class="subTitle">공통코드 정보</h3>
		<table cellspacing="0" cellpadding="0" summary="공통코드 정보">
			<colgroup>
				<col style="width: 130px;" />
				<col style="width: 250px;" />
				<col style="width: 130px;" />
				<col />
			</colgroup>
			
			<tbody>
				<tr>
					<th><label class="required">*</label>코드ID</th>
					<td>
						<c:choose>
							<c:when test="${cmmnCodeVO.mode eq 'create' }">
								<form:input path="codeId" cssClass="input-text required_textbox validate[required[alertText],custom[onlyLetterNumber],maxSize[20]]" cssStyle="width: 100px;" maxlength="20"/>
								<button type="button" id="resnd" class="gen_btn _command[CommonCode.codeIdDuplicateCheck]"><spring:message code="button.duplicate" /></button>
							</c:when>
							<c:otherwise>
								<form:hidden path="codeId"/>
								${cmmnCodeVO.codeId }
							</c:otherwise>
						</c:choose>
					</td>
					<th><label class="required">*</label>코드명</th>
					<td><form:input path="codeIdNm" cssClass="input-text required_textbox validate[required[alertText],maxSize[50]]" cssStyle="width: 200px;" maxlength="50"/></td>
				</tr>
				<tr>
					<th><label class="required">*</label>사용여부</th>
					<td colspan="3">
						<cmmCd:cdSelect codeId="COM045" name="useAt" id="useAt" defaultCode="${cmmnCodeVO.useAt }" showSelect="true" styleClass="validate[required[alertText]]" style=""/>
					</td>
				</tr>
				<tr>
					<th>코드설명</th>
					<td colspan="3"><form:input path="codeIdDc" cssClass="input-text validate[maxSize[50]]" cssStyle="width: 540px;" maxlength="50"/></td>
				</tr>
			</tbody>
		</table>
	</div>
</form:form>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />