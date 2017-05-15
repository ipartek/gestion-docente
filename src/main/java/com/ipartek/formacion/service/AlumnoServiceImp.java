package com.ipartek.formacion.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ipartek.formacion.dbms.dao.interfaces.AlumnoDAO;
import com.ipartek.formacion.dbms.persistence.Alumno;
import com.ipartek.formacion.service.interfaces.AlumnoService;

@Service
public class AlumnoServiceImp implements AlumnoService {

	@Autowired
	private AlumnoDAO alumnoDao = null;
	private final static Logger LOGGER = LoggerFactory.getLogger(AlumnoServiceImp.class);

	@Transactional
	@Override
	public Alumno create(Alumno alumno) {
		return alumnoDao.create(alumno);
	}

	@Override
	public List<Alumno> getAll() {
		List<Alumno> alumnos = alumnoDao.getAll();
		LOGGER.info("numero de alumnos:" + alumnos);
		return alumnos;
	}

	@Override
	public Alumno getById(long codigo) {
		return alumnoDao.getById(codigo);
	}

	@Transactional
	@Override
	public Alumno update(Alumno alumno) {
		return alumnoDao.update(alumno);
	}

	@Transactional
	@Override
	public void delete(long codigo) {
		// begin();
		// commit();
		// rollback();
		alumnoDao.delete(codigo);
	}

	@Override
	public Alumno getByDni(String dni) {
		return alumnoDao.getByDni(dni);
	}

	@Override
	public Alumno getInforme(int codigo) {
		return alumnoDao.getInforme(codigo);
	}

}
