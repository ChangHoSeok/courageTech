<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<div id="data_list_area" class="ct_list_area">
	<c:choose>
		<c:when test="${param.mode eq 'edit' }">
			<h3 id="listSubTitle" class="subTitle left staticHeightArea">프로그램 목록</h3>
			
			<div class="buttonSet right">
				<button type="button" class="gen_btn _command[Menu.progrmWindowOpen]" accesskey="3">프로그램 추가</button>
				<button type="button" class="gen_btn _command[Menu.deleteProgrmSubList]" accesskey="4">프로그램 삭제</button>
			</div>
		</c:when>
		<c:otherwise>
			<h3 id="listSubTitle" class="subTitle left staticHeightArea">프로그램 목록</h3>
		</c:otherwise>
	</c:choose>
	
	<div id="listArea" class="type_list">
		<div id="listTableFix" class="list_table_header">
			<table class="header" cellspacing="0" cellpadding="0">
				<colgroup>
					<c:if test="${param.mode eq 'edit' }">
						<col style="width: 50px;" />
					</c:if>
					<col style="width: 120px;" />
					<col />
					<col />
					<col style="width: 250px;">
					<col style="width: 200px;">
				</colgroup>
				
				<thead>
					<tr>
						<c:if test="${param.mode eq 'edit' }">
							<th><input type="checkbox" name="allCheck" id="allCheck" class="_command[Menu.progrmAllCheck]"></th>
						</c:if>
						<th ${param.mode eq 'edit'? 'class="separator"' : '' }>프로그램 구분</th>
						<th class="separator">프로그램 파일명</th>
						<th class="separator">프로그램명</th>
						<th class="separator">URL</th>
						<th class="separator">프로그램 설명</th>
					</tr>
				</thead>
			</table>
		</div>
		
		<div id="table_list" class="list_table_box">
			<table cellspacing="0" cellpadding="0">
				<colgroup>
					<c:if test="${param.mode eq 'edit' }">
						<col style="width: 50px;" />
					</c:if>
					<col style="width: 120px;" />
					<col />
					<col />
					<col style="width: 250px;">
					<col style="width: 182px;#width:200px;"><!-- Header size보다 18px 작게 잡아야함. 스크롤 영역 (동적처리는 IE 미지원으로 고정값 설정) -->
				</colgroup>
				
				<tbody id="progrmSubListBody">
					<c:choose>
						<c:when test="${fn:length(progrmList) <= 0 }">
							<tr id="noData">
								<c:set var="colspan" value="5" />
								<c:if test="${param.mode eq 'edit' }">
									<c:set var="colspan" value="6" />
								</c:if>
								<td colspan="${colspan }"><spring:message code="info.nodata.msg" /></td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="progrmListVO" items="${progrmList }" varStatus="sttus">
								<tr id="tr${progrmListVO.progrmFileNm }">
									<c:if test="${param.mode eq 'edit' }">
										<td class="pl0">
											<input type="checkbox" name="progrmChk" id="${progrmListVO.progrmFileNm }" title="선택" value="${progrmListVO.progrmFileNm }" />
											<input type="hidden" name="menuProgrmList" value="${progrmListVO.progrmFileNm }">
										</td>
									</c:if>
									
									<td id="progrmSe_${progrmListVO.progrmSe }"><cmmCd:cdValue code="${progrmListVO.progrmSe }" codeId="CURG002" />&nbsp;</td>
									<td class="Ltxt">
										<div class="ellipsis" title="<c:out value="${progrmListVO.progrmFileNm }" />"><c:out value="${progrmListVO.progrmFileNm }" />&nbsp;</div>
									</td>
									<td class="Ltxt"><div class="ellipsis" title="<c:out value="${progrmListVO.progrmKoreanNm}" />"><c:out value="${progrmListVO.progrmKoreanNm }" />&nbsp;</div></td>
									<td class="Ltxt"><div class="ellipsis" title="<c:out value="${progrmListVO.url}" />"><c:out value="${progrmListVO.url}" />&nbsp;</div></td>
									<td class="Ltxt"><div class="ellipsis" title="<c:out value="${progrmListVO.progrmDc }" />"><c:out value="${progrmListVO.progrmDc }" />&nbsp;</div></td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />