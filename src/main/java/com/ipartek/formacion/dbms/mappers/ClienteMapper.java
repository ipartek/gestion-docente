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
	public Cliente mapRow(ResultSet rs, int rowN) throws SQLException {
		Cliente cliente = new Cliente();
		cliente.setCodigo(rs.getInt("codigo"));
		cliente.setNombre(rs.getString("nombre"));
		cliente.setIdentificador(rs.getString("identificador"));
		cliente.setCodigoPostal(rs.getInt("codigopostal"));
		cliente.setDireccion(rs.getString("direccion"));
		cliente.setEmail(rs.getString("email"));
		cliente.setPoblacion(rs.getString("poblacion"));
		cliente.setTelefono(String.valueOf(rs.getInt("telefono")));
		cliente.setActivo(rs.getBoolean("activo"));
		return cliente;
	}

}
