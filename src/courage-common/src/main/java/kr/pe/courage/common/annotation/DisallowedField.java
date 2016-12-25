package kr.pe.courage.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * kr.pe.courage.common.annotation
 * DisallowedField.java
 * </pre>
 *
 * @Author	: ChangHo Seok
 * @Date	: 2016. 6. 21.
 * @Version	: 1.0
 * @see
 * 
 * <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2016. 6. 21., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 * </pre>
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DisallowedField {
	
}
