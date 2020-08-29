package me.coweery.fitnessnotes.presenters.trainings

import me.coweery.fitnessnotes.mvp.BasePresenter
import me.coweery.fitnessnotes.services.exercise.ExerciseService
import me.coweery.fitnessnotes.services.set.SetService
import me.coweery.fitnessnotes.sqldelight.data.model.Exercise
import me.coweery.fitnessnotes.sqldelight.data.model.Set

class TrainingScreenPresenter(
    private val exercisesService: ExerciseService,
    private val setsService: SetService

) : BasePresenter<TrainingScreenContract.View>(),
    TrainingScreenContract.Presenter {

    private var mTrainingId = -1L

    override fun onTrainingReceived(trainingId: Long) {

        mTrainingId = trainingId

        executeBlocking {
            exercisesService.getFullByTrainingId(trainingId)
                .asSequence()
                .sortedBy { it.exercise.index }
                .forEach {
                    executeInMain {
                        view?.showExercise(it.exercise)
                        it.sets.forEach {
                            view?.showSet(it)
                        }
                    }
                }
        }
    }

    override fun onAddExercisesClicked() {
        view?.showCreateExerciseInput(mTrainingId)
    }

    override fun onExercisesDataReceived(exercise: Exercise) {
        TODO("Not yet implemented")
    }

    override fun onExerciseDeleteClicked(exerciseId: Long) {
        TODO("Not yet implemented")
    }

    override fun onExerciseEditClicked(exercise: Exercise) {
        TODO("Not yet implemented")
    }

    override fun onSetDataReceived(set: Set) {
        TODO("Not yet implemented")
    }

    override fun onSetClicked(exercise: Exercise, set: Set?, setIndex: Int) {
        TODO("Not yet implemented")
    }

    override fun onSetDeleteClicked(setId: Long) {
        TODO("Not yet implemented")
    }

    override fun onExercisesIndexesChanged(changedExercises: List<Pair<Int, Exercise>>) {
        TODO("Not yet implemented")
    }
}