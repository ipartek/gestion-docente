package com.ipartek.formacion.dbms.persistence;

import java.util.Date;

public class Curso {

	public final static int CODIGO_NULO = -1;

	private long codigo;

	private String nombre;
	private String identificador;
	private Date finicio;
	private Date ffin;
	private int nhoras;
	private String temario;
	private double precio;
	private Cliente cliente;
	private Profesor profesor;
	private boolean activo;

	public Curso() {
		super();
		this.nombre = "";
		this.identificador = "";
	}

	/**
	 * @return the codigo
	 */
	public long getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(long codigo) {
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
	 * @return the finicio
	 */
	public Date getFinicio() {
		return finicio;
	}

	/**
	 * @param finicio
	 *            the finicio to set
	 */
	public void setFinicio(Date finicio) {
		this.finicio = finicio;
	}

	/**
	 * @return the ffin
	 */
	public Date getFfin() {
		return ffin;
	}

	/**
	 * @param ffin
	 *            the ffin to set
	 */
	public void setFfin(Date ffin) {
		this.ffin = ffin;
	}

	/**
	 * @return the nhoras
	 */
	public int getNhoras() {
		return nhoras;
	}

	/**
	 * @param nhoras
	 *            the nhoras to set
	 */
	public void setNhoras(int nhoras) {
		this.nhoras = nhoras;
	}

	/**
	 * @return the temario
	 */
	public String getTemario() {
		return temario;
	}

	/**
	 * @param temario
	 *            the temario to set
	 */
	public void setTemario(String temario) {
		this.temario = temario;
	}

	/**
	 * @return the precio
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * @param precio
	 *            the precio to set
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Curso [codigo=" + codigo + ", nombre=" + nombre + ", identificador=" + identificador + ", finicio="
				+ finicio + ", ffin=" + ffin + ", nhoras=" + nhoras + ", temario=" + temario + ", precio=" + precio
				+ ", cliente=" + cliente + "]";
	}

	/**
	 * @return the profesor
	 */
	public Profesor getProfesor() {
		return profesor;
	}

	/**
	 * @param profesor
	 *            the profesor to set
	 */
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
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
