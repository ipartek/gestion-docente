package com.ipartek.formacion.service.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.Profesor;

public interface ProfesorService {

	public Profesor create(Profesor profesor);

	public List<Profesor> getAll();

	public Profesor getById(long codigo);

	public Profesor update(Profesor profesor);

	public void delete(long codigo);

	Profesor getByNss(String nss);

	Profesor getByDni(String dni);

}
