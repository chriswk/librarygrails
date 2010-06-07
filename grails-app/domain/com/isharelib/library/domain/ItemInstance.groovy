package com.isharelib.library.domain

class ItemInstance implements Comparable {
    Item item
    Collector owner
    Loan loan
    boolean isAvailable = loan == null
    static belongsTo = [ owner : Collector, item : Item ]
    static constraints = {
      item(nullable: false)
      owner(nullable: false)
      loan(nullable: true)
    }

    String toString() {
      "$item.title"
    }

    int compareTo(obj) {
      return item.title.compareTo(obj.title)
    }

    static transients = [ "isAvailable" ]
}
