<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
	<c:url var="sendUrl" value="/alumnos/save"/>
	<c:url var="cancelUrl" value="/alumnos"/>
	<c:set var="accion" value="crear"/>
	<c:if test="${!empty alumno}">
		<c:set var="accion" value="editar"/>
	</c:if>
<section>
	<header><h2>${accion}</h2></header>

	<form:form action="${sendUrl}" method="post" cssClass="form-horizontal" modelAttribute="alumno">
		<c:if test="${!empty alumno}">
			<form:hidden path="codigo"/>
		</c:if>
		<div class="form-group">
			<form:label cssClass="control-label hidden-xs col-sm-2" path="nombre">
				<spring:message code="alumno.nombre" />:
			</form:label>
			<div class="col-sm-7">
				<form:input placeholder="Introduzca su nombre" path="nombre" cssErrorClass="text-danger" cssClass="form-control"/>
			</div>
			<div class="col-sm-3">
				<form:errors path="nombre" cssClass="text-danger" />
			</div>
		</div>
		<div>
			<form:label path="apellidos">Apellidos:</form:label>
			<form:input path="apellidos" cssErrorClass="" cssClass="" />
			<form:errors path="apellidos" />
		</div>
		<div>
			<form:label path="dni">Dni:</form:label>
			<form:input path="dni" cssErrorClass="" cssClass="" />
			<form:errors path="dni" />	
		</div>
		<div>
			<form:label path="email">Email:</form:label>
			<form:input path="email"/>
			<form:errors path="email"/>
		</div>
		<div>
			<form:label path="telefono">Telefono:</form:label>
			<form:input path="telefono" pattern="[0-9]{9}"/>
			<form:errors path="telefono" />
		</div>
		<div>
			<form:label path="fNacimiento">F. Nacimiento:</form:label>
			<form:input path="fNacimiento" />
			<form:errors path="fNacimiento" />
		</div>
		<div>
			<form:label path="direccion">Dirección:</form:label>
			<form:input path="direccion"/>
			<form:errors path="direccion"/>
		</div>
		<div>
			<form:label path="codigoPostal">Código Postal:</form:label>
			<form:input path="codigoPostal" />
			<form:errors path="codigoPostal"/>
		</div>
		<div>
			<form:label path="poblacion">Población:</form:label>
			<form:input path="poblacion"/>
			<form:errors path="poblacion"/>
		</div>
		<div>
			<form:label path="nHermanos">Número de Hermanos</form:label>
			<form:input path="nHermanos"/>
			<form:errors path="nHermanos"/>
		</div>
		<div class="form-group">
			<div class="col-md-offset-3">
				<button type="submit" class="btn btn-success">${accion}</button>
				<a class="btn btn-warning" href="${cancelUrl}">Cancelar</a>
			</div>
		</div>
	</form:form>
</section>





