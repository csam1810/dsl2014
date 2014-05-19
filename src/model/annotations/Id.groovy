package model.annotations

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * Maker annotation to indicate the primary key ID column. Only a single 
 * property per entity class should contain this annotation. Subsequent 
 * usages will be ignored. The ID column has to be an auto-increment
 * integer.
 * 
 * @author alexandra
 * @author carmen
 * @author mathias
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Id {}

