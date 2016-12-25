<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		BoardMaster.formInitDetail();
	});
//-->
</script>

<div class="ct_area">
	<div id="contentInfo" class="path_search">
		<div id="progrmNm" class="left_path"></div>
		<div class="right_path">
			<span id="menuNavi"></span>
		</div>
	</div>
	
	<form:form name="formBoardMaster" id="formBoardMaster" action="retrieveBoardMasterDetail.tech" method="post" commandName="boardMasterVO">
		<form:hidden path="bbsId"/>
		<cmmUtil:conditionInput condObject="${boardMasterVO }"/>
		<cmmUtil:ajaxToken name="_dt"/>
		
		<div id="contentBtnFix" class="button_area topLine staticHeightArea">
			<div class="buttonSet left">
				<button type="button" class="gen_btn needCreate _command[BoardMaster.modifyView]" accesskey="1"><spring:message code="button.modify" /></button>
				<button type="button" class="gen_btn needCreate _command[BoardMaster.deleteBoardMaster]" accesskey="2" ${boardMasterVO.posblDelete ? '' : 'disabled="disabled"' }><spring:message code="button.delete" /></button>
				<button type="button" class="gen_btn _command[BoardMaster.listView]" accesskey="0"><spring:message code="button.list" /></button>
			</div>
		</div>
	</form:form>
</div>

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
				<th>게시판ID</th>
				<td><c:out value="${boardMasterVO.bbsId }" />&nbsp;</td>
				<th>사용여부</th>
				<td colspan="3"><cmmCd:cdValue code="${boardMasterVO.useAt }" codeId="COM038" />&nbsp;</td>
			</tr>
			<tr>
				<th>게시판 명</th>
				<td colspan="5"><c:out value="${boardMasterVO.bbsNm }" />&nbsp;</td>
			</tr>
			<tr>
				<th>게시판 소개</th>
				<td colspan="5"><cmmUtil:strToHtmlStyle source="${boardMasterVO.bbsIntrcn }" />&nbsp;</td>
			</tr>
			<tr>
				<th>게시판 유형</th>
				<td><cmmCd:cdValue code="${boardMasterVO.bbsTyCode }" codeId="COM004" />&nbsp;</td>
				<th>게시판 속성</th>
				<td colspan="3"><cmmCd:cdValue code="${boardMasterVO.bbsAttrbCode }" codeId="COM009" />&nbsp;</td>
			</tr>
			<tr>
				<th>답글가능</th>
				<td><cmmCd:cdValue code="${boardMasterVO.replyPosblAt }" codeId="COM038" />&nbsp;</td>
				<th>댓글가능</th>
				<td colspan="3"><cmmCd:cdValue code="${boardMasterVO.answerPosblAt }" codeId="COM038" />&nbsp;</td>
			</tr>
			<tr>
				<th>파일첨부</th>
				<td><cmmCd:cdValue code="${boardMasterVO.fileAtchPosblAt }" codeId="COM038" />&nbsp;</td>
				<th>파일 수 제한</th>
				<td><c:out value="${boardMasterVO.fileAtchPosblAt eq 'Y' ? boardMasterVO.atchPosblFileNumber : '0' }" />&nbsp;개</td>
				<th>파일 사이즈 제한</th>
				<td><c:out value="${boardMasterVO.fileAtchPosblAt eq 'Y' ? boardMasterVO.atchPosblFileSize : '0' }" />&nbsp;MB</td>
			</tr>
			<tr>
				<th>동영상 첨부</th>
				<td><cmmCd:cdValue code="${boardMasterVO.mvpPosblAt }" codeId="COM038" />&nbsp;</td>
				<th>동영상 수 제한</th>
				<td><c:out value="${boardMasterVO.mvpPosblAt eq 'Y' ? boardMasterVO.mvpPosblFileNumber : '0' }" />&nbsp;개</td>
				<th>동영상 사이즈 제한</th>
				<td><c:out value="${boardMasterVO.mvpPosblAt eq 'Y' ? boardMasterVO.mvpPosblFileSize : '0' }" />&nbsp;MB</td>
			</tr>
			<tr>
				<th>등록자</th>
				<td><c:out value="${boardMasterVO.frstRegisterNm }" />&nbsp;</td>
				<th>등록일시</th>
				<td colspan="3"><c:out value="${boardMasterVO.frstRegistPnttm }" />&nbsp;</td>
			</tr>
		</tbody>
	</table>
	<strong style="padding: 10px; color: #FF9436; position: absolute;">※. 생성된 게시판 메뉴등록시 프로그램 목록에 게시판 모듈을 추가하고 매게변수에 bbsId=${boardMasterVO.bbsId } 을 입력 해야합니다.</strong>
</div>

<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />