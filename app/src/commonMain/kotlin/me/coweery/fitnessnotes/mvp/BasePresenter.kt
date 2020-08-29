package me.coweery.fitnessnotes.mvp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BasePresenter<T : BaseMvp.View> : BaseMvp.Presenter<T> {

    protected var view: T? = null
    private val job = Job()

    override fun attachView(view: T) {
        this.view = view
    }

    override fun detachView() {
        view = null
        job.cancel()
    }

    protected fun executeBlocking(blockingCode : suspend CoroutineScope.() -> Unit){

        GlobalScope.launch(Background + job){
            blockingCode(this)
        }
    }

    protected suspend fun CoroutineScope.executeInMain(nonBlockingCode : () -> Unit) {
        withContext(Main){
            nonBlockingCode()
        }
    }
}