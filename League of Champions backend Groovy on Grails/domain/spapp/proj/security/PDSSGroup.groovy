package spapp.proj.security

class PDSSGroup implements Serializable {

    private static final long serialVersionUID = 1

    Long id
    String name
    String createdByPDSSUserId


    PDSSGroup(String name) {
        this()
        this.name = name
    }

    @Override
    int hashCode() {
        name?.hashCode() ?: 0
    }

    @Override
    boolean equals(other) {
        is(other) || (other instanceof PDSSGroup && other.name == name)
    }

    @Override
    String toString() {
        name.tokenize("_").join(" ")
    }

    Set<Role> getAuthorities() {
        PDSSGroupRole.findAllByPdssGroup(this)*.role
    }

    static constraints = {
        name blank: false, unique: true
    }

    static mapping = {
        datasource 'sec'
        cache true
        table "PDSS_ROLE_GROUP"
        name column: "ROLE_GROUP_NAME"
        id column: "ROLE_GROUP_ID", generator: "increment"
        createdByPDSSUserId column: "CREATED_BY_USER_ID"
        version column: 'VERSION_ID'
    }
}

