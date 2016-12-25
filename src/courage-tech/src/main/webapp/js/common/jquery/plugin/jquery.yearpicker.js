/*

 * Form Year Picker 1.0 Beta, jQuery plugin
 *
 * Copyright(c) 2012, Seok ChangHo
 * http://tech.courage.pe.kr
 *
 */
(function($) {
    var methods = {
    	init : function (options) {
    		return this.each(function () {
    			var $this = $(this);
                var data = $this.data('yearpicker');
                var year = (options && options.selectedYear) ? options.selectedYear : (new Date()).getFullYear();
                var settings = $.extend({
                	selectedYear: year,
                    afterYear: 15,
                    beforeYear: 15,
                    id: "yearpicker_" + (Math.random() * Math.random()).toString().replace('.', ''),
                    onChangeCallback: null
                }, options);
    			
                if (String(settings.selectedYear) === "" || String(settings.selectedYear).replace(/(^\s*)|(\s*$)|($\s*)/g, "") === "") {
                	settings.selectedYear = year;
                }
                
    			if (!data) {
        			$(this).data('yearpicker', {
        				'target' : $this,
        				'settings': settings
        			});
        			
        			// onChange 이벤트 발생 처리
        			$this.on('change', function() {
        				var changeSetting = $(this).data('yearpicker').settings;
        				changeSetting.selectedYear = $(this).val();
        				
        				$this.yearpicker('_yearWidget', changeSetting);
        				
        				if ($jquery.isFunction(settings.onChangeCallback)) {
        					settings.onChangeCallback.call();
        				}
        			});
        			
        			//초기값설정 method 호출 _yearWidget
        			$this.yearpicker('_yearWidget', settings);
        		}
    		});
    	},
    	
    	// 초기값 설정
    	_yearWidget: function (settings) {
    		var yearCombo = this;
    		var currentYear = (new Date()).getFullYear();
    		var startYear = Number(settings.selectedYear) + Number(settings.afterYear);
    		var endYear = Number(settings.selectedYear) - Number(settings.beforeYear);
    		
    		if (startYear > currentYear) {
    			startYear = currentYear;
    		}
    		
    		$jquery(yearCombo).empty();
    		for (var i = startYear; i >= endYear; i--) {
    			var option = $('<option />').attr('value', i).append(i);
    			
                if (settings.selectedYear == i) {
                    option.attr('selected', 'selected');
                }
                
                yearCombo.append(option);
            }
    	}
    };
    
    $.fn.yearpicker = function(method) {
    	if (methods[method]) {
    		return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
		} else if (typeof method === 'object' || !method) {
			return methods.init.apply(this, arguments);
		} else {
			$.error('Method ' + method + ' does not exist on jQuery.yearpicker');
		}
    };
})(jQuery);
