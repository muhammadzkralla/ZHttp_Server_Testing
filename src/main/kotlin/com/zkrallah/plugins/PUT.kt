package com.zkrallah.plugins

import com.zkrallah.model.Post
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configurePutRouting() {
    routing {
        // Void response
        put("") {
            call.respond(HttpStatusCode.Created)
        }

        // String response
        put("/str") {
            try {
                val str = call.receive<String>()
                call.respond(HttpStatusCode.Created, str)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Invalid request")
            }
        }

        // Serializable response
        put("/post") {
            try {
                val post = call.receive<Post>()
                call.respond(HttpStatusCode.Created, post)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Invalid request")
            }
        }
    }
}
