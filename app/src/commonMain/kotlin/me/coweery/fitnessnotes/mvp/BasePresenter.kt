package me.coweery.fitnessnotes.mvp

abstract class BasePresenter<T : BaseMvp.View> : BaseMvp.Presenter<T> {

    protected var view: T? = null

    override fun attachView(view: T) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }
}