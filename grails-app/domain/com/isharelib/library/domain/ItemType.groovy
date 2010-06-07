package com.isharelib.library.domain

class ItemType {
    String itemType
    static constraints = {
        itemType(blank: false, unique: true)
    }
    
    String toString() {
       "$itemType"
    }
}
