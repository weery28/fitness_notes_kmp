package me.coweery.fitnessnotes.presenters.login

import me.coweery.fitnessnotes.mvp.BaseMvp

interface LoginScreenContract {

    interface View : BaseMvp.View {

        fun openMainScreen()
    }

    interface Presenter : BaseMvp.Presenter<View> {

        fun onLoginClicked(login: String, password: String)
    }
}