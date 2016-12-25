<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		Progrm.formInitDetail();
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
	
	<form:form name="formProgrm" id="formProgrm" action="createProgrmProc.tech" method="post" commandName="progrmVO">
		<form:hidden path="progrmFileNm"/>
		<cmmUtil:conditionInput condObject="${progrmVO }"/>
		<cmmUtil:ajaxToken name="_dt"/>
		
		<div id="contentBtnFix" class="button_area topLine staticHeightArea">
			<div class="buttonSet left">
				<button type="button" class="gen_btn needCreate _command[Progrm.modifyView]" accesskey="1"><spring:message code="button.modify" /></button>
				<button type="button" class="gen_btn needCreate _command[Progrm.deleteProgrm]" accesskey="2"><spring:message code="button.delete" /></button>
				<button type="button" class="gen_btn _command[Progrm.listView]" accesskey="0"><spring:message code="button.list" /></button>
			</div>
		</div>
	</form:form>
</div>

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
				<th>프로그램 파일명</th>
				<td colspan="5"><c:out value="${progrmVO.progrmFileNm }" />&nbsp;</td>
			</tr>
			<tr>
				<th>프로그램 구분</th>
				<td><cmmCd:cdValue code="${progrmVO.progrmSe}" codeId="CURG002"/>&nbsp;</td>
				<th>프로그램 기능 구분</th>
				<td><cmmCd:cdValue code="${progrmVO.progrmFnctSe}" codeId="CURG003"/>&nbsp;</td>
				<th>프로그램 저장경로</th>
				<td><c:out value="${progrmVO.progrmStrePath }" />&nbsp;</td>
			</tr>
			<tr>
				<th>프로그램 한글명</th>
				<td><c:out value="${progrmVO.progrmKoreanNm }" />&nbsp;</td>
				<th>URL</th>
				<td colspan="3"><c:out value="${progrmVO.url }" />&nbsp;</td>
			</tr>
			<tr>
				<th>Request Method<br />제한여부</th>
				<td>
					<c:choose>
						<c:when test="${!empty progrmVO.requestMethodList }">
							Y
						</c:when>
						<c:otherwise>
							N
						</c:otherwise>
					</c:choose>
				</td>
				<th>Request Method</th>
				<td colspan="3">
					<cmmCd:cdCheck name="requestMethodList" codeId="CURG005" defaultChecked="${progrmVO.requestMethodList }" styleClass="innerTable" disabled="true" use="true" skipCode="HEAD,OPTIONS,TRACE"/>
				</td>
			</tr>
			<tr>
				<th>Request 허용 구분</th>
				<td><cmmCd:cdValue code="${progrmVO.requestSe }" codeId="CURG006"/>&nbsp;</td>
				<th>로그인 확인 여부</th>
				<td colspan="3"><cmmCd:cdValue code="${progrmVO.loginCnfirmAt }" codeId="COM038"/>&nbsp;</td>
			</tr>
			<tr>
				<th>프로그램 설명</th>
				<td colspan="5"><c:out value="${progrmVO.progrmDc }" />&nbsp;</td>
			</tr>
		</tbody>
	</table>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />