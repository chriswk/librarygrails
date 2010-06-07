package com.isharelib.library.domain

import org.apache.commons.lang.builder.HashCodeBuilder

class CollectorAuthRole implements Serializable {

	Collector collector
	AuthRole authRole

	boolean equals(other) {
		if (!(other instanceof CollectorAuthRole)) {
			return false
		}

		other.collector?.id == collector?.id &&
			other.authRole?.id == authRole?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (collector) builder.append(collector.id)
		if (authRole) builder.append(authRole.id)
		builder.toHashCode()
	}

	static CollectorAuthRole get(long collectorId, long authRoleId) {
		find 'from CollectorAuthRole where collector.id=:collectorId and authRole.id=:authRoleId',
			[collectorId: collectorId, authRoleId: authRoleId]
	}

	static CollectorAuthRole create(Collector collector, AuthRole authRole, boolean flush = false) {
		new CollectorAuthRole(collector: collector, authRole: authRole).save(flush: flush, insert: true)
	}

	static boolean remove(Collector collector, AuthRole authRole, boolean flush = false) {
		CollectorAuthRole instance = CollectorAuthRole.findByCollectorAndAuthRole(collector, authRole)
		instance ? instance.delete(flush: flush) : false
	}

	static void removeAll(Collector collector) {
		executeUpdate 'DELETE FROM CollectorAuthRole WHERE collector=:collector', [collector: collector]
	}

	static void removeAll(AuthRole authRole) {
		executeUpdate 'DELETE FROM CollectorAuthRole WHERE authRole=:authRole', [authRole: authRole]
	}

	static mapping = {
		id composite: ['authRole', 'collector']
		version false
	}
}
