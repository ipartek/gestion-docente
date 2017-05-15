package com.ipartek.formacion.api.restfulservers.alumno.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.ipartek.formacion.api.restfulservers.alumno")
public class AlumnoRestControllerConfiguration {

}
