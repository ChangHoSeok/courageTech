<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		LogRPSChart.formInitDetail();
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
	
	<form:form name="formLogRPSChart" id="formLogRPSChart" action="retrieveLogDetailChart.tech" method="post" commandName="logRPSVO">
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
								<th>로그분류그룹</th>
								<td>
									<cmmCd:cdSelect name="condGroupId" id="condGroupId" styleClass="_enter[LogRPSChart.search]"
										operation="logClGroupDAO.selectCommonCodeListForLogClGroup"
										defaultCode="${logFileRceptVO.condGroupId }"
										showAll="true"
										use="true"/>
								</td>
								<th>기준날짜</th>
								<td>
									<form:input path="condStdrDe" cssClass="input-text-cal validate[required] separator[date] _enter[LogRPSChart.search]" cssStyle=""/>
								</td>
	                        </tr>
						</tbody>
					</table>
					
					<div class="tbl_search">
						<button type="button" id="search" class="gen_btn btn_srch_submit _command[LogRPSChart.search]"><spring:message code="button.search" /></button>
					</div>
				</div>	
			</fieldset>
		</div>
		<!-- 상세조회 : end -->
	</form:form>
</div>

<div id="infomation" class="type_write staticHeightArea">
	<div id="chartView"></div>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />