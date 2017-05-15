<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<c:url var="sendUrl" value="/profesores/save"/>
<c:url var="cancelUrl" value="/profesores"/>
<c:set var="accion" value="crear"/>
<c:if test="${!empty profesor}">
	<c:set var="accion" value="editar"/>
	<spring:message var="accion"  code="form.editar" text="nombre" />
</c:if>  
	<header><h2>${accion}</h2></header>
	<form:form action="${sendUrl}" method="post" modelAttribute="profesor">
		
		<c:if test="${!empty profesor}">
			<form:hidden path="codigo"/>
		</c:if>
		<div>
			<form:label path="nombre">Nombre:</form:label>
			<form:input path="nombre" cssErrorClass="" cssClass=""/>
			<form:errors path="nombre" cssClass="" />
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
			<form:label path="nSS">Número de SS:</form:label>
			<form:input path="nSS"/>
			<form:errors path="nSS"/>
		</div>
		<div>
			<form:label path="email">Email:</form:label>
			<form:input path="email"/>
			<form:errors path="email"/>
		</div>
		<div>
			<form:label path="telefono">Telefono:</form:label>
			<form:input path="telefono" />
			<form:errors path="telefono" />
		</div>
		<div>
			<form:label path="fNacimiento">F. Nacimiento:</form:label>
			<form:input path="fNacimiento"/>
			<form:errors path="fNacimiento" />
		</div>
		<div>
			<form:label path="direccion">Dirección:</form:label>
			<form:input path="direccion"/>
			<form:errors path="direccion"/>
		</div>
		<div>
			<form:label path="codigoPostal">Código Postal:</form:label>
			<form:input path="codigoPostal"/>
			<form:errors path="codigoPostal"/>
		</div>
		<div>
			<form:label path="poblacion">Población:</form:label>
			<form:input path="poblacion"/>
			<form:errors path="poblacion"/>
		</div>
		<div class="form-group">
			<div class="col-md-offset-3">
				<button type="submit" class="btn btn-success">${accion}</button>
				<a class="btn btn-warning" href="${cancelUrl}">Cancelar</a>
			</div>
		</div>
	</form:form>




