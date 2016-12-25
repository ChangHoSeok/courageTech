var Menu = {
	MENU_NO : "",
	FORM_ID : "formMenu",
	LIST_ID : "retrieveMenuList",
	DETAIL_ID : "retrieveMenuDetail",
	CREATE_ID : "createMenu",
	MODIFY_ID : "modifyMenu",
	PAGE_INDEX_ID : "pageIndex",
	ACTION_STATUS : "",
	IS_MENU_DUPLECATE : false,
	UPPER_MENU_ID : "",
	MENU_ID : "",
	TOTAL_CNT : 0,
	
	/**
	 * 개요 : 목록 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 26.
	 * @param 
	 */
	formInitList : function() {
		$jquery("#menuNavi").html(Navi.getNaviNm(Menu.MENU_NO));
		$jquery("#progrmNm").html(Navi.getProgrmNm(Menu.LIST_ID));
		
		// 해당 메뉴의 권한정보 조회
		AuthCommon.selectMenuAuthor(Menu.MENU_NO);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
		
		$jquery("#" + Menu.FORM_ID).separator('separatorAddForm');
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
		$jquery("#menuId").addClass("validF");
		
		if ($jquery("#" + Menu.FORM_ID + " #mode").val() == "create") {
			$jquery("#progrmNm").html(Navi.getProgrmNm(Menu.CREATE_ID));
		} else {
			$jquery("#progrmNm").html(Navi.getProgrmNm(Menu.MODIFY_ID));
		}
		
		if (!isEmpty(Menu.UPPER_MENU_ID)) {
			if (isEmpty(Menu.MENU_ID)) {
				$jquery('#formMenuProgrmList > #condMenuNo').val('-1');
			}
			
			$jquery('#menuProgrmList').ajaxload(
				'blockLoad',
				jsContextPath + '/system/progrm/retrieveProgrmList.tech',
				"POST",
				"html",
				$jquery("#formMenuProgrmList").separator('separatorRemoveForm').serialize(),
				false
			);
		}
		
		$jquery("#menuNavi").html(Navi.getNaviNm(Menu.MENU_NO));
		Menu.fieldSet($jquery("#menuIndictAt"));
		
		Menu.IS_MENU_DUPLECATE = false;
		$jquery("#" + Menu.FORM_ID).separator('separatorAddForm');
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
		if (!isEmpty(Menu.UPPER_MENU_ID)) {
			$jquery('#menuProgrmList').ajaxload(
				'blockLoad',
				jsContextPath + '/system/progrm/retrieveProgrmList.tech',
				"POST",
				"html",
				$jquery("#formMenuProgrmList").separator('separatorRemoveForm').serialize()
			);
		}
		
		$jquery("#menuNavi").html(Navi.getNaviNm(Menu.MENU_NO));
		$jquery("#progrmNm").html(Navi.getProgrmNm(Menu.DETAIL_ID));
		
		// 해당 메뉴의 권한정보 조회
		AuthCommon.selectMenuAuthor(Menu.MENU_NO);
		
		if (!AuthCommon.isCreate()) {
			$jquery('.needCreate').remove();
		}
		
		$jquery("#" + Menu.FORM_ID).separator('separatorAddForm');
		showBtnAccessKey();
	},
	
	/**
	 * 개요 : 필드값 초기화
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 6. 1.
	 * @param 
	 */
	fieldSet : function(thisObj) {
		if ($jquery(thisObj).attr("id") == "menuIndictAt") {
			if ($jquery(thisObj).val() == "Y") {
				$jquery("#" + Menu.FORM_ID + " #intrmdVriabl").prop("disabled", false).removeClass("readonly");
			} else {
				$jquery("#" + Menu.FORM_ID + " #intrmdVriabl").val("").prop("disabled", true).addClass("readonly");
			}
		}
	},
	
	fn_egov_link_page : function(pageIndex) {
		Menu.setPageIndex(pageIndex);
		Menu.refreshList();
	},
	
	/**
	 * 개요 : 페이지 인덱스 설정
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8
	 * @param pageIndex
	 */
	setPageIndex : function(pageIndex) {
		$jquery("#" + Menu.FORM_ID + " #" + Menu.PAGE_INDEX_ID).val(pageIndex);
	},

	/**
	 * 개요 : 목록 새로고침
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8
	 * @param thisObj
	 */
	refreshList : function(thisObj) {
		location.hash = "AC=/system/menu/retrieveMenuList.tech&VA=content_body&" + $jquery("#" + Menu.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 목록조회
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8
	 * @param thisObj
	 */
	search : function(thisObj) {
		Menu.setPageIndex(1);
		Menu.refreshList(thisObj);
	},

	/**
	 * 개요 : 상세조회 페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8
	 * @param thisObj
	 */
	detailView : function(thisObj) {
		$jquery("#menuId").val($jquery(thisObj).prop("id"));
		
		location.hash = "AC=/system/menu/retrieveMenuDetail.tech&VA=content_body&" + $jquery("#" + Menu.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 목록조회 페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8
	 * @param thisObj
	 */
	listView : function(thisObj) {
		location.hash = "AC=/system/menu/retrieveMenuList.tech&VA=content_body&" + $jquery("#" + Menu.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 등록 페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8.
	 * @param thisObj
	 */
	createView : function(thisObj) {
		var upperMenuId = "0";
		
		if (!isEmpty($jquery(thisObj).prop("id"))) {
			upperMenuId = $jquery(thisObj).prop("id");
		}
		
		$jquery("#" + Menu.FORM_ID + " #menuId").val("");
		$jquery("#" + Menu.FORM_ID + " #upperMenuId").val(upperMenuId);
		
		location.hash = "AC=/system/menu/createMenu.tech&VA=content_body&" + $jquery("#" + Menu.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 수정페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8.
	 * @param thisObj
	 */
	modifyView : function(thisObj) {
		location.hash = "AC=/system/menu/modifyMenu.tech&VA=content_body&" + $jquery("#" + Menu.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 수정취소
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8.
	 * @param thisObj
	 */
	modifyCancel : function(thisObj) {
		location.hash = "AC=/system/menu/retrieveMenuDetail.tech&VA=content_body&" + $jquery("#" + Menu.FORM_ID).separator("separatorRemoveForm").serialize();
	},
	
	/**
	 * 개요 : 저장
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8.
	 * @param thisObj
	 */
	save : function(thisObj) {
		if (Menu._formValidate()) {
			if (confirm("저장하시겠습니까?")) {
				$jquery("#" + Menu.FORM_ID + " #saveLaterView").val("detail");
				Menu.saveProc();
			}
		}
	},
	
	/**
	 * 개요 : 저장 후 계속
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 2. 15.
	 * @param thisObj
	 */
	saveLaterCreate : function(thisObj) {
		if (Menu._formValidate()) {
			if (confirm("저장하시겠습니까?")) {
				$jquery("#" + Menu.FORM_ID + " #saveLaterView").val("create");
				Menu.saveProc();
				
				if (Menu.ACTION_STATUS == "success") {
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
		if ($jquery("#" + Menu.FORM_ID).validationEngine("CLFValidate")) {
			if ($jquery("#" + Menu.FORM_ID + " #mode").val() == "create" && !Menu.IS_MENU_DUPLECATE) {
				alert("메뉴ID 중복검사를 해야 합니다.");
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
		var url = "/system/menu/createMenuProc.tech";
		var mode = $jquery("#" + Menu.FORM_ID + " #mode").val();
		
		if (mode == "modify") {
			url = "/system/menu/modifyMenuProc.tech";
		}
		
		$jquery("#content_body").ajaxload(
			"blockLoad",
			jsContextPath + url,
			"POST",
			"html",
			$jquery("#" + Menu.FORM_ID).separator("separatorRemoveForm").serialize(),
			false
		);
	},
	
	/**
	 * 개요 : 메뉴 삭제처리 (다중)
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 1.
	 * @param thisObj
	 */
	deleteMenuList : function(thisObj) {
		if ($jquery("input[name=deletelMenuFileNmList]:checked:checked").length > 0) {
			if (confirm("메뉴정보 삭제시 선택한 메뉴의 하위메뉴 정보까지 모두 삭제됩니다.\r\n선택한 메뉴를 삭제하시겠습니까?")) {
				$jquery("#content_body").ajaxload(
					"blockLoad",
					jsContextPath + "/system/menu/deleteMenuList.tech",
					"POST",
					"html",
					$jquery("#"+ Menu.FORM_ID).separator("separatorRemoveForm").serialize()
				);
			}
		} else {
			alert("삭제할 메뉴를 선택하세요");
		}
	},
	
	/**
	 * 개요 : 메뉴 삭제처리 (단일정보)
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 1.
	 * @param thisObj
	 */
	deleteMenu : function(thisObj) {
		if (confirm("메뉴정보 삭제시 해당 메뉴의 하위메뉴 정보까지 모두 삭제됩니다.\r\n삭제하시겠습니까?")) {
			$jquery("#content_body").ajaxload(
				"blockLoad",
				jsContextPath + "/system/menu/deleteMenu.tech",
				"POST",
				"html",
				$jquery("#"+ Menu.FORM_ID).separator("separatorRemoveForm").serialize()
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
		Menu.IS_MENU_DUPLECATE = false;
		
		var paramObj = {
			menuId : $jquery("#" + Menu.FORM_ID + " #menuId").val()
		};
		
		if ($jquery("#" + Menu.FORM_ID).validationEngine("CLFValidateField", $jquery("#menuId"))) {
			meunService.selectMenuManage(paramObj, {
				callback : function (returnObj) {
					if (returnObj !== null) {
						$jquery("#menuId").removeClass("validT");
						$jquery("#menuId").addClass("validF");
						
						alert("중복된 메뉴번호가 존재합니다.");
					} else {
						$jquery("#menuId").removeClass("validF");
						$jquery("#menuId").addClass("validT");
						
						$jquery("#menuId").data("duplecateText", paramObj.menuId);
						
						alert("등록가능한 메뉴번호 입니다.");
						Menu.IS_MENU_DUPLECATE = true;
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
	 * 개요 : 프로그램 추가 팝업 오픈
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 9.
	 * @param thisObj
	 */
	progrmWindowOpen : function(thisObj) {
		var pUrl = jsContextPath + "/system/progrm/formProgrmPopup.tech";
		var pName = "popupProgrm";
		var pWidth = 500;
		var pHeight = 500;
		var openerKey = Menu.FORM_ID;
		var returnFunction = ''; //callback function
		
		var PopupWindow = PopUpWindowOpenOnlyScroll(pName, pWidth, pHeight);
		
		var disableProgrmFileNm = [];
		$jquery('#progrmSubListBody input[name=progrmChk]').each(function() {
			disableProgrmFileNm.push($jquery(this).val());
		});
		
		addFormParameter("popupForm", "disableProgrmFileNm", disableProgrmFileNm);
		$jquery('#popupForm input[name="openerKey"]').val(openerKey);
		$jquery('#popupForm input[name="returnFunction"]').val(returnFunction);
		$jquery('#popupForm').attr('target', pName);
		$jquery('#popupForm').attr('action', pUrl).submit();
		
		removeFormParameter("popupForm", "disableProgrmFileNm");
		
		PopupWindow.focus();
	},
	
	/**
	 * 개요 : 상위메뉴 선택 팝업 오픈
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 9.
	 * @param thisObj
	 */
	menuWindowOpen : function(thisObj) {
		var pUrl = jsContextPath + "/system/menu/formMenuPopup.tech";
		var pName = "popupProgrm";
		var pWidth = 500;
		var pHeight = 500;
		var openerKey = Menu.FORM_ID;
		var returnFunction = ''; //callback function
		
		var PopupWindow = PopUpWindowOpenOnlyScroll(pName, pWidth, pHeight);
		
		addFormParameter("popupForm", "subMenuId", $jquery("#" + Menu.FORM_ID + " #menuId").val());
		
		$jquery('#popupForm input[name="openerKey"]').val(openerKey);
		$jquery('#popupForm input[name="returnFunction"]').val(returnFunction);
		$jquery('#popupForm').attr('target', pName);
		$jquery('#popupForm').attr('action', pUrl).submit();
		
		removeFormParameter("popupForm", "subMenuId");
		
		PopupWindow.focus();
	},
	
	/**
	 * 개요 : 프로그램 서브 리스트 추가
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 9.
	 * @param thisObj
	 */
	addProgrmSubList : function (progrmList) {
		if (progrmList.length > 0) {
			var innerHtml = "";
			var duplecateCnt = 0;
			var mainProgrmDuplicateCnt = 0;
			var isAddMainProgrm = false;
			
			for (var i = 0; i < progrmList.length; i++) {
				
				if ($jquery('#progrmSubListBody > #tr' + progrmList[i].progrmFileNm).length <= 0) {
					if ((progrmList[i].progrmSe == "01" && $jquery('#progrmSubListBody #progrmSe_01').length > 0) ||
							(isAddMainProgrm && progrmList[i].progrmSe == "01")) {
						mainProgrmDuplicateCnt++;
					} else {
						
						if (progrmList[i].progrmSe == "01") {
							isAddMainProgrm = true;
						}
						
						innerHtml += '<tr id="tr' + progrmList[i].progrmFileNm + '">';
						innerHtml += '<td class="pl0">';
						innerHtml += '<input type="checkbox" name="progrmChk" id="' + progrmList[i].progrmFileNm + '" title="선택" value="' + progrmList[i].progrmFileNm + '" />';
						innerHtml += '<input type="hidden" name="menuProgrmList" value="' + progrmList[i].progrmFileNm + '" />';
						innerHtml += '</td>';
						innerHtml += '<td id="progrmSe_' + progrmList[i].progrmSe + '">' + (progrmList[i].progrmSe == "01" ? "메인프로그램" : "서브프로그램") + '&nbsp;</td>';
						innerHtml += '<td class="Ltxt">' + progrmList[i].progrmFileNm + '&nbsp;</td>';
						innerHtml += '<td class="Ltxt"><div class="ellipsis" title="' + progrmList[i].progrmKoreanNm + '">' + progrmList[i].progrmKoreanNm + '&nbsp;</div></td>';
						innerHtml += '<td class="Ltxt"><div class="ellipsis" title="' + progrmList[i].url + '">' + progrmList[i].url + '&nbsp;</div></td>';
						innerHtml += '<td class="Ltxt">' + progrmList[i].progrmDc + '&nbsp;</td>';
						innerHtml += '</tr>';
					}
				} else {
					duplecateCnt++;
				}
			}
			
			if (duplecateCnt > 0) {
				alert("중복된 데이터 " + (duplecateCnt + mainProgrmDuplicateCnt) + "개 제외 후 " + (progrmList.length - duplecateCnt - mainProgrmDuplicateCnt) + "개 추가되었습니다.");
			}
			
			if (mainProgrmDuplicateCnt > 0) {
				alert("메인메뉴은 하나의 메뉴에 한개만 등록할 수 있습니다.");
			}
			
			if (!isEmpty(innerHtml)) {
				$jquery('#progrmSubListBody > #noData').remove();
			}
			
			$jquery('#progrmSubListBody').append(innerHtml);
		}
	},
	
	/**
	 * 개요 : 상위메뉴 정보 설정
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 7. 15.
	 * @param 
	 */
	setUpperMenuInfo : function(menuInfo) {
		$jquery("#" + Menu.FORM_ID + " #upperMenuId").val(menuInfo.menuId);
		$jquery("#" + Menu.FORM_ID + " #upperMenuInfo").html(menuInfo.menuId + " (" + menuInfo.menuNm + ")");
	},
	
	/**
	 * 개요 : 프로그램 서브 리스트 삭제
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 10.
	 * @param thisObj
	 */
	deleteProgrmSubList : function(thisObj) {
		if ($jquery('#progrmSubListBody input[name="progrmChk"]:checked').length > 0) {
			$jquery('#progrmSubListBody input[name="progrmChk"]:checked').each(function() {
				var rowId = $jquery(this).attr('id');
				$jquery('#progrmSubListBody > #tr' + rowId).remove();
			});
			
			if ($jquery('#progrmSubListBody input[name="progrmChk"]').length <= 0) {
				var noDataHtml = '<tr id="noData">';
				noDataHtml += '<td colspan="6">해당 데이터가 없습니다.</td>';
				noDataHtml += '</tr>';
				
				$jquery('#progrmSubListBody').append(noDataHtml);
			}
		} else {
			alert('삭제할 메뉴을 선택하세요');
		}
	},
	
	/**
	 * 개요 : 메뉴 전체 선택/해제
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 9.
	 * @param thisObj
	 */
	progrmAllCheck : function(thisObj) {
		checkboxAllCheck($jquery("input[name=progrmChk]"), thisObj);
	},
	
	/**
	 * 개요 : 엑셀 다운로드
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2014. 12. 8.
	 * @param thisObj
	 */
	excelDownload : function(thisObj){
		if(Menu.TOTAL_CNT == 0){
			alert("해당 데이터가 없습니다");
		}else if(Menu.TOTAL_CNT > 10000){
			alert("1만건 이상 엑셀받기를 할 수 없습니다");
		}else{
			$jquery.growlUI("info", "엑셀 다운로드를 요청하였습니다.<br />잠시만 기다려주세요.");
			
			$jquery("#" + Menu.FORM_ID).attr("action", "downloadMenuExcel.tech");
			$jquery("#" + Menu.FORM_ID).submit();
		}
		
	}
};