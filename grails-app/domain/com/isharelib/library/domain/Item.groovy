package com.isharelib.library.domain

class Item {
    String title
    Date releaseYear
    
    static hasMany = [ people : Person ]
    static constraints = {
        title(blank: false)
    }
    
    String toString() {
        "$title ($releaseYear.getYear())"   
    }
}
