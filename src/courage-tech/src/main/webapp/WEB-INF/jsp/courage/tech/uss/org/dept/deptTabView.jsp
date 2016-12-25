<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>
<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		Dept.formInitTabView();
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
</div>

<div id="deptTab" class="radioTabs staticHeightArea">
	<input type="radio" name="viewTab" id="viewList" class="_command[Dept.tabChange]" value="tabViewList" checked="checked">
	<label for="viewList">목록 조회</label>
	
	<input type="radio" name="viewTab" id="viewTree" class="_command[Dept.tabChange]" value="tabViewTree">
	<label for="viewTree">트리 조회</label>
</div>

<div id="tabViewList"></div>
<div id="tabViewTree"></div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />