package com.ipartek.formacion.dbms.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.ipartek.formacion.dbms.dao.interfaces.ProfesorDAO;
import com.ipartek.formacion.dbms.mappers.ProfesorMapper;
import com.ipartek.formacion.dbms.persistence.Profesor;

@Repository("profesorDaoImp")
public class ProfesorDAOImp implements ProfesorDAO {

	@Autowired
	@Qualifier("mysqlDataSource")
	private DataSource dataSource;
	private JdbcTemplate jdbctemplate;
	private SimpleJdbcCall jdbcCall;
	private final static Logger LOGGER = LoggerFactory.getLogger(ProfesorDAOImp.class);

	@Autowired
	@Qualifier("mysqlDataSource")
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbctemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Profesor create(Profesor profesor) {
		String SQL = "profesorCreate";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		jdbcCall.withProcedureName(SQL);
		SqlParameterSource in = new MapSqlParameterSource().addValue("pnombre", profesor.getNombre())
				.addValue("papellidos", profesor.getApellidos()).addValue("pcodigopostal", profesor.getCodigoPostal())
				.addValue("pnss", profesor.getnSS()).addValue("pdireccion", profesor.getDireccion())
				.addValue("ptelefono", profesor.getTelefono()).addValue("pdni", profesor.getDni())
				.addValue("pemail", profesor.getEmail()).addValue("ppoblacion", profesor.getPoblacion())
				.addValue("pfNacimiento", profesor.getfNacimiento());
		LOGGER.info(profesor.toString());
		Map<String, Object> out = jdbcCall.execute(in);
		profesor.setCodigo((Integer) out.get("pcodigo"));
		LOGGER.info(profesor.toString());
		return profesor;
	}

	@Override
	public Profesor getById(long codigo) {
		Profesor profesor = null;
		final String SQL = "CALL profesorgetById(?);";
		try {
			profesor = jdbctemplate.queryForObject(SQL, new ProfesorMapper(), new Object[] { codigo });
			LOGGER.info(profesor.toString());
		} catch (EmptyResultDataAccessException e) {
			LOGGER.info("Sin datos: " + e.getMessage());
		}

		return profesor;
	}

	@Override
	public List<Profesor> getAll() {
		List<Profesor> profesores = null;
		final String SQL = "CALL profesorgetAll();";
		try {
			profesores = jdbctemplate.query(SQL, new ProfesorMapper());
			LOGGER.info("Numero profesores: " + profesores.size());
		} catch (EmptyResultDataAccessException e) {
			LOGGER.info("Sin datos: " + e.getMessage());
		}

		return profesores;
	}

	@Override
	public Profesor update(Profesor profesor) {
		String SQL = "profesorUpdate";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		jdbcCall.withProcedureName(SQL);
		SqlParameterSource in = new MapSqlParameterSource().addValue("pnombre", profesor.getNombre())
				.addValue("papellidos", profesor.getApellidos()).addValue("pcodigopostal", profesor.getCodigoPostal())
				.addValue("pnss", profesor.getnSS()).addValue("pdireccion", profesor.getDireccion())
				.addValue("ptelefono", profesor.getTelefono()).addValue("pdni", profesor.getDni())
				.addValue("pemail", profesor.getEmail()).addValue("ppoblacion", profesor.getPoblacion())
				.addValue("pfNacimiento", profesor.getfNacimiento()).addValue("pcodigo", profesor.getCodigo());
		LOGGER.info(profesor.toString());

		jdbcCall.execute(in);
		return null;
	}

	@Override
	public void delete(long codigo) {
		String SQL = "profesorDelete";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		jdbcCall.withProcedureName(SQL);
		SqlParameterSource in = new MapSqlParameterSource().addValue("pcodigo", codigo);
		LOGGER.info(String.valueOf(codigo));
		jdbcCall.execute(in);

	}

	@Override
	public Profesor getByDni(String dni) {
		Profesor profesor = null;
		final String SQL = "CALL profesorgetByDni(?);";
		try {
			LOGGER.info("Dni enviado:" + dni);
			profesor = jdbctemplate.queryForObject(SQL, new ProfesorMapper(), new Object[] { dni });
			LOGGER.info(profesor.toString());
		} catch (EmptyResultDataAccessException e) {
			LOGGER.info("Sin datos: " + e.getMessage());
			profesor = null;
		}

		return profesor;
	}

	@Override
	public Profesor getByNss(String nss) {
		Profesor profesor = null;
		final String SQL = "CALL profesorgetByNss(?);";
		try {
			profesor = jdbctemplate.queryForObject(SQL, new ProfesorMapper(), new Object[] { nss });
			LOGGER.info(profesor.toString());
		} catch (EmptyResultDataAccessException e) {
			LOGGER.info("Sin datos: " + e.getMessage());
			profesor = null;
		}

		return profesor;
	}

}
