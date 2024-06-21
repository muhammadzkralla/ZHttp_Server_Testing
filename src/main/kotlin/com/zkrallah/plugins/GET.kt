package com.zkrallah.plugins

import com.zkrallah.model.Complex
import com.zkrallah.model.Post
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

fun Application.configureGetRouting() {
    install(ContentNegotiation) {
        json(
            Json {
                // Configure Kotlin serialization here if needed
                encodeDefaults = true
            }
        )
    }

    routing {

        // Void response
        get("") {
            call.respond(HttpStatusCode.OK)
        }

        // String response
        get("/str") {
            call.respond(HttpStatusCode.OK,"Hello World!")
        }

        // Serializable response
        get("/post") {
            val post = Post(
                id = 21,
                userId = 1,
                title = "Title",
                body = "Random post body."
            )
            call.respond(HttpStatusCode.OK, post)
        }

        // Params response
        get("/params") {
            try {
                val param1 = call.parameters["param1"] ?: throw IllegalArgumentException("param1 is required")
                val param2 = call.parameters["param2"] ?: throw IllegalArgumentException("param2 must be an integer")

                val data = Post(
                    id = 21,
                    userId = 1,
                    title = param1,
                    body = param2
                )

                call.respond(HttpStatusCode.OK, data)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Invalid JSON format")
            }
        }

        // Complex response
        get("/complex") {
            try {
                val post1 = Post(101, 99, "Title1", "Body1")
                val post2 = Post(102, 99, "Title2", "Body2")
                val post3 = Post(101, 99, "Title3", "Body1")
                val post4 = Post(102, 99, "Title2", "Body2")

                val map = mapOf(
                    1 to post1,
                    2 to post2,
                    3 to post3,
                    4 to post4
                )

                val set = setOf(
                    post1, post2, post3, post4
                )

                val list = listOf(
                    "1", "test", "2", "5.0"
                )

                val complex = Complex(
                    map,
                    set,
                    list,
                    "string",
                    5L
                )

                call.respond(HttpStatusCode.OK, complex)
            }  catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Invalid JSON format")
            }
        }

        // Map response
        get("/map") {
            try {
                val map = mapOf<Any, Any>(
                    "test" to 1,
                    2 to "any",
                    "random" to 1.5
                )
                call.respond(HttpStatusCode.OK, map)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Invalid JSON format")
            }
        }
    }
}
