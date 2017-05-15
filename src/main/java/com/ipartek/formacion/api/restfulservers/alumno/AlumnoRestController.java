package com.ipartek.formacion.api.restfulservers.alumno;

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

import com.ipartek.formacion.dbms.persistence.Alumno;
import com.ipartek.formacion.service.interfaces.AlumnoService;

@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/alumnos")
public class AlumnoRestController {
	/*
	 * 1===1 ---> son el mismo valor y el mismo tipo 1==='1' ---> no es identico
	 * pq no son del mismo tipo 1=='1' ---> son el mismo valor
	 * 
	 * @Autowired === @Inject
	 * 
	 * @EJB == @Inject
	 * 
	 */

	@Autowired
	AlumnoService aS;

	//
	// @Autowired + @Qualifier("alumnoValidator") = @Resource(name
	// ="alumnoValidator")
	@Resource(name = "alumnoValidator")
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
		binder.setValidator(validator);
	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Alumno> getById(@PathVariable("codigo") int id) {
		Alumno alumno = aS.getById(id);
		ResponseEntity<Alumno> response = null;

		if (alumno == null) {// 404
			response = new ResponseEntity<Alumno>(HttpStatus.NOT_FOUND);
		} else {// 200
			response = new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
		}

		return response;
	}

	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Alumno>> getAll() {
		List<Alumno> alumnos = aS.getAll();
		ResponseEntity<List<Alumno>> response = null;

		if (alumnos == null || alumnos.isEmpty()) {
			response = new ResponseEntity<List<Alumno>>(HttpStatus.NO_CONTENT);
		} else {
			response = new ResponseEntity<List<Alumno>>(alumnos, HttpStatus.OK);
		}

		return response;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Void> create(@Valid @RequestBody Alumno alumno, UriComponentsBuilder ucBuilder) {
		Alumno alum = aS.getByDni(alumno.getDni());
		ResponseEntity<Void> response = null;

		if (alum != null) {
			response = new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			try {
				Alumno aux = aS.create(alumno);
				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(ucBuilder.path("/api/alumnos/{codigo}").buildAndExpand(aux.getCodigo()).toUri());
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
	@RequestMapping(value = "/{codigo}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, method = RequestMethod.PUT, produces = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Alumno> update(@PathVariable("codigo") int id, @Valid @RequestBody Alumno alumno) {
		Alumno alum = aS.getById(id);
		ResponseEntity<Alumno> response = null;

		if (alum == null) {
			response = new ResponseEntity<Alumno>(HttpStatus.NOT_FOUND);
		} else {
			try {
				alum = aS.update(alumno);
				response = new ResponseEntity<Alumno>(alum, HttpStatus.ACCEPTED);

			} catch (Exception e) {
				response = new ResponseEntity<Alumno>(HttpStatus.NOT_ACCEPTABLE);
			}
		}

		return response;
	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Alumno> deleteById(@PathVariable("codigo") int id) {
		Alumno alum = aS.getById(id);
		ResponseEntity<Alumno> response = null;
		if (alum == null) {
			response = new ResponseEntity<Alumno>(HttpStatus.NOT_FOUND);
		} else {
			aS.delete(id);
			response = new ResponseEntity<Alumno>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
}
