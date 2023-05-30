package spapp.proj.business

class Player {

    //variables
    int id
    String playerName
    int age
    String country
    String teamName

    //constructor
    Player(String givenPlayer, int givenAge, String givenCountry, String givenTeam){
        playerName = givenPlayer
        age = givenAge
        country = givenCountry
        teamName = givenTeam
    }//constructor

    static constraints = {
        id unique : true
        teamName nullable: true
    }//constraints

    static mapping = {
        table                  "Player_Team"
        id                     column: "PLAYER_ID", generator: "increment"
        playerName             column: "PLAYER_NAME"
        age                    column: "AGE"
        country                column: "COUNTRY"
        teamName               column: "TEAM_NAME"
        version false
    }//mapping
}//Player class
