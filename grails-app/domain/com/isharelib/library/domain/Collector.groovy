package com.isharelib.library.domain

class Collector {

	String username
	String password
	String email
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static hasMany = [ collection : ItemInstance, loans: Loan ]
	static constraints = {
		username blank: false, unique: true
		password blank: false
		email blank: false, unique: true, email: true
	}

	static mapping = {
		password column: '`password`'
	}

	Set<AuthRole> getAuthorities() {
		CollectorAuthRole.findAllByCollector(this).collect { it.authRole } as Set
	}
}
