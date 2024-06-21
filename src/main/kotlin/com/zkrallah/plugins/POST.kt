package com.zkrallah.plugins

import com.zkrallah.model.Post
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configurePostRouting() {
    routing {
        // Void response
        post("") {
            call.respond(HttpStatusCode.Created)
        }

        // String response
        post("/str") {
            try {
                val str = call.receive<String>()
                call.respond(HttpStatusCode.Created, str)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Invalid request")
            }
        }

        // Serializable response
        post("/post") {
            try {
                val post = call.receive<Post>()
                call.respond(HttpStatusCode.Created, post)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Invalid request")
            }
        }
    }
}