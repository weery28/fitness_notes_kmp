package me.coweery.fitnessnotes.repository.token

expect class KeyValueStore() {

    fun getString(key : String) : String?

    fun putString(key: String, value : String)
}