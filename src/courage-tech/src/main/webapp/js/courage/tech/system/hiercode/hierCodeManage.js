var HierCodeManage = {
	FORM_ID : "formHierCodeManage",
	PAGE_INDEX_ID : "pageIndex",
	
	fn_egov_link_page : function(pageIndex) {
		HierCodeManage.setPageIndex(pageIndex);
		HierCodeManage.refreshList();
	},
	
	/**
	 * 개요 : 페이지 인덱스 설정
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 06
	 * @param pageIndex
	 */
	setPageIndex : function(pageIndex) {
		$jquery("#" + HierCodeManage.FORM_ID + " #" + this.PAGE_INDEX_ID).val(pageIndex);
	},
	
	/**
	 * 개요 : 트리조회
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 29
	 * @param pageIndex
	 */
	searchTree : function () {
		
		var url = jsContextPath + "/system/hiercode/retrieveHierCodeJsonResult.tech";
		
		$jquery("#jsTreeDiv").jstree({
			"plugins" : ["themes","json_data","ui","cookies"],
			"json_data" : {
				"ajax" : {
					"type"	: "POST",
					"url"	: url,
					"data"	: function (node) {
						return {
							groupId : node.attr ? $jquery(node).data('groupId') : $jquery('#hierGroupCd :selected').val(),
							upperCode : node.attr ? $jquery(node).data('code') : 0
						}; 
					}
				}
			},
			"themes" : {
				"theme" : "courage-blue",
				"dots" : false,
				"icons" : true
			},
			"cookies" : {
				"save_selected" : "heirCode_selected",
				"save_opened" : "heirCode_opened"
			}
		}).bind("select_node.jstree", function (event, data) {
			$jquery("#" + HierCodeManage.FORM_ID + " #groupCdFlag").val($jquery(data.rslt.obj).data('groupCdFlag'));
			$jquery("#" + HierCodeManage.FORM_ID + " #groupId").val($jquery(data.rslt.obj).data('groupId'));
			$jquery("#" + HierCodeManage.FORM_ID + " #code").val($jquery(data.rslt.obj).data('code'));
			
			HierCodeManage.hierCodeDetailView();
		}).delegate("a", "click", function (event, data) {
			event.preventDefault();
		});
	},
	
	/**
	 * 개요 : 트리 새로고침
	 * 
	 * @Author : Seok Chang Ho
	 * @Date : 2013. 11. 30
	 * @param pageIndex
	 */
	refreshTree : function () {
		$jquery("#jsTreeDiv").empty();
		HierCodeManage.searchTree();
	},
	
	hierCodeDetailView : function () {
		$jquery('#data_list_area_right').ajaxload(
			'blockLoad',
			jsContextPath + '/system/hiercode/retrieveHierCodeDetail.tech',
			"POST",
			"html",
			$jquery("#" + HierCodeManage.FORM_ID).separator('separatorRemoveForm').serialize()
		);
	}
};
