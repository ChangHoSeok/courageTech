<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		Board.formInitCreate();
		Board.ACTION_STATUS = '<c:out value="${param.actionStatus }" />';
		
		// 첨부파일 업로더 설정
		$jquery("#uploadifyAtch").uploadify({
			'uploader'       	: '${ctxPath }/resource/swf/uploader/uploadify.swf',
			'script'         	: '${ctxPath }/uploadify',
			'cancelImg'      	: '${ctxPath }/images/common/jquery/uploader/cancel.png',
			'queueSizeLimit' 	: parseInt('${boardMasterVO.atchPosblFileNumber }'),
			'sizeLimit'      	: parseInt('${boardMasterVO.atchPosblFileSize }') * 1024 * 1024,
			'simUploadLimit' 	: 1,
			'auto'           	: false,
			'multi'				: true,
			'buttonImg'			: '${ctxPath }/images/common/btn/btn_file.gif',
			'method'			: 'POST',
			'displayData'		: 'speed',
			onAllComplete		: function () {
				Board.atchFile.uploadFlag = true;
				
				if (Board.atchFile.uploadFlag && Board.mvpFile.uploadFlag) {
					if (!Board.mvpFile.hasError && !Board.atchFile.hasError) {
						Board.saveProc();
					} else {
						$jquery(".buttonSet").show();
						alert('첨부파일 업로드중 오류가 발생되었습니다.\n세부내용을 확인하여 주세요.');
					}
				}
			},
			
			onComplete			: function (event, queueID, fileObj, response, data) {
				$jquery("#formBoard").append('<input type="hidden" name="uploadAtchFiles" value="' + fileObj.name + '">');
				$jquery('#uploadAtchFile').append('<br />' + fileObj.name + '&nbsp;&nbsp;&nbsp;<span class="required">[업로드 완료]</span>');
			},
			
			onSelect			: function (event, queueID, fileObj) {
				Board.atchFile.uploadFlag = false;
			},
			
			onCancel     		: function (event, queueID, fileObj, data) {
				if (data.fileCount <= 0) {
					Board.atchFile.uploadFlag = true;
				} else {
					Board.atchFile.uploadFlag = false;
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
				Board.atchFile.hasError = true;
			}
		});
		
		// 동영상 업로더 설정
		$jquery("#uploadifyMvp").uploadify({
			'uploader'       	: '${ctxPath }/resource/swf/uploader/uploadify.swf',
			'script'         	: '${ctxPath }/uploadify',
			'cancelImg'      	: '${ctxPath }/images/common/jquery/uploader/cancel.png',
			'queueSizeLimit' 	: parseInt('${boardMasterVO.mvpPosblFileNumber }'),
			'sizeLimit'      	: parseInt('${boardMasterVO.mvpPosblFileSize }') * 1024 * 1024,
			'simUploadLimit' 	: 1,
			'fileDesc'			: 'mp4',
			'fileExt'			: '*.mp4',
			'auto'           	: false,
			'multi'				: true,
			'buttonImg'			: '${ctxPath }/images/common/btn/btn_file.gif',
			'method'			: 'POST',
			'displayData'		: 'speed',
			onAllComplete		: function () {
				Board.mvpFile.uploadFlag = true;
				
				if (Board.atchFile.uploadFlag && Board.mvpFile.uploadFlag) {
					if (!Board.mvpFile.hasError && !Board.atchFile.hasError) {
						Board.saveProc();
					} else {
						$jquery(".buttonSet").show();
						alert('첨부파일 업로드중 오류가 발생되었습니다.\n세부내용을 확인하여 주세요.');
					}
				}
			},
			
			onComplete			: function (event, queueID, fileObj, response, data) {
				$jquery("#formBoard").append('<input type="hidden" name="uploadMvpFiles" value="' + fileObj.name + '">');
				$jquery('#uploadMvpFile').append('<br />' + fileObj.name + '&nbsp;&nbsp;&nbsp;<span class="required">[업로드 완료]</span>');
			},
			
			onSelect			: function (event, queueID, fileObj) {
				Board.mvpFile.uploadFlag = false;
			},
			
			onCancel     		: function (event, queueID, fileObj, data) {
				if (data.fileCount <= 0) {
					Board.mvpFile.uploadFlag = true;
				} else {
					Board.mvpFile.uploadFlag = false;
				}
			},
			
			onError     		: function (event, queueID, fileObj, errorObj) {
				var message = '';
				switch (errorObj.info) {
				case 405:
					message = '등록할 수 없는 파일 형식 입니다.';
					break;
				}
				
				$jquery('#uploadifyMvp' + queueID + ' > span.message').text(' [' + message + ']');
				Board.mvpFile.hasError = true;
			}
		});
	});
//-->
</script>

<form:form name="formBoard" id="formBoard" action="createBoardProc.tech" method="post" commandName="boardVO">
	<form:hidden path="bbsId"/>
	<form:hidden path="nttId"/>
	<form:hidden path="mode"/>
	<form:hidden path="nttCnText"/>
	<cmmUtil:conditionInput condObject="${boardVO }"/>
	<cmmUtil:ajaxToken name="_dt"/>
	
	<div class="ct_area">
		<div id="contentInfo" class="path_search">
			<div id="progrmNm" class="left_path">
				<c:out value="${boardMasterVO.bbsNm }" />&nbsp;${boardVO.mode eq "create" ? "등록" : "수정" }
			</div>
			<div class="right_path">
				<span id="menuNavi"></span>
			</div>
		</div>
		
		<div id="contentBtnFix" class="button_area topLine staticHeightArea">
			<div class="buttonSet left">
				<button type="button" class="gen_btn _command[Board.save]" accesskey="1">
					<spring:message code="button.save" />
				</button>
				
				<c:if test="${boardVO.mode eq 'modify' }">
					<button type="button" class="gen_btn _command[Board.modifyCancel]" accesskey="2">
						<spring:message code="button.reset" />
					</button>
				</c:if>
				
				<button type="button" class="gen_btn _command[Board.listView]" accesskey="0">
					<spring:message code="button.list" />
				</button>
			</div>
		</div>
	</div>
	
	<c:if test="${validateCheck eq 'error' }">
		<div id="errorBox" class="errormsgbox staticHeightArea">
			<form:errors path="nttSj" /><br />
			<form:errors path="nttCn" /><br />
		</div>
	</c:if>
	
	<div id="data_list_area">
		<div id="table_list" class="type_write scroll">
			<h3 class="subTitle">게시물 정보</h3>
			<table cellspacing="0" cellpadding="0">
				<colgroup>
					<col style="width: 130px;" />
					<col style="width: 200px;" />
					<col style="width: 130px;" />
					<col style="width: 200px;" />
					<col style="width: 130px;" />
					<col />
				</colgroup>
				
				<tbody>
					<tr>
						<th><span class="required">*</span>제목</th>
						<td colspan="5">
							<form:input path="nttSj" cssClass="input-text required_textbox validate[required,maxSize[128]]" cssStyle="width: 450px;" maxlength="128"/>
							<c:if test="${boardAuthorCheckVO.mngrAuthor > 0 && boardMasterVO.bbsTyCode eq 'BBST01' }">
								<label style="margin-left: 15px;">
									<input type="checkbox" name="noticeAt" id="noticeAt" ${boardVO.noticeAt eq 'Y' ? 'checked="checked"' : '' }>
									&nbsp;공지글 (선택시 목록에 공지글로 표시됩니다.)
								</label>
							</c:if>
						</td>
					</tr>
					
					<c:if test="${boardMasterVO.bbsTyCode eq 'BBST02' }">
						<tr>
							<th><span class="required">*</span>공지기간</th>
							<td colspan="5">
								<form:input path="noticeBgnDe" cssClass="input-text-cal validate[required] separator[date]" cssStyle=""/>&nbsp;~&nbsp;
								<form:input path="noticeEndDe" cssClass="input-text-cal validate[required] separator[date]" cssStyle=""/>
							</td>
						</tr>
					</c:if>
					
					<c:if test="${boardVO.mode eq 'modify' }">
						<tr>
							<th>조회수</th>
							<td><fmt:formatNumber value="${boardVO.rdcnt }" />&nbsp;</td>
							<th>작성자</th>
							<td><c:out value="${boardVO.frstRegisterNm }" />&nbsp;</td>
							<th>작성일시</th>
							<td><fmt:formatDate value="${boardVO.frstRegistPnttm }" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;</td>
						</tr>
					</c:if>
					
					<c:if test="${boardMasterVO.fileAtchPosblAt eq 'Y' }">
						<tr>
							<th>첨부파일</th>
							<td class="attachFile" colspan="5">
								<ul class="atchFileList">
									<c:forEach var="storageFile" items="${boardVO.listAtchFiles }">
										<c:url var="fileDownUrl" value="/storage/storageFileDownload.tech">
											<c:param name="atchFileId">${storageFile.atchFileId }</c:param>
											<c:param name="fileSn">${storageFile.fileSn }</c:param>
										</c:url>
										
										<li>
											<a href="${fileDownUrl }">${storageFile.fileNm }&nbsp;(<cmmUtil:fileSizeFormat value="${storageFile.fileSize }" format="2" />)</a>&nbsp;&nbsp;
											<input type="checkbox" name="deleteAtchFiles" id="deleteAtchFiles${storageFile.fileSn }" value="${storageFile.atchFileId };${storageFile.fileSn }">&nbsp;
											<label for="deleteAtchFiles${storageFile.fileSn }">삭제</label>
										</li>
									</c:forEach>
								</ul>
								<input type="file" name="uploadifyAtch" id="uploadifyAtch" />
								<div id="uploadAtchFile"></div>
								첨부파일 ${boardMasterVO.atchPosblFileNumber }개 등록가능 각 파일 최대 용량 ${boardMasterVO.atchPosblFileSize }MB
							</td>
						</tr>
					</c:if>
					
					<c:if test="${boardMasterVO.mvpPosblAt eq 'Y' }">
						<tr>
							<th>동영상 파일</th>
							<td class="attachFile" colspan="5">
								<ul class="atchFileList">
									<c:forEach var="storageFile" items="${boardVO.listMvpFiles }">
										<c:url var="fileDownUrl" value="/storage/storageFileDownload.tech">
											<c:param name="atchFileId">${storageFile.atchFileId }</c:param>
											<c:param name="fileSn">${storageFile.fileSn }</c:param>
										</c:url>
										
										<li>
											<a href="${fileDownUrl }">${storageFile.fileNm }&nbsp;(<cmmUtil:fileSizeFormat value="${storageFile.fileSize }" format="2" />)</a>&nbsp;&nbsp;
											<input type="checkbox" name="deleteMvpFiles" id="deleteMvpFiles${storageFile.fileSn }" value="${storageFile.atchFileId };${storageFile.fileSn }">&nbsp;
											<label for="deleteMvpFiles${storageFile.fileSn }">삭제</label>
										</li>
									</c:forEach>
								</ul>
								<input type="file" name="uploadifyMvp" id="uploadifyMvp" />
								<div id="uploadMvpFile"></div>
								동영상 ${boardMasterVO.mvpPosblFileNumber }개 등록가능 각 파일 최대 용량 ${boardMasterVO.mvpPosblFileSize }MB
							</td>
						</tr>
					</c:if>
					
					<tr>
						<td colspan="6" class="wysiwygArea">
							<form:textarea path="nttCn" cssClass="editor" />
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</form:form>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />