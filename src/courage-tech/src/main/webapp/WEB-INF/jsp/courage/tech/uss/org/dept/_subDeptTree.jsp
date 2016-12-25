<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>
<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		Dept.formInitTree();
	});
//-->
</script>

<!-- TreeView -->
<div class="fL" style="width: 47%; margin: 10px;">
	<h3 class="subTitle left" style="padding: 5px 0 0 20px; background-position: 8px 7px;">부서Tree</h3>
	<label style="position: relative; top: 3px; left: 15px;">
		<input type="checkbox" name="condAblSe" id="condAblSe" class="_command[Dept.refreshTree]" value="0">&nbsp;폐지부서 포함
	</label>
	
	<div class="tbl_result_list" style="height: 480px; border: 2px solid #4A90DC; overflow: auto;">
		<div id="deptTreeView" style="padding: 10px;"></div>
	</div>
</div>

<!-- 상세정보 View -->
<div class="fL" style="width: 47%; margin: 10px;">
	<h3 class="subTitle" style="padding: 5px 0 0 20px; background-position: 8px 7px;">부서 상세정보</h3>
	
	<div id="deptDetailView" class="type_write" style="margin-top: 6px;">
		<table cellspacing="0" cellpadding="0">
			<colgroup>
				<col style="width: 120px;" />
				<col />
				<col style="width: 120px;" />
				<col />
			</colgroup>
			
			<tbody>
				<tr>
					<th>부서코드</th>
					<td id="deptCode" class="text_left" colspan="3"></td>
				</tr>
				<tr>
					<th>부서명</th>
					<td id="allDeptNm" class="text_left" colspan="3"></td>
				</tr>
				<tr>
					<th>부서명(약칭)</th>
					<td id="lowestDeptNm" class="text_left" colspan="3"></td>
				</tr>
				<tr>
					<th>차상위 부서코드</th>
					<td id="atmbUpperDeptCode" class="text_left" colspan="3"></td>
				</tr>
				<tr>
					<th>최상위 부서코드</th>
					<td id="bestDeptCode" class="text_left" colspan="3"></td>
				</tr>
				<tr>
					<th>차수</th>
					<td id="odr" class="text_left"></td>
					<th>서열</th>
					<td id="ord" class="text_left"></td>
				</tr>
				<tr>
					<th>소속부서 차수</th>
					<td id="psitnDeptOdr" class="text_left" colspan="3"></td>
				</tr>
				<tr>
					<th>폐지구분</th>
					<td id="ablSe" class="text_left" colspan="3"></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>