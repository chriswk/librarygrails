package com.isharelib.library.domain

class Person {
    String firstName
    String lastName
    Date birthDate
    PersonType personType
    static belongsTo = Item
    static hasMany = [ projects : Item ]
    static constraints = {
        firstName(blank: false)
        lastName(blank: false)
    }
    
    String toString() {
        "$firstName $lastName"
    }
}
