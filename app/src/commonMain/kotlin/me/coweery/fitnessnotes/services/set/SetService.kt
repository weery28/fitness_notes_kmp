package me.coweery.fitnessnotes.services.set

import me.coweery.fitnessnotes.sqldelight.data.model.Set

interface SetService {

    fun createOrUpdate(set: Set): Set

    fun getByExerciseId(exerciseId: Long): List<Set>

    fun delete(setId: Long)
}