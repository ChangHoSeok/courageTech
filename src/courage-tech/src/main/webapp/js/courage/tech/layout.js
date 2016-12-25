/**
 * courage....
 * 내가 했지만... 답이 안나오는 정리네...
 */
var Layout = {
	topHeight : 0, // 해더
	contentMinHeight : 0, // 컨텐츠
	contentInfoMinHeight : 0, // 컨텐츠 고정공간
	footerHeight : 0, // 푸터
	etcHeight : 0, // 사이드 내의 고정공간
	sideTreeMinHeight : 0, // 사이드 내의 트리뷰
	sideMinHeight : 0, // 사이드
	listAreaMinHeight : 0, // 컨텐츠
	staticHeightArea : 0, // 고정높이를 차지하는 영역
	tableListHeadHeight : 0, // 테이블 해더
	tableListMinHeight : 0,
	paginationHeight : 0, // 페이징 영역
	windowMinWidth : 1270, // 최소넓이
	windowMinHeight : 650, // 최소높이
	scrollWidth : 36, // 스크롤 넓이
	scrollHeight : 18, // 스크롤 높이

	_fnGetOuterHeight : function(selectorID) {
		var resultVar = 0;
		
		$jquery(selectorID + ':visible').each(function() {
			resultVar += $jquery(this).outerHeight();
		});
		
		if (resultVar == null) {
			resultVar = 0;
		}

		return resultVar;
	},
	
	_fnGetOuterWidth : function(selectorID) {
		var resultVar = $jquery(selectorID + ':visible').outerWidth();

		if (resultVar == null) {
			resultVar = 0;
		}

		return resultVar;
	},

	_fnElementsExists : function(selectorID) {
		if ($jquery(selectorID + ':visible').prop('id') == '') {
			return false;
		} else {
			return true;
		}
	},

	_fnSetHeight : function(selectorID, height) {
		if (this._fnElementsExists(selectorID)) {
			$jquery(selectorID).css('height', height);
		}
	},
	
	_fnSetWidth : function(selectorID, height) {
		if (this._fnElementsExists(selectorID)) {
			$jquery(selectorID).css('width', height);
		}
	},

	resize : function() {
		// 고정적으로 차지하는 영역들
		topHeight = this._fnGetOuterHeight("#header"); // 해더
		staticHeightArea = this._fnGetOuterHeight(".staticHeightArea"); // 고정높이 차지 영역
		contentInfoMinHeight = this._fnGetOuterHeight("#contentInfo");
		footerHeight = this._fnGetOuterHeight("#footer"); // 푸터
		
		contentMinHeight = Layout.windowMinHeight - topHeight - footerHeight; // 컨텐츠

		etcHeight = this._fnGetOuterHeight('#leftBtnFix'); // 사이드 내의 고정공간
		paginationHeight = this._fnGetOuterHeight('#pagination'); // 페이징 영역

		sideTreeMinHeight = contentMinHeight - etcHeight; // 사이드 내의 트리뷰
		sideMinHeight = etcHeight + sideTreeMinHeight; // 사이드

		listAreaMinHeight = contentMinHeight - contentInfoMinHeight - staticHeightArea; // 컨텐츠
		tableListHeadHeight = this._fnGetOuterHeight('#listTableFix'); // 테이블 해더
		tableListMinHeight = listAreaMinHeight - tableListHeadHeight - paginationHeight;

		// 서브콘텐츠
		sideTreeMinWidth = this._fnGetOuterWidth("#scrollMenu");

		// tableList
		if ($jquery(window).height() < tableListMinHeight + contentInfoMinHeight + staticHeightArea + topHeight + footerHeight + tableListHeadHeight + paginationHeight) {
			this._fnSetHeight('#table_list', tableListMinHeight);
		} else {
			this._fnSetHeight('#table_list', $jquery(window).height() - topHeight - footerHeight - contentInfoMinHeight - staticHeightArea - tableListHeadHeight - paginationHeight);
		}

		// data_list_area
		if ($jquery(window).height() < listAreaMinHeight + contentInfoMinHeight + staticHeightArea + topHeight + footerHeight) {
			this._fnSetHeight('#data_list_area', listAreaMinHeight);
		} else {
			this._fnSetHeight('#data_list_area', $jquery(window).height() - topHeight - footerHeight - contentInfoMinHeight - staticHeightArea);
		}

		// side tree view
		if ($jquery(window).height() < sideTreeMinHeight + topHeight + footerHeight + etcHeight) {
			this._fnSetHeight('#scrollMenu', sideTreeMinHeight);
		} else {
			this._fnSetHeight('#scrollMenu', $jquery(window).height() - topHeight - footerHeight - etcHeight);
		}

		// navigation
		if ($jquery(window).height() < sideMinHeight + topHeight + footerHeight) {
			this._fnSetHeight('#navigation', sideMinHeight);
			this._fnSetHeight('#content', sideMinHeight);
		} else {
			this._fnSetHeight('#navigation', $jquery(window).height() - topHeight - footerHeight);
			this._fnSetHeight('#content', $jquery(window).height() - topHeight - footerHeight);
		}
		
		// inner content width
		this._fnSetWidth("#data_list_area_right", $jquery(window).width() - sideTreeMinWidth - 20);

		// 전체 layout
		if ($jquery(window).height() < Layout.windowMinHeight - Layout.scrollHeight) {
			this._fnSetHeight('#wrap', Layout.windowMinHeight);
			$jquery('html').css('overflow-y', 'auto');
		} else {
			this._fnSetHeight('#wrap', $jquery(window).height());
			$jquery('html').css('overflow-y', 'hidden');
		}
		
		if ($jquery(window).width() < Layout.windowMinWidth - Layout.scrollWidth) {
			this._fnSetWidth('#wrap', Layout.windowMinWidth);
			$jquery('html').css('overflow-x', 'auto');
		} else {
			this._fnSetWidth('#wrap', $jquery(window).width());
			$jquery('html').css('overflow-x', 'hidden');
		}
	}
};

$jquery(window).resize(function() {
	Layout.resize();
});

$jquery(document).ready(function() {
	var effctShow = false;
	
	$jquery('#section_cen_fix').mousewheel(function(event) {
		if (event.deltaY < 0) {
			var element = $jquery('#table_list');
			
			if ((element !== undefined && element !== null) && (element[0] !== undefined) && element[0].scrollHeight > element.outerHeight() && (element[0].scrollHeight - element.scrollTop() == element.outerHeight())) {
				if (!effctShow) {
					effctShow = true;
					
					$jquery('#footer').effect('highlight', {}, 800, function() {
						effctShow = false;
					});
				}
			}
		}
	});
});