<%@ page import="sistemaacademico.Professor" %>



<div class="fieldcontain ${hasErrors(bean: professorInstance, field: 'nome', 'error')} required">
	<label for="nome">
		<g:message code="professor.nome.label" default="Nome" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nome" required="" value="${professorInstance?.nome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: professorInstance, field: 'matricula', 'error')} required">
	<label for="matricula">
		<g:message code="professor.matricula.label" default="Matricula" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="matricula" required="" value="${professorInstance?.matricula}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: professorInstance, field: 'cpf', 'error')} required">
	<label for="cpf">
		<g:message code="professor.cpf.label" default="Cpf" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="cpf" required="" value="${professorInstance?.cpf}"/>
</div>

