package me.coweery.fitnessnotes.services.training

import me.coweery.fitnessnotes.sqldelight.data.model.Training

interface TrainingService {

    fun getAll(): List<Training>

    fun save(
        name: String,
        creationDate: Long,
        date: Long?,
        isSynced: Boolean,
        serverId: Long?,
        isComplete : Boolean
    ): Training

    fun get(id: Long): Training
}