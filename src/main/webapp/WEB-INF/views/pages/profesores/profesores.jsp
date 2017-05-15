<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>  
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>         
	<a href="<c:url value='/profesores/addProfesor'/>">Crear Profesor</a>
	<table>
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Apellidos</th>	
				<th></th>		
			</tr>
		</thead>
		<tbody>
	<c:choose>
		<c:when test="${not empty listadoProfesores}"><!-- Cuando la lista tiene datos -->
			<c:forEach var="profesor" items="${listadoProfesores}">
				<sec:authorize var="" access="isAuthenticated()"/>
				<c:if test="${isLogged || profesor.activo==true}">
					<tr>
						<td>${profesor.nombre}</td>
						<td>${profesor.apellidos}</td> 
						<td>
							<a href="<c:url value='/profesores/${profesor.codigo}'/>">Editar</a>
							<a href="<c:url value='/profesores/deleteProfesor/${profesor.codigo}'/>">Borrar</a>
						</td>
					</tr>
				</c:if>
			</c:forEach>
		</c:when>
		<c:otherwise><!-- Cuando la lista NO tiene datos -->
			<tr>
				<td colspan="3">No se han encontrado profesor en la Base de Datos</td>
			</tr>
		</c:otherwise>
	</c:choose>
		</tbody>
	</table>