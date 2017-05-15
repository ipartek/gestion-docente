<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>    
	<section class="row">
		<header class="col-xs-12"><h2>Datos del curso</h2></header>
		<div class="col-xs-12">
			<p>Nombre: ${curso.nombre}</p>
			<p>
				F.Inicio: <fmt:formatDate value="${curso.finicio}" dateStyle="full" /> </p>
			<p>
				F.Fin: 	<fmt:formatDate value="${curso.ffin}"  dateStyle="full" />
	         </p>
	        <p>Horas: ${curso.nhoras}</p>
	       	<c:if test="${not empty curso.temario}">
	        	<p>Temario: <a href="<c:url value="resources/docs/${curso.temario}"/>" download >Ver</a></p>
	        </c:if>
	    
	       	<p>Precio: ${curso.precio}</p>
	       	<p>Cliente: ${curso.cliente.nombre}</p>
	       	<p>Profesor: ${curso.profesor.fullName}
        </div>
		<section class="col-xs-12">
			<header class="row"><h3 class="col-xs-12">Listado de alumnos</h3></header>
			<c:choose>
				<c:when test="${not empty curso.imparticiones}">
					<c:forEach var="imparticion" items="${curso.imparticiones}">
						<c:set var="alumno" value="${imparticion.alumno}"/>
						<div class="row">
							<a href="<c:url value='/cursos/${curso.codigo}/alumnos/${alumno.codigo}'/>">${alumno.nombre} ${alumno.apellidos} ${alumno.email} - ${imparticion.fMatriculacion}</a>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div class="row">
						El curso no tiene alumnos matriculados.
					</div>
				</c:otherwise>
			</c:choose>
		</section>
	</section>