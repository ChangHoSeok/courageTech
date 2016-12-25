<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		ProgrmPopup.formInit();
	});
//-->
</script>

<div class="popup_title_sm">
	<h1 id="progrmNm"></h1>
</div>

<div class="popup_contents">
	<div class="popup_search_box">
		<form:form name="formProgrmPopup" id="formProgrmPopup" action="retrieveProgrmPopupList.do" method="post" commandName="progrmVO">
			<!-- 페이지 유지정보 : begin -->
			<form:hidden path="pageIndex"/>
			<form:hidden path="pagingEnable"/>
			<form:hidden path="recordCountPerPage"/>
			<form:hidden path="pageSize"/>
			<!-- 페이지 유지정보 : end -->
			<form:hidden path="condOrder"/>
			<form:hidden path="condAlign"/>
			
			<table>
				<colgroup>
					<col style="width: 80px;" />
					<col style="width: 150px;" />
					<col style="width: 100px;" />
					<col style="width: 150px;" />
					<col style="width: 80px;" />
					<col />
					<col />
				</colgroup>
				<tr>
					<th>파일명</th>
					<td><form:input path="condProgrmFileNm" cssClass="_enter[ProgrmPopup.refreshList]" cssStyle="width: 95%;"/></td>
					<th>프로그램구분</th>
					<td><cmmCd:cdSelect codeId="CURG002" name="condProgrmSe" showAll="true" defaultCode="${progrmVO.condProgrmSe }" styleClass="_enter[ProgrmPopup.refreshList]" use="true" /></td>
					<th>기능구분</th>
					<td><cmmCd:cdSelect codeId="CURG003" name="condProgrmFncSe" showAll="true" defaultCode="${progrmVO.condProgrmFncSe }" styleClass="_enter[ProgrmPopup.refreshList]" use="true" /></td>
					<td><button class="gen_btn _command[ProgrmPopup.search]"><spring:message code="button.search" /></button></td>
				</tr>
				<tr>
					<th>프로그램명</th>
					<td><form:input path="condProgrmKoreanNm" cssClass="_enter[ProgrmPopup.refreshList]" cssStyle="width: 95%;"/></td>
					<th>URL</th>
					<td colspan="4"><form:input path="condUrl" cssClass="_enter[ProgrmPopup.refreshList]" cssStyle="width: 98%px;"/></td>
				</tr>
			</table>
		</form:form>
	</div>

	<div class="result_txt">
		총<span><fmt:formatNumber value="${empty pagination.totalRecordCount ? 0 : pagination.totalRecordCount }" /></span>건이 조회 되었습니다.
	</div>

	<div class="tbl_result_list">
		<table cellspacing="0" cellpadding="0">
			<colgroup>
				<col style="width: 40px;" />
				<col style="width: 100px;" />
				<col />
				<col />
				<col style="width: 250px;" />
				<col style="width: 150px;" />
			</colgroup>
			
			<thead>
				<tr>
					<th><input type="checkbox" name="allCheck" id="allCheck" class="_command[ProgrmPopup.progrmAllCheck]"></th>
					<th>프로그램 구분</th>
					<th>프로그램 파일명</th>
					<th>프로그램명</th>
					<th>URL</th>
					<th>프로그램 설명</th>
				</tr>
			</thead>
			
			<tbody>
				<c:choose>
					<c:when test="${fn:length(progrmList) <= 0 }">
						<tr>
							<td colspan="6"><spring:message code="info.nodata.msg" /></td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="progrmListVO" items="${progrmList }" varStatus="sttus">
							<tr>
								<td>
									<input type="checkbox" name="progrmChk" id="${progrmListVO.progrmFileNm }" title="선택" />
									<input type="hidden" name="progrmFileNm" id="progrmFileNm_${progrmListVO.progrmFileNm }" value="${progrmListVO.progrmFileNm }">
									<input type="hidden" name="progrmSe" id="progrmSe_${progrmListVO.progrmFileNm }" value="${progrmListVO.progrmSe }">
									<input type="hidden" name="progrmStrePath" id="progrmStrePath_${progrmListVO.progrmFileNm }" value="${progrmListVO.progrmStrePath }">
									<input type="hidden" name="progrmKoreanNm" id="progrmKoreanNm_${progrmListVO.progrmFileNm }" value="${progrmListVO.progrmKoreanNm }">
									<input type="hidden" name="progrmDc" id="progrmDc_${progrmListVO.progrmFileNm }" value="${progrmListVO.progrmDc }">
									<input type="hidden" name="url" id="url_${progrmListVO.progrmFileNm }" value="${progrmListVO.url }">
								</td>
								<td><cmmCd:cdValue code="${progrmListVO.progrmSe }" codeId="CURG002" />&nbsp;</td>
								<td class="tL pl10">
									<div class="ellipsis" title="<c:out value="${progrmListVO.progrmFileNm }" />"><c:out value="${progrmListVO.progrmFileNm }" />&nbsp;</div>
								</td>
								<td class="tL pl10">
									<div class="ellipsis" title="<c:out value="${progrmListVO.progrmKoreanNm }" />"><c:out value="${progrmListVO.progrmKoreanNm }" />&nbsp;</div>
								</td>
								<td class="tL pl10">
									<div class="ellipsis" title="<c:out value="${progrmListVO.url }" />"><c:out value="${progrmListVO.url }" />&nbsp;</div>
								</td>
								<td class="tL pl10">
									<div class="ellipsis" title="<c:out value="${progrmListVO.progrmDc }" />"><c:out value="${progrmListVO.progrmDc }" />&nbsp;</div>
								</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		
		<c:if test="${fn:length(progrmList) > 0 && progrmVO.pagingEnable eq '1' }">
			<div id="pagination" class="pagination">
				<div class="listPaging">
					<ui:pagination paginationInfo="${pagination }" type="image" jsFunction="ProgrmPopup.fn_egov_link_page" />
				</div>
			</div>
		</c:if>
	</div>
</div>

<div class="popup_bottom">
	<div class="popup_btnGroup">
		<button type="button" class="gen_btn needCreate _command[ProgrmPopup.setProgrm]" accesskey="1">확인</button>
		<button type="button" class="gen_btn _command[ProgrmPopup.popupClose]" accesskey="0">닫기</button>
	</div>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />