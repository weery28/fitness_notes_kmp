package me.coweery.fitnessnotes.screens.training.list

import java.util.Date

interface Output {

    fun onTrainingDataReceived(name : String, date : Date)
}