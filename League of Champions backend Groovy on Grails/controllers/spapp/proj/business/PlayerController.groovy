package spapp.proj.business

class PlayerController {
    def playerService //importing team service to use it

    static responseFormats = ['json','xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE", get: "GET"]


    def getAllPlayers(){
        respond playerService.getAllPlayers(params.teamName)
    }//getAllTeams


    def create() {
        respond playerService.create(request.JSON.playerName, request.JSON.age, request.JSON.country, request.JSON.teamName)
    }//create


    def update() {
       // respond playerService.update(params.id, request)
    }//update


    def delete() {
        respond playerService.delete(params.playerName, params.teamName)
    }//delete
}//PlayerController class
