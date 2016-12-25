var ApplcRealmPopup = {
	DIALOG_ID : "dialog-form-formApplcRealmPopup",
	APP_POPUP_FROM_ID : "formApplcRealmPopup",
	/**
	 * 개요 : 과학기술 표준분류 적용 팝업화면 오픈
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 9.
	 * @param 팝업창 종류 후 실행될 callback 함수
	 */
	applcRealmDialogOpen : function(callback, objIndex) {
		ApplcRealmPopup._selectEmpty();
		$jquery("#" + ApplcRealmPopup.DIALOG_ID).dialog({
			autoOpen: false,
			height: 200,
			width: 500,
			modal: true,
			resizable: false,
			draggable: false,
			position: {
				my: "top",
				at: "top",
				of: "#container"
			},
			buttons: {
				"선택": function() {
					if($jquery("#applcRealmHighLevel").val() != "" && $jquery("#applcRealmMiddleLevel").val() != ""){
						var applcObj = {
								applcHigh     : $jquery("#applcRealmHighLevel").val(),
								applcHighNm   : $jquery("#applcRealmHighLevel option:selected").text(),
								applcMiddle   : $jquery("#applcRealmMiddleLevel").val(),
								applcMiddleNm : $jquery("#applcRealmMiddleLevel option:selected").text()
						};
						setApplcRealm(applcObj, objIndex);
						$jquery("#" + ApplcRealmPopup.DIALOG_ID).dialog("close");
					}else{
						$jquery.alert("과학기술 표준분류 적용분야를 선택해 주세요.");
					}
				},
				"닫기": function() {
					$jquery("#" + ApplcRealmPopup.DIALOG_ID).dialog("close");
				}
			},
			close: function(event, ui) {
				if ($jquery.isFunction(callback)) {
					callback.call();
				}
				
				$jquery("#" + ApplcRealmPopup.DIALOG_ID).dialog("destroy");
			}
		});
		
		$jquery("#" + ApplcRealmPopup.DIALOG_ID).dialog("open");
	},
	
	/**
	 * 개요 : selectbox 초기화
	 * 
	 * @Author : KYW
	 * @Date : 2013. 11. 22.
	 * @param 
	 */
	_selectEmpty : function(){
		$jquery("#applcRealmHighLevel").val("");
		$jquery("#applcRealmMiddleLevel").empty();
		$jquery("#applcRealmMiddleLevel").append($jquery('<option>', {value : '', text : '선택'}));
	},

	applcRealmOnChange : function(){
		$jquery("#applcRealmMiddleLevel").empty();
		$jquery("#applcRealmMiddleLevel").append($jquery('<option>', {value : '', text : '선택'}));

		if($jquery("#applcRealmHighLevel").val() != ""){
			var paramObj = {
					upperCode : $jquery("#applcRealmHighLevel").val()
			};
			ApplcRealmPopupService.selectApplcRealmMiddleLevelList(paramObj, {
				callback : function (returnObj) {
					if (returnObj !== null) {
						
						
						for(var i = 0 ; i < returnObj.length ; i++){
							$jquery("#applcRealmMiddleLevel").append($jquery('<option>', {value : returnObj[i].code, text : returnObj[i].codeNm}));
						}
					} else {
						$jquery.alert('중분류 정보가 없습니다.');
					}
				},
				errorHandler : function(errorString, exception) {
					$jquery.alert('오류가 발생되었습니다. <br />' + errorString);
				}
			});
		}
	}
};
