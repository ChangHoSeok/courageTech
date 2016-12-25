var UserAuth = {
	MENU_NO : "",
	FORM_ID : "formUserAuth",
	LIST_ID : "retrieveUserAuthList",
	PAGE_INDEX_ID : "pageIndex",
	TOTAL_CNT : 0,
	
	/**
	 * 개요 : 목록 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 26.
	 * @param 
	 */
	formInitList : function() {
		$jquery("#menuNavi").html(Navi.getNaviNm(UserAuth.MENU_NO));
		$jquery("#progrmNm").html(Navi.getProgrmNm(UserAuth.LIST_ID));
	    
		// 해당 메뉴의 권한정보 조회
		AuthCommon.selectMenuAuthor(UserAuth.MENU_NO);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
		
		$jquery("#" + UserAuth.FORM_ID).separator('separatorAddForm');
		showBtnAccessKey();
	},
	
	fn_egov_link_page : function(pageIndex) {
		UserAuth.setPageIndex(pageIndex);
		UserAuth.refreshList();
	},
	
	/**
	 * 개요 : 페이지 인덱스 설정
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 11
	 * @param pageIndex
	 */
	setPageIndex : function(pageIndex) {
		$jquery("#" + UserAuth.FORM_ID + " #" + UserAuth.PAGE_INDEX_ID).val(pageIndex);
	},

	/**
	 * 개요 : 목록 새로고침
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 11
	 * @param thisObj
	 */
	refreshList : function(thisObj) {
		location.hash = "AC=/system/userauth/retrieveUserAuthList.tech&VA=content_body&" + $jquery("#" + UserAuth.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 목록조회
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 11
	 * @param thisObj
	 */
	search : function(thisObj) {
		UserAuth.setPageIndex(1);
		UserAuth.refreshList(thisObj);
	},
	
	/**
	 * 개요 : 사용자 권한 등록 팝업
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 11.
	 * @param thisObj
	 */
	createViewWindowOpen : function(thisObj) {
		var pUrl = jsContextPath + "/system/userauth/formUserAuthPopup.tech";
		var pName = "popupUserAuth";
		var pWidth = 500;
		var pHeight = 500;
		var openerKey = UserAuth.FORM_ID;
		var returnFunction = 'UserAuth.refreshList'; //callback function
		
		var PopupWindow = PopUpWindowOpenOnlyScroll(pName, pWidth, pHeight);
		
		addFormParameter("popupForm", "esntlId", $jquery(thisObj).prop("id"));
		
		$jquery('#popupForm input[name="openerKey"]').val(openerKey);
		$jquery('#popupForm input[name="returnFunction"]').val(returnFunction);
		$jquery('#popupForm').attr('target', pName);
		$jquery('#popupForm').attr('action', pUrl).submit();
		
		removeFormParameter("popupForm", "esntlId");
		
		PopupWindow.focus();
	},
	
	/**
	 * 개요 : 엑셀 다운로드
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8.
	 * @param thisObj
	 */
	excelDownload : function(thisObj) {
		if(UserAuth.TOTAL_CNT == 0){
			alert("해당 데이터가 없습니다");
		}else if(UserAuth.TOTAL_CNT > 10000){
			alert("1만건 이상 엑셀받기를 할 수 없습니다");
		}else{
			$jquery.growlUI("info", "엑셀 다운로드를 요청하였습니다.<br />잠시만 기다려주세요.");
			
			$jquery("#" + UserAuth.FORM_ID).attr("action", "downloadUserAuthExcel.tech");
			$jquery("#" + UserAuth.FORM_ID).submit();
		}
		
	}
};