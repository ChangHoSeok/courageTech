<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		LogFileRceptPopup.ACTION_STATUS = "${actionStatus}";
		LogFileRceptPopup.formInitCreate();
		
		// 첨부파일 업로더 설정
		$jquery("#uploadifyAtch").uploadify({
			'uploader'       	: '${ctxPath }/resource/swf/uploader/uploadify.swf',
			'script'         	: '${ctxPath }/uploadify',
			'cancelImg'      	: '${ctxPath }/images/common/jquery/uploader/cancel.png',
			'queueSizeLimit' 	: 10,
			'sizeLimit'      	: 200 * 1024 * 1024,
			'simUploadLimit' 	: 2,
			'auto'           	: false,
			'multi'				: true,
			'buttonImg'			: '${ctxPath }/images/common/btn/btn_file.gif',
			'method'			: 'POST',
			'displayData'		: 'speed',
			onAllComplete		: function () {
				LogFileRceptPopup.atchFile.uploadFlag = true;
				
				if (LogFileRceptPopup.atchFile.uploadFlag) {
					if (!LogFileRceptPopup.atchFile.hasError) {
						LogFileRceptPopup.saveProc();
					} else {
						$jquery(".buttonSet").show();
						alert('첨부파일 업로드중 오류가 발생되었습니다.\n세부내용을 확인하여 주세요.');
					}
				}
			},
			
			onComplete			: function (event, queueID, fileObj, response, data) {
				$jquery("#formLogFileRceptPopup").append('<input type="hidden" name="uploadAtchFiles" value="' + fileObj.name + '">');
				$jquery('#uploadAtchFile').append('<br />' + fileObj.name + '&nbsp;&nbsp;&nbsp;<span class="required">[업로드 완료]</span>');
			},
			
			onSelect			: function (event, queueID, fileObj) {
				LogFileRceptPopup.atchFile.uploadFlag = false;
			},
			
			onCancel     		: function (event, queueID, fileObj, data) {
				if (data.fileCount <= 0) {
					LogFileRceptPopup.atchFile.uploadFlag = true;
				} else {
					LogFileRceptPopup.atchFile.uploadFlag = false;
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
				LogFileRceptPopup.atchFile.hasError = true;
			}
		});
	});
//-->
</script>

<div class="popup_title_sm">
	<h1 id="progrmNm"></h1>
</div>

<div class="popup_contents">
	<form:form name="formLogFileRceptPopup" id="formLogFileRceptPopup" method="post" commandName="logFileRceptVO">
		<input type="hidden" name="openerKey" id="openerKey" value="<c:out value="${param.openerKey }" />">
		<input type="hidden" name="returnFunction" id="returnFunction" value="<c:out value="${param.returnFunction }" />">
		<form:hidden path="mode"/>
		<cmmUtil:ajaxToken name="_dt"/>
		
		<c:if test="${validateCheck eq 'error' }">
			<div id="errorBox" class="errormsgbox staticHeightArea">
				<form:errors path="groupId" /><br />
				<form:errors path="rceptTy" /><br />
				<form:errors path="uploadAtchFiles" />
			</div>
		</c:if>
		
		<div class="tbl_result_list">
			<table cellspacing="0" cellpadding="0">
				<colgroup>
					<col style="width: 100px;" />
					<col />
					<col style="width: 100px;" />
					<col />
				</colgroup>
				
				<tbody>
					<tr>
						<th><span class="required">*</span>&nbsp;로그분류 그룹</th>
						<td class="text_left">
							<cmmCd:cdSelect name="groupId" id="groupId"
								operation="logClGroupDAO.selectCommonCodeListForLogClGroup"
								defaultCode="${logFileRceptVO.groupId }"
								styleClass="validate[required]"
								showAll="true"
								use="true"/>
						</td>
						<th><span class="required">*</span>&nbsp;접수유형</th>
						<td class="text_left">
							<cmmCd:cdSelect name="rceptTy" id="rceptTy"
								codeId="CURG008"
								defaultCode="${logFileRceptVO.rceptTy }"
								styleClass="validate[required]"
								showAll="true"
								use="true"/>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<p class="mB10">※. 파일첨부 버튼을 클릭하여 로그파일을 선택하세요.</p>
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
		<button type="button" class="gen_btn needCreate _command[LogFileRceptPopup.save]" accesskey="1"><spring:message code="button.save" /></button>
		<button type="button" class="gen_btn _command[LogFileRceptPopup.popupClose]" accesskey="0"><spring:message code="button.close" /></button>
	</div>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />