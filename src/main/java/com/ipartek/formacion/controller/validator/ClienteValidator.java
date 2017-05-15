package com.ipartek.formacion.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ipartek.formacion.dbms.persistence.Cliente;
import com.ipartek.formacion.service.Util;

public class ClienteValidator implements Validator {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteValidator.class);

	@Override
	public boolean supports(Class<?> paramClass) {

		return Cliente.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		LOGGER.info(obj.toString());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "nombreRequerido",
				"tiene que introducirse un nombre");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "identificador", "identificadorRequerido",
				"tiene que introducirse un apellido");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "emailRequerido", "tiene que introducirse un email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono", "telefonoRequerido",
				"tiene que introducirse un telefono");

		Cliente cliente = (Cliente) obj;
		if (cliente.getCodigo() > 50 && !Util.validarCodigoPostal(cliente.getCodigoPostal())) {
			errors.rejectValue("codigopostal", "form.formatoCodigoPostalIncorrecto", new Object[] { "'codigopostal'" },
					"El código postal introducido no es correcto.");
		}
		if (!Util.validarEmail(cliente.getEmail())) {
			errors.rejectValue("email", "form.formatoCodigoPostalIncorrecto", new Object[] { "'email'" },
					"El email introducido no es correcto.");
		}
		if (!Util.validarTelefono(cliente.getTelefono())) {
			errors.rejectValue("telefono", "form.telefonoIncorrecto", new Object[] { "'telefono'" },
					"el telefono introducido es incorrecto");
		}
		if (cliente.getDireccion().length() > 250) {
			errors.rejectValue("direccion", "form.longitudDireccionIncorrecta", new Object[] { "'direccion'" },
					"La dirección tiene que tener una longitud máxima de 250 caracteres.");
		}
		if (cliente.getPoblacion().length() > 150) {
			errors.rejectValue("poblacion", "form.longitudPoblacionIncorrecta", new Object[] { "'poblacion'" },
					"La poblacion tiene que tener una longitud máxima de 250 caracteres.");
		}
		if (cliente.getCodigo() > 50 && !Util.validarCodigoPostal(cliente.getCodigoPostal())) {
			errors.rejectValue("codigopostal", "form.formatoCodigoPostalIncorrecto", new Object[] { "'codigopostal'" },
					"El código postal introducido no es correcto.");
		}
		if (!Util.validarCIF(cliente.getIdentificador()) && !Util.validarDni(cliente.getIdentificador())
				&& Util.validarNIE(cliente.getIdentificador())) {
			errors.rejectValue("idenficador", "form.identificadorIncorrecto", new Object[] { "'idenficador'" },
					"El identificador (CIF, NIE o NIF) introducido no es correcto.");
		}
	}

}
