<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		User.formInitDetail();
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
	
	<form:form name="formUser" id="formUser" action="retrieveUserDetail.tech" method="post" commandName="userVO">
		<form:hidden path="esntlId"/>
		<cmmUtil:conditionInput condObject="${userVO }"/>
		<cmmUtil:ajaxToken name="_dt"/>
		
		<div id="contentBtnFix" class="button_area topLine staticHeightArea">
			<div class="buttonSet left">
				<button type="button" class="gen_btn needCreate _command[User.modifyView]" accesskey="1"><spring:message code="button.modify" /></button>
				<button type="button" class="gen_btn needCreate _command[User.deleteUser]" accesskey="2"><spring:message code="button.delete" /></button>
				<button type="button" class="gen_btn needCreate _command[User.initPasswd]" accesskey="3">비밀번호 초기화</button>
				<button type="button" class="gen_btn _command[User.listView]" accesskey="0"><spring:message code="button.list" /></button>
			</div>
		</div>
	</form:form>
</div>

<div id="infomation" class="type_write staticHeightArea">
	<h3 class="subTitle">사용자 정보</h3>
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
				<th>고유ID</th>
				<td><c:out value="${userVO.esntlId }" />&nbsp;</td>
				<th>사용자ID</th>
				<td><c:out value="${userVO.emplyrId }" />&nbsp;</td>
				<th>회원구분</th>
				<td><cmmCd:cdValue code="${userVO.mberSe }" codeId="COM012" />&nbsp;</td>
			</tr>
			<tr>
				<th>사용자명</th>
				<td><c:out value="${userVO.userNm }" />&nbsp;</td>
				<th>이메일주소</th>
				<td><c:out value="${userVO.emailAdres }" />&nbsp;</td>
				<th>별명</th>
				<td><c:out value="${userVO.ncnm }" />&nbsp;</td>
			</tr>
			<tr>
				<th>부서</th>
				<td colspan="5">
					<c:out value="${userVO.deptNm }" />&nbsp;
					<c:if test="${!empty userVO.deptCode }">
						(<c:out value="${userVO.deptCode }" />)
					</c:if>
				</td>
			</tr>
			<tr>
				<th>비밀번호힌트</th>
				<td colspan="5"><cmmCd:cdValue code="${userVO.passwordHint }" codeId="COM022" />&nbsp;</td>
			</tr>
			<tr>
				<th>비밀번호정답</th>
				<td colspan="5">**********&nbsp;</td>
			</tr>
			<tr>
				<th>상태</th>
				<td id="emplyrSttusCodeNm">
					<c:choose>
						<c:when test="${userVO.emplyrSttusCode eq 'A' }">
							<div class="fL mT5" style="width: 90px">
								<cmmCd:cdValue code="${userVO.emplyrSttusCode }" codeId="COM013" />&nbsp;
							</div>
							<div class="fR mR10">
								<button type="button" class="gen_btn needCreate _command[User.sbscrbConfm]">가입승인</button>
							</div>
						</c:when>
						<c:otherwise>
							<cmmCd:cdValue code="${userVO.emplyrSttusCode }" codeId="COM013" />&nbsp;
						</c:otherwise>
					</c:choose>
				</td>
				<th>가입일시</th>
				<td colspan="3"><c:out value="${userVO.sbscrbDe }" />&nbsp;</td>
			</tr>
			<tr>
				<th>탈퇴일시</th>
				<td colspan="5"><c:out value="${userVO.secsnDe }" />&nbsp;</td>
			</tr>
			<tr>
				<th>탈퇴사유</th>
				<td colspan="5"><c:out value="${userVO.secsnResn }" />&nbsp;</td>
			</tr>
		</tbody>
	</table>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />