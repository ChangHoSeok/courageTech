var CodeExcelUpload = {
	FORM_ID : "formCodeExcelUpload",
	DIALOG_CODE_EXCEL : "dialog-form-codeExcelUpload",
	_codeFlag : "",
	_uploadifyErrorFlag : false,
	
	/**
	 * 개요 : 공통코드 엑셀등록 팝업창 오픈 설정
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 19.
	 * @param thisObj
	 */
	codeExcelUploadDialogOpen : function(thisObj) {
		CodeExcelUpload._dialogInit();
		
		CodeExcelUpload._codeFlag = "code";
		CodeExcelUpload._dialogOpen();
		$jquery("#" + CodeExcelUpload.DIALOG_CODE_EXCEL).dialog({title: "공통코드 엑셀등록"});
	},
	
	/**
	 * 개요 : 공통코드 엑셀등록 팝업창 오픈 설정
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 19.
	 * @param thisObj
	 */
	detailCodeExcelUploadDialogOpen : function(thisObj) {
		CodeExcelUpload._dialogInit();
		
		CodeExcelUpload._codeFlag = "detailCode";
		CodeExcelUpload._dialogOpen();
		$jquery("#" + CodeExcelUpload.DIALOG_CODE_EXCEL).dialog({title: "공통 상세코드 엑셀등록"});
	},
	
	/**
	 * 개요 : 공통코드 엑셀등록 팝업창 오픈
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 19.
	 * @param thisObj
	 */
	_dialogOpen : function() {
		$jquery("#" + CodeExcelUpload.DIALOG_CODE_EXCEL).dialog({
			autoOpen: false,
			height: 380,
			width: 550,
			modal: true,
			resizable: false,
			draggable: false,
			position: {
				my: "top",
				at: "top",
				of: "#container"
			},
			buttons: {
				"등록": function() {
					CodeExcelUpload.excelUpload();
				},
				"닫기": function() {
					$jquery("#" + CodeExcelUpload.DIALOG_CODE_EXCEL).dialog("close");
				}
			},
			close: function(event, ui) {
				$jquery("#" + CodeExcelUpload.DIALOG_CODE_EXCEL).dialog("destroy");
			}
		});
		
		$jquery("#" + CodeExcelUpload.DIALOG_CODE_EXCEL).dialog("open");
	},
	
	/**
	 * 개요 : 공통코드 엑셀등록 팝업창 초기화
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 19.
	 * @param thisObj
	 */
	_dialogInit : function() {
		CodeExcelUpload._uploadifyErrorFlag = false;
		CodeExcelUpload._codeFlag = "";
		
		$jquery('#' + CodeExcelUpload.FORM_ID + ' #uploadFile').empty();
		$jquery('#' + CodeExcelUpload.FORM_ID + ' input[name=excelUploadFile]').remove();
		$jquery('#deleteFlag').prop('checked', false);
		$jquery('#' + CodeExcelUpload.FORM_ID + ' #uploadifyQueue').empty();
	},
	
	/**
	 * 개요 : 공통코드 엑셀등록 이벤트 처리
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 20.
	 */
	excelUpload : function() {
		if ($jquery('#' + CodeExcelUpload.FORM_ID + ' .uploadifyError').length > 0) {
			$jquery.alert('오류파일 삭제 후 저장하세요');
			return false;
		}
		
		if (CodeExcelUpload._formValidate()) {
			$jquery("#dialog-confirm").html('<p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;" />저장하시겠습니까?</p>');
			$jquery("#dialog-confirm").dialog({
				width:350,
				buttons: {
					'아니오': function() {
						$jquery(this).dialog('close');
					},
					'예': function() {
						$jquery(this).dialog('close');
						
						CodeExcelUpload._uploadifyErrorFlag = false;
						$jquery('#uploadify').uploadifyUpload();
					}
				}
			});
			$jquery("#dialog-confirm").dialog('open');
		}
	},
	
	/**
	 * 개요 : 공통코드 엑셀등록 처리
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 20.
	 */
	excelUploadProc : function() {
		var url;
		
		if (CodeExcelUpload._codeFlag == "code") {
			url = '/system/code/createCmmnCodeExcelUpload.tech';
		} else {
			url = '/system/code/createCmmnDetailCodeExcelUpload.tech';
		}
		
		$jquery.ajax({
			type		: "POST",
			url			: jsContextPath + url,
			data		: $jquery("#" + CodeExcelUpload.FORM_ID).separator('separatorRemoveForm').serialize(),
			dataType	: "html",
			success		: function(data) {
				var actionStatus = $jquery(data).find('#actionStatus').val();
				
				if (actionStatus == "success") {
					
				} else if (actionStatus == "failed") {
					
				} else if (actionStatus == "validatorError") {
					
				}
			},
			error		: function(x, e) {
				if (x.status == 0) {
					$jquery.alert('서버와 통신할 수 없습니다.<br />네트워크 설정을 확인하여 주시기 바랍니다.');
				} else if (x.status == 404 || x.status == 500) {
					$jquery.alert('오류가 발생되었습니다.<br />관리자에게 문의하여 주세요.');
				} else if (e == 'timeout') {
					$jquery.alert('일시적인 장애가 발생되었습니다.<br />재시도해 주세요.');
				} else {
					$jquery.alert('알수없는 오류가 발생되었습니다.' + x.responseText);
				}
			}
		});
	},
	
	/**
	 * 개요 : 공통코드 엑셀등록 폼 유효성 검사
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 20.
	 */
	_formValidate : function() {
		if ($jquery('#' + CodeExcelUpload.FORM_ID + ' #uploadifyQueue > .uploadifyQueueItem').length <= 0) {
			$jquery.alert('엑셀파일을 선택하세요.');
			return false;
		}
		
		return true;
	}
};