<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$(document).ready(function() {
		Board.USE_AT = "${boardVO.useAt }";
		Board.formInitContent();
	});
//-->
</script>
            
<div class="clearfix"></div>

<div class="row">
	<div class="col-xs-12 col-md-12">
		<div class="x_panel">
			<div class="x_title">
				<h2>
					<c:out value="${boardVO.nttSj }" />
					<small><fmt:formatDate value="${boardVO.frstRegistPnttm }" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;(<c:out value="${boardVO.frstRegisterNm }" />)</small>
				</h2>
				<ul class="nav navbar-right panel_toolbox">
					<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#"><i class="fa fa-pencil-square-o mR5"></i>수정</a></li>
							<li><a href="#"><i class="fa fa-trash mR5"></i>삭제</a></li>
						</ul>
					</li>
				</ul>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<c:choose>
					<c:when test="${boardVO.useAt eq 'Y' }">
						<form action="${ctxPath }/template/jsp/boardContentsView.jsp" name="boardContentsForm" id="boardContentsForm" method="post" target="boardContentsView">
							<input type="hidden" name="nttCn" id="nttCn" value="<c:out value="${boardVO.nttCn }" />">
						</form>
						
						<div class="col-lg-12">
							<iframe name="boardContentsView" id="boardContentsView" scrolling="no" frameborder="0" width="100%" onload="autoHeightIframe(this);" style="min-height: 150px;"></iframe>
						</div>
					</c:when>
					<c:otherwise>
						블라인드 처리된 게시글 입니다.
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</div>