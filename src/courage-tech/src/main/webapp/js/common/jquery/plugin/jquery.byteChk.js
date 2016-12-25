/*
 * TextArea text byte checker 1.0 Beta, jQuery plugin
 *
 * Copyright(c) 2015, Seok ChangHo
 * http://tech.courage.pe.kr
 *
 */
(function($) {
	var methods = {
		keypress : function(chkByte, maxLength) {
			$(this).each(function(){
				init(chkByte, maxLength, $(this));
				
				$(this).on("keypress", function() {
					var length = bytChk(chkByte, maxLength, $(this));
			    	$(this).next().find('.byteChke').text(length);
				});
			});
		},
		
		keyup : function(chkByte, maxLength) {
			$(this).each(function(){
				init(chkByte, maxLength, $(this));
				
				$(this).on("keyup", function() {
					var length = bytChk(chkByte, maxLength, $(this));
					$(this).next().find('.byteChke').text(length);
				});
			});
		},
		
		keydown : function(chkByte, maxLength) {
			$(this).each(function(){
				init(chkByte, maxLength, $(this));
				
				$(this).on("keydown", function() {
					var length = bytChk(chkByte, maxLength, $(this));
					$(this).next().find('.byteChke').text(length);
				});
			});
		}
	};
	
	var bytChk = function(chkByte, maxLength, targetObj) {
    	var text = targetObj.val();
    	var byteLength = 0;
    	var tmpText = "";
		var tmpLength = 0;
		
    	for (var inx = 0; inx < text.length; inx++) {
	        var oneChar = escape(text.charAt(inx));
	        
	        if ( oneChar.length == 1 ) {
	            byteLength ++;
	        } else if (oneChar.indexOf("%u") != -1) {
	            byteLength += chkByte;
	        } else if (oneChar.indexOf("%") != -1) {
	            byteLength += oneChar.length / 3;
	        }
	        
	        if (byteLength > (maxLength * chkByte)) {
	        	tmpText = text.substr(0, inx);
	        	alert("입력 범위를 초과했습니다.\n최대 한글 " + maxLength + " 자 영문 " + (maxLength * chkByte) + " 자를 입력 할 수 있습니다.");
	        	targetObj.val(tmpText);
	        	break;
	        }
	        
	        tmpLength = byteLength;
    	}
    	
    	return tmpLength;
    };
    
    var init = function(chkByte, maxLength, targetObj) {
    	if (targetObj.prop('type') != "textarea" && targetObj.prop('type') != "text") {
			alert("textare or input text only");
			return false;
		}
		
		var tmpLength = bytChk(chkByte, maxLength, targetObj);
		var html = "<p class='byteChkInfo'>※. 한글 기준" + maxLength + "자 내로 등록할 수 있습니다. [ <strong class='byteChke' style='color: red;'>" + tmpLength + "</strong> Byte / " + (maxLength * chkByte) + " Byte]</p>";
		
		targetObj.after(html);
    };

	$.fn.byteChk = function(method) {
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