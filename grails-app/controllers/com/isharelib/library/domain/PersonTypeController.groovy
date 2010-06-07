package com.isharelib.library.domain

class PersonTypeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [personTypeInstanceList: PersonType.list(params), personTypeInstanceTotal: PersonType.count()]
    }

    def create = {
        def personTypeInstance = new PersonType()
        personTypeInstance.properties = params
        return [personTypeInstance: personTypeInstance]
    }

    def save = {
        def personTypeInstance = new PersonType(params)
        if (personTypeInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'personType.label', default: 'PersonType'), personTypeInstance.id])}"
            redirect(action: "show", id: personTypeInstance.id)
        }
        else {
            render(view: "create", model: [personTypeInstance: personTypeInstance])
        }
    }

    def show = {
        def personTypeInstance = PersonType.get(params.id)
        if (!personTypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'personType.label', default: 'PersonType'), params.id])}"
            redirect(action: "list")
        }
        else {
            [personTypeInstance: personTypeInstance]
        }
    }

    def edit = {
        def personTypeInstance = PersonType.get(params.id)
        if (!personTypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'personType.label', default: 'PersonType'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [personTypeInstance: personTypeInstance]
        }
    }

    def update = {
        def personTypeInstance = PersonType.get(params.id)
        if (personTypeInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (personTypeInstance.version > version) {
                    
                    personTypeInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'personType.label', default: 'PersonType')] as Object[], "Another user has updated this PersonType while you were editing")
                    render(view: "edit", model: [personTypeInstance: personTypeInstance])
                    return
                }
            }
            personTypeInstance.properties = params
            if (!personTypeInstance.hasErrors() && personTypeInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'personType.label', default: 'PersonType'), personTypeInstance.id])}"
                redirect(action: "show", id: personTypeInstance.id)
            }
            else {
                render(view: "edit", model: [personTypeInstance: personTypeInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'personType.label', default: 'PersonType'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def personTypeInstance = PersonType.get(params.id)
        if (personTypeInstance) {
            try {
                personTypeInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'personType.label', default: 'PersonType'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'personType.label', default: 'PersonType'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'personType.label', default: 'PersonType'), params.id])}"
            redirect(action: "list")
        }
    }
}
