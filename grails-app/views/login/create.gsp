<%@ page import="sistemaacademico.Login" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'login.label', default: 'Login')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-login" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="create-login" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<div class="message" role="status" id="divMessage" style="display: none;'"></div>
			<g:hasErrors bean="${loginInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${loginInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="save" >
				<fieldset class="form">
					<div class="fieldcontain ${hasErrors(bean: loginInstance, field: 'login', 'error')} required">
						<label for="login">
							<g:message code="login.login.label" default="Login" />
							<span class="required-indicator">*</span>
						</label>
						<g:textField name="login" required="" value="${loginInstance?.login}"/><div id='errormsg'/>
						<script>
							$('#login').blur(function() {
								var dados = {'login' : $("#login").val()}
								$.ajax({
									url: "${createLink(controller: 'login', action: 'verificaLoginExistente')}",
									type: "POST",
									data: dados,
									success: function(result) {
										if (result != "") {
											$("#divMessage").html(result);
											$("#divMessage").fadeIn();
										}
									}
								});
							});

							$('#login').focus(function() {
								$('#divMessage').fadeOut();
								$("#login").val('');
							});
						</script>
					</div>
					
					<div class="fieldcontain ${hasErrors(bean: loginInstance, field: 'senha', 'error')} required">
						<label for="senha">
							<g:message code="login.senha.label" default="Senha" />
							<span class="required-indicator">*</span>
						</label>
						<g:field type="password" name="senha" required="" value="${loginInstance?.senha}"/>
					</div>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
