package spapp.proj.business

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        group "/knowledgeelement", {
            "/" (controller: "KnowledgeElement", action: "getAllActive", method: "GET")
            "/$id" (controller: "KnowledgeElement", action: "getById", method: "GET")
            "/$id" (controller: "KnowledgeElement", action: "update", method: "PUT")
            "/" (controller: "KnowledgeElement", action: "create", method: "POST")
            "/$id" (controller: "KnowledgeElement", action: "delete", method: "DELETE")
        }

        group "/team", {
            "/" (controller: "Team", action: "getAllTeams", method: "GET")
            "/$id" (controller: "Team", action: "getById", method: "GET")
            "/$id" (controller: "Team", action: "update", method: "PUT")
            "/" (controller: "Team", action: "create", method: "POST")
            "/$id" (controller: "Team", action: "delete", method: "DELETE")
        }

        group "/player", {
            "/$teamName" (controller: "Player", action: "getAllPlayers", method: "GET")
            "/$id" (controller: "Player", action: "getById", method: "GET")
            "/$id" (controller: "Player", action: "update", method: "PUT")
            "/" (controller: "Player", action: "create", method: "POST")
            "/$playerName/$teamName" (controller: "Player", action: "delete", method: "DELETE")
        }

        "/pub/getCurrentBuild" (controller: "AppInfo",action:"getCurrentBuild",method:"GET")

        "/users/getCurrentUser" (controller: "AppCurrentUser",action:"initCurrentUser",method:"GET")

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')


    }//mappings
}//URLMappings
