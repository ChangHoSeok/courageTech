/*
 * ajaxload 1.0 Beta, jQuery plugin
 *
 * Copyright(c) 2013, Seok ChangHo
 * http://tech.courage.pe.kr
 *
 */
(function($) {
    var methods = {
    	genLoad : function(url, type, dataType, data, async) {
    		var content = this;
    		async = typeof async !== 'undefined' ? async : true;
    		
    		$.ajax({
				type		: type,
				url			: url,
				data		: data,
				dataType	: dataType,
				async		: async,
				beforeSend	: function() { // 전처리
					
				},
				success		: function(data, textStatus, jqXHR) {
					if (jqXHR.status == 200) {
						$(content).html(data);
					} else if (jqXHR.status == 204) {
						alert('표시할 컨텐츠 내용이 없습니다.');
					}
				},
				error		: function(x, e) {
					methods._errorCatch(x, e);
				},
				complete	: function() { // 후처리
				}
			});
    	},
    	
    	fadeLoad : function(url, type, dataType, data, async) {
    		var content = this;
    		async = typeof async !== 'undefined' ? async : true;
    		
    		$(content).fadeOut('fast', function() {
    			$jquery.ajax({
    				type		: type,
    				url			: url,
    				data		: data,
    				dataType	: dataType,
    				async		: async,
    				beforeSend	: function() { // 전처리
    					
    				},
    				success		: function(data, textStatus, jqXHR) {
    					if (jqXHR.status == 200) {
    						$(content).html(data);
    					} else if (jqXHR.status == 204) {
    						alert('표시할 컨텐츠 내용이 없습니다.');
    					}
    				},
    				error		: function(x, e) {
    					methods._errorCatch(x, e);
    				},
    				complete	: function() { // 후처리
    					$(content).fadeIn('slow');
    				}
    			});
    		});
    	},
    	
    	blockLoad : function(url, type, dataType, data, async) {
    		var content = this;
    		async = typeof async !== 'undefined' ? async : true;
    		
    		$.ajax({
				type		: type,
				url			: url,
				data		: data,
				dataType	: dataType,
				async		: async,
				beforeSend	: function() { // 전처리
					
				},
				success		: function(data, textStatus, jqXHR) {
					if (jqXHR.status == 200) {
						$(content).html(data);
					} else if (jqXHR.status == 204) {
						alert('표시할 컨텐츠 내용이 없습니다.');
					}
				},
				error		: function(x, e) {
					methods._errorCatch(x, e);
				},
				complete	: function() { // 후처리
				}
			});
    	},
    	
    	_errorCatch : function(x, e) {
    		if (x.status == 0) {
				alert('서버와 통신할 수 없습니다.\r\n네트워크 설정을 확인하여 주시기 바랍니다.');
			} else if (x.status == 400) { // 권한없음 (인증안됨)
				location.href = jsContextPath + "/error/errorInvalidAccess.do";
			} else if (x.status == 401) { // 권한없음 (인증안됨)
				location.href = jsContextPath + "/error/errorAuth.do";
			} else if (x.status == 402) { // IP접근 금지
				location.href = jsContextPath + "/error/errorAccess.do";
			} else if (x.status == 403) { // 세션체크 오류
				location.href = jsContextPath + "/error/errorSession.do";
			} else if (x.status == 404) {
				location.href = jsContextPath + "/error/error404.do";
			} else if (x.status == 500 || x.status == 999) { // Spring ExceptionResolver 오류
				location.href = jsContextPath + "/error/errorSystem.do";
			} else if (e == 'timeout') {
				alert('일시적인 장애가 발생\r\n\r\n재시도해 주세요.');
			} else {
				alert('알수없는 오류가 발생되었습니다.' + x.status);
			}
    	}
    };

    
    $.fn.ajaxload = function(method) {
        var thisObj = $(this);
        var args;
        
        if (arguments.length <= 1) {
	    	args = arguments;
	    } else {
	    	args = Array.prototype.slice.call(arguments, 1);
	    }
        
        if(!thisObj[0]) {
		  return false;
        }
        
        if (typeof(method) === 'string' && method.charAt(0) != '_' && methods[method]) {
        	methods[method].apply(thisObj, args);
        }
        
        return true;
    };
})(jQuery);
