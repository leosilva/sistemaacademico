<%@ page import="sistemaacademico.Turma" %>



<div class="fieldcontain ${hasErrors(bean: turmaInstance, field: 'codigo', 'error')} required">
	<label for="codigo">
		<g:message code="turma.codigo.label" default="Codigo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="codigo" type="number" value="${turmaInstance.codigo}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: turmaInstance, field: 'dataInicial', 'error')} required">
	<label for="dataInicial">
		<g:message code="turma.dataInicial.label" default="Data Inicial" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="dataInicial" value="${turmaInstance.dataInicial}" required=""/>
	<script type="text/javascript">
        $(document).ready(function() {
          $("#dataInicial").datepicker({dateFormat: 'dd/mm/yy'});
        })
    </script>
</div>

<div class="fieldcontain ${hasErrors(bean: turmaInstance, field: 'alunos', 'error')} ">
	<label for="alunos">
		<g:message code="turma.alunos.label" default="Alunos" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${turmaInstance?.alunos?}" var="a">
    <li><g:link controller="aluno" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="aluno" action="create" params="['turma.id': turmaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'aluno.label', default: 'Aluno')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: turmaInstance, field: 'professor', 'error')} required">
	<label for="professor">
		<g:message code="turma.professor.label" default="Professor" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="professor" name="professor.id" from="${sistemaacademico.Professor.list()}" optionKey="id" required="" value="${turmaInstance?.professor?.id}" class="many-to-one"/>
</div>

