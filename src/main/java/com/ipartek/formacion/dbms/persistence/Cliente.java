package com.ipartek.formacion.dbms.persistence;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Cliente implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int CODIGO_NULO = -1;
	private static final int CODIGOPOSTALICIALES = 48;
	private int codigo;
	private String nombre;
	private String email;
	private String direccion;
	private String poblacion;
	private int codigoPostal;
	private String telefono;
	private String identificador;
	private boolean activo;
	private Map<Long, Curso> cursos;

	public Cliente() {
		super();
		this.codigo = CODIGO_NULO;
		this.nombre = "";
		this.email = "";
		this.direccion = "";
		this.poblacion = "";
		this.codigoPostal = CODIGOPOSTALICIALES;
		this.telefono = "";
		this.identificador = "";
		this.activo = true;
		cursos = new HashMap<Long, Curso>();
	}

	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion
	 *            the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the poblacion
	 */
	public String getPoblacion() {
		return poblacion;
	}

	/**
	 * @param poblacion
	 *            the poblacion to set
	 */
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	/**
	 * @return the codigoPostal
	 */
	public int getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * @param codigoPostal
	 *            the codigoPostal to set
	 */
	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/**
	 * @return the identificador
	 */
	public String getIdentificador() {
		return identificador;
	}

	/**
	 * @param identificador
	 *            the identificador to set
	 */
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono
	 *            the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
		result = prime * result + codigo;
		result = prime * result + ((identificador == null) ? 0 : identificador.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		boolean igual = false;
		if (obj != null && obj instanceof Cliente) {
			Cliente cliente = (Cliente) obj;
			if (obj == this || (codigo == cliente.getCodigo()
					&& cliente.getIdentificador().equalsIgnoreCase(this.identificador))) {
				igual = true;
			}
		}

		return igual;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cliente [codigo=" + codigo + ", nombre=" + nombre + ", email=" + email + ", direccion=" + direccion
				+ ", poblacion=" + poblacion + ", codigoPostal=" + codigoPostal + ", telefono=" + telefono
				+ ", identificador=" + identificador + "]";
	}

	/**
	 * @return the cursos
	 */
	public Map<Long, Curso> getCursos() {
		return cursos;
	}

	/**
	 * @param cursos
	 *            the cursos to set
	 */
	public void setCursos(Map<Long, Curso> cursos) {
		this.cursos = cursos;
	}

	/**
	 * @return the activo
	 */
	public boolean isActivo() {
		return activo;
	}

	/**
	 * @param activo
	 *            the activo to set
	 */
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}
