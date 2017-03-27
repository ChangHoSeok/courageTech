<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<div class="modal-dialog">
	<form:form name="formUser" id="formUser" cssClass="form-horizontal" action="${ctxPath }/system/user/createUserSbscrb.tech" method="post" commandName="userVO">
		<input type="hidden" name="emplyrId" value="dummy">
		
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">회원가입</h4>
			</div>
			
			<div class="modal-body">
				<span class="section">회원 정보입력</span>
				
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
						<form:input path="emailId" placeholder="이메일 주소" cssClass="form-control idCheck validate[required,custom[email]]" maxlength="100"/>
						
						<div id="emailIdValidMsg" class="alert alert-danger fade in" role="alert" style="margin: 5px 0 0 5px !important; padding: 10px !important">
							사용할 수 없는 이메일 주소 입니다.
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="userNm" class="col-xs-12 col-sm-3 control-label">닉네임</label>
					<div class="col-xs-12 col-sm-9">
						<form:input path="userNm" placeholder="닉네임" cssClass="form-control validate[required]" maxlength="50"/>
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-xs-12 col-sm-3 control-label">비밀번호</label>
					<div class="col-xs-12 col-sm-9">
						<input type="password" name="password" id="password" class="form-control validate[required,custom[password]]" maxlength="30" placeholder="비밀번호">
					</div>
				</div>
				<div class="form-group">
					<label for="passwordConfirm" class="col-xs-12 col-sm-3 control-label">비밀번호 확인</label>
					<div class="col-xs-12 col-sm-9">
						<input type="password" name="passwordConfirm" id="passwordConfirm" class="form-control validate[required,custom[password],equals[password]]" maxlength="30" placeholder="비밀번호 확인">
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-default _command[UserSbscrb.modalDestroy]" data-dismiss="modal">닫기</button>
				<button type="button" id="btnLogin" class="btn btn-primary _command[UserSbscrb.sbscrbSave]" data-login-type="layerPopup">회원가입</button>
			</div>
		</div>
	</form:form>
</div>

<script type="text/javascript" src="${ctxPath}/dwr/interface/userService.js"></script>
<script type="text/javascript">
<!--
	$(document).ready(function() {
		$(document).on("focusout", "#" + UserSbscrb.FORM_ID + " .idCheck", function() {
			var emailId = $("#" + UserSbscrb.FORM_ID + " #emailId").val();

			if (!isEmpty(emailId)) {
				UserSbscrb.duplicateCheck(emailId);
			}
		});
		
		UserSbscrb.formInitSbscrbCreatePopup();
	});
//-->
</script>

<c:import url="/WEB-INF/jsp/courage/tech/commonActionMessage.jsp" />