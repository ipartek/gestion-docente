package com.ipartek.formacion.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ipartek.formacion.dbms.persistence.Alumno;
import com.ipartek.formacion.service.interfaces.AlumnoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context-test.xml")
public class AlumnoServiceTest {

	@Autowired
	AlumnoService as;
	int[] codigos;
	Alumno alumno;
	List<Alumno> alumnos;

	@Before
	public void setUp() throws Exception {
		alumno = new Alumno();
		alumno.setNombre("Jon Koldobika");
		alumno.setApellidos("Ajurigogeaskoa Belaustegigoitia");
		alumno.setDni("30685168z");
		alumno.setCodigoPostal(48006);
		codigos = new int[4];
		codigos[0] = 0;
		codigos[1] = 1;
		codigos[2] = 2;
		codigos[3] = 4;
	}

	@After
	public void tearDown() throws Exception {
		alumno = null;
	}

	@Test
	public void testClase() {
		assertEquals("class com.ipartek.formacion.service.AlumnoServiceImp", this.as.getClass().toString());
	}

	@Test()
	public void createTest() {
		Alumno alum = as.create(alumno);
		assertNotNull("El alumno es nulo", alum);
		assertTrue("El codigo debe ser mayor que 0", alum.getCodigo() > 0); // if(alumno>0)==true)
		assertFalse("El codigo no debe ser menor que 0", alum.getCodigo() < 0);
		assertEquals("el nombre no es identico", alum.getNombre(), alumno.getNombre());
		// Todos los atributos
		assertEquals("Los datos no son identicos", alum, as.getById(alum.getCodigo()));

		as.delete(alum.getCodigo());
	}

	@Test
	public void deleteTest() {
		Alumno alum = as.create(alumno);
		as.delete(alum.getCodigo());
		assertNull("El alumno no se ha borrado correctamente", as.getById(alum.getCodigo()));
		// TODO hay que validar que el alumno exista antes de borrarlo
		as.delete(-1);
	}

	@Test
	public void getByIdTest() {
		for (int i = 0; i < codigos.length; i++) {
			Alumno alum = as.getById(codigos[i]);
			assertNotNull("El alumno tiene que existir. El alumno con codigo " + codigos[i] + " esta en base de datos",
					alum);
			assertEquals("El codigo del alumno no coincide. El codigo enviado es:" + codigos[i] + " y el recibido es:"
					+ alum.getCodigo(), codigos[i], alum.getCodigo());
		}
	}

	@Test(timeout = 800)
	public void getAllTest() {
		List<Alumno> alumnos = as.getAll();
		int size = 4;
		assertEquals("número de alumnos incorrecto", size, alumnos.size());
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

}
