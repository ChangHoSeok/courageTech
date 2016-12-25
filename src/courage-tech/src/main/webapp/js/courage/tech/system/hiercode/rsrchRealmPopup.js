var RsrchRealmResPopup = {
	DIALOG_ID : "dialog-form-formRsrchRealmPopup",
	RES_POPUP_FROM_ID : "formRsrchRealmPopup",
	/**
	 * 개요 : 과학기술 표준분류 연구 팝업화면 오픈
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 9.
	 * @param 팝업창 종류 후 실행될 callback 함수
	 */
	rsrchRealmDialogOpen : function(callback, objIndex) {
		RsrchRealmResPopup._selectEmpty();
		
		$jquery("#" + RsrchRealmResPopup.DIALOG_ID).dialog({
			autoOpen: false,
			height: 250,
			width: 450,
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
					if($jquery("#rsrchRealmHighLevel").val() != "" && $jquery("#rsrchRealmMiddleLevel").val() != "" && $jquery("#rsrchRealmLowLevel").val() != ""){
						var rsrchObj = {
								rsrchHigh     : $jquery("#rsrchRealmHighLevel").val(),
								rsrchHighNm   : $jquery("#rsrchRealmHighLevel option:selected").text(),
								rsrchMiddle   : $jquery("#rsrchRealmMiddleLevel").val(),
								rsrchMiddleNm : $jquery("#rsrchRealmMiddleLevel option:selected").text(),
								rsrchLow      : $jquery("#rsrchRealmLowLevel").val(),
								rsrchLowNm    : $jquery("#rsrchRealmLowLevel option:selected").text()
						};
						setRsrchRealm(rsrchObj, objIndex);
						$jquery("#" + RsrchRealmResPopup.DIALOG_ID).dialog("close");
					}else{
						$jquery.alert("과학기술 표준분류 적용분야를 선택해 주세요.");
					}
				},
				"닫기": function() {
					$jquery("#" + RsrchRealmResPopup.DIALOG_ID).dialog("close");
				}
			},
			close: function(event, ui) {
				if ($jquery.isFunction(callback)) {
					callback.call();
				}
				
				$jquery("#" + RsrchRealmResPopup.DIALOG_ID).dialog("destroy");
			}
		});
		
		$jquery("#" + RsrchRealmResPopup.DIALOG_ID).dialog("open");
	},
	/**
	 * 개요 : selectbox 초기화
	 * 
	 * @Author : KYW
	 * @Date : 2013. 11. 22.
	 * @param 
	 */
	
	_selectEmpty : function(){
		$jquery("#rsrchRealmHighLevel").val("");
		
		$jquery("#rsrchRealmMiddleLevel").empty();
		$jquery("#rsrchRealmMiddleLevel").append($jquery('<option>', {value : '', text : '선택'}));
		
		$jquery("#rsrchRealmLowLevel").empty();
		$jquery("#rsrchRealmLowLevel").append($jquery('<option>', {value : '', text : '선택'}));
	},
	
	rsrchRealmOnChange : function(){
		$jquery("#rsrchRealmMiddleLevel").empty();
		$jquery("#rsrchRealmMiddleLevel").append($jquery('<option>', {value : '', text : '선택'}));
		$jquery("#rsrchRealmLowLevel").empty();
		$jquery("#rsrchRealmLowLevel").append($jquery('<option>', {value : '', text : '선택'}));
	
		if($jquery("#rsrchRealmHighLevel").val() != ""){
			var paramObj = {
					upperCode : $jquery("#rsrchRealmHighLevel").val()
			};
			RsrchRealmPopupService.selectRsrchRealmLevelList(paramObj, {
				callback : function (returnObj) {
					if (returnObj !== null) {
					
						for(var i = 0 ; i < returnObj.length ; i++){
							$jquery("#rsrchRealmMiddleLevel").append($jquery('<option>', {value : returnObj[i].code, text : returnObj[i].codeNm}));
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
	},
	rsrchRealmMiddleOnChange : function(){
		$jquery("#rsrchRealmLowLevel").empty();
		$jquery("#rsrchRealmLowLevel").append($jquery('<option>', {value : '', text : '선택'}));

		if($jquery("#rsrchRealmMiddleLevel").val() != ""){
			var paramObj = {
					upperCode : $jquery("#rsrchRealmMiddleLevel").val()
			};
			RsrchRealmPopupService.selectRsrchRealmLevelList(paramObj, {
				callback : function (returnObj) {
					if (returnObj !== null) {

						for(var i = 0 ; i < returnObj.length ; i++){
							$jquery("#rsrchRealmLowLevel").append($jquery('<option>', {value : returnObj[i].code, text : returnObj[i].codeNm}));
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
