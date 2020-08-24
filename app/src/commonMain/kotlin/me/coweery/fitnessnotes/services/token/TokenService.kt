package me.coweery.fitnessnotes.services.token

interface TokenService {

    fun getToken(): String?

    fun saveToken(token: String)
}