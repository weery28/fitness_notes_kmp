package me.coweery.fitnessnotes.services.set

import me.coweery.fitnessnotes.sqldelight.data.model.Set

interface SetService {

    fun create(
        exerciseId: Long,
        repsCount: Long,
        weight: Float,
        index: Long
    ): Set

    fun update(set: Set)

    fun getByExerciseId(exerciseId: Long): List<Set>

    fun delete(setId: Long)
}