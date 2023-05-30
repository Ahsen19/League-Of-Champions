package spapp.proj.security

import grails.gorm.DetachedCriteria
import groovy.transform.ToString
import org.apache.commons.lang.builder.HashCodeBuilder

@ToString(cache=true, includeNames=true, includePackage=false)
class PDSSGroupRole implements Serializable {

	private static final long serialVersionUID = 1

	PDSSGroup pdssGroup
	Role role
	String createdBy

	PDSSGroupRole(PDSSGroup g, Role r) {
		this()
		pdssGroup = g
		role = r
	}

	@Override
	boolean equals(other) {
		if (!(other instanceof PDSSGroupRole)) {
			return false
		}

		other.role?.id == role?.id && other.pdssGroup?.id == pdssGroup?.id
	}

	@Override
	int hashCode() {
		def builder = new HashCodeBuilder()
		if (pdssGroup) builder.append(pdssGroup.id)
		if (role) builder.append(role.id)
		builder.toHashCode()
	}

	static PDSSGroupRole get(long roleGroupId, long roleId) {
		criteriaFor(roleGroupId, roleId).get()
	}

	static boolean exists(long roleGroupId, long roleId) {
		criteriaFor(roleGroupId, roleId).count()
	}

	private static DetachedCriteria criteriaFor(long roleGroupId, long roleId) {
		PDSSGroupRole.where {
			pdssGroup == PDSSGroup.load(roleGroupId) &&
			role == Role.load(roleId)
		}
	}

	static PDSSGroupRole create(PDSSGroup roleGroup, Role role, boolean flush = false) {
		def instance = new PDSSGroupRole(roleGroup, role)
		instance.save(flush: flush, insert: true)
		instance
	}

	static boolean remove(PDSSGroup rg, Role r, boolean flush = false) {
		if (rg == null || r == null) return false

		int rowCount = PDSSGroupRole.where { pdssGroup == rg && role == r }.deleteAll()

		if (flush) { PDSSGroupRole.withSession { it.flush() } }

		rowCount
	}

	static void removeAll(Role r, boolean flush = false) {
		if (r == null) return

		PDSSGroupRole.where { role == r }.deleteAll()

		if (flush) { PDSSGroupRole.withSession { it.flush() } }
	}

	static void removeAll(PDSSGroup rg, boolean flush = false) {
		if (rg == null) return

		PDSSGroupRole.where { pdssGroup == rg }.deleteAll()

		if (flush) { PDSSGroupRole.withSession { it.flush() } }
	}

	static constraints = {
		role validator: { Role r, PDSSGroupRole rg ->
			if (rg.pdssGroup == null || rg.pdssGroup.id == null) return
			boolean existing = false
			PDSSGroupRole.withNewSession {
				existing = PDSSGroupRole.exists(rg.pdssGroup.id, r.id)
			}
			if (existing) {
				return 'roleGroup.exists'
			}
		}
	}

	static mapping = {
		datasource 'sec'
		id composite: ['pdssGroup', 'role']
		version false
		
		table "PDSS_ROLE_GROUP_ROLE"
		role column: "ROLE_ID"
		pdssGroup column: "ROLE_GROUP_ID"
		createdBy column: "CREATED_BY_USER_ID"
	}
}
