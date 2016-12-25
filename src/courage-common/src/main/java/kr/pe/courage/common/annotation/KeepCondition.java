package kr.pe.courage.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * kr.pe.courage.cmm.core.annotation
 * KeepCondition.java
 * </pre>
 *
 * @Author	: Seok Chang Ho
 * @Date	: 2013. 10. 31.
 * @Version	: 1.0
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface KeepCondition {
	
}
