<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>
<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		User.TOTAL_CNT = '<c:out value="${pagination.totalRecordCount }" />';
		User.formInitList();
		
		setDept = function(deptVO) {
			$jquery("#" + User.FORM_ID + " #condDeptNm").val(deptVO.lowestDeptNm);
			$jquery("#" + User.FORM_ID + " #condDeptCode").val(deptVO.deptCode);
		};
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
	
	<form:form name="formUser" id="formUser" action="retrieveUserList.do" method="post" commandName="userVO">
		<!-- 페이지 유지정보 : begin -->
		<form:hidden path="pageIndex"/>
		<form:hidden path="pagingEnable"/>
		<form:hidden path="recordCountPerPage"/>
		<form:hidden path="pageSize"/>
		<!-- 페이지 유지정보 : end -->
		
		<form:hidden path="esntlId"/>
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
	                   		<col style="width:200px;"/>
	                   		<col style="width:100px;"/>
	                   		<col style="width:200px;"/>
	                   		<col style="width:100px;"/>
	                        <col />
						</colgroup>
						
						<tbody>
							<tr>
								<th>사용자ID</th>
								<td><form:input path="condEmplyrId" cssClass="_enter[User.search]" cssStyle="width: 95%;"/></td>
								<th>사용자명</th>
								<td><form:input path="condUserNm" cssClass="_enter[User.search]" cssStyle="width: 95%;"/></td>
								<th>회원구분</th>
								<td><cmmCd:cdSelect codeId="COM012" name="condMberSe" showAll="true" defaultCode="${userVO.condMberSe }" styleClass="_enter[User.search]" use="true" /></td>
	                        </tr>
	                        <tr>
								<th>부서</th>
								<td colspan="3">
									<form:input path="condDeptNm" cssClass="readonly" cssStyle="width: 300px;" readonly="true"/>
									<form:hidden path="condDeptCode" />
									<button type="button" id="deptSearch" style="height: 25px;" class="_command[User.deptPopupView]"></button>
									<button type="button" id="deptClear" style="height: 25px;" class="gen_btn _command[User.condDeptInit]"></button>
								</td>
								<th>상태</th>
								<td><cmmCd:cdSelect codeId="COM013" name="condEmplyrSttusCode" showAll="true" defaultCode="${userVO.condEmplyrSttusCode }" styleClass="_enter[User.search]" use="true" /></td>
	                        </tr>
						</tbody>
					</table>
					
					<div class="tbl_search">
						<button type="button" id="search" class="gen_btn btn_srch_submit _command[User.search]"><spring:message code="button.search" /></button>
					</div>
				</div>	
			</fieldset>
		</div>
		<!-- 상세조회 : end -->
		
		<div id="contentBtnFix" class="button_area staticHeightArea">
			<div class="buttonSet left">
				<button type="button" class="gen_btn needCreate _command[User.createView]" accesskey="2"><spring:message code="button.new.create" /></button>
				<button type="button" class="gen_btn needCreate _command[User.excelDownload]" accesskey="3"><spring:message code="button.exel" /></button>
			</div>
		</div>
	</form:form>
</div>

<div id="data_list_area" class="type_list">
	<div id="listTableFix" class="list_table_header">
		<table class="header" cellspacing="0" cellpadding="0" summary="목록 항목입니다.">
			<colgroup>
				<col style="width:200px;"/>
				<col style="width:200px;"/>
				<col/>
				<col style="width:150px;"/>
				<col style="width:100px;"/>
				<col style="width:150px;"/>
			</colgroup>
			
			<thead>
				<tr>
					<th>고유ID</th>
					<th class="separator"><a id="emplyr_id" class="_command[_listHeaderToggle|User.refreshList]">사용자ID</a></th>
					<th class="separator"><a id="user_nm" class="_command[_listHeaderToggle|User.refreshList]">사용자명</a></th>
					<th class="separator"><a id="mber_se" class="_command[_listHeaderToggle|User.refreshList]">회원구분</a></th>
					<th class="separator"><a id="emplyr_sttus_code" class="_command[_listHeaderToggle|User.refreshList]">상태</a></th>
					<th class="separator"><a id="sbscrb_de" class="_command[_listHeaderToggle|User.refreshList]">가입일자</a></th>
				</tr>
			</thead>
		</table>
	</div>
	
	<div id="table_list" class="list_table_box">
		<table cellspacing="0" cellpadding="0">
			<colgroup>
				<col style="width:200px;"/>
				<col style="width:200px;"/>
				<col/>
				<col style="width:150px;"/>
				<col style="width:100px;"/>
				<col style="width:132px;#width:150px;"/><!-- Header size보다 18px 작게 잡아야함. 스크롤 영역 (동적처리는 IE 미지원으로 고정값 설정) -->
			</colgroup>
			
			<tbody>
				<c:choose>
					<c:when test="${fn:length(userList) <= 0 }">
						<tr>
							<td colspan="6"><spring:message code="info.nodata.msg" /></td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="userListVO" items="${userList }" varStatus="sttus">
							<tr>
								<td>
									<div class="ellipsis" title="<c:out value="${userListVO.esntlId }" />">
										<a id="${userListVO.esntlId }" class="_command[User.detailView]"><c:out value="${userListVO.esntlId }" /></a>&nbsp;
									</div>
								</td>
								<td>${userListVO.emplyrId }&nbsp;</td>
								<td>${userListVO.userNm }&nbsp;</td>
								<td><cmmCd:cdValue code="${userListVO.mberSe }" codeId="COM012" />&nbsp;</td>
								<td><cmmCd:cdValue code="${userListVO.emplyrSttusCode }" codeId="COM013" />&nbsp;</td>
								<td>${userListVO.sbscrbDe }&nbsp;</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
	
	<c:if test="${fn:length(userList) > 0 && userVO.pagingEnable eq '1' }">
		<div id="pagination" class="pagination">
			<div class="listPaging">
				<ui:pagination paginationInfo="${pagination }" type="image" jsFunction="User.fn_egov_link_page" />
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