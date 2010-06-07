package com.isharelib.library.domain

class ItemInstanceController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [itemInstanceInstanceList: ItemInstance.list(params), itemInstanceInstanceTotal: ItemInstance.count()]
    }

    def create = {
        def itemInstanceInstance = new ItemInstance()
        itemInstanceInstance.properties = params
        return [itemInstanceInstance: itemInstanceInstance]
    }

    def save = {
        def itemInstanceInstance = new ItemInstance(params)
        if (itemInstanceInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'itemInstance.label', default: 'ItemInstance'), itemInstanceInstance.id])}"
            redirect(action: "show", id: itemInstanceInstance.id)
        }
        else {
            render(view: "create", model: [itemInstanceInstance: itemInstanceInstance])
        }
    }

    def show = {
        def itemInstanceInstance = ItemInstance.get(params.id)
        if (!itemInstanceInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'itemInstance.label', default: 'ItemInstance'), params.id])}"
            redirect(action: "list")
        }
        else {
            [itemInstanceInstance: itemInstanceInstance]
        }
    }

    def edit = {
        def itemInstanceInstance = ItemInstance.get(params.id)
        if (!itemInstanceInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'itemInstance.label', default: 'ItemInstance'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [itemInstanceInstance: itemInstanceInstance]
        }
    }

    def update = {
        def itemInstanceInstance = ItemInstance.get(params.id)
        if (itemInstanceInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (itemInstanceInstance.version > version) {
                    
                    itemInstanceInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'itemInstance.label', default: 'ItemInstance')] as Object[], "Another user has updated this ItemInstance while you were editing")
                    render(view: "edit", model: [itemInstanceInstance: itemInstanceInstance])
                    return
                }
            }
            itemInstanceInstance.properties = params
            if (!itemInstanceInstance.hasErrors() && itemInstanceInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'itemInstance.label', default: 'ItemInstance'), itemInstanceInstance.id])}"
                redirect(action: "show", id: itemInstanceInstance.id)
            }
            else {
                render(view: "edit", model: [itemInstanceInstance: itemInstanceInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'itemInstance.label', default: 'ItemInstance'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def itemInstanceInstance = ItemInstance.get(params.id)
        if (itemInstanceInstance) {
            try {
                itemInstanceInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'itemInstance.label', default: 'ItemInstance'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'itemInstance.label', default: 'ItemInstance'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'itemInstance.label', default: 'ItemInstance'), params.id])}"
            redirect(action: "list")
        }
    }
}
