package spapp.proj.business

import grails.transaction.Transactional

@Transactional
class PlayerService {
    //variables
    def springSecurityService


    //getAllPlayers---returns all players for a given team
    def getAllPlayers(givenTeam){
        return Player.findAllByTeamName(givenTeam)
    }//getAllTeams


    //create---this will create a new Player
    def create(String givenPlayer, int givenAge, String givenCountry, String givenTeam) {

        def player = new Player(givenPlayer, givenAge, givenCountry, givenTeam)

        try {
            if (!player.save()) {
                println "Made it to save but save returned false: " + player.errors
                return player.errors
            }//if
            else {
                player = player.save(flush: true)
            }//else
        }//try
        catch (Exception e) {
            println e
            return e
        }//catch
        return player
    }//create


    //update---this will update an existing Team
    def update(givenId, request) {
//        def ke = Team.findById(givenId)
//        def flags = []
//        request.JSON.each {
//            key, value ->
//                if(!flags.contains(key)) //if any of the keys in the request are equal to any flags in the array we skip the update.
//                {
//                    ke[key] = value
//                }//if
//        }//request.JSON.each
//        try{
//            if (!ke.save()) {
//                println ke.errors
//                return ke.errors
//            }//if
//            else {
//                ke.save(flush: true)
//            }//else
//        }//try
//        catch (Exception e) {
//            println e
//            return e
//        }//catch
//        return ke
    }//update


    //delete---this will delete an existing Team
    def delete(givenPlayer, givenTeam) {
        def player = Player.findByPlayerNameAndTeamName(givenPlayer, givenTeam)
//        player.playerName = null
//        player.age = null
//        player.country = null
        player.teamName = null //deleted so it is null

        try {
            if (!player.save()) {
                println player.errors
                return player.errors
            } else {
                player = player.save(flush: true)
            }
        } catch (Exception e) {
            println e
            return e
        }//catch
        return player
    }//delete
}//PlayerService class
