<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<!-- Bootstrap -->
<link href="${ctxPath}/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
<!-- Font Awesome -->
<link href="${ctxPath}/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<!-- NProgress -->
<link href="${ctxPath}/vendors/nprogress/nprogress.css" rel="stylesheet">

<!-- jQuery custom content scroller -->
<link href="${ctxPath}/vendors/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.min.css" rel="stylesheet"/>
    
<!-- Custom styling plus plugins -->
<link href="${ctxPath}/vendors/gentelella-master/css/custom.css" rel="stylesheet">

<!-- bootstrap model effect -->
<link href="${ctxPath}/css/courage/tech/bootstrap-modal-effect.css" rel="stylesheet">

<!-- jquery ui : begin -->
<link href="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.css.basePath']" />/common/jquery/ui/flick/jquery.ui.theme.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.css.basePath']" />/common/jquery/ui/flick/jquery-ui.css" rel="stylesheet" type="text/css" media="screen" />
<!-- jquery ui : end -->

<!-- jquery validation : begin -->
<link href="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.css.basePath']" />/common/jquery/validation/validationEngine.jquery.css" rel="stylesheet" type="text/css" media="screen" />
<!-- jquery validation : end -->

<!-- jquery blockUI : begin -->
<link href="${pageContext.request.contextPath}/<spring:eval expression="@systemConfig['system.css.basePath']" />/common/jquery/blockUI/blockUI.css" rel="stylesheet" type="text/css" media="screen" />
<!-- jquery blockUI : end -->