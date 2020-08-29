package me.coweery.fitnessnotes.services.exercise

import com.soywiz.klock.Date
import com.soywiz.klock.DateTime
import me.coweery.fitnessnotes.models.ExerciseCompletion
import me.coweery.fitnessnotes.models.ExerciseWithSets
import me.coweery.fitnessnotes.sqldelight.data.model.Exercise
import me.coweery.fitnessnotes.sqldelight.data.model.ExerciseQueries
import me.coweery.fitnessnotes.sqldelight.data.model.Set
import me.coweery.fitnessnotes.sqldelight.data.model.SetQueries
import me.coweery.fitnessnotes.sqldelight.data.model.TrainingQueries

class ExerciseServiceImpl(
    private val exerciseQueries: ExerciseQueries,
    private val trainingQueries: TrainingQueries,
    private val setQueries: SetQueries
) : ExerciseService {

    override fun create(
        name: String,
        trainingId: Long,
        weight: Float?,
        count: Long,
        sets: Long,
        index: Long
    ): Exercise {

        var id: Long? = null
        exerciseQueries.transaction {
            exerciseQueries.insert(name, trainingId, weight, count, sets, index)
            id = exerciseQueries.lastInsertRowId().executeAsOne()
        }
        return Exercise.Impl(id!!, name, trainingId, weight, count, sets, index)
    }

    override fun getByTrainingId(id: Long): List<Exercise> {
        return exerciseQueries.fetchByTrainingId(id).executeAsList()
    }

    override fun getFullByTrainingId(id: Long): List<ExerciseWithSets> {

        return setQueries.fetchSetsWithExercisesByExerciceId(1)
            .executeAsList()
            .groupBy {
                it.exerciseId
            }
            .map {

                val exercise = it.value.first().let {
                    Exercise.Impl(
                        it.exerciseId,
                        it.exerciseName,
                        it.exerciseTrainingId,
                        it.exerciseWeight,
                        it.exerciseCount,
                        it.exerciseSets,
                        it.exerciseIndex
                    )
                }

                ExerciseWithSets(
                    exercise,
                    it.value.map {
                        Set.Impl(
                            it.setId,
                            it.setExerciseId,
                            it.setRepsCount,
                            it.setWeight,
                            it.setIndex
                        )
                    }
                )
            }
    }

    override fun delete(id: Long) {
        exerciseQueries.delete(id)
    }

    override fun update(exercise: Exercise) {
        exerciseQueries.updateById(
            exercise.name,
            exercise.trainingId,
            exercise.weight,
            exercise.count,
            exercise.sets,
            exercise.index,
            exercise.id
        )
    }

    override fun updateAll(exercises: List<Exercise>) {
        exercises.forEach(this::update)
    }

    override fun getLastCompletion(
        exerciseName: String,
        exceptTrainingId: Long?
    ): ExerciseCompletion {

        val training = if (exceptTrainingId == null) {
            trainingQueries.findLastWithExercise(exerciseName)
                .executeAsOne()
        } else {
            trainingQueries.findLastWithExerciseExceptId(exerciseName, exceptTrainingId)
                .executeAsOne()
        }

        val exercise = exerciseQueries.fetchByTrainingIdAndName(training.id, exerciseName)
            .executeAsOne()

        val sets = setQueries.fetchByExerciseId(exercise.id).executeAsList()

        return ExerciseCompletion(
            exercise, sets, DateTime(training.date!!)
        )
    }

    override fun getExerciseCount(trainingId: Long): Int {
        return exerciseQueries.getExerciseCount(trainingId).executeAsOne().toInt()
    }
}