<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		Answer.ACTION_STATUS = "${actionStatus }";
		Answer.formInitCreate();
	});
//-->
</script>

<c:set var="isCreate" value="${false }" />

<c:if test="${boardAuthorCheckVO.mngrAuthor > 0 || boardAuthorCheckVO.answerWritng > 0 }">
	<c:set var="isCreate" value="${true }" />
</c:if>

<table cellspacing="0" cellpadding="0" style="border-top: 0px;">
	<tbody>
		<tr>
			<td>
				<form:form name="formAnswer" id="formAnswer" action="retrieveAnswerList.tech" method="post" commandName="answerVO">
					<form:hidden path="mode"/>
					<form:hidden path="answerNo"/>
					
					<c:if test="${validateCheck eq 'error' }">
						<div id="errorBox" class="errormsgbox staticHeightArea">
							<form:errors path="answerCn" />
						</div>
					</c:if>
					
					<div class="fL">
						<c:choose>
							<c:when test="${isCreate }">
								<form:textarea path="answerCn" cssClass="input-text required_textbox validate[required,maxSize[2000]]" cssStyle="width: 830px; height: 65px;"/>
							</c:when>
							<c:otherwise>
								<textarea style="width: 830px; height: 65px;" class="input-text" disabled="disabled">댓글 작성 권한이 없습니다.</textarea>
							</c:otherwise>
						</c:choose>
					</div>
					
					<div class="buttonSet left">
						<button type="button" class="gen_btn _command[Answer.save]" ${isCreate ? '' : 'disabled="disabled"' }>댓글저장</button>
						
						<c:if test="${answerVO.mode eq 'modify' && isCreate }">
							<br />
							<button type="button" style="top: 5px;" class="gen_btn _command[Answer.modifyCancel]">수정취소</button>
						</c:if>
					</div>
				</form:form>
			</td>
		</tr>
	</tbody>
</table>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />