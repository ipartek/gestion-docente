package com.ipartek.formacion.controller.pojo;

import java.io.Serializable;

/**
 * Mensajes para el usuario
 *
 * @author ur00
 *
 */
public class Mensaje implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2679853944556101925L;

	private String msg; // literal del mensaje
	private MensajeType type; // tipo de mensaje [success,info,warning,danger]
	private int code; // codigo http

	public Mensaje(final String txt) {
		super();
		this.msg = txt;
		this.type = MensajeType.MSG_TYPE_SUCCESS;
	}

	public Mensaje(final MensajeType type) {
		super();
		this.msg = "";
		this.type = type;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(final String msg) {
		this.msg = msg;
	}

	public MensajeType getType() {
		return type;
	}

	public void setType(final MensajeType type) {
		this.type = type;
	}

	public int getCode() {
		return code;
	}

	public void setCode(final int code) {
		this.code = code;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Mensaje [msg=" + msg + ", type=" + type + ", code=" + code + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((msg == null) ? 0 : msg.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Mensaje)) {
			return false;
		}
		Mensaje other = (Mensaje) obj;
		if (code != other.code) {
			return false;
		}
		if (msg == null) {
			if (other.msg != null) {
				return false;
			}
		} else if (!msg.equals(other.msg)) {
			return false;
		}
		if (type != other.type) {
			return false;
		}
		return true;
	}

}