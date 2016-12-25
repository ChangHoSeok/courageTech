var NtrmPopup = {
	DIALOG_ID : "dialog-form-formNtrmPopup",
	NTRM_POPUP_FROM_ID : "formNtrmPopup",
	/**
	 * 개요 : NRTM 분류 팝업화면 오픈
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 9.
	 * @param 팝업창 종류 후 실행될 callback 함수
	 */
	ntrmDialogOpen : function(callback) {
		NtrmPopup._selectEmpty();
		$jquery("#" + NtrmPopup.DIALOG_ID).dialog({
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
					if($jquery("#ntrmHighLevel").val() != "" && $jquery("#ntrmMiddleLevel").val() != ""){
						var ntrmObj = {
								ntrmHigh     : $jquery("#ntrmHighLevel").val(),
								ntrmHighNm   : $jquery("#ntrmHighLevel option:selected").text(),
								ntrmMiddle   : $jquery("#ntrmMiddleLevel").val(),
								ntrmMiddleNm : $jquery("#ntrmMiddleLevel option:selected").text()
						};
						setNtrm(ntrmObj);
						$jquery("#" + NtrmPopup.DIALOG_ID).dialog("close");
					}else{
						$jquery.alert("NTRM 분류를 선택해 주세요.");
					}
				},
				"닫기": function() {
					$jquery("#" + NtrmPopup.DIALOG_ID).dialog("close");
				}
			},
			close: function(event, ui) {
				if ($jquery.isFunction(callback)) {
					callback.call();
				}
				
				$jquery("#" + NtrmPopup.DIALOG_ID).dialog("destroy");
			}
		});
		
		$jquery("#" + NtrmPopup.DIALOG_ID).dialog("open");
	},
	
	/**
	 * 개요 : selectbox 초기화
	 * 
	 * @Author : KYW
	 * @Date : 2013. 11. 22.
	 * @param 
	 */
	_selectEmpty : function(){
		$jquery("#ntrmHighLevel").val("");
		$jquery("#ntrmMiddleLevel").empty();
		$jquery("#ntrmMiddleLevel").append($jquery('<option>', {value : '', text : '선택'}));
	},
	
	ntrmOnChange : function(){
		$jquery("#ntrmMiddleLevel").empty();
		$jquery("#ntrmMiddleLevel").append($jquery('<option>', {value : '', text : '선택'}));

		if($jquery("#ntrmHighLevel").val() != ""){
			var paramObj = {
					upperCode : $jquery("#ntrmHighLevel").val()
			};
			NtrmPopupService.selectNtrmMiddleLevelList(paramObj, {
				callback : function (returnObj) {
					if (returnObj !== null) {
						
						
						for(var i = 0 ; i < returnObj.length ; i++){
							$jquery("#ntrmMiddleLevel").append($jquery('<option>', {value : returnObj[i].code, text : returnObj[i].codeNm}));
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
