<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
<%@include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Expires" content="1" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<link href="${pageContext.request.contextPath}/index.ico" rel="shortcut icon" type="image/x-icon" />

<tiles:insertAttribute name="resource" />
<title><spring:message code="courage.tech.title" /></title>

<script type="text/javascript">
	$jquery(document).ready(function() {
		// 공지사항 롤링
		$jquery.ajax({
			type	: "POST",
			url		: jsContextPath + "/cop/bbs/retrieveNoticeRollingList.tech",
			dataType: "html",
			success	: function (data) {
				$jquery("#noticeRoll").html(data);
				
				if ($jquery("#noticeRoll li").length > 0) {
					$jquery("#noticeRoll").vTicker("init", {
						speed: 1000,
						height: 15,
						margin: 8
					});
					
					$jquery("#noticeRoll").show();
				}
			},
			error	: function(x, e) {
				alert("오류가 발생되었습니다.");
				console.debug(x);
				console.debug(e);
			}
		});
	});
</script>

</head>

<body>
	<div id="wrap">
		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>

		<div id="container">
			<div id="leftSide" class="fixLeft">
				<div id="navigation" class="fixLeft">
					<tiles:insertAttribute name="left_menu" />
				</div>
			</div>

			<div id="section_cen">
				<div id="section_cen_fix">
					<%-- 공지사항 롤링 영역 --%>
					<div id="noticeRoll" class="staticHeightArea" style="display: none;"></div>
					<tiles:insertAttribute name="body" />
				</div>
			</div>
		</div>

		<div id="footer">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
	
	<!-- dialog:Begin -->
	<div id="dialog-message" title="Message"></div>
	
	<div id="dialog-confirm" title="Confirm"></div>
	<!-- dialog:End -->
</body>
</html>