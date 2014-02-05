package sistemaacademico

class ProfessorService {

	def performSave(professorInstance) {
		if (!professorInstance.save(flush: true)) {
			return [false, professorInstance]
		}
		return [true, professorInstance]
	}

}