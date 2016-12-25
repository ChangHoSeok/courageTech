var BoardMaster = {
	MENU_NO : "",
	FORM_ID : "formBoardMaster",
	LIST_ID : "retrieveBoardMasterList",
	DETAIL_ID : "retrieveBoardMasterDetail",
	CREATE_ID : "createBoardMaster",
	MODIFY_ID : "modifyBoardMaster",
	PAGE_INDEX_ID : "pageIndex",
	ACTION_STATUS : "",
	TOTAL_CNT : 0,
	
	/**
	 * 개요 : 목록 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 23.
	 * @param 
	 */
	formInitList : function() {
		$jquery("#menuNavi").html(Navi.getNaviNm(BoardMaster.MENU_NO));
		$jquery("#progrmNm").html(Navi.getProgrmNm(BoardMaster.LIST_ID));
	    
		// 해당 메뉴의 권한정보 조회
		AuthCommon.selectMenuAuthor(BoardMaster.MENU_NO);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
		
		$jquery("#" + BoardMaster.FORM_ID).separator('separatorAddForm');
		showBtnAccessKey();
	},
	
	/**
	 * 개요 : 등록 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 23.
	 * @param 
	 */
	formInitCreate : function() {
		if ($jquery("#" + BoardMaster.FORM_ID + " #mode").val() == "create") {
			$jquery("#progrmNm").html(Navi.getProgrmNm(BoardMaster.CREATE_ID));
		} else {
			$jquery("#progrmNm").html(Navi.getProgrmNm(BoardMaster.MODIFY_ID));
		}
		
		$jquery("#" + BoardMaster.FORM_ID).separator('separatorAddForm');
		$jquery("#" + BoardMaster.FORM_ID + " #bbsIntrcn").byteChk("keyup", 2, 1000);
		
		$jquery("#menuNavi").html(Navi.getNaviNm(BoardMaster.MENU_NO));
		
		showBtnAccessKey();
		
		// Field 설정
		if (isEmpty($jquery("#useAt").val())) {
			$jquery("#useAt option[value=Y]").prop("selected", true);
		}
		
		BoardMaster.fieldSet($jquery("#fileAtchPosblAt"));
		BoardMaster.fieldSet($jquery("#mvpPosblAt"));
	},

	/**
	 * 개요 : 상세 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 23.
	 * @param 
	 */
	formInitDetail : function() {
		$jquery("#menuNavi").html(Navi.getNaviNm(BoardMaster.MENU_NO));
		$jquery("#progrmNm").html(Navi.getProgrmNm(BoardMaster.DETAIL_ID));
		
		// 해당 메뉴의 권한정보 조회
		AuthCommon.selectMenuAuthor(BoardMaster.MENU_NO);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
		
		$jquery("#" + BoardMaster.FORM_ID).separator('separatorAddForm');
		showBtnAccessKey();
	},
	
	fn_egov_link_page : function(pageIndex) {
		BoardMaster.setPageIndex(pageIndex);
		BoardMaster.refreshList();
	},
	
	/**
	 * 개요 : 필드 값에 대한 정보처리
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 5. 25.
	 * @param 
	 */
	fieldSet : function(thisObj) {
		if ($jquery(thisObj).attr("id") == "fileAtchPosblAt") {
			if ($jquery(thisObj).val() == "Y") {
				$jquery("#atchPosblFileNumber").prop("disabled", false).addClass("required_textbox").removeClass("readonly");
				$jquery("#atchPosblFileSize").prop("disabled", false).addClass("required_textbox").removeClass("readonly");
				$jquery(".fileStar").css("display", "");
			} else {
				$jquery("#atchPosblFileNumber").val('').prop("disabled", true).addClass("readonly").removeClassRegEx("required_textbox");
				$jquery("#atchPosblFileSize").val('').prop("disabled", true).addClass("readonly").removeClassRegEx("required_textbox");
				$jquery(".fileStar").css("display", "none");
			}
		} else if ($jquery(thisObj).attr("id") == "mvpPosblAt") {
			if ($jquery(thisObj).val() == "Y") {
				$jquery("#mvpPosblFileNumber").prop("disabled", false).addClass("required_textbox").removeClass("readonly");
				$jquery("#mvpPosblFileSize").prop("disabled", false).addClass("required_textbox").removeClass("readonly");
				$jquery(".mvpStar").css("display", "");
			} else {
				$jquery("#mvpPosblFileNumber").val('').prop("disabled", true).addClass("readonly").removeClassRegEx("required_textbox");
				$jquery("#mvpPosblFileSize").val('').prop("disabled", true).addClass("readonly").removeClassRegEx("required_textbox");
				$jquery(".mvpStar").css("display", "none");
			}
		}
	},
	
	/**
	 * 개요 : 페이지 인덱스 설정
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 23.
	 * @param pageIndex
	 */
	setPageIndex : function(pageIndex) {
		$jquery("#" + BoardMaster.FORM_ID + " #" + BoardMaster.PAGE_INDEX_ID).val(pageIndex);
	},

	/**
	 * 개요 : 목록 새로고침
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 23.
	 * @param thisObj
	 */
	refreshList : function(thisObj) {
		location.hash = "AC=/cop/bbs/retrieveBoardMasterList.tech&VA=content_body&" + $jquery("#" + BoardMaster.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 목록조회
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 23.
	 * @param thisObj
	 */
	search : function(thisObj) {
		BoardMaster.setPageIndex(1);
		BoardMaster.refreshList(thisObj);
	},

	/**
	 * 개요 : 상세조회 페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 23.
	 * @param thisObj
	 */
	detailView : function(thisObj) {
		$jquery("#" + BoardMaster.FORM_ID + " #bbsId").val($jquery(thisObj).prop("id"));
		
		location.hash = "AC=/cop/bbs/retrieveBoardMasterDetail.tech&VA=content_body&" + $jquery("#" + BoardMaster.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 목록조회 페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 23.
	 * @param thisObj
	 */
	listView : function(thisObj) {
		location.hash = "AC=/cop/bbs/retrieveBoardMasterList.tech&VA=content_body&" + $jquery("#" + BoardMaster.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 등록 페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 23.
	 * @param thisObj
	 */
	createView : function(thisObj) {
		$jquery("#" + BoardMaster.FORM_ID + " #emplyrId").val('');
		
		location.hash = "AC=/cop/bbs/createBoardMaster.tech&VA=content_body&" + $jquery("#" + BoardMaster.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 수정페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 23.
	 * @param thisObj
	 */
	modifyView : function(thisObj) {
		location.hash = "AC=/cop/bbs/modifyBoardMaster.tech&VA=content_body&" + $jquery("#" + BoardMaster.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 수정취소
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 23.
	 * @param thisObj
	 */
	modifyCancel : function(thisObj) {
		location.hash = "AC=/cop/bbs/retrieveBoardMasterDetail.tech&VA=content_body&" + $jquery("#" + BoardMaster.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 저장
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 23.
	 * @param thisObj
	 */
	save : function(thisObj) {
		if (BoardMaster._formValidate()) {
			if (confirm("저장하시겠습니까?")) {
				BoardMaster.saveProc();
			}
		}
	},
	
	/**
	 * 개요 : 입력항목 유효성 검사
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 23.
	 * @param thisObj
	 */
	_formValidate : function() {
		$jquery("#atchPosblFileNumber").removeClassRegEx("validate");
		$jquery("#atchPosblFileSize").removeClassRegEx("validate");
		
		$jquery("#mvpPosblFileNumber").removeClassRegEx("validate");
		$jquery("#mvpPosblFileSize").removeClassRegEx("validate");
		
		// 파일첨부 추가 속성
		if ($jquery("#fileAtchPosblAt").val() == "Y") {
			$jquery("#atchPosblFileNumber").addClass("validate[required,onlyNumber,min[1],max[5]]");
			$jquery("#atchPosblFileSize").addClass("validate[required,onlyNumber,min[1],max[500]]");
		} else {
			$jquery("#atchPosblFileNumber").addClass("validate[onlyNumber,min[1],max[5]]");
			$jquery("#atchPosblFileSize").addClass("validate[onlyNumber,min[1],max[500]]");
		}
		
		// 동영상 첨부 추가 속성
		if ($jquery("#mvpPosblAt").val() == "Y") {
			$jquery("#mvpPosblFileNumber").addClass("validate[required,onlyNumber,min[1],max[5]]");
			$jquery("#mvpPosblFileSize").addClass("validate[required,onlyNumber,min[1],max[500]]");
		} else {
			$jquery("#mvpPosblFileNumber").addClass("validate[onlyNumber,min[1],max[5]]");
			$jquery("#mvpPosblFileSize").addClass("validate[onlyNumber,min[1],max[500]]");
		}
		
		if ($jquery("#" + BoardMaster.FORM_ID).validationEngine("CLFValidate")) {
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
	 * @Date : 2016. 5. 23.
	 * @param thisObj
	 */
	saveProc : function() {
		var url = "/cop/bbs/createBoardMasterProc.tech";
		var mode = $jquery("#" + BoardMaster.FORM_ID + " #mode").val();
		
		if (mode == "modify") {
			url = "/cop/bbs/modifyBoardMasterProc.tech";
		}
		
		$jquery("#content_body").ajaxload(
			"blockLoad",
			jsContextPath + url,
			"POST",
			"html",
			$jquery("#" + BoardMaster.FORM_ID).separator("separatorRemoveForm").serialize(),
			false
		);
	},
	
	/**
	 * 개요 : 프로그램 삭제처리 (단일정보)
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 23.
	 * @param thisObj
	 */
	deleteBoardMaster : function(thisObj) {
		if (confirm("삭제하시겠습니까?")) {
			location.hash = "AC=/cop/bbs/deleteBoardMaster.tech&VA=content_body&" + $jquery("#" + BoardMaster.FORM_ID).separator("separatorRemoveForm").serialize();
		}
	},
	
	/**
	 * 개요 : 엑셀 다운로드
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 23.
	 * @param thisObj
	 */
	excelDownload : function(thisObj){
		if(BoardMaster.TOTAL_CNT == 0){
			alert("해당 데이터가 없습니다");
		}else if(BoardMaster.TOTAL_CNT > 10000){
			alert("1만건 이상 엑셀받기를 할 수 없습니다");
		}else{
			$jquery.growlUI("info", "엑셀 다운로드를 요청하였습니다.<br />잠시만 기다려주세요.");
			
			$jquery("#" + BoardMaster.FORM_ID).attr("action", "downloadBoardMasterExcel.tech");
			$jquery("#" + BoardMaster.FORM_ID).submit();
		}
	}
};