var Navi = {
	/**
	 * 개요 : 메뉴 네비게이션 설정
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2015. 1. 8.
	 * @param menuNo 메뉴번호
	 */
	getNaviNm : function (menuNo) {
		var resultNavi = "";
		var paramObj = {
			menuNo : menuNo
		};
		
		naviService.selectMenuNaviList(paramObj, {
			callback : function (returnObj) {
				if (returnObj.length > 0) {
					
					for (var i = 0; i < returnObj.length; i++) {
						if (resultNavi != "") {
							resultNavi += "&nbsp;&gt;&nbsp;";
						}
						
						resultNavi += returnObj[i].menuNm;
					}
				}
			},
			errorHandler : function(errorString, exception) {
				alert('오류가 발생되었습니다.\r\n');
			},
			async : false
		});
		
		return resultNavi;
	},
	
	/**
	 * 개요 : 프로그램 명 설정
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2015. 1. 8.
	 * @param progrmId 프로그램 아이디
	 */
	getProgrmNm : function (progrmId) {
		var progrmNm = "";
		var paramObj = {
			progrmFileNm : progrmId
		};
		
		progrmService.selectProgrmNm(paramObj, {
			callback : function (returnObj) {
				progrmNm = returnObj;
			},
			errorHandler : function(errorString, exception) {
				alert('오류가 발생되었습니다.\r\n');
			},
			async : false
		});
		
		return progrmNm;
	}
};