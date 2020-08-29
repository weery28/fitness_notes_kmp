package me.coweery.fitnessnotes.models

import com.soywiz.klock.DateTime
import me.coweery.fitnessnotes.sqldelight.data.model.Exercise
import me.coweery.fitnessnotes.sqldelight.data.model.Set

class ExerciseCompletion(
    val exercise: Exercise,
    val sets: List<Set>,
    val date: DateTime
)