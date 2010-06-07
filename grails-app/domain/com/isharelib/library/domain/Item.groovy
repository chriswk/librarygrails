package com.isharelib.library.domain

class Item {
    String title
    Date releaseYear
    ItemType itemType
    static hasMany = [ people : Person ]
    static constraints = {
        title(blank: false)
    }
    
    String toString() {
        Date release = new Date(releaseYear.getTime())
        "$title (${release.getYear()+1900})"
    }


}
