package me.coweery.fitnessnotes.presenters.trainings.list

import com.soywiz.klock.DateTime
import me.coweery.fitnessnotes.mvp.BasePresenter
import me.coweery.fitnessnotes.services.training.TrainingService

class TrainingsListPresenter(
    private val trainingService: TrainingService
) : BasePresenter<TrainingsListContract.View>(),
    TrainingsListContract.Presenter {

    override fun onScreenLoaded() {
        executeBlocking {
            val trainings = trainingService.getAll()
            executeInMain { view?.showTrainings(trainings) }
        }
    }

    override fun onAddTrainingClicked() {
        view?.showCreateTrainingScreen()
    }

    override fun onTrainingClicked(id: Long) {
        view?.showTrainingScreen(id)
    }

    override fun onTrainingDataReceived(name: String, date: Long) {
        executeBlocking {
            val training = trainingService.save(
                name,
                DateTime.nowUnixLong(),
                date,
                false,
                null,
                false
            )
            executeInMain { view?.showTrainingScreen(training.id) }
        }
    }
}