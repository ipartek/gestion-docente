package com.ipartek.formacion.api.restfulservers.curso;

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

import com.ipartek.formacion.dbms.persistence.Curso;
import com.ipartek.formacion.service.interfaces.CursoService;

@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/cursos")
public class CursoRestController {
	@Autowired
	CursoService cS;

	@Resource(name = "cursoValidator")
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
	public ResponseEntity<Curso> getById(@PathVariable("codigo") int id) {
		Curso curso = cS.getById(id);
		ResponseEntity<Curso> response = null;

		if (curso == null) {// 404
			response = new ResponseEntity<Curso>(HttpStatus.NOT_FOUND);
		} else {// 200
			response = new ResponseEntity<Curso>(curso, HttpStatus.OK);
		}

		return response;
	}

	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Curso>> getAll() {
		List<Curso> cursos = cS.getAll();
		ResponseEntity<List<Curso>> response = null;

		if (cursos == null || cursos.isEmpty()) {
			response = new ResponseEntity<List<Curso>>(HttpStatus.NO_CONTENT);
		} else {
			response = new ResponseEntity<List<Curso>>(cursos, HttpStatus.OK);
		}

		return response;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> create(@Valid @RequestBody Curso curso, UriComponentsBuilder ucBuilder) {
		Curso course = cS.getById(curso.getCodigo());
		ResponseEntity<Void> response = null;

		if (course != null) {
			response = new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			try {
				Curso aux = cS.create(course);
				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(ucBuilder.path("/api/curso/{codigo}").buildAndExpand(aux.getCodigo()).toUri());
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
	public ResponseEntity<Curso> update(@PathVariable("codigo") int id, @Valid @RequestBody Curso curso) {
		Curso course = cS.getById(id);
		ResponseEntity<Curso> response = null;

		if (course == null) {
			response = new ResponseEntity<Curso>(HttpStatus.NOT_FOUND);
		} else {
			try {
				course = cS.update(curso);
				response = new ResponseEntity<Curso>(course, HttpStatus.ACCEPTED);

			} catch (Exception e) {
				response = new ResponseEntity<Curso>(HttpStatus.NOT_ACCEPTABLE);
			}
		}

		return response;
	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Curso> deleteById(@PathVariable("codigo") int id) {
		Curso course = cS.getById(id);
		ResponseEntity<Curso> response = null;
		if (course == null) {
			response = new ResponseEntity<Curso>(HttpStatus.NOT_FOUND);
		} else {
			cS.delete(id);
			response = new ResponseEntity<Curso>(HttpStatus.NO_CONTENT);
		}
		return response;
	}

}
