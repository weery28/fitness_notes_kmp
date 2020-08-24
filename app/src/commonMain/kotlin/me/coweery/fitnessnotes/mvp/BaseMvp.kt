package me.coweery.fitnessnotes.mvp

interface BaseMvp {

    interface View

    interface Presenter<T : View> {

        fun attachView(view: T)

        fun detachView()
    }
}