var User = {
	MENU_NO : "",
	FORM_ID : "formUser",
	LIST_ID : "retrieveUserList",
	DETAIL_ID : "retrieveUserDetail",
	CREATE_ID : "createUser",
	MODIFY_ID : "modifyUser",
	PAGE_INDEX_ID : "pageIndex",
	ACTION_STATUS : "",
	IS_EMPLYR_ID_DUPLECATE : false,
	TOTAL_CNT : 0,
	
	/**
	 * 개요 : 목록 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 26.
	 * @param 
	 */
	formInitList : function() {
		$jquery("#menuNavi").html(Navi.getNaviNm(User.MENU_NO));
		$jquery("#progrmNm").html(Navi.getProgrmNm(User.LIST_ID));
		
		$jquery("#" + User.FORM_ID + " #deptSearch").button({
			text : false,
			icons : {
				primary : "ui-icon-search"
			}
		});

		$jquery("#" + User.FORM_ID + " #deptClear").button({
			text : false,
			icons : {
				primary : "ui-icon-closethick"
			}
		});
	    
		// 해당 메뉴의 권한정보 조회
		AuthCommon.selectMenuAuthor(User.MENU_NO);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
		
		$jquery("#" + User.FORM_ID).separator('separatorAddForm');
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
		$jquery("#emplyrId").addClass("validF");
		
		$jquery("#" + User.FORM_ID + " #deptSearch").button({
			text : false,
			icons : {
				primary : "ui-icon-search"
			}
		});

		$jquery("#" + User.FORM_ID + " #deptClear").button({
			text : false,
			icons : {
				primary : "ui-icon-closethick"
			}
		});
		
		if ($jquery("#" + User.FORM_ID + " #mode").val() == "create") {
			$jquery("#progrmNm").html(Navi.getProgrmNm(User.CREATE_ID));
		} else {
			$jquery("#progrmNm").html(Navi.getProgrmNm(User.MODIFY_ID));
		}
		
		$jquery("#" + User.FORM_ID).separator('separatorAddForm');
		
		$jquery("#menuNavi").html(Navi.getNaviNm(User.MENU_NO));
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
		$jquery("#menuNavi").html(Navi.getNaviNm(User.MENU_NO));
		$jquery("#progrmNm").html(Navi.getProgrmNm(User.DETAIL_ID));
		
		// 해당 메뉴의 권한정보 조회
		AuthCommon.selectMenuAuthor(User.MENU_NO);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
		
		$jquery("#" + User.FORM_ID).separator('separatorAddForm');
		showBtnAccessKey();
	},
	
	fn_egov_link_page : function(pageIndex) {
		User.setPageIndex(pageIndex);
		User.refreshList();
	},
	
	/**
	 * 개요 : 페이지 인덱스 설정
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8
	 * @param pageIndex
	 */
	setPageIndex : function(pageIndex) {
		$jquery("#" + User.FORM_ID + " #" + User.PAGE_INDEX_ID).val(pageIndex);
	},

	/**
	 * 개요 : 목록 새로고침
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8
	 * @param thisObj
	 */
	refreshList : function(thisObj) {
		location.hash = "AC=/system/user/retrieveUserList.tech&VA=content_body&" + $jquery("#" + User.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 목록조회
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8
	 * @param thisObj
	 */
	search : function(thisObj) {
		User.setPageIndex(1);
		User.refreshList(thisObj);
	},

	/**
	 * 개요 : 상세조회 페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8
	 * @param thisObj
	 */
	detailView : function(thisObj) {
		$jquery("#esntlId").val($jquery(thisObj).prop("id"));
		
		location.hash = "AC=/system/user/retrieveUserDetail.tech&VA=content_body&" + $jquery("#" + User.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 목록조회 페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8
	 * @param thisObj
	 */
	listView : function(thisObj) {
		location.hash = "AC=/system/user/retrieveUserList.tech&VA=content_body&" + $jquery("#" + User.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 등록 페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8.
	 * @param thisObj
	 */
	createView : function(thisObj) {
		$jquery("#" + User.FORM_ID + " #emplyrId").val('');
		
		location.hash = "AC=/system/user/createUser.tech&VA=content_body&" + $jquery("#" + User.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 수정페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8.
	 * @param thisObj
	 */
	modifyView : function(thisObj) {
		location.hash = "AC=/system/user/modifyUser.tech&VA=content_body&" + $jquery("#" + User.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 수정취소
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8.
	 * @param thisObj
	 */
	modifyCancel : function(thisObj) {
		location.hash = "AC=/system/user/retrieveUserDetail.tech&VA=content_body&" + $jquery("#" + User.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 저장
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8.
	 * @param thisObj
	 */
	save : function(thisObj) {
		if (User._formValidate()) {
			if (confirm("저장하시겠습니까?")) {
				$jquery("#" + User.FORM_ID + " #saveLaterView").val("detail");
				User.saveProc();
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
		if (User._formValidate()) {
			if (confirm("저장하시겠습니까?")) {
				$jquery("#" + User.FORM_ID + " #saveLaterView").val("create");
				User.saveProc();
				
				if (User.ACTION_STATUS == "success") {
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
		if ($jquery("#" + User.FORM_ID).validationEngine("CLFValidate")) {
			if ($jquery('#' + User.FORM_ID + ' #mode').val() == "create" && !User.IS_EMPLYR_ID_DUPLECATE) {
				alert("사용자ID 중복검사를 해야 합니다.");
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
		var url = "/system/user/createUserProc.tech";
		var mode = $jquery("#" + User.FORM_ID + " #mode").val();
		
		if (mode == "modify") {
			url = "/system/user/modifyUserProc.tech";
		}
		
		$jquery("#content_body").ajaxload(
			"blockLoad",
			jsContextPath + url,
			"POST",
			"html",
			$jquery("#" + User.FORM_ID).separator("separatorRemoveForm").serialize(),
			false
		);
	},
	
	/**
	 * 개요 : 사용자 정보 삭제처리
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 1.
	 * @param thisObj
	 */
	deleteUser : function(thisObj) {
		if (confirm("삭제하시겠습니까?")) {
			location.hash = "AC=/system/user/deleteUser.tech&VA=content_body&" + $jquery("#" + User.FORM_ID).separator("separatorRemoveForm").serialize();
		}
	},
	
	/**
	 * 개요 : 아이디 중복검사
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8.
	 * @param thisObj
	 */
	duplicateCheck : function(thisObj) {
		User.IS_EMPLYR_ID_DUPLECATE = false;
		
		var paramObj = {
			emplyrId : $jquery('#' + User.FORM_ID + ' #emplyrId').val()
		};
		
		if ($jquery("#" + User.FORM_ID).validationEngine("CLFValidateField", $jquery("#emplyrId"))) {
			userService.selectUserIdExists(paramObj, {
				callback : function (returnObj) {
					if (returnObj) {
						$jquery("#emplyrId").removeClass("validT");
						$jquery("#emplyrId").addClass("validF");
						alert('이미 등록된 아이디 입니다.\n다른 아이디를 입력하세요.');
					} else {
						$jquery("#emplyrId").removeClass("validF");
						$jquery("#emplyrId").addClass("validT");
						
						$jquery("#emplyrId").data("duplecateText", paramObj.emplyrId);
						
						alert('등록가능한 아이디 입니다.');
						User.IS_EMPLYR_ID_DUPLECATE = true;
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
				User.IS_EMPLYR_ID_DUPLECATE = false;
			} else {
				$jquery(thisObj).removeClass("validF");
				$jquery(thisObj).addClass("validT");
				User.IS_EMPLYR_ID_DUPLECATE = true;
			}
		}
	},
	
	/**
	 * 개요 : 이메일 도메인 설정
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 2. 22.
	 * @param 
	 */
	setEmailDomain : function(thisObj, domainObjId) {
		if ($jquery(thisObj).find("option:selected").val() != "1") {
			$jquery("#" + User.FORM_ID + " #" + domainObjId).val($jquery(thisObj).find("option:selected").text());
			$jquery("#" + User.FORM_ID + " #" + domainObjId).attr('readonly', true);
			$jquery("#" + User.FORM_ID + " #" + domainObjId).addClass('readonly');
		} else {
			$jquery("#" + User.FORM_ID + " #" + domainObjId).val('');
			$jquery("#" + User.FORM_ID + " #" + domainObjId).attr('readonly', false);
			$jquery("#" + User.FORM_ID + " #" + domainObjId).removeClassRegEx('readonly');
			$jquery("#" + User.FORM_ID + " #" + domainObjId).focus();
		}
	},
	
	/**
	 * 개요 : 가입승인 처리
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 3. 1.
	 * @param 
	 */
	sbscrbConfm : function(thisObj) {
		if (confirm("가입승인 처리 하시겠습니까?")) {
			$jquery.ajax({
				type	: "POST",
				url		: jsContextPath + "/system/user/modifyUserSbscrbConfm.tech",
				data	: {"esntlId" : $jquery('#' + User.FORM_ID + ' #esntlId').val()},
				dataType: "json",
				success	: function (data) {
					if (data.actionStatus == "success") {
						$jquery("#emplyrSttusCodeNm").html("승인");
						alert("가입승인 되었습니다.");
					} else {
						alert("승인처리 실패");
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
	 * 개요 : 비밀번호 초기화
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 3. 1.
	 * @param 
	 */
	initPasswd : function(thisObj) {
		if (confirm("비밀번호를 초기화 하시겠습니까?")) {
			$jquery.ajax({
				type	: "POST",
				url		: jsContextPath + "/system/user/modifyUserInitPassword.tech",
				data	: {"esntlId" : $jquery('#' + User.FORM_ID + ' #esntlId').val()},
				dataType: "json",
				success	: function (data) {
					if (data.actionStatus == "success") {
						prompt("비밀번호가 초기화 되었습니다. Ctrl+C를 눌러 복사하세요.", data.password)
					} else {
						alert("비밀번호 초기화 실패");
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
	 * 개요 : 부서선택 팝업
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 10. 16.
	 * @param 
	 */
	deptPopupView : function(thisObj) {
		var pUrl = jsContextPath + "/uss/org/dept/formDeptPopup.tech";
		var pName = "deptPopup";
		var pWidth = 500;
		var pHeight = 500;
		var openerKey = User.FORM_ID;
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
	 * 개요 : 부서입력 항목 정리
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 11. 28.
	 * @param 
	 */
	deptClear : function(thisObj) {
		$jquery("#" + User.FORM_ID + " #deptNm").val("");
		$jquery("#" + User.FORM_ID + " #deptCode").val("");
	},
	
	/**
	 * 개요 : 부서검색조건 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 10. 16.
	 * @param thisObj
	 */
	condDeptInit : function(thisObj) {
		$jquery("#" + User.FORM_ID + " #condDeptNm").val("");
		$jquery("#" + User.FORM_ID + " #condDeptCode").val("");
	},
	
	/**
	 * 개요 : 엑셀 다운로드
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8.
	 * @param thisObj
	 */
	excelDownload : function(thisObj){
		if(User.TOTAL_CNT == 0){
			alert("해당 데이터가 없습니다");
		}else if(User.TOTAL_CNT > 10000){
			alert("1만건 이상 엑셀받기를 할 수 없습니다");
		}else{
			$jquery.growlUI("info", "엑셀 다운로드를 요청하였습니다.<br />잠시만 기다려주세요.");
			
			$jquery("#" + User.FORM_ID).attr("action", "downloadUserExcel.tech");
			$jquery("#" + User.FORM_ID).submit();
		}
	}	
};