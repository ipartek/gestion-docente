package com.ipartek.formacion.service.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.Alumno;

public interface AlumnoService {

	public Alumno create(Alumno alumno);

	public List<Alumno> getAll();

	public Alumno getById(long codigo);

	public Alumno update(Alumno alumno);

	public void delete(long codigo);

	public Alumno getByDni(String dni);

	public Alumno getInforme(long codigo);

}
