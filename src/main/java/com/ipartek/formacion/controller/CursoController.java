package com.ipartek.formacion.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ipartek.formacion.controller.exceptions.CursoNoEncontradoException;
import com.ipartek.formacion.controller.pojo.Mensaje;
import com.ipartek.formacion.controller.pojo.MensajeType;
import com.ipartek.formacion.controller.validator.CursoValidator;
import com.ipartek.formacion.controller.validator.FileValidator;
import com.ipartek.formacion.dbms.persistence.Curso;
import com.ipartek.formacion.dbms.persistence.Profesor;
import com.ipartek.formacion.service.interfaces.AlumnoService;
import com.ipartek.formacion.service.interfaces.ClienteService;
import com.ipartek.formacion.service.interfaces.CursoService;
import com.ipartek.formacion.service.interfaces.ProfesorService;

@Controller
@RequestMapping("/cursos")
public class CursoController {

	@Autowired
	private CursoService cS;
	@Autowired
	private ProfesorService pS;
	@Autowired
	private ClienteService cliS;
	@Autowired
	private AlumnoService aS;
	@Resource(name = "cursoValidator")
	CursoValidator validator;
	private ModelAndView mav = null;
	@Autowired
	private MessageSource msgSrc;

	@Autowired
	private ServletContext servletContext;
	private static final Logger LOGGER = LoggerFactory.getLogger(CursoController.class);

	@InitBinder("curso")
	public void initBinder(WebDataBinder binder, Locale locale) {
		// DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT,
		// locale);

		DateFormat dtf = new SimpleDateFormat(msgSrc.getMessage("formato.fecha", null, locale));
		dtf.setLenient(false);
		// dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dtf, true));
		binder.addValidators(validator);
	}

	@InitBinder("fichero")
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new FileValidator());
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getAll(Model model) {
		List<Curso> cursos = cS.getAll();
		// LOGGER.info("tamaño:" + cursos.size());
		model.addAttribute("listadoCursos", cursos);
		return "cursos";
	}

	@RequestMapping(value = "/{codigo}")
	public String getById(@PathVariable("codigo") long codigo, Model model) throws CursoNoEncontradoException {
		Curso curso = cS.getById(codigo);
		if (curso == null) {
			throw new CursoNoEncontradoException(codigo);
		}
		model.addAttribute("curso", curso);
		return "cursodetalle";
	}

	@RequestMapping(value = "/{codigocurso}/alumnos/{codigoalumno}")
	public ModelAndView getAlumnoByCurso(@PathVariable("codigocurso") long codigocurso,
			@PathVariable("codigoalumno") long codigoalumno) {

		return mav;
	}

	@RequestMapping(value = "/editCurso/{codigocurso}", method = RequestMethod.GET)
	public ModelAndView editCurso(@PathVariable("codigocurso") long codigocurso) {
		mav = new ModelAndView("cursoform");
		Curso curso = cS.getById(codigocurso);
		// LOGGER.info(codigocurso + " " + curso.toString());
		mav.addObject("curso", curso);
		List<Profesor> profesores = pS.getAll();
		mav.addObject("listadoProfesores", profesores);
		mav.addObject("listadoClientes", cliS.getAll());
		mav.addObject("listadoAlumnos", aS.getAll());
		return mav;
	}

	@RequestMapping(value = "/deleteCurso/{codigocurso}")
	public String deleteCurso(@PathVariable("codigocurso") long codigocurso, Model model) {

		return "redirect:/cursos";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveCurso(@Validated @RequestParam("fichero") MultipartFile file,
			@ModelAttribute("curso") @Valid Curso curso, BindingResult bindingResult, ModelMap model,
			RedirectAttributes redirectMap) throws IOException {

		/*
		 * Model ModelMap RedirectAttributes
		 */
		String destino = "";
		Mensaje mensaje = null;
		String txt = "";

		LOGGER.info(curso.toString());
		if (bindingResult.hasErrors()) {
			// bindingResult.
			LOGGER.info("curso tiene errores" + bindingResult.getErrorCount());

			model.addAttribute("listadoProfesores", pS.getAll());
			model.addAttribute("listadoAlumnos", aS.getAll());
			model.addAttribute("listadoClientes", cliS.getAll());

			mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);

			txt = "Los datos de formulario contienen errores";

			model.addAttribute("mensaje", mensaje);
			destino = "cursoform";
		} else {
			destino = "redirect:/cursos";

			String fileName = uploadFile(file);

			curso.setTemario(fileName);
			if (curso.getCodigo() > Curso.CODIGO_NULO) {
				LOGGER.info(curso.toString());
				LOGGER.info(curso.getProfesor().toString());
				// LOGGER.info(curso.getImparticiones().toString());

				try {
					cS.update(curso);
					txt = "El curso se ha actualizado correctamente.";
					mensaje = new Mensaje(MensajeType.MSG_TYPE_SUCCESS);
				} catch (Exception e) {
					LOGGER.error("Se ha lanzadado una excepcion update. " + e.toString());
					mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);
					txt = "Ha habido problemas en la actualización.";
					// destino = "cursos/cursoform";
				}

			} else {
				LOGGER.info(curso.toString());
				LOGGER.info(curso.getProfesor().toString());
				// LOGGER.info(curso.getImparticiones().toString());
				try {
					cS.create(curso);
					txt = "El curso se ha creado correctamente.";
					mensaje = new Mensaje(MensajeType.MSG_TYPE_SUCCESS);
				} catch (Exception e) {
					LOGGER.info("Se ha lanzadado una excepcion create. " + e.toString());
					mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);
					txt = "Ha habido problemas en la creación del curso.";
					// destino = "cursos/cursoform";
				}
			}
			mensaje.setMsg(txt);
			LOGGER.info(mensaje.toString());
			redirectMap.addFlashAttribute("mensaje", mensaje);
		}
		return destino;
	}

	/**
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private String uploadFile(MultipartFile file) throws IOException {
		String fileName = null;
		// obtengo el chorro de datos
		InputStream in = file.getInputStream();

		String root = File.separator + "resources" + File.separator + "docs" + File.separator;
		// ruta absoluta del contexto de la aplicación
		String ruta = servletContext.getRealPath(root);

		// crearme el archivo fisico que no tiene nada con un
		File destination = new File(ruta + File.separator + file.getOriginalFilename());
		if (!destination.isDirectory()) {
			// se copia el chorro de bits al archivo fisico
			FileUtils.copyInputStreamToFile(in, destination);
			LOGGER.info(destination.getAbsolutePath());

			LOGGER.info(ruta + File.separator + file.getOriginalFilename());
			// guardo dentro de Curso --> Temario la ruta del fichero

			fileName = file.getOriginalFilename();
		}
		return fileName;
	}

	@RequestMapping(value = "/addCurso")
	public ModelAndView addCurso() {
		mav = new ModelAndView("cursoform");
		Curso curso = new Curso();
		curso.setActivo(true);
		mav.addObject("curso", curso);
		List<Profesor> profesores = pS.getAll();
		mav.addObject("listadoProfesores", profesores);
		mav.addObject("listadoClientes", cliS.getAll());
		mav.addObject("listadoAlumnos", aS.getAll());
		return mav;
	}

	@ExceptionHandler(CursoNoEncontradoException.class)
	public ModelAndView handleCursoNoEncontradoException(HttpServletRequest request, Exception ex) {
		ModelAndView mav = null;
		LOGGER.error("URL pedida" + request.getRequestURL());
		LOGGER.error("Excepcion lanzada:" + ex);
		mav = new ModelAndView();
		mav.addObject("url", request.getRequestURL());
		mav.addObject("exception", ex);
		mav.setViewName("error");
		return mav;
	}
}