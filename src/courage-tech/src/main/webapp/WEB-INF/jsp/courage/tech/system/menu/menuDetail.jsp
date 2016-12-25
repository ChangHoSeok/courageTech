<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		Menu.UPPER_MENU_ID = '${menuVO.upperMenuId }';
		Menu.formInitDetail();
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
	
	<form:form name="formMenu" id="formMenu" action="retrieveMenuDetail.tech" method="post" commandName="menuVO">
		<form:hidden path="menuId"/>
		<form:hidden path="upperMenuId"/>
		<cmmUtil:conditionInput condObject="${menuVO }"/>
		<cmmUtil:ajaxToken name="_dt"/>
		
		<div id="contentBtnFix" class="button_area topLine staticHeightArea">
			<div class="buttonSet left">
				<button type="button" class="gen_btn needCreate _command[Menu.modifyView]" accesskey="1"><spring:message code="button.modify" /></button>
				<c:if test="${!empty menuVO.upperMenuId }">
					<button type="button" class="gen_btn needCreate _command[Menu.deleteMenu]" accesskey="2"><spring:message code="button.delete" /></button>
				</c:if>
				<button type="button" class="gen_btn _command[Menu.listView]" accesskey="0"><spring:message code="button.list" /></button>
			</div>
		</div>
	</form:form>
</div>

<div id="infomation" class="type_write staticHeightArea">
	<h3 class="subTitle">메뉴관리 정보</h3>
	
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
				<th>메뉴ID</th>
				<td colspan="5">${menuVO.menuId }&nbsp;</td>
			</tr>
			<tr>
				<th>상위메뉴ID</th>
				<td colspan="5">${menuVO.upperMenuId }&nbsp;(${menuVO.upperMenuNm })</td>
			</tr>
			<tr>
				<th>메뉴명</th>
				<td><c:out value="${menuVO.menuNm }" />&nbsp;</td>
				<th>메뉴순서</th>
				<td>${menuVO.menuOrdr }&nbsp;</td>
				<th>메뉴표시여부</th>
				<td><cmmCd:cdValue code="${menuVO.menuIndictAt }" codeId="COM045"/> &nbsp;</td>
			</tr>
			<tr>
				<th>매개변수</th>
				<td colspan="5"><c:out value="${menuVO.intrmdVriabl }" />&nbsp;</td>
			</tr>
			<tr>
				<th>메뉴설명</th>
				<td colspan="5"><c:out value="${menuVO.menuDc }" />&nbsp;</td>
			</tr>
		</tbody>
	</table>
</div>

<div id="menuProgrmList"></div>

<form name="formMenuProgrmList" id="formMenuProgrmList" method="post">
	<input type="hidden" name="condMenuNo" id="condMenuNo" value="${menuVO.menuId }">
	<input type="hidden" name="condOrder" id="condOrder" value="PROGRM_SE">
	<input type="hidden" name="condAlign" id="condAlign" value="ASC">
	<input type="hidden" name="pagingEnable" id="pagingEnable" value="0">
	<input type="hidden" name="customView" id="customView" value="<spring:eval expression="@systemView['progrmSubList']" />">
</form>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />