<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		Progrm.formInitCreate();
		Progrm.ACTION_STATUS = '<c:out value="${param.actionStatus }" />';
		
		$jquery("#progrmFileNm").on("keyup", function() {
			Progrm.duplecateTextCheck($jquery(this));
		});
	});
//-->
</script>

<form:form name="formProgrm" id="formProgrm" action="createProgrmProc.tech" method="post" commandName="progrmVO">
	<form:hidden path="mode"/>
	<form:hidden path="saveLaterView"/>
	<cmmUtil:conditionInput condObject="${progrmVO }"/>
	<cmmUtil:ajaxToken name="_dt"/>
	
	<div class="ct_area">
		<div id="contentInfo" class="path_search">
			<div class="left_path">
				프로그램 ${progrmVO.mode eq "create" ? "등록" : "수정" }
			</div>
			<div class="right_path">
				<span>시스템관리 &gt; 프로그램관리</span>
			</div>
		</div>
		
		<div id="contentBtnFix" class="button_area topLine staticHeightArea">
			<div class="buttonSet left">
				<button type="button" class="gen_btn _command[Progrm.save]" accesskey="1">
					<spring:message code="button.save" />
				</button>
				
				<c:choose>
					<c:when test="${progrmVO.mode eq 'create' }">
						<button type="button" class="gen_btn _command[Progrm.saveLaterCreate]" accesskey="2">
							저장 후 계속
						</button>
					</c:when>
					<c:otherwise>
						<button type="button" class="gen_btn _command[Progrm.modifyCancel]" accesskey="2">
							<spring:message code="button.reset" />
						</button>
					</c:otherwise>
				</c:choose>
				
				<button type="button" class="gen_btn _command[Progrm.listView]" accesskey="0">
					<spring:message code="button.list" />
				</button>
			</div>
		</div>
	</div>
	
	<c:if test="${validateCheck eq 'error' }">
		<div id="errorBox" class="errormsgbox staticHeightArea">
			<form:errors path="progrmFileNm" />
			<form:errors path="progrmSe" />
			<form:errors path="progrmFnctSe" />
			<form:errors path="progrmStrePath" />
			<form:errors path="progrmKoreanNm" />
			<form:errors path="url" />
			<form:errors path="loginCnfirmAt" />
		</div>
	</c:if>
	
	<div id="infomation" class="type_write staticHeightArea">
		<h3 class="subTitle">프로그램 정보</h3>
		<table cellspacing="0" cellpadding="0">
			<colgroup>
				<col style="width: 140px;" />
				<col style="width: 200px;" />
				<col style="width: 140px;" />
				<col style="width: 200px;" />
				<col style="width: 140px;" />
				<col />
			</colgroup>
			
			<tbody>
				<tr>
					<th><span class="required">*</span>프로그램 파일명</th>
					<td colspan="5">
						<c:choose>
							<c:when test="${progrmVO.mode eq 'create' }">
								<form:input path="progrmFileNm" cssClass="input-text required_textbox validT validate[required,custom[onlyLetterNumber]] _enter[Progrm.duplicateCheck]" cssStyle="width: 180px;" maxlength="60"/>
								<button type="button" class="gen_btn _command[Progrm.duplicateCheck]"><spring:message code="button.duplicate" /></button>
							</c:when>
							<c:otherwise>
								<form:hidden path="progrmFileNm"/>
								${progrmVO.progrmFileNm }&nbsp;
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th><span class="required">*</span>프로그램 구분</th>
					<td><cmmCd:cdSelect codeId="CURG002" name="progrmSe" showSelect="true" defaultCode="${progrmVO.progrmSe}" style="" styleClass="validate[required]" use="true"/></td>
					<th><span class="required">*</span>프로그램 기능 구분</th>
					<td><cmmCd:cdSelect codeId="CURG003" name="progrmFnctSe" showSelect="true" defaultCode="${progrmVO.progrmFnctSe}" style="" styleClass="validate[required]" use="true"/></td>
					<th><span class="required">*</span>프로그램 저장경로</th>
					<td><form:input path="progrmStrePath" cssClass="input-text required_textbox validate[required]" cssStyle="width: 150px;" maxlength="100"/></td>
				</tr>
				<tr>
					<th><span class="required">*</span>프로그램 한글명</th>
					<td><form:input path="progrmKoreanNm" cssClass="input-text required_textbox validate[required]" cssStyle="width: 150px;" maxlength="50"/></td>
					<th><span class="required">*</span>URL</th>
					<td colspan="3"><form:input path="url" cssClass="input-text required_textbox validate[required]" cssStyle="width: 480px;" maxlength="100"/></td>
				</tr>
				<tr>
					<th><span class="required">*</span>Request Method<br />제한여부</th>
					<td><cmmCd:cdSelect name="requestLmttAt" codeId="COM038" showSelect="true" use="true" styleClass="validate[required]" defaultCode="${empty progrmVO.requestMethodList ? 'N' : 'Y' }" onChange="Progrm.fieldSet(this);"/></td>
					<th><span class="required requestMethodStar">*</span>Request Method</th>
					<td colspan="3">
						<cmmCd:cdCheck name="requestMethodList" codeId="CURG005" defaultChecked="${progrmVO.requestMethodList }" styleClass="innerTable" use="true" skipCode="HEAD,OPTIONS,TRACE"/>
						<span style="color: #888888">※. 요청을 허용할 Method를 선택하세요.</span>
						<div id="requestMethodList_validate_errorArea"></div>
					</td>
				</tr>
				<tr>
					<th>Request 허용 구분</th>
					<td><cmmCd:cdSelect name="requestSe" codeId="CURG006" showSelect="true" defaultCode="${progrmVO.requestSe }" use="true"/></td>
					<th><span class="required">*</span>로그인 확인 여부</th>
					<td colspan="3">
						<cmmCd:cdSelect codeId="COM038" name="loginCnfirmAt" showSelect="true" defaultCode="${progrmVO.loginCnfirmAt}" style="" use="true" styleClass="validate[required]"/>
					</td>
				</tr>
				<tr>
					<th>프로그램 설명</th>
					<td colspan="5"><form:input path="progrmDc" cssClass="input-text" cssStyle="width: 810px;" maxlength="100"/></td>
				</tr>
			</tbody>
		</table>
	</div>
</form:form>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />