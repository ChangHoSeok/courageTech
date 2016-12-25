var LogFileRcept = {
	MENU_NO : "",
	FORM_ID : "formLogFileRcept",
	LIST_ID : "retrieveLogFileRceptList",
	PAGE_INDEX_ID : "pageIndex",
	ACTION_STATUS : "",
	TOTAL_CNT : 0,
	IS_DUPLECATE : false,
	
	/**
	 * 개요 : 목록 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 18.
	 * @param 
	 */
	formInitList : function() {
		$jquery("#menuNavi").html(Navi.getNaviNm(LogFileRcept.MENU_NO));
		$jquery("#progrmNm").html(Navi.getProgrmNm(LogFileRcept.LIST_ID));
	    
		// 해당 메뉴의 권한정보 조회
		AuthCommon.selectMenuAuthor(LogFileRcept.MENU_NO);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
		
		$jquery("#" + LogFileRcept.FORM_ID).separator('separatorAddForm');
		showBtnAccessKey();
	},
	
	/**
	 * 개요 : 상세 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 18.
	 * @param 
	 */
	formInitDetail : function() {
		$jquery("#menuNavi").html(Navi.getNaviNm(LogFileRcept.MENU_NO));
		$jquery("#progrmNm").html(Navi.getProgrmNm(LogFileRcept.DETAIL_ID));
		
		// 해당 메뉴의 권한정보 조회
		AuthCommon.selectMenuAuthor(LogFileRcept.MENU_NO);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
		
		$jquery("#" + LogFileRcept.FORM_ID).separator('separatorAddForm');
		showBtnAccessKey();
	},
	
	fn_egov_link_page : function(pageIndex) {
		LogFileRcept.setPageIndex(pageIndex);
		LogFileRcept.refreshList();
	},
	
	/**
	 * 개요 : 페이지 인덱스 설정
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 18.
	 * @param pageIndex
	 */
	setPageIndex : function(pageIndex) {
		$jquery("#" + LogFileRcept.FORM_ID + " #" + LogFileRcept.PAGE_INDEX_ID).val(pageIndex);
	},

	/**
	 * 개요 : 목록 새로고침
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 18.
	 * @param thisObj
	 */
	refreshList : function(thisObj) {
		location.hash = "AC=/das/log/retrieveLogFileRceptList.tech&VA=content_body&" + $jquery("#" + LogFileRcept.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 목록조회
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 18.
	 * @param thisObj
	 */
	search : function(thisObj) {
		LogFileRcept.setPageIndex(1);
		LogFileRcept.refreshList(thisObj);
	},
	
	/**
	 * 개요 : 등록 팝업 호출
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 8. 10.
	 * @param thisObj
	 */
	createView : function(thisObj) {
		var pUrl = jsContextPath + "/das/log/formLogFileRceptPopup.tech";
		var pName = "popupLogFileRcept";
		var pWidth = 500;
		var pHeight = 500;
		var openerKey = LogFileRcept.FORM_ID;
		var returnFunction = 'LogFileRcept.refreshList'; //callback function
		
		var PopupWindow = PopUpWindowOpenOnlyScroll(pName, pWidth, pHeight);
		
		addFormParameter("popupForm", "mode", 'create');
		
		$jquery('#popupForm input[name="openerKey"]').val(openerKey);
		$jquery('#popupForm input[name="returnFunction"]').val(returnFunction);
		$jquery('#popupForm').attr('target', pName);
		$jquery('#popupForm').attr('action', pUrl).submit();
		
		removeFormParameter("popupForm", "mode");
		
		PopupWindow.focus();
	},
	
	/**
	 * 개요 : 상세조회 팝업 호출
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 8. 23.
	 * @param thisObj
	 */
	detailView : function(thisObj) {
		var pUrl = jsContextPath + "/das/log/formLogFileRceptPopup.tech";
		var pName = "popupLogFileRcept";
		var pWidth = 500;
		var pHeight = 500;
		var openerKey = LogFileRcept.FORM_ID;
		var returnFunction = ""; //callback function
		
		var PopupWindow = PopUpWindowOpenOnlyScroll(pName, pWidth, pHeight);
		
		var paramVal = $jquery(thisObj).attr("id").split("|");
		
		addFormParameter("popupForm", "mode", "detail");
		addFormParameter("popupForm", "groupId", paramVal[0]);
		addFormParameter("popupForm", "rceptId", paramVal[1]);
		
		$jquery('#popupForm input[name="openerKey"]').val(openerKey);
		$jquery('#popupForm input[name="returnFunction"]').val(returnFunction);
		$jquery('#popupForm').attr('target', pName);
		$jquery('#popupForm').attr('action', pUrl).submit();
		
		removeFormParameter("popupForm", "mode");
		removeFormParameter("popupForm", "groupId");
		removeFormParameter("popupForm", "rceptId");
		
		PopupWindow.focus();
	},
	
	/**
	 * 개요 : 로그파일접수 정보 삭제처리
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 8. 24.
	 * @param thisObj
	 */
	deleteLogFileRcept : function(thisObj) {
		var paramVal = $jquery(thisObj).attr("id").split("|");
		
		$jquery("#" + LogFileRcept.FORM_ID + " #groupId").val(paramVal[0]);
		$jquery("#" + LogFileRcept.FORM_ID + " #rceptId").val(paramVal[1]);
		
		if (confirm("접수정보를 삭제하면 등록된 로그정보가 모두 삭제됩니다.\n삭제 하시겠습니까?")) {
			location.hash = "AC=/das/log/deleteLogFileRcept.tech&VA=content_body&" + $jquery("#" + LogFileRcept.FORM_ID).separator("separatorRemoveForm").serialize();
		}
	}
};