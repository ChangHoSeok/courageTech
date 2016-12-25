<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		DeptPopup.formInitDetail();
	});
//-->
</script>

<div class="popup_title_sm">
	<h1 id="progrmNm"></h1>
</div>

<div class="popup_contents">
	<div style="min-width: 600px;">
		<!-- TreeView -->
		<div class="fL" style="width: 50%;">
			<h3 class="subTitle left" style="padding: 5px 0 0 20px; background-position: 8px 7px;">부서Tree</h3>
			<label style="position: relative; top: 3px; left: 190px;">
				<input type="checkbox" name="condAblSe" id="condAblSe" class="_command[DeptPopup.refreshTree]" value="0">&nbsp;폐지부서 포함
			</label>
			
			<div class="tbl_result_list" style="height: 380px; border: 2px solid #4A90DC; overflow: auto;">
				<div id="deptTreeView" style="padding: 10px;"></div>
			</div>
		</div>
		
		<!-- 상세정보 View -->
		<div class="fR" style="width: 47%;">
			<h3 class="subTitle" style="padding: 5px 0 0 20px; background-position: 8px 7px;">부서 상세정보</h3>
			
			<div id="deptDetailView" class="tbl_result_list">
				<table cellspacing="0" cellpadding="0">
					<colgroup>
						<col style="width: 100px;" />
						<col />
						<col style="width: 100px;" />
						<col />
					</colgroup>
					
					<tbody>
						<tr>
							<th>부서코드</th>
							<td id="deptCode" class="text_left" colspan="3"></td>
						</tr>
						<tr>
							<th>부서명</th>
							<td id="allDeptNm" class="text_left" colspan="3"></td>
						</tr>
						<tr>
							<th>부서명(약칭)</th>
							<td id="lowestDeptNm" class="text_left" colspan="3"></td>
						</tr>
						<tr>
							<th>차상위 부서코드</th>
							<td id="atmbUpperDeptCode" class="text_left" colspan="3"></td>
						</tr>
						<tr>
							<th>최상위 부서코드</th>
							<td id="bestDeptCode" class="text_left" colspan="3"></td>
						</tr>
						<tr>
							<th>차수</th>
							<td id="odr" class="text_left"></td>
							<th>서열</th>
							<td id="ord" class="text_left"></td>
						</tr>
						<tr>
							<th>소속부서 차수</th>
							<td id="psitnDeptOdr" class="text_left" colspan="3"></td>
						</tr>
						<tr>
							<th>폐지구분</th>
							<td id="ablSe" class="text_left" colspan="3"></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<div class="popup_bottom">
	<div class="popup_btnGroup buttonSet">
		<form:form name="formDeptPopup" id="formDeptPopup" method="post" commandName="deptVO">
			<input type="hidden" name="openerKey" id="openerKey" value="<c:out value="${param.openerKey }" />">
			<input type="hidden" name="returnFunction" id="returnFunction" value="<c:out value="${param.returnFunction }" />">
			<cmmUtil:ajaxToken name="_dt"/>
			
			<button type="button" class="gen_btn _command[DeptPopup.select]" accesskey="1">선택</button>
			<button type="button" class="gen_btn _command[DeptPopup.popupClose]" accesskey="0"><spring:message code="button.close" /></button>
		</form:form>
	</div>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />