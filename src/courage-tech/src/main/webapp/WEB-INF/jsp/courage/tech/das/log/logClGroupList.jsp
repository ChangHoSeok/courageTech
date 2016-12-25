<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>
<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		LogClGroup.TOTAL_CNT = '<c:out value="${pagination.totalRecordCount }" />';
		LogClGroup.formInitList();
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
	
	<form:form name="formLogClGroup" id="formLogClGroup" action="retrieveLogClGroupList.tech" method="post" commandName="logClGroupVO">
		<!-- 페이지 유지정보 : begin -->
		<form:hidden path="pageIndex"/>
		<form:hidden path="pagingEnable"/>
		<form:hidden path="recordCountPerPage"/>
		<form:hidden path="pageSize"/>
		<!-- 페이지 유지정보 : end -->
		
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
								<th>그룹ID</th>
								<td><form:input path="condGroupId" cssClass="_enter[LogClGroup.search]" cssStyle="width: 90%;"/></td>
								<th>그룹명</th>
								<td><form:input path="condGroupNm" cssClass="_enter[LogClGroup.search]" cssStyle="width: 90%;"/></td>
								<th>그룹설명</th>
								<td><form:input path="condGroupDc" cssClass="_enter[LogClGroup.search]" cssStyle="width: 90%;"/></td>
	                        </tr>
						</tbody>
					</table>
					
					<div class="tbl_search">
						<button type="button" id="search" class="gen_btn btn_srch_submit _command[LogClGroup.search]"><spring:message code="button.search" /></button>
					</div>
				</div>	
			</fieldset>
		</div>
		<!-- 상세조회 : end -->
	</form:form>
	
	<div id="contentBtnFix" class="button_area staticHeightArea needCreate">
		<span style="position: relative; top: 10px;">※. 정보를 수정하려면 더블클릭 하세요</span>
	</div>
</div>

<div id="data_list_area" class="type_list">
	<div id="listTableFix" class="list_table_header">
		<table class="header" cellspacing="0" cellpadding="0" summary="목록 항목입니다.">
			<colgroup>
				<col style="width:180px;"/>
				<col style="width:180px;"/>
				<col/>
				<col style="width:100px;"/>
			</colgroup>
			
			<thead>
				<tr>
					<th><a id="group_id" class="_command[_listHeaderToggle|LogClGroup.refreshList]">그룹ID</a></th>
					<th class="separator"><a id="group_nm" class="_command[_listHeaderToggle|LogClGroup.refreshList]">그룹명</a></th>
					<th class="separator">그룹설명</th>
					<th class="separator">비고</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div id="table_list" class="list_table_box">
		<form:form name="formLogClGroupCreate" id="formLogClGroupCreate" action="createLogClGroupProc.tech" method="post" commandName="logClGroupVO" cssClass="needCreate">
			<table cellspacing="0" cellpadding="0">
				<colgroup>
					<col style="width:180px;"/>
					<col style="width:180px;"/>
					<col/>
					<col style="width:82px;#width:100px;"/><!-- Header size보다 18px 작게 잡아야함. 스크롤 영역 (동적처리는 IE 미지원으로 고정값 설정) -->
				</colgroup>
				
				<tbody>
					<tr>
						<td class="Ltxt">
							<form:input path="groupId" cssClass="input-text required_textbox validF validate[required,custom[onlyLetterNumber],maxSize[5]] _enter[LogClGroup.save]" maxlength="5" cssStyle="width: 140px;"/>
						</td>
						<td class="Ltxt">
							<form:input path="groupNm" cssClass="input-text required_textbox validate[required,maxSize[64]] _enter[LogClGroup.save]" maxlength="64" cssStyle="width: 140px;"/>
						</td>
						<td class="Ltxt">
							<form:input path="groupDc" cssClass="input-text required_textbox validate[required,maxSize[1000]] _enter[LogClGroup.save]" maxlength="1000" cssStyle="width: 490px;"/>
						</td>
						<td>
							<button type="button" class="gen_btn _command[LogClGroup.save]">
								<spring:message code="button.add" />
							</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form:form>
		
		<form:form name="formLogClGroupModify" id="formLogClGroupModify" action="modifyLogClGroupProc.tech" method="post" commandName="logClGroupVO">
			<table cellspacing="0" cellpadding="0">
				<colgroup>
					<col style="width:180px;"/>
					<col style="width:180px;"/>
					<col/>
					<col style="width:82px;#width:100px;"/><!-- Header size보다 18px 작게 잡아야함. 스크롤 영역 (동적처리는 IE 미지원으로 고정값 설정) -->
				</colgroup>
				
				<tbody>
					<c:choose>
						<c:when test="${fn:length(logClGroupList) <= 0 }">
							<tr>
								<td colspan="4"><spring:message code="info.nodata.msg" /></td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="logClGroupListVO" items="${logClGroupList }" varStatus="sttus">
								<tr id="view_${logClGroupListVO.groupId }" class="dbclickListener viewTr">
									<td>
										<div class="ellipsis Ctxt" title="<c:out value="${logClGroupListVO.groupId }" />">
											<c:out value="${logClGroupListVO.groupId }" />&nbsp;
										</div>
									</td>
									<td>
										<div class="ellipsis Ctxt" title="<c:out value="${logClGroupListVO.groupNm }" />">
											<c:out value="${logClGroupListVO.groupNm }" />&nbsp;
										</div>
									</td>
									<td class="Ltxt">
										<div class="ellipsis" title="<c:out value="${logClGroupListVO.groupDc }" />">
											<c:out value="${logClGroupListVO.groupDc }" />&nbsp;
										</div>
									</td>
									<td>
										<button type="button" id="btnDelete_${logClGroupListVO.groupId }" class="gen_btn needCreate _command[LogClGroup.deleteLogClGroup]">
											<spring:message code="button.delete" />
										</button>
									</td>
								</tr>
								
								<tr id="editor_${logClGroupListVO.groupId }" class="editorTr needCreate" style="display: none;">
									<td>
										<div class="ellipsis Ctxt" title="<c:out value="${logClGroupListVO.groupId }" />">
											<c:out value="${logClGroupListVO.groupId }" />&nbsp;
										</div>
										<input type="hidden" name="groupId" id="groupId_${logClGroupListVO.groupId }" value="${logClGroupListVO.groupId }">
									</td>
									<td class="Ltxt">
										<input type="text" name="groupNm" id="groupNm_${logClGroupListVO.groupId }"
											class="input-text required_textbox validate[required,maxSize[64]] _enter[LogClGroup.save]"
											maxlength="64"
											style="width: 140px;"
											value="${logClGroupListVO.groupNm }">
									</td>
									<td class="Ltxt">
										<input type="text" name="groupDc" id="groupDc_${logClGroupListVO.groupId }"
											class="input-text required_textbox validate[required,maxSize[1000]] _enter[LogClGroup.save]"
											maxlength="1000"
											style="width: 490px;"
											value="${logClGroupListVO.groupDc }">
									</td>
									<td>
										<button type="button" id="btn_${logClGroupListVO.groupId }" class="gen_btn _command[LogClGroup.save]">
											<spring:message code="button.save" />
										</button>
									</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</form:form>
	</div>
	
	<c:if test="${fn:length(logClGroupList) > 0 && logClGroupVO.pagingEnable eq '1' }">
		<div id="pagination" class="pagination">
			<div class="listPaging">
				<ui:pagination paginationInfo="${pagination }" type="image" jsFunction="LogClGroup.fn_egov_link_page" />
			</div>
		</div>
	</c:if>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />