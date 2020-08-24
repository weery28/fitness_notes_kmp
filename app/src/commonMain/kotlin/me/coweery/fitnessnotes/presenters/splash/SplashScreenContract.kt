package me.coweery.fitnessnotes.presenters.splash

import me.coweery.fitnessnotes.mvp.BaseMvp

interface SplashScreenContract {

    interface View : BaseMvp.View {

        fun openLoginScreen()

        fun openMainScreen()
    }

    interface Presenter : BaseMvp.Presenter<View> {

        fun onViewLoaded()
    }
}