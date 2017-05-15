package com.ipartek.formacion.dbms.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.dbms.persistence.Alumno;

public class AlumnoMapper implements RowMapper<Alumno> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AlumnoMapper.class);

	@Override
	public Alumno mapRow(ResultSet rs, int rowNum) throws SQLException {
		Alumno alumno = new Alumno();
		alumno.setCodigo(rs.getInt("codigo"));
		alumno.setApellidos(rs.getString("apellidos"));
		alumno.setNombre(rs.getString("nombre"));
		alumno.setActivo(rs.getBoolean("activo"));
		alumno.setnHermanos(rs.getInt("nhermanos"));
		alumno.setDni(rs.getString("dni"));
		alumno.setfNacimiento(rs.getDate("fnacimiento"));
		alumno.setEmail(rs.getString("email"));
		alumno.setDireccion(rs.getString("direccion"));
		alumno.setPoblacion(rs.getString("poblacion"));
		alumno.setCodigoPostal(rs.getInt("codigopostal"));
		alumno.setTelefono(String.valueOf(rs.getInt("telefono")));

		return alumno;
	}

}
