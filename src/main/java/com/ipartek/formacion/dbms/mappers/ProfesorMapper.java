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
	public Profesor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Profesor profesor = null;
		profesor = new Profesor();
		profesor.setCodigo(rs.getInt("profesorcodigo"));
		profesor.setNombre(rs.getString("profesornombre"));
		profesor.setApellidos(rs.getString("profesorapellidos"));
		profesor.setnSS(rs.getString("profesornss"));
		profesor.setDni(rs.getString("profesordni"));
		profesor.setfNacimiento(rs.getDate("profesorfNacimiento"));
		profesor.setCodigoPostal(rs.getInt("profesorcodigopostal"));
		profesor.setDireccion(rs.getString("profesordireccion"));
		profesor.setEmail(rs.getString("profesoremail"));
		profesor.setPoblacion(rs.getString("profesorpoblacion"));
		profesor.setTelefono(rs.getString("profesortelefono"));
		profesor.setPoblacion(rs.getString("profesorpoblacion"));
		profesor.setActivo(rs.getBoolean("profesoractivo"));
		return profesor;
	}

}
