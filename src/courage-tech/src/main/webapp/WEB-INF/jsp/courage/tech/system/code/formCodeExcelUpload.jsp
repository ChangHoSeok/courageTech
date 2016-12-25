<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function () {
		// Dialog 초기 설정
		
		// 파일 업로드 설정
		$jquery("#uploadify").uploadify({
			'uploader'       	: '${pageContext.request.contextPath}/resource/swf/uploader/uploadify.swf',
			'script'         	: '${pageContext.request.contextPath}/uploadify',
			'cancelImg'      	: '${pageContext.request.contextPath}/images/common/btn/cancel.png',
			'queueSizeLimit' 	: 1,
			'sizeLimit'      	: 1000 * 1024 * 1024,
			'simUploadLimit' 	: 1,
			'auto'           	: false,
			'multi'				: true,
			'buttonImg'			: '${pageContext.request.contextPath}/images/common/btn/btn_file.gif',
			'method'			: 'POST',
			'displayData'		: 'speed',
			'fileDesc'			: 'xls',
			'fileExt'			: '*.xls',
			onAllComplete		: function () {
				if (!CodeExcelUpload._uploadifyErrorFlag) {
					//CodeExcelUpload.excelUploadProc();
				} else {
					$jquery.alert('첨부파일 업로드중 오류가 발생되었습니다. 세부내용을 확인하여 주세요.');
				}
			},
			onComplete			: function (event, queueID, fileObj, response, data) {
				$jquery("#" + CodeExcelUpload.FORM_ID).append('<input type="hidden" name="excelUploadFile" value="' + fileObj.name + '">');
				$jquery("#" + CodeExcelUpload.FORM_ID + ' #uploadFile').append('<br />' + fileObj.name + '&nbsp;&nbsp;&nbsp;<span class="required">[업로드 완료]</span>');
			},
			onError     		: function (event, queueID, fileObj, errorObj) {
				var message = '';
				switch (errorObj.info) {
				case 405:
					message = '등록할 수 없는 파일 형식 입니다.';
					break;
				}
				
				$jquery('#uploadify' + queueID + ' > span.message').text(' [' + message + ']');
				CodeExcelUpload._uploadifyErrorFlag = true;
			}
		});
	});
//-->
</script>

<div id="dialog-form-codeExcelUpload" title="공통코드 엑셀 등록" style="display: none;" class="popup">
	<p class="explain">등록할 공통코드 엑셀파일을 파일첨부 버튼을 눌러 선택해주세요.</p>
	<p>엑셀파일 서식을 받으려면 <a>[다운받기]</a>를 클릭하세요.</p>
	<p>이미 등록된 공통코드를 전체 삭제 후 등록하려면 하단의 &ldquo;삭제후 등록&ldquo;을 선택해주세요.</p>
	<p>삭제 후 등록을 선택하지 않을 경우 기존 코드에 추가됩니다.</p>
	
	<h3 class="subTitle">파일을 선택하세요</h3>
	
	<form name="formCodeExcelUpload" id="formCodeExcelUpload" method="post">
		<div class="popup_search_boarder">
			<input type="file" name="uploadify" id="uploadify" />
			<div id="uploadFile"></div>
		</div>
	
		<div class="sep"></div>
		<span>
			<input type="checkbox" name="deleteFlag" id="deleteFlag" value="1">&nbsp;&nbsp;<label for="deleteFlag">삭제 후 등록</label>
		</span>
	</form>
</div>