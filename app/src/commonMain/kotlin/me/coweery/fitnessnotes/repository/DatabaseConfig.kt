package me.coweery.fitnessnotes.repository

import com.squareup.sqldelight.db.SqlDriver
import me.coweery.Database

expect class DriverFactory() {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): Database {
    val driver = driverFactory.createDriver()
    val database = Database(driver)

    return database
}