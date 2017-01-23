<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/courage/tech/commonHead.jsp" %>

<!-- jquery video/audio player : begin -->
<link href="${ctxPath}/<spring:eval expression="@systemConfig['system.css.basePath']" />/common/jquery/jplayer/theme-vintage-wine/style.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" charset="utf-8" src="${ctxPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/jquery/jplayer/jquery.jplayer.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctxPath}/<spring:eval expression="@systemConfig['system.js.basePath']" />/common/jquery/jplayer/add-on/jplayer.playlist.js"></script>
<!-- jquery video/audio player : end -->

<script type="text/javascript">
<!--
	$jquery(document).ready(function() {
		Board.USE_AT = "${boardVO.useAt }";
		Board.formInitDetail();
		
		if ('${boardMasterVO.mvpPosblAt }' == 'Y' && parseInt('${fn:length(boardVO.listMvpFiles) }') > 0) {
			var cssStyle = {
				jPlayer: "#jquery_jplayer_1",
				cssSelectorAncestor: "#jp_container_1"
			};
			
			var playList = [];
			
			var playOptions = {
				solutions: "html,flash",
				swfPath: "${pageContext.request.contextPath }/resource/swf/jplayer",
				supplied: "m4v",
				smoothPlayBar: true,
				useStateClassSkin: true,
				keyEnabled: true,
				remainingDuration: true,
		        toggleDuration: true,
		        size:{
					width:		"100%",
					height:		"500px",
					cssClass:	"jp-video-360p"
				},
		        playlistOptions: {
		        	enableRemoveControls: true
		        }
			};
			
			var moviePlayList = new jPlayerPlaylist(cssStyle, playList, playOptions);
			
			<c:forEach var="mvpVO" items="${boardVO.listMvpFiles }">
			moviePlayList.add({
				title: "${mvpVO.fileNm }",
				m4v: "${pageContext.request.contextPath }${mvpVO.filePath }/${mvpVO.uniqFileNm }",
				poster: "${pageContext.request.contextPath }/images/common/player_cover.png"
			});
			</c:forEach>
		}
	});
//-->
</script>

<div id="data_list_area">
	<div id="table_list" class="type_write scroll">
		<div class="path_search">
			<div id="progrmNm" class="left_path"><c:out value="${boardMasterVO.bbsNm }" />&nbsp;상세조회${fn:length(boardVO.listMvpFiles) }</div>
			<div class="right_path">
				<span id="menuNavi"></span>
			</div>
		</div>
		
		<c:set var="isCreate" value="${false }" />
		<c:set var="isReply" value="${false }" />
		
		<c:choose>
			<%-- 관리자 --%>
			<c:when test="${boardAuthorCheckVO.mngrAuthor > 0 }">
				<c:set var="isCreate" value="${true }" />
				<c:set var="isReply" value="${true }" />
			</c:when>
			<c:when test="${boardAuthorCheckVO.sntncWritng > 0 }">
				<c:set var="isReply" value="${true }" />
				
				<%-- 일반사용자 : 권한있고 내가 작성한 글의 경우 --%>
				<c:if test="${boardVO.frstRegisterId eq sessionScope['uniqID'] }">
					<c:set var="isCreate" value="${true }" />
				</c:if>
			</c:when>
		</c:choose>
		
		<form:form name="formBoard" id="formBoard" action="retrieveBoardDetail.tech" method="post" commandName="boardVO">
			<form:hidden path="bbsId"/>
			<form:hidden path="nttId"/>
			<cmmUtil:conditionInput condObject="${boardVO }"/>
			<cmmUtil:ajaxToken name="_dt"/>
			
			<div id="contentBtnFix" class="button_area topLine">
				<div class="buttonSet left">
					<c:if test="${isCreate }">
						<button type="button" class="gen_btn needCreate _command[Board.modifyView]" accesskey="1"><spring:message code="button.modify" /></button>
						<button type="button" class="gen_btn needCreate _command[Board.deleteBoard]" accesskey="2" ${boardVO.answerCnt + boardVO.replyCnt <= 0 ? '' : 'disabled="disabled"' }><spring:message code="button.delete" /></button>
					</c:if>
					<button type="button" class="gen_btn _command[Board.listView]" accesskey="0"><spring:message code="button.list" /></button>
				</div>
				
				<div class="buttonSet right">
					<c:if test="${boardMasterVO.replyPosblAt eq 'Y' && isReply }">
						<button type="button" class="gen_btn needCreate _command[Board.replyView]" accesskey="3" ${boardVO.parntsSntncNo > 0 ? 'disabled="disabled"' : '' }>답글쓰기</button>
					</c:if>
				</div>
			</div>
		</form:form>
		
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
					<th>제목</th>
					<td colspan="5">
						<c:choose>
							<c:when test="${boardVO.useAt eq 'Y' }">
								<c:choose>
									<c:when test="${boardVO.noticeAt eq 'Y' }">
										<strong style="color: #FF9436;">[공지]&nbsp;<c:out value="${boardVO.nttSj }" /></strong>
									</c:when>
									<c:otherwise>
										<c:out value="${boardVO.nttSj }" />&nbsp;
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								블라인드 처리된 게시글 입니다.
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				
				<c:if test="${boardMasterVO.bbsTyCode eq 'BBST02' }">
					<tr>
						<th>공지기간</th>
						<td colspan="5">
							<cmmUtil:strFormat pattern="-" value="${boardVO.noticeBgnDe }" format="####-##-##"/>&nbsp;~
							<cmmUtil:strFormat pattern="-" value="${boardVO.noticeEndDe }" format="####-##-##"/>
						</td>
					</tr>
				</c:if>
				
				<tr>
					<th>조회수</th>
					<td><fmt:formatNumber value="${boardVO.rdcnt }" />&nbsp;</td>
					<th>작성자</th>
					<td><c:out value="${boardVO.frstRegisterNm }" />&nbsp;</td>
					<th>작성일시</th>
					<td><fmt:formatDate value="${boardVO.frstRegistPnttm }" pattern="yyyy-MM-dd HH:mm:ss" />&nbsp;</td>
				</tr>
				
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
										<a href="${fileDownUrl }">${storageFile.fileNm }&nbsp;(<cmmUtil:fileSizeFormat value="${storageFile.fileSize }" format="2" />)</a>
									</li>
								</c:forEach>
							</ul>
						</td>
					</tr>
				</c:if>
				
				<c:if test="${boardMasterVO.mvpPosblAt eq 'Y' && fn:length(boardVO.listMvpFiles) > 0 }">
					<tr>
						<td colspan="6">
							<div id="jp_container_1" class="jp-video" role="application" aria-label="media player" style="">
								<div class="jp-type-playlist" style="border: 1px solid #c4c6c7;width: 890px; margin: 30px auto;">
						            <div id="jquery_jplayer_1" class="jp-jplayer"></div>
						            <div class="jp-gui">
						                <div class="jp-video-play">
						                    <a href="javascript:;" class="jp-video-play-icon" tabindex="1">play</a>
						                </div>
						                <div class="jp-interface">
						                    <div class="jp-controls-holder">
						                        <ul class="jp-controls">
						                            <li><a href="javascript:;" class="jp-previous" tabindex="1">previous</a></li>
						                            <li><a href="javascript:;" class="jp-play" tabindex="1">play</a></li>
						                            <li><a href="javascript:;" class="jp-pause" tabindex="1">pause</a></li>
						                            <li><a href="javascript:;" class="jp-next" tabindex="1">next</a></li>
						                            <li><a href="javascript:;" class="jp-stop" tabindex="1">stop</a></li>
						                            <li><a href="javascript:;" class="jp-mute" tabindex="1" title="mute">mute</a></li>
						                            <li><a href="javascript:;" class="jp-unmute" tabindex="1" title="unmute">unmute</a></li>
						                            <li><a href="javascript:;" class="jp-volume-max" tabindex="1" title="max volume">max volume</a></li>
						                        </ul>
						                        <div class="jp-volume-bar">
						                            <div class="jp-volume-bar-value"></div>
						                        </div>
						                        <ul class="jp-toggles">
						                            <li><a href="javascript:;" class="jp-full-screen" tabindex="1" title="full screen">full screen</a></li>
						                            <li><a href="javascript:;" class="jp-restore-screen" tabindex="1" title="restore screen">restore screen</a></li>
						                            <li><a href="javascript:;" class="jp-shuffle" tabindex="1" title="shuffle">shuffle</a></li>
						                            <li><a href="javascript:;" class="jp-shuffle-off" tabindex="1" title="shuffle off">shuffle off</a></li>
						                            <li><a href="javascript:;" class="jp-repeat" tabindex="1" title="repeat">repeat</a></li>
						                            <li><a href="javascript:;" class="jp-repeat-off" tabindex="1" title="repeat off">repeat off</a></li>
						                        </ul>
						                    </div>
						                    <div class="jp-progress">
						                        <div class="jp-seek-bar">
						                            <div class="jp-play-bar"></div>
						                        </div>
						                    </div>
						                    <div class="jp-current-time"></div>
						                    <div class="jp-duration"></div>
						                    <div class="jp-title">
						                        <ul>
						                            <li></li>
						                        </ul>
						                    </div>
						                </div>
						            </div>
						            <div class="jp-playlist">
						                <ul>
						                    <!-- The method Playlist.displayPlaylist() uses this unordered list -->
						                    <li></li>
						                </ul>
						            </div>
						            <div class="jp-no-solution">
						                <span>Update Required</span>
						                To play the media you will need to either update your browser to a recent version or update your <a href="http://get.adobe.com/flashplayer/" target="_blank">Flash plugin</a>.
						            </div>
						        </div>
							</div>
						</td>
					</tr>
				</c:if>
				
				<tr>
					<td colspan="6">
						<c:choose>
							<c:when test="${boardVO.useAt eq 'Y' }">
								<form action="${ctxPath }/template/jsp/boardContentsView.jsp" name="boardContentsForm" id="boardContentsForm" method="post" target="boardContentsView">
									<input type="hidden" name="nttCn" id="nttCn" value="<c:out value="${boardVO.nttCn }" />">
								</form>
								
								<iframe name="boardContentsView" id="boardContentsView" scrolling="no" frameborder="0" width="100%" onload="autoHeightIframe(this);" style="min-height: 150px;"></iframe>
							</c:when>
							<c:otherwise>
								블라인드 처리된 게시글 입니다.
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</tbody>
		</table>
		
		<c:if test="${boardMasterVO.answerPosblAt eq 'Y' }">
			<%-- 댓글 표시 영역 --%>
			<div id="answerArea"></div>
		</c:if>
	</div>
</div>
<c:import url="/WEB-INF/jsp/courage/tech/commonAjaxHead.jsp" />