<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		BoardMaster.formInitCreate();
		BoardMaster.ACTION_STATUS = '<c:out value="${param.actionStatus }" />';
	});
//-->
</script>

<form:form name="formBoardMaster" id="formBoardMaster" action="createBoardMasterProc.tech" method="post" commandName="boardMasterVO">
	<form:hidden path="mode"/>
	<cmmUtil:conditionInput condObject="${boardMasterVO }"/>
	<cmmUtil:ajaxToken name="_dt"/>
	
	<div class="ct_area">
		<div id="contentInfo" class="path_search">
			<div id="progrmNm" class="left_path"></div>
			<div class="right_path">
				<span id="menuNavi"></span>
			</div>
		</div>
		
		<div id="contentBtnFix" class="button_area topLine staticHeightArea">
			<div class="buttonSet left">
				<button type="button" class="gen_btn _command[BoardMaster.save]" accesskey="1">
					<spring:message code="button.save" />
				</button>
				
				<c:if test="${boardMasterVO.mode eq 'modify' }">
					<button type="button" class="gen_btn _command[BoardMaster.modifyCancel]" accesskey="2">
						<spring:message code="button.reset" />
					</button>
				</c:if>
				
				<button type="button" class="gen_btn _command[BoardMaster.listView]" accesskey="0">
					<spring:message code="button.list" />
				</button>
			</div>
		</div>
	</div>
	
	<c:if test="${validateCheck eq 'error' }">
		<div id="errorBox" class="errormsgbox staticHeightArea">
			<form:errors path="bbsNm" /><br />
			<form:errors path="bbsTyCode" /><br />
			<form:errors path="bbsAttrbCode" /><br />
			<form:errors path="replyPosblAt" /><br />
			<form:errors path="answerPosblAt" /><br />
			<form:errors path="fileAtchPosblAt" /><br />
			<form:errors path="mvpPosblAt" /><br />
			<form:errors path="useAt" />
		</div>
	</c:if>
	
	<div id="infomation" class="type_write staticHeightArea">
		<h3 class="subTitle">게시판 정보</h3>
		<table cellspacing="0" cellpadding="0">
			<colgroup>
				<col style="width: 140px;" />
				<col style="width: 190px;" />
				<col style="width: 140px;" />
				<col style="width: 190px;" />
				<col style="width: 140px;" />
				<col />
			</colgroup>
			
			<tbody>
				<tr>
					<th><span class="required">*</span>게시판ID</th>
					<td>
						<c:choose>
							<c:when test="${boardMasterVO.mode eq 'create' }">
								자동부여
							</c:when>
							<c:otherwise>
								<c:out value="${boardMasterVO.bbsId }" />
								<form:hidden path="bbsId"/>
							</c:otherwise>
						</c:choose>
					</td>
					<th><span class="required">*</span>사용여부</th>
					<td colspan="3">
						<cmmCd:cdSelect codeId="COM038" name="useAt" id="useAt" styleClass="validate[required]" defaultCode="${boardMasterVO.useAt }" showSelect="true" use="true"/>
					</td>
				</tr>
				<tr>
					<th><span class="required">*</span>게시판 명</th>
					<td colspan="5"><form:input path="bbsNm" cssClass="input-text required_textbox validate[required,maxSize[128]]" cssStyle="width: 450px;" maxlength="128"/></td>
				</tr>
				<tr>
					<th>게시판 소개</th>
					<td colspan="5"><form:textarea path="bbsIntrcn" cssClass="input-text validate[maxSize[1000]]" cssStyle="height: 100px; width: 450px;"></form:textarea></td>
				</tr>
				<tr>
					<th><span class="required">*</span>게시판 유형</th>
					<td>
						<cmmCd:cdSelect codeId="COM004" name="bbsTyCode" id="bbsTyCode" styleClass="validate[required]" defaultCode="${boardMasterVO.bbsTyCode }" showSelect="true" use="true"/>
					</td>
					<th><span class="required">*</span>게시판 속성</th>
					<td colspan="3">
						<cmmCd:cdSelect codeId="COM009" name="bbsAttrbCode" id="bbsAttrbCode" styleClass="validate[required]" defaultCode="${boardMasterVO.bbsAttrbCode }" showSelect="true" use="true"/>
					</td>
				</tr>
				<tr>
					<th><span class="required">*</span>답글가능</th>
					<td>
						<cmmCd:cdSelect codeId="COM038" name="replyPosblAt" id="replyPosblAt" styleClass="validate[required]" defaultCode="${boardMasterVO.replyPosblAt }" showSelect="true" use="true"/>
					</td>
					<th><span class="required">*</span>댓글가능</th>
					<td colspan="3">
						<cmmCd:cdSelect codeId="COM038" name="answerPosblAt" id="answerPosblAt" styleClass="validate[required]" defaultCode="${boardMasterVO.answerPosblAt }" showSelect="true" use="true"/>
					</td>
				</tr>
				<tr>
					<th><span class="required">*</span>파일첨부</th>
					<td>
						<cmmCd:cdSelect codeId="COM038" name="fileAtchPosblAt" id="fileAtchPosblAt" styleClass="validate[required]" defaultCode="${boardMasterVO.fileAtchPosblAt }" showSelect="true" use="true" onChange="BoardMaster.fieldSet(this);"/>
					</td>
					<th><span class="required fileStar">*</span>파일 수 제한</th>
					<td>
						<form:input path="atchPosblFileNumber" cssClass="input-text validate[onlyNumber,min[1],max[10]]" cssStyle="width: 30px;" maxlength="2"/>&nbsp;개
					</td>
					<th><span class="required fileStar">*</span>파일 사이즈 제한</th>
					<td>
						<form:input path="atchPosblFileSize" cssClass="input-text validate[onlyNumber,min[1],max[500]]" cssStyle="width: 30px;" maxlength="3"/>&nbsp;MB
					</td>
				</tr>
				<tr>
					<th><span class="required">*</span>동영상 첨부</th>
					<td>
						<cmmCd:cdSelect codeId="COM038" name="mvpPosblAt" id="mvpPosblAt" styleClass="validate[required]" defaultCode="${boardMasterVO.mvpPosblAt }" showSelect="true" use="true" onChange="BoardMaster.fieldSet(this);"/>
					</td>
					<th><span class="required mvpStar">*</span>동영상 수 제한</th>
					<td>
						<form:input path="mvpPosblFileNumber" cssClass="input-text validate[onlyNumber,min[1],max[5]]" cssStyle="width: 30px;" maxlength="1"/>&nbsp;개
					</td>
					<th><span class="required mvpStar">*</span>동영상 사이즈 제한</th>
					<td>
						<form:input path="mvpPosblFileSize" cssClass="input-text validate[onlyNumber,min[1],max[500]]" cssStyle="width: 30px;" maxlength="3"/>&nbsp;MB
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</form:form>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />