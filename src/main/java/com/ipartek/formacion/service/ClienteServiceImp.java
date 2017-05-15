package com.ipartek.formacion.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.dbms.dao.interfaces.ClienteDAO;
import com.ipartek.formacion.dbms.persistence.Cliente;
import com.ipartek.formacion.service.interfaces.ClienteService;

@Service
public class ClienteServiceImp implements ClienteService {

	@Inject
	ClienteDAO clienteDao;
	private final static Logger LOGGER = LoggerFactory.getLogger(ClienteServiceImp.class);

	@Override
	public Cliente create(Cliente cliente) {

		return clienteDao.create(cliente);
	}

	@Override
	public Cliente getById(long codigo) {

		return clienteDao.getById(codigo);
	}

	@Override
	public List<Cliente> getAll() {
		List<Cliente> clientes = clienteDao.getAll();
		LOGGER.info("Total clientes" + clientes.size());
		return clientes;
	}

	@Override
	public Cliente update(Cliente cliente) {

		return clienteDao.update(cliente);
	}

	@Override
	public void delete(int codigo) {
		clienteDao.delete(codigo);
	}

	@Override
	public Cliente getByIdentificador(String identificador) {
		return clienteDao.getByIdentificador(identificador);
	}

	@Override
	public Cliente getInforme(int codigo) {
		Cliente cliente = clienteDao.getInforme(codigo);
		LOGGER.info("descripcion:" + cliente.toString());
		return cliente;
	}

}
