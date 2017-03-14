<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	alert('aa');
	$jquery(document).ready(function() {
		Board.USE_AT = "${boardVO.useAt }";
		Board.formInitContent();
	});
//-->
</script>

<div id="data_list_area">
	<div id="table_list" class="type_write scroll">
		<table class="innerTable" cellspacing="0" cellpadding="0">
			<colgroup>
				<col style="width: 100%;" />
			</colgroup>
			
			<tbody>
				<tr>
					<td>
						<c:choose>
							<c:when test="${empty boardVO }">
								설정된 컨텐츠가 없습니다.
							</c:when>
							<c:when test="${boardVO.useAt eq 'Y' }">
								<form action="${ctxPath }/template/jsp/boardContentsView.jsp" name="boardContentsForm" id="boardContentsForm" method="post" target="boardContentsView">
									<input type="hidden" name="nttCn" id="nttCn" value="<c:out value="${boardVO.nttCn }" />">
								</form>
								
								<iframe name="boardContentsView" id="boardContentsView" scrolling="no" frameborder="0" width="100%" onload="autoHeightIframe(this);" style="min-height: 150px;"></iframe>
							</c:when>
							<c:otherwise>
								블라인드 처리된 게시글 입니다.
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />