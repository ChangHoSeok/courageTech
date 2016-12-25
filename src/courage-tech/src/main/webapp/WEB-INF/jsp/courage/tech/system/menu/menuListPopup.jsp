<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		MenuPopup.formInit();
	});
//-->
</script>

<div class="popup_title_sm">
	<h1 id="progrmNm"></h1>
</div>

<div class="popup_contents">
	<div class="result_txt">
		총<span><fmt:formatNumber value="${empty pagination.totalRecordCount ? 0 : pagination.totalRecordCount }" /></span>건이 조회 되었습니다.
	</div>

	<div class="tbl_result_list">
		<table cellspacing="0" cellpadding="0">
			<colgroup>
				<col style="width: 100px;" />
				<col />
				<col style="width: 80px;" />
				<col style="width: 80px;" />
			</colgroup>
			
			<thead>
				<tr>
					<th>메뉴ID</th>
					<th>메뉴명</th>
					<th>정렬순서</th>
					<th>선택</th>
				</tr>
			</thead>
			
			<tbody>
				<c:choose>
					<c:when test="${fn:length(menuList) <= 0 }">
						<tr>
							<td colspan="4"><spring:message code="info.nodata.msg" /></td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="menuListVO" items="${menuList }" varStatus="sttus">
							<tr>
								<td><c:out value="${menuListVO.menuId }" />&nbsp;</td>
								<td class="Ltxt">
									<div class="ellipsis" title="<c:out value="${menuListVO.menuNm }" />">
										<c:forEach begin="1" end="${menuListVO.menuLevel }">
											&nbsp;&nbsp;&nbsp;
										</c:forEach>
										<c:out value="${menuListVO.menuNm }" />
									</div>
								</td>
								<td><cmmUtil:numFormat value="${menuListVO.menuOrdr }" />&nbsp;</td>
								<td>
									<input type="hidden" name="menuNm" id="menuNm_${menuListVO.menuId }" value="${menuListVO.menuNm }">
									<button type="button" id="${menuListVO.menuId }" class="gen_btn needCreate _command[MenuPopup.setMenu]">선택</button>
								</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		
		<c:if test="${fn:length(menuList) > 0 && menuVO.pagingEnable eq '1' }">
			<div id="pagination" class="pagination">
				<div class="listPaging">
					<ui:pagination paginationInfo="${pagination }" type="image" jsFunction="MenuPopup.fn_egov_link_page" />
				</div>
			</div>
		</c:if>
	</div>
</div>

<div class="popup_bottom">
	<div class="popup_btnGroup">
		<button type="button" class="gen_btn _command[MenuPopup.popupClose]" accesskey="0">닫기</button>
	</div>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />