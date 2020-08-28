package me.coweery.fitnessnotes.presenters.trainings.list

import com.soywiz.klock.DateTime
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.coweery.fitnessnotes.mvp.Background
import me.coweery.fitnessnotes.mvp.BasePresenter
import me.coweery.fitnessnotes.mvp.Main
import me.coweery.fitnessnotes.mvp.doInMain
import me.coweery.fitnessnotes.services.training.TrainingService

class TrainingsListPresenter(
    private val trainingService: TrainingService
) : BasePresenter<TrainingsListContract.View>(),
    TrainingsListContract.Presenter {

    override fun onScreenLoaded() {
        GlobalScope.launch(Background) {
            val trainings = trainingService.getAll()
            withContext(Main) {
                view?.showTrainings(trainings)
            }
        }
    }

    override fun onAddTrainingClicked() {
        view?.showCreateTrainingScreen()
    }

    override fun onTrainingClicked(id: Long) {
        view?.showTrainingScreen(id)
    }

    override fun onTrainingDataReceived(name: String, date: Long) {
        GlobalScope.launch(Background) {
            val training = trainingService.save(
                name,
                DateTime.nowUnixLong(),
                date,
                false,
                null
            )
            doInMain { view?.showTrainingScreen(training.id) }
        }
    }
}