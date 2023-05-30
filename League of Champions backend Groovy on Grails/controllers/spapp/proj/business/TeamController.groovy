package spapp.proj.business

class TeamController {

    def teamService //importing team service to use it

    static responseFormats = ['json','xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE", get: "GET"]


    def getAllTeams(){
        respond teamService.getAllTeams()
    }//getAllTeams

    def getById() {
        respond teamService.getById(params.id)
    }//getById


    def create() {
        respond teamService.create(request.JSON.teamName)
    }//create

    def update() {
        respond teamService.update(params.id, request)
    }//update

    def delete() {
        respond teamService.delete(params.id)
    }//delete

    ///*params.id*/
}//TeamController