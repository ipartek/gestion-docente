package com.ipartek.formacion.dbms.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.ipartek.formacion.dbms.dao.interfaces.CursoDAO;
import com.ipartek.formacion.dbms.persistence.Curso;

public class CursoDAOImp implements CursoDAO {
	@Autowired
	@Qualifier("mysqlDataSource")
	private DataSource dataSource;
	private JdbcTemplate jdbctemplate;
	private SimpleJdbcCall jdbcCall;

	@Autowired
	@Qualifier("mysqlDataSource") // es lo mismo que @Inject
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbctemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Curso> getAll() {

		return null;
	}

	@Override
	public Curso getById(long codigo) {

		return null;
	}

	@Override
	public void delete(long codigo) {

	}

	@Override
	public Curso update(Curso curso) {

		return null;
	}

	@Override
	public Curso create(Curso curso) {

		return null;
	}

}
