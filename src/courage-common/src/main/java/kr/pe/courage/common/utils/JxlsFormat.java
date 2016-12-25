package kr.pe.courage.common.utils;

import java.math.BigInteger;
import java.text.DecimalFormat;

/**
 * <pre>
 * kr.pe.courage.common.utils
 * JxlsFormat.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 2. 1.
 * @Version	: 
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : , 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
public class JxlsFormat {

    private String format;
    private Character pattern;

    public JxlsFormat(String format) {
        this.format = format;
    }

    public JxlsFormat(String format, Character pattern) {
        this.format = format;
        this.pattern = pattern;
    }

    public String dateFormat(String var1) {
        return Util.strFormat(var1, format, pattern);
    }
    
    public String strFormat(String var1, String format1) {
    	return Util.strFormat(var1, format1, pattern);
    }

    public String commaFormat(Object var1){
        BigInteger var2 = new BigInteger(String.valueOf(var1));
        DecimalFormat df = new DecimalFormat(format);
        return df.format(var2);
    }

}
