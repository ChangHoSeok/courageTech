var Board = {
	MENU_NO : "",
	FORM_ID : "formBoard",
	LIST_ID : "retrieveBoardList",
	DETAIL_ID : "retrieveBoardDetail",
	CREATE_ID : "createBoard",
	MODIFY_ID : "modifyBoard",
	PAGE_INDEX_ID : "pageIndex",
	ACTION_STATUS : "",
	TOTAL_CNT : 0,
	USE_AT : "",
	atchFile : {
		uploadFlag : false,
		hasError : false
	},
	mvpFile : {
		uploadFlag : false,
		hasError : false
	},
	
	/**
	 * 개요 : 목록 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 23.
	 * @param 
	 */
	formInitList : function() {
		$("#menuNavi").html(Navi.getNaviNm(Board.MENU_NO));
	    
		// 해당 메뉴의 권한정보 조회
		AuthCommon.selectMenuAuthor(Board.MENU_NO);
		
		if (!AuthCommon.isCreate()) {
			$('.needCreate').remove();
		}
		
		$("#" + Board.FORM_ID).separator('separatorAddForm');
	},
	
	/**
	 * 개요 : 등록 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 23.
	 * @param 
	 */
	formInitCreate : function() {
		$("#" + Board.FORM_ID).separator('separatorAddForm');
		
		$("#menuNavi").html(Navi.getNaviNm(Board.MENU_NO));
		
		var ckeditorInstance = CKEDITOR.instances['nttCn'];
		if (ckeditorInstance) {
			ckeditorInstance.destroy(true);
		}
		
		CKEDITOR.replace('nttCn', {
			height : 380
		});
		
		CKEDITOR.plugins.registered['save'] = {
			init: function (editor) {
				var command = editor.addCommand('save', {
			          modes: { wysiwyg: 1, source: 1 },
			          exec: function (editor) { // Add here custom function for the save button
			        	  Board.save();
			          }
				});
				
				editor.ui.addButton('Save', { label: 'Save', command: 'save' });
			}
		}
	},

	/**
	 * 개요 : 상세 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 23.
	 * @param 
	 */
	formInitDetail : function() {
		$("#menuNavi").html(Navi.getNaviNm(Board.MENU_NO));
		
		// 해당 메뉴의 권한정보 조회
		AuthCommon.selectMenuAuthor(Board.MENU_NO);
		
		if (!AuthCommon.isCreate()) {
			$('.needCreate').remove();
		}
		
		$("#" + Board.FORM_ID).separator('separatorAddForm');
		
		if (Board.USE_AT === "Y") {
			$("#boardContentsForm").submit();
		}
		
		// 댓글 조회
		if ($("#answerArea").length > 0) {
			$("#answerArea").ajaxload(
				"blockLoad",
				jsContextPath + "/cop/bbs/formAnswer.tech",
				"POST",
				"html",
				$("#" + Board.FORM_ID).separator("separatorRemoveForm").serialize(),
				false
			);
		}
	},
	
	/**
	 * 개요 : 게시물 컨텐츠 초기화
	 * 
	 * @Author : schkk
	 * @Date : 2016. 11. 13.
	 * @param 
	 */
	formInitContent : function() {
		// 해당 메뉴의 권한정보 조회
		AuthCommon.selectMenuAuthor(Board.MENU_NO);
		
		if (!AuthCommon.isCreate()) {
			$('.needCreate').remove();
		}
		
		$("#" + Board.FORM_ID).separator('separatorAddForm');
		
		if (Board.USE_AT === "Y") {
			$("#boardContentsForm").submit();
		}
	},
	
	fn_egov_link_page : function(pageIndex) {
		Board.setPageIndex(pageIndex);
		Board.refreshList();
	},
	
	/**
	 * 개요 : 페이지 인덱스 설정
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 23.
	 * @param pageIndex
	 */
	setPageIndex : function(pageIndex) {
		$("#" + Board.FORM_ID + " #" + Board.PAGE_INDEX_ID).val(pageIndex);
	},

	/**
	 * 개요 : 목록 새로고침
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 23.
	 * @param thisObj
	 */
	refreshList : function(thisObj) {
		location.hash = "AC=/cop/bbs/retrieveBoardList.tech&VA=content_body&" + $("#" + Board.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 목록조회
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 23.
	 * @param thisObj
	 */
	search : function(thisObj) {
		Board.setPageIndex(1);
		Board.refreshList(thisObj);
	},

	/**
	 * 개요 : 상세조회 페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 23.
	 * @param thisObj
	 */
	detailView : function(thisObj) {
		$("#" + Board.FORM_ID + " #nttId").val($(thisObj).prop("id"));
		location.hash = "AC=/cop/bbs/retrieveBoardDetail.tech&VA=content_body&" + $("#" + Board.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 목록조회 페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 23.
	 * @param thisObj
	 */
	listView : function(thisObj) {
		location.hash = "AC=/cop/bbs/retrieveBoardList.tech&VA=content_body&" + $("#" + Board.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 등록 페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 23.
	 * @param thisObj
	 */
	createView : function(thisObj) {
		$("#" + Board.FORM_ID + " #nttId").val("0");
		location.hash = "AC=/cop/bbs/createBoard.tech&VA=content_body&" + $("#" + Board.FORM_ID).separator("separatorRemoveForm").serialize();
	},
	
	/**
	 * 개요 : 답글 페이지 이동
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 6. 21.
	 * @param 
	 */
	replyView : function(thisObj) {
		location.hash = "AC=/cop/bbs/createBoard.tech&VA=content_body&" + $("#" + Board.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 수정페이지 이동
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 23.
	 * @param thisObj
	 */
	modifyView : function(thisObj) {
		location.hash = "AC=/cop/bbs/modifyBoard.tech&VA=content_body&" + $("#" + Board.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 수정취소
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 23.
	 * @param thisObj
	 */
	modifyCancel : function(thisObj) {
		location.hash = "AC=/cop/bbs/retrieveBoardDetail.tech&VA=content_body&" + $("#" + Board.FORM_ID).separator("separatorRemoveForm").serialize();
	},

	/**
	 * 개요 : 저장
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 23.
	 * @param thisObj
	 */
	save : function(thisObj) {
		if (Board._formValidate()) {
			var isFileExists = false;
			
			// 첨부파일 업로드 오류 여부 확인
			if ($("#" + Board.FORM_ID + " .uploadifyError").length > 0) {
				alert("첨부파일 업로드 오류 파일 삭제 후 저장하세요.");
				return false;
			}
			
			if (confirm("저장하시겠습니까?")) {
				Board.atchFile.hasError = false;
				Board.mvpFile.hasError = false;
				
				$("#" + Board.FORM_ID + " .buttonSet").hide();
				
				// 첨부파일 업로드
				if($("#" + Board.FORM_ID + " #uploadifyAtchQueue > .uploadifyQueueItem").length > 0) {
					$("#" + Board.FORM_ID + " #uploadifyAtch").uploadifyUpload();
					isFileExists = true;
				} else {
					Board.atchFile.uploadFlag = true;
				}
				
				// 동영상 업로드
				if($("#" + Board.FORM_ID + " #uploadifyMvpQueue > .uploadifyQueueItem").length > 0) {
					$("#" + Board.FORM_ID + " #uploadifyMvp").uploadifyUpload();
					isFileExists = true;
				} else {
					Board.mvpFile.uploadFlag = true;
				}
				
				if (!isFileExists) {
					Board.saveProc();
				}
			}
		}
	},
	
	/**
	 * 개요 : 입력항목 유효성 검사
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 23.
	 * @param thisObj
	 */
	_formValidate : function() {
		if (isEmpty(CKEDITOR.instances['nttCn'].getData().trim())) {
			alert("내용을 입력하세요.");
			return false;
		}
		
		var noticeBgnDe = $("#" + Board.FORM_ID + " #noticeBgnDe").val();
		
		if (!isEmpty(noticeBgnDe)) {
			$("#" + Board.FORM_ID + " #noticeEndDe").removeClassRegEx("validate");
			$("#" + Board.FORM_ID + " #noticeEndDe").addClass("validate[required,future[" + noticeBgnDe + "]]");
		}
		
		if ($("#" + Board.FORM_ID).validationEngine("CLFValidate")) {
			return true;
		} else {
			$.growlUI("warn", "입력값을 확인하세요");
			return false;
		}
	},

	/**
	 * 개요 : 저장처리
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 23.
	 * @param thisObj
	 */
	saveProc : function() {
		var url = "/cop/bbs/createBoardProc.tech";
		var mode = $("#" + Board.FORM_ID + " #mode").val();
		
		if (mode == "modify") {
			url = "/cop/bbs/modifyBoardProc.tech";
		}
		
		var noticeAtObj = $("#" + Board.FORM_ID + " #noticeAt");
		if ($(noticeAtObj).length > 0) {
			if ($(noticeAtObj).prop("checked")) {
				$(noticeAtObj).val("Y");
			} else {
				$(noticeAtObj).val("N");
			}
			
			$(noticeAtObj).prop("checked", true);
		}
		
		$('#nttCn').val(CKEDITOR.instances['nttCn'].getData());
		$('#nttCnText').val(CKEDITOR.instances['nttCn'].document.getBody().getText());
		
		$("#content_body").ajaxload(
			"blockLoad",
			jsContextPath + url,
			"POST",
			"html",
			$("#" + Board.FORM_ID).separator("separatorRemoveForm").serialize(),
			false
		);
	},
	
	/**
	 * 개요 : 프로그램 삭제처리 (단일정보)
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2016. 5. 23.
	 * @param thisObj
	 */
	deleteBoard : function(thisObj) {
		if (confirm("삭제하시겠습니까?")) {
			location.hash = "AC=/cop/bbs/deleteBoard.tech&VA=content_body&" + $("#" + Board.FORM_ID).separator("separatorRemoveForm").serialize();
		}
	}
};