<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<section>
	<header><h2>Inforación de Debug</h2></header>
	<div>
		<p>URL = ${url}</p>
		<p>Exception = ${exception.message}</p>
		<c:forEach items="${exception.stackTrace}" var="st">
			${st}
		</c:forEach>
	</div>
</section>