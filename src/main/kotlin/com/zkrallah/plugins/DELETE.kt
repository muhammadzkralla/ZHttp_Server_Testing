package com.zkrallah.plugins

import com.zkrallah.model.Post
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureDeleteRouting() {
    routing {
        // Void response
        delete("") {
            call.respond(HttpStatusCode.Accepted)
        }

        // String response
        delete("/str") {
            call.respond(HttpStatusCode.Accepted,"Deleted!")
        }

        // Serializable response
        delete("/post") {
            val post = Post(
                id = 21,
                userId = 1,
                title = "Title",
                body = "Random post body."
            )
            call.respond(HttpStatusCode.Accepted, post)
        }

        // Params response
        delete("/post/{id}") {
            val postId = call.parameters["id"]?.toIntOrNull()

            if (postId != null) {
                call.respond(HttpStatusCode.Accepted, postId)
            } else {
                call.respond(HttpStatusCode.InternalServerError, "Invalid ID format")
            }
        }
    }
}
