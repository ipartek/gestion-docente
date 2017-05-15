package com.ipartek.formacion.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ipartek.formacion.dbms.persistence.Alumno;
import com.ipartek.formacion.service.interfaces.AlumnoService;

@Controller()
@RequestMapping(value = "/alumnos")
public class AlumnoController {
	@Inject() // @Autowired
	private AlumnoService aS;
	@Autowired
	private MessageSource msgSrc;
	private static final Logger LOGGER = LoggerFactory.getLogger(AlumnoController.class);
	private ModelAndView mav = null;

	@Resource(name = "alumnoValidator") // ==@Autowired@Qualifier("alumnoValidator")
	private Validator validator = null;

	@InitBinder("alumno")
	public void initBinder(WebDataBinder binder, Locale locale) {

		// DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT,
		// locale);
		DateFormat dtf = new SimpleDateFormat(msgSrc.getMessage("formato.fecha", null, locale));
		dtf.setLenient(false);
		// dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dtf, true));
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAll() {
		mav = new ModelAndView("alumnos");
		// cargar la lista de alumnos
		List<Alumno> alumnos = aS.getAll();
		// engancharla al modelandview
		mav.addObject("listadoAlumnos", alumnos);
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveAlumno(Model model, @ModelAttribute("alumno") @Validated Alumno alumno,
			BindingResult bindingResult) {
		String destino = "";

		if (bindingResult.hasErrors()) {
			LOGGER.info("alumno tiene errores");
			destino = "alumnoform";
		} else {
			destino = "redirect:/alumnos";
			if (alumno.getCodigo() > Alumno.CODIGO_NULO) {
				LOGGER.info(alumno.toString());
				aS.update(alumno);
			} else {
				LOGGER.info(alumno.toString());
				aS.create(alumno);
			}
		}
		return destino;
	}

	@RequestMapping(value = "/{id}")
	public ModelAndView getById(@PathVariable("id") int id) {
		mav = new ModelAndView("alumnoform");
		mav.addObject("alumno", aS.getById(id));
		return mav;
	}

	@RequestMapping(value = "/addAlumno")
	public String addAlumno(Model model) {
		model.addAttribute("alumno", new Alumno());
		return "alumnoform";
	}

	@RequestMapping(value = "/deleteAlumno/{id}")
	public String delete(@PathVariable("id") int id) {
		aS.delete(id);
		return "redirect:/alumnos";
	}

	@RequestMapping(value = "/getInforme/{codigo}")
	public String getInforme(Model model, @PathVariable("codigo") int codigo) {
		Alumno alumno = aS.getInforme(codigo);
		model.addAttribute("alumno", alumno);
		return "alumnodetalle";

	}
}
