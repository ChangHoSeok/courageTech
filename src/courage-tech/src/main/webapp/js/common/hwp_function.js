	/*******************************************************************
		기본설정
	*******************************************************************/
	var BasePath = m_host;								// 현재 접속된 페이지의 홈	
	var MinVersion = 0x0505010B;			
	var _DEBUG = false; 								// Debug mode.
	var pHwpCtrl;			
	var NS = String.fromCharCode(0x00A0); 				// 0x00A0 : Latin-1 NO-BREAK SPACE
	
	/*******************************************************************
		한글 버전 확인
	*******************************************************************/	
	function _VerifyVersion(){
		if(pHwpCtrl.getAttribute("Version") == null){
			alert("한글 2002 컨트롤이 설치되지 않았습니다.");
			return false;
		}
		//버젼 확인
		CurVersion = pHwpCtrl.Version;
		if(CurVersion < MinVersion){
			alert("HwpCtrl의 버젼이 낮아서 정상적으로 동작하지 않을 수 있습니다.\n"+
		"최신 버젼으로 업데이트하기를 권장합니다.\n\n"+
		"현재 버젼:" + CurVersion + "\n"+
		"권장 버젼:" + MinVersion + " 이상");
		}
		return true;
	}
	
	/*******************************************************************
 		한글 현재 페이지 확인
	 *******************************************************************/	
	function OnGetPage(){
		var curPage = 0;   // 현재 페이지
		var act = pHwpCtrl.CreateAction("DocumentInfo"); // 문서 정보 액션을 생성
		var set = act.CreateSet();     //문서 정보 액션 실행에 필요한 파라미터 셋 생성
		act.GetDefault(set);
		set.SetItem("DetailInfo", 1);    //문서에 대해 자세한 정보를 알아 오도록 파라미터 셋 값 설정
		act.Execute(set);      // 액션 실행
		curPage = set.Item("DetailCurPage");   // 현재 페이지 정보를 파라미터 셋 값으로부터 얻어 옴
		return curPage;
	}

	/*******************************************************************
		한글 ToolBar 설정
	 *******************************************************************/	
	function InitToolBarJS(){
		HwpControl.HwpCtrl.SetToolBar(-1, "TOOLBAR_MENU");
		HwpControl.HwpCtrl.SetToolBar(-1, "TOOLBAR_STANDARD");
		HwpControl.HwpCtrl.SetToolBar(-1, "TOOLBAR_FORMAT");
		HwpControl.HwpCtrl.SetToolBar(-1, "TOOLBAR_DRAW");
		HwpControl.HwpCtrl.ShowToolBar(true);
		HwpControl.HwpCtrl.ShowStatusBar(true);
	}
	
	/*******************************************************************
		Table 테두리 설정 : 두께는 default로 사용		
		left 	:  테투리 왼쪽
		right 	:  테투리 오른쪽
		top 	:  테투리 위
		bottom 	:  테투리 아래
		
		선 모양 참고	
			0 없음. (NULL)
			1 실선. (SOLID)
			2 긴점선. (DASH)
			3 점선. (DOT)
			4 -.-.-.-.
			5 -..-..-..
			6 HNCDR_LS_DASH보다 긴 선분의 반복. (LONGDASH)
			7 HNCDR_LS_DOT보다 큰 동그미의 반복. (CIRCLE)
			8 2중선. (DOUBLESLIM)
			9 가는선 + 굵은선 2중선. (SLIMTHICK)
			10 굵은선 + 가는선 2중선. (THICKSLIM)
			11 가는선 + 굵은선 + 가는선 3중선. (SLIMTHICKSLIM)
			12 물결. (WAVE)
			13 물결 2중선. (DOUBLEWAVE)
			14 두꺼운 3D. (THICK3D)
			15 두꺼운 3D. 광원 반대. (THICKREV3D)
			16 3D 단선. (3D)
			17 3D 단선. 광원 반대. (REV3D)
	 *******************************************************************/	
	function setBorder(left,right,top,bottom){
		var HAction = pHwpCtrl.CreateAction("CellBorderFill");			//테두리 스타일 지정	
		var borderTypeSet = HAction.CreateSet();
		HAction.GetDefault(borderTypeSet);
		borderTypeSet.SetItem("BorderTypeLeft",left);					
		borderTypeSet.SetItem("BorderTypeRight",right);					
		borderTypeSet.SetItem("BorderTypeTop",top);						
		borderTypeSet.SetItem("BorderTypeBottom",bottom);				
	    HAction.Execute(borderTypeSet);
	}
	
	/*******************************************************************
		글자모양	
		font : 글씨체
		size : 글씨크기
		bold : Bold 효과(0-사용안함,1-사용)
		italic : 기울임 효과(0-사용안함,1-사용)
		underline : 밑줄(0-사용안함,1-사용)
	 *******************************************************************/
	function ChangeCharShape(font,size,bold,italic,underline){
	    var charact = pHwpCtrl.CreateAction("CharShape");			// 글자 모양 
		var charset = charact.CreateSet();	
		charact.GetDefault(charset);
		charset.SetItem("FaceNameHangul",font);						// 한글글씨체
		charset.SetItem("FaceNameLatin",font);						// 영어글씨체
		charset.SetItem("FaceNameOther",font);						// 기타글씨체
		charset.SetItem("FaceNameSymbol",font);						// 모양글씨체
		charset.SetItem("Height",size*100);							// 사이즈 9 (사이즈*100)
		if(bold=="1")charset.SetItem("Bold",1);	
		if(italic=="1")charset.SetItem("Italic",1);	
		if(underline=="1")charset.SetItem("UnderlineType",1);	
		charact.Execute(charset);
	}
	
	/*******************************************************************
		내용입력
		Text : 내용
	 *******************************************************************/	
	function InsertText(Text,font,size,bold,italic,underline){				
		ChangeCharShape(font,size,bold,italic,underline);								
		var dact = pHwpCtrl.CreateAction("InsertText");
		var dset = dact.CreateSet();	
		dset.SetItem("Text", Text);
		dact.Execute(dset);
	}	
	
	/*******************************************************************
		첫번째 즉, 이미 그려져 있는 줄
		FirstCellName : 표의 첫번째 행, 첫번째 열의 셀필드 명.
	 *******************************************************************/
	function TableAppendRowTop(FirstCellName,font,size,bold,italic,underline){
		if (pHwpCtrl.MoveToField(FirstCellName)){					
			pHwpCtrl.Run("TableCellBlockRow");							// 줄 전체 블록		
			ChangeCharShape(font,size,bold,italic,underline); 		
			
			pHwpCtrl.Run("TableResizeDown");			// 한번에 방향키 한번씩 밑으로
	        pHwpCtrl.Run("TableResizeDown");
	        //pHwpCtrl.Run("TableResizeDown");
			pHwpCtrl.Run("Cancel");			
	
			return true;
		}
		else{
			if (_DEBUG)
				alert("셀필드(" + FirstCellName +")가 존재하지 않습니다.");
			return false;
		}
	}
	
	/*******************************************************************
		데이터가 없을때 모든 셀 합치기		
	 *******************************************************************/	
	function TableAppendRowEnd(FirstCellName,font,size,bold,italic,underline){
		if (pHwpCtrl.MoveToField(FirstCellName)){					
			pHwpCtrl.Run("TableCellBlockRow");	
			pHwpCtrl.Run("TableMergeCell");							// 셀 합치기
		    pHwpCtrl.Run("ParagraphShapeAlignCenter");				// 셀 중간으로 정렬				
			ChangeCharShape(font,size,bold,italic,underline); 								        			
			pHwpCtrl.Run("Cancel");
			return true;
		}
		else{
			if (_DEBUG)
				alert("셀필드(" + FirstCellName +")가 존재하지 않습니다.");
			return false;
		}
	}
	
	/*******************************************************************
		해당 테이블의 마지막 행에 이어서 행을 추가한다.
	 *******************************************************************/
	function TableAppendRow(FirstCellName,left,right,top,bottom,font,size,bold,italic,underline){
		if (pHwpCtrl.MoveToField(FirstCellName)){
			pHwpCtrl.Run("TableCellBlock");				// 현재 커서 블록
			pHwpCtrl.Run("TableColPageDown");			// 커서 맨 아래로
			pHwpCtrl.Run("Cancel");
			pHwpCtrl.Run("TableAppendRow");				// 한줄추카
			pHwpCtrl.Run("TableCellBlockRow");			
	
			setBorder(left,right,top,bottom);
			ChangeCharShape(font,size,bold,italic,underline);
	
			pHwpCtrl.Run("TableResizeDown");			// 한번에 방향키 한번씩 밑으로
	        pHwpCtrl.Run("TableResizeDown");
	        //pHwpCtrl.Run("TableResizeDown");
	        //pHwpCtrl.Run("TableResizeDown");
	        //pHwpCtrl.Run("TableResizeDown");
	        //pHwpCtrl.Run("TableResizeDown");
	        //pHwpCtrl.Run("TableResizeDown");	
	        pHwpCtrl.Run("Cancel");
			return true;
		}
		else{
			if (_DEBUG)
				alert("셀필드(" + FirstCellName +")가 존재하지 않습니다.");
			return false;
		}
	}
				
	/*******************************************************************
		해당 테이블의 마지막 행에 이어서 행을 추가하고 처음 2칸을 합친다.
	 *******************************************************************/
	function TableAppendRowMerge(FirstCellName,left,right,top,bottom,font,size,bold,italic,underline){
		if (pHwpCtrl.MoveToField(FirstCellName)){
			pHwpCtrl.Run("TableCellBlock");
			pHwpCtrl.Run("TableColPageDown");
			pHwpCtrl.Run("Cancel");
			pHwpCtrl.Run("TableAppendRow");
			pHwpCtrl.Run("TableCellBlockRow");
	
			setBorder(left,right,top,bottom);
			ChangeCharShape(font,size,bold,italic,underline); 
			
	        pHwpCtrl.Run("TableResizeDown");
	        pHwpCtrl.Run("TableResizeDown");
	        //pHwpCtrl.Run("TableResizeDown");
	        //pHwpCtrl.Run("TableResizeDown");			        			        			
	        pHwpCtrl.Run("Cancel");
	
	        pHwpCtrl.Run("TableColBegin");				// 커서 맨 앞으로
		    pHwpCtrl.Run("TableCellBlock");				// 블록 선택
		    pHwpCtrl.Run("TableCellBlockExtend");		// 셀블럭 연장(F5 + F5)
	        pHwpCtrl.Run("TableRightCell");			    // 오른쪽으로 한칸이동  	
	        pHwpCtrl.Run("TableMergeCell");				// 셀 합치기
	        pHwpCtrl.Run("ParagraphShapeAlignCenter");	// 셀 중간으로 정렬			   	     
	       	pHwpCtrl.Run("Cancel");
			return true;
		}
		else{
			if (_DEBUG)
				alert("셀필드(" + FirstCellName +")가 존재하지 않습니다.");
			return false;
		}
	}
	/*******************************************************************
		현재 커서위치부터 행의 마지막까지 text를 추가한다.
		ColumnArray : 삽입할 text배열
	 *******************************************************************/
	function TableColumnContents(ColumnArray){
		if (pHwpCtrl.ParentCtrl.CtrlID == "tbl"){ 				// 테이블 내에 커서가 있는가?

			var i;
			var size;
			var dact = pHwpCtrl.CreateAction("InsertText");
			var dset = dact.CreateSet();
			
			size = ColumnArray.length;
			pHwpCtrl.Run("TableColBegin");
			
			for (i = 0;i < size; i++){
				dset.SetItem("Text", ColumnArray[i]);
				dact.Execute(dset);															
				pHwpCtrl.Run("TableRightCell");					// 오른쪽으로 한칸 이동
				pHwpCtrl.Run("Cancel");
				
			}
			return true;
		}
		else{
			if (_DEBUG)
				alert("현재 커서의 위치가 표의 내부가 아님.");
			return false;
		}
	}	
	
	/*******************************************************************
		표중간에 마지막 행을 추가하고, 내용을 채운다.
	 *******************************************************************/	
	function TableAppendRowContents(FirstCellName,left,right,top,bottom,font,size,bold,italic,underline,ColumnArray){
		if(TableAppendRow(FirstCellName,left,right,top,bottom,font,size,bold,italic,underline))
			TableColumnContents(ColumnArray);
	}
	
	/*******************************************************************
		원본의 기본, 즉 맨위줄에 내용을 채운다.
	 *******************************************************************/	
	function TableAppendRowContentsTop(FirstCellName,font,size,bold,italic,underline,ColumnArray){
		if(TableAppendRowTop(FirstCellName,font,size,bold,italic,underline))
			TableColumnContents(ColumnArray);
	}
	
	/*******************************************************************
		표에 마지막 행을 추가하고, 내용을 채운다.
	 *******************************************************************/
	function TableAppendRowContentsDown(FirstCellName,left,right,top,bottom,font,size,bold,italic,underline,ColumnArray){
		if(TableAppendRowMerge(FirstCellName,left,right,top,bottom,font,size,bold,italic,underline))
			TableColumnContents(ColumnArray);
	}
	
	/*******************************************************************
		데이터가 없을때 셀 전체 합친후 내용을 채운다.
	 *******************************************************************/
	function TableAppendRowContentsEnd(FirstCellName,font,size,bold,italic,underline, ColumnArray){
		if(TableAppendRowEnd(FirstCellName,font,size,bold,italic,underline))
			TableColumnContents(ColumnArray);
	}
	
	/*******************************************************************
		문서 쪽수 확인
	 *******************************************************************/
	function getDocumentPage() {
		var actDocSummaryInfo = pHwpCtrl.CreateAction("DocSummaryInfo");
		var setSummaryInfo = actDocSummaryInfo.CreateSet();
		actDocSummaryInfo.GetDefault(setSummaryInfo);
		
		return setSummaryInfo.Item("Pages");
	}
	
	/*******************************************************************
		Hwp Control => binary text
	 *******************************************************************/
	function getHwpBinary() {
		var binary = pHwpCtrl.GetTextFile("HWP", "");
		
		return binary;
	}
	
	/*******************************************************************
		Binary String => Hwp Control
	 *******************************************************************/
	function setHwpBinary(binary) {
		var resultFlag = true;
		if (pHwpCtrl.SetTextFile(binary, "HWP", "") == 0) {
			resultFlag = false;
		}
		
		return resultFlag;
	}