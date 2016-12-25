<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		UserAuthPopup.ACTION_STATUS = "<c:out value="${param.actionStatus }" />";
		UserAuthPopup.formInitCreate();
	});
//-->
</script>

<div class="popup_title_sm">
	<h1 id="progrmNm"></h1>
</div>

<div class="popup_contents">
	<div class="tbl_result_list">
		<table cellspacing="0" cellpadding="0">
			<col style="width: 150px;" />
			<col style="width: 215px;" />
			<col style="width: 150px;">
			<col />

			<tr>
				<th>아이디</th>
				<td><c:out value="${userVO.emplyrId }" />&nbsp;</td>
				<th>성명</th>
				<td><c:out value="${userVO.userNm }" />&nbsp;</td>
			</tr>
			<tr>
				<th>별명</th>
				<td><c:out value="${userVO.ncnm }" />&nbsp;</td>
				<th>회원구분</th>
				<td><cmmCd:cdValue code="${userVO.mberSe }" codeId="COM012" />&nbsp;</td>
			</tr>
		</table>
	</div>
	
	<form:form name="formUserAuthPopup" id="formUserAuthPopup" action="createUserAuthPopupProc.tech" method="post" commandName="userVO">
		<input type="hidden" name="openerKey" id="openerKey" value="<c:out value="${param.openerKey }" />">
		<input type="hidden" name="returnFunction" id="returnFunction" value="<c:out value="${param.returnFunction }" />">
		
		<form:hidden path="esntlId"/>
		
		<div id="paramArrayDiv"></div>
		
		<div class="tbl_result_list">
			<table cellspacing="0" cellpadding="0">
				<colgroup>
					<col style="width: 80px;" />
					<col style="width: 150px;" />
					<col />
					<col style="width: 80px;" />
				</colgroup>
				
				<thead>
					<tr>
						<th>사용권한</th>
						<th>권한명</th>
						<th>권한설명</th>
						<th>기본권한</th>
					</tr>
				</thead>
				
				<tbody>
					<c:choose>
						<c:when test="${fn:length(authList) <= 0 }">
							<tr>
								<td colspan="4"><spring:message code="info.nodata.msg" /></td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="authListVO" items="${authList}" varStatus="sttus">
								<tr>
									<td class="pl0">
										<input type="checkbox" id="authorCodeChk${sttus.index}" name="authorCodeChk" <c:if test="${!empty authListVO.esntlId }">checked="checked"</c:if> onclick="UserAuthPopup.userAuthCheck('${sttus.index}');"/>
										<input type="hidden" id="authorCodeChk${sttus.index}Value" name="" value="${authListVO.authorCode }" /> 
									</td>
									<td class="text_left">
										<div class="ellipsis" title="<c:out value="${authListVO.authorNm }" />">
											<c:out value="${authListVO.authorNm }" />&nbsp;
										</div>
									</td>
									<td class="text_left"><div class="ellipsis" title="<c:out value="${authListVO.authorDc }" />"><c:out value="${authListVO.authorDc}" /></div></td>
									<td>
										<input type="checkbox" id="authorCodeChk${sttus.index}Yn" name="defaultAuthorYn" <c:if test="${authListVO.defaultAuthorYn eq 'Y' }">checked="checked"</c:if> onclick="UserAuthPopup.defaultAuthorYnCheck(this,'${sttus.index}');" />
									</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</form:form>
</div>

<div class="popup_bottom">
	<div class="popup_btnGroup">
		<button type="button" class="gen_btn needCreate _command[UserAuthPopup.save]" accesskey="1"><spring:message code="button.save" /></button>
		<button type="button" class="gen_btn _command[UserAuthPopup.popupClose]" accesskey="0"><spring:message code="button.close" /></button>
	</div>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />