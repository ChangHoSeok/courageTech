<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		Dept.formInitDetail();
	});
//-->
</script>

	
<form:form name="formDept" id="formDept" action="retrieveDeptDetail.tech" method="post" commandName="deptVO">
	<form:hidden path="deptCode"/>
	<cmmUtil:conditionInput condObject="${deptVO }"/>
	<cmmUtil:ajaxToken name="_dt"/>
	
	<div id="contentBtnFix" class="button_area topLine staticHeightArea">
		<div class="buttonSet left">
			<button type="button" class="gen_btn needCreate _command[Dept.modifyView]" accesskey="1"><spring:message code="button.modify" /></button>
			<button type="button" class="gen_btn needCreate _command[Dept.deleteDept]" accesskey="2"><spring:message code="button.delete" /></button>
			<button type="button" class="gen_btn _command[Dept.listView]" accesskey="0"><spring:message code="button.list" /></button>
		</div>
	</div>
</form:form>

<div id="infomation" class="type_write staticHeightArea">
	<h3 class="subTitle">부서정보</h3>
	<table cellspacing="0" cellpadding="0">
		<colgroup>
			<col style="width: 130px;" />
			<col style="width: 200px;" />
			<col style="width: 130px;" />
			<col style="width: 200px;" />
			<col style="width: 130px;" />
			<col />
		</colgroup>
		
		<tbody>
			<tr>
				<th>부서코드</th>
				<td colspan="5"><c:out value="${deptVO.deptCode }" />&nbsp;</td>
			</tr>
			<tr>
				<th>전체 부서명</th>
				<td colspan="5"><c:out value="${deptVO.allDeptNm }" />&nbsp;</td>
			</tr>
			<tr>
				<th>부서명(약칭)</th>
				<td colspan="5"><c:out value="${deptVO.lowestDeptNm }" />&nbsp;</td>
			</tr>
			<tr>
				<th>차상위 부서코드</th>
				<td colspan="5"><c:out value="${deptVO.atmbUpperDeptCode }" />&nbsp;</td>
			</tr>
			<tr>
				<th>최상위 부서코드</th>
				<td colspan="5"><c:out value="${deptVO.bestDeptCode }" />&nbsp;</td>
			</tr>
			<tr>
				<th>차수</th>
				<td><fmt:formatNumber value="${deptVO.odr }" />&nbsp;</td>
				<th>서열</th>
				<td><fmt:formatNumber value="${deptVO.ord }" />&nbsp;</td>
				<th>소속부서 차수</th>
				<td><fmt:formatNumber value="${deptVO.psitnDeptOdr }" />&nbsp;</td>
			</tr>
			<tr>
				<th>폐지구분</th>
				<td colspan="5"><cmmCd:cdValue code="${deptVO.ablSe }" codeId="CURG009" />&nbsp;</td>
			</tr>
		</tbody>
	</table>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />