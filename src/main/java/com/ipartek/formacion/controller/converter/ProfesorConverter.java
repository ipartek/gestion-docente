package com.ipartek.formacion.controller.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.ipartek.formacion.dbms.persistence.Profesor;
import com.ipartek.formacion.service.interfaces.ProfesorService;

public class ProfesorConverter implements Converter<String, Profesor> {
	@Autowired
	ProfesorService pS;
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfesorConverter.class);

	@Override
	public Profesor convert(String codigo) {
		LOGGER.info(codigo);
		Profesor profesor = pS.getById(Long.parseLong((String) codigo));
		LOGGER.info(profesor.toString());
		return profesor;
	}

}
