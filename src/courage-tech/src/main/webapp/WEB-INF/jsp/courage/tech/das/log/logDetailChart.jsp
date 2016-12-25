<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		LogDetailChart.formInitDetail();
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
	
	<form:form name="formLogDetailChart" id="formLogDetailChart" action="retrieveLogDetailChart.tech" method="post" commandName="logDetailVO">
		<form:hidden path="condStartHour"/>
		<form:hidden path="condEndHour"/>
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
	                   		<col style="width:150px;"/>
	                   		<col style="width:50px;"/>
	                        <col />
						</colgroup>
						
						<tbody>
							<tr>
								<th>로그분류그룹</th>
								<td>
									<cmmCd:cdSelect name="condGroupId" id="condGroupId" styleClass="_enter[LogDetailChart.search]"
										operation="logClGroupDAO.selectCommonCodeListForLogClGroup"
										defaultCode="${logFileRceptVO.condGroupId }"
										showAll="true"
										use="true"/>
								</td>
								<th>기준날짜</th>
								<td>
									<form:input path="condStanDe" cssClass="input-text-cal validate[required] separator[date] _enter[LogDetailChart.search]" cssStyle=""/>
								</td>
								<th>시간</th>
								<td>
									<div id="timeSlider" class="fL" style="width: 300px; margin: 3px 5px;"></div>
									<strong id="timeValue"></strong>
								</td>
	                        </tr>
	                        <tr>
	                        	<th>조회제한(PT)</th>
	                        	<td colspan="5">
	                        		<div id="PTSlider" class="fL" style="width: 300px; margin: 3px 5px;"></div>
									<strong id="PTValue"></strong>
	                        		<label><input type="checkbox" name="condUnlimited" id="condUnlimited" value="1" class="_command[LogDetailChart.switchLimit]" ${logDetailVO.condUnlimited eq '1' ? 'checked="checked"' : '' }>&nbsp;제한없음 (성능저하)</label>
	                        		
	                        		<form:hidden path="condLimitPTStart" />&nbsp;
	                        		<form:hidden path="condLimitPTEnd" />&nbsp;
	                        	</td>
	                        </tr>
						</tbody>
					</table>
					
					<div class="tbl_search">
						<button type="button" id="search" class="gen_btn btn_srch_submit _command[LogDetailChart.search]"><spring:message code="button.search" /></button>
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