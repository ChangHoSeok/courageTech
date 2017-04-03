<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">&times;</button>
	<h4 class="modal-title">Profile</h4>
</div>

<div class="modal-body">
	<form:form name="formUser" id="formUser" cssClass="form-horizontal" action="userSbscrbDetail.tech" method="post" commandName="userVO">
		<input type="hidden" name="emplyrId" value="dummy">
		<input type="hidden" name="emailId" value="dummy">
		<input type="hidden" name="emailDomain" value="dummy">
		<input type="hidden" name="password" value="dummy">
		
		<c:if test="${validateCheck eq 'error' }">
			<div class="form-group">
				<c:set var="errEmplyrId"><form:errors path="emplyrId" /></c:set>
				<c:set var="errUserNm"><form:errors path="userNm" /></c:set>
				<c:set var="errEmailId"><form:errors path="emailId" /></c:set>
				<c:set var="errEmailDomain"><form:errors path="emailDomain" /></c:set>
				<c:set var="errPassword"><form:errors path="password" /></c:set>
				
				<c:if test="${!empty errEmplyrId }">
					<div class="alert alert-danger fade in" role="alert">
						<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						${errEmplyrId }
					</div>
				</c:if>
				<c:if test="${!empty errUserNm }">
					<div class="alert alert-danger fade in" role="alert">
						<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						${errUserNm }
					</div>
				</c:if>
				<c:if test="${!empty errEmailId }">
					<div class="alert alert-danger fade in" role="alert">
						<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						${errEmailId }
					</div>
				</c:if>
				<c:if test="${!empty errEmailDomain }">
					<div class="alert alert-danger fade in" role="alert">
						<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						${errEmailDomain }
					</div>
				</c:if>
				<c:if test="${!empty errPassword }">
					<div class="alert alert-danger fade in" role="alert">
						<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						${errPassword }
					</div>
				</c:if>
			</div>
		</c:if>
		
		<div class="form-group">
			<label for="emailId" class="col-xs-12 col-sm-3 control-label">아이디</label>
			<div class="col-xs-12 col-sm-9">
				<p class="form-control-static"><c:out value="${userVO.emplyrId }" /></p>
			</div>
		</div>
		<div class="form-group">
			<label for="userNm" class="col-xs-12 col-sm-3 control-label">닉네임</label>
			<div class="col-xs-12 col-sm-9">
				<form:input path="userNm" placeholder="닉네임" cssClass="form-control validate[required,custom[onlyKoreaEng]]" maxlength="50"/>
			</div>
		</div>
		<div class="form-group">
			<label for="password" class="col-xs-12 col-sm-3 control-label">신규 비밀번호</label>
			<div class="col-xs-12 col-sm-9">
				<input type="password" name="password" id="password" class="form-control validate[required,custom[password]]" style="margin-bottom: 5px;" maxlength="30" placeholder="비밀번호">
				<input type="password" name="passwordConfirm" id="passwordConfirm" class="form-control validate[required,custom[password],equals[password]]" maxlength="30" placeholder="비밀번호 확인">
				<p class="form-control-static">※. 신규 비밀번호를 입력하면 입력한 비밀번호로 변경 됩니다.</p>
			</div>
		</div>
		<div class="form-group">
			<label for="emailId" class="col-xs-12 col-sm-3 control-label">가입일시</label>
			<div class="col-xs-12 col-sm-9">
				<p class="form-control-static"><c:out value="${userVO.sbscrbDe }" /></p>
			</div>
		</div>
	</form:form>
</div>

<div class="modal-footer">
	<button type="button" class="btn btn-default _command[UserSbscrb.modalDestroy]" data-dismiss="modal">닫기</button>
	<button type="button" class="btn btn-primary _command[UserSbscrb.modifySbscrb]">회원정보 변경</button>
	<button type="button" class="btn btn-danger _command[UserSbscrb.showSecsn]">회원탈퇴</button>
</div>

<script type="text/javascript">
<!--
	$(document).ready(function() {
		UserSbscrb.ACTION_STATUS = "${actionStatus}";
		UserSbscrb.formInitSbscrbDetail();
	});
//-->
</script>

<c:import url="/WEB-INF/jsp/courage/tech/commonActionMessage.jsp" />