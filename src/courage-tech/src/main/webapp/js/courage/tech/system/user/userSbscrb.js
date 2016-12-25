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
	 * @Author : schkk
	 * @Date : 2016. 12. 2.
	 * @param
	 */
	formInitSbscrbDetail : function() {
		$jquery("#progrmNm").html(Navi.getProgrmNm(UserSbscrb.SBSCRB_DETAIL_ID));
		
		$jquery("#" + UserSbscrb.FORM_ID).separator('separatorAddForm');
		showBtnAccessKey();
		
		UserSbscrb.userConfirmFormInit();
		UserSbscrb.userSecsnPopupFormInit();
		
		if (UserSbscrb.ACTION_STATUS === "success") {
			$jquery.growlUI("info", "저장되었습니다.");
		}
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
		$jquery("#" + UserSbscrb.FORM_ID + " #passwordConfirm").removeClassRegEx("validate");
		
		if (!isEmpty($jquery("#" + UserSbscrb.FORM_ID + " #password").val())) {
			$jquery("#" + UserSbscrb.FORM_ID + " #passwordConfirm").addClass("validate[required,custom[password],equals[newPassword]]");
		} else {
			$jquery("#" + UserSbscrb.FORM_ID + " #passwordConfirm").addClass("validate[custom[password],equals[newPassword]]");
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
		$jquery("#dialog-userSecsn").ajaxload(
			"blockLoad",
			jsContextPath + "/system/user/retrieveSecsnInfo.tech",
			"POST",
			"html"
		);
		
		$jquery("#dialog-userSecsn").dialog("open");
	},
	
	/**
	 * 개요 : 회원탈퇴
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 12. 4.
	 * @param 
	 */
	secsn : function() {
		if ($jquery("#secsnAgre").prop("checked")) {
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
		if (!$jquery("#" + UserSbscrb.CONFIRM_FORM_ID).validationEngine("validate")) {
			return false;
		}
		
		$jquery.ajax({
			type	: "POST",
			url		: jsContextPath + "/system/user/retrieveUserConfirmProc.tech",
			data	: $jquery("#" + UserSbscrb.CONFIRM_FORM_ID).separator("separatorRemoveForm").serialize(),
			dataType: "json",
			success	: function (confirmFlag) {
				if (confirmFlag) {
					$jquery("#dialog-userConfirm").dialog("close");
					
					if (UserSbscrb.CONFIRM_MODE === "modify") {
						$jquery("#content_body").ajaxload(
							"blockLoad",
							jsContextPath + "/system/user/modifyUserSbscrbProc.tech",
							"POST",
							"html",
							$jquery("#" + UserSbscrb.FORM_ID).separator("separatorRemoveForm").serialize()
						);
					} else if(UserSbscrb.CONFIRM_MODE === "secsn") {
						location.href = jsContextPath + "/system/user/userSecsnProc.tech";
					}
				} else {
					alert("사용자 패스워드 인증을 실패했습니다.\n비밀번호를 다시 확인해주세요.");
					$jquery("#" + UserSbscrb.CONFIRM_FORM_ID + " #password").val("");
					$jquery("#" + UserSbscrb.CONFIRM_FORM_ID + " #password").focus();
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
		$jquery("#dialog-userConfirm").ajaxload(
			"blockLoad",
			jsContextPath + "/system/user/retrieveUserConfirm.tech",
			"POST",
			"html",
			"",
			true
		);
		
		$jquery("#dialog-userConfirm").dialog("open");
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
			$jquery.growlUI("warn", "입력값을 확인하세요");
		}
	},

	_sbscrbFormValidate : function(validateTy) {
		if ($jquery("#" + UserSbscrb.FORM_ID).validationEngine(validateTy)) {
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
		
		if ($jquery("#" + UserSbscrb.FORM_ID).validationEngine("validateField", $jquery("#emailId"))
				&& $jquery("#" + UserSbscrb.FORM_ID).validationEngine("validateField", $jquery("#emailDomain"))) {
			
			userService.selectUserIdExists(paramObj, {
				callback : function (returnObj) {
					if (returnObj) {
						$jquery("#emailId").removeClass("validT");
						$jquery("#emailDomain").removeClass("validT");
						
						$jquery("#emailId").addClass("validF");
						$jquery("#emailDomain").addClass("validF");
						
						$jquery("#idValidMsg").show();
					} else {
						$jquery("#emailId").removeClass("validF");
						$jquery("#emailDomain").removeClass("validF");
						
						$jquery("#emailId").addClass("validT");
						$jquery("#emailDomain").addClass("validT");
						
						$jquery("#idValidMsg").hide();
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
		$jquery.ajax({
			type	: "POST",
			url		: jsContextPath + "/system/user/createUserSbscrbProc.tech",
			data	: $jquery("#" + UserSbscrb.FORM_ID).separator("separatorRemoveForm").serialize(),
			dataType: "html",
			success	: function (html) {
				$jquery("#dialog-userSbscrb").html(html);
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
		if ($jquery(thisObj).find("option:selected").val() != "1") {
			$jquery("#" + UserSbscrb.FORM_ID + " #" + domainObjId).val($jquery(thisObj).find("option:selected").text());
			$jquery("#" + UserSbscrb.FORM_ID + " #" + domainObjId).attr('readonly', true);
			$jquery("#" + UserSbscrb.FORM_ID + " #" + domainObjId).addClass('readonly');
		} else {
			$jquery("#" + UserSbscrb.FORM_ID + " #" + domainObjId).val('');
			$jquery("#" + UserSbscrb.FORM_ID + " #" + domainObjId).attr('readonly', false);
			$jquery("#" + UserSbscrb.FORM_ID + " #" + domainObjId).removeClassRegEx('readonly');
			$jquery("#" + UserSbscrb.FORM_ID + " #" + domainObjId).focus();
		}
	},
	
	/**
	 * 개요 : 사용자 인증 팝업 폼 초기화
	 * 
	 * @Author : schkk
	 * @Date : 2016. 12. 2.
	 * @param
	 */
	userConfirmFormInit : function() {
		$jquery("#dialog-userConfirm").dialog({
			autoOpen: false,
			resizable: false,
			draggable: false,
			modal: true,
			title: '사용자 인증',
			width: 400,
			height: 350,
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
	 * 개요 : 회원가입 팝업 폼 초기화
	 * 
	 * @Author : schkk
	 * @Date : 2016. 11. 29.
	 * @param
	 */
	userSbscrlPopupFormInit : function() {
		$jquery("#dialog-userSbscrb").dialog({
			autoOpen: false,
			resizable: false,
			draggable: false,
			modal: true,
			title: '회원가입',
			width: 650,
			height: 500,
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
	 * 개요 : 회원탈퇴 팝업 폼 초기화
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 12. 4.
	 * @param 
	 */
	userSecsnPopupFormInit : function() {
		$jquery("#dialog-userSecsn").dialog({
			autoOpen: false,
			resizable: false,
			draggable: false,
			modal: true,
			title: '회원탈퇴',
			width: 650,
			height: 450,
			position: {
				my: "center top+30",
				at: "center top",
				of: window
			},
			buttons: {
				'회원탈퇴': function() {
					UserSbscrb.secsn();
				},
				'취소': function() {
					$jquery(this).dialog('close');
				}
			}
		});
	},
	
	/**
	 * 개요 : 회원가입 팝업 조회
	 * 
	 * @Author : schkk
	 * @Date : 2016. 11. 29.
	 * @param
	 */
	showUserSbscrbForm : function(thisObj) {
		$jquery("#dialog-userSbscrb").ajaxload(
			"blockLoad",
			jsContextPath + "/system/user/retrieveUserSbscrbAgre.tech",
			"POST",
			"html",
			"",
			true
		);
		
		$jquery("#dialog-userSbscrb").dialog({
			height: 500
		});
		
		$jquery("#dialog-userSbscrb").dialog("open");
	},
	
	/**
	 * 개요 : 개인정보처리방침 동의/비동의 이벤트 처리
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 11. 30.
	 * @param
	 */
	sbscrbAgre : function(thisObj) {
		if ($jquery(thisObj).prop("name") === "agre") {
			$jquery("#dialog-userSbscrb").ajaxload(
				"blockLoad",
				jsContextPath + "/system/user/createUserSbscrb.tech",
				"POST",
				"html",
				{"sbscrbAgre" : $jquery(thisObj).prop("name")},
				true
			);
			
			$jquery("#dialog-userSbscrb").dialog({
				height: 600
			});
		} else if ($jquery(thisObj).prop("name") === "disagre") {
			if (confirm("개인정보처리방침에 동의하지 않으면 회원가입을 할 수 없습니다.\n회원가입을 취소하시겠습니까?")) {
				$jquery("#dialog-userSbscrb").dialog("close");
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
			$jquery("#dialog-userSbscrb").dialog("close");
		}
	}
};