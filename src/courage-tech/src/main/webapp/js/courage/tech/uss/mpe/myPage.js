var MyPage = {
	AVATAR_ICON_ELEMENT : undefined,
	
	/**
	 * 개요 : avatar 팝업창 초기화
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 11. 22.
	 * @param 
	 */
	avatarInit : function(){
		$jquery("#dialog-userIcons").dialog({
			autoOpen: false,
			resizable: false,
			draggable: false,
			modal: true,
			title: '사용자 아이콘 설정',
			width: 470,
			height: 350,
			position: {
				my: "center top+30",
				at: "center top",
				of: window
			},
			buttons: {
				'닫기': function() {
					$jquery(this).dialog('close');
				}
			}
		});
	},
	
	/**
	 * 개요 : 사용자 지정 아이콘 설정 팝업
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 10. 21.
	 * @param 
	 */
	showAvatar : function(thisObj) {
		$jquery("#dialog-userIcons").ajaxload(
			"blockLoad",
			jsContextPath + "/uss/mpe/retrieveAvatarList.tech",
			"POST",
			"html",
			"",
			false
		);
		
		$jquery("#dialog-userIcons").dialog("open");
		
		MyPage.AVATAR_ICON_ELEMENT = $jquery(thisObj).find("img");
	},
	
	/**
	 * 개요 : 사용자 아이콘 설정
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 10. 23.
	 * @param 
	 */
	setAvatarIcon : function(thisObj) {
		if (MyPage.AVATAR_ICON_ELEMENT === undefined) {
			alert("사용자 지정 아이콘을 설정할 수 없습니다.")
			return;
		}
		
		if (confirm("선택한 아이콘으로 설정하시겠습니까?")) {
			$jquery.ajax({
				type	: "POST",
				url		: jsContextPath + "/uss/mpe/modifyAvatarProc.tech",
				data	: {"iconPath" : $jquery(thisObj).find("img").prop("src")},
				dataType: "json",
				success	: function (data) {
					if (data.actionMessage === "success") {
						MyPage.AVATAR_ICON_ELEMENT.prop("src", $jquery(thisObj).find("img").prop("src"));
						$jquery("#dialog-userIcons").dialog("close");
					}
				},
				error	: function(x, e) {
					alert("오류가 발생되었습니다.");
				}
			});
		}
	}
};