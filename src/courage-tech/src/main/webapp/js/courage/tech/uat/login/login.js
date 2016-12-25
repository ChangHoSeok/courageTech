var Login = {
	FORM_ID : "formLogin",
	PAGE_INDEX_ID : "pageIndex",
	ACTION_STATUS : "",
	
	/**
	 * 개요 : 로그인 Form 초기화
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 1. 18.
	 * @param 
	 */
	formInit : function() {
		if (!isEmpty($jquery("#" + Login.FORM_ID + " #emplyrId").val())) {
			$jquery("#" + Login.FORM_ID + " #password").focus();
		}
	},
	
	/**
	 * 개요 : 로그인 LayerPopup Form 초기화
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 11. 22.
	 * @param 
	 */
	subFormInit : function() {
		if (Login.ACTION_STATUS === "success") {
			location.reload();
		}
		
		if (!isEmpty($jquery("#" + Login.FORM_ID + " #emplyrId").val())) {
			$jquery("#" + Login.FORM_ID + " #password").focus();
		}
	},
	
	/**
	 * 개요 : 팝업 로그인 폼 초기화
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 11. 22.
	 * @param 
	 */
	loginPopupFormInit : function() {
		$jquery("#dialog-login").dialog({
			autoOpen: false,
			resizable: false,
			draggable: false,
			modal: true,
			title: 'Login to Courage Tech',
			width: 470,
			height: 400,
			position: {
				my: "center top+30",
				at: "center top",
				of: window
			},
			buttons: {
				'취소': function() {
					$jquery(this).dialog('close');
				}
			}
		});
	},
	
	/**
	 * 개요 : 로그인 팝업 폼 조회
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 11. 22.
	 * @param 
	 */
	showLoginPopupForm : function(thisObj) {
		$jquery("#dialog-login").dialog("open");
		
		$jquery("#dialog-login").ajaxload(
			"blockLoad",
			jsContextPath + "/uat/login/subFormLogin.tech",
			"POST",
			"html"
		);
	},
	
	/**
	 * 개요 : 홈페이지 이동
	 * 
	 * @Author : schkk
	 * @Date : 2016. 11. 21.
	 * @param 
	 */
	goHome : function(thisObj) {
		location.href = jsContextPath;
	},
	
	/**
	 * 개요 : 사용자 로그인 
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 1. 18.
	 * @param 
	 */
	onLogin : function(thisObj) {
		var actionUrl = jsContextPath + "/uat/login/userLogin.tech";
		
		$jquery.block();
		
		if (Login._formValidate()) {
			if ($jquery(thisObj).prop("id") === "form") {
				$jquery("#" + Login.FORM_ID).attr("action", actionUrl);
				$jquery("#" + Login.FORM_ID).submit();
			} else if ($jquery(thisObj).prop("id") === "layerPopup") {
				$jquery.ajax({
					type	: "POST",
					url		: actionUrl,
					data	: $jquery("#" + Login.FORM_ID).separator('separatorRemoveForm').serialize(),
					dataType: "html",
					success	: function (html) {
						$jquery("#dialog-login").html(html);
						$jquery.unBlock();
					},
					error	: function(x, e) {
						alert("오류가 발생되었습니다.");
						$jquery.unBlock();
					}
				});
			}
		}
	},
	
	/**
	 * 개요 : 로그인 form Validate
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 1. 18.
	 * @param 
	 */
	_formValidate : function() {
		if ($jquery("#" + Login.FORM_ID).validationEngine('validate')) {
			return true;
		} else {
			return false;
		}
	},
};
