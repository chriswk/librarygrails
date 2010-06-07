package com.isharelib.library.domain

class Loan {
    ItemInstance loanItem
    Collector loaner
    static belongsTo = [ loaner : Collector, loanItem : ItemInstance ]
    static constraints = {
    }

    String toString() {
      "${loanItem.item.title}"
    }
}
