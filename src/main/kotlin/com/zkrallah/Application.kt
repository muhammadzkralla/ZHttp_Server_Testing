package com.zkrallah

import com.zkrallah.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.tomcat.*

fun main() {
    embeddedServer(Tomcat, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureGetRouting()
    configureDeleteRouting()
    configurePostRouting()
    configurePutRouting()
    configurePatchRouting()
    configureMultipartRouting()
}
