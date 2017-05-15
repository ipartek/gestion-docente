package com.ipartek.formacion.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

public class FileValidator implements Validator {
	private static final Logger LOGGER = LoggerFactory.getLogger(CursoValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {

		return MultipartFile.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		final MultipartFile multipartFile = (MultipartFile) obj;
		if (multipartFile != null && multipartFile.isEmpty()) {
			errors.rejectValue("temario", "file.empty");
		}
	}

}
