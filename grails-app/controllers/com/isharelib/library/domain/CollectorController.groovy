package com.isharelib.library.domain

class CollectorController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def springSecurityService

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [collectorInstanceList: Collector.list(params), collectorInstanceTotal: Collector.count()]
    }

    def create = {
        def collectorInstance = new Collector()
        collectorInstance.properties = params
        return [collectorInstance: collectorInstance]
    }

    def save = {
        def collectorInstance = new Collector(params)
        collectorInstance.password = springSecurityService.encodePassword(params.password)
        if (collectorInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'collector.label', default: 'Collector'), collectorInstance.id])}"
            redirect(action: "show", id: collectorInstance.id)
        }
        else {
            render(view: "create", model: [collectorInstance: collectorInstance])
        }
    }

    def show = {
        def collectorInstance = Collector.get(params.id)
        if (!collectorInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'collector.label', default: 'Collector'), params.id])}"
            redirect(action: "list")
        }
        else {
            [collectorInstance: collectorInstance]
        }
    }

    def edit = {
        def collectorInstance = Collector.get(params.id)
        if (!collectorInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'collector.label', default: 'Collector'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [collectorInstance: collectorInstance]
        }
    }

    def update = {
        def collectorInstance = Collector.get(params.id)
        if (collectorInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (collectorInstance.version > version) {
                    
                    collectorInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'collector.label', default: 'Collector')] as Object[], "Another user has updated this Collector while you were editing")
                    render(view: "edit", model: [collectorInstance: collectorInstance])
                    return
                }
            }
            if(collectorInstance.password != params.password) {
              params.password = springSecurityService.encodePassword(params.password)
            }
            collectorInstance.properties = params
            if (!collectorInstance.hasErrors() && collectorInstance.save(flush: true)) {
                if(springSecurityService.loggedIn && springSecurityService.principal.username == collectorInstance.username) {
                  springSecurityService.reauthenticate collectorInstance.username
                }
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'collector.label', default: 'Collector'), collectorInstance.id])}"
                redirect(action: "show", id: collectorInstance.id)
            }
            else {
                render(view: "edit", model: [collectorInstance: collectorInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'collector.label', default: 'Collector'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def collectorInstance = Collector.get(params.id)
        if (collectorInstance) {
            try {
                collectorInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'collector.label', default: 'Collector'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'collector.label', default: 'Collector'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'collector.label', default: 'Collector'), params.id])}"
            redirect(action: "list")
        }
    }
}
