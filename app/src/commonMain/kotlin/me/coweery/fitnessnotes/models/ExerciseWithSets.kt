package me.coweery.fitnessnotes.models

import me.coweery.fitnessnotes.sqldelight.data.model.Exercise
import me.coweery.fitnessnotes.sqldelight.data.model.Set

class ExerciseWithSets(
    val exercise: Exercise,
    val sets: List<Set>
)