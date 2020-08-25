package me.coweery.fitnessnotes.repository.token

import com.ironz.binaryprefs.BinaryPreferencesBuilder
import me.coweery.fitnessnotes.AppContext

actual class KeyValueStore {

    private val sharedPreferences = BinaryPreferencesBuilder(AppContext.instance)
        .build()

    actual fun getString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    actual fun putString(key: String, value: String) {

        sharedPreferences.edit()
            .putString(key, value)
            .commit()
    }
}