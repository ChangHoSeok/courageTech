var LogClGroup = {
	MENU_NO : "",
	FORM_ID : "formLogClGroup",
	CREATE_FORM_ID : "formLogClGroupCreate",
	MODIFY_FORM_ID : "formLogClGroupModify",
	LIST_ID : "retrieveLogClGroupList",
	PAGE_INDEX_ID : "pageIndex",
	ACTION_STATUS : "",
	TOTAL_CNT : 0,
	IS_DUPLECATE : false,
	
	/**
	 * 개요 : 목록 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 18.
	 * @param 
	 */
	formInitList : function() {
		$jquery("#menuNavi").html(Navi.getNaviNm(LogClGroup.MENU_NO));
		$jquery("#progrmNm").html(Navi.getProgrmNm(LogClGroup.LIST_ID));
	    
		// 해당 메뉴의 권한정보 조회
		AuthCommon.selectMenuAuthor(LogClGroup.MENU_NO);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		} else {
			$jquery("#" + LogClGroup.CREATE_FORM_ID + " #groupId").on("blur", function() {
				LogClGroup.duplicateCheck();
			});
			
			$jquery("#" + LogClGroup.CREATE_FORM_ID + " #groupId").on("keyup", function() {
				LogClGroup.duplecateTextCheck($jquery(this));
			});
			
			$jquery("tr.dbclickListener").on("dblclick", function() {
				var groupId = $jquery(this).prop("id").split("_")[1];
				
				$jquery(".editorTr").hide();
				$jquery(".viewTr").show();
				
				$jquery("#view_" + groupId).hide();
				$jquery("#editor_" + groupId).show();
				
				LogClGroup.modifyView(groupId);
			});
		}
		
		$jquery("#" + LogClGroup.FORM_ID).separator('separatorAddForm');
		showBtnAccessKey();
	},
	
	/**
	 * 개요 : 등록 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 18.
	 * @param 
	 */
	formInitCreate : function() {
		if ($jquery("#" + LogClGroup.FORM_ID + " #mode").val() == "create") {
			$jquery("#progrmNm").html(Navi.getProgrmNm(LogClGroup.CREATE_ID));
		} else {
			$jquery("#progrmNm").html(Navi.getProgrmNm(LogClGroup.MODIFY_ID));
		}
		
		$jquery("#" + LogClGroup.FORM_ID).separator('separatorAddForm');
		$jquery("#" + LogClGroup.FORM_ID + " #bbsIntrcn").byteChk("keyup", 2, 1000);
		
		$jquery("#menuNavi").html(Navi.getNaviNm(LogClGroup.MENU_NO));
		
		showBtnAccessKey();
		
		// Field 설정
		if (isEmpty($jquery("#useAt").val())) {
			$jquery("#useAt option[value=Y]").prop("selected", true);
		}
		
		LogClGroup.fieldSet($jquery("#fileAtchPosblAt"));
		LogClGroup.fieldSet($jquery("#mvpPosblAt"));
	},

	/**
	 * 개요 : 상세 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 18.
	 * @param 
	 */
	formInitDetail : function() {
		$jquery("#menuNavi").html(Navi.getNaviNm(LogClGroup.MENU_NO));
		$jquery("#progrmNm").html(Navi.getProgrmNm(LogClGroup.DETAIL_ID));
		
		// 해당 메뉴의 권한정보 조회
		AuthCommon.selectMenuAuthor(LogClGroup.MENU_NO);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
		
		$jquery("#" + LogClGroup.FORM_ID).separator('separatorAddForm');
		showBtnAccessKey();
	},
	
	fn_egov_link_page : function(pageIndex) {
		LogClGroup.setPageIndex(pageIndex);
		LogClGroup.refreshList();
	},
	
	/**
	 * 개요 : 페이지 인덱스 설정
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 18.
	 * @param pageIndex
	 */
	setPageIndex : function(pageIndex) {
		$jquery("#" + LogClGroup.FORM_ID + " #" + LogClGroup.PAGE_INDEX_ID).val(pageIndex);
	},

	/**
	 * 개요 : 목록 새로고침
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 18.
	 * @param thisObj
	 */
	refreshList : function(thisObj) {
		location.hash = "AC=/das/log/retrieveLogClGroupList.tech&VA=content_body&" + $jquery("#" + LogClGroup.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 목록조회
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 18.
	 * @param thisObj
	 */
	search : function(thisObj) {
		LogClGroup.setPageIndex(1);
		LogClGroup.refreshList(thisObj);
	},

	/**
	 * 개요 : 수정페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 18.
	 * @param groupId
	 */
	modifyView : function(groupId) {
		$jquery.ajax({
			type	: "POST",
			url		: jsContextPath + "/das/log/retrieveLogClGroupDetail.tech",
			data	: {"groupId" : groupId},
			dataType: "json",
			success	: function (data) {
				$jquery("#" + LogClGroup.MODIFY_FORM_ID + " #groupNm_" + groupId).val(data.logClGroupVO.groupNm);
				$jquery("#" + LogClGroup.MODIFY_FORM_ID + " #groupDc_" + groupId).val(data.logClGroupVO.groupDc);
			},
			error	: function(x, e) {
				alert("오류가 발생되었습니다.");
			},
			async	: false
		});
	},

	/**
	 * 개요 : 저장
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 18.
	 * @param thisObj
	 */
	save : function(thisObj) {
		var groupId = $jquery(thisObj).prop("id").split("_")[1];
		
		if (LogClGroup._formValidate(groupId)) {
			if (confirm("저장하시겠습니까?")) {
				LogClGroup.saveProc(groupId);
			}
		}
	},
	
	/**
	 * 개요 : 입력항목 유효성 검사
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 18.
	 * @param thisObj
	 */
	_formValidate : function(groupId) {
		var targetForm = LogClGroup.CREATE_FORM_ID;
		if (groupId !== undefined && groupId !== "") {
			targetForm = LogClGroup.MODIFY_FORM_ID;
		}
		
		if ($jquery("#" + targetForm).validationEngine("validate")) {
			return true;
		} else {
			$jquery.growlUI("warn", "입력값을 확인하세요");
			return false;
		}
	},
	
	/**
	 * 개요 : 그룹아이디 중복 확인
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 18.
	 * @param thisObj
	 */
	duplicateCheck : function() {
		LogClGroup.IS_DUPLECATE = false;
		
		var paramObj = {
			groupId : $jquery('#' + LogClGroup.CREATE_FORM_ID + ' #groupId').val()
		};
		
		if ($jquery("#" + LogClGroup.CREATE_FORM_ID).validationEngine("validateField", $jquery("#groupId"))) {
			logClGroupService.selectLogClGroupIdExists(paramObj, {
				callback : function (returnObj) {
					if (returnObj) {
						$jquery("#groupId").removeClass("validT");
						$jquery("#groupId").addClass("validF");
						alert('이미 등록된 아이디 입니다.\n다른 아이디를 입력하세요.');
						
						$jquery("#groupId").focus();
					} else {
						$jquery("#groupId").removeClass("validF");
						$jquery("#groupId").addClass("validT");
						
						$jquery("#groupId").data("duplecateText", paramObj.groupId);
						
						LogClGroup.IS_DUPLECATE = true;
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
		if ($jquery(thisObj).val() !== "" && $jquery(thisObj).data("duplecateText") !== undefined) {
			if ($jquery(thisObj).val() !== $jquery(thisObj).data("duplecateText")) {
				$jquery(thisObj).removeClass("validT");
				$jquery(thisObj).addClass("validF");
				LogClGroup.IS_DUPLECATE = false;
			} else {
				$jquery(thisObj).removeClass("validF");
				$jquery(thisObj).addClass("validT");
				LogClGroup.IS_DUPLECATE = true;
			}
		}
	},

	/**
	 * 개요 : 저장처리
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 18.
	 * @param thisObj
	 */
	saveProc : function(groupId) {
		var url = "/das/log/createLogClGroupProc.tech";
		var data = $jquery("#" + LogClGroup.CREATE_FORM_ID).separator("separatorRemoveForm").serialize();
		
		if (groupId !== undefined && groupId !== "") {
			url = "/das/log/modifyLogClGroupProc.tech";
			data = $jquery("#" + LogClGroup.MODIFY_FORM_ID + " #editor_" + groupId + " input").separator("separatorRemoveForm").serialize();
		}
		
		$jquery.ajax({
			type	: "POST",
			url		: jsContextPath + url,
			data	: data,
			dataType: "json",
			success	: function (data) {
				if (data.actionStatus == "success") {
					$jquery.growlUI("info", "저장되었습니다.");
					LogClGroup.refreshList();
				} else {
					if (data.actionMessage !== "") {
						alert(data.actionMessage);
					} else {
						alert("저장처리를 정상적으로 완료하지 못했습니다.");
					}
				}
			},
			error	: function(x, e) {
				alert("오류가 발생되었습니다.");
			},
			async	: false
		});
	},
	
	/**
	 * 개요 : 프로그램 삭제처리 (단일정보)
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 18.
	 * @param thisObj
	 */
	deleteLogClGroup : function(thisObj) {
		var groupId = $jquery(thisObj).prop("id").split("_")[1];
		
		if (confirm("그룹정보를 삭제하면 등록된 로그정보가 모두 삭제됩니다.\n삭제 하시겠습니까?")) {
			$jquery.ajax({
				type	: "POST",
				url		: jsContextPath + "/das/log/deleteLogClGroup.tech",
				data	: {"groupId" : groupId},
				dataType: "json",
				success	: function (data) {
					if (data.actionStatus == "success") {
						$jquery.growlUI("info", "삭제 되었습니다.");
						LogClGroup.refreshList();
					} else {
						if (data.actionMessage !== "") {
							alert(data.actionMessage);
						} else {
							alert("삭제처리를 정상적으로 완료하지 못했습니다.");
						}
					}
				},
				error	: function(x, e) {
					alert("오류가 발생되었습니다.");
				},
				async	: false
			});
		}
	}
};