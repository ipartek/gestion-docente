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
		alumno.setCodigo(rs.getInt("alumnocodigo"));
		alumno.setApellidos(rs.getString("alumnoapellidos"));
		alumno.setNombre(rs.getString("alumnonombre"));
		alumno.setActivo(rs.getBoolean("alumnoactivo"));
		alumno.setnHermanos(rs.getInt("alumnonhermanos"));
		alumno.setDni(rs.getString("alumnodni"));
		alumno.setfNacimiento(rs.getDate("alumnofnacimiento"));
		alumno.setEmail(rs.getString("alumnoemail"));
		alumno.setDireccion(rs.getString("alumnodireccion"));
		alumno.setPoblacion(rs.getString("alumnopoblacion"));
		alumno.setCodigoPostal(rs.getInt("alumnocodigopostal"));
		alumno.setTelefono(String.valueOf(rs.getInt("alumnotelefono")));

		return alumno;
	}

}
