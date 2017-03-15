<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/courage/tech/system/code/commonDetailCode.js"></script>
<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		CommonDetailCode.TOTAL_CNT = '<c:out value="${pagination.totalRecordCount }" />';
		CommonDetailCode._subformInit();
		
		// 상위 페이지의 권한을 승계받아 처리
		// CommonDetailCode.js는 공통사용 이라 해당되는 페이지에 직접 정의함
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
	});
//-->
</script>

<form:form name="formCommonDetailCode" id="formCommonDetailCode" action="retrieveCmmnDetailCodeList.tech" method="post" commandName="cmmnDetailCodeVO">
	<!-- 페이지 유지정보 : begin -->
	<form:hidden path="pageIndex"/>
	<form:hidden path="pagingEnable"/>
	<form:hidden path="recordCountPerPage"/>
	<form:hidden path="pageSize"/>
	<!-- 페이지 유지정보 : end -->
	
	<input type="hidden" name="actionStatus" id="actionStatus" value="${actionStatus }">
	<input type="hidden" name="codeId" id="codeId" value="${cmmnDetailCodeVO.condCodeId }">
	<form:hidden path="code"/>
	<form:hidden path="condCodeId"/>
	<form:hidden path="condOrder"/>
	<form:hidden path="condAlign"/>
</form:form>

<div style="display: inline-block; width: 100%;">
	<h3 id="listSubTitle" class="subTitle left staticHeightArea">공통 상세코드 목록</h3>
	
	<div class="buttonSet right">
		<button type="button" id="modifyCommonCode" class="gen_btn needCreate _command[CommonDetailCode.createView]" accesskey="3">공통 상세코드 등록</button>
		<button type="button" id="modifyCommonCode" class="gen_btn _command[CommonDetailCode.excelDownload]" accesskey="4"><spring:message code="button.exel" /></button>
	</div>
</div>
	
<div id="data_list_area" class="ct_list_area">
	<div id="listArea" class="type_list">
		<div id="listTableFix" class="list_table_header">
			<table class="header" cellspacing="0" cellpadding="0" summary="목록 항목입니다.">
				<colgroup>
					<col style="width: 120px;">
					<col>
					<col style="width: 80px;">
					<col style="width: 80px;">
					<col style="width: 110px;">
					<col style="width: 160px;">
				</colgroup>
				
				<thead>
					<tr>
						<th><a id="code" class="_command[_listHeaderToggle(formCommonDetailCode)|CommonDetailCode.refreshList]">상세코드</a></th>
						<th class="separator"><a id="code_nm" class="_command[_listHeaderToggle(formCommonDetailCode)|CommonDetailCode.refreshList]">상세코드명</a></th>
						<th class="separator"><a id="use_at" class="_command[_listHeaderToggle(formCommonDetailCode)|CommonDetailCode.refreshList]">사용여부</a></th>
						<th class="separator"><a id="ordr" class="_command[_listHeaderToggle(formCommonDetailCode)|CommonDetailCode.refreshList]">정렬순서</a></th>
						<th class="separator">등록자</th>
						<th class="separator"><a id="frst_regist_pnttm" class="_command[_listHeaderToggle(formCommonDetailCode)|CommonDetailCode.refreshList]">등록일시</a></th>
					</tr>
				</thead>
			</table>
		</div>
		
		<div id="table_list" class="list_table_box">
			<table cellspacing="0" cellpadding="0" summary="상세코드 목록">
				<colgroup>
					<col style="width: 120px;">
					<col>
					<col style="width: 80px;">
					<col style="width: 80px;">
					<col style="width: 110px;">
					<col style="width: 142px;#width:160px;"><!-- Header size보다 18px 작게 잡아야함. 스크롤 영역 (동적처리는 IE 미지원으로 고정값 설정) -->
				</colgroup>
				
				<tbody>
					<c:choose>
						<c:when test="${fn:length(cmmnDetailCodeList) <= 0 }">
							<tr>
								<td colspan="6"><spring:message code="info.nodata.msg" /></td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="cmmnDetailCodeVO" items="${cmmnDetailCodeList }" varStatus="sttus">
								<tr>
									<td>${cmmnDetailCodeVO.code }</td>
									<td class="Ltxt">
										<div class="ellipsis" title="${cmmnDetailCodeVO.codeNm }">
											<a id="${cmmnDetailCodeVO.code }" class="_command[CommonDetailCode.detailView]">${cmmnDetailCodeVO.codeNm }</a>
										</div>
									</td>
									<td><cmmCd:cdValue codeId="COM045" code="${cmmnDetailCodeVO.useAt }" /></td>
									<td>${cmmnDetailCodeVO.ordr }</td>
									<td>${cmmnDetailCodeVO.frstRegisterNm }</td>
									<td>${cmmnDetailCodeVO.frstRegistPnttm }</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		
		<c:if test="${fn:length(cmmnDetailCodeList) > 0 && cmmnDetailCodeVO.pagingEnable eq '1' }">
			<div id="pagination" class="pagination">
				<div class="listPaging">
					<ui:pagination paginationInfo="${pagination }" type="image" jsFunction="CommonDetailCode.fn_egov_link_page" />
				</div>
			</div>
		</c:if>
	</div>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />