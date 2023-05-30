package spapp.proj.business

import grails.transaction.Transactional

@Transactional
class TeamService {

    //variables
    def springSecurityService


    //getAllTeams---returns all teams
    def getAllTeams(){
        return Team.findAllByTeamNameIsNotNull()
    }//getAllTeams

    //getById---finds the team with the given id
    def getById(givenId) {
        return Team.findById(givenId)
    }//getById


    //create---this will create a new Team
    def create(givenName) {

        def te = new Team(givenName.toString())

        try {
            if (!te.save()) {
                println "Made it to save but save returned false: " + te.errors
                return te.errors
            }//if
            else {
                te = te.save(flush: true)
            }//else
        }//try
        catch (Exception e) {
            println e
            return e
        }//catch
        return te
    }//create


    //update---this will update an existing Team
    def update(givenId, request) {
        def ke = Team.findById(givenId)
        def flags = []
        request.JSON.each {
            key, value ->
                if(!flags.contains(key)) //if any of the keys in the request are equal to any flags in the array we skip the update.
                {
                    ke[key] = value
                }//if
        }//request.JSON.each
        try{
            if (!ke.save()) {
                println ke.errors
                return ke.errors
            }//if
            else {
                ke.save(flush: true)
            }//else
        }//try
        catch (Exception e) {
            println e
            return e
        }//catch
        return ke
    }//update


    //delete---this will delete an existing Team
    def delete(givenId) {
            def ke = Team.findById(givenId)
            ke.teamName = null //deleted so it is null

            try {
                if (!ke.save()) {
                    println ke.errors
                    return ke.errors
                } else {
                    ke = ke.save(flush: true)
                }
            } catch (Exception e) {
                println e
                return e
            }//catch
            return ke
    }//delete

}//TeamService
