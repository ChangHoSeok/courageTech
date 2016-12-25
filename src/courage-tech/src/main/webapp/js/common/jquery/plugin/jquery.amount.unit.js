/*
 * Form amount unit Changer 1.0 Beta, jQuery plugin
 *
 * Copyright(c) 2015, Seok ChangHo
 * http://tech.courage.pe.kr
 *
 */
(function($) {
    var methods = {
    	removeUnit: function() {
    		this.find(':input[class*=amount]').each( function() {
                var field = $(this);
                methods._amountField(field, 'remove');
            });
    	},
    	addUnit: function() {
    		this.find('input[class*=amount]').each( function() {
                var field = $(this);
                methods._amountField(field, 'add');
            });
    	},
    	_amountField: function(field, option) {

            if (!field.attr("id")) {
                $.error("jQueryAmountUnit: an ID attribute is required for this field: " + field.attr("name") + " class:" + field.attr("class"));
            }
            
            var numberValid = /^(\-)?(\d+)?([\.]\d+)?$/;
            
            if (field.val() && !numberValid.test(field.val())) {
            	$.error("jQueryAmountUnit: 숫자 형식의 값만 단위변환이 가능합니다. 해당 field를 확인하세요: " + field.attr("name") + " val:" + field.val());
            }
            
            var classStr = field.attr('class').split(/\s/);
            var unitsParsing = "";
            
            for (var i = 0; i < classStr.length; i++) {
            	if (classStr[i].indexOf("amount") == 0) {
            		unitsParsing = classStr[i];
            		break;
            	}
            }
            
            if (unitsParsing == null || unitsParsing == "") {
            	return false;
            }
            
            var getUnits = /amount\[(.*)\]/.exec(unitsParsing);
            var unit = getUnits[1];
            
            if (unit === null) {
                return false;
            }
            
            switch (unit) {
                case "100":
                case "1000":
                case "10000":
                case "100000":
                case "1000000":
                case "10000000":
                	if(option == 'remove') {
                		methods._removeUnit(field, unit);
                	} else {
                		methods._addUnit(field, unit);
                	}
                    break;
                default:
                	$.error("jQuerySeparator unit not found");
            }

        },
        _removeUnit: function(field, unit) {
            switch (field.prop("type")) {
                case "text":
                case "hidden":
                	if(field.val()) {
                		var fieldAmount = Number(field.val());
                		var unitAmount = Number(unit);
                		
                		field.val(fieldAmount * unitAmount);
                	}
                	break;
            }
        },
        _addUnit: function(field, unit) {
        	switch (field.prop("type")) {
	            case "text":
	            case "hidden":
	            	if(field.val()) {
	            		var fieldAmount = Number(field.val());
                		var unitAmount = Number(unit);
                		
                		field.val(fieldAmount / unitAmount);
	            	}
	            	break;
	        }
        }
    };

    
    $.fn.amountUnit = function(method) {
        var form = $(this);
        
        if(!form[0]) {
		  return form;  // stop here if the form do not exist
        }
        
        if (typeof(method) === 'string' && method.charAt(0) != '_' && methods[method]) {
        	methods[method].apply(form);
        }
        
        return form;
    };
})(jQuery);
