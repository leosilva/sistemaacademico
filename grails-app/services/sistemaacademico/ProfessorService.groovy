package sistemaacademico

import grails.plugin.cache.Cacheable;

class ProfessorService {
	
	def performSave(professorInstance) {
		if (!professorInstance.save(flush: true)) {
			return [false, professorInstance]
		}
		return [true, professorInstance]
	}
	
	@Cacheable('listProfessores')
	def listProfessores() {
		Professor.list()
	}

}