<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		User.formInitCreate();
		User.ACTION_STATUS = '<c:out value="${param.actionStatus }" />';
		
		$jquery("#emplyrId").on("keyup", function() {
			User.duplecateTextCheck($jquery(this));
		});
		
		$jquery("#emailDomainSelect").on("change", function() {
			User.setEmailDomain(this, "emailDomain");
		});
		
		setDept = function(deptVO) {
			$jquery("#" + User.FORM_ID + " #deptNm").val(deptVO.lowestDeptNm);
			$jquery("#" + User.FORM_ID + " #deptCode").val(deptVO.deptCode);
		};
	});
//-->
</script>

<form:form name="formUser" id="formUser" action="createUserProc.tech" method="post" commandName="userVO">
	<form:hidden path="mode"/>
	<form:hidden path="saveLaterView"/>
	<cmmUtil:conditionInput condObject="${userVO }"/>
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
				<button type="button" class="gen_btn _command[User.save]" accesskey="1">
					<spring:message code="button.save" />
				</button>
				
				<c:choose>
					<c:when test="${userVO.mode eq 'create' }">
						<button type="button" class="gen_btn _command[User.saveLaterCreate]" accesskey="2">
							저장 후 계속
						</button>
					</c:when>
					<c:otherwise>
						<button type="button" class="gen_btn _command[User.modifyCancel]" accesskey="2">
							<spring:message code="button.reset" />
						</button>
					</c:otherwise>
				</c:choose>
				
				<button type="button" class="gen_btn _command[User.listView]" accesskey="0">
					<spring:message code="button.list" />
				</button>
			</div>
		</div>
	</div>
	
	<c:if test="${validateCheck eq 'error' }">
		<div id="errorBox" class="errormsgbox staticHeightArea">
			<form:errors path="emplyrId" /><br />
			<form:errors path="userNm" /><br />
			<form:errors path="emailId" /><br />
			<form:errors path="emailDomain" /><br />
			<form:errors path="password" />
		</div>
	</c:if>
	
	<div id="infomation" class="type_write staticHeightArea">
		<h3 class="subTitle">사용자 정보</h3>
		<table cellspacing="0" cellpadding="0">
			<colgroup>
				<col style="width: 140px;" />
				<col style="width: 350px;" />
				<col style="width: 140px;" />
				<col />
			</colgroup>
			
			<tbody>
				<tr>
					<th><span class="required">*</span>사용자ID</th>
					<td>
						<c:choose>
							<c:when test="${userVO.mode eq 'create' }">
								<form:input path="emplyrId" cssClass="input-text required_textbox validT validate[required,custom[onlyLetterNumber]] _enter[User.duplicateCheck]" cssStyle="width: 180px;" maxlength="60"/>
								<button type="button" class="gen_btn _command[User.duplicateCheck]"><spring:message code="button.duplicate" /></button>
							</c:when>
							<c:otherwise>
								<form:hidden path="esntlId"/>
								<form:hidden path="emplyrId"/>
								${userVO.emplyrId }&nbsp;
							</c:otherwise>
						</c:choose>
					</td>
					<th>회원구분</th>
					<td><cmmCd:cdSelect codeId="COM012" name="mberSe" showSelect="true" defaultCode="${userVO.mberSe eq '' ? 'USR02' : userVO.mberSe }" use="true"/></td>
				</tr>
				<tr>
					<th><span class="required">*</span>사용자명</th>
					<td><form:input path="userNm" cssClass="input-text required_textbox validate[required,custom[onlyKoreaEng]]" cssStyle="width: 180px;" maxlength="25"/></td>
					<th>별명</th>
					<td><form:input path="ncnm" cssClass="input-text" cssStyle="width: 180px;" maxlength="10"/></td>
				</tr>
				<tr>
					<th><span class="required">*</span>이메일주소</th>
					<td colspan="3">
						<form:input path="emailId" cssClass="input-text required_textbox validate[required,custom[email1]]" cssStyle="width: 130px;" maxlength="40"/>@
						<form:input path="emailDomain" cssClass="input-text required_textbox validate[required,custom[email2]]" cssStyle="width: 130px;" maxlength="20"/>
						<cmmCd:cdSelect codeId="CURG004" name="emailDomainSelect" use="true"/>
					</td>
				</tr>
				<tr>
					<th>부서</th>
					<td colspan="3">
						<form:input path="deptNm" cssClass="input-text readonly" cssStyle="width: 350px;" readonly="true"/>
						<form:hidden path="deptCode"/>
						<button type="button" id="deptSearch" style="height: 25px;" class="_command[User.deptPopupView]"></button>
						<button type="button" id="deptClear" style="height: 25px;" class="_command[User.deptClear]"></button>
					</td>
				</tr>
				<c:choose>
					<c:when test="${userVO.mode eq 'create' }">
						<tr>
							<th><span class="required">*</span>비밀번호</th>
							<td><form:password path="password" cssClass="input-text required_textbox validate[required,custom[password]]" cssStyle="width: 180px;" maxlength="15"/></td>
							<th><span class="required">*</span>비밀번호 확인</th>
							<td><input type="password" name="passwordConfirm" id="passwordConfirm" class="input-text required_textbox validate[required,custom[password],equals[password]]" style="width: 180px;" maxlength="15"></td>
						</tr>
						<tr>
							<th>비밀번호힌트</th>
							<td colspan="3"><cmmCd:cdSelect codeId="COM022" name="passwordHint" showSelect="true" defaultCode="${userVO.passwordHint}" style="" use="true"/></td>
						</tr>
						<tr>
							<th>비밀번호정답</th>
							<td colspan="3"><form:input path="passwordCnsr" cssClass="input-text" cssStyle="width: 430px;" maxlength="50"/></td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<th><span class="required">*</span>비밀번호</th>
							<td colspan="3">
								비밀번호는 수정할 수 없습니다. (비밀번호 초기화를 이용하세요)
								<input type="hidden" name="password" value="dummy">
							</td>
						</tr>
						<tr>
							<th>비밀번호힌트</th>
							<td colspan="3">
								비밀번호힌트는 수정할 수 없습니다. (사용자별 마이페이지를 이용하세요)
								<input type="hidden" name="passwordHint" value="dummy">
								<input type="hidden" name="passwordCnsr" value="dummy">
							</td>
						</tr>
					</c:otherwise>
				</c:choose>
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