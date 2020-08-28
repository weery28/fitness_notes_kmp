package me.coweery.fitnessnotes.mvp

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal actual val Main: CoroutineDispatcher = Dispatchers.Main

internal actual val Background: CoroutineDispatcher = Dispatchers.IO