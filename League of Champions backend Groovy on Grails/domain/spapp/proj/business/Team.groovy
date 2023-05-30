package spapp.proj.business

class Team {
    int id
    String teamName

    Team(String givenName){
        this.teamName = givenName
    }//constructor

    static constraints = {
        id unique: true
        teamName nullable: true
    }//constraints

    static mapping = {
        table                "TEAM"
        id                     column: "TEAM_ID", generator: "increment"
        teamName               column: "TEAM_NAME"
        version false
    }//mapping

}//Team
