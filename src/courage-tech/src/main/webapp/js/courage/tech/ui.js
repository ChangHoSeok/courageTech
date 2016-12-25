$jquery(function() {
	$jquery("#dialog-confirm").dialog({
		autoOpen: false,
		resizable: false,
		draggable: false,
		height:240,
		modal: true,
		position: {
			my: "top",
			at: "top",
			of: window
		}
	});
	
	$jquery("#dialog-message").dialog({
		autoOpen: false,
		resizable: false,
		draggable: false,
		modal: true,
		title: '안내',
		width: 400,
		height: 'auto',
		position: {
			my: "center top+30",
			at: "center top",
			of: window
		},
		buttons: {
			'확인': function() {
				$jquery(this).dialog('close');
			}
		}
	});
	
	$jquery.alert = function(value) {
		$jquery("#dialog-message").html('<p><div class="ui-icon ui-icon-circle-check" style="position: absolute"></div><div style="margin-left: 20px">' + value + '</div></p>');
		$jquery("#dialog-message").dialog("open");
	};
	
	$jquery.block = function() {
		$jquery.blockUI({
			message: '<h3><img src="' + jsContextPath + '/images/common/progress/progress01.gif" /><span style="position: relative; top: -10px; padding-left: 10px;">정보 조회중</span></h3>'
		});
	};
	
	$jquery.unBlock = function() {
		$jquery.unblockUI();
	};
	
	// 이미지 로딩 설정
	$jquery('img').lazyload({
		placeholder	: jsContextPath + "/images/common/empty.gif",
		effect		: "fadeIn",
		threshold	: 200
	});
	
	// 버튼 단축키 표시
	showBtnAccessKey = function() {
		$jquery("button").not(":contains('Alt')").each(function() {
			if ($jquery(this).attr('accesskey') !== undefined && $jquery(this).attr('accesskey') !== "") {
				$jquery(this).html($jquery(this).html() + " (Alt+" + $jquery(this).attr('accesskey') + ")");
			}
		});
	};
	
	_jqueryUIInit = function() {
		// Tooltips 적용
		$jquery('input[type=text]').filter(function() {
			if ($jquery(this).attr('title') != "" && $jquery(this).attr('title') != undefined) {
				return true;
			}
		}).poshytip({
			showOn: 'focus',
			alignTo: 'target',
			alignX: 'right',
			alignY: 'center',
			offsetX: 5
		});
		
		// 탭 스타일 적용
		$jquery('.radioTabs').buttonset();
		
		// 일반 버튼 스타일 적용
		$jquery(":button[class*=gen_btn]").button();
		
		// 버튼 조회 아이콘
		$jquery(":button[class*=icon_btn_search]").button({
			icons : {
				primary : "ui-icon-search"
			}
		});
		
		// Date field 적용
		$jquery('.input-text-cal').datepicker({
			onSelect: function(date) {
				$jquery(this).val(date);
			}
		});
		
		// Date(Month) field 적용
		$jquery('.input-text-cal-month').monthpicker();
		
		// Button 영역 정리
		if ($jquery("#contentBtnFix button").length <= 0) {
			$jquery("#contentBtnFix").hide();
		}
	};
	
	// 정렬 필드
	_listHeaderToggle = function(thisObj, formID) {
		var headerObj;
		var orderObj;
		var alignObj;
		
		if (formID == undefined) {
			orderObj = $jquery('#condOrder');
			alignObj = $jquery('#condAlign');
			headerObj = $jquery('.header a');
		} else {
			orderObj = $jquery('#' + formID + ' #condOrder');
			alignObj = $jquery('#' + formID + ' #condAlign');
			headerObj = $jquery('#' + formID + ' ~ div .header a');
		}
		
		var orderField = orderObj.val();
		var align = alignObj.val();
		
		if (thisObj == undefined || $jquery('.header a').length <= 0) {
			return false;
		}
		
		if (orderField != undefined && align != undefined) {
			headerObj.removeClass('desc asc');
		
			if(orderField.toUpperCase() == $jquery(thisObj).prop('id').toUpperCase()) {
				
				if (align.toLowerCase() == 'desc') {
					alignObj.val('asc');
				} else {
					alignObj.val('desc');
				}
				
				$jquery(thisObj).addClass(alignObj.val());
			} else {
				orderObj.val($jquery(thisObj).prop('id').toUpperCase());
				alignObj.val("desc");
				$jquery(thisObj).addClass('desc');
			}
		}
	};
	
	// 정렬 필드 UI 셋팅
	_listOrderInit = function(formID) {
		var headerObj;
		var orderObj;
		var alignObj;
		
		if (formID == undefined) {
			orderObj = $jquery('#condOrder');
			alignObj = $jquery('#condAlign');
			headerObj = $jquery('.header a');
		} else {
			orderObj = $jquery('#' + formID + ' #condOrder');
			alignObj = $jquery('#' + formID + ' #condAlign');
			headerObj = $jquery('#' + formID + ' ~ div .header a');
		}
		
		if (headerObj.length > 0) {
			var orderField = orderObj.val();
			var align = alignObj.val();
			
			if (orderField != undefined && align != undefined && orderField.length > 0 && align.length > 0) {
				headerObj.each(function () {
					if ($jquery(this).prop('id').toLowerCase() == orderField.toLowerCase()) {
						$jquery(this).addClass(align.toLowerCase());
						return false;
					}
				});
			}
		}
	};
	
	_jqueryUIInit();
	_listOrderInit();
	Layout.resize();
});