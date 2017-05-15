/**
 * 
 */
package com.ipartek.formacion.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author va00
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(SQLException.class)
	public String handlerSQLException(HttpServletRequest request, Exception ex) {
		LOGGER.error("SQLException URL:" + request.getRequestURL());
		LOGGER.error("Mensaje: " + ex.getMessage());
		return "database_error";
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "IOException lanzada")
	@ExceptionHandler(IOException.class)
	public void handleIOException() {
		LOGGER.error("Se ha ejecutado una exception de tipo IOException");
	}

}
