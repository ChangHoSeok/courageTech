/*
 * Form Event Handler 1.0 Beta, jQuery plugin
 *
 * Copyright(c) 2012, Seok ChangHo
 * http://tech.courage.pe.kr
 * 
 * hashchange event 종속성 jquery.ba-hashchange.js
 */
(function($) {
	// hash event handler
	$jquery(window).hashchange(function() {
		if (isEmpty($jquery.url.hashParam('AC'))) {
			return false;
		}
		
		$jquery('#' + $jquery.url.hashParam('VA')).ajaxload(
			'blockLoad',
			jsContextPath + $jquery.url.hashParam('AC'),
			"POST",
			"html",
			location.hash.substring(1, location.hash.length),
			true
		);
	});
	
	// click event handler
	$(document).on('click', 'a, button, img, input[type=submit], input[type=button], input[type=checkbox], input[type=radio], input[type=image]', function() {
		if ($(this).attr('onclick') != undefined && $(this).attr('onclick') != "") {
			return true;
		}
		
		if ($(this).attr('class') != undefined && $(this).attr('class') != "") {
			var callParsing = $(this).attr('class').split(/\s/);
			var callbackParsing = '';
			
			for (var i = 0; i < callParsing.length; i++) {
            	if (callParsing[i].indexOf("_command") == 0) {
            		callbackParsing = callParsing[i];
            		break;
            	}
            }
			
			if (callbackParsing == null || callbackParsing == '') {
				return true;
			}
			
			var getFunctions = /_command\[(.*)\]/.exec(callbackParsing);
			
			if (getFunctions === null) {
				return true;
			}
			
			var functions = getFunctions[1].split(/\|/gi);
			
			for (var i = 0; i < functions.length; i++) {
				var paramStartIdx = functions[i].indexOf('(');
				var paramEndIdx = functions[i].indexOf(')');
				var fncName = undefined;
				var params = undefined;
				
				if (paramStartIdx > 0) {
					fncName = functions[i].substring(0, paramStartIdx);
					params = functions[i].substring(paramStartIdx + 1, paramEndIdx).split('\,');
				} else {
					fncName = functions[i];
				}
				
				if (params == undefined) {
					_executeFunctionByName(fncName, this);
				} else {
					_executeFunctionByName(fncName, this, params);
				}
			}
			
			if ($jquery(this).attr('type') == 'checkbox' || $jquery(this).attr('type') == 'radio') {
				return true;
			} else {
				return false;
			}
		}
		
		return true;
	});
	
	// enter keydown event handler
	$(document).on('keydown', 'input[type=text], input[type=password], select', function(event) {
		if ($(this).attr('class') != undefined && $(this).attr('class') != "" && event.keyCode == 13) {
			var callParsing = $(this).attr('class').split(/\s/);
			var callbackParsing = '';
			
			for (var i = 0; i < callParsing.length; i++) {
            	if (callParsing[i].indexOf("_enter") == 0) {
            		callbackParsing = callParsing[i];
            		break;
            	}
            }
			
			if (callbackParsing == null || callbackParsing == '') {
				return true;
			}
			
			var getFunctions = /_enter\[(.*)\]/.exec(callbackParsing);
			
			if (getFunctions === null) {
				return true;
			}
			
			var functions = getFunctions[1].split(/\|/gi);
			
			for (var i = 0; i < functions.length; i++) {
				var paramStartIdx = functions[i].indexOf('(');
				var paramEndIdx = functions[i].indexOf(')');
				var fncName = undefined;
				var params = undefined;
				
				if (paramStartIdx > 0) {
					fncName = functions[i].substring(0, paramStartIdx);
					params = functions[i].substring(paramStartIdx + 1, paramEndIdx).split('\,');
				} else {
					fncName = functions[i];
				}
				
				if (params == undefined) {
					_executeFunctionByName(fncName, this);
				} else {
					_executeFunctionByName(fncName, this, params);
				}
			}
			
			if ($jquery(this).attr('type') == 'checkbox' || $jquery(this).attr('type') == 'radio') {
				return true;
			} else {
				return false;
			}
		}
		
		return true;
	});
	
	_executeFunctionByName = function(functionName, obj, params) {
	    var args;
	    var namespaces = functionName.split(".");
	    var func = namespaces.pop();
	    var ns = namespaces.join('.');
	    
	    if (arguments.length <= 1) {
	    	args = arguments;
	    } else {
	    	args = Array.prototype.slice.call(arguments, 1,2).concat(params);
	    }

	    if(ns == '') {
	        ns = 'window';
	    }

	    ns = eval(ns);

	    return ns[func].apply(ns, args);
	};
})(jQuery);
