package com.ipartek.formacion.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Util {
	private static final Logger LOGGER = LoggerFactory.getLogger(Util.class);

	private Util() {

	}

	public static boolean validarDni(final String dni) {
		boolean valido = false;
		final String REGEX = "[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKET]";

		if (checkRegex(REGEX, dni) && comprobarLetraDni(dni)) {
			valido = true;
		}
		return valido;
	}

	public static boolean validarNIE(final String nie) {
		boolean valido = true;
		return valido;
	}

	public static boolean validarCIF(final String cif) {
		boolean valido = true;

		return valido;
	}

	public static boolean checkRegex(final String REGEX, final String parameter) {
		boolean valido = false;
		final Pattern pattern = Pattern.compile(REGEX);
		final Matcher matcher = pattern.matcher(parameter);
		if (matcher.matches()) {
			valido = true;
		}
		return valido;
	}

	public static boolean validarCodigoPostal(final int codigoPostal) {
		boolean valido = false;
		final String REGEX = "[0-9]{5}";
		if (checkRegex(REGEX, String.valueOf(codigoPostal))) {
			valido = true;
		}
		return valido;
	}

	public static boolean validarTelefono(final String telefono) {
		boolean valido = false;
		final String REGEX = "[0-9]{9}";

		if (checkRegex(REGEX, telefono)) {
			valido = true;
		}
		return valido;
	}

	public static boolean validarEmail(final String email) {
		boolean valido = false;
		if (email.contains("@") && email.length() < 150) {
			valido = true;
		}
		return valido;
	}

	private static boolean comprobarLetraDni(final String dni) {
		boolean valido = false;
		String letras = "TRWAGMYFPDXBNJZSQVHLCKE";

		try {
			int dniNum = Integer.parseInt(dni.substring(0, dni.length() - 1));
			char letra = letras.charAt(dniNum % 23);
			if (letra == dni.charAt(dni.length() - 1)) {
				valido = true;
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			valido = false;
		}

		return valido;

	}
}
