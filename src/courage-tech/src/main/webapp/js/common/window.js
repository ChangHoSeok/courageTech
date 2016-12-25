function popupModalDialog(url, arg, width, height, resizable, scroll) {
	var sFeatures = "dialogWidth:"+width+"px; dialogHeight:"+height+"px; resizable:"+resizable+"; scroll:"+scroll;
	return window.showModalDialog(url, arg, sFeatures);//showModalDialog
}
function popupArgModalDialog(url, arg, width, height) {
	var sFeatures = "dialogWidth:"+width+"px; dialogHeight:"+height+"px";
	return window.showModalDialog(url, arg, sFeatures);
}
function popupBaicModalDialog(url, width, height) {
	return popupArgModalDialog(url, window, width, height);
}

/**
 * @type   : method
 * @access : public
 * @desc   : 팝업 윈도우을 출력한다.
 * @sig    : surl, popupwidth, popupheight
 * @param  : surl - 팝업 윈도우에 로드할 URL
 * @param  : popupwidth - 팝업 윈도우 너비
 * @param  : popupheight - 팝업 윈도우 높이
 * @return : 생성된 팝업 윈도우
 */
function popUpWindowOpen(surl, popupwidth, popupheight) {
	if( popupwidth  > screen.width )
		popupwidth = screen.width;
	
	if( popupheight > screen.height )
		popupheight = screen.height;
	
	if( isNaN(parseInt(popupwidth)) ){
		Top  = (screen.availHeight - 600) / 2;
		Left = (screen.availWidth  - 800) / 2;
	} else {
		Top  = (screen.availHeight - popupheight) / 2;
		Left = (screen.availWidth  - popupwidth) / 2;
	}
	
	if (Top < 0) Top = 0;
	if (Left < 0) Left = 0;
	Future = "dependent=yes,fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,left=" + Left + ",top=" + Top + ",width=" + popupwidth + ",height=" + popupheight;
	
	PopUpWindow = window.open("", "PopUp", Future);
	PopUpWindow.location = (surl != "") ? surl : "";
	PopUpWindow.resizeTo(parseInt(popupwidth)+10, parseInt(popupheight)+29);
	PopUpWindow.focus();
	
	return PopUpWindow;
}

/**
 * @type   : method
 * @access : public
 * @desc   : 팝업 윈도우을 출력한다.
 * @sig    : surl, popupwidth, popupheight
 * @param  : surl - 팝업 윈도우에 로드할 URL
 * @param  : popupwidth - 팝업 윈도우 너비
 * @param  : popupheight - 팝업 윈도우 높이
 * @return : 생성된 팝업 윈도우
 */
function popUpWindowOpenScroll(surl, popupwidth, popupheight) {
	if( popupwidth  > screen.width )
		popupwidth = screen.width;
	
	if( popupheight > screen.height )
		popupheight = screen.height;
	
	if( isNaN(parseInt(popupwidth)) ){
		Top  = (screen.availHeight - 600) / 2;
		Left = (screen.availWidth  - 800) / 2;
	} else {
		Top  = (screen.availHeight - popupheight) / 2;
		Left = (screen.availWidth  - popupwidth) / 2;
	}
	
	if (Top < 0) Top = 0;
	if (Left < 0) Left = 0;
	Future = "dependent=yes,fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,left=" + Left + ",top=" + Top + ",width=" + popupwidth + ",height=" + popupheight;
	
	PopUpWindow = window.open("", "PopUp", Future);
	PopUpWindow.location = (surl !=" ") ? surl : "";
	PopUpWindow.resizeTo(parseInt(popupwidth)+10, parseInt(popupheight)+29);
	PopUpWindow.focus();
	
	return PopUpWindow;
}

/**
 * @type   : method
 * @access : public
 * @desc   : 비어있는 팝업 윈도우을 출력한다.
 * @sig    : popupname, popupwidth, popupheight
 * @param  : popupname - 팝업 윈도우 명
 * @param  : popupwidth - 팝업 윈도우 너비
 * @param  : popupheight - 팝업 윈도우 높이
 * @return : 생성된 팝업 윈도우
 */
function popUpWindowOpenOnly(popupname, popupwidth, popupheight) {
    var Top = 0, Left =0;
	if( popupwidth  > screen.width )
		popupwidth = screen.width;
		
	if( popupheight > screen.height )
		popupheight = screen.height;

	if( isNaN(parseInt(popupwidth)) ){
		Top  = (screen.availHeight - 600) / 2;
		Left = (screen.availWidth  - 800) / 2;
	} else {
		Top  = (screen.availHeight - popupheight) / 2;
		Left = (screen.availWidth  - popupwidth) / 2;
	}
	
	if (Top < 0) Top = 0;
	if (Left < 0) Left = 0;
	Future = "dependent=yes,fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,left=" + Left + ",top=" + Top + ",width=" + popupwidth + ",height=" + popupheight;
	
	PopUpWindow = window.open("", popupname, Future);
	
	return PopUpWindow;
}

/**
 * @type   : method
 * @access : public
 * @desc   : 비어있는 팝업 윈도우을 지정된 TOP, LEFT에출력한다.
 * @sig    : popupname, popupwidth, popupheight, top, left
 * @param  : popupname - 팝업 윈도우 명
 * @param  : popupwidth - 팝업 윈도우 너비
 * @param  : popupheight - 팝업 윈도우 높이
 * @param  : top - 팝업이 표시될 top위치
 * @param  : left - 팝업이 표시될 left위치
 * @return : 생성된 팝업 윈도우
 */
function popUpWindowLocationOpenOnly(popupname, popupwidth, popupheight, top, left) {
	if( popupwidth  > screen.width )
		popupwidth = screen.width;
		
	if( popupheight > screen.height )
		popupheight = screen.height;
	
	if(top > screen.height || top < 0)
		top = 0;
	
	if(left > screen.width || left < 0)
		left = 0;
	
	Future = "dependent=yes,fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,left=" + left + ",top=" + top + ",width=" + popupwidth + ",height=" + popupheight;
	
	PopUpWindow = window.open("", popupname, Future);
	
	childNames = window.document.getElementsByName('_popupname');
	if (childNames[0] == null) {
		popupnameElement = document.createElement("input");
		popupnameElement.setAttribute("type", "hidden");
		popupnameElement.setAttribute("name", "_popupname");
		popupnameElement.setAttribute("value", popupname);
		window.document.body.appendChild(popupnameElement);
	} else {
		childNames[0].value = popupname;
	}
	
	return PopUpWindow;
}

function PopUpWindowOpenOnlyScroll(popupname, popupwidth, popupheight) {
    var Top = 0, Left =0;
	if( popupwidth  > screen.width )
		popupwidth = screen.width;
		
	if( popupheight > screen.height )
		popupheight = screen.height;

	if( isNaN(parseInt(popupwidth)) ){
		Top  = (screen.availHeight - 600) / 2;
		Left = (screen.availWidth  - 800) / 2;
	} else {
		Top  = (screen.availHeight - popupheight) / 2;
		Left = (screen.availWidth  - popupwidth) / 2;
	}
	
	if (Top < 0) Top = 0;
	if (Left < 0) Left = 0;
	Future = "dependent=yes,fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,left=" + Left + ",top=" + Top + ",width=" + popupwidth + ",height=" + popupheight;
	
	PopUpWindow = window.open("", popupname, Future);
	
	childNames = window.document.getElementsByName('_popupname');
	if (childNames[0] == null) {
		popupnameElement = document.createElement("input");
		popupnameElement.setAttribute("type", "hidden");
		popupnameElement.setAttribute("name", "_popupname");
		popupnameElement.setAttribute("value", popupname);		
		window.document.body.appendChild(popupnameElement);
	} else {
		childNames[0].value = popupname;
	}
	
	return PopUpWindow;
}

/**
 * Form에 동적으로 hidden값 세팅
 * @param name 동적추가되는 hidden태그 name, id
 * @param value 동적추가되는 hidden태그 value
 */
function addFormParameter(formId, inputId, inputValue) {
	if(formId == "") {
		$jquery("form").each(function(idx, obj) {
			var parameter = "<input type=\"hidden\" name=\""+inputId+"\" id=\""+inputId+"_"+idx+"\" value=\"\" />";
			if(obj.id != "") {
				if($jquery("#"+obj.id+" input[name="+inputId+"]").val() == undefined) {
					$jquery("#"+obj.id).append(parameter);
				}
				$jquery("#"+obj.id+" input[name="+inputId+"]").val(inputValue);
			}
		});
	}else {
		var parameter = "<input type=\"hidden\" name=\""+inputId+"\" id=\""+inputId+"\" value=\"\" />";
		
		if($jquery("#"+formId+" input[name='"+inputId+"']").val() == undefined) {
			$jquery("#"+formId).append(parameter);
		}
		$jquery("#"+formId+" input[name='"+inputId+"']").val(inputValue);
	}
}

function removeFormParameter(formId, inputId) {
	if (formId == "") {
		$jquery("form").each(function(idx, obj) {
			$jquery("input[name=" + inputId + "]");
		});
	} else {
		$jquery("#" + formId + " input[name=" + inputId + "]").remove();
	}
}

/**
 * popupForm을 초기화 한다.
 */
function popupFormInit() {
	$jquery('#popupForm input[type=hidden]').remove();
	$jquery('#popupForm').append('<input type="hidden" name="openerKey" id="openerKey" value="" />');
	$jquery('#popupForm').append('<input type="hidden" name="returnFunction" id="returnFunction" value="" />');
}