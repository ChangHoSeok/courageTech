<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript">
<!--
	$(document).ready(function() {
		
		var message = ''; 
		if ('${actionMessage }' != '') {
			'<fmt:message key="${actionMessage }" var="message"/>';
			message = '${message}';
		} else if('${param.actionMessage}'){
 			'<fmt:message key="${param.actionMessage }" var="message"/>';
		}
		
		message = '${fn:indexOf(message, "???") eq 0 ? fn:replace(message, "???", "") : message }';
		
		if (message != '') {
 			alert(message);
 		} else if ('${actionMessage }' != '') {
 			alert('${actionMessage }');
		} else if ('${param.actionMessage }' != '') {
			alert('${param.actionMessage }');
		}
	});
//-->
</script>