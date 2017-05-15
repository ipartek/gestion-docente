package com.ipartek.formacion.dbms.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.dbms.persistence.Profesor;

public class ProfesorMapper implements RowMapper<Profesor> {
	private final static Logger LOGGER = LoggerFactory.getLogger(ProfesorMapper.class);

	@Override
	public Profesor mapRow(ResultSet rs, int rownum) throws SQLException {
		Profesor profesor = null;
		profesor = new Profesor();
		profesor.setCodigo(rs.getInt("codigo"));
		profesor.setNombre(rs.getString("nombre"));
		profesor.setApellidos(rs.getString("apellidos"));
		profesor.setnSS(rs.getString("nss"));
		profesor.setDni(rs.getString("dni"));
		profesor.setfNacimiento(rs.getDate("fNacimiento"));
		profesor.setCodigoPostal(rs.getInt("codigopostal"));
		profesor.setDireccion(rs.getString("direccion"));
		profesor.setEmail(rs.getString("email"));
		profesor.setPoblacion(rs.getString("poblacion"));
		profesor.setTelefono(rs.getString("telefono"));
		profesor.setPoblacion(rs.getString("poblacion"));
		profesor.setActivo(rs.getBoolean("activo"));
		return profesor;
	}

}
