package me.coweery.fitnessnotes.repository

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import me.coweery.Database
import me.coweery.fitnessnotes.AppContext

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(Database.Schema, AppContext.instance, "app.db")
    }
}