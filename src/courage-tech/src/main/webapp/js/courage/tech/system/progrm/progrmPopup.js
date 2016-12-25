var ProgrmPopup = {
	MENU_NO : "",
	FORM_ID : "formProgrmPopup",
	PAGE_INDEX_ID : "pageIndex",
	OPENER_KEY : "",
	CALLBACK_FUNCTION : "",
	DISABLE_PROGRM_FILE_NM : "",
	
	/**
	 * 개요 : 목록 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 26.
	 * @param 
	 */
	formInit : function() {
		window.resizeTo(1024, 720);
		
		$jquery("#progrmNm").html(Navi.getProgrmNm(ProgrmPopup.FORM_ID));
		AuthCommon.selectMenuAuthor(ProgrmPopup.MENU_NO, ProgrmPopup.FORM_ID);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
			$jquery("input:checkbox").prop("disabled", true);
		}
		
		if (!isEmpty(ProgrmPopup.DISABLE_PROGRM_FILE_NM)) {
			var disableProgrmFileNm = ProgrmPopup.DISABLE_PROGRM_FILE_NM.split(",");
			
			for (var i = 0; i < disableProgrmFileNm.length; i++) {
				$jquery("#" + disableProgrmFileNm[i]).prop("disabled", true);
			}
		}
		
		showBtnAccessKey();
	},
	
	/**
	 * 개요 : 페이징 처리
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 9.
	 * @param pageIndex
	 */
	fn_egov_link_page : function(pageIndex) {
		ProgrmPopup.setPageIndex(pageIndex);
		ProgrmPopup.refreshList();
	},
	
	/**
	 * 개요 : 페이지 인덱스 설정
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 9.
	 * @param pageIndex
	 */
	setPageIndex : function(pageIndex) {
		$jquery("#" + ProgrmPopup.FORM_ID + " #" + ProgrmPopup.PAGE_INDEX_ID).val(pageIndex);
	},

	/**
	 * 개요 : 목록 새로고침
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 9.
	 * @param thisObj
	 */
	refreshList : function(thisObj) {
		$jquery("#content_body").ajaxload(
			"blockLoad",
			jsContextPath + "/system/progrm/retrieveProgrmListPopup.do",
			"POST",
			"html",
			$jquery("#" + ProgrmPopup.FORM_ID).separator("separatorRemoveForm").serialize()
		);
	},

	/**
	 * 개요 : 검색
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 9.
	 * @param thisObj
	 */
	search : function(thisObj) {
		this.setPageIndex(1);
		this.refreshList(thisObj);
	},
	
	/**
	 * 개요 : 프로그램 정보 전달
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 9.
	 * @param thisObj
	 * @param idx
	 */
	setProgrm : function(thisObj){
		if (ProgrmPopup._fncCheckOpener()) {
			if ($jquery("input[name=progrmChk]:checked:checked").length <= 0) {
				alert('프로그램을 선택하세요');
				return false;
			}
			
			var resultList = [];
			
			$jquery("input[name=progrmChk]:checked:checked").each(function(index) {
				var progrmChkId = $jquery(this).attr('id');
				
				var progrmObj = {
					progrmFileNm : $jquery('#progrmFileNm_' + progrmChkId).val(),
					progrmSe : $jquery('#progrmSe_' + progrmChkId).val(),
					progrmStrePath : $jquery('#progrmStrePath_' + progrmChkId).val(),
					progrmKoreanNm : $jquery('#progrmKoreanNm_' + progrmChkId).val(),
					progrmDc : $jquery('#progrmDc_' + progrmChkId).val(),
					url : $jquery('#url_' + progrmChkId).val()
				};
				
				resultList.push(progrmObj);
			});
			
			opener.setProgrm(resultList);
			
			ProgrmPopup.popupClose();
		} else {
			window.close();
		}
	},
	
	/**
	 * 개요 : opener 유효성 검사
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 9.
	 * @param 
	 */
	_fncCheckOpener : function() {
		if (opener == null || opener.closed) {
			alert("현재 창을 호출한 화면이 종료되었습니다.\n현재 창을 종료합니다.");
			return false;
		} else if (isEmpty($jquery("#popupForm #openerKey", opener.document).val())) {
			alert("현재 창을 호출한 화면이 이동되었습니다.\n현재 창을 종료합니다.");
			return false;
		} else if ($jquery("#popupForm #openerKey", opener.document).val() != ProgrmPopup.OPENER_KEY) {
			alert("현재 창을 호출한 화면이 변경되었습니다.\n현재 창을 종료합니다.");
			return false;
		}
		
		return true;
	},

	/**
	 * 개요 : 팝업창 닫기
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 9.
	 * @param thisObj
	 */
	popupClose : function(thisObj) {
		if (!isEmpty(ProgrmPopup.CALLBACK_FUNCTION)) {
			if (ProgrmPopup._fncCheckOpener()) {
				eval("opener." + ProgrmPopup.CALLBACK_FUNCTION + "()");
			}
		}
		
		window.close();
	},
	
	/**
	 * 개요 : 프로그램 전체 선택/해제
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 9.
	 * @param thisObj
	 */
	progrmAllCheck : function(thisObj) {
		checkboxAllCheck($jquery("input[name=progrmChk]"), thisObj);
	}
};