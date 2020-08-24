package me.coweery.fitnessnotes.repository

import io.ktor.client.HttpClient
import io.ktor.client.features.json.defaultSerializer
import io.ktor.client.request.post

object KtorConfig {

    val baseUrl = ""
    val defaultSerializer = defaultSerializer()
}

suspend inline fun <reified T> HttpClient.post(url: String, body: Any): T {

    return post<T>("http://95.213.143.115:8080$url") {
        this.body = KtorConfig.defaultSerializer.write(body)
        println(body.toString())
    }
}