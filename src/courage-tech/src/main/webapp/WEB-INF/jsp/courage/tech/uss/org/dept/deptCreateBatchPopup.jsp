<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		DeptPopup.ACTION_STATUS = "${actionStatus}";
		DeptPopup.formInitCreate();
		
		// 첨부파일 업로더 설정
		$jquery("#uploadifyAtch").uploadify({
			'uploader'       	: '${ctxPath }/resource/swf/uploader/uploadify.swf',
			'script'         	: '${ctxPath }/uploadify',
			'cancelImg'      	: '${ctxPath }/images/common/jquery/uploader/cancel.png',
			'queueSizeLimit' 	: 1,
			'sizeLimit'      	: 20 * 1024 * 1024,
			'simUploadLimit' 	: 1,
			'fileDesc'			: 'xls,xlsx',
			'fileExt'			: '*.xls;*.xlsx',
			'auto'           	: false,
			'multi'				: true,
			'buttonImg'			: '${ctxPath }/images/common/btn/btn_file.gif',
			'method'			: 'POST',
			'displayData'		: 'speed',
			onAllComplete		: function () {
				DeptPopup.atchFile.uploadFlag = true;
				
				if (DeptPopup.atchFile.uploadFlag) {
					if (!DeptPopup.atchFile.hasError) {
						DeptPopup.saveProc();
					} else {
						$jquery(".buttonSet").show();
						alert('첨부파일 업로드중 오류가 발생되었습니다.\n세부내용을 확인하여 주세요.');
					}
				}
			},
			
			onComplete			: function (event, queueID, fileObj, response, data) {
				$jquery("#formDeptPopup").append('<input type="hidden" name="uploadAtchFile" value="' + fileObj.name + '">');
				$jquery('#uploadAtchFile').append('<br />' + fileObj.name + '&nbsp;&nbsp;&nbsp;<span class="required">[업로드 완료]</span>');
			},
			
			onSelect			: function (event, queueID, fileObj) {
				DeptPopup.atchFile.uploadFlag = false;
			},
			
			onCancel     		: function (event, queueID, fileObj, data) {
				if (data.fileCount <= 0) {
					DeptPopup.atchFile.uploadFlag = true;
				} else {
					DeptPopup.atchFile.uploadFlag = false;
				}
			},
			
			onError     		: function (event, queueID, fileObj, errorObj) {
				var message = '';
				switch (errorObj.info) {
				case 405:
					message = '등록할 수 없는 파일 형식 입니다.';
					break;
				}
				
				$jquery('#uploadifyAtch' + queueID + ' > span.message').text(' [' + message + ']');
				DeptPopup.atchFile.hasError = true;
			}
		});
	});
//-->
</script>

<div class="popup_title_sm">
	<h1 id="progrmNm"></h1>
</div>

<div class="popup_contents">
	<form:form name="formDeptPopup" id="formDeptPopup" method="post" commandName="deptBatchVO">
		<input type="hidden" name="openerKey" id="openerKey" value="<c:out value="${param.openerKey }" />">
		<input type="hidden" name="returnFunction" id="returnFunction" value="<c:out value="${param.returnFunction }" />">
		<form:hidden path="mode"/>
		<cmmUtil:ajaxToken name="_dt"/>
		
		<c:if test="${validateCheck eq 'error' }">
			<div id="errorBox" class="errormsgbox staticHeightArea">
				<form:errors path="uploadAtchFile" />
			</div>
		</c:if>
		
		<div class="tbl_result_list">
			<table cellspacing="0" cellpadding="0">
				<colgroup>
					<col style="width: 100px;" />
					<col />
				</colgroup>
				
				<tbody>
					<tr>
						<td colspan="2" class="text_left">
							<label><form:checkbox path="deleteFlag" value="delete"/>&nbsp;기존 데이터 삭제 후 등록</label>
						</td>
					</tr>
					<tr class="needCreate">
						<td colspan="2">
							<p class="mB10">※. 파일첨부 버튼을 클릭하여 로그파일을 선택하세요. (xls, xlsx)</p>
							<input type="file" name="uploadifyAtch" id="uploadifyAtch" />
							<div id="uploadAtchFile"></div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</form:form>
</div>

<div class="popup_bottom">
	<div class="popup_btnGroup buttonSet">
		<button type="button" class="gen_btn needCreate _command[DeptPopup.save]" accesskey="1"><spring:message code="button.save" /></button>
		<button type="button" class="gen_btn _command[DeptPopup.popupClose]" accesskey="0"><spring:message code="button.close" /></button>
	</div>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />