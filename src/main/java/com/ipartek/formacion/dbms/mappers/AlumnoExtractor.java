package com.ipartek.formacion.dbms.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.ipartek.formacion.dbms.persistence.Alumno;
import com.ipartek.formacion.dbms.persistence.Curso;

public class AlumnoExtractor implements ResultSetExtractor<Map<String, Alumno>> {
	private static final Logger LOGGER = LoggerFactory.getLogger(AlumnoExtractor.class);

	@Override
	public Map<String, Alumno> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<String, Alumno> alumnos = new HashMap<String, Alumno>();
		while (rs.next()) {
			String dni = rs.getString("dni");
			Alumno alumno = alumnos.get(dni);
			if (alumno == null) {
				alumno = new Alumno();
				alumno.setCodigo(rs.getInt("codigo"));
				alumno.setApellidos(rs.getString("apellidos"));
				alumno.setNombre(rs.getString("nombre"));
				alumno.setActivo(rs.getBoolean("activo"));
				alumno.setnHermanos(rs.getInt("nhermanos"));
				alumno.setDni(dni);
				alumno.setfNacimiento(rs.getDate("fnacimiento"));
				alumno.setEmail(rs.getString("email"));
				alumno.setDireccion(rs.getString("direccion"));
				alumno.setPoblacion(rs.getString("poblacion"));
				alumno.setCodigoPostal(rs.getInt("codigopostal"));
				alumno.setTelefono(String.valueOf(rs.getInt("telefono")));
				alumno.setCursos(new ArrayList<Curso>());
				alumnos.put(alumno.getDni(), alumno);
			}

			Long cCurso = rs.getLong("codigocurso");
			if (cCurso != null && cCurso > -1) {

				Curso curso = new Curso();
				curso.setCodigo(rs.getLong("codigocurso"));
				curso.setNombre(rs.getString("nombrecurso"));
				curso.setFinicio(rs.getDate("finicio"));
				curso.setFfin(rs.getDate("ffin"));
				curso.setNhoras(rs.getInt("nhoras"));
				LOGGER.info("Datos del alumno:" + alumno.toString());
				LOGGER.info("Datos de curso:" + curso.toString());
				List<Curso> cursos = alumno.getCursos();
				cursos.add(curso);
				alumno.setCursos(cursos);
			}
		}
		return alumnos;
	}

}
