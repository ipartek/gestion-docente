package com.ipartek.formacion.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipartek.formacion.dbms.dao.interfaces.CursoDAO;
import com.ipartek.formacion.dbms.persistence.Curso;
import com.ipartek.formacion.service.interfaces.CursoService;

//import com.formacion.ipartek.curso.CursoServiceRemote;

//import com.ipartek.formacion.service.interfaces.CursoService;

@Service("cursoServiceImp")
public class CursoServiceImp implements CursoService {

	@Autowired
	private CursoDAO cursoDao;
	private final static Logger LOGGER = LoggerFactory.getLogger(CursoServiceImp.class);

	@Override
	public Curso getById(long codigo) {
		return cursoDao.getById(codigo);
	}

	@Override
	public List<Curso> getAll() {
		return cursoDao.getAll();
	}

	@Transactional
	@Override
	public Curso update(Curso curso) {

		return cursoDao.update(curso);
	}

	@Transactional
	@Override
	public void delete(long codigo) {

		Curso curso = cursoDao.getById(codigo);
		curso.setActivo(false);
		cursoDao.update(curso);
	}

	@Transactional
	@Override
	public Curso create(Curso curso) {

		return cursoDao.create(curso);
	}

}
