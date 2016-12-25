var LoginSttus = {
	MENU_NO : "",
	FORM_ID : "formLoginSttus",
	LIST_ID : "retrieveLoginSttusList",
	PAGE_INDEX_ID : "pageIndex",
	TOTAL_CNT : 0,
	
	/**
	 * 개요 : 목록 초기화
	 * 
	 * @LoginSttusor : Seok Chang Ho
	 * @Date : 2016. 9. 28.
	 */
	formInitList : function() {
		$jquery("#menuNavi").html(Navi.getNaviNm(LoginSttus.MENU_NO));
		$jquery("#progrmNm").html(Navi.getProgrmNm(LoginSttus.LIST_ID));
	    
	    // 해당 메뉴의 권한정보 조회
		AuthCommon.selectMenuAuthor(LoginSttus.MENU_NO);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
		
		$jquery("#" + LoginSttus.FORM_ID).separator('separatorAddForm');
		showBtnAccessKey();
	},

	fn_egov_link_page : function(pageIndex) {
		LoginSttus.setPageIndex(pageIndex);
		LoginSttus.refreshList();
	},
	
	/**
	 * 개요 : 페이지 인덱스 설정
	 * 
	 * @LoginSttusor : Seok Chang Ho
	 * @Date : 2016. 9. 28.
	 * @param pageIndex
	 */
	setPageIndex : function(pageIndex) {
		$jquery("#" + LoginSttus.FORM_ID + " #" + LoginSttus.PAGE_INDEX_ID).val(pageIndex);
	},

	/**
	 * 개요 : 목록 새로고침
	 * 
	 * @LoginSttusor : Seok Chang Ho
	 * @Date : 2016. 9. 28.
	 * @param thisObj
	 */
	refreshList : function(thisObj) {
		location.hash = "AC=/uat/login/retrieveLoginSttusList.tech&VA=content_body&" + $jquery("#" + LoginSttus.FORM_ID).separator("separatorRemoveForm").serialize();
	}
};