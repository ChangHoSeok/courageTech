var MenuPopup = {
	MENU_NO : "",
	FORM_ID : "formMenuPopup",
	PAGE_INDEX_ID : "pageIndex",
	SUB_MENU_ID : "",
	OPENER_KEY : "",
	CALLBACK_FUNCTION : "",
	
	/**
	 * 개요 : 목록 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 14. 
	 * @param 
	 */
	formInit : function() {
		window.resizeTo(1024, 750);
		
		$jquery("#progrmNm").html(Navi.getProgrmNm(MenuPopup.FORM_ID));
		AuthCommon.selectMenuAuthor(MenuPopup.MENU_NO, MenuPopup.FORM_ID);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
		
		$jquery("#" + MenuPopup.SUB_MENU_ID).prop("disabled", true);
		
		showBtnAccessKey();
	},
	
	/**
	 * 개요 : 페이징 처리
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 14. 
	 * @param pageIndex
	 */
	fn_egov_link_page : function(pageIndex) {
		MenuPopup.setPageIndex(pageIndex);
		MenuPopup.refreshList();
	},
	
	/**
	 * 개요 : 페이지 인덱스 설정
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 14. 
	 * @param pageIndex
	 */
	setPageIndex : function(pageIndex) {
		$jquery("#" + MenuPopup.FORM_ID + " #" + MenuPopup.PAGE_INDEX_ID).val(pageIndex);
	},

	/**
	 * 개요 : 목록 새로고침
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 14. 
	 * @param thisObj
	 */
	refreshList : function(thisObj) {
		$jquery("#content_body").ajaxload(
			"blockLoad",
			jsContextPath + "/system/menu/retrieveMenuListPopup.do",
			"POST",
			"html",
			$jquery("#" + MenuPopup.FORM_ID).separator("separatorRemoveForm").serialize()
		);
	},

	/**
	 * 개요 : 검색
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 14. 
	 * @param thisObj
	 */
	search : function(thisObj) {
		this.setPageIndex(1);
		this.refreshList(thisObj);
	},
	
	/**
	 * 개요 : 메뉴 정보 전달
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 14. 
	 * @param thisObj
	 * @param idx
	 */
	setMenu : function(thisObj){
		if (MenuPopup._fncCheckOpener()) {
			var menuId = $jquery(thisObj).attr("id");
			var menuObj = {
				menuId: menuId,
				menuNm : $jquery("#menuNm_" + menuId).val(),
			};
			
			opener.setMenu(menuObj);
			MenuPopup.popupClose();
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
		} else if ($jquery("#popupForm #openerKey", opener.document).val() != MenuPopup.OPENER_KEY) {
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
		if (!isEmpty(MenuPopup.CALLBACK_FUNCTION)) {
			if (MenuPopup._fncCheckOpener()) {
				eval("opener." + MenuPopup.CALLBACK_FUNCTION + "()");
			}
		}
		
		window.close();
	}
};