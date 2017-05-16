package com.ipartek.formacion.api.restfulservers.cliente;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ipartek.formacion.dbms.persistence.Cliente;
import com.ipartek.formacion.service.interfaces.ClienteService;

@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/clientes")
public class ClienteRestController {

	@Autowired
	ClienteService cS;

	@Resource(name = "clienteValidator")
	Validator validator;

	// http://gestionformacion/api/alumnos/1
	// PUT -----> UPDATE
	// DELETE --> DELETE
	// GET------> GETBYID
	// http://gestionformacion/api/alumno
	// GET------> GETALL
	// POST-----> CREATE

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Cliente> getById(@PathVariable("codigo") int id) {
		Cliente cliente = cS.getById(id);
		ResponseEntity<Cliente> response = null;

		if (cliente == null) {// 404
			response = new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		} else {// 200
			response = new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		}

		return response;
	}

	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Cliente>> getAll() {
		List<Cliente> clientes = cS.getAll();
		ResponseEntity<List<Cliente>> response = null;

		if (clientes == null || clientes.isEmpty()) {
			response = new ResponseEntity<List<Cliente>>(HttpStatus.NO_CONTENT);
		} else {
			response = new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
		}

		return response;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> create(@Valid @RequestBody Cliente cliente, UriComponentsBuilder ucBuilder) {
		Cliente client = cS.getByIdentificador(cliente.getIdentificador());
		ResponseEntity<Void> response = null;

		if (client != null) {
			response = new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			try {
				Cliente aux = cS.create(cliente);
				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(ucBuilder.path("/api/clientes/{codigo}").buildAndExpand(aux.getCodigo()).toUri());
				response = new ResponseEntity<Void>(headers, HttpStatus.CREATED);
			} catch (Exception e) {
				response = new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);

			}
			// Si no reciclase el metodo getById la respuesta es <Alumno>
			// response = new ResponseEntity<Alumno>(alumno,HttpStatus.CREATED);
			// se manipulan los encabezados HTTP para llamar al metodo getById
			// del RestController

		}

		return response;
	}

	@RequestMapping(value = "/{codigo}", consumes = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.PUT, produces = {
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Cliente> update(@PathVariable("codigo") int id, @Valid @RequestBody Cliente cliente) {
		Cliente client = cS.getById(id);
		ResponseEntity<Cliente> response = null;

		if (client == null) {
			response = new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		} else {
			try {
				client = cS.update(cliente);
				response = new ResponseEntity<Cliente>(client, HttpStatus.ACCEPTED);

			} catch (Exception e) {
				response = new ResponseEntity<Cliente>(HttpStatus.NOT_ACCEPTABLE);
			}
		}

		return response;
	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Cliente> deleteById(@PathVariable("codigo") int id) {
		Cliente client = cS.getById(id);
		ResponseEntity<Cliente> response = null;
		if (client == null) {
			response = new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		} else {
			cS.delete(id);
			response = new ResponseEntity<Cliente>(HttpStatus.NO_CONTENT);
		}
		return response;
	}

}
