<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
	<c:url var="sendUrl" value="/cursos/save"/>
	<c:url var="cancelUrl" value="/cursos"/>
	<c:set var="accion" value="crear"/>
	<c:if test="${!empty curso}">
		<c:set var="accion" value="editar"/>
	</c:if>
	<section class="row">
		<header class="col-xs-12 col-md-10 col-md-offset-2"><h2>${accion}</h2></header>
		<div class="container-fluid">
			<form:form action="${sendUrl}" enctype="multipart/form-data" method="post" cssClass="form-horizontal" id="cursoForm" modelAttribute="curso">
				<c:if test="${!empty curso}">
					<form:hidden path="codigo"/>
					
				</c:if>
				<div class="form-group">
						<form:label path="nombre" cssClass="control-label  col-xs-2"><spring:message code="form.nombre" text="Nombre"/></form:label>
						<div class="col-xs-5">
							<form:input type="text" path="nombre" cssClass="form-control" cssErrorClass="form-control text-danger"/>
						</div>
					<form:errors path="nombre" cssClass="text-danger col-xs-5"></form:errors>
				</div>
				<div class="form-group">
						<form:label path="identificador" cssClass="control-label  col-xs-2">Idenficador</form:label>
						<div class="col-xs-5">
							<form:input type="text" path="identificador" cssClass="form-control" cssErrorClass="form-control text-danger"/>
						</div>
					<form:errors path="identificador" cssClass="text-danger col-xs-5"></form:errors>
				</div>
				<div class="form-group">
					<form:label path="finicio" cssClass="control-label col-xs-2"><spring:message code="form.fInicio" text="F. Inicio"/></form:label>
					<div class="col-xs-5">
						<form:input type="text" path="finicio" cssClass="form-control" cssErrorClass="text-danger"/>
					</div>
					<form:errors path="finicio" cssClass="text-danger col-xs-5"></form:errors>
				</div>
				<div class="form-group">
						<form:label path="ffin" cssClass="control-label  col-xs-2"><spring:message code="form.fFin" text="F. Fin"/></form:label>
						<div class="col-xs-5">
							<form:input type="text" path="ffin" cssClass="form-control" cssErrorClass="text-danger"/>
						</div>
						<form:errors path="ffin" cssClass="text-danger col-xs-5"></form:errors>
				</div>
				<div class="form-group">
						<form:label path="nhoras" cssClass="control-label  col-xs-2"><spring:message code="form.nHoras" text="Núm. Horas"/></form:label>
						<div class="col-xs-5">
							<form:input path="nhoras" cssClass="form-control" cssErrorClass="text-danger"/>
						</div>
						<form:errors path="nhoras" cssClass="text-danger col-xs-5"></form:errors>
				</div>
				<div class="form-group">
						<form:label path="precio" cssClass="control-label  col-xs-2"><spring:message code="form.precio" text="Precio"/></form:label>
						<div class="col-xs-5">
							<form:input path="precio" cssClass="form-control" cssErrorClass="text-danger"/>
						</div>
						<form:errors path="precio" cssClass="text-danger col-xs-5"></form:errors>
				</div>

            	<div class="form-group">
            		<form:label path="temario" cssClass="control-label  col-xs-2">Temario:</form:label>
            		<div class="col-xs-5">
						<form:input path="temario" disabled="disabled" cssClass="form-control" cssErrorClass="text-danger"/>
					</div>
					<form:errors path="temario" cssClass="text-danger col-xs-5"></form:errors>
					 
					 <label class="btn btn-primary">
                		Examinar&hellip; <input type="file" data-max-size="1500000" id="fichero" name="fichero" style="display: none;">
            		</label>
            		 <!--
            		<input type="file" id="fichero" name="fichero">
            		-->
            	</div>			
				<div class="form-group">
					<form:label cssClass="control-label col-xs-2" path="profesor">Profesor:</form:label>
					<div class="col-xs-5">
						<form:select cssClass="form-control" path="profesor">
			               <form:option value="0" label="Elija un profesor"/>
							<form:options items="${listadoProfesores}" itemValue="codigo" itemLabel="fullName" />
			            </form:select> 
		            </div>
		            <form:errors cssClass="text-error"  path="profesor"/>   
				</div>
				<div class="form-group">
					<form:label cssClass="control-label col-xs-2" path="cliente">Cliente:</form:label>
					<div class="col-xs-5">
						<form:select  cssClass="form-control" path="cliente">
							<form:option value="0" label="Elija un cliente"/>
							<form:options  items="${listadoClientes}" itemValue="codigo" itemLabel="nombre" />
			            </form:select> 
		            </div>
		            <form:errors cssClass="text-error"  path="cliente"/>   
				</div>
				<div class="form-group" style="display: none;">
					<form:label cssClass="control-label col-xs-2" path="imparticiones">Alumnos:</form:label>
					<c:forEach var="imparticion" items="${curso.imparticiones}">
						<c:set var="fullName" value="${imparticion.alumno.nombre} ${imparticion.alumno.apellidos}"/>
						<form:checkbox checked="ckecked" value="${imparticion.codigo}" label="${fullName}" path="imparticiones" />
										
					</c:forEach>
					
					
				</div>

				<div class="form-group">
					<div class="col-md-offset-3">
						<button type="submit" class="btn btn-success">${accion}</button>
						<a class="btn btn-warning" href="${cancelUrl}">Cancelar</a>
					</div>
				</div>
				<form:hidden path="activo"/>
			</form:form>
		</div>
	</section>