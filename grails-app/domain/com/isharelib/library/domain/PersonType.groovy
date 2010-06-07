package com.isharelib.library.domain

class PersonType {
    String personType
    static constraints = {
        personType(blank: false, unique: true)
    }
    String toString() {
        "$personType"
    }
}
