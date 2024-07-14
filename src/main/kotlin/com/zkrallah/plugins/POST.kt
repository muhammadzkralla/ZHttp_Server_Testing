package com.zkrallah.plugins

import com.zkrallah.model.Complex
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

        // List response
        post("/posts") {
            val posts = call.receive<List<Post>>()

            if (posts.isNotEmpty()) {
                call.respond(HttpStatusCode.Created, posts)
            } else {
                call.respond(HttpStatusCode.InternalServerError, "Invalid ID format")
            }
        }

        // Params response
        post("/post/{id}") {
            val postId = call.parameters["id"]?.toIntOrNull()
            val post = call.receive<Post>()
            println(post)

            if (postId != null) {
                call.respond(HttpStatusCode.Created, post)
            } else {
                call.respond(HttpStatusCode.InternalServerError, "Invalid ID format")
            }
        }

        // Complex response
        post("/complex") {
            try {
                val complex = call.receive<Complex>()
                call.respond(HttpStatusCode.Created, complex)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Invalid ID format")
            }
        }

        // Map response
        post("/map") {
            try {
                val map = call.receive<Map<Any, Any>>()
                call.respond(HttpStatusCode.Created, map)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Invalid ID format")
            }
        }
    }
}