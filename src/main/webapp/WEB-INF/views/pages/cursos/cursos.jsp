<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>    
	<section class="row">
		<header class="col-xs-12"><h2>Listado Cursos</h2></header>
		<div class="col-xs-12">
		<a class="btn btn-primary" href="<c:url value="/cursos/addCurso"/>">Crear Curso</a> 
		</div>
		<div class="col-xs-12">
			<div class="row">
				<div class="col-xs-5">Nombre</div>
				<div class="col-xs-2">F.Inicio</div>
				<div class="col-xs-2">F.Fin</div>
				<div class="col-xs-3"></div>
			</div>
			<c:choose>
				<c:when test="${not empty listadoCursos}">
					<c:forEach var="curso" items="${listadoCursos}">
						<div class="row">
							<div class="col-xs-5">
								<a href="<c:url value='/cursos/${curso.codigo}'/>">${curso.nombre}</a>
							</div>
							<div class="col-xs-2">
								${curso.finicio}
				            </div>
							<div class="col-xs-2">
								${curso.ffin}
				            </div>
				            <div class="col-xs-3">
				            	<a class="btn btn-warning" href="<c:url value="/cursos/editCurso/${curso.codigo}"/>">Editar Curso</a>
				            	<a class="btn btn-danger" href="<c:url value="/cursos/deleteCurso/${curso.codigo}"/>">Borrar Curso</a>
				            </div>			
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div class="row">
						<p class="col-xs-12">No existen resultados</p>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</section>
