package me.coweery.fitnessnotes.services.exercise

import me.coweery.fitnessnotes.models.ExerciseCompletion
import me.coweery.fitnessnotes.models.ExerciseWithSets
import me.coweery.fitnessnotes.sqldelight.data.model.Exercise

interface ExerciseService {

    fun create(
        name: String,
        trainingId: Long,
        weight: Float?,
        count: Long,
        sets: Long,
        index: Long
    ): Exercise

    fun getByTrainingId(id: Long): List<Exercise>

    fun getFullByTrainingId(id: Long): List<ExerciseWithSets>

    fun delete(id: Long)

    fun update(exercise: Exercise)

    fun updateAll(exercises: List<Exercise>)

    fun getLastCompletion(exerciseName: String, exceptTrainingId: Long?): ExerciseCompletion

    fun getExerciseCount(trainingId: Long): Int
}