package spapp.proj.security

import grails.transaction.Transactional
import org.grails.web.json.JSONObject

@Transactional
class AppCurrentUserController {

    def springSecurityService
    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def initCurrentUser() {

        def currentUser = springSecurityService.getCurrentUser()
        def returnObj
        def user

        if (currentUser) {
            user = User.findByUsername(currentUser)
            returnObj = [
                    user: user,
//                    keyRing  : keyRing,
//                    lastLogin: lastLogin,
            ]
            respond(returnObj)
        } else {
//            response.sendError(401)
            user = User.findByUsername("")
            returnObj = [
                    user: user,
            ]
            respond(returnObj)  //this makes you impersonate whatever user you enter here (party id)
        }
    }
}
