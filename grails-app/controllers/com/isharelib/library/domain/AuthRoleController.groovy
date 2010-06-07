package com.isharelib.library.domain

class AuthRoleController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [authRoleInstanceList: AuthRole.list(params), authRoleInstanceTotal: AuthRole.count()]
    }

    def create = {
        def authRoleInstance = new AuthRole()
        authRoleInstance.properties = params
        return [authRoleInstance: authRoleInstance]
    }

    def save = {
        def authRoleInstance = new AuthRole(params)
        if (authRoleInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'authRole.label', default: 'AuthRole'), authRoleInstance.id])}"
            redirect(action: "show", id: authRoleInstance.id)
        }
        else {
            render(view: "create", model: [authRoleInstance: authRoleInstance])
        }
    }

    def show = {
        def authRoleInstance = AuthRole.get(params.id)
        if (!authRoleInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'authRole.label', default: 'AuthRole'), params.id])}"
            redirect(action: "list")
        }
        else {
            [authRoleInstance: authRoleInstance]
        }
    }

    def edit = {
        def authRoleInstance = AuthRole.get(params.id)
        if (!authRoleInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'authRole.label', default: 'AuthRole'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [authRoleInstance: authRoleInstance]
        }
    }

    def update = {
        def authRoleInstance = AuthRole.get(params.id)
        if (authRoleInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (authRoleInstance.version > version) {
                    
                    authRoleInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'authRole.label', default: 'AuthRole')] as Object[], "Another user has updated this AuthRole while you were editing")
                    render(view: "edit", model: [authRoleInstance: authRoleInstance])
                    return
                }
            }
            authRoleInstance.properties = params
            if (!authRoleInstance.hasErrors() && authRoleInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'authRole.label', default: 'AuthRole'), authRoleInstance.id])}"
                redirect(action: "show", id: authRoleInstance.id)
            }
            else {
                render(view: "edit", model: [authRoleInstance: authRoleInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'authRole.label', default: 'AuthRole'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def authRoleInstance = AuthRole.get(params.id)
        if (authRoleInstance) {
            try {
                authRoleInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'authRole.label', default: 'AuthRole'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'authRole.label', default: 'AuthRole'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'authRole.label', default: 'AuthRole'), params.id])}"
            redirect(action: "list")
        }
    }
}
