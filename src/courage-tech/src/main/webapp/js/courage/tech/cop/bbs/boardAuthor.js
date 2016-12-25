var BoardAuthor = {
	MENU_NO : "",
	FORM_ID : "formBoardAuthor",
	LIST_ID : "retrieveBoardAuthorList",
	DETAIL_ID : "retrieveBoardAuthorDetail",
	CREATE_ID : "createBoardAuthor",
	MODIFY_ID : "modifyBoardAuthor",
	PAGE_INDEX_ID : "pageIndex",
	ACTION_STATUS : "",
	TOTAL_CNT : 0,
	
	/**
	 * 개요 : 목록 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 11.
	 * @param 
	 */
	formInitList : function() {
		$jquery("#menuNavi").html(Navi.getNaviNm(BoardAuthor.MENU_NO));
		$jquery("#progrmNm").html(Navi.getProgrmNm(BoardAuthor.LIST_ID));
	    
		// 해당 메뉴의 권한정보 조회
		AuthCommon.selectMenuAuthor(BoardAuthor.MENU_NO);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
		
		$jquery("#" + BoardAuthor.FORM_ID).separator('separatorAddForm');
		showBtnAccessKey();
	},
	
	fn_egov_link_page : function(pageIndex) {
		BoardAuthor.setPageIndex(pageIndex);
		BoardAuthor.refreshList();
	},
	
	/**
	 * 개요 : 페이지 인덱스 설정
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 11.
	 * @param pageIndex
	 */
	setPageIndex : function(pageIndex) {
		$jquery("#" + BoardAuthor.FORM_ID + " #" + BoardAuthor.PAGE_INDEX_ID).val(pageIndex);
	},

	/**
	 * 개요 : 목록 새로고침
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 11.
	 * @param thisObj
	 */
	refreshList : function(thisObj) {
		location.hash = "AC=/cop/bbs/retrieveBoardAuthorList.tech&VA=content_body&" + $jquery("#" + BoardAuthor.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 목록조회
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 11.
	 * @param thisObj
	 */
	search : function(thisObj) {
		BoardAuthor.setPageIndex(1);
		BoardAuthor.refreshList(thisObj);
	},

	/**
	 * 개요 : 저장
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 11.
	 * @param thisObj
	 */
	save : function(thisObj) {
		if (BoardAuthor._formValidate()) {
			if (confirm("저장하시겠습니까?")) {
				BoardAuthor.saveProc();
			}
		}
	},
	
	/**
	 * 개요 : 입력항목 유효성 검사
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 11.
	 * @param thisObj
	 */
	_formValidate : function() {
		if ($jquery("#" + BoardAuthor.FORM_ID).validationEngine("CLFValidate")) {
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
	 * @Date : 2016. 7. 11.
	 * @param thisObj
	 */
	saveProc : function() {
		// 권한 선택 값 설정
		$jquery("#" + BoardAuthor.FORM_ID + " input[type=checkbox]").val("N");
		$jquery("#" + BoardAuthor.FORM_ID + " input[type=checkbox]:checked").val("Y");
		$jquery("#" + BoardAuthor.FORM_ID + " input[type=checkbox]").prop("checked", true);
		
		$jquery("#content_body").ajaxload(
			"blockLoad",
			jsContextPath + "/cop/bbs/createBoardAuthorProc.tech",
			"POST",
			"html",
			$jquery("#" + BoardAuthor.FORM_ID).separator("separatorRemoveForm").serialize(),
			false
		);
	}
};