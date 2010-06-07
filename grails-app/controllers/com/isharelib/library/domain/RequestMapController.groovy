package com.isharelib.library.domain

class RequestMapController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [requestMapInstanceList: RequestMap.list(params), requestMapInstanceTotal: RequestMap.count()]
    }

    def create = {
        def requestMapInstance = new RequestMap()
        requestMapInstance.properties = params
        return [requestMapInstance: requestMapInstance]
    }

    def save = {
        def requestMapInstance = new RequestMap(params)
        if (requestMapInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'requestMap.label', default: 'RequestMap'), requestMapInstance.id])}"
            redirect(action: "show", id: requestMapInstance.id)
        }
        else {
            render(view: "create", model: [requestMapInstance: requestMapInstance])
        }
    }

    def show = {
        def requestMapInstance = RequestMap.get(params.id)
        if (!requestMapInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'requestMap.label', default: 'RequestMap'), params.id])}"
            redirect(action: "list")
        }
        else {
            [requestMapInstance: requestMapInstance]
        }
    }

    def edit = {
        def requestMapInstance = RequestMap.get(params.id)
        if (!requestMapInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'requestMap.label', default: 'RequestMap'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [requestMapInstance: requestMapInstance]
        }
    }

    def update = {
        def requestMapInstance = RequestMap.get(params.id)
        if (requestMapInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (requestMapInstance.version > version) {
                    
                    requestMapInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'requestMap.label', default: 'RequestMap')] as Object[], "Another user has updated this RequestMap while you were editing")
                    render(view: "edit", model: [requestMapInstance: requestMapInstance])
                    return
                }
            }
            requestMapInstance.properties = params
            if (!requestMapInstance.hasErrors() && requestMapInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'requestMap.label', default: 'RequestMap'), requestMapInstance.id])}"
                redirect(action: "show", id: requestMapInstance.id)
            }
            else {
                render(view: "edit", model: [requestMapInstance: requestMapInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'requestMap.label', default: 'RequestMap'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def requestMapInstance = RequestMap.get(params.id)
        if (requestMapInstance) {
            try {
                requestMapInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'requestMap.label', default: 'RequestMap'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'requestMap.label', default: 'RequestMap'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'requestMap.label', default: 'RequestMap'), params.id])}"
            redirect(action: "list")
        }
    }
}
