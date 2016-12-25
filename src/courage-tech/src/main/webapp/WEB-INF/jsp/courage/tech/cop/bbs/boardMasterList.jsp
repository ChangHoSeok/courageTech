<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>
<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		BoardMaster.TOTAL_CNT = '<c:out value="${pagination.totalRecordCount }" />';
		BoardMaster.formInitList();
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
	
	<form:form name="formBoardMaster" id="formBoardMaster" action="retrieveBoardMasterList.do" method="post" commandName="boardMasterVO">
		<!-- 페이지 유지정보 : begin -->
		<form:hidden path="pageIndex"/>
		<form:hidden path="pagingEnable"/>
		<form:hidden path="recordCountPerPage"/>
		<form:hidden path="pageSize"/>
		<!-- 페이지 유지정보 : end -->
		
		<form:hidden path="bbsId"/>
		<form:hidden path="condOrder"/>
		<form:hidden path="condAlign"/>
		<cmmUtil:ajaxToken name="_dt"/>
		
		<!-- 상세조회 : begin -->
		<div id="detail_search" class="section_detail_search staticHeightArea">
			<fieldset>
				<div class="item_row">
					<table>
						<colgroup>
							<col style="width:100px;"/>
	                   		<col style="width:150px;"/>
	                   		<col style="width:100px;"/>
	                   		<col style="width:150px;"/>
	                   		<col style="width:100px;"/>
	                        <col />
						</colgroup>
						
						<tbody>
							<tr>
								<th>게시판 명</th>
								<td><form:input path="condBbsNm" cssClass="_enter[BoardMaster.search]" cssStyle="width: 90%;"/></td>
								<th>게시판 유형</th>
								<td><cmmCd:cdSelect name="condBbsTyCode" id="condBbsTyCode" codeId="COM004" defaultCode="${boardMasterVO.condBbsTyCode }" showAll="true" use="true"/></td>
								<th>게시판 속성</th>
								<td><cmmCd:cdSelect name="condAttrbCode" id="condAttrbCode" codeId="COM009" defaultCode="${boardMasterVO.condAttrbCode }" showAll="true" use="true"/></td>
	                        </tr>
	                        <tr>
								<th>답글가능</th>
								<td><cmmCd:cdSelect name="condReplyPosblAt" id="condReplyPosblAt" codeId="COM038" defaultCode="${boardMasterVO.condReplyPosblAt }" showAll="true" use="true"/></td>
								<th>댓글가능</th>
								<td><cmmCd:cdSelect name="condAnswerPosblAt" id="condAnswerPosblAt" codeId="COM038" defaultCode="${boardMasterVO.condAnswerPosblAt }" showAll="true" use="true"/></td>
								<th>파일첨부</th>
								<td><cmmCd:cdSelect name="condFileAtchPosblAt" id="condFileAtchPosblAt" codeId="COM038" defaultCode="${boardMasterVO.condFileAtchPosblAt }" showAll="true" use="true"/></td>
	                        </tr>
						</tbody>
					</table>
					
					<div class="tbl_search">
						<button type="button" id="search" class="gen_btn btn_srch_submit _command[BoardMaster.search]"><spring:message code="button.search" /></button>
					</div>
				</div>	
			</fieldset>
		</div>
		<!-- 상세조회 : end -->
		
		<div id="contentBtnFix" class="button_area staticHeightArea">
			<div class="buttonSet left">
				<button type="button" class="gen_btn needCreate _command[BoardMaster.createView]" accesskey="2"><spring:message code="button.new.create" /></button>
				<button type="button" id="downExcel" class="gen_btn _command[BoardMaster.excelDownload]" accesskey="3"><spring:message code="button.exel" /></button>
			</div>
		</div>
	</form:form>
</div>

<div id="data_list_area" class="type_list">
	<div id="listTableFix" class="list_table_header">
		<table class="header" cellspacing="0" cellpadding="0" summary="목록 항목입니다.">
			<colgroup>
				<col style="width:160px;"/>
				<col/>
				<col style="width:120px;"/>
				<col style="width:120px;"/>
				<col style="width:70px;"/>
				<col style="width:70px;"/>
				<col style="width:70px;"/>
				<col style="width:100px;"/>
			</colgroup>
			
			<thead>
				<tr>
					<th><a id="bbs_id" class="_command[_listHeaderToggle|BoardMaster.refreshList]">게시판ID</a></th>
					<th class="separator"><a id="bbs_nm" class="_command[_listHeaderToggle|BoardMaster.refreshList]">게시판 명</a></th>
					<th class="separator"><a id="bbs_ty_code" class="_command[_listHeaderToggle|BoardMaster.refreshList]">게시판 유형</a></th>
					<th class="separator"><a id="bbs_attrb_code" class="_command[_listHeaderToggle|BoardMaster.refreshList]">게시판 속성</a></th>
					<th class="separator"><a id="reply_posbl_at" class="_command[_listHeaderToggle|BoardMaster.refreshList]">답글가능</a></th>
					<th class="separator"><a id="answer_posbl_at" class="_command[_listHeaderToggle|BoardMaster.refreshList]">댓글가능</a></th>
					<th class="separator"><a id="file_atch_posbl_at" class="_command[_listHeaderToggle|BoardMaster.refreshList]">파일첨부</a></th>
					<th class="separator"><a id="use_at" class="_command[_listHeaderToggle|BoardMaster.refreshList]">사용여부</a></th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div id="table_list" class="list_table_box">
		<table cellspacing="0" cellpadding="0">
			<colgroup>
				<col style="width:160px;"/>
				<col/>
				<col style="width:120px;"/>
				<col style="width:120px;"/>
				<col style="width:70px;"/>
				<col style="width:70px;"/>
				<col style="width:70px;"/>
				<col style="width:82px;#width:100px;"/><!-- Header size보다 18px 작게 잡아야함. 스크롤 영역 (동적처리는 IE 미지원으로 고정값 설정) -->
			</colgroup>
			
			<tbody>
				<c:choose>
					<c:when test="${fn:length(boardMasterList) <= 0 }">
						<tr>
							<td colspan="8"><spring:message code="info.nodata.msg" /></td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="boardMasterListVO" items="${boardMasterList }" varStatus="sttus">
							<tr>
								<td>${boardMasterListVO.bbsId }&nbsp;</td>
								<td class="Ltxt">
									<div class="ellipsis" title="<c:out value="${boardMasterListVO.bbsNm }" />">
										<a id="${boardMasterListVO.bbsId }" class="_command[BoardMaster.detailView]">
											<c:out value="${boardMasterListVO.bbsNm }" />&nbsp;
										</a>
									</div>
								</td>
								<td><cmmCd:cdValue code="${boardMasterListVO.bbsTyCode }" codeId="COM004" />&nbsp;</td>
								<td><cmmCd:cdValue code="${boardMasterListVO.bbsAttrbCode }" codeId="COM009" />&nbsp;</td>
								<td><cmmCd:cdValue code="${boardMasterListVO.replyPosblAt }" codeId="COM038" />&nbsp;</td>
								<td><cmmCd:cdValue code="${boardMasterListVO.answerPosblAt }" codeId="COM038" />&nbsp;</td>
								<td><cmmCd:cdValue code="${boardMasterListVO.fileAtchPosblAt }" codeId="COM038" />&nbsp;</td>
								<td>${boardMasterListVO.useAt }&nbsp;</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	
	<c:if test="${fn:length(boardMasterList) > 0 && boardMasterVO.pagingEnable eq '1' }">
		<div id="pagination" class="pagination">
			<div class="listPaging">
				<ui:pagination paginationInfo="${pagination }" type="image" jsFunction="BoardMaster.fn_egov_link_page" />
			</div>
		</div>
	</c:if>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />