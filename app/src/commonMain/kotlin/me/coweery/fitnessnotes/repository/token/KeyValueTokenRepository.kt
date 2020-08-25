package me.coweery.fitnessnotes.repository.token

class KeyValueTokenRepository(
    private val storage: KeyValueStore
) : TokenRepository {

    companion object {
        const val TOKEN_KEY = "TOKEN_KEY"
    }

    override fun get(): String? {
        return storage.getString(TOKEN_KEY)
    }

    override fun save(token: String) {
        storage.putString(TOKEN_KEY, token)
    }
}