<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
	<nav class="navbar navbar-inverse">
	  <div class="container-fluid">
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
	        <span class="sr-only">Pincha para visualizar</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <!-- 
	      <a class="navbar-brand" href="#">Ipartek S. Coop.</a>
	       -->
	    </div>
	    <!-- Collect the nav links, forms, and other content for toggling -->
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <ul class="nav navbar-nav">
	        <li>
				<a href="<c:url value='/alumnos'/>">
					<spring:message code="menu.alumnos" text="G.Alumnos" />
				</a>
	        </li>
	        <li>
				<a href="<c:url value='/profesores'/>">
					<spring:message code="menu.profesores" text="G.Profesores" />
				</a>
	        </li>
	        <li>
				<a href="<c:url value='/clientes'/>">
						<spring:message code="menu.clientes" text="G.Clientes" />
				</a>
	        </li>
	        <li role="separator" class="divider"></li>
	        <li>
				<a href="<c:url value='/cursos'/>">
					 G.Cursos
				</a>
	        </li>
	        <!-- 
	        <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
	          <ul class="dropdown-menu">
	            <li><a href="#">Action</a></li>
	            <li><a href="#">Another action</a></li>
	            <li><a href="#">Something else here</a></li>
	            <li role="separator" class="divider"></li>
	            <li><a href="#">Separated link</a></li>
	            <li role="separator" class="divider"></li>
	            <li><a href="#">One more separated link</a></li>
	          </ul>
	        </li>
	         -->
	      </ul>
	      <ul class="nav navbar-nav navbar-right">
	        <li>
	        	<div class="btn-group">
					<button class="btn btn-default btn-lg dropdown-toggle" data-toggle="dropdown" >
						Idiomas <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<li>
							<a class="" href="?locale=es">				
							<spring:message code="idioma.castellano" text="castellano"/>
							</a>
						</li>
						<li>
							<a class="" href="?locale=en">
								<spring:message code="idioma.ingles" text="ingles"/>
							</a>
						</li>
						<li>
							<a class="" href="?locale=eu">
								<spring:message code="idioma.euskera" text="euskera"/>
							</a>
						</li>
					</ul>
				</div>
			</li>
			<li>
			<sec:authorize access="isAnonymous()">
			    <form method="POST"  role="form" class="navbar-form navbar-right" action="<c:url value='/login'/>">
			         <div class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
				        <input name="userId" type="text" class="form-control" value="${SPRING_SECURITY_LAST_USERNAME}" />
				     </div> 
					<div class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
						<input name="password" class="form-control" type="password" />
					</div> 

			        <input type="hidden" name="${_csrf.parameterName}" 	value="${_csrf.token}" />
					<div class="form-group ">				
				   		 <button type="submit" class="btn btn-primary">Login</button>
				   	</div>
			    </form>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
			    <a class="btn " href="<c:url value="/logout" />">Logout</a>
			</sec:authorize>
			</li>
			  <!-- 
	        <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
	          <ul class="dropdown-menu">
	            <li><a href="#">Action</a></li>
	            <li><a href="#">Another action</a></li>
	            <li><a href="#">Something else here</a></li>
	            <li role="separator" class="divider"></li>
	            <li><a href="#">Separated link</a></li>
	          </ul>
	        </li>
	         -->
	      </ul>
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>
	<div class="container">
		<c:if test="${not empty mensaje}">
		
			<div class="${mensaje.type.styles}">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
	   			 <strong>${mensaje.msg}</strong> 
	  		</div>
  		</c:if>
	</div>