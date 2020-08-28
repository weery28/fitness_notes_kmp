package me.coweery.fitnessnotes.mvp

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

internal expect val Main: CoroutineDispatcher

internal expect val Background: CoroutineDispatcher

fun CoroutineScope.doInMain(action : () -> Unit){

    GlobalScope.launch(Main){
        action()
    }
}

fun CoroutineScope.executeBlocking(blockingCode : () -> Unit) {

    GlobalScope.launch(Background) {
        blockingCode()
    }
}