/**
 * @license Copyright (c) 2003-2016, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	
	// 허용하지 않는 테그
	config.disallowedContent = 'script; *[on*]';
	
	// skin 설정
	config.skin = 'moonocolor';
	
	// 내설정
	config.enterMode = CKEDITOR.ENTER_BR;
	config.shiftEnterMode = CKEDITOR.ENTER_DIV;
	config.filebrowserUploadUrl = jsContextPath + '/ckUploader';
	
	// PlugIn 설정
	config.extraPlugins = 'lineutils,font,chart,lineutils,widget,image2,codesnippet,youtube';
	config.removePlugins = 'flash,iframe';
	
	// 코드 스페닛 설정
	config.codeSnippet_theme = 'monokai_sublime';
	
	/*
	config.extraPlugins = 'mathjax';
	config.mathJaxClass = 'my-math';
	config.mathJaxLib = '//cdn.mathjax.org/mathjax/2.6-latest/MathJax.js?config=TeX-AMS_HTML';
	*/
};
