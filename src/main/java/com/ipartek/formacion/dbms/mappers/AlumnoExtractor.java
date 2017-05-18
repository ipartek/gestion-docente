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

public class AlumnoExtractor implements ResultSetExtractor<Map<Long, Alumno>> {
	private static final Logger LOGGER = LoggerFactory.getLogger(AlumnoExtractor.class);

	@Override
	public Map<Long, Alumno> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<Long, Alumno> alumnos = new HashMap<Long, Alumno>();
		while (rs.next()) {
			Long codigo = rs.getLong("alumnocodigo");
			Alumno alumno = alumnos.get(codigo);
			if (alumno == null) {
				alumno = new Alumno();
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
				alumno.setCursos(new ArrayList<Curso>());
				alumnos.put(codigo, alumno);
			}

			Long cCurso = rs.getLong("cursocodigo");
			if (cCurso != null && cCurso > -1) {

				Curso curso = new Curso();
				curso.setCodigo(rs.getLong("cursocodigo"));
				curso.setNombre(rs.getString("cursonombre"));
				curso.setFinicio(rs.getDate("cursofinicio"));
				curso.setFfin(rs.getDate("cursoffin"));
				curso.setNhoras(rs.getInt("cursonhoras"));
				LOGGER.info("Datos del alumno:" + alumno.toString());
				LOGGER.info("Datos de curso:" + curso.toString());
				List<Curso> cursos = alumno.getCursos();
				cursos.add(curso);
				alumno.setCursos(cursos);
			}
			// alumnos.put(codigo, alumno);
		}
		LOGGER.info("" + alumnos.size());
		return alumnos;
	}

}
