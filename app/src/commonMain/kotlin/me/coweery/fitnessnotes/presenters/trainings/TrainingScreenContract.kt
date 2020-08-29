package me.coweery.fitnessnotes.presenters.trainings

import me.coweery.fitnessnotes.mvp.BaseMvp
import me.coweery.fitnessnotes.sqldelight.data.model.Exercise
import me.coweery.fitnessnotes.sqldelight.data.model.Set

interface TrainingScreenContract {

    interface View : BaseMvp.View {

        fun showExercise(exercise: Exercise)

        fun showUpdateExerciseInput(exercise: Exercise)

        fun showSet(set: Set)

        fun showCreateExerciseInput(trainingId: Long)

        fun showCreateSetInput(exerciseId: Long)

        fun deleteExercise(id: Long)

        fun deleteSet(id: Long)

        fun showSetInput(set: Set)

        fun actualizeIndexes()
    }

    interface Presenter : BaseMvp.Presenter<View> {

        fun onTrainingReceived(trainingId: Long)

        fun onAddExercisesClicked()

        fun onExercisesDataReceived(exercise: Exercise)

        fun onExerciseDeleteClicked(exerciseId: Long)

        fun onExerciseEditClicked(exercise: Exercise)

        fun onSetDataReceived(set: Set)

        fun onSetClicked(exercise: Exercise, set: Set?, setIndex: Int)

        fun onSetDeleteClicked(setId: Long)

        fun onExercisesIndexesChanged(changedExercises: List<Pair<Int, Exercise>>)
    }


}