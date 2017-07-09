<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulário</title>
</head>
<body>
	<h3>Adicionar tarefas</h3>
	<spring:url value="adicionaTarefa" var="tarefaAdicionada" />
	<form:form action="${tarefaAdicionada}" modelAttribute="tarefa"
		method="post">
		<!--<form:hidden path="id"/> -->
		<!--<spring:bind path="descricao">-->
			<label>Descrição:</label>
			<br />
			<form:textarea path="descricao" id="descricao" rows="5" cols="100"></form:textarea>
			<br />
			<form:button>Adicionar</form:button>
		<!--</spring:bind> -->
	</form:form>
</body>
</html>