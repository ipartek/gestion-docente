package com.ipartek.formacion.dbms.dao.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.Profesor;

public interface ProfesorDAO extends DAOSetter {
	public Profesor create(Profesor profesor);

	public Profesor getById(long codigo);

	public List<Profesor> getAll();

	public Profesor update(Profesor profesor);

	public void delete(long codigo);

	public Profesor getByDni(String dni);

	public Profesor getByNss(String nss);
}
