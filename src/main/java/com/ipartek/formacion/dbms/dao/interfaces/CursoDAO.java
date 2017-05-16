package com.ipartek.formacion.dbms.dao.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.Curso;

public interface CursoDAO extends DAOSetter {

	public List<Curso> getAll();

	public Curso getById(long codigo);

	public void delete(long codigo);

	public Curso update(Curso curso);

	public Curso create(Curso curso);

	public Curso getByIdentificador(String identificador);
}
