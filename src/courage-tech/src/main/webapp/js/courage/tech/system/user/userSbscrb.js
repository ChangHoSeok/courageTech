var UserSbscrb = {
	MENU_NO : "",
	FORM_ID : "formUser",
	CONFIRM_FORM_ID : "formUserConfirm",
	SBSCRB_DETAIL_ID : "retrieveUserSbscrbDetail",
	ACTION_STATUS : "",
	CONFIRM_MODE : "",
	IS_EMPLYR_ID_DUPLECATE : false,
	
	/**
	 * 개요 : 회원정보 조회 초기화
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 12. 2.
	 * @param
	 */
	formInitSbscrbDetail : function() {
		$("#progrmNm").html(Navi.getProgrmNm(UserSbscrb.SBSCRB_DETAIL_ID));
		
		$("#" + UserSbscrb.FORM_ID).separator('separatorAddForm');
		showBtnAccessKey();
		
		UserSbscrb.userConfirmFormInit();
		UserSbscrb.userSecsnPopupFormInit();
		
		if (UserSbscrb.ACTION_STATUS === "success") {
			$.growlUI("info", "저장되었습니다.");
		}
	},
	
	/**
	 * 개요 : 회원가입 팝업 초기화
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2017. 3. 25.
	 * @param 
	 */
	formInitSbscrbCreatePopup : function() {
		$("#" + UserSbscrb.FORM_ID + " #emailIdValidMsg").hide();
		$("#" + UserSbscrb.FORM_ID + " #emailId").focus();
	},
	
	/**
	 * 개요 : 회원정보 변경
	 * 
	 * @Author : schkk
	 * @Date : 2016. 12. 2.
	 * @param
	 */
	modifySbscrb : function(thisObj) {
		UserSbscrb.IS_EMPLYR_ID_DUPLECATE = true;
		$("#" + UserSbscrb.FORM_ID + " #passwordConfirm").removeClassRegEx("validate");
		
		if (!isEmpty($("#" + UserSbscrb.FORM_ID + " #password").val())) {
			$("#" + UserSbscrb.FORM_ID + " #passwordConfirm").addClass("validate[required,custom[password],equals[newPassword]]");
		} else {
			$("#" + UserSbscrb.FORM_ID + " #passwordConfirm").addClass("validate[custom[password],equals[newPassword]]");
		}
		
		if (UserSbscrb._sbscrbFormValidate("CLFValidate")) {
			UserSbscrb.CONFIRM_MODE = "modify";
			UserSbscrb.showUserConfirm();
		}
	},
	 
	/**
	 * 개요 : 회원탈퇴 안내화면 조회
	 * 
	 * @Author : schkk
	 * @Date : 2016. 12. 4.
	 * @param 
	 */
	showSecsn : function(thisObj) {
		$("#dialog-userSecsn").ajaxload(
			"blockLoad",
			jsContextPath + "/system/user/retrieveSecsnInfo.tech",
			"POST",
			"html"
		);
		
		$("#dialog-userSecsn").dialog("open");
	},
	
	/**
	 * 개요 : 회원탈퇴
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 12. 4.
	 * @param 
	 */
	secsn : function() {
		if ($("#secsnAgre").prop("checked")) {
			UserSbscrb.CONFIRM_MODE = "secsn";
			UserSbscrb.showUserConfirm();
		} else {
			alert("회원탈퇴 사항을 숙지하시고 동의를 선택해주세요.");
			return false;
		}
	},
	
	/**
	 * 개요 : 사용자 인증
	 * 
	 * @Author : schkk
	 * @Date : 2016. 12. 2.
	 * @param
	 */
	userConfirm : function(thisObj) {
		if (!$("#" + UserSbscrb.CONFIRM_FORM_ID).validationEngine("validate")) {
			return false;
		}
		
		$.ajax({
			type	: "POST",
			url		: jsContextPath + "/system/user/retrieveUserConfirmProc.tech",
			data	: $("#" + UserSbscrb.CONFIRM_FORM_ID).separator("separatorRemoveForm").serialize(),
			dataType: "json",
			success	: function (confirmFlag) {
				if (confirmFlag) {
					$("#dialog-userConfirm").dialog("close");
					
					if (UserSbscrb.CONFIRM_MODE === "modify") {
						$("#content_body").ajaxload(
							"blockLoad",
							jsContextPath + "/system/user/modifyUserSbscrbProc.tech",
							"POST",
							"html",
							$("#" + UserSbscrb.FORM_ID).separator("separatorRemoveForm").serialize()
						);
					} else if(UserSbscrb.CONFIRM_MODE === "secsn") {
						location.href = jsContextPath + "/system/user/userSecsnProc.tech";
					}
				} else {
					alert("사용자 패스워드 인증을 실패했습니다.\n비밀번호를 다시 확인해주세요.");
					$("#" + UserSbscrb.CONFIRM_FORM_ID + " #password").val("");
					$("#" + UserSbscrb.CONFIRM_FORM_ID + " #password").focus();
				}
			},
			error	: function(x, e) {
				alert("오류가 발생되었습니다.");
			}
		});
	},
	
	/**
	 * 개요 : 사용자 인증
	 * 
	 * @Author : schkk
	 * @Date : 2016. 12. 2.
	 * @param
	 */
	showUserConfirm : function() {
		$("#dialog-userConfirm").ajaxload(
			"blockLoad",
			jsContextPath + "/system/user/retrieveUserConfirm.tech",
			"POST",
			"html",
			"",
			true
		);
		
		$("#dialog-userConfirm").dialog("open");
	},
	
	/**
	 * 개요 : 회원가입 정보 등록
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 11. 30.
	 * @param
	 */
	sbscrbSave : function(thisObj) {
		if (UserSbscrb._sbscrbFormValidate("validate")) {
			if (confirm("회원가입 하시겠습니까?")) {
				UserSbscrb.sbscrbSaveProc();
			}
		} else {
			$.growlUI("warn", "입력값을 확인하세요");
		}
	},

	_sbscrbFormValidate : function(validateTy) {
		if ($("#" + UserSbscrb.FORM_ID).validationEngine(validateTy)) {
			if (!UserSbscrb.IS_EMPLYR_ID_DUPLECATE) {
				return false;
			}
			
			return true;
		} else {
			return false;
		}
	},
	
	/**
	 * 개요 : 아이디 중복검사
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8.
	 * @param thisObj
	 */
	duplicateCheck : function(userId) {
		UserSbscrb.IS_EMPLYR_ID_DUPLECATE = false;
		
		var paramObj = {
			emplyrId : userId
		};
		
		if ($("#" + UserSbscrb.FORM_ID).validationEngine("validateField", $("#emailId"))) {
			
			userService.selectUserIdExists(paramObj, {
				callback : function (returnObj) {
					if (returnObj) {
						$("#emailId").parents('.form-group').addClass("has-error");
						$("#emailId").parents('.form-group').removeClass("has-success");
						
						$("#emailIdValidMsg").show('fade');
					} else {
						$("#emailId").parents('.form-group').removeClass("has-error");
						$("#emailId").parents('.form-group').addClass("has-success");
						
						$("#emailIdValidMsg").hide('fade');
						UserSbscrb.IS_EMPLYR_ID_DUPLECATE = true;
					}
				},
				errorHandler : function(errorString, exception) {
					alert('오류가 발생되었습니다.\r\n');
				}
			});
		}
	},

	/**
	 * 개요 : 회원가입 정보 저장 처리
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 11. 30.
	 * @param
	 */
	sbscrbSaveProc : function() {
		$.ajax({
			type	: "POST",
			url		: jsContextPath + "/system/user/createUserSbscrbProc.tech",
			data	: $("#" + UserSbscrb.FORM_ID).separator("separatorRemoveForm").serialize(),
			dataType: "html",
			success	: function (html) {
				$("#dialog-userSbscrb").html(html);
			},
			error	: function(x, e) {
				alert("오류가 발생되었습니다.");
			}
		});
	},
	
	/**
	 * 개요 : 이메일 도메인 설정
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 2. 22.
	 * @param
	 */
	setEmailDomain : function(thisObj, domainObjId) {
		if ($(thisObj).find("option:selected").val() != "1") {
			$("#" + UserSbscrb.FORM_ID + " #" + domainObjId).val($(thisObj).find("option:selected").text());
			$("#" + UserSbscrb.FORM_ID + " #" + domainObjId).attr('readonly', true);
			$("#" + UserSbscrb.FORM_ID + " #" + domainObjId).addClass('readonly');
		} else {
			$("#" + UserSbscrb.FORM_ID + " #" + domainObjId).val('');
			$("#" + UserSbscrb.FORM_ID + " #" + domainObjId).attr('readonly', false);
			$("#" + UserSbscrb.FORM_ID + " #" + domainObjId).removeClassRegEx('readonly');
			$("#" + UserSbscrb.FORM_ID + " #" + domainObjId).focus();
		}
	},
	
	/**
	 * 개요 : 회원가입 팝업 조회
	 * 
	 * @Author : schkk
	 * @Date : 2016. 11. 29.
	 * @param
	 */
	showUserSbscrbForm : function(thisObj) {
		$("#dialog-userSbscrb").ajaxload(
			"blockLoad",
			jsContextPath + "/system/user/retrieveUserSbscrbAgre.tech",
			"POST",
			"html",
			"",
			true
		);
	},
	
	/**
	 * 개요 : 개인정보처리방침 동의/비동의 이벤트 처리
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 11. 30.
	 * @param
	 */
	sbscrbAgre : function(thisObj) {
		var agre = $(thisObj).data('agre');
		
		if (agre === "agre") {
			$("#dialog-userSbscrb").ajaxload(
				"blockLoad",
				jsContextPath + "/system/user/createUserSbscrb.tech",
				"POST",
				"html",
				{"sbscrbAgre" : agre},
				true
			);
		} else if (agre === "disagre") {
			if (confirm("개인정보처리방침에 동의하지 않으면 회원가입을 할 수 없습니다.\n회원가입을 취소하시겠습니까?")) {
				$('#dialog-userSbscrb').modal('hide');
			}
		}
	},
	
	/**
	 * 개요 : 로그인 화면 호출
	 * 
	 * @Author : schkk
	 * @Date : 2016. 11. 30.
	 * @param
	 */
	showLoginPopupForm : function(thisObj) {
		try {
			Login.showLoginPopupForm();
		} catch(error) {
			alert("페이지 연결이 잘못되었습니다.\n관리자에게 문의하세요.");
		} finally {
			$("#dialog-userSbscrb").dialog("close");
		}
	},
	
	/**
	 * 개요 : 모달 종료 처리
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2017. 3. 25.
	 * @param 
	 */
	modalDestroy : function(thisObj) {
		$("#" + UserSbscrb.FORM_ID).validationEngine('hideAll');
	}
};