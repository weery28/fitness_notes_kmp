package me.coweery.fitnessnotes.repository.token

interface TokenRepository {

    fun get(): String?

    fun save(token: String)
}