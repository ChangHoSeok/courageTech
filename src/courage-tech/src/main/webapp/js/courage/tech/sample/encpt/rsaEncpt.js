var RSAEncpt = {
	MENU_NO : "",
	FORM_ID : "formRSAEncpt",
	CREATE_ID : "createRSAEncpt",
	DETAIL_ID : "retrieveRSAEncptDetail",
	PAGE_INDEX_ID : "pageIndex",
	ACTION_STATUS : "",
	TOTAL_CNT : 0,
	
	/**
	 * 개요 : 목록 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 18.
	 * @param 
	 */
	formInitCreate : function() {
		$jquery("#" + RSAEncpt.FORM_ID).separator('separatorAddForm');
		
		$jquery("#progrmNm").html(Navi.getProgrmNm(RSAEncpt.CREATE_ID));
		$jquery("#menuNavi").html(Navi.getNaviNm(RSAEncpt.MENU_NO));
		
		$jquery("#" + RSAEncpt.FORM_ID + " #data1").byteChk("keyup", 3, 85);
		
		showBtnAccessKey();
		
		alert("암/복호화 테스트 중단!!\nIE에서 암호화시 속도가 너무너무 느림.");
	},
	
	/**
	 * 개요 : 상세조회 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 21.
	 * @param 
	 */
	formInitDetail : function() {
		$jquery("#progrmNm").html(Navi.getProgrmNm(RSAEncpt.DETAIL_ID));
		$jquery("#menuNavi").html(Navi.getNaviNm(RSAEncpt.MENU_NO));
		
		showBtnAccessKey();
	},
	
	/**
	 * 개요 : 서버 전송
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 7. 21.
	 * @param 
	 */
	submit : function(thisObj) {
		$jquery("#content_body").ajaxload(
			"blockLoad",
			jsContextPath + "/sample/encpt/retrieveRSAEncptDetail.tech",
			"POST",
			"html",
			$jquery("#" + RSAEncpt.FORM_ID).separator("separatorRemoveForm").serialize(),
			false
		);
	},
	
	back : function(thisObj) {
		location.hash = "AC=/sample/encpt/createRSAEncpt.tech&VA=content_body&" + $jquery("#" + RSAEncpt.FORM_ID).separator("separatorRemoveForm").serialize();
	},
	
	/**
	 * 개요 : 평문 암호화 (공개키 사용)
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 7. 20.
	 * @param 
	 */
	encrypt : function(thisObj) {
		var encrypt = new JSEncrypt();
		var data = $jquery("#data1").val();
		
		if (data !== "") {
			var cipherText = encrypt.encrypt(data);
			$jquery("#data2").val(cipherText);
		} else {
			$jquery("#data2").val("");
		}
	}
};