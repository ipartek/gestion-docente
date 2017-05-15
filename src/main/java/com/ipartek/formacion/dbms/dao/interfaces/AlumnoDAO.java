package com.ipartek.formacion.dbms.dao.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.Alumno;

/**
 * Esta intefaz define los metodos de consulta a Base de Datos de la entidad
 * Alumno que tiene su correspondencia en la clase <code>Alumno</code> de la
 * capa de persistencia.
 * 
 * @author Urko Villanueva.
 *
 */
public interface AlumnoDAO extends DAOSetter {

	/**
	 * Metodo que crea un <code>Alumno</code> en la Base de Datos. El
	 * <code>Alumno</code> tendra los datos necesarios excepto el codigo que es
	 * generado por la base de datos.
	 * 
	 * @param alumno
	 *            <code>Alumno</code> el alumno a ser insertado en la Base de
	 *            Datos.
	 * @return <code>Alumno</code> se devuelve el objeto enviado con el codigo
	 *         generado en la Base de datos.
	 */
	public Alumno create(Alumno alumno);

	public List<Alumno> getAll();

	public Alumno getById(long codigo);

	public Alumno update(Alumno alumno);

	public void delete(long codigo);

	public Alumno getByDni(String dni);

	public Alumno getInforme(long codigo);
}
