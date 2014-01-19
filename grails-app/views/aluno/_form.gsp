<%@ page import="sistemaacademico.Aluno" %>



<div class="fieldcontain ${hasErrors(bean: alunoInstance, field: 'nome', 'error')} required">
	<label for="nome">
		<g:message code="aluno.nome.label" default="Nome" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nome" required="" value="${alunoInstance?.nome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: alunoInstance, field: 'matricula', 'error')} required">
	<label for="matricula">
		<g:message code="aluno.matricula.label" default="Matricula" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="matricula" required="" value="${alunoInstance?.matricula}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: alunoInstance, field: 'cpf', 'error')} required">
	<label for="cpf">
		<g:message code="aluno.cpf.label" default="Cpf" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="cpf" required="" value="${alunoInstance?.cpf}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: alunoInstance, field: 'turma', 'error')} required">
	<label for="turma">
		<g:message code="aluno.turma.label" default="Turma" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="turma" name="turma.id" from="${sistemaacademico.Turma.list()}" optionKey="id" required="" value="${alunoInstance?.turma?.id}" class="many-to-one"/>
</div>

