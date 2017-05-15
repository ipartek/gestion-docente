package com.ipartek.formacion.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ipartek.formacion.dbms.persistence.Curso;

public class CursoValidator implements Validator {
	private static final Logger LOGGER = LoggerFactory.getLogger(CursoValidator.class);

	@Override
	public boolean supports(Class<?> paramClass) {

		return Curso.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		LOGGER.info(obj.toString());
	}

}
