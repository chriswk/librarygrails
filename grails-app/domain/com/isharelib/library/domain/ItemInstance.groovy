package com.isharelib.library.domain

class ItemInstance implements Comparable {
    Item item
    Collector owner
    
    static belongsTo = [ owner : Collector, item : Item ]
    static constraints = {
    }

    String toString() {
      "$item.title"
    }

    int compareTo(obj) {
      return item.title.compareTo(obj.title)
    }
}
