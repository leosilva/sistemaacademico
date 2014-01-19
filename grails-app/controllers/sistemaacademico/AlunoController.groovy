package sistemaacademico

import org.springframework.dao.DataIntegrityViolationException

class AlunoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [alunoInstanceList: Aluno.list(params), alunoInstanceTotal: Aluno.count()]
    }

    def create() {
        [alunoInstance: new Aluno(params)]
    }

    def save() {
        def alunoInstance = new Aluno(params)
        if (!alunoInstance.save(flush: true)) {
            render(view: "create", model: [alunoInstance: alunoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'aluno.label', default: 'Aluno'), alunoInstance.id])
        redirect(action: "show", id: alunoInstance.id)
    }

    def show(Long id) {
        def alunoInstance = Aluno.get(id)
        if (!alunoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'aluno.label', default: 'Aluno'), id])
            redirect(action: "list")
            return
        }

        [alunoInstance: alunoInstance]
    }

    def edit(Long id) {
        def alunoInstance = Aluno.get(id)
        if (!alunoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'aluno.label', default: 'Aluno'), id])
            redirect(action: "list")
            return
        }

        [alunoInstance: alunoInstance]
    }

    def update(Long id, Long version) {
        def alunoInstance = Aluno.get(id)
        if (!alunoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'aluno.label', default: 'Aluno'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (alunoInstance.version > version) {
                alunoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'aluno.label', default: 'Aluno')] as Object[],
                          "Another user has updated this Aluno while you were editing")
                render(view: "edit", model: [alunoInstance: alunoInstance])
                return
            }
        }

        alunoInstance.properties = params

        if (!alunoInstance.save(flush: true)) {
            render(view: "edit", model: [alunoInstance: alunoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'aluno.label', default: 'Aluno'), alunoInstance.id])
        redirect(action: "show", id: alunoInstance.id)
    }

    def delete(Long id) {
        def alunoInstance = Aluno.get(id)
        if (!alunoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'aluno.label', default: 'Aluno'), id])
            redirect(action: "list")
            return
        }

        try {
            alunoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'aluno.label', default: 'Aluno'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'aluno.label', default: 'Aluno'), id])
            redirect(action: "show", id: id)
        }
    }
}
