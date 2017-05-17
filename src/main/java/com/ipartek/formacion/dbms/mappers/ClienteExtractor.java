package com.ipartek.formacion.dbms.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.ipartek.formacion.dbms.persistence.Cliente;
import com.ipartek.formacion.dbms.persistence.Curso;

public class ClienteExtractor implements ResultSetExtractor<Map<Integer, Cliente>> {
	private final static Logger LOGGER = LoggerFactory.getLogger(ClienteExtractor.class);

	@Override
	public Map<Integer, Cliente> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<Integer, Cliente> clientes = new HashMap<Integer, Cliente>();
		// Map<Long, Curso> cursos = null;
		while (rs.next()) {
			// recogemos el codigo de cliente
			int codigo = rs.getInt("clientecodigo");
			// recogemos el cliente del mapa
			Cliente cliente = clientes.get(codigo);

			if (cliente == null) {// si el cliente no esta en el mapa
				cliente = new Cliente();
				cliente.setNombre(rs.getString("clientenombre"));
				cliente.setIdentificador(rs.getString("clienteidentificador"));
				cliente.setCodigoPostal(rs.getInt("clientecodigopostal"));
				cliente.setDireccion(rs.getString("clientedireccion"));
				cliente.setEmail(rs.getString("clienteemail"));
				cliente.setPoblacion(rs.getString("clientepoblacion"));
				cliente.setTelefono(String.valueOf(rs.getInt("clientetelefono")));
				cliente.setActivo(rs.getBoolean("clienteactivo"));
				cliente.setCodigo(rs.getInt("clientecodigo"));
				// cliente.setCursos();
				clientes.put(cliente.getCodigo(), cliente);
			}
			// aqui es donde cargamos el mapa de cursos
			// cursos = cliente.getCursos();
			Long cCurso = rs.getLong("cursocodigo");
			LOGGER.info("nº cursos1: " + cliente.getCursos().size());
			if (cCurso != null && cCurso > -1) {
				Curso curso = new Curso();
				curso.setCodigo(rs.getLong("cursocodigo"));
				curso.setNombre(rs.getString("cursonombre"));
				curso.setFinicio(rs.getDate("cursofinicio"));
				curso.setFfin(rs.getDate("cursoffin"));
				curso.setNhoras(rs.getInt("cursonhoras"));
				cliente.getCursos().put(cCurso, curso);
			}
			LOGGER.info("nº cursos2: " + cliente.getCursos().size());
		}

		return clientes;
	}

}
