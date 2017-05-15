package com.ipartek.formacion.controller.validator;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ipartek.formacion.dbms.persistence.Alumno;
import com.ipartek.formacion.service.Util;
import com.ipartek.formacion.service.interfaces.AlumnoService;

//@PropertySource(value = "classpath:/constantes/costantes.properties")
public class AlumnoValidator implements Validator {
	private static final Logger LOGGER = LoggerFactory.getLogger(AlumnoValidator.class);
	@Value("${alumno.nombre.size.min}")
	private int nombreTamMin;
	@Value("${alumno.nombre.size.max}")
	private int nombreTamMax;
	@Inject
	AlumnoService aS;

	@Override
	public boolean supports(Class<?> paramClass) {
		return Alumno.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {

		LOGGER.info(obj.toString());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "form.nombreRequerido",
				"Tiene que introducirse un nombre");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellidos", "form.apellidoRequerido",
				"tiene que introducirse un apellido");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "form.emailRequerido",
				"tiene que introducirse un email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "form.dniRequerido", "tiene que introducirse un dni");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telefono", "form.telefonoRequerido",
				"tiene que introducirse un telefono");

		Alumno alum = (Alumno) obj;
		if (alum.getCodigo() < Alumno.CODIGO_NULO) {
			errors.reject("codigo", new Object[] { "'codigo'" }, "no puede ser menor que " + Alumno.CODIGO_NULO);

		}

		if (!Util.validarTelefono(alum.getTelefono())) {
			errors.rejectValue("telefono", "form.telefonoIncorrecto", new Object[] { "'telefono'" },
					"el telefono introducido es incorrecto");
		}
		if (!Util.validarDni(alum.getDni().toUpperCase())) {// validación de la
															// letra del DNI
			errors.rejectValue("dni", "form.letraDniIncorrecta", new Object[] { "'dni'" },
					"el DNI introducido es incorrecto");

		}
		if (alum.getNombre().length() < nombreTamMin || alum.getNombre().length() > nombreTamMax) {
			errors.rejectValue("nombre", "form.longitudNombreIncorrecta", new Object[] { nombreTamMin, nombreTamMax },
					"El nombre tiene que ocupar entre " + nombreTamMin + " y " + nombreTamMax + " caracteres.");
		}
		if (alum.getApellidos().length() < 7 || alum.getApellidos().length() > 250) {
			errors.rejectValue("apellidos", "form.longitudApellidoIncorrecta", new Object[] { "'apellidos'" },
					"Los dos apellidos tienen que ocupar entre 7 y 250 caracteres.");
		}

		if (alum.getDireccion().length() > 250) {
			errors.rejectValue("direccion", "form.longitudDireccionIncorrecta", new Object[] { "'direccion'" },
					"La dirección tiene que tener una longitud máxima de 250 caracteres.");
		}
		if (alum.getPoblacion().length() > 150) {
			errors.rejectValue("poblacion", "form.longitudPoblacionIncorrecta", new Object[] { "'poblacion'" },
					"La poblacion tiene que tener una longitud máxima de 250 caracteres.");
		}

		if (alum.getCodigo() > 50 && !Util.validarCodigoPostal(alum.getCodigoPostal())) {
			errors.rejectValue("codigopostal", "form.formatoCodigoPostalIncorrecto", new Object[] { "'codigopostal'" },
					"El código postal introducido no es correcto.");
		}
		if (!Util.validarEmail(alum.getEmail())) {
			errors.rejectValue("email", "form.formatoCodigoPostalIncorrecto", new Object[] { "'email'" },
					"El email introducido no es correcto.");
		}
		Alumno aux = aS.getByDni(alum.getDni());
		if ((alum.getCodigo() == Alumno.CODIGO_NULO && aux != null)
				|| (!alum.equals(aux) && alum.getDni().equalsIgnoreCase(aux.getDni()))) {
			errors.rejectValue("dni", "form.dniExiste", new Object[] { alum.getDni() },
					"el dni ya existe en la base de datos");

		}
	}

}
