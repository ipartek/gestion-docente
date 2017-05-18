package com.ipartek.formacion.dbms.dao.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.Cliente;

public interface ClienteDAO extends DAOSetter {
	public Cliente create(Cliente cliente);

	public Cliente getById(long codigo);

	public List<Cliente> getAll();

	public Cliente update(Cliente cliente);

	public void delete(long codigo);

	public Cliente getByIdentificador(String identificador);

	public Cliente getInforme(long codigo);

}
