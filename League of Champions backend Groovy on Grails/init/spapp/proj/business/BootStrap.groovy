package spapp.proj.business

import grails.converters.JSON
import grails.plugin.springsecurity.SecurityFilterPosition
import grails.plugin.springsecurity.SpringSecurityUtils

class BootStrap {

    def springSecurityService
    def grailsApplication

    def init = { servletContext ->
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
//        JsonMarshaller.initialize()

        SpringSecurityUtils.clientRegisterFilter('requestHeaderAuthenticationFilter', SecurityFilterPosition.PRE_AUTH_FILTER)

        JSON.registerObjectMarshaller(KnowledgeElement) {
            return [
                    id                        : it.id,
                    name                      : it.name,
                    description               : it.description,
                    createdByPDSSUserId       : it.createdByPDSSUserId,
                    createdOnDatetime         : it.createdOnDatetime,
                    lastUpdatedByPDSSUserId   : it.lastUpdatedByPDSSUserId,
                    lastUpdatedOnDatetime     : it.lastUpdatedOnDatetime,
                    logicalDeletedByPDSSUserId: it.logicalDeletedByPDSSUserId,
                    logicalDeletedOnDatetime  : it.logicalDeletedOnDatetime,
            ]
        }

        JSON.registerObjectMarshaller(Team) {
            return [
                    id                    : it.id,
                    teamName              : it.teamName,

            ]
        }

        JSON.registerObjectMarshaller(Player) {
            return [
                    id                    : it.id,
                    playerName            : it.playerName,
                    age                   :it.age,
                    country               :it.country,
                    teamName            : it.teamName,

            ]
        }


    }



    def destroy = {
    }
}
