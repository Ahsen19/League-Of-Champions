package spapp.proj.security

import grails.gorm.DetachedCriteria
import groovy.transform.ToString
import org.apache.commons.lang.builder.HashCodeBuilder

@ToString(cache=true, includeNames=true, includePackage=false)
class PDSSGroupUser implements Serializable {

	private static final long serialVersionUID = 1

	User user
	PDSSGroup pdssGroup
	String createdByPDSSUserId
	Date createdOnDatetime
	
	String lastUpdatedByPDSSUserId
	Date lastUpdatedOnDatetime
	
	Date logicalDeletedOnDatetime
	

	PDSSGroupUser(User u, PDSSGroup rg) {
		this()
		user = u
		pdssGroup = rg
	}

	@Override
	boolean equals(other) {
		if (!(other instanceof PDSSGroupUser)) {
			return false
		}

		other.user?.id == user?.id && other.pdssGroup?.id == pdssGroup?.id
	}

	@Override
	int hashCode() {
		def builder = new HashCodeBuilder()
		if (user) builder.append(user.id)
		if (pdssGroup) builder.append(pdssGroup.id)
		builder.toHashCode()
	}

	static PDSSGroupUser get(long userId, long pDSSGroupId) {
		criteriaFor(userId, pDSSGroupId).get()
	}

	static boolean exists(long userId, long pDSSGroupId) {
		criteriaFor(userId, pDSSGroupId).count()
	}

	private static DetachedCriteria criteriaFor(long userId, long pDSSGroupId) {
		PDSSGroupUser.where {
			user == User.load(userId) &&
			pdssGroup == PDSSGroup.load(pDSSGroupId)
		}
	}

	static boolean remove(User u, PDSSGroup rg, boolean flush = false) {
		if (u == null || rg == null) return false

		int rowCount = PDSSGroupUser.where { user == u && pdssGroup == rg }.deleteAll()

		if (flush) { PDSSGroupUser.withSession { it.flush() } }

		rowCount
	}

	static void removeAll(User u, boolean flush = false) {
		if (u == null) return

		PDSSGroupUser.where { user == u }.deleteAll()

		if (flush) { PDSSGroupUser.withSession { it.flush() } }
	}

	static void removeAll(PDSSGroup rg, boolean flush = false) {
		if (rg == null) return

		PDSSGroupUser.where { pdssGroup == rg }.deleteAll()

		if (flush) { PDSSGroupUser.withSession { it.flush() } }
	}

	static constraints = {
		user validator: { User u, PDSSGroupUser ug ->
			if (ug.pdssGroup == null || ug.pdssGroup.id == null) return
			boolean existing = false
			PDSSGroupUser.withNewSession {
				existing = PDSSGroupUser.exists(u.id, ug.pdssGroup.id)
			}
			if (existing) {
				return 'userGroup.exists'
			}
		}
	}

	static mapping = {
		datasource 'sec'
		id composite: ['pdssGroup', 'user']
		version false
		table "PDSS_USER_ROLE_GROUP"
		pdssGroup column: "ROLE_GROUP_ID"
		user column: "USER_ID"
		logicalDeletedOnDatetime column: "LOGICAL_DELETE_DATETIME"
		createdByPDSSUserId column: "CREATED_BY_USER_ID"
		createdOnDatetime column: "CREATED_DATETIME"
		lastUpdatedByPDSSUserId column :"LAST_UPDATE_USER_ID"
		lastUpdatedOnDatetime column: "LAST_UPDATE_DATETIME"
	}
}
