<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="kr.pe.courage.common.prop.PropertiesMap"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>
<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		Board.TOTAL_CNT = '<c:out value="${pagination.totalRecordCount }" />';
		Board.formInitList();
	});
//-->
</script>

<div class="ct_area">
	<div id="contentInfo" class="path_search">
		<div id="progrmNm" class="left_path"><c:out value="${boardMasterVO.bbsNm }" />&nbsp;목록조회</div>
		<div class="right_path">
			<span id="menuNavi"></span>
		</div>
	</div>
	
	<form:form name="formBoard" id="formBoard" action="retrieveBoardList.do" method="post" commandName="boardVO">
		<!-- 페이지 유지정보 : begin -->
		<form:hidden path="pageIndex"/>
		<form:hidden path="pagingEnable"/>
		<form:hidden path="recordCountPerPage"/>
		<form:hidden path="pageSize"/>
		<!-- 페이지 유지정보 : end -->
		
		<form:hidden path="bbsId"/>
		<form:hidden path="nttId"/>
		<cmmUtil:ajaxToken name="_dt"/>
		
		<!-- 상세조회 : begin -->
		<div id="detail_search" class="section_detail_search staticHeightArea">
			<fieldset>
				<div class="item_row">
					<table>
						<colgroup>
							<col style="width:100px;"/>
	                   		<col style="width:250px;"/>
	                   		<col style="width:100px;"/>
	                        <col />
						</colgroup>
						
						<tbody>
							<tr>
								<th>제목</th>
								<td><form:input path="condNttSj" cssClass="_enter[Board.search]" cssStyle="width: 200px;"/></td>
								<th>작성자</th>
								<td><form:input path="condFrstRegisterNm" cssClass="_enter[Board.search]" cssStyle="width: 120px;"/></td>
							</tr>
							<tr>
								<th>내용</th>
								<td><form:input path="condNttCn" cssClass="_enter[Board.search]" cssStyle="width: 350px;"/></td>
	                        </tr>
						</tbody>
					</table>
					
					<div class="tbl_search">
						<button type="button" id="search" class="gen_btn btn_srch_submit _command[Board.search]"><spring:message code="button.search" /></button>
					</div>
				</div>	
			</fieldset>
		</div>
		<!-- 상세조회 : end -->
		
		<div id="contentBtnFix" class="button_area staticHeightArea">
			<div class="buttonSet left">
				<c:if test="${boardAuthorCheckVO.mngrAuthor > 0 || boardAuthorCheckVO.sntncWritng > 0 }">
					<button type="button" class="gen_btn needCreate _command[Board.createView]" accesskey="2"><spring:message code="button.new.create" /></button>
				</c:if>
			</div>
		</div>
	</form:form>
</div>

<div id="data_list_area" class="type_list">
	<div id="listTableFix" class="list_table_header">
		<table class="header" cellspacing="0" cellpadding="0" summary="목록 항목입니다.">
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
					<th class="separator">제목</th>
					<th class="separator">작성자</th>
					<th class="separator">작성일시</th>
					<th class="separator">조회수</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div id="table_list" class="list_table_box">
		<table cellspacing="0" cellpadding="0">
			<colgroup>
				<col style="width:80px;"/>
				<col/>
				<col style="width:120px;"/>
				<col style="width:130px;"/>
				<col style="width:82px;#width:100px;"/><!-- Header size보다 18px 작게 잡아야함. 스크롤 영역 (동적처리는 IE 미지원으로 고정값 설정) -->
			</colgroup>
			
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
														<a id="${boardListVO.nttId }" class="_command[Board.detailView] ${boardListVO.noticeAt eq 'Y' ? 'notice' : '' }">
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
	</div>
	
	<c:if test="${fn:length(boardList) > 0 && boardVO.pagingEnable eq '1' }">
		<div id="pagination" class="pagination">
			<div class="listPaging">
				<ui:pagination paginationInfo="${pagination }" type="image" jsFunction="Board.fn_egov_link_page" />
			</div>
		</div>
	</c:if>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />