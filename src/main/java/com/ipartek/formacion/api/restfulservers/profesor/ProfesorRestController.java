package com.ipartek.formacion.api.restfulservers.profesor;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ipartek.formacion.dbms.persistence.Profesor;
import com.ipartek.formacion.service.interfaces.ProfesorService;

@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/profesores")
public class ProfesorRestController {

	@Autowired
	ProfesorService pS;

	// @Resource(name = "profesorValidator")
	// Validator validator;

	// http://gestionformacion/api/alumnos/1
	// PUT -----> UPDATE
	// DELETE --> DELETE
	// GET------> GETBYID
	// http://gestionformacion/api/alumno
	// GET------> GETALL
	// POST-----> CREATE

	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Profesor> getById(@PathVariable("codigo") int id) {
		Profesor profesor = pS.getById(id);
		ResponseEntity<Profesor> response = null;

		if (profesor == null) {// 404
			response = new ResponseEntity<Profesor>(HttpStatus.NOT_FOUND);
		} else {// 200
			response = new ResponseEntity<Profesor>(profesor, HttpStatus.OK);
		}

		return response;
	}

	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Profesor>> getAll() {
		List<Profesor> profesores = pS.getAll();
		ResponseEntity<List<Profesor>> response = null;

		if (profesores == null || profesores.isEmpty()) {
			response = new ResponseEntity<List<Profesor>>(HttpStatus.NO_CONTENT);
		} else {
			response = new ResponseEntity<List<Profesor>>(profesores, HttpStatus.OK);
		}

		return response;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> create(@Valid @RequestBody Profesor profesor, UriComponentsBuilder ucBuilder) {
		Profesor profe = pS.getByDni(profesor.getDni());
		ResponseEntity<Void> response = null;

		if (profe != null) {
			response = new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			try {
				Profesor aux = pS.create(profesor);
				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(ucBuilder.path("/api/profesores/{codigo}").buildAndExpand(aux.getCodigo()).toUri());
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

	// alumnos/nombre/apellidos/ --> alumnos?nombre= &&acute;apellidos=
	// alumnos/dni (string)
	// alumnos/codigo (numero)
	@RequestMapping(value = "/{codigo}", consumes = {
			MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.PUT, produces = {
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Profesor> update(@PathVariable("codigo") int id, @Valid @RequestBody Profesor profesor) {
		Profesor profe = pS.getById(id);
		ResponseEntity<Profesor> response = null;

		if (profe == null) {
			response = new ResponseEntity<Profesor>(HttpStatus.NOT_FOUND);
		} else {
			try {
				profe = pS.update(profesor);
				response = new ResponseEntity<Profesor>(profe, HttpStatus.ACCEPTED);

			} catch (Exception e) {
				response = new ResponseEntity<Profesor>(HttpStatus.NOT_ACCEPTABLE);
			}
		}

		return response;
	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Profesor> deleteById(@PathVariable("codigo") int id) {
		Profesor profe = pS.getById(id);
		ResponseEntity<Profesor> response = null;
		if (profe == null) {
			response = new ResponseEntity<Profesor>(HttpStatus.NOT_FOUND);
		} else {
			pS.delete(id);
			response = new ResponseEntity<Profesor>(HttpStatus.NO_CONTENT);
		}
		return response;
	}

}
