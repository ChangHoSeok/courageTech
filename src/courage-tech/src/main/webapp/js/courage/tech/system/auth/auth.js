var Auth = {
	MENU_NO : "",
	FORM_ID : "formAuth",
	LIST_ID : "retrieveAuthList",
	DETAIL_ID : "retrieveAuthDetail",
	CREATE_ID : "createAuth",
	MODIFY_ID : "modifyAuth",
	PAGE_INDEX_ID : "pageIndex",
	IS_AUTH_DUPLICATE : false,
	TOTAL_CNT : 0,
	
	/**
	 * 개요 : 목록 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 26.
	 */
	formInitList : function() {
		$jquery("#menuNavi").html(Navi.getNaviNm(Auth.MENU_NO));
		$jquery("#progrmNm").html(Navi.getProgrmNm(Auth.LIST_ID));
	    
	    // 해당 메뉴의 권한정보 조회
		AuthCommon.selectMenuAuthor(Auth.MENU_NO);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
		
		$jquery("#" + Auth.FORM_ID).separator('separatorAddForm');
		showBtnAccessKey();
	},
	
	/**
	 * 개요 : 등록 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 26.
	 */
	formInitCreate : function() {
		$jquery("#authorCode").addClass("validF");
		
		$jquery('#detailMenuList').ajaxload(
			'blockLoad',
			jsContextPath + '/system/auth/retrieveAuthMenuList.do',
			"POST",
			"html",
			$jquery("#" + Auth.FORM_ID).separator('separatorRemoveForm').serialize(),
			false
		);
		
		// 시작페이지 설정
		$jquery("input[name=authorIndexUrl][value='" + $jquery("#authorIndexUrlChkVal").val() + "']").prop("checked", true);
		
		Auth.IS_AUTH_DUPLICATE = false;
		
		if ($jquery("#" + Auth.FORM_ID + " #mode").val() == "create") {
			$jquery("#progrmNm").html(Navi.getProgrmNm(Auth.CREATE_ID));
		} else {
			$jquery("#progrmNm").html(Navi.getProgrmNm(Auth.MODIFY_ID));
		}
		
		$jquery("#menuNavi").html(Navi.getNaviNm(Auth.MENU_NO));
		showBtnAccessKey();
	},

	/**
	 * 개요 : 상세 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 26.
	 */
	formInitDetail : function() {
		$jquery('#detailMenuList').ajaxload(
			'blockLoad',
			jsContextPath + '/system/auth/retrieveAuthMenuList.do',
			"POST",
			"html",
			$jquery("#" + Auth.FORM_ID).separator('separatorRemoveForm').serialize(),
			false
		);
		
		// 시작페이지 설정
		if (!isEmpty($jquery("#authorIndexUrlChkVal").val())) {
			$jquery("input[name=authorIndexUrl][value='" + $jquery("#authorIndexUrlChkVal").val() + "']").prop("checked", true);
		}
		
		$jquery("#menuNavi").html(Navi.getNaviNm(Auth.MENU_NO));
		$jquery("#progrmNm").html(Navi.getProgrmNm(Auth.DETAIL_ID));
		
		// 해당 메뉴의 권한정보 조회
		AuthCommon.selectMenuAuthor(Auth.MENU_NO);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
		
		Auth.IS_AUTH_DUPLICATE = false;
		showBtnAccessKey();
	},
	
	fn_egov_link_page : function(pageIndex) {
		Auth.setPageIndex(pageIndex);
		Auth.refreshList();
	},
	
	/**
	 * 개요 : 페이지 인덱스 설정
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8
	 * @param pageIndex
	 */
	setPageIndex : function(pageIndex) {
		$jquery("#" + Auth.FORM_ID + " #" + Auth.PAGE_INDEX_ID).val(pageIndex);
	},

	/**
	 * 개요 : 목록 새로고침
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8
	 * @param thisObj
	 */
	refreshList : function(thisObj) {
		location.hash = "AC=/system/auth/retrieveAuthList.tech&VA=content_body&" + $jquery("#" + Auth.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 목록조회
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8
	 * @param thisObj
	 */
	search : function(thisObj) {
		Auth.setPageIndex(1);
		Auth.refreshList(thisObj);
	},

	/**
	 * 개요 : 상세조회 페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8
	 * @param thisObj
	 */
	detailView : function(thisObj) {
		$jquery("#authorCode").val($jquery(thisObj).prop("id"));
		location.hash = "AC=/system/auth/retrieveAuthDetail.tech&VA=content_body&" + $jquery("#" + Auth.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 목록조회 페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8
	 * @param thisObj
	 */
	listView : function(thisObj) {
		location.hash = "AC=/system/auth/retrieveAuthList.tech&VA=content_body&" + $jquery("#" + Auth.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 등록 페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8.
	 * @param thisObj
	 */
	createView : function(thisObj) {
		$jquery("#" + Auth.FORM_ID + " #authorCode").val("");
		location.hash = "AC=/system/auth/createAuth.tech&VA=content_body&" + $jquery("#" + Auth.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 수정페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8.
	 * @param thisObj
	 */
	modifyView : function(thisObj) {
		location.hash = "AC=/system/auth/modifyAuth.tech&VA=content_body&" + $jquery("#" + Auth.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 수정취소
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8.
	 * @param thisObj
	 */
	modifyCancel : function(thisObj) {
		location.hash = "AC=/system/auth/retrieveAuthDetail.tech&VA=content_body&" + $jquery("#" + Auth.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 저장
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8.
	 * @param thisObj
	 */
	save : function(thisObj) {
		if (Auth._formValidate()) {
			if (confirm("저장하시겠습니까?")) {
				Auth.saveProc();
			}
		}
	},

	/**
	 * 개요 : 입력항목 유효성 검사
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8.
	 * @param thisObj
	 */
	_formValidate : function() {
		if ($jquery("#" + Auth.FORM_ID).validationEngine("CLFValidate")) {
			if ($jquery("#" + Auth.FORM_ID + " #mode").val() == "create" && !Auth.IS_AUTH_DUPLICATE) {
				alert("권한ID 중복검사를 해야 합니다.");
				return false;
			}
			
			if (isEmpty($jquery("input[name=authorIndexUrl]:checked").val())) {
				alert("시작페이지를 선택하세요.");
				return false;
			}
			
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
	 * @Date : 2014. 12. 8.
	 * @param thisObj
	 */
	saveProc : function() {
		// 메뉴권한 값 초기화
		$jquery('#' + Auth.FORM_ID + ' input[name$=Array]:checkbox').val('N');
		$jquery('#' + Auth.FORM_ID + ' input[name$=Array]:checkbox:checked').val('Y');
		$jquery('#' + Auth.FORM_ID + ' input[name$=Array]:checkbox').attr('checked', 'checked');
		
		// 사용자 기본권한 값 초기화
		$jquery('#' + Auth.FORM_ID + ' #mberBassAuthorYn:checkbox').val('N');
		$jquery('#' + Auth.FORM_ID + ' #mberBassAuthorYn:checkbox:checked').val('Y');
		$jquery('#' + Auth.FORM_ID + ' #mberBassAuthorYn').attr('checked', 'checked');
		
		var url = "/system/auth/createAuthProc.do";
		var mode = $jquery("#" + Auth.FORM_ID + " #mode").val();
		
		if (mode == "modify") {
			url = "/system/auth/modifyAuthProc.do";
		}
		
		$jquery("#content_body").ajaxload(
			"blockLoad",
			jsContextPath + url,
			"POST",
			"html",
			$jquery("#" + Auth.FORM_ID).separator("separatorRemoveForm").serialize()
		);
	},
	
	/**
	 * 개요 : 메뉴 삭제처리 (단일정보)
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 1.
	 * @param thisObj
	 */
	deleteAuth : function(thisObj) {
		if (confirm("권한을 삭제하면 해당 권한을 부여받은 사용자의 권한은 해제 됩니다.\r\n삭제하시겠습니까?")) {
			$jquery("#content_body").ajaxload(
				"blockLoad",
				jsContextPath + "/system/auth/deleteAuth.do",
				"POST",
				"html",
				$jquery("#"+ Auth.FORM_ID).separator("separatorRemoveForm").serialize()
			);
		}
	},
	
	/**
	 * 개요 : 메뉴ID 중복검사
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8.
	 * @param thisObj
	 */
	duplicateCheck : function(thisObj) {
		Auth.IS_AUTH_DUPLICATE = false;
		
		var paramObj = {
			authorCode : $jquery('#' + Auth.FORM_ID + ' #authorCode').val()
		};
		
		if ($jquery("#" + Auth.FORM_ID).validationEngine("CLFValidateField", $jquery("#authorCode"))) {
			authService.selectAuthor(paramObj, {
				callback : function (returnObj) {
					if (returnObj !== null) {
						$jquery("#authorCode").removeClass("validT");
						$jquery("#authorCode").addClass("validF");
						alert('중복된 권한ID가 존재합니다.');
					} else {
						$jquery("#authorCode").removeClass("validF");
						$jquery("#authorCode").addClass("validT");
						
						$jquery("#authorCode").data("duplecateText", paramObj.authorCode);
						
						alert('등록가능한 권한ID 입니다.');
						Auth.IS_AUTH_DUPLICATE = true;
					}
				},
				errorHandler : function(errorString, exception) {
					alert('오류가 발생되었습니다.\r\n');
				},
				async : false
			});
		}
	},
	
	/**
	 * 개요 : 중복체크 텍스트 변경상태 확인
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 2. 17.
	 * @param thisObj
	 */
	duplecateTextCheck : function(thisObj) {
		if ($jquery(thisObj).val() !== "") {
			if ($jquery(thisObj).val() !== $jquery(thisObj).data("duplecateText")) {
				$jquery(thisObj).removeClass("validT");
				$jquery(thisObj).addClass("validF");
				Auth.IS_PROGRM_DUPLECATE = false;
			} else {
				$jquery(thisObj).removeClass("validF");
				$jquery(thisObj).addClass("validT");
				Auth.IS_PROGRM_DUPLECATE = true;
			}
		}
	},
	
	/**
	 * 개요 : 엑셀 다운로드
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8.
	 * @param thisObj
	 */
	excelDownload : function(thisObj){
		if(Auth.TOTAL_CNT == 0){
			alert("해당 데이터가 없습니다");
		}else if(Auth.TOTAL_CNT > 10000){
			alert("1만건 이상 엑셀받기를 할 수 없습니다");
		}else{
			$jquery.growlUI("info", "엑셀 다운로드를 요청하였습니다.<br />잠시만 기다려주세요.");
			
			$jquery("#" + Auth.FORM_ID).attr("action", "downloadAuthExcel.do");
			$jquery("#" + Auth.FORM_ID).submit();
		}
		
	},
	
	/**
	 * 개요 : 시작페이지 조회권한 설정
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 4. 26.
	 * @param idx
	 */
	searchAuthCheck : function(idx) {
		$jquery("#searchFlagArray" + idx).prop('checked', true);
	},
	
	/**
	 * 개요 : 조회권한 설정 해제 시 시작페이지 해제
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 4. 26.
	 * @param idx
	 */
	authorIndexUrlCheck : function(idx) {
		$jquery("#authorIndexUrl" + idx).prop('checked', false);
	}
};