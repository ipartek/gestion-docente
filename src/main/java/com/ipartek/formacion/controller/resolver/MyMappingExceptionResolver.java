package com.ipartek.formacion.controller.resolver;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class MyMappingExceptionResolver extends SimpleMappingExceptionResolver {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver#
	 * buildLogMessage(java.lang.Exception,
	 * javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected String buildLogMessage(Exception ex, HttpServletRequest request) {

		return "Gestion Docente exception: " + ex.getLocalizedMessage();
	}

}
