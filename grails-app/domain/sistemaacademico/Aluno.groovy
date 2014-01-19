package sistemaacademico

class Aluno {
	
	String nome
	String matricula
	String cpf
	
	static belongsTo = [turma: Turma]

    static constraints = {
		nome nullable: false, blank: false
		matricula nullable: false, blank: false, validator: { val, obj ->
			if (!(val ==~ /^\d{3}\.\d{3}/)) {
				return false
			}
		}
		cpf nullable: false, blank: false, validator: {val, obj ->
			if (!(val ==~ /^\d{3}\.\d{3}\.\d{3}\-\d{2}$/)) {
				return false
			}
		}
    }
	
	static mapping = {
		nome column: 'nm_aluno'
		matricula column: 'nr_matricula'
		cpf column: 'nr_cpf'
	}
}
