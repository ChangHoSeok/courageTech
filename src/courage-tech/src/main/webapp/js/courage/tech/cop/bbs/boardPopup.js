var BoardPopup = {
	MENU_NO : "",
	FORM_ID : "formBoard",
	DETAIL_ID : "retrieveBoardDetail",
	USE_AT : "",
	PAGE_INDEX_ID : "pageIndex",
	
	/**
	 * 개요 : 목록 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 12. 11.
	 * @param 
	 */
	formInit : function() {
		window.resizeTo(1024, 720);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
		
		showBtnAccessKey();
	},
	
	formInitDetail : function() {
		window.resizeTo(1024, 720);
		
		if (BoardPopup.USE_AT === "Y") {
			$jquery("#boardContentsForm").submit();
		}
		
		showBtnAccessKey();
	},
	
	/**
	 * 개요 : 페이징 처리
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 12. 11.
	 * @param pageIndex
	 */
	fn_egov_link_page : function(pageIndex) {
		BoardPopup.setPageIndex(pageIndex);
		BoardPopup.refreshList();
	},
	
	/**
	 * 개요 : 페이지 인덱스 설정
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 12. 11.
	 * @param pageIndex
	 */
	setPageIndex : function(pageIndex) {
		$jquery("#" + BoardPopup.FORM_ID + " #" + BoardPopup.PAGE_INDEX_ID).val(pageIndex);
	},

	/**
	 * 개요 : 목록 새로고침
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 12. 11.
	 * @param thisObj
	 */
	refreshList : function(thisObj) {
		$jquery("#content_body").ajaxload(
			"blockLoad",
			jsContextPath + "/cop/bbs/retrieveBoardListPopup.do",
			"POST",
			"html",
			$jquery("#" + BoardPopup.FORM_ID).separator("separatorRemoveForm").serialize()
		);
	},
	
	/**
	 * 개요 : 상세조회 페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 12. 12.
	 * @param thisObj
	 */
	detailView : function(thisObj) {
		$jquery("#" + BoardPopup.FORM_ID + " #nttId").val($jquery(thisObj).prop("id"));
		location.hash = "AC=/cop/bbs/retrieveBoardDetail.tech&VA=content_body&" + $jquery("#" + BoardPopup.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 목록조회 페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 12. 12.
	 * @param thisObj
	 */
	listView : function(thisObj) {
		location.hash = "AC=/cop/bbs/retrieveBoardList.tech&VA=content_body&" + $jquery("#" + BoardPopup.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 검색
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 12. 11.
	 * @param thisObj
	 */
	search : function(thisObj) {
		this.setPageIndex(1);
		this.refreshList(thisObj);
	},
	
	/**
	 * 개요 : 팝업창 닫기
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 12. 11.
	 * @param thisObj
	 */
	popupClose : function(thisObj) {
		window.close();
	}
};