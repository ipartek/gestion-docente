<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>  
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>   
	<section class="row">
		<header class="col-xs-12"><h2 class="text-center text-capitalize">Lista de Alumnos</h2></header>
		<div class="col-xs-12">
		<a class="btn btn-info" href="<c:url value='/alumnos/addAlumno'/>">Crear Alumno</a>
		<a class="btn btn-danger text-capitalize" href="#">borrar alumnos</a>
		</div>
		<div class="col-xs-12">
			<!-- si se anidan las columnas tiene que haber un row -->
			<div class="row">
				<div class="col-xs-1"><input id="selectall" type="checkbox"></div>
				<div class="col-xs-1 col-md-2 col-lg-1">nombre</div>
				<div class="col-xs-3 col-sm-2">apellidos</div>
				<div class="hidden-xs col-sm-2">email</div>
				<div class="hidden-xs col-sm-1">teléfono</div>
				<div class="hidden-xs hidden-sm col-md-2">direcci&oacute;n</div>
				<div class="col-xs-4 col-sm-3"></div>
			</div>
			<c:choose>
				<c:when test="${not empty listadoAlumnos}">
					<c:forEach var="alumno" items="${listadoAlumnos}">
					<sec:authorize var="" access="isAuthenticated()"/>
					<c:if test="${isLogged || alumno.activo==true}">
						<div class="row">
							<div class="col-xs-1">
								<input type="checkbox" value="${alumno.codigo}">
							</div>
							<div class="col-xs-2 col-md-1">
								${alumno.nombre}
							</div>
							<div class="col-xs-3 col-md-2">
								${alumno.apellidos}
							</div>
							<div class="col-xs-2">
								${alumno.email}
							</div>
							<div class="hidden-xs col-sm-1">
								${alumno.telefono}
							</div>
							<div class="hidden-xs hidden-sm col-md-2">
								${alumno.direccion}
							</div>
							<div class="col-xs-4 col-sm-3">
								<a class="" href="<c:url value='/alumnos/${alumno.codigo}'/>">Editar</a>
								<a class="" href="<c:url value='/alumnos/deleteAlumno/${alumno.codigo}'/>">Borrar</a>
								<a class="" href="<c:url value='/alumnos/getInforme/${alumno.codigo}'/>">Ver Informe</a>
							</div>
						</div>
					</c:if>	
					</c:forEach>
				</c:when>
				<c:otherwise><!-- Cuando la lista NO tiene datos -->
					<div class="row"><span class="text-danger text-center">no se han encontrado alumnos en la Base de Datos</span></div>
				</c:otherwise>
			</c:choose>
		</div>
	</section>
