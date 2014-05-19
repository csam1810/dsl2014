package model.annotations

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 *
 *
 * @author alexandra
 * @author carmen
 * @author mathias
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column {
	
	/**
	 * 
	 * @return
	 */
	def int length() default 254
	
	/**
	 * 
	 * @return
	 */
	def boolean nullable() default true
}
