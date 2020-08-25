package me.coweery.fitnessnotes.presenters.login

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.coweery.fitnessnotes.mvp.Background
import me.coweery.fitnessnotes.mvp.BasePresenter
import me.coweery.fitnessnotes.mvp.Main
import me.coweery.fitnessnotes.services.login.LoginService
import me.coweery.fitnessnotes.services.token.TokenService

class LoginScreenPresenter(
    private val tokenService: TokenService,
    private val loginService: LoginService
) : BasePresenter<LoginScreenContract.View>(), LoginScreenContract.Presenter {

    override fun onLoginClicked(login: String, password: String) {

        if (login.isEmpty() || login.isBlank() || password.isEmpty() || password.isBlank()) {
            return
        }
        GlobalScope.launch(Background) {
            try {
                val token = loginService.login(login, password)
                tokenService.saveToken(token)
                withContext(Main){
                    view?.openMainScreen()
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}