<html>
	<head>
		<title>Turmas por Professor</title>
	</head>
	<body>
		<g:link action="list">Voltar</g:link>
		<h3>Turmas</h3>
		<g:each in="${turmas}">
			<h4>Turma ${it.codigo}</h4>
			<p>Professor: ${it.professor.nome}</p>
			<p>Alunos</p>
			<ul>
				<g:each in="${it.alunos}" var="aluno">
					<li>${aluno.matricula} - ${aluno.nome}</li>
				</g:each>
			</ul>
		</g:each>
	</body>
</html>