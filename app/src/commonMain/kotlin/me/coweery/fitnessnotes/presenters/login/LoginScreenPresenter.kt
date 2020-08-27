package me.coweery.fitnessnotes.presenters.login

import io.ktor.util.date.GMTDate
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.coweery.Database
import me.coweery.fitnessnotes.mvp.Background
import me.coweery.fitnessnotes.mvp.BasePresenter
import me.coweery.fitnessnotes.mvp.Main
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

        GlobalScope.launch(Background) {
            database.trainingQueries.insert(
                "test",
                GMTDate().timestamp,
                GMTDate().timestamp,
                false,
                null
            )

            database.trainingQueries.selectAll { id, name, creationDate, date, isSynced, serverId ->
                id
            }.executeAsList()
                .forEach {
                    println(it)
                }
        }
    }
}