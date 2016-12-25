(function($){
    $.fn.validationEngineLanguage = function(){
    };
    $.validationEngineLanguage = {
        newLang: function(){
            $.validationEngineLanguage.allRules = {
                "required": { // Add your regex rules here, you can take telephone as an example
                    "regex": "none",                 
                    "alertText": "* 필수입력 항목 입니다.",
                    "alertTextCheckboxMultiple": "* 항목을 선택해 주세요",
                    "alertTextCheckboxe": "* 동의가 필요합니다."
                },
                "minSize": {
                    "regex": "none",
                    "alertText": "* 최소 ",
                    "alertText2": " 글자 이상 입력"
                },
                "maxSize": {
                    "regex": "none",
                    "alertText": "* 최대 ",
                    "alertText2": " 글자 입력"
                },
                "min": {
                    "regex": "none",
                    "alertText": "* 최소값 "
                },
                "max": {
                    "regex": "none",
                    "alertText": "* 최대값 "
                },
                "past": {
                    "regex": "none",
                    "alertText": "보다 이전날짜를 등록해 주세요! "
                },
                "future": {
                    "regex": "none",
                    "alertText": "보다 이후날자를 등록해 주세요! "
                },
                "pastYear": {
                    "regex": "none",
                    "alertText": "보다 이전년도를 등록해 주세요! "
                },
                "futureYear": {
                    "regex": "none",
                    "alertText": "보다 이후년도를 등록해 주세요! "
                },
                "pastYearMonth": {
                    "regex": "none",
                    "alertText": "보다 이전년월을 등록해 주세요! "
                },
                "futureYearMonth": {
                    "regex": "none",
                    "alertText": "보다 이후년월을 등록해 주세요! "
                },
                "maxCheckbox": {
                    "regex": "none",
                    "alertText": "* 최대",
                    "alertText2": "개 까지 선택이 가능합니다."
                },
                "minCheckbox": {
                    "regex": "none",
                    "alertText": "* 최소 ",
                    "alertText2": "개 이상의 선택이 필요합니다."
                },
                "equals": {
                    "regex": "none",
                    "alertText": "* 비밀번호가 맞지 않습니다."
                },
                "phone": {
                    // credit: jquery.h5validate.js / orefalo
                    "regex": /^([\+][0-9]{1,3}[ \.\-])?([\(]{1}[0-9]{2,6}[\)])?([0-9 \.\-\/]{3,20})((x|ext|extension)[ ]?[0-9]{1,4})?$/,
                    "alertText": "* 잘못된 전화번호 입니다."
                },
                "mobile": {
                	"regex": /^([0][1][0|1|6|7|8|9])-([0-9]{3,4})-([0-9]{4})$/,
                	"alertText": "* 잘못된 핸드폰번호 형식 입니다. (010-1234-1234)"
                },
                "tel": {
                	"regex": /^([0][2]|[0][3-9]{1}[0-9]{1})-([0-9]{3,4})-([0-9]{4})$/,
                	"alertText": "* 잘못된 전화번호 형식 입니다. (02-1234-1234)"
                },
                "email": {
                    // Simplified, was not working in the Iphone browser
                    "regex": /^([A-Za-z0-9_\-\.\'])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,6})$/,
                    "alertText": "* 잘못된 E-Mail 주소입니다."
                },
                "email1": {
                	// Simplified, was not working in the Iphone browser
                	"regex": /^([A-Za-z0-9_\-\.\']){4,80}$/,
                	"alertText": "* 잘못된 E-Mail 주소입니다. (영문, 숫자 가능 4자리 이상 80자리 미만)"
                },
                "email2": {
                	// Simplified, was not working in the Iphone browser
                	"regex": /^([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,6})$/,
                	"alertText": "* 잘못된 E-Mail 도메인 입니다."
                },
                "post": {
                	"regex": /^\d{3}[\-]\d{3}$/,
                    "alertText": "* 잘못된 우편번호입니다."
                },
                "integer": {
                    "regex": /^[\-\+]?\d+$/,
                    "alertText": "* 숫자만 입력해 주세요( - 가능)" 
                },
                "number": {
                    // Number, including positive, negative, and floating decimal. credit: orefalo
                    "regex": /^[\-\+]?(([0-9]+)([\.,]([0-9]+))?|([\.,]([0-9]+))?)$/,
                    "alertText": "* 숫자만 입력해 주세요( -, 소수점 가능)"
                },
                "date": {
                    // Date in ISO format. Credit: bassistance
                    "regex": /^(19|20)\d{2}[\-](0[1-9]|1[012])[\-](0[1-9]|[12][0-9]|3[0-1])$/,
                    "alertText": "* 잘못된 날짜 형식입니다(YYYY-MM-DD 으로 입력해주세요!)."
                },
                "year": {
                	"regex": /^[12][0-9]{3}$/,
                    "alertText": "* 잘못된 년도 형식입니다(YYYY 으로 입력해주세요!)."
                },
                "yearMonth": {
                	"regex": /^(19|20)\d{2}[\-](0[1-9]|1[012])$/,
                    "alertText": "* 잘못된 년월 형식입니다(YYYY-MM 으로 입력해주세요!)."
                },
                "password" : {
                	"regex": /^(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-]).*/,
                    "alertText": "* 잘못된 패스워드 입니다. 영문자, 숫자, 특수문자 조합으로 입력해주세요."
                },
                "ipv4": {
                    "regex": /^((([01]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))[.]){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))$/,
                    "alertText": "* 잘못된 IP 입력입니다."
                },
                "url": {
                    "regex": /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/,
                    "alertText": "* 잘못된 URL입니다."
                },
                "onlyNumber": {
                    "regex": /^[0-9]+$/, 
                    "alertText": "* 숫자만 가능합니다." 
                },
                "onlyNumberSp": {
                    "regex": /^[0-9\ ]+$/, 
                    "alertText": "* 숫자만 가능합니다." 
                },
                "onlyNumberIF": {
                    "regex": /^([0-9]{1,5})(\.[0-9]{1,5})?$/, 
                    "alertText": "* 숫자만 가능합니다.(정수부 최대 5자리 소수부 최대 5자리 입력가능)" 
                },
                "onlyKoreaEng": {
                	"regex" : /[ㄱ-힣a-zA-Z]/,
                	"alertText": "* 문자만 입력이 가능합니다.",
                	"alertTextNo": "* 문자만 제외하고 입력 가능합니다."
                },
                "onlyKorea": {
                	"regex" : /[ㄱ-힣]/,
                	"alertText": "* 한글명만 입력이 가능합니다.",
                	"alertTextNo": "* 문자(Korean)만 제외하고 입력 가능합니다."
                },
                "onlyLetter": {
                    "regex": /^[a-zA-Z]+$/,
                    "alertText": "* 문자(English)만 가능합니다.",
                    "alertTextNo": "* 문자(English)만 제외하고 입력 가능합니다."
                },
                "onlyLetterSp": {
                    "regex": /^[a-zA-Z\ \']+$/,
                    "alertText": "* 문자(English)만 가능합니다."
                },
                "onlyLetterSpChar": {
                    "regex": /^[a-zA-Z\ \'!@#$%^*+=-_,]+$/,
                    "alertText": "* 문자(English)와 특수문자만 가능합니다."
                },
                "onlyLetterNumberSpChar": {
                    "regex": /^[0-9a-zA-Z\ \'!@#$%^*+=-_,&()-{}|<>"~]+$/,
                    "alertText": "* 문자(English)와 특수문자만 가능합니다."
                },
                "onlyLetterNumber": {
                    "regex": /^[0-9a-zA-Z]+$/,
                    "alertText": "* 숫자와 문자(English)만 가능합니다."
                },
                "onlyLetterNumberSp": {
                	"regex" : /^[a-zA-Z0-9\s]+$/,
                	"alertText": "* 숫자와 문자(English)만 가능합니다."
                },
                "onlyLetterKorean": {
                	"regex" : /^[가-힣0-9]+$/,
                	"alertText": "* 숫자와 문자(Korean)만 가능합니다."
                },
                "onlyLetterKoreanSp": {
                	"regex" : /^[가-힣0-9\s\'!@#$%^&*()_+,]+$/,
                	"alertText": "* 숫자와 문자(Korean)만 가능합니다."
                },
                "onlyDate": {
                    "regex": /^\d{4}\d{2}\d{2}$/,
                    "alertText": "* 잘못된 날짜 형식입니다(YYYYMMDD 으로 입력해주세요!)."
                },
                "onlyYearMonth": {
                	"regex": /^\d{4}\d{2}$/,
                    "alertText": "* 잘못된 년월 형식입니다(YYYYMM 으로 입력해주세요!)."
                },
                // --- CUSTOM RULES -- Those are specific to the demos, they can be removed or changed to your likings
                "ajaxUserCall": {
                    "url": "ajaxValidateFieldUser",
                    // you may want to pass extra data on the ajax call
                    "extraData": "name=eric",
                    "alertText": "* This user is already taken",
                    "alertTextLoad": "* Validating, please wait"
                },
                "ajaxNameCall": {
                    // remote json service location
                    "url": "ajaxValidateFieldName",
                    // error
                    "alertText": "* This name is already taken",
                    // if you provide an "alertTextOk", it will show as a green prompt when the field validates
                    "alertTextOk": "* This name is available",
                    // speaks by itself
                    "alertTextLoad": "* Validating, please wait"
                },
                "cost": {
                    "regex": /^([0-9]+|[0-9]{1,3}(,[0-9]{3})*)?$/,
                    "alertText": "* 금액형식으로 입력해주세요.( , 가능)"
                },
                "corporateNumber": {
                	"regex": /^\d{3}[\-]\d{2}[\-]\d{5}$/,
                    "alertText": "* 잘못된 사업자등록번호 형식입니다(123-12-12345 형식으로 입력해주세요!)."
                },
                "jurirno": {
                	"regex": /^\d{6}[\-]\d{7}$/,
                    "alertText": "* 잘못된 법인등록번호 형식입니다(123456-1234567 형식으로 입력해주세요!)."
                }
            };
            
        }
    };
    $.validationEngineLanguage.newLang();
})(jQuery);


    
