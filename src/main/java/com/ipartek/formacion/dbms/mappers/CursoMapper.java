package com.ipartek.formacion.dbms.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.dbms.persistence.Curso;

public class CursoMapper implements RowMapper<Curso> {

	@Override
	public Curso mapRow(ResultSet rs, int rowNum) throws SQLException {
		Curso curso = new Curso();
		curso.setCodigo(rs.getLong("cursocodigo"));
		curso.setActivo(rs.getBoolean("cursoactivo"));
		curso.setNombre(rs.getString("cursonombre"));
		curso.setIdentificador(rs.getString("cursoidentificador"));
		curso.setTemario(rs.getString("cursotemario"));
		curso.setNhoras(rs.getInt("cursonhoras"));
		curso.setPrecio(rs.getDouble("cursoprecio"));
		curso.setFinicio(rs.getDate("cursofinicio"));
		curso.setFfin(rs.getDate("cursoffin"));
		ClienteMapper cm = new ClienteMapper();
		curso.setCliente(cm.mapRow(rs, rowNum));
		ProfesorMapper pm = new ProfesorMapper();
		curso.setProfesor(pm.mapRow(rs, rowNum));
		/*
		 * 
		 * 
		 * 
		 * 
		 * private Cliente cliente; private Profesor profesor;
		 * 
		 * 
		 * 
		 */
		return curso;
	}

}
