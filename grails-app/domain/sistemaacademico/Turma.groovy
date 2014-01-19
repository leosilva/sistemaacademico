package sistemaacademico

class Turma {

	Integer codigo
	Integer qtdAlunos
	Professor professor
	
	static hasMany = [alunos: Aluno]
	
    static constraints = {
		codigo nullable: false, blank: false
		professor nullable: true, blank: true
    }
	
	static transients = ['qtdAlunos']
	
	static mapping = {
		codigo column: 'cod_turma'
	}
}
