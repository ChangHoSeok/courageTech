<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/jquery-migrate-1.4.1.js"></script>
<script type="text/javascript">
<!--
	//jquery 충돌방지 $방식의 호출이 충돌됨으로 $jquery으로 변경
	var $jquery = jQuery.noConflict();
	var jsContextPath = '${pageContext.request.contextPath}';
	jQuery.migrateMute = true;
//-->
</script>

<!-- courage-tech js : begin -->
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/courage/common/cmmVariable.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/courage/tech/layout.js"></script>
<script type="text/javascript" charset="utf-8" src='${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/courage/tech/ui.js'></script>
<!-- courage-tech js : end -->

<!-- common : begin -->
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/common.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/window.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/swfobject.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/extendString.js"></script>
<!-- common : end -->

<!-- jquery ui : begin -->
<link href="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.css.basePath']" />/common/jquery/ui/flick/jquery.ui.theme.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.css.basePath']" />/common/jquery/ui/flick/jquery-ui.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/jquery/ui/jquery-ui-1.10.2.custom.js"></script>
<!-- jquery ui : end -->

<!-- jquery plugin : begin -->
<link href="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.css.basePath']" />/common/jquery/blockUI/blockUI.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.css.basePath']" />/common/jquery/messagebox/messagebox.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.css.basePath']" />/common/jquery/multipleaccordion/multipleaccordion.css" rel="stylesheet" type="text/css" media="screen" />

<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/jquery/plugin/jquery.cookie.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/jquery/plugin/jquery.blockUI.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/jquery/plugin/jquery.lazyload.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/jquery/plugin/jquery.separator.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/jquery/plugin/jquery.classRegEx.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/jquery/plugin/jquery.ba-hashchange.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/jquery/plugin/jquery.eventHandler.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/jquery/plugin/jquery.ajaxload.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/jquery/plugin/jquery.url.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/jquery/plugin/jquery.mtz.monthpicker.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/jquery/plugin/jquery.yearpicker.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/jquery/plugin/jquery.mousewheel.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/jquery/plugin/jquery.multiple.accordion.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/jquery/plugin/jquery.byteChk.js"></script>
<!-- jquery plugin : end -->

<!-- jquery uploader : begin -->
<link href="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.css.basePath']" />/common/jquery/uploader/uploadify.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/jquery/uploader/jquery.uploadify.v2.1.0.js"></script>
<!-- jquery uploader : end -->

<!-- jquery validation : begin -->
<link href="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.css.basePath']" />/common/jquery/validation/validationEngine.jquery.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/jquery/validation/jquery.validationEngine-ko.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/jquery/validation/jquery.validationEngine.js"></script>
<!-- jquery validation : end -->

<!-- juqery tooltip : begin -->
<link href="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.css.basePath']" />/common/jquery/tooltip/tip-yellowsimple/tip-yellowsimple.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/jquery/plugin/jquery.poshytip.js"></script>

<link href="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.css.basePath']" />/common/jquery/tooltip/qtip/jquery.qtip.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/jquery/plugin/jquery.qtip.js"></script>
<!-- juqery tooltip : end -->

<!-- juqery jsTree : begin -->
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/jquery/jstree/jquery.jstree.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/jquery/plugin/jquery.hotkeys.js"></script>
<!-- juqery jsTree : end -->

<script type="text/javascript">
<!--
	// jsTree Themes default Path
	$jquery.jstree._themes = jsContextPath + "/<spring:eval expression="@systemConfig['system.css.basePath']" />/common/jquery/jstree/themes/";
//-->
</script>

<!-- dwr : begin -->
<script type="text/javascript" charset="utf-8" src='${pageContext.request.contextPath}/dwr/engine.js'></script>
<script type="text/javascript" charset="utf-8" src='${pageContext.request.contextPath}/dwr/util.js'></script>
<!-- dwr : end -->

<!-- system css : begin -->
<link href="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.css.basePath']" />/courage/tech/base.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.css.basePath']" />/courage/tech/layout.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.css.basePath']" />/courage/tech/popup.css" rel="stylesheet" type="text/css" media="screen" />
<!-- system css : end -->

<!-- 권한관리 (Client side) : begin -->
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/dwr/interface/authService.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/courage/tech/authCommon.js"></script>
<!-- 권한관리 (Client side) : end -->

<!-- Navi 관련 : begin -->
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/dwr/interface/naviService.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/dwr/interface/progrmService.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/courage/tech/naviCommon.js"></script>
<!-- Navi 관련 : end -->