package com.ipartek.formacion.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

import com.ipartek.formacion.dbms.persistence.Cliente;
import com.ipartek.formacion.service.interfaces.ClienteService;

/**
 * Este controlador procesara las peticiones de la clase <code>Cliente</code>
 * .INSERT INTO
 * alumno(nombre,apellidos,dni,email,direccion,codigopostal,poblacion,
 * fNacimiento,telefono,nHermanos)
 * 
 * 
 * @author Urko Villanueva.
 *
 */
@Controller
@RequestMapping("/clientes")
public class ClienteController {
	@Inject // @Autowired
	private ClienteService cS;
	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteController.class);
	private ModelAndView mav = null;

	@Autowired
	@Qualifier("clienteValidator")
	private Validator validator = null;

	@InitBinder("cliente")
	public void initBinder(WebDataBinder binder, Locale locale) {
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, locale);

		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAll() {
		mav = new ModelAndView("clientes");
		// cargar la lista de alumnos
		List<Cliente> clientes = cS.getAll();
		// engancharla al modelandview
		mav.addObject("listadoClientes", clientes);
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveCliente(Model model, @ModelAttribute("cliente") @Validated Cliente cliente,
			BindingResult bindingResult) {
		String destino = "";

		if (bindingResult.hasErrors()) {
			LOGGER.info("cliente tiene errores");
			destino = "clienteform";
		} else {
			destino = "redirect:/clientes";
			if (cliente.getCodigo() > Cliente.CODIGO_NULO) {
				LOGGER.info(cliente.toString());
				cS.update(cliente);
			} else {
				LOGGER.info(cliente.toString());
				cS.create(cliente);
			}
		}
		return destino;
	}

	@RequestMapping(value = "/{id}")
	public ModelAndView getById(@PathVariable("id") int id) {
		mav = new ModelAndView("clienteform");
		mav.addObject("cliente", cS.getById(id));
		return mav;
	}

	@RequestMapping(value = "/addCliente")
	public String addAlumno(Model model) {
		model.addAttribute("cliente", new Cliente());
		LOGGER.info(new Cliente().toString());
		return "clienteform";
	}

	@RequestMapping(value = "/deleteCliente/{id}")
	public String delete(@PathVariable("id") int id) {
		cS.delete(id);
		return "redirect:/clientes";
	}

	@RequestMapping(value = "/getInforme/{codigo}")
	public String getInforme(Model model, @PathVariable("codigo") int codigo) {
		LOGGER.info("codigo: " + codigo);
		Cliente cliente = cS.getInforme(codigo);
		LOGGER.info("cursos" + cliente.getCursos().size());
		model.addAttribute("cliente", cliente);
		return "clientedetalle";

	}
}
