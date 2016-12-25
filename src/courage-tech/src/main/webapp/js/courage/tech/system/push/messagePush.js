var MessagePush = {
	MENU_NO : "",
	FORM_ID : "formMessagePush",
	LIST_ID : "retrieveMessagePushList",
	CREATE_ID : "createMessagePush",
	PAGE_INDEX_ID : "pageIndex",
	ACTION_STATUS : "",
	TOTAL_CNT : 0,
	CALLBACK_FUNCTION : "",
	OPENER_KEY : "",
	
	/**
	 * 개요 : 목록 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 17.
	 * @param 
	 */
	formInitList : function() {
		$jquery("#menuNavi").html(Navi.getNaviNm(MessagePush.MENU_NO));
		$jquery("#progrmNm").html(Navi.getProgrmNm(MessagePush.LIST_ID));
	    
		// 해당 메뉴의 권한정보 조회
		AuthCommon.selectMenuAuthor(MessagePush.MENU_NO);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
		
		$jquery("#" + MessagePush.FORM_ID).separator('separatorAddForm');
		showBtnAccessKey();
	},
	
	/**
	 * 개요 : 등록 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 17.
	 * @param 
	 */
	formInitCreate : function() {
		$jquery("#menuNavi").html(Navi.getNaviNm(MessagePush.MENU_NO));
		$jquery("#progrmNm").html(Navi.getProgrmNm(MessagePush.CREATE_ID));
		
		$jquery("#" + MessagePush.FORM_ID).separator('separatorAddForm');
		
		$jquery("#" + MessagePush.FORM_ID + " #mssageCn").byteChk("keyup", 2, 256);
		
		window.resizeTo(650, 380);
		showBtnAccessKey();
		
		if (MessagePush.ACTION_STATUS == "success") {
			$jquery.growlUI("info", "저장되었습니다.");
		}
	},
	
	fn_egov_link_page : function(pageIndex) {
		MessagePush.setPageIndex(pageIndex);
		MessagePush.refreshList();
	},
	
	/**
	 * 개요 : 페이지 인덱스 설정
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 17.
	 * @param pageIndex
	 */
	setPageIndex : function(pageIndex) {
		$jquery("#" + MessagePush.FORM_ID + " #" + MessagePush.PAGE_INDEX_ID).val(pageIndex);
	},

	/**
	 * 개요 : 목록 새로고침
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 17.
	 * @param thisObj
	 */
	refreshList : function(thisObj) {
		location.hash = "AC=/system/push/retrieveMessagePushList.tech&VA=content_body&" + $jquery("#" + MessagePush.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 목록조회
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 17.
	 * @param thisObj
	 */
	search : function(thisObj) {
		MessagePush.setPageIndex(1);
		MessagePush.refreshList(thisObj);
	},

	/**
	 * 개요 : 상세조회 페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 17.
	 * @param thisObj
	 */
	detailView : function(thisObj) {
		var mssageCn = $jquery("#mssageCn_" + $jquery(thisObj).attr("id")).val();
		
		mssageCn = mssageCn.replaceAll(" ", "&nbsp;");
		mssageCn = mssageCn.replaceAll("\r\n", "<br />");
		mssageCn = mssageCn.replaceAll("\n", "<br />");
		
		$jquery.alert(mssageCn.trim());
	},

	/**
	 * 개요 : 목록조회 페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 17.
	 * @param thisObj
	 */
	listView : function(thisObj) {
		location.hash = "AC=/system/push/retrieveMessagePushList.tech&VA=content_body&" + $jquery("#" + MessagePush.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 등록 페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 17.
	 * @param thisObj
	 */
	createView : function(thisObj) {
		var pUrl = jsContextPath + "/system/push/formMessagePushPopup.tech";
		var pName = "popupMessagePushCreate";
		var pWidth = 500;
		var pHeight = 500;
		var openerKey = MessagePush.FORM_ID;
		var returnFunction = 'MessagePush.refreshList'; //callback function
		
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
	 * 개요 : 저장
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 17.
	 * @param thisObj
	 */
	save : function(thisObj) {
		if (MessagePush._formValidate()) {
			if (confirm("저장하시겠습니까?")) {
				MessagePush.saveProc();
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
		if ($jquery("#" + MessagePush.FORM_ID).validationEngine("CLFValidate")) {
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
		var url = "/system/push/createMessagePushProc.tech";
		
		$jquery("#content_body").ajaxload(
			"blockLoad",
			jsContextPath + url,
			"POST",
			"html",
			$jquery("#" + MessagePush.FORM_ID).separator("separatorRemoveForm").serialize(),
			false
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
		if (!isEmpty(MessagePush.CALLBACK_FUNCTION)) {
			eval('opener.' + MessagePush.CALLBACK_FUNCTION + '()');
		}
		
		window.close();
	},
	
	/**
	 * 개요 : Push 메시지 미리보기
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 5. 18.
	 * @param 
	 */
	preview : function(thisObj) {
		var mssageCn = $jquery("#" + MessagePush.FORM_ID + " #mssageCn").val();
		
		if (!isEmpty(mssageCn)) {
			mssageCn = mssageCn.replaceAll(" ", "&nbsp;");
			mssageCn = mssageCn.replaceAll("\r\n", "<br />");
			mssageCn = mssageCn.replaceAll("\n", "<br />");
			
			$jquery.alert(mssageCn.trim());
		} else {
			alert("메시지 내용을 입력하세요.");
		}
	},
	
	/**
	 * 개요 : 엑셀 다운로드
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 17.
	 * @param thisObj
	 */
	excelDownload : function(thisObj){
		if(MessagePush.TOTAL_CNT == 0){
			alert("해당 데이터가 없습니다");
		}else if(MessagePush.TOTAL_CNT > 10000){
			alert("1만건 이상 엑셀받기를 할 수 없습니다");
		}else{
			$jquery.growlUI("info", "엑셀 다운로드를 요청하였습니다.<br />잠시만 기다려주세요.");
			
			$jquery("#" + MessagePush.FORM_ID).attr("action", "downloadMessagePushExcel.tech");
			$jquery("#" + MessagePush.FORM_ID).submit();
		}
	}
};