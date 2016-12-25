var Progrm = {
	MENU_NO : "",
	FORM_ID : "formProgrm",
	LIST_ID : "retrieveProgrmList",
	DETAIL_ID : "retrieveProgrmDetail",
	CREATE_ID : "createProgrm",
	MODIFY_ID : "modifyProgrm",
	PAGE_INDEX_ID : "pageIndex",
	ACTION_STATUS : "",
	IS_PROGRM_DUPLECATE : false,
	TOTAL_CNT : 0,
	
	/**
	 * 개요 : 목록 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 26.
	 * @param 
	 */
	formInitList : function() {
	    $jquery("#menuNavi").html(Navi.getNaviNm(Progrm.MENU_NO));
		$jquery("#progrmNm").html(Navi.getProgrmNm(Progrm.LIST_ID));
	    
		// 해당 메뉴의 권한정보 조회
		AuthCommon.selectMenuAuthor(Progrm.MENU_NO);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
		
		$jquery("#" + Progrm.FORM_ID).separator('separatorAddForm');
		showBtnAccessKey();
	},
	
	/**
	 * 개요 : 등록 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 26.
	 * @param 
	 */
	formInitCreate : function() {
		$jquery("#progrmFileNm").addClass("validF");
		
		if ($jquery("#" + Progrm.FORM_ID + " #mode").val() == "create") {
			$jquery("#progrmNm").html(Navi.getProgrmNm(Progrm.CREATE_ID));
		} else {
			$jquery("#progrmNm").html(Navi.getProgrmNm(Progrm.MODIFY_ID));
		}
		
		$jquery("#" + Progrm.FORM_ID).separator('separatorAddForm');
		
		$jquery("#menuNavi").html(Navi.getNaviNm(Progrm.MENU_NO));
		showBtnAccessKey();
		
		Progrm.fieldSet($jquery("#requestLmttAt"));
	},

	/**
	 * 개요 : 상세 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 26.
	 * @param 
	 */
	formInitDetail : function() {
		$jquery("#menuNavi").html(Navi.getNaviNm(Progrm.MENU_NO));
		$jquery("#progrmNm").html(Navi.getProgrmNm(Progrm.DETAIL_ID));
		
		// 해당 메뉴의 권한정보 조회
		AuthCommon.selectMenuAuthor(Progrm.MENU_NO);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
		
		$jquery("#" + Progrm.FORM_ID).separator('separatorAddForm');
		showBtnAccessKey();
	},
	
	fn_egov_link_page : function(pageIndex) {
		Progrm.setPageIndex(pageIndex);
		Progrm.refreshList();
	},
	
	fieldSet : function(thisObj) {
		if ($jquery(thisObj).attr("id") == "requestLmttAt") {
			if ($jquery(thisObj).val() == "Y") {
				$jquery("input[name=requestMethodList]").prop("disabled", false);
				$jquery(".requestMethodStar").css("display", "");
			} else {
				$jquery("input[name=requestMethodList]").prop("disabled", true);
				$jquery(".requestMethodStar").css("display", "none");
			}
		}
	},
	
	/**
	 * 개요 : 페이지 인덱스 설정
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8
	 * @param pageIndex
	 */
	setPageIndex : function(pageIndex) {
		$jquery("#" + Progrm.FORM_ID + " #" + Progrm.PAGE_INDEX_ID).val(pageIndex);
	},

	/**
	 * 개요 : 목록 새로고침
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8
	 * @param thisObj
	 */
	refreshList : function(thisObj) {
		location.hash = "AC=/system/progrm/retrieveProgrmList.tech&VA=content_body&" + $jquery("#" + Progrm.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 목록조회
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8
	 * @param thisObj
	 */
	search : function(thisObj) {
		Progrm.setPageIndex(1);
		Progrm.refreshList(thisObj);
	},

	/**
	 * 개요 : 상세조회 페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8
	 * @param thisObj
	 */
	detailView : function(thisObj) {
		$jquery("#progrmFileNm").val($jquery(thisObj).prop("id"));
		
		location.hash = "AC=/system/progrm/retrieveProgrmDetail.tech&VA=content_body&" + $jquery("#" + Progrm.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 목록조회 페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8
	 * @param thisObj
	 */
	listView : function(thisObj) {
		location.hash = "AC=/system/progrm/retrieveProgrmList.tech&VA=content_body&" + $jquery("#" + Progrm.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 등록 페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8.
	 * @param thisObj
	 */
	createView : function(thisObj) {
		$jquery("#" + Progrm.FORM_ID + " #progrmFileNm").val('');
		
		location.hash = "AC=/system/progrm/createProgrm.tech&VA=content_body&" + $jquery("#" + Progrm.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 수정페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8.
	 * @param thisObj
	 */
	modifyView : function(thisObj) {
		location.hash = "AC=/system/progrm/modifyProgrm.tech&VA=content_body&" + $jquery("#" + Progrm.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 수정취소
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8.
	 * @param thisObj
	 */
	modifyCancel : function(thisObj) {
		location.hash = "AC=/system/progrm/retrieveProgrmDetail.tech&VA=content_body&" + $jquery("#" + Progrm.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 저장
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8.
	 * @param thisObj
	 */
	save : function(thisObj) {
		if (Progrm._formValidate()) {
			if (confirm("저장하시겠습니까?")) {
				$jquery("#" + Progrm.FORM_ID + " #saveLaterView").val("detail");
				Progrm.saveProc();
			}
		}
	},
	
	/**
	 * 개요 : 저장 후 계속
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 2. 5.
	 * @param thisObj
	 */
	saveLaterCreate : function(thisObj) {
		if (Progrm._formValidate()) {
			if (confirm("저장하시겠습니까?")) {
				$jquery("#" + Progrm.FORM_ID + " #saveLaterView").val("create");
				Progrm.saveProc();
				
				if (Progrm.ACTION_STATUS == "success") {
					$jquery.growlUI("info", "저장되었습니다.");
				}
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
		// Request Method 유효성 검사
		$jquery("input[name=requestMethodList]").removeClassRegEx("validate");
		
		if ($jquery("#requestLmttAt").val() == "Y") {
			$jquery("input[name=requestMethodList]").addClass("validate[minCheckbox[1]]");
		} else {
			// 필수선택이 아닐 시 초기화를 위해 더미 체크
			$jquery("input[name=requestMethodList]").addClass("validate[minSize[1]]");
		}
		
		if ($jquery("#" + Progrm.FORM_ID).validationEngine("CLFValidate")) {
			if ($jquery('#' + Progrm.FORM_ID + ' #mode').val() == "create" && !Progrm.IS_PROGRM_DUPLECATE) {
				alert("프로그램 파일명 중복검사를 해야 합니다.");
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
		var url = "/system/progrm/createProgrmProc.tech";
		var mode = $jquery("#" + Progrm.FORM_ID + " #mode").val();
		
		if (mode == "modify") {
			url = "/system/progrm/modifyProgrmProc.tech";
		}
		
		$jquery("#content_body").ajaxload(
			"blockLoad",
			jsContextPath + url,
			"POST",
			"html",
			$jquery("#" + Progrm.FORM_ID).separator("separatorRemoveForm").serialize(),
			false
		);
	},
	
	/**
	 * 개요 : 프로그램 삭제처리 (다중)
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 1.
	 * @param thisObj
	 */
	deleteProgrmList : function(thisObj) {
		if ($jquery("input[name=deletelProgrmFileNmList]:checked:checked").length > 0) {
			if (confirm("선택한 프로그램을 삭제하시겠습니까?")) {
				$jquery('#content_body').ajaxload(
					'blockLoad',
					jsContextPath + '/system/progrm/deleteProgrmList.tech',
					"POST",
					"html",
					$jquery("#"+ Progrm.FORM_ID).separator('separatorRemoveForm').serialize()
				);
			}
		} else {
			alert("삭제할 프로그램을 선택하세요");
		}
	},
	
	/**
	 * 개요 : 프로그램 삭제처리 (단일정보)
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 1.
	 * @param thisObj
	 */
	deleteProgrm : function(thisObj) {
		if (confirm("삭제하시겠습니까?")) {
			location.hash = "AC=/system/progrm/deleteProgrm.tech&VA=content_body&" + $jquery("#" + Progrm.FORM_ID).separator("separatorRemoveForm").serialize();
		}
	},
	
	/**
	 * 개요 : 프로그램명칭 중복검사
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8.
	 * @param thisObj
	 */
	duplicateCheck : function(thisObj) {
		Progrm.IS_PROGRM_DUPLECATE = false;
		
		var paramObj = {
			progrmFileNm : $jquery('#' + Progrm.FORM_ID + ' #progrmFileNm').val()
		};
		
		if ($jquery("#" + Progrm.FORM_ID).validationEngine("CLFValidateField", $jquery("#progrmFileNm"))) {
			progrmService.selectProgrmNm(paramObj, {
				callback : function (returnObj) {
					if (returnObj !== null) {
						$jquery("#progrmFileNm").removeClass("validT");
						$jquery("#progrmFileNm").addClass("validF");
						alert('중복된 프로그램 파일명이 존재합니다.');
					} else {
						$jquery("#progrmFileNm").removeClass("validF");
						$jquery("#progrmFileNm").addClass("validT");
						
						$jquery("#progrmFileNm").data("duplecateText", paramObj.progrmFileNm);
						
						alert('등록가능한 프로그램 파일명 입니다.');
						Progrm.IS_PROGRM_DUPLECATE = true;
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
	 * @Date : 2016. 2. 5.
	 * @param 
	 */
	duplecateTextCheck : function(thisObj) {
		if ($jquery(thisObj).val() !== "") {
			if ($jquery(thisObj).val() !== $jquery(thisObj).data("duplecateText")) {
				$jquery(thisObj).removeClass("validT");
				$jquery(thisObj).addClass("validF");
				Progrm.IS_PROGRM_DUPLECATE = false;
			} else {
				$jquery(thisObj).removeClass("validF");
				$jquery(thisObj).addClass("validT");
				Progrm.IS_PROGRM_DUPLECATE = true;
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
		if(Progrm.TOTAL_CNT == 0){
			alert("해당 데이터가 없습니다");
		}else if(Progrm.TOTAL_CNT > 10000){
			alert("1만건 이상 엑셀받기를 할 수 없습니다");
		}else{
			$jquery.growlUI("info", "엑셀 다운로드를 요청하였습니다.<br />잠시만 기다려주세요.");
			
			$jquery("#" + Progrm.FORM_ID).attr("action", "downloadProgrmExcel.tech");
			$jquery("#" + Progrm.FORM_ID).submit();
		}
	}
};