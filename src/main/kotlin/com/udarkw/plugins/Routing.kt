package com.udarkw.plugins

import com.udarkw.routes.customerRouting
import com.udarkw.routes.getOrderRoute
import com.udarkw.routes.listOrderRoutes
import com.udarkw.routes.totalizeOrderRoute
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.plugins.doublereceive.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.resources.*
import io.ktor.server.resources.Resources
import kotlinx.serialization.Serializable
import io.ktor.server.application.*

fun Application.configureRouting() {
    install(AutoHeadResponse)
    install(DoubleReceive)
    install(Resources)
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        post("/double-receive") {
            val first = call.receiveText()
            val theSame = call.receiveText()
            call.respondText(first + " " + theSame)
        }
        get<Articles> { article ->
            // Get all articles ...
            call.respond("List of articles sorted starting from ${article.sort}")
        }
        customerRouting()
        listOrderRoutes()
        getOrderRoute()
        totalizeOrderRoute()
    }
}

@Serializable
@Resource("/articles")
class Articles(val sort: String? = "new")
