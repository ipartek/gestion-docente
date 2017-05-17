package com.ipartek.formacion.dbms.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.ipartek.formacion.dbms.persistence.Curso;

public class CursoExtractor implements ResultSetExtractor<Map<String, Curso>> {

	@Override
	public Map<String, Curso> extractData(ResultSet rs) throws SQLException, DataAccessException {

		return null;
	}

}
