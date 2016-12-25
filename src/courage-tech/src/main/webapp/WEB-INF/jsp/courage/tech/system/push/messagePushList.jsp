<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>
<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		MessagePush.TOTAL_CNT = '<c:out value="${pagination.totalRecordCount }" />';
		MessagePush.formInitList();
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
	
	<form:form name="formMessagePush" id="formMessagePush" action="retrieveMessagePushList.do" method="post" commandName="messagePushVO">
		<!-- 페이지 유지정보 : begin -->
		<form:hidden path="pageIndex"/>
		<form:hidden path="pagingEnable"/>
		<form:hidden path="recordCountPerPage"/>
		<form:hidden path="pageSize"/>
		<!-- 페이지 유지정보 : end -->
		
		<form:hidden path="mssageId"/>
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
	                   		<col style="width:100px;"/>
	                   		<col style="width:100px;"/>
	                        <col />
						</colgroup>
						
						<tbody>
							<tr>
								<th>발송여부</th>
								<td><cmmCd:cdSelect name="condMssageSndngAt" id="condMssageSndngAt" codeId="COM038" defaultCode="${messagePushVO.condMssageSndngAt }" showAll="true" use="true"/></td>
								<th>등록자</th>
								<td><form:input path="condFrstRegisterNm" cssClass="_enter[MessagePush.search]" cssStyle="width: 150px;"/></td>
	                        </tr>
						</tbody>
					</table>
					
					<div class="tbl_search">
						<button type="button" id="search" class="gen_btn btn_srch_submit _command[MessagePush.search]"><spring:message code="button.search" /></button>
					</div>
				</div>	
			</fieldset>
		</div>
		<!-- 상세조회 : end -->
		
		<div id="contentBtnFix" class="button_area staticHeightArea">
			<div class="buttonSet left">
				<button type="button" class="gen_btn needCreate _command[MessagePush.createView]" accesskey="2"><spring:message code="button.new.create" /></button>
				<button type="button" class="gen_btn _command[MessagePush.excelDownload]" accesskey="3"><spring:message code="button.exel" /></button>
			</div>
		</div>
	</form:form>
</div>

<div id="data_list_area" class="type_list">
	<div id="listTableFix" class="list_table_header">
		<table class="header" cellspacing="0" cellpadding="0" summary="목록 항목입니다.">
			<colgroup>
				<col/>
				<col style="width:80px;"/>
				<col style="width:150px;"/>
				<col style="width:180px;"/>
			</colgroup>
			
			<thead>
				<tr>
					<th>메시지 내용</th>
					<th class="separator"><a id="mssage_sndng_at" class="_command[_listHeaderToggle|MessagePush.refreshList]">발송여부</a></th>
					<th class="separator"><a id="frst_register_nm" class="_command[_listHeaderToggle|MessagePush.refreshList]">등록자</a></th>
					<th class="separator"><a id="frst_regist_pnttm" class="_command[_listHeaderToggle|MessagePush.refreshList]">등록일시</a></th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div id="table_list" class="list_table_box">
		<table cellspacing="0" cellpadding="0">
			<colgroup>
				<col/>
				<col style="width:80px;"/>
				<col style="width:150px;"/>
				<col style="width:162px;#width:180px;"/><!-- Header size보다 18px 작게 잡아야함. 스크롤 영역 (동적처리는 IE 미지원으로 고정값 설정) -->
			</colgroup>
			
			<tbody>
				<c:choose>
					<c:when test="${fn:length(messagePushList) <= 0 }">
						<tr>
							<td colspan="4"><spring:message code="info.nodata.msg" /></td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="messagePushListVO" items="${messagePushList }" varStatus="sttus">
							<tr>
								<td class="Ltxt">
									<div class="ellipsis" title="<c:out value="${messagePushListVO.mssageCn }" />">
										<a id="${messagePushListVO.mssageId }" class="_command[MessagePush.detailView]">
											<c:out value="${messagePushListVO.mssageCn }" />&nbsp;
										</a>
									</div>
									<input type="hidden" id="mssageCn_${messagePushListVO.mssageId }" value="${messagePushListVO.mssageCn }">
								</td>
								<td><cmmCd:cdValue code="${messagePushListVO.mssageSndngAt }" codeId="COM038" />&nbsp;</td>
								<td>${messagePushListVO.frstRegisterNm }&nbsp;</td>
								<td>${messagePushListVO.frstRegistPnttm }&nbsp;</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	
	<c:if test="${fn:length(messagePushList) > 0 && messagePushVO.pagingEnable eq '1' }">
		<div id="pagination" class="pagination">
			<div class="listPaging">
				<ui:pagination paginationInfo="${pagination }" type="image" jsFunction="MessagePush.fn_egov_link_page" />
			</div>
		</div>
	</c:if>
</div>

<!-- commonForm:Begin -->
<form name="popupForm" id="popupForm" method="post" action="">
    <input type="hidden" name="openerKey" id="openerKey" value="" />
    <input type="hidden" name="returnFunction" id="returnFunction" value="" />
</form>
<!-- commonForm:End -->

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />