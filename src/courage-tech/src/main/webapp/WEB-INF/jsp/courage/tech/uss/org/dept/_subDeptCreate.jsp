<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		Dept.formInitCreate();
		Dept.ACTION_STATUS = '<c:out value="${param.actionStatus }" />';
		
		$jquery("#deptCode").on("keyup", function() {
			Dept.duplecateTextCheck($jquery(this));
		});
		
		setDept = function(deptVO) {
			Dept.setDept(deptVO);
		};
	});
//-->
</script>

<form:form name="formDept" id="formDept" action="createDeptProc.tech" method="post" commandName="deptVO">
	<form:hidden path="mode"/>
	<form:hidden path="saveLaterView"/>
	<cmmUtil:conditionInput condObject="${deptVO }"/>
	<cmmUtil:ajaxToken name="_dt"/>
	
	<div id="contentBtnFix" class="button_area topLine staticHeightArea">
		<div class="buttonSet left">
			<button type="button" class="gen_btn _command[Dept.save]" accesskey="1">
				<spring:message code="button.save" />
			</button>
			
			<c:choose>
				<c:when test="${deptVO.mode eq 'create' }">
					<button type="button" class="gen_btn _command[Dept.saveLaterCreate]" accesskey="2">
						저장 후 계속
					</button>
				</c:when>
				<c:otherwise>
					<button type="button" class="gen_btn _command[Dept.modifyCancel]" accesskey="2">
						<spring:message code="button.reset" />
					</button>
				</c:otherwise>
			</c:choose>
			
			<button type="button" class="gen_btn _command[Dept.listView]" accesskey="0">
				<spring:message code="button.list" />
			</button>
		</div>
	</div>
	
	<c:if test="${validateCheck eq 'error' }">
		<div id="errorBox" class="errormsgbox staticHeightArea">
			<form:errors path="deptCode" /><br />
			<form:errors path="allDeptNm" /><br />
			<form:errors path="lowestDeptNm" /><br />
			<form:errors path="ablSe" />
		</div>
	</c:if>
	
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
					<th><span class="required">*</span>부서코드</th>
					<td colspan="5">
						<c:choose>
							<c:when test="${deptVO.mode eq 'create' }">
								<form:input path="deptCode" cssClass="input-text required_textbox validT validate[required,custom[onlyLetterNumber],minSize[3],maxSize[128]] _enter[Dept.duplicateCheck]" cssStyle="width: 180px;" maxlength="128"/>
								<button type="button" class="gen_btn _command[Dept.duplicateCheck]"><spring:message code="button.duplicate" /></button>
							</c:when>
							<c:otherwise>
								<c:out value="${deptVO.deptCode }" />
								<form:hidden path="deptCode"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th><span class="required">*</span>전체 부서명</th>
					<td colspan="5"><form:input path="allDeptNm" cssClass="input-text required_textbox validate[required,maxSize[128]]" cssStyle="width: 350px" maxlength="128"/></td>
				</tr>
				<tr>
					<th><span class="required">*</span>부서명(약칭)</th>
					<td colspan="5"><form:input path="lowestDeptNm" cssClass="input-text required_textbox validate[required,maxSize[128]]" cssStyle="width: 350px" maxlength="128"/></td>
				</tr>
				<tr>
					<th>차상위 부서코드</th>
					<td colspan="5">
						<form:input path="atmbUpperDeptCode" cssClass="input-text validate[custom[onlyLetterNumber],maxSize[128]]" cssStyle="width: 180px;" maxlength="128"/>
						<button type="button" class="gen_btn _command[Dept.deptPopupView]">부서조회</button>
					</td>
				</tr>
				<tr>
					<th>최상위 부서코드</th>
					<td colspan="5">
						<form:input path="bestDeptCode" cssClass="input-text readonly validate[custom[onlyLetterNumber],maxSize[128]]" cssStyle="width: 180px;" maxlength="128" readonly="true"/>
						<strong>※. 차상위 부서코드 선택시 자동 입력됩니다.</strong>
					</td>
				</tr>
				<tr>
					<th><span class="required">*</span>차수</th>
					<td><form:input path="odr" cssClass="input-text validate[required,custom[onlyNumber],max[9999]]" maxlength="4" cssStyle="width: 80px;"/></td>
					<th>서열</th>
					<td><form:input path="ord" cssClass="input-text validate[custom[onlyNumber],max[9999]]" maxlength="4" cssStyle="width: 80px;"/></td>
					<th>소속부서 차수</th>
					<td><form:input path="psitnDeptOdr" cssClass="input-text validate[custom[onlyNumber],max[9999]]" maxlength="4" cssStyle="width: 80px;"/></td>
				</tr>
				<tr>
					<th><span class="required">*</span>폐지구분</th>
					<td colspan="5">
						<cmmCd:cdSelect name="ablSe" id="ablSe"
							codeId="CURG009"
							defaultCode="${deptVO.ablSe }"
							showSelect="true"
							use="true"
							styleClass="validate[required]"/>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</form:form>

<!-- commonForm:Begin -->
<form name="popupForm" id="popupForm" method="post" action="">
    <input type="hidden" name="openerKey" id="openerKey" value="" />
    <input type="hidden" name="returnFunction" id="returnFunction" value="" />
</form>
<!-- commonForm:End -->

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />