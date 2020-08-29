package me.coweery.fitnessnotes.presenters.login

import me.coweery.Database
import me.coweery.fitnessnotes.mvp.BasePresenter
import me.coweery.fitnessnotes.services.login.LoginService
import me.coweery.fitnessnotes.services.token.TokenService

class LoginScreenPresenter(
    private val tokenService: TokenService,
    private val loginService: LoginService,
    private val database: Database
) : BasePresenter<LoginScreenContract.View>(), LoginScreenContract.Presenter {

    override fun onLoginClicked(login: String, password: String) {

        if (login.isEmpty() || login.isBlank() || password.isEmpty() || password.isBlank()) {
            return
        }
        executeBlocking {
            try {
                val token = loginService.login(login, password)
                tokenService.saveToken(token)
                executeInMain {
                    view?.openMainScreen()
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}