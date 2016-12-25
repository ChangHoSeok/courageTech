var LogFileRceptPopup = {
	MENU_NO : "",
	FORM_ID : "formLogFileRceptPopup",
	CREATE_ID : "createLogFileRceptPopup",
	DETAIL_ID : "retrieveLogFileRceptDetailPopup",
	ACTION_STATUS : "",
	TOTAL_CNT : 0,
	CALLBACK_FUNCTION : "",
	OPENER_KEY : "",
	atchFile : {
		uploadFlag : false,
		hasError : false
	},
	
	/**
	 * 개요 : 등록 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 17.
	 * @param 
	 */
	formInitCreate : function() {
		$jquery("#progrmNm").html(Navi.getProgrmNm(LogFileRceptPopup.CREATE_ID));
		
		$jquery("#" + LogFileRceptPopup.FORM_ID).separator('separatorAddForm');
		
		window.resizeTo(750, 560);
		showBtnAccessKey();
		
		if (LogFileRceptPopup.ACTION_STATUS == "success") {
			$jquery.growlUI("info", "저장되었습니다.");
		}
	},
	
	/**
	 * 개요 : 상세 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 26.
	 * @param 
	 */
	formInitDetail : function() {
		// 해당 메뉴의 권한정보 조회
		AuthCommon.selectMenuAuthor(LogFileRceptPopup.MENU_NO, LogFileRceptPopup.FORM_ID);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
		
		$jquery("#progrmNm").html(Navi.getProgrmNm(LogFileRceptPopup.DETAIL_ID));
		
		window.resizeTo(750, 560);
		showBtnAccessKey();
	},

	/**
	 * 개요 : 저장
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 17.
	 * @param thisObj
	 */
	save : function(thisObj) {
		if (LogFileRceptPopup._formValidate()) {
			
			// 첨부파일 업로드 오류 여부 확인
			if ($jquery("#" + LogFileRceptPopup.FORM_ID + " .uploadifyError").length > 0) {
				alert("첨부파일 업로드 오류 파일 삭제 후 저장하세요.");
				return false;
			}
			
			if (confirm("저장하시겠습니까?")) {
				LogFileRceptPopup.atchFile.hasError = false;
				$jquery("#" + LogFileRceptPopup.FORM_ID + " .buttonSet").hide();
				
				// 첨부파일 업로드
				if($jquery("#" + LogFileRceptPopup.FORM_ID + " #uploadifyAtchQueue > .uploadifyQueueItem").length > 0) {
					$jquery("#" + LogFileRceptPopup.FORM_ID + " #uploadifyAtch").uploadifyUpload();
				} else {
					alert("첨부파일을 선택하세요.");
				}
			}
		}
	},

	/**
	 * 개요 : 입력항목 유효성 검사
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 17.
	 * @param thisObj
	 */
	_formValidate : function() {
		if ($jquery("#" + LogFileRceptPopup.FORM_ID).validationEngine("CLFValidate")) {
			return true;
		} else {
			$jquery.growlUI("warn", "입력값을 확인하세요");
			return false;
		}
	},

	/**
	 * 개요 : 저장처리
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 17.
	 * @param thisObj
	 */
	saveProc : function() {
		var url = "/das/log/createLogFileRceptProcPopup.tech";
		
		$jquery("#content_body").ajaxload(
			"blockLoad",
			jsContextPath + url,
			"POST",
			"html",
			$jquery("#" + LogFileRceptPopup.FORM_ID).separator("separatorRemoveForm").serialize(),
			true
		);
	},
	
	/**
	 * 개요 : 팝업창 닫기
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 17.
	 * @param openerKey
	 */
	popupClose : function(thisObj) {
		if (!isEmpty(LogFileRceptPopup.CALLBACK_FUNCTION)) {
			eval('opener.' + LogFileRceptPopup.CALLBACK_FUNCTION + '()');
		}
		
		window.close();
	}
};