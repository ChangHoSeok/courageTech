var AuthCommon = {
	_SEARCH_FLAG : undefined,
	_CREATE_FLAG : undefined,
	_CUSTOM_FLAG1 : undefined,
	_CUSTOM_FLAG2 : undefined,
	_CUSTOM_FLAG3 : undefined,
	
	/**
	 * 개요 : Client Side 권한 조회 (화면에 표시되는 버튼 및 기능을 위해 사용)
	 *        menuNo를 전달받지 못하는 페이지(Popup 페이지 등)는 progrmFileNm으로 조회
	 *        menuNo와 progrmFileNm 둘가 값이 존재할 수 있고, 각 하나의 매개변수에만 값이 존재할 수 있음
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 4. 26.
	 * @param 
	 */
	selectMenuAuthor : function (menuNo, progrmFileNm) {
		if (isEmpty(menuNo) && isEmpty(progrmFileNm)) {
			alert("권한정보 매개변수 설정이 잘못되었습니다.\n관리자에게 문의하시기 바랍니다.");
			return false;
		}
		
		var paramObj = {
			menuNo : menuNo,
			progrmFileNm : progrmFileNm
		};
		
		$.ajax({
			type	: "POST",
			url		: jsContextPath + "/system/auth/retrieveMenuAuthor.tech",
			data	: paramObj,
			dataType: "json",
			success	: function (returnObj) {
				if (returnObj != null) {
					AuthCommon._SEARCH_FLAG = returnObj.searchFlag;
					AuthCommon._CREATE_FLAG = returnObj.createFlag;
					AuthCommon._CUSTOM_FLAG1 = returnObj.customFlag1;
					AuthCommon._CUSTOM_FLAG2 = returnObj.customFlag2;
					AuthCommon._CUSTOM_FLAG3 = returnObj.customFlag3;
				} else {
					document.location.href = jsContextPath + "/error/errorAuth.do";
					return false;
				}
			},
			error	: function(x, e) {
				alert("권한정보 설정이 잘못되었습니다.\n관리자에게 문의하시기 바랍니다.");
			},
			async	: false
		});
	},
	
	isSearch : function() {
		if (AuthCommon._SEARCH_FLAG == "Y") {
			return true;
		} else {
			document.location.href = jsContextPath + "/error/errorAuth.do";
			return false;
		}
	},
	
	isCreate : function() {
		if (AuthCommon._CREATE_FLAG == "Y") {
			return true;
		} else {
			return false;
		}
	},
	
	isCustom1 : function() {
		if (AuthCommon._CUSTOM_FLAG1 == "Y") {
			return true;
		} else {
			return false;
		}
	},
	
	isCustom2 : function() {
		if (AuthCommon._CUSTOM_FLAG2 == "Y") {
			return true;
		} else {
			return false;
		}
	},
	
	isCustom3 : function() {
		if (AuthCommon._CUSTOM_FLAG3 == "Y") {
			return true;
		} else {
			return false;
		}
	}
};