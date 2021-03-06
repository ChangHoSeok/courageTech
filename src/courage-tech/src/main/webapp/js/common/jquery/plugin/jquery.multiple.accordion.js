/*
 * jQuery UI Multilevel Accordion v.1
 * 
 * Copyright (c) 2011 Pieter Pareit
 *
 * http://www.scriptbreaker.com
 *
 */

//plugin definition
(function($){
    $.fn.extend({

    //pass the options variable to the function
    accordion: function(options) {
        
		var defaults = {
			accordion: 'true',
			speed: 300,
			closedSign: '[+]',
			openedSign: '[-]',
			cookieId : ''
		};

		// Extend our default options with those provided.
		var opts = $.extend(defaults, options);
		//Assign current element to variable, in this case is UL element
 		var $this = $(this);
 		
 		//add a mark [+] to a multilevel menu
 		$this.find("li").each(function() {
 			if($(this).find("ul").size() != 0){
 				//add the multilevel sign next to the link
 				$(this).find("a:first").append("<span>"+ opts.closedSign +"</span>");
 				
 				//avoid jumping to the top of the page when the href is an #
 				if($(this).find("a:first").attr('href') == "#"){
 		  			$(this).find("a:first").click(function(){return false;});
 		  		}
 			}
 		});
 		
 		// Cookie에 저장된 값이 있으면 설정한다.
 		if (opts.cookieId !== "" && opts.cookieId !== undefined) {
 			try {
 				var openAccordion = $.cookie(opts.cookieId);
 				
 				if (openAccordion != "" && openAccordion != null && openAccordion != undefined) {
 					var menus = openAccordion.split(";");
 					
 					$(menus).each(function(index, value) {
 						$($this).find("li#" + value).addClass("open");
 					});
 				}
 			} catch (error) {
 				$.cookie(opts.cookieId, null);
 			}
 		}

 		//open active level
 		$this.find("li.active").each(function() {
 			$(this).parents("ul").show();
 			$(this).parents("ul").parent("li").find("span:first").html(opts.openedSign);
 		});
 		
 		//open
 		$this.find("li.open").each(function() {
 			$(this).find(" > ul").show();
 			$(this).find(" > ul").parent("li").find("span:first").html(opts.openedSign);
 		});

  		$this.find("li a").click(function() {
  			if($(this).parent().find("ul").size() != 0){
  				if(opts.accordion){
  					//Do nothing when the list is open
  					if(!$(this).parent().find("ul").is(':visible')){
  						parents = $(this).parent().parents("ul");
  						visible = $this.find("ul:visible");
  						visible.each(function(visibleIndex){
  							var close = true;
  							parents.each(function(parentIndex){
  								if(parents[parentIndex] == visible[visibleIndex]){
  									close = false;
  									return false;
  								}
  							});
  							if(close){
  								if($(this).parent().find("ul") != visible[visibleIndex]){
  									$(visible[visibleIndex]).slideUp(opts.speed, function(){
  										$(this).parent("li").find("span:first").html(opts.closedSign);
  									});
  									
  								}
  							}
  						});
  					}
  				}
  				if($(this).parent().find("ul:first").is(":visible")){
  					$(this).parent().find("ul:first").slideUp(opts.speed, function(){
  						$(this).parent("li").find("span:first").delay(opts.speed).html(opts.closedSign);
  					});
  					
  					$(this).parent("li").removeClass("open").addClass("close");
  				}else{
  					$(this).parent().find("ul:first").slideDown(opts.speed, function(){
  						$(this).parent("li").find("span:first").delay(opts.speed).html(opts.openedSign);
  					});
  					
  					$(this).parent("li").removeClass("close").addClass("open");
  				}
  			}
  			
  			if (opts.cookieId !== "" && opts.cookieId !== undefined) {
  				var openAccordion = "";
  				
  				$($this).find("li.open").each(function() {
  					openAccordion += $(this).attr("id") + ";";
  				});
  				
  				if (openAccordion !== "") {
  					openAccordion = openAccordion.substr(0, openAccordion.length - 1);
  					
  					$.cookie(opts.cookieId, null);
  	  				$.cookie(opts.cookieId, openAccordion, {expires : 7, path : '/'});
  				}
  			}
  		});
    }
});
})(jQuery);