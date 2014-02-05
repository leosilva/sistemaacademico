package sistemaacademico


import grails.converters.JSON

import org.springframework.dao.DataIntegrityViolationException

class ProfessorController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [professorInstanceList: Professor.list(params), professorInstanceTotal: Professor.count()]
    }

    def create() {
        [professorInstance: new Professor(params)]
    }

    def save() {
        def professorInstance = new Professor(params)
        performSave(professorInstance)
    }

    def show(Long id) {
        def professorInstance = Professor.get(id)
        if (!professorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'professor.label', default: 'Professor'), id])
            redirect(action: "list")
            return
        }

        [professorInstance: professorInstance]
    }

    def edit(Long id) {
        def professorInstance = Professor.get(id)
        if (!professorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'professor.label', default: 'Professor'), id])
            redirect(action: "list")
            return
        }

        [professorInstance: professorInstance]
    }

    def update(Long id, Long version) {
        def professorInstance = Professor.get(id)
        if (!professorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'professor.label', default: 'Professor'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (professorInstance.version > version) {
                professorInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'professor.label', default: 'Professor')] as Object[],
                          "Another user has updated this Professor while you were editing")
                render(view: "edit", model: [professorInstance: professorInstance])
                return
            }
        }

        professorInstance.properties = params

        if (!professorInstance.save(flush: true)) {
            render(view: "edit", model: [professorInstance: professorInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'professor.label', default: 'Professor'), professorInstance.id])
        redirect(action: "show", id: professorInstance.id)
    }

    def delete(Long id) {
        def professorInstance = Professor.get(id)
        if (!professorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'professor.label', default: 'Professor'), id])
            redirect(action: "list")
            return
        }

        try {
            professorInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'professor.label', default: 'Professor'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'professor.label', default: 'Professor'), id])
            redirect(action: "show", id: id)
        }
    }
	
	def getAsJSON() {
		def professores = Professor.list()
		render professores as JSON
	}
	
	def saveWithJSON() {
		def professor = (JSON.parse(params.json)) as Professor
		performSave(professor)
	}
	
	private def performSave(professorInstance) {
		if (!professorInstance.save(flush: true)) {
			render(view: "create", model: [professorInstance: professorInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [message(code: 'professor.label', default: 'Professor'), professorInstance.id])
		redirect(action: "show", id: professorInstance.id)
	}
	
}
