<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/vendors/jquery/dist/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/jquery-migrate-1.4.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/vendors/fastclick/lib/fastclick.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/vendors/nprogress/nprogress.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/vendors/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"></script>
<script type="text/javascript">
<!--
	var jsContextPath = '${pageContext.request.contextPath}';
//-->
</script>

<!-- common : begin -->
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/common.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/window.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/swfobject.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/extendString.js"></script>
<!-- common : end -->

<!-- jquery plugin : begin -->
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
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/jquery/vticker/jquery.vticker.js"></script>
<!-- jquery plugin : end -->

<!-- dwr : begin -->
<script type="text/javascript" charset="utf-8" src='${pageContext.request.contextPath}/dwr/engine.js'></script>
<script type="text/javascript" charset="utf-8" src='${pageContext.request.contextPath}/dwr/util.js'></script>
<!-- dwr : end -->

<!-- 권한관리 (Client side) : begin -->
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/dwr/interface/authService.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/courage/tech/authCommon.js"></script>
<!-- 권한관리 (Client side) : end -->

<!-- Navi 관련 : begin -->
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/dwr/interface/naviService.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/dwr/interface/progrmService.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/courage/tech/naviCommon.js"></script>
<!-- Navi 관련 : end -->