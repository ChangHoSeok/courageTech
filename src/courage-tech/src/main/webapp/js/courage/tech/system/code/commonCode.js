var CommonCode = {
	MENU_NO : "",
	FORM_ID : "formCommonCode",
	LIST_ID : "retrieveCmmnCodeList",
	DETAIL_ID : "retrieveCmmnCodeDetail",
	CREATE_ID : "createCmmnCode",
	MODIFY_ID : "modifyCmmnCode",
	PAGE_INDEX_ID : "pageIndex",
	IS_CODE_DUPLECATE : false,
	TOTAL_CNT : 0,
	
	/**
	 * 개요 : ListView 초기화
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 1. 24.
	 * @param 
	 */
	formInitList : function() {
		$jquery("#menuNavi").html(Navi.getNaviNm(CommonCode.MENU_NO));
		$jquery("#progrmNm").html(Navi.getProgrmNm(CommonCode.LIST_ID));
		
		// 해당 메뉴의 권한정보 조회
		AuthCommon.selectMenuAuthor(CommonCode.MENU_NO);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
		
		$jquery("#" + CommonCode.FORM_ID).separator('separatorAddForm');
		showBtnAccessKey();
	},
	
	/**
	 * 개요 : createView 초기화
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 1. 24.
	 * @param 
	 */
	formInitCreate : function() {
		$jquery("#codeId").addClass("validF");
		
		if ($jquery("#" + CommonCode.FORM_ID + " #mode").val() == "create") {
			$jquery("#progrmNm").html(Navi.getProgrmNm(CommonCode.CREATE_ID));
		} else {
			$jquery("#progrmNm").html(Navi.getProgrmNm(CommonCode.MODIFY_ID));
		}
		
		CommonCode.IS_CODE_DUPLECATE = false;
		$jquery("#" + CommonCode.FORM_ID).separator('separatorAddForm');
		
		$jquery("#menuNavi").html(Navi.getNaviNm(CommonCode.MENU_NO));
		
		showBtnAccessKey();
	},
	
	/**
	 * 개요 : detailView 초기화
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 1. 24.
	 * @param 
	 */
	formInitDetail : function() {
		$jquery('#commonDetailCodeList').ajaxload(
			'blockLoad',
			jsContextPath + '/system/code/retrieveCmmnDetailCodeList.tech',
			"POST",
			"html",
			'condCodeId=' + $jquery('#codeId').val()
		);
		
		$jquery("#menuNavi").html(Navi.getNaviNm(CommonCode.MENU_NO));
		$jquery("#progrmNm").html(Navi.getProgrmNm(CommonCode.DETAIL_ID));
		
		// 해당 메뉴의 권한정보 조회
		AuthCommon.selectMenuAuthor(CommonCode.MENU_NO);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
		
		showBtnAccessKey();
	},
	
	fn_egov_link_page : function(pageIndex) {
		CommonCode.setPageIndex(pageIndex);
		CommonCode.refreshList();
	},
	
	/**
	 * 개요 : 페이지 인덱스 설정
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 06
	 * @param pageIndex
	 */
	setPageIndex : function(pageIndex) {
		$jquery("#" + CommonCode.FORM_ID + " #" + CommonCode.PAGE_INDEX_ID).val(pageIndex);
	},

	/**
	 * 개요 : 목록 새로고침
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 06
	 * @param thisObj
	 */
	refreshList : function(thisObj) {
		$jquery("#content_body").ajaxload(
			"blockLoad",
			jsContextPath + "/system/code/retrieveCmmnCodeList.tech",
			"POST",
			"html",
			$jquery("#" + CommonCode.FORM_ID).separator("separatorRemoveForm").serialize()
		);
	},

	/**
	 * 개요 : 페이지 인덱스 설정
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 8.
	 * @param thisObj
	 */
	search : function(thisObj) {
		this.setPageIndex(1);
		this.refreshList(thisObj);
	},

	/**
	 * 개요 : 공통코드 상세조회
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 8.
	 * @param thisObj
	 */
	detailView : function(thisObj) {
		$jquery("#codeId").val($jquery(thisObj).prop("id"));
		
		location.hash = "AC=/system/code/retrieveCmmnCodeDetail.tech&VA=content_body&" + $jquery("#" + CommonCode.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 공통코드 목록조회
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 8.
	 * @param thisObj
	 */
	listView : function(thisObj) {
		location.hash = "AC=/system/code/retrieveCmmnCodeList.tech&VA=content_body&" + $jquery("#" + CommonCode.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 공통코드 등록정보 조회
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 11.
	 * @param thisObj
	 */
	createView : function(thisObj) {
		$jquery("#" + CommonCode.FORM_ID + " > #codeId").val("");
		
		location.hash = "AC=/system/code/createCmmnCode.tech&VA=content_body&" + $jquery("#" + CommonCode.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 공통코드 수정정보 조회
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 12.
	 * @param thisObj
	 */
	modifyView : function(thisObj) {
		location.hash = "AC=/system/code/modifyCmmnCode.tech&VA=content_body&" + $jquery("#" + CommonCode.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 수정취소
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 8.
	 * @param thisObj
	 */
	modifyCancel : function(thisObj) {
		location.hash = "AC=/system/code/retrieveCmmnCodeDetail.tech&VA=content_body&" + $jquery("#" + CommonCode.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 공통코드 저장 이벤트 처리
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 12.
	 * @param thisObj
	 */
	save : function(thisObj) {
		if (CommonCode._formValidate()) {
			if (confirm("저장하시겠습니까?")) {
				CommonCode.saveProc();
			}
		}
	},

	/**
	 * 개요 : 공통코드 항목 유효성 검사
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 12.
	 * @param thisObj
	 */
	_formValidate : function() {
		if ($jquery("#" + CommonCode.FORM_ID).validationEngine("CLFValidate")) {
			if ($jquery("#" + CommonCode.FORM_ID + " #mode").val() == "create" && !CommonCode.IS_CODE_DUPLECATE) {
				alert("코드 중복검사를 해야 합니다.");
				return false;
			}
			return true;
		} else {
			$jquery.growlUI("warn", "입력값을 확인하세요");
			return false;
		}
	},

	/**
	 * 개요 : 공통코드 저장
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 12.
	 * @param thisObj
	 */
	saveProc : function() {
		var url;

		if ($jquery("#" + CommonCode.FORM_ID + " #mode").val() == "create") {
			url = "/system/code/createCmmnCodeProc.tech";
		} else {
			url = "/system/code/modifyCmmnCodeProc.tech";
		}
		
		$jquery("#content_body").ajaxload(
			"blockLoad",
			jsContextPath + url,
			"POST",
			"html",
			$jquery("#" + CommonCode.FORM_ID).separator("separatorRemoveForm").serialize()
		);
	},
	
	/**
	 * 개요 : 코드ID 중복 검사
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 16.
	 * @param thisObj
	 */
	codeIdDuplicateCheck : function(thisObj) {
		CommonCode.IS_CODE_DUPLECATE = false;
		
		if ($jquery("#" + CommonCode.FORM_ID).validationEngine("CLFValidateField", $jquery("#codeId"))) {
			var codeId = $jquery("#" + CommonCode.FORM_ID + " #codeId").val();
			
			var paramObj = {
				codeId : codeId
			};
			
			cmmnCodeManageService.selectCmmnCodeDetail(paramObj, {
				callback : function (returnObj) {
					if (returnObj !== null) {
						$jquery("#codeId").removeClass("validT");
						$jquery("#codeId").addClass("validF");
						
						alert("중복된 코드ID가 존재합니다.");
					} else {
						$jquery("#codeId").removeClass("validF");
						$jquery("#codeId").addClass("validT");
						
						$jquery("#codeId").data("duplecateText", paramObj.codeId);
						
						alert("등록가능한 코드ID 입니다.");
						CommonCode.IS_CODE_DUPLECATE = true;
					}
				},
				errorHandler : function(errorString, exception) {
					alert("오류가 발생되었습니다.\r\n");
				},
				async : false
			});
		}
	},
	
	/**
	 * 개요 : 중복체크 텍스트 변경상태 확인
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 2. 5.
	 * @param 
	 */
	duplecateTextCheck : function(thisObj) {
		if ($jquery(thisObj).val() !== "") {
			if ($jquery(thisObj).val() !== $jquery(thisObj).data("duplecateText")) {
				$jquery(thisObj).removeClass("validT");
				$jquery(thisObj).addClass("validF");
				CommonCode.IS_PROGRM_DUPLECATE = false;
			} else {
				$jquery(thisObj).removeClass("validF");
				$jquery(thisObj).addClass("validT");
				CommonCode.IS_PROGRM_DUPLECATE = true;
			}
		}
	},
	
	/**
	 * 개요 : 공통코드 캐시 새로고침
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 11. 20.
	 * @param thisObj
	 */	
	cacheReload : function(thisObj) {
		if (confirm("공통코드 캐시를 재설정 하시겠습니까?")) {
			location.href = jsContextPath + "/courageCommonCodeInitServlet.com";
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
		if (CommonCode.TOTAL_CNT == 0) {
			alert("해당 데이터가 없습니다");
		} else if (CommonCode.TOTAL_CNT > 10000) {
			alert("1만건 이상 엑셀받기를 할 수 없습니다");
		} else {
			$jquery.growlUI("info", "엑셀 다운로드를 요청하였습니다.<br />잠시만 기다려주세요.");
			
			$jquery("#" + CommonCode.FORM_ID).attr("action", jsContextPath + "/system/code/downloadCommonCodeExcel.tech");
			$jquery("#" + CommonCode.FORM_ID).submit();
		}
		
	}
};
