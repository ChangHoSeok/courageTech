var LeftMenu = {
	MENU_PREFIX : "CTM_",
	MENU_LIST : null,

	menuInit : function(){
		$jquery("#accrodionMenu").html("");
		
		$jquery("#join").button({
			icons : {
				primary : "ui-icon-person"
			}
		});
		
		$jquery("#login").button({
			icons : {
				primary : "ui-icon-key"
			}
		});
		
		$jquery.post(jsContextPath + "/uat/login/retrieveLoginUserMenuListJson.tech", function(data) {
			LeftMenu.MENU_LIST = data.menuList;
			LeftMenu.appendMenu(LeftMenu.MENU_LIST[0]);
			
			$jquery(".topnav").accordion({
				accordion:false,
				speed: 500,
				closedSign: '[+]',
				openedSign: '[-]',
				cookieId: 'courageMenuInfo'
			});
			
			$jquery('#scrollMenu').animate({
				scrollTop : $jquery("#scrollMenu li.active").offset().top - 135
			}, 500);
		});
	},
	
	appendMenu : function(menuObj) {
		var tagA = $jquery("<a>");
		var tagLi = $jquery("<li>");
		
		tagA.html(menuObj.menuNm);
		
		// 메뉴 연결 링크
		if (menuObj.url != null) {
			var linkUrl = jsContextPath + menuObj.url + "?menuNo=" + menuObj.menuId + (isEmpty(menuObj.intrmdVriabl) ? "" : "&" + menuObj.intrmdVriabl);
			if (menuObj.menuId == LeftMenu.getMenuNo()) {
				tagLi.attr("class", "active");
			}
			
			tagA.attr("href", linkUrl);
		} else {
			tagA.attr("href", "#");
		}
		
		tagLi.attr("id", LeftMenu.MENU_PREFIX + menuObj.menuId);
		tagLi.append(tagA);
		
		if (menuObj.upperMenuId !== null && menuObj.upperMenuId !== undefined && menuObj.upperMenuId !== "") {
			if ($jquery("#" + LeftMenu.MENU_PREFIX + menuObj.upperMenuId).length <= 0) {
				$jquery("#accrodionMenu").append(tagLi);
			} else {
				if ($jquery("#" + LeftMenu.MENU_PREFIX + menuObj.upperMenuId + " > ul").length <= 0) {
					$jquery("#" + LeftMenu.MENU_PREFIX + menuObj.upperMenuId).append("<ul>");
				}
				
				$jquery("#" + LeftMenu.MENU_PREFIX + menuObj.upperMenuId + " > ul").append(tagLi);
			}
		}
		
		if (menuObj.subMenuList != null && menuObj.subMenuList.length > 0) {
			for(var i = 0; i < menuObj.subMenuList.length; i++) {
				LeftMenu.appendMenu(menuObj.subMenuList[i]);
			}
		}
	},
	
	/**
	 * 개요 : 사용자 로그인
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 11. 21.
	 * @param 
	 */
	login : function(thisObj) {
		location.href = jsContextPath + "/uat/login/formLogin.tech";
	},
	
	/**
	 * 개요 : 사용자 로그아웃
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 4. 22.
	 * @param 
	 */
	logout : function(thisObj) {
		if (confirm("로그아웃 하시겠습니까?")) {
			location.href = jsContextPath + "/uat/login/logout.tech";
		}
	},
	
	/**
	 * 개요 : 현재 위치의 menuNo 조회
	 * 
	 * @Author : ChangHo Seok
	 * @Date : 2016. 6. 23.
	 * @param 
	 */
	getMenuNo : function() {
		var parameters = location.search.replace("?", "").split("&");
		
		if (parameters.length > 0) {
			for (var i = 0; i < parameters.length; i++) {
				if (parameters[i].startsWith("menuNo")) {
					return parameters[i].split("=")[1];
				}
			}
		}
		
		return undefined;
	},
	
	/**
	 * 개요 : 마이페이지 조회
	 * 
	 * @Author : schkk
	 * @Date : 2016. 12. 2.
	 * @param 
	 */
	myInfo : function(thisObj) {
		location.hash = "AC=/system/user/retrieveUserSbscrbDetail.tech&VA=content_body";
	}
};