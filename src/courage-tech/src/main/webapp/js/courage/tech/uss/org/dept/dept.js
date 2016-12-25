var Dept = {
	MENU_NO : "",
	FORM_ID : "formDept",
	LIST_ID : "retrieveDeptList",
	DETAIL_ID : "retrieveDeptDetail",
	CREATE_ID : "createDept",
	MODIFY_ID : "modifyDept",
	PAGE_INDEX_ID : "pageIndex",
	ACTION_STATUS : "",
	IS_DEPT_CODE_DUPLECATE : false,
	IS_MODIFY : false,
	SELECTED_VO : {},
	TOTAL_CNT : 0,
	
	/**
	 * 개요 : 탭 화면 초기화
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 11. 24.
	 * @param 
	 */
	formInitTabView : function() {
		$jquery("#tabViewList").ajaxload(
			"blockLoad",
			jsContextPath + "/uss/org/dept/retrieveDeptList.tech",
			"POST",
			"html"
		);
		
		$jquery("#tabViewTree").ajaxload(
			"genLoad",
			jsContextPath + "/uss/org/dept/retrieveDeptTree.tech",
			"POST",
			"html"
		);
		
		$jquery("#" + $jquery("#deptTab input[type=radio]:checked").val()).show();
		$jquery("#" + $jquery("#deptTab input[type=radio]:not(:checked)").val()).hide();
	},
	
	/**
	 * 개요 : 목록 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 9. 29.
	 * @param 
	 */
	formInitList : function() {
		$jquery("#menuNavi").html(Navi.getNaviNm(Dept.MENU_NO));
		$jquery("#progrmNm").html(Navi.getProgrmNm(Dept.LIST_ID));
	    
		// 해당 메뉴의 권한정보 조회
		AuthCommon.selectMenuAuthor(Dept.MENU_NO);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
		
		$jquery("#" + Dept.FORM_ID).separator('separatorAddForm');
		showBtnAccessKey();
	},
	
	/**
	 * 개요 : 트리 뷰 초기화
	 * 
	 * @Author : schkk
	 * @Date : 2016. 11. 24.
	 * @param 
	 */
	formInitTree : function() {
		Dept.searchTree();
	},
	
	/**
	 * 개요 : 트리 목록 새로고침
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 11. 24.
	 * @param 
	 */
	refreshTree : function() {
		$jquery("#tabViewTree #deptTreeView").empty();
		Dept.searchTree();
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
		
		$jquery("#tabViewTree #deptTreeView").jstree({
			"plugins" : ["themes","json_data","ui","cookies"],
			"json_data" : {
				"ajax" : {
					"type"	: "POST",
					"url"	: url,
					"data"	: function (node) {
						return {
							condAtmbUpperDeptCode : $jquery(node).data('deptCode'),
							condAblSe : $jquery("#tabViewTree #condAblSe").prop("checked") ? "1" : ""
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
				"save_selected" : "dept_selected",
				"save_opened" : "dept_opened"
			}
		}).bind("select_node.jstree", function (event, data) {
			Dept.SELECTED_VO = $jquery(data.rslt.obj).data("VO");
			Dept.setDetailView();
		}).delegate("a", "click", function (event, data) {
			event.preventDefault();
		});
	},
	
	/**
	 * 개요 : 부서 상세정보 설정
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 10. 7.
	 * @param 
	 */
	setDetailView : function() {
		$jquery("#deptDetailView #deptCode").html(Dept.SELECTED_VO.deptCode);
		$jquery("#deptDetailView #allDeptNm").html(Dept.SELECTED_VO.allDeptNm);
		$jquery("#deptDetailView #lowestDeptNm").html(Dept.SELECTED_VO.lowestDeptNm);
		$jquery("#deptDetailView #odr").html(Dept.SELECTED_VO.odr);
		$jquery("#deptDetailView #ord").html(Dept.SELECTED_VO.ord);
		$jquery("#deptDetailView #atmbUpperDeptCode").html(Dept.SELECTED_VO.atmbUpperDeptCode);
		$jquery("#deptDetailView #bestDeptCode").html(Dept.SELECTED_VO.bestDeptCode);
		$jquery("#deptDetailView #psitnDeptOdr").html(Dept.SELECTED_VO.psitnDeptOdr);
		$jquery("#deptDetailView #ablSe").html(Dept.SELECTED_VO.ablSe === "0" ? "현존" : "폐지");
	},
	
	/**
	 * 개요 : 등록 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 9. 29.
	 * @param 
	 */
	formInitCreate : function() {
		$jquery("#tabViewList #deptCode").addClass("validF");
		
		if ($jquery("#" + Dept.FORM_ID + " #mode").val() == "create") {
			$jquery("#progrmNm").html(Navi.getProgrmNm(Dept.CREATE_ID));
		} else {
			$jquery("#progrmNm").html(Navi.getProgrmNm(Dept.MODIFY_ID));
		}
		
		$jquery("#" + Dept.FORM_ID).separator('separatorAddForm');
		
		$jquery("#menuNavi").html(Navi.getNaviNm(Dept.MENU_NO));
		showBtnAccessKey();
	},

	/**
	 * 개요 : 상세 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 9. 29.
	 * @param 
	 */
	formInitDetail : function() {
		$jquery("#menuNavi").html(Navi.getNaviNm(Dept.MENU_NO));
		$jquery("#progrmNm").html(Navi.getProgrmNm(Dept.DETAIL_ID));
		
		// 해당 메뉴의 권한정보 조회
		AuthCommon.selectMenuAuthor(Dept.MENU_NO);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
		
		$jquery("#" + Dept.FORM_ID).separator('separatorAddForm');
		showBtnAccessKey();
	},
	
	/**
	 * 개요 : 
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 11. 24.
	 * @param 
	 */
	tabChange : function(thisObj) {
		if ($jquery(thisObj).prop("id") === "viewList") {
			$jquery("#tabViewTree").hide();
			$jquery("#tabViewList").fadeIn(500);
		} else if ($jquery(thisObj).prop("id") === "viewTree") {
			if (Dept.IS_MODIFY) {
				Dept.refreshTree();
				Dept.IS_MODIFY = false;
			}
			
			$jquery("#tabViewList").hide();
			$jquery("#tabViewTree").fadeIn(500);
		}
	},
	
	fn_egov_link_page : function(pageIndex) {
		Dept.setPageIndex(pageIndex);
		Dept.refreshList();
	},
	
	/**
	 * 개요 : 페이지 인덱스 설정
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 9. 29
	 * @param pageIndex
	 */
	setPageIndex : function(pageIndex) {
		$jquery("#" + Dept.FORM_ID + " #" + Dept.PAGE_INDEX_ID).val(pageIndex);
	},

	/**
	 * 개요 : 목록 새로고침
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 9. 29
	 * @param thisObj
	 */
	refreshList : function(thisObj) {
		location.hash = "AC=/uss/org/dept/retrieveDeptList.tech&VA=tabViewList&" + $jquery("#" + Dept.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 목록조회
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 9. 29
	 * @param thisObj
	 */
	search : function(thisObj) {
		Dept.setPageIndex(1);
		Dept.refreshList(thisObj);
	},

	/**
	 * 개요 : 상세조회 페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 9. 29
	 * @param thisObj
	 */
	detailView : function(thisObj) {
		$jquery("#deptCode").val($jquery(thisObj).prop("id"));
		
		location.hash = "AC=/uss/org/dept/retrieveDeptDetail.tech&VA=tabViewList&" + $jquery("#" + Dept.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 목록조회 페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 9. 29
	 * @param thisObj
	 */
	listView : function(thisObj) {
		location.hash = "AC=/uss/org/dept/retrieveDeptList.tech&VA=tabViewList&" + $jquery("#" + Dept.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 등록 페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 9. 29.
	 * @param thisObj
	 */
	createView : function(thisObj) {
		$jquery("#" + Dept.FORM_ID + " #deptCode").val('');
		
		location.hash = "AC=/uss/org/dept/createDept.tech&VA=tabViewList&" + $jquery("#" + Dept.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 수정페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 9. 29.
	 * @param thisObj
	 */
	modifyView : function(thisObj) {
		location.hash = "AC=/uss/org/dept/modifyDept.tech&VA=tabViewList&" + $jquery("#" + Dept.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 수정취소
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 9. 29.
	 * @param thisObj
	 */
	modifyCancel : function(thisObj) {
		location.hash = "AC=/uss/org/dept/retrieveDeptDetail.tech&VA=tabViewList&" + $jquery("#" + Dept.FORM_ID).separator("separatorRemoveForm").serialize();
	},
	
	/**
	 * 개요 : 부서선택 팝업
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 10. 5.
	 * @param 
	 */
	deptPopupView : function(thisObj) {
		var pUrl = jsContextPath + "/uss/org/dept/formDeptPopup.tech";
		var pName = "deptPopup";
		var pWidth = 500;
		var pHeight = 500;
		var openerKey = Dept.FORM_ID;
		var returnFunction = ""; //callback function
		
		var PopupWindow = PopUpWindowOpenOnlyScroll(pName, pWidth, pHeight);
		
		addFormParameter("popupForm", "mode", "treeView");
		
		$jquery('#popupForm input[name="openerKey"]').val(openerKey);
		$jquery('#popupForm input[name="returnFunction"]').val(returnFunction);
		$jquery('#popupForm').attr('target', pName);
		$jquery('#popupForm').attr('action', pUrl).submit();
		
		removeFormParameter("popupForm", "mode");
		
		PopupWindow.focus();
	},
	
	/**
	 * 개요 : 부서 등록 팝업
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 10. 5.
	 * @param 
	 */
	batchCreateView : function(thisObj) {
		var pUrl = jsContextPath + "/uss/org/dept/formDeptPopup.tech";
		var pName = "deptPopup";
		var pWidth = 500;
		var pHeight = 500;
		var openerKey = Dept.FORM_ID;
		var returnFunction = "Dept.refreshList"; //callback function
		
		var PopupWindow = PopUpWindowOpenOnlyScroll(pName, pWidth, pHeight);
		
		addFormParameter("popupForm", "mode", "createBatchView");
		
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
	 * @Date : 2016. 9. 29.
	 * @param thisObj
	 */
	save : function(thisObj) {
		if (Dept._formValidate()) {
			if (confirm("저장하시겠습니까?")) {
				$jquery("#" + Dept.FORM_ID + " #saveLaterView").val("detail");
				Dept.saveProc();
			}
		}
	},
	
	/**
	 * 개요 : 저장 후 계속
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 9. 29.
	 * @param thisObj
	 */
	saveLaterCreate : function(thisObj) {
		if (Dept._formValidate()) {
			if (confirm("저장하시겠습니까?")) {
				$jquery("#" + Dept.FORM_ID + " #saveLaterView").val("create");
				Dept.saveProc();
				
				if (Dept.ACTION_STATUS == "success") {
					$jquery.growlUI("info", "저장되었습니다.");
				}
			}
		}
	},

	/**
	 * 개요 : 입력항목 유효성 검사
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 9. 29.
	 * @param thisObj
	 */
	_formValidate : function() {
		if ($jquery("#" + Dept.FORM_ID).validationEngine("CLFValidate")) {
			if ($jquery('#' + Dept.FORM_ID + ' #mode').val() == "create" && !Dept.IS_DEPT_CODE_DUPLECATE) {
				alert("부서코드 중복검사를 해야 합니다.");
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
	 * @Date : 2016. 9. 29.
	 * @param thisObj
	 */
	saveProc : function() {
		var url = "/uss/org/dept/createDeptProc.tech";
		var mode = $jquery("#" + Dept.FORM_ID + " #mode").val();
		
		if (mode == "modify") {
			url = "/uss/org/dept/modifyDeptProc.tech";
		}
		
		$jquery("#tabViewList").ajaxload(
			"blockLoad",
			jsContextPath + url,
			"POST",
			"html",
			$jquery("#" + Dept.FORM_ID).separator("separatorRemoveForm").serialize(),
			false
		);
		
		Dept.IS_MODIFY = true;
	},
	
	/**
	 * 개요 : 프로그램 삭제처리 (단일정보)
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 9. 29.
	 * @param thisObj
	 */
	deleteDept : function(thisObj) {
		if (confirm("삭제하시겠습니까?")) {
			location.hash = "AC=/uss/org/dept/deleteDept.tech&VA=tabViewList&" + $jquery("#" + Dept.FORM_ID).separator("separatorRemoveForm").serialize();
			Dept.IS_MODIFY = true;
		}
	},
	
	/**
	 * 개요 : 프로그램명칭 중복검사
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 9. 29.
	 * @param thisObj
	 */
	duplicateCheck : function(thisObj) {
		Dept.IS_DEPT_CODE_DUPLECATE = false;
		
		if ($jquery("#" + Dept.FORM_ID).validationEngine("CLFValidateField", $jquery("#" + Dept.FORM_ID + " #deptCode"))) {
			$jquery.ajax({
				type	: "POST",
				url		: jsContextPath + "/uss/org/dept/isExistDeptCode.tech",
				data	: $jquery("#" + Dept.FORM_ID + " #deptCode").serialize(),
				dataType: "json",
				success	: function (data) {
					if (data.result) {
						$jquery("#" + Dept.FORM_ID + " #deptCode").removeClass("validT");
						$jquery("#" + Dept.FORM_ID + " #deptCode").addClass("validF");
						alert('이미 등록된 부서코드 입니다.\n다른 부서코드를 입력하세요.');
					} else {
						$jquery("#" + Dept.FORM_ID + " #deptCode").removeClass("validF");
						$jquery("#" + Dept.FORM_ID + " #deptCode").addClass("validT");
						
						$jquery("#" + Dept.FORM_ID + " #deptCode").data("duplecateText", $jquery("#" + Dept.FORM_ID + " #deptCode").val());
						
						alert('등록가능한 부서코드 입니다.');
						Dept.IS_DEPT_CODE_DUPLECATE = true;
					}
				},
				error	: function(x, e) {
					alert("오류가 발생되었습니다.");
				},
				async	: false
			});
		}
	},
	
	/**
	 * 개요 : 중복체크 텍스트 변경상태 확인
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 9. 29.
	 * @param 
	 */
	duplecateTextCheck : function(thisObj) {
		if ($jquery(thisObj).val() !== "") {
			if ($jquery(thisObj).val() !== $jquery(thisObj).data("duplecateText")) {
				$jquery(thisObj).removeClass("validT");
				$jquery(thisObj).addClass("validF");
				Dept.IS_DEPT_CODE_DUPLECATE = false;
			} else {
				$jquery(thisObj).removeClass("validF");
				$jquery(thisObj).addClass("validT");
				Dept.IS_DEPT_CODE_DUPLECATE = true;
			}
		}
	},
	
	setDept : function(deptVO) {
		var mode = $jquery("#" + Dept.FORM_ID + " #mode").val();
		
		if (mode === "modify") {
			if ($jquery("#" + Dept.FORM_ID + " #deptCode").val() == deptVO.deptCode) {
				alert("현재 부서를 상위 부서로 지정할 수 없습니다.");
				return;
			}
		}
		
		$jquery("#" + Dept.FORM_ID + " #atmbUpperDeptCode").val(deptVO.deptCode);
		
		if (!isEmpty(deptVO.bestDeptCode)) {
			$jquery("#" + Dept.FORM_ID + " #bestDeptCode").val(deptVO.bestDeptCode);
		} else if (!isEmpty(deptVO.atmbUpperDeptCode)) {
			$jquery("#" + Dept.FORM_ID + " #bestDeptCode").val(deptVO.atmbUpperDeptCode);
		} else {
			$jquery("#" + Dept.FORM_ID + " #bestDeptCode").val(deptVO.deptCode);
		}
	},
	
	/**
	 * 개요 : 엑셀 다운로드
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 9. 29.
	 * @param thisObj
	 */
	excelDownload : function(thisObj) {
		if(Dept.TOTAL_CNT == 0){
			alert("해당 데이터가 없습니다");
		}else if(Dept.TOTAL_CNT > 10000){
			alert("1만건 이상 엑셀받기를 할 수 없습니다");
		}else{
			$jquery.growlUI("info", "엑셀 다운로드를 요청하였습니다.<br />잠시만 기다려주세요.");
			
			$jquery("#" + Dept.FORM_ID).attr("action", "downloadDeptExcel.tech");
			$jquery("#" + Dept.FORM_ID).submit();
		}
	}
};