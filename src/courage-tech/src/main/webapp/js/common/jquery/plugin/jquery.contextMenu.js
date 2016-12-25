// jQuery Context Menu Plugin
//
// Version 1.01
//
// Cory S.N. LaViska
// A Beautiful Site (http://abeautifulsite.net/)
//
// More info: http://abeautifulsite.net/2008/09/jquery-context-menu-plugin/
//
// Terms of Use
//
// This plugin is dual-licensed under the GNU General Public License
//   and the MIT License and is copyright A Beautiful Site, LLC.
//
if(jQuery)( function() {
	$jquery.extend($jquery.fn, {
		
		contextMenu: function(o, callback) {
			// Defaults
			if( o.menu == undefined ) return false;
			if( o.inSpeed == undefined ) o.inSpeed = 150;
			if( o.outSpeed == undefined ) o.outSpeed = 75;
			// 0 needs to be -1 for expected results (no fade)
			if( o.inSpeed == 0 ) o.inSpeed = -1;
			if( o.outSpeed == 0 ) o.outSpeed = -1;
			// Loop each context menu
			$jquery(this).each( function() {
				var el = $jquery(this);
				var offset = $jquery(el).offset();
				// Add contextMenu class
				$jquery('#' + o.menu).addClass('contextMenu');
				// Simulate a true right click
				$jquery(this).mousedown( function(e) {
					var evt = e;
					evt.stopPropagation();					
					$jquery(this).mouseup( function(e) {						
						e.stopPropagation();
						var srcElement = $jquery(this);
						$jquery(this).unbind('mouseup');
						
						// mouse click left : 1 , right : 2
						if( evt.button != 2 ) {							
							// Hide context menus that may be showing
							$jquery(".contextMenu").hide();
							// Get this context menu
							var menu = $jquery('#' + o.menu);
							
							if( $jquery(el).hasClass('disabled') ) return false;
							
							// Detect mouse position
							var d = {}, x, y;
							if( self.innerHeight ) {
								d.pageYOffset = self.pageYOffset;
								d.pageXOffset = self.pageXOffset;
								d.innerHeight = self.innerHeight;
								d.innerWidth = self.innerWidth;
							} else if( document.documentElement &&
								document.documentElement.clientHeight ) {
								d.pageYOffset = document.documentElement.scrollTop;
								d.pageXOffset = document.documentElement.scrollLeft;
								d.innerHeight = document.documentElement.clientHeight;
								d.innerWidth = document.documentElement.clientWidth;
							} else if( document.body ) {
								d.pageYOffset = document.body.scrollTop;
								d.pageXOffset = document.body.scrollLeft;
								d.innerHeight = document.body.clientHeight;
								d.innerWidth = document.body.clientWidth;
							}
							(e.pageX) ? x = e.pageX-180 : x = e.clientX + d.scrollLeft;
							(e.pageY) ? y = e.pageY-50 : y = e.clientY + d.scrollTop;
							
							// Show the menu
							$jquery(document).unbind('click');
							$jquery(menu).css({ top: y, left: x }).fadeIn(o.inSpeed);
							// Hover events
							$jquery(menu).find('A').mouseover( function() {
								$jquery(menu).find('LI.hover').removeClass('hover');
								$jquery(this).parent().addClass('hover');
							}).mouseout( function() {
								$jquery(menu).find('LI.hover').removeClass('hover');
							});
							
							// Keyboard
							$jquery(document).keypress( function(e) {
								switch( e.keyCode ) {
									case 38: // up
										if( $jquery(menu).find('LI.hover').size() == 0 ) {
											$jquery(menu).find('LI:last').addClass('hover');
										} else {
											$jquery(menu).find('LI.hover').removeClass('hover').prevAll('LI:not(.disabled)').eq(0).addClass('hover');
											if( $jquery(menu).find('LI.hover').size() == 0 ) $jquery(menu).find('LI:last').addClass('hover');
										}
									break;
									case 40: // down
										if( $jquery(menu).find('LI.hover').size() == 0 ) {
											$jquery(menu).find('LI:first').addClass('hover');
										} else {
											$jquery(menu).find('LI.hover').removeClass('hover').nextAll('LI:not(.disabled)').eq(0).addClass('hover');
											if( $jquery(menu).find('LI.hover').size() == 0 ) $jquery(menu).find('LI:first').addClass('hover');
										}
									break;
									case 13: // enter
										$jquery(menu).find('LI.hover A').trigger('click');
									break;
									case 27: // esc
										$jquery(document).trigger('click');
									break
								}
							});
							
							// When items are selected
							$jquery('#' + o.menu).find('A').unbind('click');
							$jquery('#' + o.menu).find('LI:not(.disabled) A').click( function() {
								$jquery(document).unbind('click').unbind('keypress');
								$jquery(".contextMenu").hide();
								// Callback
								if( callback ) callback( $jquery(this).attr('href').substr(1), $jquery(srcElement), {x: x - offset.left, y: y - offset.top, docX: x, docY: y} );
								return false;
							});
							/*
							// Hide bindings
							setTimeout( function() { // Delay for Mozilla
								$jquery(document).click( function() {
									$jquery(document).unbind('click').unbind('keypress');
									$jquery(menu).fadeOut(o.outSpeed);
									return false;
								});
							}, 0);
							*/
						}
					});
				});
				
				// Disable text selection
				if( $jquery.browser.mozilla ) {
					$jquery('#' + o.menu).each( function() { $jquery(this).css({ 'MozUserSelect' : 'none' }); });
				} else if( $jquery.browser.msie ) {
					$jquery('#' + o.menu).each( function() { $jquery(this).bind('selectstart.disableTextSelect', function() { return false; }); });
				} else {
					$jquery('#' + o.menu).each(function() { $jquery(this).bind('mousedown.disableTextSelect', function() { return false; }); });
				}
				// Disable browser context menu (requires both selectors to work in IE/Safari + FF/Chrome)
				$jquery(el).add($jquery('UL.contextMenu')).bind('contextmenu', function() { return false; });
				
			});
			return $jquery(this);
		},
		
		// Disable context menu items on the fly
		disableContextMenuItems: function(o) {
			if( o == undefined ) {
				// Disable all
				$jquery(this).find('LI').addClass('disabled');
				return( $jquery(this) );
			}
			$jquery(this).each( function() {
				if( o != undefined ) {
					var d = o.split(',');
					for( var i = 0; i < d.length; i++ ) {
						$jquery(this).find('A[href="' + d[i] + '"]').parent().addClass('disabled');
						
					}
				}
			});
			return( $jquery(this) );
		},
		
		// Enable context menu items on the fly
		enableContextMenuItems: function(o) {
			if( o == undefined ) {
				// Enable all
				$jquery(this).find('LI.disabled').removeClass('disabled');
				return( $jquery(this) );
			}
			$jquery(this).each( function() {
				if( o != undefined ) {
					var d = o.split(',');
					for( var i = 0; i < d.length; i++ ) {
						$jquery(this).find('A[href="' + d[i] + '"]').parent().removeClass('disabled');
						
					}
				}
			});
			return( $jquery(this) );
		},
		
		// Disable context menu(s)
		disableContextMenu: function() {
			$jquery(this).each( function() {
				$jquery(this).addClass('disabled');
			});
			return( $jquery(this) );
		},
		
		// Enable context menu(s)
		enableContextMenu: function() {
			$jquery(this).each( function() {
				$jquery(this).removeClass('disabled');
			});
			return( $jquery(this) );
		},
		
		// Destroy context menu(s)
		destroyContextMenu: function() {
			// Destroy specified context menus
			$jquery(this).each( function() {
				// Disable action
				$jquery(this).unbind('mousedown').unbind('mouseup');
			});
			return( $jquery(this) );
		}
		
	});
})(jQuery);