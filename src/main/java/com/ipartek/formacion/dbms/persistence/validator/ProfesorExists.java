/**
 * 
 */
package com.ipartek.formacion.dbms.persistence.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author va00
 *
 */
@Documented
@Constraint(validatedBy = ProfesorExistsValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ProfesorExists {

	String message()

	default "{com.ipartek.formacion.dbms.persistence.validator.ProfesorExists.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String code();

	String key();

	@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		ProfesorExists[] value();
	}
}
