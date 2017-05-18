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
import com.ipartek.formacion.dbms.persistence.Cliente;
import com.ipartek.formacion.dbms.persistence.Curso;
import com.ipartek.formacion.dbms.persistence.Imparticion;
import com.ipartek.formacion.dbms.persistence.Profesor;

public class CursoExtractor implements ResultSetExtractor<Map<Long, Curso>> {
	private static final Logger LOGGER = LoggerFactory.getLogger(CursoExtractor.class);

	@Override
	public Map<Long, Curso> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<Long, Curso> cursos = new HashMap<Long, Curso>();
		while (rs.next()) {
			Long codigo = rs.getLong("cursocodigo");
			Curso curso = cursos.get(codigo);
			if (curso == null) {
				curso = new Curso();
				curso.setCodigo(rs.getLong("cursocodigo"));
				curso.setActivo(rs.getBoolean("cursoactivo"));
				curso.setNombre(rs.getString("cursonombre"));
				curso.setIdentificador(rs.getString("cursoidentificador"));
				curso.setTemario(rs.getString("cursotemario"));
				curso.setNhoras(rs.getInt("cursonhoras"));
				curso.setPrecio(rs.getDouble("cursoprecio"));
				curso.setFinicio(rs.getDate("cursofinicio"));
				curso.setFfin(rs.getDate("cursoffin"));

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

				curso.setCliente(cliente);

				Profesor profesor = new Profesor();
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

				curso.setProfesor(profesor);

				curso.setImparticiones(new ArrayList<Imparticion>());
			}
			Long cImparticion = rs.getLong("imparticioncodigo");
			if (cImparticion != null && cImparticion > -1) {
				Imparticion imparticion = new Imparticion();
				imparticion.setCodigo(rs.getInt("imparticioncodigo"));
				imparticion.setfMatriculacion(rs.getDate("imparticionfmatriculacion"));
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
				imparticion.setAlumno(alumno);
				List<Imparticion> imparticiones = curso.getImparticiones();
				imparticiones.add(imparticion);
				curso.setImparticiones(imparticiones);
			}
			cursos.put(curso.getCodigo(), curso);
		}
		LOGGER.info("" + cursos.size());
		return cursos;
	}

}
