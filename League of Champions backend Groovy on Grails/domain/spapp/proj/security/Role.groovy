package spapp.proj.security

class Role implements Serializable {

	private static final long serialVersionUID = 1

	String authority
	Date logicalDeletedOnDatetime
	

	Role(String authority) {
		this()
		this.authority = authority
	}

	@Override
	int hashCode() {
		authority?.hashCode() ?: 0
	}

	@Override
	boolean equals(other) {
		is(other) || (other instanceof Role && other.authority == authority)
	}

	@Override
	String toString() {
		authority.tokenize("_").join(" ")
	}

	static constraints = {
		authority blank: false, unique: true
	}

	static mapping = {
		datasource 'sec'
		table "PDSS_ROLE"
		id column: "ROLE_ID", generator: 'increment'
		version column: 'VERSION_ID'
		authority column: "AUTHORITY_TEXT"
		logicalDeletedOnDatetime column: "LOGICAL_DELETE_DATETIME"

		
	}
}