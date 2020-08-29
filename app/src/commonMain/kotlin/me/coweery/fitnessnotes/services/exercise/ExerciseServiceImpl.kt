package me.coweery.fitnessnotes.services.exercise

import me.coweery.fitnessnotes.models.ExerciseCompletion
import me.coweery.fitnessnotes.models.ExerciseWithSets
import me.coweery.fitnessnotes.sqldelight.data.model.Exercise
import me.coweery.fitnessnotes.sqldelight.data.model.ExerciseQueries

class ExerciseServiceImpl(
    private val exerciseQueries: ExerciseQueries
) : ExerciseService {

    override fun create(
        name: String,
        trainingId: Long,
        weight: Float?,
        count: Long,
        sets: Long,
        index: Long
    ): Exercise {

    }

    override fun getByTrainingId(id: Long): List<Exercise> {
        TODO("Not yet implemented")
    }

    override fun getFullByTrainingId(id: Long): List<ExerciseWithSets> {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long) {
        TODO("Not yet implemented")
    }

    override fun update(exercise: Exercise) {
        TODO("Not yet implemented")
    }

    override fun updateAll(exercises: List<Exercise>) {
        TODO("Not yet implemented")
    }

    override fun getLastCompletion(
        exerciseName: String,
        exceptTrainingId: Long?
    ): ExerciseCompletion {
        TODO("Not yet implemented")
    }

    override fun getExerciseCount(trainingId: Long): Int {
        TODO("Not yet implemented")
    }
}