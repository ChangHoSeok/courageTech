var DeptPopup = {
	MENU_NO : "",
	FORM_ID : "formDeptPopup",
	DETAIL_ID : "retrieveDeptPopup",
	CREATE_ID : "createDeptBatchPopup",
	ACTION_STATUS : "",
	OPENER_KEY : "",
	CALLBACK_FUNCTION : "",
	SELECTED_VO : {},
	TOTAL_CNT : 0,
	atchFile : {
		uploadFlag : false,
		hasError : false
	},
	
	/**
	 * 개요 : 상세조회 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 10. 5.
	 * @param 
	 */
	formInitDetail : function() {
		window.resizeTo(800, 600);
		
		AuthCommon.selectMenuAuthor(DeptPopup.MENU_NO, DeptPopup.FORM_ID);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
		
		$jquery("#progrmNm").html(Navi.getProgrmNm(DeptPopup.DETAIL_ID));
		
		showBtnAccessKey();
		DeptPopup.searchTree();
	},
	
	/**
	 * 개요 : 등록화면 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 10. 9.
	 * @param 
	 */
	formInitCreate : function() {
		window.resizeTo(690, 400);
		
		AuthCommon.selectMenuAuthor(DeptPopup.MENU_NO, DeptPopup.FORM_ID);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
		
		$jquery("#progrmNm").html(Navi.getProgrmNm(DeptPopup.CREATE_ID));
		
		showBtnAccessKey();
		
		if (DeptPopup.ACTION_STATUS == "success") {
			$jquery.growlUI("info", "일괄등록이 완료되었습니다.");
		}
	},
	
	/**
	 * 개요 : 오류목록 조회 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 10. 9.
	 * @param 
	 */
	formInitErrorList : function() {
		window.resizeTo(1000, 600);
		
		AuthCommon.selectMenuAuthor(DeptPopup.MENU_NO, DeptPopup.FORM_ID);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
		
		$jquery("#progrmNm").html(Navi.getProgrmNm(DeptPopup.CREATE_ID));
		
		showBtnAccessKey();
		
		if (DeptPopup.ACTION_STATUS == "failed") {
			$jquery.growlUI("warn", "오류내역을 확인하세요.", 5000);
		}
	},
	
	/**
	 * 개요 : 트리 목록 새로고침
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 10. 7.
	 * @param 
	 */
	refreshTree : function() {
		$jquery("#deptTreeView").empty();
		DeptPopup.searchTree();
	},
	
	/**
	 * 개요 : 트리 데이터 조회
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 10. 5.
	 * @param 
	 */
	searchTree : function () {
		
		var url = jsContextPath + "/uss/org/dept/retrieveDeptTreeData.tech";
		
		$jquery("#deptTreeView").jstree({
			"plugins" : ["themes","json_data","ui","cookies"],
			"json_data" : {
				"ajax" : {
					"type"	: "POST",
					"url"	: url,
					"data"	: function (node) {
						return {
							condAtmbUpperDeptCode : $jquery(node).data('deptCode'),
							condAblSe : $jquery("#condAblSe").prop("checked") ? "1" : ""
						};
					}
				}
			},
			"themes" : {
				"theme" : "courage-blue",
				"dots" : false,
				"icons" : true
			},
			"cookies" : {
				"save_selected" : "deptPopup_selected",
				"save_opened" : "deptPopup_opened"
			}
		}).bind("select_node.jstree", function (event, data) {
			DeptPopup.SELECTED_VO = $jquery(data.rslt.obj).data("VO");
			DeptPopup.setDetailView();
		}).delegate("a", "click", function (event, data) {
			event.preventDefault();
		});
	},
	
	/**
	 * 개요 : opener 유효성 검사
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 10. 5.
	 * @param 
	 */
	_fncCheckOpener : function() {
		if (opener == null || opener.closed) {
			alert("현재 창을 호출한 화면이 종료되었습니다.\n현재 창을 종료합니다.");
			return false;
		} else if (isEmpty($jquery("#popupForm #openerKey", opener.document).val())) {
			alert("현재 창을 호출한 화면이 이동되었습니다.\n현재 창을 종료합니다.");
			return false;
		} else if ($jquery("#popupForm #openerKey", opener.document).val() != DeptPopup.OPENER_KEY) {
			alert("현재 창을 호출한 화면이 변경되었습니다.\n현재 창을 종료합니다.");
			return false;
		}
		
		return true;
	},
	
	/**
	 * 개요 : 부서 상세정보 설정
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 10. 7.
	 * @param 
	 */
	setDetailView : function() {
		$jquery("#deptCode").html(DeptPopup.SELECTED_VO.deptCode);
		$jquery("#allDeptNm").html(DeptPopup.SELECTED_VO.allDeptNm);
		$jquery("#lowestDeptNm").html(DeptPopup.SELECTED_VO.lowestDeptNm);
		$jquery("#odr").html(DeptPopup.SELECTED_VO.odr);
		$jquery("#ord").html(DeptPopup.SELECTED_VO.ord);
		$jquery("#atmbUpperDeptCode").html(DeptPopup.SELECTED_VO.atmbUpperDeptCode);
		$jquery("#bestDeptCode").html(DeptPopup.SELECTED_VO.bestDeptCode);
		$jquery("#psitnDeptOdr").html(DeptPopup.SELECTED_VO.psitnDeptOdr);
		$jquery("#ablSe").html(DeptPopup.SELECTED_VO.ablSe === "0" ? "현존" : "폐지");
	},
	
	/**
	 * 개요 : 부서 선택
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 10. 7.
	 * @param 
	 */
	select : function(thisObj) {
		if (DeptPopup._fncCheckOpener()) {
			if ($jquery.isFunction(opener.setDept)) {
				opener.setDept(DeptPopup.SELECTED_VO);
				DeptPopup.popupClose();
			} else {
				alert("시스템 설정이 잘못되었습니다.\n관리자에게 문의하세요.");
			}
		} else {
			window.close();
		}
	},
	
	/**
	 * 개요 : 저장
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 17.
	 * @param thisObj
	 */
	save : function(thisObj) {
		if (DeptPopup._formValidate()) {
			
			// 첨부파일 업로드 오류 여부 확인
			if ($jquery("#" + DeptPopup.FORM_ID + " .uploadifyError").length > 0) {
				alert("첨부파일 업로드 오류 파일 삭제 후 저장하세요.");
				return false;
			}
			
			if (confirm("저장하시겠습니까?")) {
				DeptPopup.atchFile.hasError = false;
				$jquery("#" + DeptPopup.FORM_ID + " .buttonSet").hide();
				
				// 첨부파일 업로드
				if($jquery("#" + DeptPopup.FORM_ID + " #uploadifyAtchQueue > .uploadifyQueueItem").length > 0) {
					$jquery("#" + DeptPopup.FORM_ID + " #uploadifyAtch").uploadifyUpload();
				} else {
					alert("첨부파일을 선택하세요.");
				}
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
		if ($jquery("#" + DeptPopup.FORM_ID).validationEngine("CLFValidate")) {
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
		var url = "/uss/org/dept/createDeptBatchProcPopup.tech";
		
		$jquery("#content_body").ajaxload(
			"blockLoad",
			jsContextPath + url,
			"POST",
			"html",
			$jquery("#" + DeptPopup.FORM_ID).separator("separatorRemoveForm").serialize(),
			true
		);
	},
	
	/**
	 * 개요 : 엑셀 다운로드
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 10. 15.
	 * @param thisObj
	 */
	excelDownload : function(thisObj) {
		if(DeptPopup.TOTAL_CNT == 0){
			alert("해당 데이터가 없습니다");
		}else if(DeptPopup.TOTAL_CNT > 10000){
			alert("1만건 이상 엑셀받기를 할 수 없습니다");
		}else{
			$jquery.growlUI("info", "엑셀 다운로드를 요청하였습니다.<br />잠시만 기다려주세요.");
			
			$jquery("#" + DeptPopup.FORM_ID).attr("action", "downloadDeptBatchErrorExcel.tech");
			$jquery("#" + DeptPopup.FORM_ID).submit();
		}
	},

	/**
	 * 개요 : 팝업창 닫기
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 10. 5.
	 * @param thisObj
	 */
	popupClose : function(thisObj) {
		if (!isEmpty(DeptPopup.CALLBACK_FUNCTION)) {
			if (DeptPopup._fncCheckOpener()) {
				eval("opener." + DeptPopup.CALLBACK_FUNCTION + "()");
			}
		}
		
		window.close();
	}
};