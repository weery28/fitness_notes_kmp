package me.coweery.fitnessnotes.repository.token

import com.github.florent37.preferences.Preferences

class PreferencesTokenRepository(
    private val preferences: Preferences
) : TokenRepository {

    companion object {
        const val TOKEN_KEY = "TOKEN_KEY"
    }

    override fun get(): String? {
        return preferences.getString(TOKEN_KEY)
    }

    override fun save(token: String) {
        preferences.setString(TOKEN_KEY, token)
    }
}