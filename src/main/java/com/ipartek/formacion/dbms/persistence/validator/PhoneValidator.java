package com.ipartek.formacion.dbms.persistence.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

	@Override
	public void initialize(Phone param) {

	}

	@Override
	public boolean isValid(String phoneN, ConstraintValidatorContext ctx) {
		boolean valido = true;

		if (phoneN != null) {
			if (!phoneN.matches("\\d{9}")) {
				valido = false;
			}
		}

		return valido;
	}

}
