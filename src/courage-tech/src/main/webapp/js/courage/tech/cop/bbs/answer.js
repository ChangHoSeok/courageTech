var Answer = {
	MENU_NO : "",
	FORM_ID : "formAnswer",
	LIST_ID : "retrieveAnswerList",
	CREATE_ID : "createAnswer",
	MODIFY_ID : "modifyAnswer",
	PAGE_INDEX_ID : "pageIndex",
	ACTION_STATUS : "",
	TOTAL_CNT : 0,
	USE_AT : "",
	
	/**
	 * 개요 : 메인 Form 초기화
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 7. 6.
	 * @param 
	 */
	formInit : function () {
		// 댓글 목록 조회
		Answer.refreshList();
		
		// 댓글 등록 화면 조회
		$jquery("#answerCreateForm").ajaxload(
			"blockLoad",
			jsContextPath + "/cop/bbs/createAnswer.tech",
			"POST",
			"html",
			"",
			true
		);
	},
	
	/**
	 * 개요 : 목록 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 6. 29.
	 * @param 
	 */
	formInitList : function() {
		showBtnAccessKey();		
	},
	
	/**
	 * 개요 : 등록화면 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 6. 29.
	 * @param 
	 */
	formInitCreate : function() {
		$jquery("#" + Answer.FORM_ID + " #answerCn").byteChk("keyup", 2, 1000);
		$jquery("#" + Answer.FORM_ID).separator('separatorAddForm');
		
		showBtnAccessKey();
		
		if (Answer.ACTION_STATUS == "success") {
			$jquery.growlUI("info", "저장되었습니다.");
		}
	},
	
	fn_egov_link_page : function(pageIndex) {
		Answer.setPageIndex(pageIndex);
		Answer.refreshList();
	},
	
	/**
	 * 개요 : 페이지 인덱스 설정
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 6. 29.
	 * @param pageIndex
	 */
	setPageIndex : function(pageIndex) {
		$jquery("#" + Answer.FORM_ID + " #" + Answer.PAGE_INDEX_ID).val(pageIndex);
	},

	/**
	 * 개요 : 목록 새로고침
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 6. 29.
	 * @param thisObj
	 */
	refreshList : function(thisObj) {
		$jquery("#answerList").ajaxload(
			"blockLoad",
			jsContextPath + "/cop/bbs/retrieveAnswerList.tech",
			"POST",
			"html",
			"",
			true
		);
	},

	/**
	 * 개요 : 수정페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 6. 29.
	 * @param thisObj
	 */
	modifyView : function(thisObj) {
		$jquery("#answerCreateForm").ajaxload(
			"blockLoad",
			jsContextPath + "/cop/bbs/modifyAnswer.tech",
			"POST",
			"html",
			"answerNo=" + $jquery(thisObj).attr("id").split("_")[1],
			true
		);
		
		// 스크롤 다운
		$jquery("#table_list").scrollTop($jquery("#table_list")[0].scrollHeight);
	},

	/**
	 * 개요 : 수정취소
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 6. 29.
	 * @param thisObj
	 */
	modifyCancel : function(thisObj) {
		if (confirm("댓글 수정을 취소하시겠습니까?")) {
			$jquery("#answerCreateForm").ajaxload(
				"blockLoad",
				jsContextPath + "/cop/bbs/createAnswer.tech",
				"POST",
				"html",
				"",
				true
			);
		}
	},

	/**
	 * 개요 : 저장
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 6. 29.
	 * @param thisObj
	 */
	save : function(thisObj) {
		if (Answer._formValidate()) {
			if (confirm("저장하시겠습니까?")) {
				Answer.saveProc();
			}
		}
	},
	
	/**
	 * 개요 : 입력항목 유효성 검사
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 6. 29.
	 * @param thisObj
	 */
	_formValidate : function() {
		if ($jquery("#" + Answer.FORM_ID).validationEngine("CLFValidate")) {
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
	 * @Date : 2016. 6. 29.
	 * @param thisObj
	 */
	saveProc : function() {
		var url = "/cop/bbs/createAnswerProc.tech";
		var mode = $jquery("#" + Answer.FORM_ID + " #mode").val();
		
		if (mode == "modify") {
			url = "/cop/bbs/modifyAnswerProc.tech";
		}
		
		$jquery("#answerCreateForm").ajaxload(
			"blockLoad",
			jsContextPath + url,
			"POST",
			"html",
			$jquery("#" + Answer.FORM_ID).separator("separatorRemoveForm").serialize(),
			false
		);
		
		if (Answer.ACTION_STATUS == "success") {
			// 목록 재조회
			Answer.refreshList();
		}
	},
	
	/**
	 * 개요 : 프로그램 삭제처리 (단일정보)
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 6. 29.
	 * @param thisObj
	 */
	deleteAnswer : function(thisObj) {
		var answerNo = $jquery(thisObj).attr("id").split("_")[1];
		
		if (confirm("삭제하시겠습니까?")) {
			// 삭제대상 글이 수정 중 상태의 경우 초기화
			if ($jquery("#" + Answer.FORM_ID + " #answerNo").val() == answerNo) {
				$jquery("#answerCreateForm").ajaxload(
					"blockLoad",
					jsContextPath + "/cop/bbs/createAnswer.tech",
					"POST",
					"html",
					"",
					true
				);
			}
			
			$jquery.ajax({
				type	: "POST",
				url		: jsContextPath + "/cop/bbs/deleteAnswer.tech",
				data	: {"answerNo" : answerNo},
				dataType: "json",
				success	: function (data) {
					if (data.actionStatus == "success") {
						$jquery.growlUI("info", "삭제되었습니다.");
						$jquery("#answerList #" + answerNo).hide(500, function() {
							if ($jquery("#answerList tr:visible").length <= 0) {
								var noDataTr = $jquery("<tr>").append('<td class="Ctxt" style="border: 0">등록된 댓글이 없습니다.</td>');
								$jquery("#answerList table").append(noDataTr);
								
								$jquery("#answerCnt").html("");
							} else {
								$jquery("#answerCnt").html($jquery("#answerList tr:visible").length);
							}
						});
					} else {
						alert("삭제처리를 정상적으로 완료하지 못했습니다.");
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