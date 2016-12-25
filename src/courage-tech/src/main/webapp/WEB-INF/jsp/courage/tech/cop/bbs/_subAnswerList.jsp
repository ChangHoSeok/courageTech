<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		Answer.MENU_NO = "${param.menuNo }";
		Answer.formInitList();
	});
//-->
</script>

<h3 class="subTitle">댓글 <span id="answerCnt"><fmt:formatNumber value="${pagination.totalRecordCount > 0 ? pagination.totalRecordCount : '' }" /></span></h3>

<table cellspacing="0" cellpadding="0" class="answer">
	<tbody>
		<c:if test="${fn:length(answerList) <= 0 }">
			<tr>
				<td class="Ctxt" style="border: 0">등록된 댓글이 없습니다.</td>
			</tr>
		</c:if>
		<c:forEach var="answerListVO" items="${answerList }">
			<c:set var="isCreate" value="${false }" />

			<c:choose>
				<%-- 관리자 --%>
				<c:when test="${boardAuthorCheckVO.mngrAuthor > 0 }">
					<c:set var="isCreate" value="${true }" />
				</c:when>
				<%-- 일반사용자 : 권한있고 내가 작성한 댓글의 경우 --%>
				<c:when test="${boardAuthorCheckVO.answerWritng > 0 && answerListVO.frstRegisterId eq sessionScope['uniqID']}">
					<c:set var="isCreate" value="${true }" />
				</c:when>
			</c:choose>
			
			<tr id="${answerListVO.answerNo }">
				<td>
					<p class="wrter">
						${answerListVO.frstRegisterNm }&nbsp;(<fmt:formatDate value="${answerListVO.frstRegistPnttm }" pattern="yy-MM-dd HH:mm:ss"/>)
						
						<c:if test="${isCreate }">
							<img id="modifyAnswer_${answerListVO.answerNo }" alt="수정" title="수정" src="" class="icon_pack_808080 ui-icon ui-icon-gear _command[Answer.modifyView]">
							<img id="deleteAnswer_${answerListVO.answerNo }" alt="삭제" title="삭제" src="" class="icon_pack_808080 ui-icon ui-icon-trash _command[Answer.deleteAnswer]">
						</c:if>
					</p>
					<p class="cn">
						<c:choose>
							<c:when test="${answerListVO.useAt eq 'Y' }">
								<cmmUtil:strToHtmlStyle source="${answerListVO.answerCn }" />
							</c:when>
							<c:otherwise>
								블라인드 처리된 댓글입니다.
							</c:otherwise>
						</c:choose>
					</p>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />