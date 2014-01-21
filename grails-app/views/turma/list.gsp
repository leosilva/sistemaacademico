
<%@ page import="sistemaacademico.Turma" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'turma.label', default: 'Turma')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-turma" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				<li><g:link action="turmasPorProfessor">Turmas por Professor</g:link></li>
			</ul>
		</div>
		<div id="list-turma" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="codigo" title="${message(code: 'turma.codigo.label', default: 'Codigo')}" />
					
						<th><g:message code="turma.professor.label" default="Professor" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${turmaInstanceList}" status="i" var="turmaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${turmaInstance.id}">${fieldValue(bean: turmaInstance, field: "codigo")}</g:link></td>
					
						<td>${fieldValue(bean: turmaInstance, field: "professor")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${turmaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
