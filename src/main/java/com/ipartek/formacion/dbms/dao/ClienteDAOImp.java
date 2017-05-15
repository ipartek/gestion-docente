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

import com.ipartek.formacion.dbms.dao.interfaces.ClienteDAO;
import com.ipartek.formacion.dbms.mappers.ClienteExtractor;
import com.ipartek.formacion.dbms.mappers.ClienteMapper;
import com.ipartek.formacion.dbms.persistence.Cliente;

@Repository("clienteDaoImp")
public class ClienteDAOImp implements ClienteDAO {
	private final static Logger LOGGER = LoggerFactory.getLogger(ClienteDAOImp.class);

	@Autowired
	@Qualifier("mysqlDataSource")
	private DataSource dataSource;
	private JdbcTemplate jdbctemplate;
	private SimpleJdbcCall jdbcCall;

	@Autowired
	@Qualifier("mysqlDataSource")
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbctemplate = new JdbcTemplate(dataSource);

	}

	@Override
	public Cliente create(Cliente cliente) {
		final String SQL = "clienteCreate";
		this.jdbcCall = new SimpleJdbcCall(dataSource);

		jdbcCall.withProcedureName(SQL);
		SqlParameterSource in = new MapSqlParameterSource().addValue("pnombre", cliente.getNombre())
				.addValue("pcodigopostal", cliente.getCodigoPostal()).addValue("pdireccion", cliente.getDireccion())
				.addValue("ptelefono", cliente.getTelefono()).addValue("pidentificador", cliente.getIdentificador())
				.addValue("pemail", cliente.getEmail()).addValue("ppoblacion", cliente.getPoblacion());

		LOGGER.info(cliente.toString());
		Map<String, Object> out = jdbcCall.execute(in);
		cliente.setCodigo((Integer) out.get("pcodigo"));
		LOGGER.info(cliente.toString());
		return cliente;
	}

	@Override
	public Cliente getById(long codigo) {
		final String SQL = "CALL clientegetById(?);";
		Cliente cliente = null;
		try {
			cliente = jdbctemplate.queryForObject(SQL, new ClienteMapper(), new Object[] { codigo });
			LOGGER.info(cliente.toString());
		} catch (EmptyResultDataAccessException e) {
			LOGGER.info("sin datos:" + e.getMessage() + " " + SQL);
		}
		return cliente;
	}

	@Override
	public List<Cliente> getAll() {
		final String SQL = "CALL clientegetAll();";
		List<Cliente> clientes = null;
		try {
			clientes = jdbctemplate.query(SQL, new ClienteMapper());
			LOGGER.info(String.valueOf(clientes.size()));
		} catch (EmptyResultDataAccessException e) {
			LOGGER.info("sin datos:" + e.getMessage() + " " + SQL);
		}

		return clientes;
	}

	@Override
	public Cliente update(Cliente cliente) {
		final String SQL = "clienteUpdate";
		this.jdbcCall = new SimpleJdbcCall(dataSource);

		jdbcCall.withProcedureName(SQL);
		SqlParameterSource in = new MapSqlParameterSource().addValue("pnombre", cliente.getNombre())
				.addValue("pcodigopostal", cliente.getCodigoPostal()).addValue("pdireccion", cliente.getDireccion())
				.addValue("ptelefono", cliente.getTelefono()).addValue("pidentificador", cliente.getIdentificador())
				.addValue("pemail", cliente.getEmail()).addValue("ppoblacion", cliente.getPoblacion())
				.addValue("pcodigo", cliente.getCodigo());

		LOGGER.info(cliente.toString());
		jdbcCall.execute(in);
		return cliente;
	}

	@Override
	public void delete(long codigo) {
		final String SQL = "clienteDelete";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		jdbcCall.withProcedureName(SQL);
	}

	@Override
	public Cliente getByIdentificador(String identificador) {
		final String SQL = "CALL clientegetByIdentificador(?);";
		Cliente cliente = null;
		try {
			cliente = jdbctemplate.queryForObject(SQL, new ClienteMapper(), new Object[] { identificador });
			LOGGER.info(cliente.toString());
		} catch (EmptyResultDataAccessException e) {
			cliente = null;
			LOGGER.info("sin datos:" + e.getMessage() + " " + SQL);
		}
		return cliente;
	}

	@Override
	public Cliente getInforme(int codigo) {
		final String SQL = "CALL clienteInforme(?);";
		Cliente cliente = null;
		try {
			LOGGER.info("codigo:" + codigo);
			Map<Integer, Cliente> clientes = jdbctemplate.query(SQL, new ClienteExtractor(), new Object[] { codigo });
			cliente = clientes.get(codigo);
			LOGGER.info("cliente:" + cliente.toString());
		} catch (EmptyResultDataAccessException e) {
			cliente = null;
			LOGGER.info("sin datos" + e.getMessage() + " " + SQL);
		}
		return cliente;
	}

}
