package me.coweery.fitnessnotes.repository.token

import com.russhwolf.settings.Settings

class SettingsTokenRepository(
    private val settings: Settings
) : TokenRepository {

    companion object {
        const val TOKEN_KEY = "TOKEN_KEY"
    }

    override fun get(): String? {
        return settings.getString(TOKEN_KEY)
    }

    override fun save(token: String) {
        settings.putString(TOKEN_KEY, token)
    }
}