<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		UserSbscrb.ACTION_STATUS = "${actionStatus}";
		UserSbscrb.formInitSbscrbDetail();
	});
//-->
</script>

<form:form name="formUser" id="formUser" action="userSbscrbDetail.tech" method="post" commandName="userVO">
	<input type="hidden" name="emplyrId" value="dummy">
	<input type="hidden" name="emailId" value="dummy">
	<input type="hidden" name="emailDomain" value="dummy">
	<input type="hidden" name="password" value="dummy">
	<cmmUtil:ajaxToken name="_dt"/>
	
	<div class="ct_area">
		<div id="contentInfo" class="path_search">
			<div id="progrmNm" class="left_path"></div>
		</div>
		
		<div id="contentBtnFix" class="button_area topLine staticHeightArea">
			<div class="buttonSet left">
				<button type="button" class="gen_btn needCreate _command[UserSbscrb.modifySbscrb]" accesskey="1">회원정보 변경</button>
				<button type="button" class="gen_btn needCreate _command[UserSbscrb.showSecsn]" accesskey="2">회원탈퇴</button>
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
		<table cellspacing="0" cellpadding="0">
			<colgroup>
				<col style="width: 180px;" />
				<col />
			</colgroup>
			
			<tbody>
				<tr>
					<th>사용자ID</th>
					<td><c:out value="${userVO.emplyrId }" />&nbsp;</td>
				</tr>
				<tr>
					<th>닉네임</th>
					<td><form:input path="userNm" cssClass="input-text required_textbox validate[required,custom[onlyKoreaEng]]" cssStyle="width: 250px;" maxlength="25"/></td>
				</tr>
				<tr>
					<th>신규 비밀번호</th>
					<td>
						<form:password path="newPassword" cssClass="input-text validate[custom[password]]" cssStyle="width: 250px;" maxlength="15"/>
						※. 신규 비밀번호를 입력하면 입력한 비밀번호로 변경 됩니다.
					</td>
				</tr>
				<tr>
					<th>신규 비밀번호 확인</th>
					<td><input type="password" name="passwordConfirm" id="passwordConfirm" class="input-text validate[custom[password],equals[newPassword]]" style="width: 250px;" maxlength="15"></td>
				</tr>
				<tr>
					<th>가입일시</th>
					<td><c:out value="${userVO.sbscrbDe }" />&nbsp;</td>
				</tr>
			</tbody>
		</table>
	</div>
</form:form>

<div id="dialog-userConfirm" title="사용자 인증" style="display: none;"></div>
<div id="dialog-userSecsn" title="회원탈퇴" style="display: none;"></div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />