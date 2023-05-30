package spapp.proj.business

import grails.plugin.springsecurity.annotation.Secured

//@Secured(['ROLE'])
class KnowledgeElementController {

    def knowledgeElementService

    static responseFormats = ['json','xml']
    static allowedMethods = [create: "POST", update: "PUT", delete: "DELETE", get: "GET"]

    def getById() {
        respond knowledgeElementService.getById(params.id)
    }

    def getAllActive() {
        respond knowledgeElementService.getAllActive()
    }

    def create() {
        respond knowledgeElementService.create(request.JSON.name, request.JSON.description)
    }

    def update() {
        respond knowledgeElementService.update(params.id, request)
    }

    def delete() {
        respond knowledgeElementService.delete(params.id)
    }
}
