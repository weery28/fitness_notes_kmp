package me.coweery.fitnessnotes.services.set

import me.coweery.fitnessnotes.sqldelight.data.model.Set
import me.coweery.fitnessnotes.sqldelight.data.model.SetQueries

class SetServiceImpl(
    private val setQueries: SetQueries
) : SetService {

    override fun create(exerciseId: Long, repsCount: Long, weight: Float, index: Long): Set {

        var id : Long? = null
        setQueries.transaction {
            setQueries.insert(exerciseId, repsCount, weight, index)
            id = setQueries.lastInsertRowId().executeAsOne()
        }
        return Set.Impl(id!!, exerciseId, repsCount, weight, index)
    }

    override fun update(set: Set) {

        setQueries.updateById(
            set.exerciseId, set.repsCount, set.weight, set.index, set.id
        )
    }

    override fun getByExerciseId(exerciseId: Long): List<Set> {
        return setQueries.fetchByExerciseId(exerciseId).executeAsList()
    }

    override fun delete(setId: Long) {
        setQueries.delete(setId)
    }
}