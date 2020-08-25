package me.coweery.fitnessnotes.repository.token

import platform.Foundation.NSUserDefaults
import platform.Foundation.setValue

actual class KeyValueStore {

    private val nsUserDefaults = NSUserDefaults()

    actual fun getString(key : String) : String? = nsUserDefaults.stringForKey(key)

    actual fun putString(key: String, value : String) = nsUserDefaults.setValue(value, forKey = key)
}