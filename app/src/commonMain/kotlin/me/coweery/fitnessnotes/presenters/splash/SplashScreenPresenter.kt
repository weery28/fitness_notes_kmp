package me.coweery.fitnessnotes.presenters.splash

import me.coweery.fitnessnotes.mvp.BasePresenter
import me.coweery.fitnessnotes.services.token.TokenService

class SplashScreenPresenter(
    private val tokenService: TokenService
) : BasePresenter<SplashScreenContract.View>(), SplashScreenContract.Presenter {

    override fun onViewLoaded() {
        if (tokenService.getToken() == null) {
            view?.openLoginScreen()
        } else {
            view?.openMainScreen()
        }
    }
}