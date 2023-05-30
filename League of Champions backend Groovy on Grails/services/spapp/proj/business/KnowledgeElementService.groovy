package spapp.proj.business

import grails.transaction.Transactional
import spapp.proj.security.User

@Transactional
class KnowledgeElementService {

    def springSecurityService

    def getById(id) {
        return KnowledgeElement.findById(id)
    }

    def getAllActive() {
        return KnowledgeElement.findAllByLogicalDeletedOnDatetimeIsNull()
    }

    def create(name, description) {
        def user = User.findByUsername(springSecurityService.getCurrentUser())
        def ke = new KnowledgeElement(name, description, user.id)

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
        }
        return ke
    }

    def update(id, request) {
        def user = User.findByUsername(springSecurityService.getCurrentUser())
        def ke = KnowledgeElement.findById(id)
        def flags = []
        request.JSON.each {
            key, value ->
                if(!flags.contains(key)) //if any of the keys in the request are equal to any flags in the array we skip the update.
                {
                    ke[key] = value
                }
        }
        ke.lastUpdatedByPDSSUserId = user.id
        ke.lastUpdatedOnDatetime = new Date()

        try {
            if (!ke.save()) {
                println ke.errors
                return ke.errors
            } else {
                ke.save(flush: true)
            }
        } catch (Exception e) {
            println e
            return e
        }
        return ke
    }

    def delete(id) {
        def user = User.findByUsername(springSecurityService.getCurrentUser())
        def ke = KnowledgeElement.findById(id)
        ke.logicalDeletedByPDSSUserId = user.id
        ke.logicalDeletedOnDatetime = new Date()
        ke.lastUpdatedByPDSSUserId = user.id
        ke.lastUpdatedOnDatetime = new Date()
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
        }
        return ke
    }
}
