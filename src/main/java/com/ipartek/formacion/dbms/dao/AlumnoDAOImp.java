package com.ipartek.formacion.dbms.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.ipartek.formacion.dbms.dao.interfaces.AlumnoDAO;
import com.ipartek.formacion.dbms.mappers.AlumnoExtractor;
import com.ipartek.formacion.dbms.mappers.AlumnoMapper;
import com.ipartek.formacion.dbms.persistence.Alumno;

@Repository("alumnoDaoImp")
public class AlumnoDAOImp implements AlumnoDAO {

	@Autowired
	@Qualifier("mysqlDataSource")
	private DataSource dataSource;
	private JdbcTemplate jdbctemplate;
	private SimpleJdbcCall jdbcCall;
	private final static Logger LOGGER = LoggerFactory.getLogger(AlumnoDAOImp.class);
	@Value("${alumno.crear}")
	private String sqlCreate;
	@Value("${alumno.borrar}")
	private String sqlDelete;
	@Value("${alumno.editar}")
	private String sqlUpdate;
	@Value("${alumno.obtener.todos}")
	private String sqlReadall;
	@Value("${alumno.obtener.id}")
	private String sqlReadbyid;
	@Value("${alumno.obtener.dni}")
	private String sqlReadbydni;

	@Autowired
	@Qualifier("mysqlDataSource") // es lo mismo que @Inject
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbctemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Alumno create(Alumno alumno) {
		// se asigna el nombre del procedimiento almacenado
		final String SQL = "alumnoCreate";
		// se llama al construcctor dado que cachea el SqlParameterSource
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		jdbcCall.withProcedureName(SQL);
		// crear un mapa con los parametros de procedimiento almacenado de
		// entrada (IN).
		SqlParameterSource in = new MapSqlParameterSource().addValue("pnombre", alumno.getNombre())
				.addValue("papellidos", alumno.getApellidos()).addValue("pcodigopostal", alumno.getCodigoPostal())
				.addValue("pnHermanos", alumno.getnHermanos()).addValue("pdireccion", alumno.getDireccion())
				.addValue("ptelefono", alumno.getTelefono()).addValue("pdni", alumno.getDni())
				.addValue("pemail", alumno.getEmail()).addValue("ppoblacion", alumno.getPoblacion())
				.addValue("pfNacimiento", alumno.getfNacimiento());
		LOGGER.info(alumno.toString());
		// se ejecuta la consulta
		Map<String, Object> out = jdbcCall.execute(in);
		// en out se han recogido los parametros out de la consulta a BBDD.
		alumno.setCodigo((Integer) out.get("pcodigo"));

		return alumno;
	}

	@Override
	public List<Alumno> getAll() {
		final String SQL = "CALL alumnogetAll();";
		List<Alumno> alumnos = null;
		try {
			alumnos = jdbctemplate.query(SQL, new AlumnoMapper());
			LOGGER.info(String.valueOf(alumnos.size()));
		} catch (EmptyResultDataAccessException e) {
			alumnos = null;
			LOGGER.info("sin datos:" + e.getMessage() + " " + SQL);
		}

		return alumnos;
	}

	@Override
	public Alumno getById(long codigo) {
		Alumno alumno = null;
		final String SQL = "CALL alumnogetById(?);";
		try {
			alumno = jdbctemplate.queryForObject(SQL, new AlumnoMapper(), new Object[] { codigo });
			LOGGER.info(alumno.toString());
		} catch (EmptyResultDataAccessException e) {
			alumno = null;
			LOGGER.info("No se ha encontrado Alumno para codigo: " + codigo + " " + e.getMessage());
		}
		return alumno;
	}

	@Override
	public Alumno update(Alumno alumno) {
		// se asigna el nombre del procedimiento almacenado
		final String SQL = "alumnoUpdate";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		jdbcCall.withProcedureName(SQL);
		// crear un mapa con los parametros de procedimiento almacenado de
		// entrada (IN).
		SqlParameterSource in = new MapSqlParameterSource().addValue("pnombre", alumno.getNombre())
				.addValue("papellidos", alumno.getApellidos()).addValue("pcodigopostal", alumno.getCodigoPostal())
				.addValue("pnHermanos", alumno.getnHermanos()).addValue("pdireccion", alumno.getDireccion())
				.addValue("ptelefono", alumno.getTelefono()).addValue("pdni", alumno.getDni())
				.addValue("pemail", alumno.getEmail()).addValue("ppoblacion", alumno.getPoblacion())
				.addValue("pfNacimiento", alumno.getfNacimiento()).addValue("pcodigo", alumno.getCodigo());
		LOGGER.info(alumno.toString());
		// se ejecuta
		jdbcCall.execute(in);
		return alumno;
	}

	@Override
	public void delete(long codigo) {

		String SQL = "alumnoDelete";
		this.jdbcCall = new SimpleJdbcCall(dataSource);
		jdbcCall.withProcedureName(SQL);
		SqlParameterSource in = new MapSqlParameterSource().addValue("pcodigo", codigo);
		LOGGER.info(String.valueOf(codigo));
		jdbcCall.execute(in);

	}

	@Override
	public Alumno getByDni(String dni) {
		Alumno alumno = null;
		String SQL = "call " + sqlReadbydni + "(?);";
		try {
			alumno = jdbctemplate.queryForObject(SQL, new AlumnoMapper(), new Object[] { dni });
			LOGGER.info(alumno.toString());
		} catch (EmptyResultDataAccessException e) {
			alumno = null;
			LOGGER.info("No se ha encontrado Alumno para el dni: " + dni + " " + e.getMessage());
		}
		return alumno;
	}

	@Override
	public Alumno getInforme(long codigo) {
		Alumno alumno = null;
		final String SQL = "CALL alumnoInforme(?)";
		try {
			Map<Long, Alumno> alumnos = jdbctemplate.query(SQL, new AlumnoExtractor(), new Object[] { codigo });
			LOGGER.info("" + alumnos.size());
			alumno = alumnos.get(codigo);
			LOGGER.info(alumno.toString());
		} catch (EmptyResultDataAccessException e) {
			LOGGER.info("No se ha encontrado Alumno para el codigo: " + codigo + " " + e.getMessage());
		}
		return alumno;
	}

}
