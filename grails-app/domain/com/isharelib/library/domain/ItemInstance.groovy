package com.isharelib.library.domain

class ItemInstance {
    Item item
    Collector owner
    
    static belongsTo = [ owner : Collector, item : Item ]
    static constraints = {
    }
}
