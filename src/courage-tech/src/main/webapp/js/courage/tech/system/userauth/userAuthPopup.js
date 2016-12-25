var UserAuthPopup = {
	MENU_NO : "",
	FORM_ID : "formUserAuthPopup",
	CREATE_ID : "createUserAuthPopup",
	ACTION_STATUS : "",
	OPENER_KEY : "",
	CALLBACK_FUNCTION : "",
	
	/**
	 * 개요 : 등록 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 26.
	 * @param 
	 */
	formInitCreate : function() {
		window.resizeTo(800, 600);
		
		AuthCommon.selectMenuAuthor(UserAuthPopup.MENU_NO, UserAuthPopup.FORM_ID);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
			$jquery("input:checkbox").prop("disabled", true);
		}
		
		$jquery("#progrmNm").html(Navi.getProgrmNm(UserAuthPopup.CREATE_ID));
		
		if (UserAuthPopup.ACTION_STATUS == "success") {
			$jquery.growlUI("info", "저장되었습니다.");
		}
		
		showBtnAccessKey();
	},
	
	fn_egov_link_page : function(pageIndex) {
		UserAuthPopup.setPageIndex(pageIndex);
		UserAuthPopup.refreshList();
	},
	
	/**
	 * 개요 : 사용권한 상태 확인
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 11.
	 * @param 
	 */
	userAuthCheck : function(index) {
		if (!$jquery("#authorCodeChk" + index).attr("checked")) {
			$jquery("#authorCodeChk" + index + "Yn").attr("checked", false);
		}
	},
	
	/**
	 * 개요 : 기본권한 상태 확인
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 11.
	 * @param 
	 */
	defaultAuthorYnCheck : function(thisObj, index) {
		if ($jquery("#authorCodeChk" + index).attr("checked")) {
		} else {
			$jquery("#authorCodeChk" + index).attr("checked", true);
		}
		
		checkboxOnlyOneChecked($jquery("input[name=defaultAuthorYn]"), thisObj);
	},
	
	/**
	 * 개요 : 사용자 권한 저장
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 11.
	 * @param 
	 */
	save : function(thisObj) {
		var checkBoxCnt = $jquery('#' + UserAuthPopup.FORM_ID +" input[name=defaultAuthorYn]:checked:checked").length;
		
		if (checkBoxCnt > 0) {
			if (confirm("저장하시겠습니까?")) {
				UserAuthPopup.saveProc();
			}
		} else {
			alert("기본권한이 선택되지 않았습니다.");
			return false;
		}
	},
	
	/**
	 * 개요 : 사용자 권한 저장 처리
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 11.
	 * @param 
	 */
	saveProc : function() {
		var innerHTML = "";
		var authorCode = "";
		
		$jquery("#paramArrayDiv").html('');
		
		$jquery("input[name=authorCodeChk]:checked:checked").each( function( index ) {
			var authorCodeId = $jquery(this).attr("id");
			authorCode = $jquery("#"+authorCodeId+"Value").val();
			innerHTML += '<input type="hidden" name="authorCodeArray" id="authorCodeArray" value="'+authorCode+'">';
			
			if ($jquery("#"+authorCodeId+"Yn").attr("checked")) {
				innerHTML += '<input type="hidden" name="defaultAuthorYnArray" id="defaultAuthorYnArray" value="Y">';
			} else {
				innerHTML += '<input type="hidden" name="defaultAuthorYnArray" id="defaultAuthorYnArray" value="N">';
			}
		});
		
		$jquery("#paramArrayDiv").html(innerHTML);
		
		$jquery("#content_body").ajaxload(
			"blockLoad",
			jsContextPath + "/system/userauth/createUserAuthPopupProc.tech",
			"POST",
			"html",
			$jquery("#" + UserAuthPopup.FORM_ID).separator("separatorRemoveForm").serialize(),
			false
		);
	},
	
	/**
	 * 개요 : opener 유효성 검사
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 11.
	 * @param 
	 */
	_fncCheckOpener : function() {
		if (opener == null || opener.closed) {
			alert("현재 창을 호출한 화면이 종료되었습니다.\n현재 창을 종료합니다.");
			return false;
		} else if (isEmpty($jquery("#popupForm #openerKey", opener.document).val())) {
			alert("현재 창을 호출한 화면이 이동되었습니다.\n현재 창을 종료합니다.");
			return false;
		} else if ($jquery("#popupForm #openerKey", opener.document).val() != UserAuthPopup.OPENER_KEY) {
			alert("현재 창을 호출한 화면이 변경되었습니다.\n현재 창을 종료합니다.");
			return false;
		}
		
		return true;
	},

	/**
	 * 개요 : 팝업창 닫기
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 11.
	 * @param thisObj
	 */
	popupClose : function(thisObj) {
		if (!isEmpty(UserAuthPopup.CALLBACK_FUNCTION)) {
			if (UserAuthPopup._fncCheckOpener()) {
				eval("opener." + UserAuthPopup.CALLBACK_FUNCTION + "()");
			}
		}
		
		window.close();
	}
};