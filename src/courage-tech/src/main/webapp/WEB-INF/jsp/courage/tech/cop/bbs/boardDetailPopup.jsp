<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		BoardPopup.USE_AT = "${boardVO.useAt }";
		BoardPopup.formInitDetail();
	});
//-->
</script>

<form:form name="formBoard" id="formBoard" action="retrieveBoardDetail.tech" method="post" commandName="boardVO">
	<form:hidden path="bbsId"/>
	<form:hidden path="nttId"/>
	<input type="hidden" name="viewTy" value="popup">
	<cmmUtil:conditionInput condObject="${boardVO }"/>
	<cmmUtil:ajaxToken name="_dt"/>
</form:form>

<div class="popup_title_sm">
	<h1 id="progrmNm"><c:out value="${boardMasterVO.bbsNm }" />&nbsp;상세조회</h1>
</div>

<div class="popup_contents">
	<div class="tbl_result_list">
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
					<th>제목</th>
					<td colspan="5" class="text_left">
						<c:choose>
							<c:when test="${boardVO.useAt eq 'Y' }">
								<c:choose>
									<c:when test="${boardVO.noticeAt eq 'Y' }">
										<strong style="color: #FF9436;">[공지]&nbsp;<c:out value="${boardVO.nttSj }" /></strong>
									</c:when>
									<c:otherwise>
										<c:out value="${boardVO.nttSj }" />&nbsp;
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								블라인드 처리된 게시글 입니다.
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				
				<c:if test="${boardMasterVO.bbsTyCode eq 'BBST02' }">
					<tr>
						<th>공지기간</th>
						<td colspan="5" class="text_left">
							<cmmUtil:strFormat pattern="-" value="${boardVO.noticeBgnDe }" format="####-##-##"/>&nbsp;~
							<cmmUtil:strFormat pattern="-" value="${boardVO.noticeEndDe }" format="####-##-##"/>
						</td>
					</tr>
				</c:if>
				
				<tr>
					<th>조회수</th>
					<td class="text_left"><fmt:formatNumber value="${boardVO.rdcnt }" />&nbsp;</td>
					<th>작성자</th>
					<td class="text_left"><c:out value="${boardVO.frstRegisterNm }" />&nbsp;</td>
					<th>작성일시</th>
					<td class="text_left"><fmt:formatDate value="${boardVO.frstRegistPnttm }" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;</td>
				</tr>
				
				<c:if test="${boardMasterVO.fileAtchPosblAt eq 'Y' }">
					<tr>
						<th>첨부파일</th>
						<td class="attachFile text_left" colspan="5">
							<ul class="atchFileList">
								<c:forEach var="storageFile" items="${boardVO.listAtchFiles }">
									<c:url var="fileDownUrl" value="/storage/storageFileDownload.tech">
										<c:param name="atchFileId">${storageFile.atchFileId }</c:param>
										<c:param name="fileSn">${storageFile.fileSn }</c:param>
									</c:url>
									
									<li>
										<a href="${fileDownUrl }">${storageFile.fileNm }&nbsp;(<cmmUtil:fileSizeFormat value="${storageFile.fileSize }" format="2" />)</a>
									</li>
								</c:forEach>
							</ul>
						</td>
					</tr>
				</c:if>
				
				<tr>
					<td colspan="6">
						<c:choose>
							<c:when test="${boardVO.useAt eq 'Y' }">
								<form action="${ctxPath }/template/jsp/boardContentsView.jsp" name="boardContentsForm" id="boardContentsForm" method="post" target="boardContentsView">
									<input type="hidden" name="nttCn" id="nttCn" value="<c:out value="${boardVO.nttCn }" />">
								</form>
								
								<iframe name="boardContentsView" id="boardContentsView" scrolling="no" frameborder="0" width="100%" onload="autoHeightIframe(this);" style="min-height: 150px;"></iframe>
							</c:when>
							<c:otherwise>
								블라인드 처리된 게시글 입니다.
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>

<div class="popup_bottom">
	<div class="popup_btnGroup">
		<button type="button" class="gen_btn _command[BoardPopup.listView]" accesskey="1">목록</button>
		<button type="button" class="gen_btn _command[BoardPopup.popupClose]" accesskey="0">닫기</button>
	</div>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />