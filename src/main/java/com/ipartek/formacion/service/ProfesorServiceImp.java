package com.ipartek.formacion.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.dbms.dao.interfaces.ProfesorDAO;
import com.ipartek.formacion.dbms.persistence.Profesor;
import com.ipartek.formacion.service.interfaces.ProfesorService;

@Service
public class ProfesorServiceImp implements ProfesorService {
	@Inject
	private ProfesorDAO profesorDAO;
	private final static Logger LOGGER = LoggerFactory.getLogger(ProfesorServiceImp.class);

	@Override
	public Profesor create(Profesor profesor) {
		return profesorDAO.create(profesor);
	}

	@Override
	public List<Profesor> getAll() {
		List<Profesor> profesores = profesorDAO.getAll();
		LOGGER.info("Numero de profesores: " + profesores.size());
		return profesores;
	}

	@Override
	public Profesor getById(long codigo) {
		return profesorDAO.getById(codigo);
	}

	@Override
	public void delete(long codigo) {
		profesorDAO.delete(codigo);
	}

	@Override
	public Profesor update(Profesor profesor) {
		return profesorDAO.update(profesor);
	}

	@Override
	public Profesor getByNss(String nss) {
		return profesorDAO.getByNss(nss);
	}

	@Override
	public Profesor getByDni(String dni) {
		return profesorDAO.getByDni(dni);
	}
}
