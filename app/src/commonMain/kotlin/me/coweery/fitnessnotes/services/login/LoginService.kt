package me.coweery.fitnessnotes.services.login

interface LoginService {

    suspend fun login(login: String, password: String): String
}