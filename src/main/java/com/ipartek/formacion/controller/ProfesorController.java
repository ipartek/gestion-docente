package com.ipartek.formacion.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ipartek.formacion.dbms.persistence.Profesor;
import com.ipartek.formacion.service.interfaces.ProfesorService;

@Controller
@RequestMapping("/profesores")
public class ProfesorController {

	@Autowired
	private ProfesorService pS;
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfesorController.class);
	private ModelAndView mav = null;

	@InitBinder("profesor")
	public void initBinder(WebDataBinder binder, Locale locale) {
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, locale);

		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAll() {
		mav = new ModelAndView("profesores");
		// cargar la lista de alumnos
		// engancharla al modelandview
		mav.addObject("listadoProfesores", pS.getAll());
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProfesor(Model model, @ModelAttribute("profesor") @Valid Profesor profesor,
			BindingResult bindingResult) {
		String destino = "";

		if (bindingResult.hasErrors()) {
			// bindingResult.
			LOGGER.info("profesor tiene errores");
			destino = "profesorform";
		} else {
			destino = "redirect:/profesores";
			if (profesor.getCodigo() > Profesor.CODIGO_NULO) {
				LOGGER.info(profesor.toString());
				pS.update(profesor);
			} else {
				LOGGER.info(profesor.toString());
				pS.create(profesor);
			}
		}
		return destino;
	}

	@RequestMapping(value = "/{id}")
	public ModelAndView getById(@PathVariable("id") int id) {
		mav = new ModelAndView("profesorform");
		mav.addObject("profesor", pS.getById(id));
		return mav;
	}

	@RequestMapping(value = "/addProfesor")
	public String addAlumno(Model model) {
		model.addAttribute("profesor", new Profesor());
		return "profesorform";
	}

	@RequestMapping(value = "/deleteProfesor/{id}")
	public String delete(@PathVariable("id") int id) {
		pS.delete(id);
		return "redirect:/profesores";
	}

}
