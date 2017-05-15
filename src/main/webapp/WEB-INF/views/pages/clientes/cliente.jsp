<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<spring:message var="accion" code="form.crear" text="nombre" />
<c:url var="sendUrl" value="/clientes/save"/>
<c:url var="cancelUrl" value="/clientes"/>
<c:if test="${!empty cliente}">
	<spring:message var="accion"  code="form.editar" text="nombre" />
</c:if>   
	<form:form action="${sendUrl}" method="post" modelAttribute="cliente">
		
		<c:if test="${!empty cliente}">
			<form:hidden path="codigo"/>
		</c:if>
		<div class="form-group">
			<form:label path="nombre">Nombre:</form:label>
			<form:input path="nombre" cssErrorClass="" cssClass=""/>
			<form:errors path="nombre" cssClass="" />
		</div>
		<div class="form-group">
			<form:label path="identificador">Identificador:</form:label>
			<form:input path="identificador" cssErrorClass="" cssClass="" />
			<form:errors path="identificador" />	
		</div>
		<div class="form-group">
			<form:label path="email">Email:</form:label>
			<form:input path="email"/>
			<form:errors path="email"/>
		</div>
		<div class="form-group">
			<form:label path="telefono">Telefono:</form:label>
			<form:input path="telefono" />
			<form:errors path="telefono" />
		</div>
		<div class="form-group">
			<form:label path="direccion">Dirección:</form:label>
			<form:input path="direccion"/>
			<form:errors path="direccion"/>
		</div>
		<div class="form-group">
			<form:label path="codigoPostal">Código Postal:</form:label>
			<form:input path="codigoPostal"/>
			<form:errors path="codigoPostal"/>
		</div>
		<div class="form-group">
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




