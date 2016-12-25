var CommonDetailCode = {
	MENU_NO : "",
	FORM_ID : "formCommonDetailCode",
	POPUP_FORM_ID : "formCommonDetailCodePopup",
	DETAIL_ID : "retrieveCmmnDetailCodeDetail",
	CREATE_ID : "createCmmnDetailCode",
	MODIFY_ID : "modifyCmmnDetailCode",
	PAGE_INDEX_ID : "pageIndex",
	CALLBACK_FUNCTION : "",
	IS_DETAILCODE_DUPLICATE : false,
	OPENER_KEY : "",
	TOTAL_CNT : 0,
	
	/**
	 * 개요 : 등록 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 26.
	 * @param 
	 */
	formInitCreate : function() {
		$jquery("#code").addClass("validF");
		
		if ($jquery("#" + CommonDetailCode.FORM_ID + " #mode").val() == "create") {
			$jquery("#progrmNm").html(Navi.getProgrmNm(CommonDetailCode.CREATE_ID));
		} else {
			$jquery("#progrmNm").html(Navi.getProgrmNm(CommonDetailCode.MODIFY_ID));
		}
		
		window.resizeTo(820, 620);
		showBtnAccessKey();
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
		AuthCommon.selectMenuAuthor(CommonDetailCode.MENU_NO, CommonDetailCode.POPUP_FORM_ID);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
		
		$jquery("#progrmNm").html(Navi.getProgrmNm(CommonDetailCode.DETAIL_ID));
		
		window.resizeTo(820, 420);
		showBtnAccessKey();
	},

	/**
	 * 개요 : 하위 목록 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 26.
	 * @param 
	 */
	_subformInit : function() {
		_listOrderInit(CommonDetailCode.FORM_ID);
		showBtnAccessKey();
	},
	
	fn_egov_link_page : function(pageIndex) {
		CommonDetailCode.setPageIndex(pageIndex);
		CommonDetailCode.refreshList();
	},
	
	/**
	 * 개요 : 페이지 인덱스 설정
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 16
	 * @param pageIndex
	 */
	setPageIndex : function(pageIndex) {
		$jquery("#" + CommonDetailCode.FORM_ID + " #" + this.PAGE_INDEX_ID).val(pageIndex);
	},
	
	/**
	 * 개요 : 목록 새로고침
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 06
	 * @param thisObj
	 */
	refreshList : function(thisObj) {
		$jquery('#commonDetailCodeList').ajaxload(
			'blockLoad',
			jsContextPath + '/system/code/retrieveCmmnDetailCodeList.tech',
			"POST",
			"html",
			$jquery("#" + CommonDetailCode.FORM_ID).separator('separatorRemoveForm').serialize()
		);
	},
	
	/**
	 * 개요 : 공통 상세코드 등록 (팝업)
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 10. 14.
	 * @param thisObj
	 */
	createView : function(thisObj) {
		var pUrl = jsContextPath + "/system/code/formCommonDetailCodePopup.tech";
		var pName = "popupCommonDetailCodeCreate";
		var pWidth = 500;
		var pHeight = 500;
		var openerKey = CommonDetailCode.FORM_ID;
		var returnFunction = 'CommonDetailCode.refreshList'; //callback function
		
		var PopupWindow = PopUpWindowOpenOnlyScroll(pName, pWidth, pHeight);
		
		addFormParameter("popupForm", "codeId", $jquery('#' + CommonDetailCode.FORM_ID + ' > #codeId').val());
		addFormParameter("popupForm", "code", '');
		addFormParameter("popupForm", "mode", 'create');
		
		$jquery('#popupForm input[name="openerKey"]').val(openerKey);
		$jquery('#popupForm input[name="returnFunction"]').val(returnFunction);
		$jquery('#popupForm').attr('target', pName);
		$jquery('#popupForm').attr('action', pUrl).submit();
		
		removeFormParameter("popupForm", "codeId");
		removeFormParameter("popupForm", "code");
		removeFormParameter("popupForm", "mode");
		
		PopupWindow.focus();
	},
	
	/**
	 * 개요 : 공통 상세코드 상세조회 (팝업)
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 10. 14.
	 * @param thisObj
	 */
	detailView : function(thisObj) {
		var pUrl = jsContextPath + "/system/code/formCommonDetailCodePopup.tech";
		var pName = "popupCommonDetailCode";
		var pWidth = 500;
		var pHeight = 500;
		var openerKey = CommonDetailCode.FORM_ID;
		var returnFunction = 'CommonDetailCode.refreshList'; //callback function
		
		var PopupWindow = PopUpWindowOpenOnlyScroll(pName, pWidth, pHeight);
		
		addFormParameter("popupForm", "codeId", $jquery('#' + CommonDetailCode.FORM_ID + ' > #codeId').val());
		addFormParameter("popupForm", "code", $jquery(thisObj).prop('id'));
		addFormParameter("popupForm", "mode", "detail");
		
		$jquery('#popupForm input[name="openerKey"]').val(openerKey);
		$jquery('#popupForm input[name="returnFunction"]').val(returnFunction);
		$jquery('#popupForm').attr('target', pName);
		$jquery('#popupForm').attr('action', pUrl).submit();
		
		removeFormParameter("popupForm", "codeId");
		removeFormParameter("popupForm", "code");
		removeFormParameter("popupForm", "mode");
		
		PopupWindow.focus();
	},
	
	/**
	 * 개요 : 공통 상세코드 수정
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 9.
	 * @param thisObj
	 */
	modifyView : function(thisObj) {
		location.hash = "AC=/system/code/modifyCmmnDetailCode.tech&VA=content_body&" + $jquery("#" + CommonDetailCode.FORM_ID).separator("separatorRemoveForm").serialize();
	},
	
	/**
	 * 개요 : 수정 취소
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 2. 2.
	 * @param 
	 */
	modifyCancel : function(thisObj) {
		location.hash = "AC=/system/code/retrieveCmmnDetailCodeDetail.tech&VA=content_body&" + $jquery("#" + CommonDetailCode.FORM_ID).separator("separatorRemoveForm").serialize();
	},
	
	/**
	 * 개요 : 공통상세코드 저장
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 10. 13.
	 * @param openerKey
	 */
	save : function(thisObj) {
		if ($jquery('#' + CommonDetailCode.FORM_ID + ' #mode').val() == "create" && !CommonDetailCode.IS_DETAILCODE_DUPLICATE) {
			alert("코드 중복검사를 해야 합니다.");
			return false;
		}
		
		if (CommonDetailCode._formValidate()) {
			if(confirm("저장하시겠습니까?")) {
				var url = "/system/code/createCmmnDetailCodeProc.tech";
				
				if ($jquery('#' + CommonDetailCode.FORM_ID + ' #mode').val() == "modify") {
					url = '/system/code/modifyCmmnDetailCodeProc.tech';
				}
				
				$jquery("#content_body").ajaxload(
					"blockLoad",
					jsContextPath + url,
					"POST",
					"html",
					$jquery("#" + CommonDetailCode.FORM_ID).separator("separatorRemoveForm").serialize()
				);
			}
		}
	},
	
	/**
	 * 개요 : 공통 상세코드 항목 유효성 검사
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 10.
	 * @param thisObj
	 */
	_formValidate : function() {
		if ($jquery("#" + CommonDetailCode.FORM_ID).validationEngine('CLFValidate')) {
			return true;
		} else {
			$jquery.growlUI("warn", "입력값을 확인하세요");
			return false;
		}
	},
	
	/**
	 * 개요 : 코드 중복 검사
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 16.
	 * @param thisObj
	 */
	codeDuplicateCheck : function(thisObj) {
		CommonDetailCode.IS_DETAILCODE_DUPLICATE = false;
		var codeId = $jquery("#" + CommonDetailCode.FORM_ID + " #codeId").val();
		var code = $jquery("#" + CommonDetailCode.FORM_ID + " #code").val();
		
		if (isEmpty(code)) {
			alert('공통 상세코드를 입력하세요');
			return false;
		}
		
		var paramObj = {
			codeId : codeId,
			code : code
		};
		
		cmmnDetailCodeManageService.selectCmmnDetailCodeDetail(paramObj, {
			callback : function (returnObj) {
				if (returnObj !== null) {
					$jquery("#code").removeClass("validT");
					$jquery("#code").addClass("validF");
					
					alert('중복된 코드가 존재합니다.');
				} else {
					$jquery("#code").removeClass("validF");
					$jquery("#code").addClass("validT");
					
					$jquery("#code").data("duplecateText", paramObj.code);
					
					alert('등록가능한 코드 입니다.');
					CommonDetailCode.IS_DETAILCODE_DUPLICATE = true;
				}
			},
			errorHandler : function(errorString, exception) {
				alert('오류가 발생되었습니다.\r\n');
			},
			async : false
		});
	},
	
	/**
	 * 개요 : 중복체크 텍스트 변경상태 확인
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 2. 5.
	 * @param 
	 */
	duplecateTextCheck : function(thisObj) {
		if ($jquery(thisObj).val() !== $jquery(thisObj).data("duplecateText")) {
			$jquery(thisObj).removeClass("validT");
			$jquery(thisObj).addClass("validF");
			CommonDetailCode.IS_PROGRM_DUPLECATE = false;
		} else {
			$jquery(thisObj).removeClass("validF");
			$jquery(thisObj).addClass("validT");
			CommonDetailCode.IS_PROGRM_DUPLECATE = true;
		}
	},
	
	/**
	 * 개요 : 상세보기 팝업창 닫기
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 10. 13.
	 * @param openerKey
	 */
	popupClose : function(thisObj) {
		if (!isEmpty(CommonDetailCode.CALLBACK_FUNCTION)) {
			eval('opener.' + CommonDetailCode.CALLBACK_FUNCTION + '()');
		}
		
		window.close();
	},
	
	/**
	 * 개요 : opener 유효성 검사
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 10. 13.
	 * @param openerKey
	 */
	_fncCheckOpener : function(openerKey) {
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
	 * 개요 : 엑셀 다운로드
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8.
	 * @param thisObj
	 */
	excelDownload : function(thisObj){
		if(CommonDetailCode.TOTAL_CNT == 0){
			alert("해당 데이터가 없습니다");
		}else if(CommonDetailCode.TOTAL_CNT > 10000){
			alert("1만건 이상 엑셀받기를 할 수 없습니다");
		}else{
			$jquery.growlUI("info", "엑셀 다운로드를 요청하였습니다.<br />잠시만 기다려주세요.");
			
			$jquery("#" + CommonDetailCode.FORM_ID).attr("action", jsContextPath + "/system/code/downloadCommonDetailCodeExcel.tech");
			$jquery("#" + CommonDetailCode.FORM_ID).submit();
		}
		
	}
};