<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>  
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>     
<section>
	<header><h2>Listado</h2></header>
	<a href="<c:url value='/clientes/addCliente'/>">Crear Cliente</a>
	<table>
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Email</th>	
				<th>Télefono</th>
				<th></th>		
			</tr>
		</thead>
		<tbody>
	<c:choose>
		<c:when test="${not empty listadoClientes}"><!-- Cuando la lista tiene datos -->
			<c:forEach var="cliente" items="${listadoClientes}">
				<sec:authorize var="" access="isAuthenticated()"/>
				<c:if test="${isLogged || cliente.activo==true}">
					<tr>
						<td>${cliente.nombre}</td>
						<td>${cliente.email}</td>
						<td>${cliente.telefono}</td>
						<td>
							<a href="<c:url value='/clientes/${cliente.codigo}'/>">Editar</a>
							<a href="<c:url value='/clientes/deleteCliente/${cliente.codigo}'/>">Borrar</a>
							<a href="<c:url value='/clientes/getInforme/${cliente.codigo}'/>">Ver Informe</a>
						</td>
					</tr>
				</c:if>
			</c:forEach>
		</c:when>
		<c:otherwise><!-- Cuando la lista NO tiene datos -->
			<tr>
				<td colspan="4">No se han encontrado clientes en la Base de Datos</td>
			</tr>
		</c:otherwise>
	</c:choose>
		</tbody>
	</table>
</section>

