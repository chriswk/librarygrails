package com.isharelib.library.service

import com.isharelib.library.domain.Collector
import com.isharelib.library.domain.Item
import com.isharelib.library.domain.ItemInstance

class CollectorException extends RuntimeException {
  String message
  Collector collector
}
class CollectorService {

    static transactional = true

    def addItemToCollection(Collector collector, Item item) {
         new ItemInstance(owner: collector, item: item).save(flush: true)
    }
}
