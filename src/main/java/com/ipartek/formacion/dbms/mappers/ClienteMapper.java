package com.ipartek.formacion.dbms.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.dbms.persistence.Cliente;

public class ClienteMapper implements RowMapper<Cliente> {
	private final static Logger LOGGER = LoggerFactory.getLogger(ClienteMapper.class);

	@Override
	public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
		Cliente cliente = new Cliente();
		cliente.setCodigo(rs.getInt("clientecodigo"));
		cliente.setNombre(rs.getString("clientenombre"));
		cliente.setIdentificador(rs.getString("clienteidentificador"));
		cliente.setCodigoPostal(rs.getInt("clientecodigopostal"));
		cliente.setDireccion(rs.getString("clientedireccion"));
		cliente.setEmail(rs.getString("clienteemail"));
		cliente.setPoblacion(rs.getString("clientepoblacion"));
		cliente.setTelefono(String.valueOf(rs.getInt("clientetelefono")));
		cliente.setActivo(rs.getBoolean("clienteactivo"));
		return cliente;
	}

}
