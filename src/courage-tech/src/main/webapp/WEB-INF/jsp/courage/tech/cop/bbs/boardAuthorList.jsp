<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>
<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		BoardAuthor.TOTAL_CNT = '<c:out value="${pagination.totalRecordCount }" />';
		BoardAuthor.formInitList();
	});
//-->
</script>

<form:form name="formBoardAuthor" id="formBoardAuthor" action="retrieveBoardAuthorList.do" method="post" commandName="boardAuthorVO">
	<!-- 페이지 유지정보 : begin -->
	<form:hidden path="pageIndex"/>
	<form:hidden path="pagingEnable"/>
	<form:hidden path="recordCountPerPage"/>
	<form:hidden path="pageSize"/>
	<!-- 페이지 유지정보 : end -->
	
	<form:hidden path="condOrder"/>
	<form:hidden path="condAlign"/>
	<cmmUtil:ajaxToken name="_dt"/>
	
	<div class="ct_area">
		<div id="contentInfo" class="path_search">
			<div id="progrmNm" class="left_path"></div>
			<div class="right_path">
				<span id="menuNavi"></span>
			</div>
		</div>
		
		<c:if test="${validateCheck eq 'error' }">
			<div id="errorBox" class="errormsgbox staticHeightArea">
				<form:errors path="authorCodes" /><br />
				<form:errors path="bbsIds" /><br />
				<form:errors path="lists" /><br />
				<form:errors path="readngs" /><br />
				<form:errors path="sntncWritngs" /><br />
				<form:errors path="answerWritngs" /><br />
				<form:errors path="mngrAuthors" />
			</div>
		</c:if>
		
		<!-- 상세조회 : begin -->
		<div id="detail_search" class="section_detail_search staticHeightArea">
			<fieldset>
				<div class="item_row">
					<table>
						<colgroup>
							<col style="width:100px;"/>
							<col style="width:150px;"/>
							<col style="width:100px;"/>
	                        <col />
						</colgroup>
						
						<tbody>
							<tr>
								<th>조회기준</th>
								<td>
									<label><input type="radio" name="condRetrieveStdr" value="01" class="_command[BoardAuthor.search]" style="margin: 0 5px;" ${empty boardAuthorVO.condRetrieveStdr || boardAuthorVO.condRetrieveStdr eq '01' ? 'checked="checked"' : '' }>게시판</label>
									<label><input type="radio" name="condRetrieveStdr" value="02" class="_command[BoardAuthor.search]" style="margin: 0 5px;" ${boardAuthorVO.condRetrieveStdr eq '02' ? 'checked="checked"' : '' }>권한</label>
								</td>
								<c:choose>
									<c:when test="${empty boardAuthorVO.condRetrieveStdr || boardAuthorVO.condRetrieveStdr eq '01' }">
										<th>게시판명</th>
										<td>
											<cmmCd:cdSelect name="condBbsId" operation="boardMasterDAO.selectCommonCodeListForBoardMaster"
												defaultCode="${boardAuthorVO.condBbsId }"
												showSelect="true"
												use="true"
												styleClass="validate[required]"
												onChange="BoardAuthor.search();" />
										</td>
									</c:when>
									<c:otherwise>
										<th>권한명</th>
										<td>
											<cmmCd:cdSelect name="condAuthorCode" operation="AuthorManage.selectCommonCodeListForAuthorInfo"
												defaultCode="${boardAuthorVO.condAuthorCode }"
												showSelect="true"
												use="true"
												styleClass="validate[required]"
												onChange="BoardAuthor.search();" />
										</td>
									</c:otherwise>
								</c:choose>
	                        </tr>
						</tbody>
					</table>
				</div>
			</fieldset>
		</div>
		<!-- 상세조회 : end -->
		
		<div id="contentBtnFix" class="button_area staticHeightArea">
			<div class="buttonSet left">
				<button type="button" class="gen_btn needCreate _command[BoardAuthor.save]" accesskey="1" ${pagination.totalRecordCount > 0 ? '' : 'disabled="disabled"'}>
					<spring:message code="button.save" />
				</button>
			</div>
		</div>
	</div>

	<div id="data_list_area" class="type_list">
		<div id="listTableFix" class="list_table_header">
			<table class="header" cellspacing="0" cellpadding="0" summary="목록 항목입니다.">
				<colgroup>
					<col style="width:200px;"/>
					<col/>
					<col style="width:100px;"/>
					<col style="width:100px;"/>
					<col style="width:100px;"/>
					<col style="width:100px;"/>
					<col style="width:100px;"/>
				</colgroup>
				
				<thead>
					<tr>
						<c:choose>
							<c:when test="${empty boardAuthorVO.condRetrieveStdr || boardAuthorVO.condRetrieveStdr eq '01' }">
								<th>권한코드</th>
								<th class="separator">권한명</th>
							</c:when>
							<c:otherwise>
								<th>게시판ID</th>
								<th class="separator">게시판명</th>
							</c:otherwise>
						</c:choose>
						<th class="separator">목록</th>
						<th class="separator">열람</th>
						<th class="separator">글 작성</th>
						<th class="separator">댓글 작성</th>
						<th class="separator">관리자 권한</th>
					</tr>
				</thead>
			</table>
		</div>
		
		<div id="table_list" class="list_table_box">
			<table cellspacing="0" cellpadding="0">
				<colgroup>
					<col style="width:200px;"/>
					<col/>
					<col style="width:100px;"/>
					<col style="width:100px;"/>
					<col style="width:100px;"/>
					<col style="width:100px;"/>
					<col style="width:82px;#width:100px;"/><!-- Header size보다 18px 작게 잡아야함. 스크롤 영역 (동적처리는 IE 미지원으로 고정값 설정) -->
				</colgroup>
				
				<tbody>
					<c:choose>
						<c:when test="${fn:length(boardAuthorList) <= 0 }">
							<tr>
								<td colspan="7"><spring:message code="info.nodata.msg" /></td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="boardAuthorListVO" items="${boardAuthorList }" varStatus="sttus">
								<tr>
									<c:choose>
										<c:when test="${empty boardAuthorVO.condRetrieveStdr || boardAuthorVO.condRetrieveStdr eq '01' }">
											<td>${boardAuthorListVO.authorCode }&nbsp;</td>
											<td class="Ltxt">
												<div class="ellipsis" title="<c:out value="${boardAuthorListVO.authorNm }" />">
													<c:out value="${boardAuthorListVO.authorNm }" />&nbsp;
												</div>
												
												<input type="hidden" name="authorCodes" value="${boardAuthorListVO.authorCode }">
												<input type="hidden" name="bbsIds" value="${boardAuthorVO.condBbsId }">
											</td>
										</c:when>
										<c:otherwise>
											<td>${boardAuthorListVO.bbsId }&nbsp;</td>
											<td class="Ltxt">
												<div class="ellipsis" title="<c:out value="${boardAuthorListVO.bbsNm }" />">
													<c:out value="${boardAuthorListVO.bbsNm }" />&nbsp;
												</div>
												
												<input type="hidden" name="authorCodes" value="${boardAuthorVO.condAuthorCode }">
												<input type="hidden" name="bbsIds" value="${boardAuthorListVO.bbsId }">
											</td>
										</c:otherwise>
									</c:choose>
									
									<td><input type="checkbox" name="lists" value="" ${boardAuthorListVO.list eq 'Y' ? 'checked="checked"' : '' }></td>
									<td><input type="checkbox" name="readngs" value="" ${boardAuthorListVO.readng eq 'Y' ? 'checked="checked"' : '' }></td>
									<td><input type="checkbox" name="sntncWritngs" value="" ${boardAuthorListVO.sntncWritng eq 'Y' ? 'checked="checked"' : '' }></td>
									<td><input type="checkbox" name="answerWritngs" value="" ${boardAuthorListVO.answerWritng eq 'Y' ? 'checked="checked"' : '' }></td>
									<td><input type="checkbox" name="mngrAuthors" value="" ${boardAuthorListVO.mngrAuthor eq 'Y' ? 'checked="checked"' : '' }></td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		
		<c:if test="${fn:length(boardAuthorList) > 0 && boardAuthorVO.pagingEnable eq '1' }">
			<div id="pagination" class="pagination">
				<div class="listPaging">
					<ui:pagination paginationInfo="${pagination }" type="image" jsFunction="BoardAuthor.fn_egov_link_page" />
				</div>
			</div>
		</c:if>
	</div>
</form:form>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />