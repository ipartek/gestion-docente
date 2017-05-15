package com.ipartek.formacion.dbms.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Urko Villanueva
 *
 */
public class Alumno implements Comparable<Alumno>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6698866485450376235L;
	public static final int CODIGO_NULO = -1;
	private static final int INICIALCODIGOPOSTAL = 48;
	private int codigo;
	private boolean activo;
	private int nHermanos;
	private String dni;
	private String nombre;
	private String apellidos;
	private Date fNacimiento;
	private String email;
	private String direccion;
	private String poblacion;
	private int codigoPostal;
	private String telefono;
	private List<Curso> cursos;

	/**
	 * Constructor en blaco de la clase alumno.
	 */
	public Alumno() {
		super();// constructor de la clase padre
		this.codigo = CODIGO_NULO;
		this.nombre = "";
		this.apellidos = "";
		this.dni = "";
		this.fNacimiento = new Date();
		this.email = "";
		this.direccion = "";
		this.poblacion = "";
		this.telefono = "94";
		this.codigoPostal = INICIALCODIGOPOSTAL;
		this.activo = true;
		this.nHermanos = 0;
		cursos = null;

	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	public boolean isActivo() {
		return activo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Alumno [codigo=" + codigo + ", activo=" + activo + ", nHermanos=" + nHermanos + ", dni=" + dni
				+ ", nombre=" + nombre + ", apellidos=" + apellidos + ", fNacimiento=" + fNacimiento + ", email="
				+ email + ", direccion=" + direccion + ", poblacion=" + poblacion + ", codigoPostal=" + codigoPostal
				+ ", telefono=" + telefono + ", cursos=" + cursos + "]";
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getnHermanos() {
		return nHermanos;
	}

	public void setnHermanos(int nHermanos) {
		this.nHermanos = nHermanos;
	}

	/**
	 * Se usa en el caso de ordenamiento de List o Array
	 * 
	 * @param o
	 * @return
	 */
	@Override
	public int compareTo(final Alumno alumno) {
		return this.apellidos.compareToIgnoreCase(alumno.getApellidos());
	}

	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * @param dni
	 *            the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
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
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * @param apellidos
	 *            the apellidos to set
	 */
	public void setApellidos(final String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * @return the fNacimiento
	 */
	public Date getfNacimiento() {
		return fNacimiento;
	}

	/**
	 * @param fNacimiento
	 *            the fNacimiento to set
	 */
	public void setfNacimiento(Date fNacimiento) {
		this.fNacimiento = fNacimiento;
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
		return result;
	}

	/**
	 * Para evaluar si los objetos son iguales
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		boolean iguales = false;

		if (obj != null && obj instanceof Alumno) {
			final Alumno alum = (Alumno) obj;
			if (this == alum || (alum.getCodigo() == this.codigo && this.dni.equalsIgnoreCase(alum.getDni()))) {
				iguales = true;
			}
		}
		return iguales;
	}

	/**
	 * @return the cursos
	 */
	public List<Curso> getCursos() {
		return cursos;
	}

	/**
	 * @param cursos
	 *            the cursos to set
	 */
	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

}
