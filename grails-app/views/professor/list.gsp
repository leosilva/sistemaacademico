
<%@ page import="sistemaacademico.Professor" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'professor.label', default: 'Professor')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<script>
			$(document).ready(function() {
				$("#getJSON").click(function(e) {
				    $.getJSON("${createLink(controller: 'professor', action: 'getAsJSON')}", function(data) {
					    $('#list-professor').hide();
					    var text = ""
						$.each(data, function(i, item){
							text += "<p>Nome: " + item.nome + "</p>"
							text += "<p>Matricula: " + item.matricula + "</p>"
							text += "<p>CPF: " + item.cpf + "</p>"
						});
						$('#list-professor-json').html(text);
						$('#list-professor-json').show();
					});
				});
			});
		</script>
	</head>
	<body>
		<a href="#list-professor" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				<li><a href="#" id="getJSON"><g:message code="default.list.with.json" args="[entityName]" /></a></li>
			</ul>
		</div>
		<div id="list-professor" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
						<g:sortableColumn property="nome" title="${message(code: 'professor.nome.label', default: 'Nome')}" />
						<g:sortableColumn property="matricula" title="${message(code: 'professor.matricula.label', default: 'Matricula')}" />
						<g:sortableColumn property="cpf" title="${message(code: 'professor.cpf.label', default: 'Cpf')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${professorInstanceList}" status="i" var="professorInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td><g:link action="show" id="${professorInstance.id}">${fieldValue(bean: professorInstance, field: "nome")}</g:link></td>
						<td>${fieldValue(bean: professorInstance, field: "matricula")}</td>
						<td>${fieldValue(bean: professorInstance, field: "cpf")}</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${professorInstanceTotal}" />
			</div>
		</div>
		<div id='list-professor-json' />
	</body>
</html>
