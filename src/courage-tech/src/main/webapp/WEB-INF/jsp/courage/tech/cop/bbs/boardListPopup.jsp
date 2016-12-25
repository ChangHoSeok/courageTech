<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="kr.pe.courage.common.prop.PropertiesMap"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		BoardPopup.formInit();
	});
//-->
</script>

<div class="popup_title_sm">
	<h1 id="progrmNm"><c:out value="${boardMasterVO.bbsNm }" />&nbsp;목록조회</h1>
</div>

<div class="popup_contents">
	<form:form name="formBoard" id="formBoard" action="retrieveBoardPopupList.do" method="post" commandName="boardVO">
		<!-- 페이지 유지정보 : begin -->
		<form:hidden path="pageIndex"/>
		<form:hidden path="pagingEnable"/>
		<form:hidden path="recordCountPerPage"/>
		<form:hidden path="pageSize"/>
		<!-- 페이지 유지정보 : end -->
		
		<form:hidden path="bbsId"/>
		<form:hidden path="nttId"/>
		<input type="hidden" name="viewTy" value="popup">
		<cmmUtil:ajaxToken name="_dt"/>
	</form:form>

	<div class="result_txt">
		총<span><fmt:formatNumber value="${empty pagination.totalRecordCount ? 0 : pagination.totalRecordCount }" /></span>건이 조회 되었습니다.
	</div>

	<div class="tbl_result_list">
		<table cellspacing="0" cellpadding="0">
			<colgroup>
				<col style="width:80px;"/>
				<col/>
				<col style="width:120px;"/>
				<col style="width:130px;"/>
				<col style="width:100px;"/>
			</colgroup>
			
			<thead>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일시</th>
					<th>조회수</th>
				</tr>
			</thead>
			
			<tbody>
				<c:choose>
					<c:when test="${fn:length(boardList) <= 0 }">
						<tr>
							<td colspan="5"><spring:message code="info.nodata.msg" /></td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="boardListVO" items="${boardList }" varStatus="sttus">
							<tr>
								<td>${boardListVO.nttNo }&nbsp;</td>
								<td class="Ltxt">
									<c:choose>
										<c:when test="${boardListVO.useAt eq 'Y' }">
											<div class="ellipsis" title="<c:out value="${boardListVO.nttSj }" />">
												<%-- 게시판 속성에 따른 ICON 설정 --%>
												<c:choose>
													<c:when test="${boardMasterVO.bbsAttrbCode eq 'BBSA01' }">
														<c:if test="${boardListVO.parntsSntncNo > 0 }">
															<img src="${ctxPath }/images/courage/tech/bbs/reply.gif" alt="[답글]" class="reply">&nbsp;&nbsp;
														</c:if>
													</c:when>
													<c:when test="${boardMasterVO.bbsAttrbCode eq 'BBSA03' && boardListVO.noticeAt ne 'Y' }">
														<%-- QNA 게시판의 질/답 아이콘 설정 --%>
														<c:choose>
															<c:when test="${boardListVO.parntsSntncNo <= 0 }">
																<img src="${ctxPath }/images/courage/tech/icon/icon_q.gif" alt="[질문]" class="nttQ">
															</c:when>
															<c:otherwise>
																<img src="${ctxPath }/images/courage/tech/icon/icon_a.gif" alt="[답변]" class="nttA">&nbsp;&nbsp;
															</c:otherwise>
														</c:choose>
													</c:when>
												</c:choose>
												
												<%-- 열람권한 설정 --%>
												<c:choose>
													<c:when test="${boardAuthorCheckVO.mngrAuthor > 0 || boardAuthorCheckVO.readng > 0 }">
														<a id="${boardListVO.nttId }" class="_command[BoardPopup.detailView] ${boardListVO.noticeAt eq 'Y' ? 'notice' : '' }">
															${boardListVO.noticeAt eq 'Y' ? '[공지]' : '' }
															<c:out value="${boardListVO.nttSj }" />
															<c:if test="${boardMasterVO.replyPosblAt eq 'Y' && boardListVO.answerCnt > 0 }">
																<span style="color: #E8925E">[<fmt:formatNumber value="${boardListVO.answerCnt }" />]</span>
															</c:if>
														</a>
													</c:when>
													<c:otherwise>
														<span class="${boardListVO.noticeAt eq 'Y' ? 'notice' : '' }">
															${boardListVO.noticeAt eq 'Y' ? '[공지]' : '' }
															<c:out value="${boardListVO.nttSj }" />
														</span>
														<c:if test="${boardMasterVO.replyPosblAt eq 'Y' && boardListVO.answerCnt > 0 }">
															<span style="color: #E8925E">[<fmt:formatNumber value="${boardListVO.answerCnt }" />]</span>
														</c:if>
													</c:otherwise>
												</c:choose>
											</div>
										</c:when>
										<c:otherwise>
											<div class="ellipsis" title="블라인드 처리된 게시글 입니다.">
												<c:choose>
													<c:when test="${boardMasterVO.bbsAttrbCode eq 'BBSA01' }">
														<c:if test="${boardListVO.parntsSntncNo > 0 }">
															<img src="${ctxPath }/images/courage/tech/bbs/reply.gif" alt="[답글]" class="reply">&nbsp;&nbsp;
														</c:if>
													</c:when>
													<c:when test="${boardMasterVO.bbsAttrbCode eq 'BBSA03' }">
														<%-- QNA 게시판의 질/답 아이콘 설정 --%>
														<c:choose>
															<c:when test="${boardListVO.parntsSntncNo <= 0 }">
																<img src="${ctxPath }/images/courage/tech/icon/icon_q.gif" alt="[질문]" class="nttQ">
															</c:when>
															<c:otherwise>
																<img src="${ctxPath }/images/courage/tech/icon/icon_a.gif" alt="[답변]" class="nttA">&nbsp;&nbsp;
															</c:otherwise>
														</c:choose>
													</c:when>
												</c:choose>
												
												블라인드 처리된 게시글 입니다.
												<c:if test="${boardMasterVO.replyPosblAt eq 'Y' && boardListVO.answerCnt > 0 }">
													<span style="color: #E8925E">[<fmt:formatNumber value="${boardListVO.answerCnt }" />]</span>
												</c:if>
											</div>
										</c:otherwise>
									</c:choose>
								</td>
								<td>
									<% pageContext.setAttribute("anonymousUniqid", PropertiesMap.getInstance().getValue("system.anonymous.uniqid")); %>
									<c:choose>
										<c:when test="${boardListVO.frstRegisterId eq anonymousUniqid }">
											익명사용자
										</c:when>
										<c:otherwise>
											<c:out value="${boardListVO.frstRegisterNm }" />&nbsp;
										</c:otherwise>
									</c:choose>
								</td>
								<td><fmt:formatDate value="${boardListVO.frstRegistPnttm }" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;</td>
								<td><fmt:formatNumber value="${boardListVO.rdcnt }" />&nbsp;</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		
		<c:if test="${fn:length(boardList) > 0 && boardVO.pagingEnable eq '1' }">
			<div id="pagination" class="pagination">
				<div class="listPaging">
					<ui:pagination paginationInfo="${pagination }" type="image" jsFunction="BoardPopup.fn_egov_link_page" />
				</div>
			</div>
		</c:if>
	</div>
</div>

<div class="popup_bottom">
	<div class="popup_btnGroup">
		<button type="button" class="gen_btn _command[BoardPopup.popupClose]" accesskey="0">닫기</button>
	</div>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />