package spapp.proj.security

import org.grails.web.json.JSONObject

class AppInfoController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [create: "POST", update: "PUT", delete: "DELETE", get: "GET"]

    def getCurrentBuild() {
        respond new JSONObject([build: "${grails.util.Metadata.current.getApplicationVersion()}"])
    }
}
