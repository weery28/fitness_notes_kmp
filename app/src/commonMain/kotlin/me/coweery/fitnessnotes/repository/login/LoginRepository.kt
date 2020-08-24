package me.coweery.fitnessnotes.repository.login

interface LoginRepository {

    suspend fun login(login: String, password: String): String
}