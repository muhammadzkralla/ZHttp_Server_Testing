package com.zkrallah.plugins

import com.google.gson.Gson
import com.zkrallah.model.Images
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Application.configureMultipartRouting() {
    routing {
        post("images") {
            val multipart = call.receiveMultipart()
            val id = 101
            var userId = -1
            var title = ""
            val images = mutableListOf<String>()
            multipart.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        val postItem = Gson().fromJson(part.value, Images::class.java)
                        title = postItem.title!!
                        userId = postItem.userId!!
                    }

                    is PartData.FileItem -> {
                        try {
                            part.save("build/resources/main/static/images/", "${part.name!!}.jpg")
                            images.add("build/resources/main/static/images/${part.name!!}.jpg")
                        } catch (e: Exception) {
                            call.respond(HttpStatusCode.BadRequest, e.message.toString())
                        }
                    }

                    else -> Unit
                }
            }
            val createdImages = Images(id, userId, title, images)
            call.respond(HttpStatusCode.Created, createdImages)
        }
    }
}

fun PartData.FileItem.save(path: String, fileName: String): String {
    val fileBytes = streamProvider().readBytes()
    val folder = File(path)
    folder.mkdirs()
    File("$path$fileName").writeBytes(fileBytes)
    return fileName
}