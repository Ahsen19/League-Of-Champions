package spapp.proj.security

class User implements Serializable {

	private static final long serialVersionUID = 1

	transient springSecurityService
	
	Long id
	String username
	String password
	String email
	String lastName
	String legalNames
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	
	String createdByPDSSUserId
	Date createdOnDatetime
	
	String lastUpdatedByPDSSUserId
	Date lastUpdatedOnDatetime
	
	Date logicalDeletedOnDatetime

    User(String username, String password) {
		this()
		this.username = username
		this.password = password
	}

	@Override
	int hashCode() {
		username?.hashCode() ?: 0
	}

	@Override
	boolean equals(other) {
		is(other) || (other instanceof User && other.username == username)
	}

	@Override
	String toString() {
		username
	}

	Set<PDSSGroup> getAuthorities() {
		PDSSGroupUser.findAllByUser(this)*.pdssGroup
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true
		password blank: false
		logicalDeletedOnDatetime nullable: true
	}

	static mapping = {
		datasource 'sec'
		table "PDSS_USER"
		id column: "USER_ID", generator: "assigned"
		enabled column: 'ENABLED_IND', defaultValue: true
		accountExpired column: 'ACCNT_EXPIRED_IND', defaultValue: false
		accountLocked column: 'ACCNT_LOCKED_IND', defaultValue: false
		passwordExpired column: 'PASSWORD_EXPIRED_IND', defaultValue: false
		password column: "PASSWORD_TEXT", defaultValue: "-"
		username column: "USERNAME"
		lastName column: "LAST_NAME"
		legalNames column: "LEGAL_GIVEN_NAMES"
		email column: "EMAIL_ADDRESS"
		logicalDeletedOnDatetime column: "LOGICAL_DELETE_DATETIME"
		createdByPDSSUserId column: "CREATED_BY_USER_ID"
		createdOnDatetime column: "CREATED_DATETIME"
		lastUpdatedByPDSSUserId column: "LAST_UPDATE_USER_ID"
		lastUpdatedOnDatetime column: "LAST_UPDATE_DATETIME"
		version column: 'VERSION_ID'
	}
}
