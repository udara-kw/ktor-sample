package com.udarkw

import com.udarkw.plugins.configureRouting
import com.udarkw.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*

fun main() {
    embeddedServer(Netty, port = 9665, host = "127.0.0.1", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(CORS) {
        anyHost()
    }

//    Uncomment Security, HTTP and monitoring if needed
//    configureSecurity()
//    configureHTTP()
//    configureMonitoring()

    configureSerialization()
    configureRouting()
}
