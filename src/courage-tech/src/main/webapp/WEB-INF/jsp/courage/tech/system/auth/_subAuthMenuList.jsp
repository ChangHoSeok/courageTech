<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<h3 id="listSubTitle" class="subTitle staticHeightArea">메뉴 목록</h3>

<div id="data_list_area" class="ct_list_area">	
	<div id="listArea" class="type_list">
		<div id="listTableFix" class="list_table_header">
			<table class="header" cellspacing="0" cellpadding="0">
				<colgroup>
					<col style="width: 120px;" />
					<col />
					<col />
					<!-- <col style="width: 100px;" /> -->
					<col style="width: 70px;">
					<col style="width: 70px;">
					<col style="width: 70px;">
					<col style="width: 70px;">
					<col style="width: 70px;">
					<col style="width: 70px;">
				</colgroup>
				
				<thead>
					<tr>
						<th>메뉴ID</th>
						<th class="separator">메뉴명</th>
						<th class="separator">메뉴설명</th>
						<!-- <th class="separator">상위메뉴ID</th> -->
						<th class="separator">시작페이지</th>
						<th class="separator">조회</th>
						<th class="separator">등록</th>
						<th class="separator">커스텀1</th>
						<th class="separator">커스텀2</th>
						<th class="separator">커스텀3</th>
					</tr>
				</thead>
			</table>
		</div>
		
		<div id="table_list" class="list_table_box">
			<table cellspacing="0" cellpadding="0">
				<colgroup>
					<col style="width: 120px;" />
					<col />
					<col />
					<!-- <col style="width: 100px;" /> -->
					<col style="width: 70px;">
					<col style="width: 70px;">
					<col style="width: 70px;">
					<col style="width: 70px;">
					<col style="width: 70px;">
					<col style="width: 52px;#width:70px;"><!-- Header size보다 18px 작게 잡아야함. 스크롤 영역 (동적처리는 IE 미지원으로 고정값 설정) -->
				</colgroup>
				
				<tbody>
					<c:choose>
						<c:when test="${fn:length(authMenuList) <= 0 }">
							<tr id="noData">
								<td colspan="10"><spring:message code="info.nodata.msg" /></td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="authMenuListVO" items="${authMenuList }"  varStatus="sttus">
								<tr id="tr${authMenuListVO.menuNo }">
									<td>
										<c:out value="${authMenuListVO.menuNo }" />&nbsp;
										<input type="hidden" name="authorMenuArray" value="${authMenuListVO.menuNo }">
									</td>
									<td class="Ltxt">
										<div class="ellipsis" title="<c:out value="${authMenuListVO.menuNm}" />">
											<c:forEach begin="1" end="${authMenuListVO.menuLevel }">
												&nbsp;&nbsp;&nbsp;
											</c:forEach>
											<c:out value="${authMenuListVO.menuNm }" />
										</div>
									</td>
									<td class="Ltxt">
										<div class="ellipsis" title="<c:out value="${authMenuListVO.menuDc}" />">
											<c:out value="${authMenuListVO.menuDc}" />&nbsp;
										</div>
									</td>
									<!-- <td><c:out value="${authMenuListVO.upperMenuId }" />&nbsp;</td> -->
									<c:choose>
										<c:when test="${authMenuListVO.childMenuCnt > 0 }">
											<td>&nbsp;</td>
											<td><input type="hidden" name="searchFlagArray" id="" title="" value="N" />&nbsp;</td>
											<td><input type="hidden" name="createFlagArray" id="" title="" value="N"/>&nbsp;</td>
											<td><input type="hidden" name="customFlag1Array" id="" title="" value="N"/>&nbsp;</td>
											<td><input type="hidden" name="customFlag2Array" id="" title="" value="N"/>&nbsp;</td>
											<td><input type="hidden" name="customFlag3Array" id="" title="" value="N"/>&nbsp;</td>
										</c:when>
										<c:otherwise>
											<td>
												<c:if test="${authMenuListVO.menuIndictAt eq 'Y' }">
													<c:set var="menuNoParam" value="?menuNo=${authMenuListVO.menuNo }" />
													<c:set var="urlParam" value="&${authMenuListVO.intrmdVriabl }" />
													
													<input type="radio" name="authorIndexUrl" id="authorIndexUrl${sttus.index}" 
														onclick="Auth.searchAuthCheck(${sttus.index});" 
														value="${authMenuListVO.url }${!empty authMenuListVO.url ? menuNoParam : '' }${!empty authMenuListVO.url && !empty authMenuListVO.intrmdVriabl ? urlParam : '' }" 
														${!empty authMenuListVO.url && authMenuListVO.authorIndexUrl eq authMenuListVO.url ? 'checked="checked"' : '' } 
														${empty param.mode ? 'disabled="disabled"' : '' }/>
												</c:if>
												&nbsp;
											</td>
											<td>
												<input type="checkbox" name="searchFlagArray" onclick="Auth.authorIndexUrlCheck(${sttus.index});" id="searchFlagArray${sttus.index}" value="" title="조회" ${authMenuListVO.searchFlag eq 'Y' ? 'checked="checked"' : '' } ${empty param.mode ? 'disabled="disabled"' : '' }/>	
											</td>
											<td>
												<input type="checkbox" name="createFlagArray" id="createFlagArray${sttus.index}" value="" title="등록" ${authMenuListVO.createFlag eq 'Y' ? 'checked="checked"' : '' } ${empty param.mode ? 'disabled="disabled"' : '' }/>
											</td>
											<td>
												<input type="checkbox" name="customFlag1Array" id="customFlag1Array${sttus.index}" value="" title="커스텀1" ${authMenuListVO.customFlag1 eq 'Y' ? 'checked="checked"' : '' } ${empty param.mode ? 'disabled="disabled"' : '' }/>
											</td>
											<td>
												<input type="checkbox" name="customFlag2Array" id="customFlag2Array${sttus.index}" value="" title="커스텀2" ${authMenuListVO.customFlag2 eq 'Y' ? 'checked="checked"' : '' } ${empty param.mode ? 'disabled="disabled"' : '' }/>
											</td>
											<td>
												<input type="checkbox" name="customFlag3Array" id="customFlag3Array${sttus.index}" value="" title="커스텀3" ${authMenuListVO.customFlag3 eq 'Y' ? 'checked="checked"' : '' } ${empty param.mode ? 'disabled="disabled"' : '' }/>
											</td>
										</c:otherwise>
									</c:choose>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>
</div>