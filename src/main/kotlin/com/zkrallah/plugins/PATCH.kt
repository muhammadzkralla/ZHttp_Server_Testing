package com.zkrallah.plugins

import com.zkrallah.model.Complex
import com.zkrallah.model.Post
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configurePatchRouting() {
    routing {
        // Void response
        patch("") {
            call.respond(HttpStatusCode.Created)
        }

        // String response
        patch("/str") {
            try {
                val str = call.receive<String>()
                call.respond(HttpStatusCode.Created, str)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Invalid request")
            }
        }

        // Serializable response
        patch("/post") {
            try {
                val post = call.receive<Post>()
                call.respond(HttpStatusCode.Created, post)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Invalid request")
            }
        }

        // List response
        patch("/posts") {
            val posts = call.receive<List<Post>>()

            if (posts.isNotEmpty()) {
                call.respond(HttpStatusCode.Created, posts)
            } else {
                call.respond(HttpStatusCode.InternalServerError, "Invalid ID format")
            }
        }

        // Params response
        patch("/post/{id}") {
            val postId = call.parameters["id"]?.toIntOrNull()
            val post = call.receive<Post>()

            if (postId != null) {
                call.respond(HttpStatusCode.Created, post)
            } else {
                call.respond(HttpStatusCode.InternalServerError, "Invalid ID format")
            }
        }

        // Complex response
        patch("/complex") {
            try {
                val complex = call.receive<Complex>()
                call.respond(HttpStatusCode.Created, complex)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Invalid ID format")
            }
        }

        // Map response
        patch("/map") {
            try {
                val map = call.receive<Map<Any, Any>>()
                call.respond(HttpStatusCode.Created, map)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Invalid ID format")
            }
        }
    }
}