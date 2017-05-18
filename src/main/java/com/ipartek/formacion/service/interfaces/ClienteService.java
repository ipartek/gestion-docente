package com.ipartek.formacion.service.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.persistence.Cliente;

public interface ClienteService {
	public Cliente create(Cliente cliente);

	public Cliente getById(long cod);

	public List<Cliente> getAll();

	public Cliente update(Cliente cliente);

	public void delete(long codigo);

	public Cliente getByIdentificador(String identificador);

	public Cliente getInforme(long codigo);

}
