<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		Auth.formInitCreate();
		
		$jquery("#authorCode").on("keyup", function() {
			Auth.duplecateTextCheck($jquery(this));
		});
	});
//-->
</script>

<form:form name="formAuth" id="formAuth" action="createAuthProc.do" method="post" commandName="authVO">
	<form:hidden path="mode"/>
	<input type="hidden" name="authorIndexUrlChkVal" id="authorIndexUrlChkVal" value="${authVO.authorIndexUrl }">
	<cmmUtil:conditionInput condObject="${authVO }"/>
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
				<button type="button" class="gen_btn _command[Auth.save]" accesskey="1"><spring:message code="button.save" /></button>
				<c:if test="${authVO.mode eq 'modify' }">
					<button type="button" class="gen_btn _command[Auth.modifyCancel]" accesskey="2"><spring:message code="button.reset" /></button>
				</c:if>
				<button type="button" class="gen_btn _command[Auth.listView]" accesskey="0"><spring:message code="button.list" /></button>
			</div>
		</div>
	</div>
	
	<c:if test="${validateCheck eq 'error' }">
		<div id="errorBox" class="errormsgbox staticHeightArea">
			<form:errors path="authorCode" />
			<form:errors path="authorNm" />
		</div>
	</c:if>
	
	<div id="infomation" class="type_write staticHeightArea">
		<h3 class="subTitle">권한 정보</h3>
		<table cellspacing="0" cellpadding="0">
			<colgroup>
				<col style="width: 200px;" />
				<col />
			</colgroup>
			
			<tbody>
				<tr>
					<th><span class="red">*</span>권한ID</th>
					<td>
						<c:choose>
							<c:when test="${authVO.mode eq 'create' }">
								<form:input path="authorCode" cssClass="input-text required_textbox validate[required,custom[onlyLetterNumberSpChar],maxSize[30]] _enter[Auth.duplicateCheck]" cssStyle="width: 180px;" maxlength="30"/>
								<button type="button" class="gen_btn _command[Auth.duplicateCheck]"><spring:message code="button.duplicate" /></button>
							</c:when>
							<c:otherwise>
								<form:hidden path="authorCode"/>
								${authVO.authorCode }&nbsp;
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>권한명</th>
					<td><form:input path="authorNm" cssClass="input-text required_textbox validate[required,maxSize[30]]" cssStyle="width: 180px;" maxlength="30"/></td>
				</tr>
				<tr>
					<th><span class="red">*</span>사용자 기본권한 설정</th>
					<td>
						<div class="mT10 mB10">
							<input type="checkbox" name="mberBassAuthorYn" id="mberBassAuthorYn" value="Y" ${authVO.mberBassAuthorYn eq 'Y' ? 'checked="checked"' : '' }>
							<label for="mberBassAuthorYn">회원가입시 부여될 기본 권한을 설정합니다.</label>
						</div>
						<div style="color: #888888">
							※. 선택된 권한은 회원등록(회원가입) 시 자동으로 부여되는 권한이며, 한개의 권한만 지정할 수 있습니다. <br />
							&nbsp;&nbsp;&nbsp;&nbsp;(이전에 지정된 권한이 있다면 자동으로 해제 됩니다.)
						</div>
					</td>
				</tr>
				<tr>
					<th>권한설명</th>
					<td><form:input path="authorDc" cssClass="input-text validate[maxSize[100]]" cssStyle="width: 350px;" maxlength="100"/></td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<!-- 메뉴 목록 영역 -->
	<div id="detailMenuList"></div>
</form:form>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />