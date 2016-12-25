<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		LogFileRceptPopup.formInitDetail();
	});
//-->
</script>

<div class="popup_title_sm">
	<h1 id="progrmNm"></h1>
</div>

<div class="popup_contents">
	<div class="tbl_result_list">
		<table cellspacing="0" cellpadding="0">
			<colgroup>
				<col style="width: 100px;" />
				<col />
				<col style="width: 100px;" />
				<col />
			</colgroup>
			
			<tbody>
				<tr>
					<th>로그분류그룹</th>
					<td class="text_left"><c:out value="${logFileRceptVO.groupNm }" />&nbsp;(<c:out value="${logFileRceptVO.groupId }" />)</td>
					<th>접수ID</th>
					<td class="text_left"><c:out value="${logFileRceptVO.rceptId }" />&nbsp;</td>
				</tr>
				<tr>
					<th>접수구분</th>
					<td class="text_left"><cmmCd:cdValue code="${logFileRceptVO.rceptTy }" codeId="CURG008" />&nbsp;</td>
					<th>처리상태</th>
					<td class="text_left"><cmmCd:cdValue code="${logFileRceptVO.processSttus }" codeId="CURG007" />&nbsp;</td>
				</tr>
				<tr>
					<th>접수데이터 건수</th>
					<td class="text_left" colspan="3"><fmt:formatNumber value="${logFileRceptVO.rceptDataCnt }" />&nbsp;</td>
				</tr>
				<tr>
					<th>접수일시</th>
					<td class="text_left"><fmt:formatDate value="${logFileRceptVO.rceptDt }" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;</td>
					<th>처리완료 일시</th>
					<td class="text_left">
						<c:if test="${logFileRceptVO.processSttus ne '1' && logFileRceptVO.processSttus ne '2' }">
							<fmt:formatDate value="${logFileRceptVO.lastUpdtPnttm }" pattern="yyyy-MM-dd HH:mm:ss" />
						</c:if>
						&nbsp;
					</td>
				</tr>
				<tr>
					<th>접수파일</th>
					<td class="text_left" colspan="3">
						<ul class="atchFileList">
							<c:forEach var="storageFile" items="${logFileRceptVO.atchFileList }">
								<li>${storageFile.fileNm }&nbsp;(<cmmUtil:fileSizeFormat value="${storageFile.fileSize }" format="2" />)</li>
							</c:forEach>
						</ul>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>

<div class="popup_bottom">
	<div class="popup_btnGroup buttonSet">
		<form:form name="formLogFileRceptPopup" id="formLogFileRceptPopup" method="post" commandName="logFileRceptVO">
			<input type="hidden" name="openerKey" id="openerKey" value="<c:out value="${param.openerKey }" />">
			<input type="hidden" name="returnFunction" id="returnFunction" value="<c:out value="${param.returnFunction }" />">
			<form:hidden path="groupId"/>
			<form:hidden path="rceptId"/>
			<cmmUtil:ajaxToken name="_dt"/>
			
			<button type="button" class="gen_btn _command[LogFileRceptPopup.popupClose]" accesskey="0"><spring:message code="button.close" /></button>
		</form:form>
	</div>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />