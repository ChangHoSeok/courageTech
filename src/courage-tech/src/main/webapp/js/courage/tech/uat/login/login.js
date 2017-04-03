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
		if (!isEmpty($("#" + Login.FORM_ID + " #emplyrId").val())) {
			$("#" + Login.FORM_ID + " #password").focus();
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
		
		if (isEmpty($("#" + Login.FORM_ID + " #emplyrId").val())) {
			$("#" + Login.FORM_ID + " #emplyrId").focus();
		} else {
			$("#" + Login.FORM_ID + " #password").focus();
		}
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
		var loginType = $(thisObj).data('login-type');
		
		if (Login._formValidate()) {
			if (loginType === "form") {
				$("#" + Login.FORM_ID).attr("action", actionUrl);
				$("#" + Login.FORM_ID).submit();
			} else if (loginType === "layerPopup") {
				$.ajax({
					type	: "POST",
					url		: actionUrl,
					data	: $("#" + Login.FORM_ID).separator('separatorRemoveForm').serialize(),
					dataType: "html",
					success	: function (html) {
						$("#dialog-login .modal-content").html(html);
					},
					error	: function(x, e) {
						alert("오류가 발생되었습니다.");
					}
				});
			}
		}
	},
	
	/**
	 * 개요 : 모달 종료 처리
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2017. 3. 24.
	 * @param 
	 */
	modalDestroy : function(thisObj) {
		$("#" + Login.FORM_ID).validationEngine('hideAll');
	},
	
	/**
	 * 개요 : 로그인 form Validate
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 1. 18.
	 * @param 
	 */
	_formValidate : function() {
		if ($("#" + Login.FORM_ID).validationEngine('CLFValidate')) {
			return true;
		} else {
			return false;
		}
	},
};
