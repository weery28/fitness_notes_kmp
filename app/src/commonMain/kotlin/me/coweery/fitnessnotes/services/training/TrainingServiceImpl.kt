package me.coweery.fitnessnotes.services.training

import me.coweery.fitnessnotes.sqldelight.data.model.Training
import me.coweery.fitnessnotes.sqldelight.data.model.TrainingQueries

class TrainingServiceImpl(
    private val trainingQueries: TrainingQueries
) : TrainingService {

    override fun getAll(): List<Training> {
        return trainingQueries.selectAll().executeAsList()
    }

    override fun save(
        name: String,
        creationDate: Long?,
        date: Long?,
        isSynced: Boolean?,
        serverId: Long?
    ): Training {

        var id: Long? = null
        trainingQueries.transaction {
            trainingQueries.insert(name, creationDate, date, isSynced, serverId)
            id = trainingQueries.lastInserRowId().executeAsOne()
        }
        return Training.Impl(id!!, name, creationDate, date, isSynced, serverId)
    }

    override fun get(id: Long): Training {
        return trainingQueries.findById(id).executeAsOne()
    }
}