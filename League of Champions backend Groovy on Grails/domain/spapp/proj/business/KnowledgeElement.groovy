package spapp.proj.business

class KnowledgeElement {

    Long   id
    String name
    String description
    Long   createdByPDSSUserId
    Date   createdOnDatetime
    Long   lastUpdatedByPDSSUserId
    Date   lastUpdatedOnDatetime
    Long   logicalDeletedByPDSSUserId
    Date   logicalDeletedOnDatetime

    KnowledgeElement(name, description, userId) {
        def date = new Date()
        this.name = name
        this.description = description
        this.createdByPDSSUserId = userId
        this.createdOnDatetime = date
        this.lastUpdatedByPDSSUserId = userId
        this.lastUpdatedOnDatetime = date
    }

    static constraints = {
        id unique: true
        createdByPDSSUserId nullable: false
        createdOnDatetime nullable: false
        lastUpdatedByPDSSUserId nullable: false
        lastUpdatedOnDatetime nullable: false
        logicalDeletedByPDSSUserId nullable: true
        logicalDeletedOnDatetime nullable: true
    }

    static mapping = {
        table "KNOWLEDGE_ELEMENT"
        id column: "KE_ID", generator: 'increment'
        name column: "KE_NAME"
        description column: "KE_DESC"
        createdByPDSSUserId column: "CREATED_BY_USER_ID"
        createdOnDatetime column: "CREATED_DATETIME"
        lastUpdatedByPDSSUserId column: "LAST_UPDATE_USER_ID"
        lastUpdatedOnDatetime column: "LAST_UPDATE_DATETIME"
        logicalDeletedByPDSSUserId column: "LOGICAL_DELETE_USER_ID"
        logicalDeletedOnDatetime column: "LOGICAL_DELETE_DATETIME"
        version false
    }
}
