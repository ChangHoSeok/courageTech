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

<div class="page-title">
	<div class="title_left">
		<h3>Typography</h3>
	</div>
</div>

<div class="clearfix"></div>

<div class="row">
	<div class="col-md-12">
		<div class="x_panel">
			<div class="x_title">
				<h2>
					Typography <small>different design elements</small>
				</h2>
				<ul class="nav navbar-right panel_toolbox">
					<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">Settings 1</a></li>
							<li><a href="#">Settings 2</a></li>
						</ul>
					</li>
					<li><a class="close-link"><i class="fa fa-close"></i></a></li>
				</ul>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<c:choose>
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
			</div>
		</div>
	</div>
</div>