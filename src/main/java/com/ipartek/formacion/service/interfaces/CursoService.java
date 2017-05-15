package com.ipartek.formacion.service.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.Curso;

public interface CursoService {

	// public void setCursoServiceRemote(CursoServiceRemote cursoService);

	public Curso getById(long codigo);

	public List<Curso> getAll();

	public Curso update(Curso curso);

	public void delete(long codigo);

	public Curso create(Curso curso);

}
