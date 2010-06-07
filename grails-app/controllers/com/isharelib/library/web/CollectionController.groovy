package com.isharelib.library.web

import com.isharelib.library.domain.Collector
import com.isharelib.library.domain.Item
import com.isharelib.library.domain.ItemInstance
import grails.plugins.springsecurity.Secured

class CollectionController {
    def springSecurityService

    def collectorService

    @Secured(['isAuthenticated()'])
    def index = {
          def principal = springSecurityService.principal
          def collector = Collector.findByUsername(principal.username)
          [ collection : collector.collection ]
    }

    @Secured(['isAuthenticated()'])
    def create = {
        def itemInstance = new Item()
        itemInstance.properties = params
        [ itemInstance : itemInstance]
    }

    @Secured(['isAuthenticated()'])
    def save = {
      def principal = springSecurityService.principal
      def collector = Collector.findByUsername(principal.username)
      def itemInstance = new Item(params)
      if (itemInstance.save(flush: true)) {
            collectorService.addItemToCollection(collector, itemInstance)
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'item.label', default: 'Item'), itemInstance.id])}"
            redirect(action: "index")
        }
        else {
            
            render(view: "create", model: [itemInstance: itemInstance])
        }

    }
}
