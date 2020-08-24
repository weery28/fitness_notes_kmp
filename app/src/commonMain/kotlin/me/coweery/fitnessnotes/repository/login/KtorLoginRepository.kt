package me.coweery.fitnessnotes.repository.login

import io.ktor.client.HttpClient
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.Serializable
import me.coweery.fitnessnotes.repository.post

class KtorLoginRepository(
    private val httpClient: HttpClient
) : LoginRepository {

    override suspend fun login(login: String, password: String): String {

        val response = httpClient.post<HttpResponse>("/login", LoginRequest(login, password))
        return response.headers["Authorization"] ?: throw Exception()
    }

    @Serializable
    data class LoginRequest(
        val login: String,
        val password: String
    )
}