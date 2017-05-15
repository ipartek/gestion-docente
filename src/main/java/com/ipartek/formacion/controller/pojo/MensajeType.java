package com.ipartek.formacion.controller.pojo;

public enum MensajeType {
	// llamar al constructor private de la Enum
	MSG_TYPE_SUCCESS("alert alert-success alert-dismissable"), MSG_TYPE_INFO(
			"alert alert-info alert-dismissable"), MSG_TYPE_WARNING(
					"alert alert-warning alert-dismissable"), MSG_TYPE_DANGER("alert alert-danger alert-dismissable");

	private String styles;

	private MensajeType(String s) {
		this.styles = s;
	}

	/**
	 * @return the styles
	 */
	public String getStyles() {
		return styles;
	}

	/**
	 * @param styles
	 *            the styles to set
	 */
	public void setStyles(String styles) {
		this.styles = styles;
	}

}
