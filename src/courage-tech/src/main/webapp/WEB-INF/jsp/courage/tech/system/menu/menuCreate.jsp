<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		Menu.UPPER_MENU_ID = '<c:out value="${menuVO.upperMenuId }" />';
		Menu.MENU_ID = '<c:out value="${menuVO.menuId }" />';
		Menu.ACTION_STATUS = '<c:out value="${param.actionStatus }" />';
		
		Menu.formInitCreate();
		
		$jquery("#menuId").on("keyup", function() {
			Menu.duplecateTextCheck($jquery(this));
		});
	});
	
	setProgrm = function(progrmList) {
		Menu.addProgrmSubList(progrmList);
	};
	
	setMenu = function(menuInfo) {
		Menu.setUpperMenuInfo(menuInfo);
	};
//-->
</script>

<form:form name="formMenu" id="formMenu" action="createMenuProc.tech" method="post" commandName="menuVO">
	<form:hidden path="mode"/>
	<form:hidden path="saveLaterView"/>
	<cmmUtil:conditionInput condObject="${menuVO }"/>
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
				<button type="button" class="gen_btn _command[Menu.save]" accesskey="1">
					<spring:message code="button.save" />
				</button>
				
				<c:choose>
					<c:when test="${menuVO.mode eq 'create' }">
						<button type="button" class="gen_btn _command[Menu.saveLaterCreate]" accesskey="2">
							저장 후 계속
						</button>
					</c:when>
					<c:otherwise>
						<button type="button" class="gen_btn _command[Menu.modifyCancel]" accesskey="2">
							<spring:message code="button.reset" />
						</button>
					</c:otherwise>
				</c:choose>
				
				<button type="button" class="gen_btn _command[Menu.listView]" accesskey="0">
					<spring:message code="button.list" />
				</button>
			</div>
		</div>
	</div>
	
	<c:if test="${validateCheck eq 'error' }">
		<div id="errorBox" class="errormsgbox staticHeightArea">
			<form:errors path="menuId" />
			<form:errors path="upperMenuId" />
			<form:errors path="menuNm" />
			<form:errors path="menuOrdr" />
			<form:errors path="menuIndictAt" />
			<form:errors path="menuDc" />
		</div>
	</c:if>
	
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
					<th><span class="required">*</span>메뉴ID</th>
					<td colspan="5">
						<c:choose>
							<c:when test="${menuVO.mode eq 'create' }">
								<form:input path="menuId" cssClass="required_textbox validate[required,custom[onlyNumberSp],maxSize[20]] _enter[Menu.duplicateCheck]" cssStyle="width: 150px;" maxlength="20"/>
								<button type="button" class="gen_btn mL5 _command[Menu.duplicateCheck]"><spring:message code="button.duplicate" /></button>
							</c:when>
							<c:otherwise>
								${menuVO.menuId }&nbsp;
								<form:hidden path="menuId"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th><span class="required">*</span>상위메뉴ID</th>
					<td colspan="5">
						<span id="upperMenuInfo">${menuVO.upperMenuId }&nbsp;(${menuVO.upperMenuNm })</span>
						<form:hidden path="upperMenuId"/>
						&nbsp;&nbsp;
						<c:if test="${menuVO.mode eq 'modify' }">
							<button type="button" class="icon_btn_search _command[Menu.menuWindowOpen]">변경</button>
						</c:if>
					</td>
				</tr>
				<tr>
					<th><span class="required">*</span>메뉴명</th>
					<td><form:input path="menuNm" cssClass="required_textbox validate[required,maxSize[60]]" cssStyle="width: 150px;" maxlength="60"/></td>
					<th><span class="required">*</span>메뉴순서</th>
					<td><form:input path="menuOrdr" cssClass="required_textbox validate[required,custom[onlyNumberSp],maxSize[5]]" cssStyle="width: 150px;" maxlength="5"/></td>
					<th><span class="required">*</span>메뉴표시여부</th>
					<td><cmmCd:cdSelect codeId="COM045" name="menuIndictAt" id="menuIndictAt" styleClass="validate[required]" showSelect="true" defaultCode="${menuVO.menuIndictAt }" use="true" onChange="Menu.fieldSet(this);"/></td>
				</tr>
				<tr>
					<th>매개변수</th>
					<td colspan="5"><form:input path="intrmdVriabl" cssClass="validate[maxSize[1000]]" cssStyle="width: 820px;" maxlength="100"/></td>
				</tr>
				<tr>
					<th>메뉴설명</th>
					<td colspan="5"><form:input path="menuDc" cssClass="validate[maxSize[125]]" cssStyle="width: 820px;" maxlength="125"/></td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div id="menuProgrmList"></div>
</form:form>

<form name="formMenuProgrmList" id="formMenuProgrmList" method="post">
	<input type="hidden" name="condMenuNo" id="condMenuNo" value="${menuVO.menuId }">
	<input type="hidden" name="condOrder" id="condOrder" value="PROGRM_SE">
	<input type="hidden" name="condAlign" id="condAlign" value="ASC">
	<input type="hidden" name="pagingEnable" id="pagingEnable" value="0">
	<input type="hidden" name="customView" id="customView" value="<spring:eval expression="@systemView['progrmSubList']" />">
	<input type="hidden" name="mode" id="mode" value="edit">
</form>

<!-- commonForm:Begin -->
<form name="popupForm" id="popupForm" method="post" action="">
    <input type="hidden" name="openerKey" id="openerKey" value="" />
    <input type="hidden" name="returnFunction" id="returnFunction" value="" />
</form>
<!-- commonForm:End -->

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />