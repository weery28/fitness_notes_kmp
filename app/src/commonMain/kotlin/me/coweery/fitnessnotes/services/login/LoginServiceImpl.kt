package me.coweery.fitnessnotes.services.login

import me.coweery.fitnessnotes.repository.login.LoginRepository

class LoginServiceImpl(
    private val loginRepository: LoginRepository
) : LoginService {

    override suspend fun login(login: String, password: String): String {
        return loginRepository.login(login, password)
    }
}