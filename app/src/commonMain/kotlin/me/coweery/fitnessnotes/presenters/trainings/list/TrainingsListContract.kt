package me.coweery.fitnessnotes.presenters.trainings.list

import me.coweery.fitnessnotes.mvp.BaseMvp
import me.coweery.fitnessnotes.sqldelight.data.model.Training

interface TrainingsListContract {

    interface View : BaseMvp.View {

        fun showTrainings(list: List<Training>)

        fun showCreateTrainingScreen()

        fun showTrainingScreen(id: Long)
    }

    interface Presenter : BaseMvp.Presenter<View> {

        fun onScreenLoaded()

        fun onAddTrainingClicked()

        fun onTrainingClicked(id: Long)

        fun onTrainingDataReceived(name: String, date: Long)
    }
}