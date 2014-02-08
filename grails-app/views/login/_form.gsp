<%@ page import="sistemaacademico.Login" %>



<div class="fieldcontain ${hasErrors(bean: loginInstance, field: 'login', 'error')} required">
	<label for="login">
		<g:message code="login.login.label" default="Login" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="login" required="" value="${loginInstance?.login}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: loginInstance, field: 'senha', 'error')} required">
	<label for="senha">
		<g:message code="login.senha.label" default="Senha" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="password" name="senha" required="" value="${loginInstance?.senha}"/>
</div>