package sistemaacademico

import org.springframework.dao.DataIntegrityViolationException

class TurmaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [turmaInstanceList: Turma.list(params), turmaInstanceTotal: Turma.count()]
    }

    def create() {
        [turmaInstance: new Turma(params)]
    }

    def save() {
        def turmaInstance = new Turma(params)
        if (!turmaInstance.save(flush: true)) {
            render(view: "create", model: [turmaInstance: turmaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'turma.label', default: 'Turma'), turmaInstance.id])
        redirect(action: "show", id: turmaInstance.id)
    }

    def show(Long id) {
        def turmaInstance = Turma.get(id)
        if (!turmaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'turma.label', default: 'Turma'), id])
            redirect(action: "list")
            return
        }

        [turmaInstance: turmaInstance]
    }

    def edit(Long id) {
        def turmaInstance = Turma.get(id)
        if (!turmaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'turma.label', default: 'Turma'), id])
            redirect(action: "list")
            return
        }

        [turmaInstance: turmaInstance]
    }

    def update(Long id, Long version) {
        def turmaInstance = Turma.get(id)
        if (!turmaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'turma.label', default: 'Turma'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (turmaInstance.version > version) {
                turmaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'turma.label', default: 'Turma')] as Object[],
                          "Another user has updated this Turma while you were editing")
                render(view: "edit", model: [turmaInstance: turmaInstance])
                return
            }
        }

        turmaInstance.properties = params

        if (!turmaInstance.save(flush: true)) {
            render(view: "edit", model: [turmaInstance: turmaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'turma.label', default: 'Turma'), turmaInstance.id])
        redirect(action: "show", id: turmaInstance.id)
    }

    def delete(Long id) {
        def turmaInstance = Turma.get(id)
        if (!turmaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'turma.label', default: 'Turma'), id])
            redirect(action: "list")
            return
        }

        try {
            turmaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'turma.label', default: 'Turma'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'turma.label', default: 'Turma'), id])
            redirect(action: "show", id: id)
        }
    }
	
	def turmasPorProfessor() {
		def turmas = Turma.list()
		[turmas: turmas]
	}
}
