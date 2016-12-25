/*
 * Form removeClassRegEx 1.0, jQuery plugin
 *
 * Copyright(c) 2013, Seok ChangHo
 * http://tech.courage.pe.kr
 * class 명을 정규표현식으로 검색
 */
(function($)
{
    $.fn.removeClassRegEx = function(regex)
    {
        var classes = $(this).attr('class');

        if(!classes || !regex) return false;

        var classArray = [];
        classes = classes.split(' ');

        for(var i=0, len=classes.length; i<len; i++) if(!classes[i].match(regex)) classArray.push(classes[i]);

        $(this).attr('class', classArray.join(' '));
    };
    
    $.fn.hasClassRegEx = function(regex)
    {
        var classes = $(this).attr('class');
        
        if(!classes || !regex) return false;
        
        classes = classes.split(' ');
        
        for(var i=0, len=classes.length; i < len; i++)
            if(classes[i].match(regex)) return true;
        
        return false;
    };
})(jQuery);